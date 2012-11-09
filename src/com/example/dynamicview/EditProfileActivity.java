package com.example.dynamicview;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynamicview.dynamic_views.DynamicFormCreation;
import com.example.dynamicview.dynamic_views.StoreDynamicFormValues;
import com.example.dynamicview.helpers.Field;
import com.example.dynamicview.helpers.JsonParser;
import com.example.dynamicview.helpers.ProfileInfoHolder;
import com.example.dynamicview.helpers.UtilityClass;

/**
 * @author prativa
 * 
 */
public class EditProfileActivity extends Activity {
	TextView textView;

	private ActionMode actionMode;

	private String JSON_FILENAME = "jsonresponse.txt";
	DynamicFormCreation runTimeUiLibs;
	LinearLayout editPageLayout;
	LinearLayout.LayoutParams layoutParamsBasic;
	Button saveBtn;
	ArrayList<Field> fieldValuesArray;
	ProfileInfoHolder profileDetails;
	
	int intToDpi10;
	int count = 0;
	private LinkedHashMap<String, Field> fieldMapList;
	private StoreDynamicFormValues storeDynamicFormValues = new StoreDynamicFormValues();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		initViews();
		try {
			fetchJsonDataFromAssets();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initViews() {
		initDpiData();
		profileDetails = new ProfileInfoHolder();
		runTimeUiLibs = new DynamicFormCreation(EditProfileActivity.this);
		editPageLayout = (LinearLayout) findViewById(R.id.linearLayoutForEdit);
		layoutParamsBasic = UtilityClass.getLayoutParams();

	}

	public void initDpiData() {
		intToDpi10 = UtilityClass.intToDpi(10, EditProfileActivity.this);
	}

	public void fetchJsonDataFromAssets() throws IOException {

		String jsonLocation = convertJsonToStringFromAssetFolder(JSON_FILENAME);
		Log.i("Json Data Are", jsonLocation);
		parseJsonFileToJavaObjects(jsonLocation);// this function parses the
													// json data to java objects
	}

	public String convertJsonToStringFromAssetFolder(String fileName)
			throws IOException {
		AssetManager manager = this.getAssets();
		InputStream file = manager.open(fileName);

		byte[] data = new byte[file.available()];
		file.read(data);
		file.close();
		return new String(data);
	}

	public void parseJsonFileToJavaObjects(String json) {

		if (json != null) {
			try {
				ProfileInfoHolder.fieldHashValues = JsonParser
						.parseUserProfileDetails(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			createRegistrationForm(ProfileInfoHolder.fieldHashValues);

		} else {
			Toast.makeText(EditProfileActivity.this, "Null JSON",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void createRegistrationForm(
			LinkedHashMap<String, ArrayList<Field>> fieldHashValues) {

		if (fieldHashValues.size() > 0) {
			fieldMapList = new LinkedHashMap<String, Field>();
			Iterator it = fieldHashValues.entrySet().iterator();
			fieldValuesArray = new ArrayList<Field>();
			count = count + 1;
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();

				// create field header and its run time UI
				String fieldHeader = (String) pairs.getKey();
				addFieldHeaderTextView(fieldHeader);

				// iteratively create fields of each field header
				fieldValuesArray = (ArrayList<Field>) pairs.getValue();
				int length = fieldValuesArray.size();
				count = count + 1;

				for (int i = 0; i < length; i++) {

					Field field = fieldValuesArray.get(i);
					count = count + 1;
					int lastCoreFieldId = count++;

					View view = runTimeUiLibs.getRuntimeView(field,
							lastCoreFieldId);

					if (view != null) {
						LinearLayout fieldAndPrivacyLayout = UtilityClass
								.getLinearLayoutHorizontal(EditProfileActivity.this);

						view.setLayoutParams(layoutParamsBasic);

						fieldAndPrivacyLayout.addView(view);

						editPageLayout.addView(fieldAndPrivacyLayout);
					}
					Log.i("Id Checkin in Edit Profile",
							String.valueOf(view.getId()));
					fieldMapList.put(String.valueOf(view.getId()), field);

				}

			}

			it.remove(); // avoids a ConcurrentModificationException
			addSaveButton();
		} else {
			Toast.makeText(this, "Error fetching json file", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void addFieldHeaderTextView(String fieldHeader) {
		TextView tv = new TextView(EditProfileActivity.this);
		tv.setText(fieldHeader);
		layoutParamsBasic = UtilityClass.getLayoutParamsFillParentWidth();
		tv.setLayoutParams(layoutParamsBasic);
		editPageLayout.addView(tv);
	}

	public void addSaveButton() {
		// add save button at the last of the fields
		saveBtn = UtilityClass.getButton(EditProfileActivity.this, "Save");

		layoutParamsBasic = UtilityClass.getLayoutParamsFillParentWidth();
		layoutParamsBasic.setMargins(intToDpi10, intToDpi10, intToDpi10,
				intToDpi10);
		saveBtn.setLayoutParams(layoutParamsBasic);
		saveBtn.setOnClickListener(saveButtonClickListener);
		editPageLayout.addView(saveBtn);
	}

	private OnClickListener saveButtonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// the runtime user inputs and privacy fields are accessed through
			// getRegistration form fields
			storeDynamicFormValues.getRegistrationFormFields(editPageLayout,
					fieldMapList);
			if (storeDynamicFormValues.inCorrectFieldsList.size() > 0) {
				Toast.makeText(EditProfileActivity.this,
						"Please Fill Form Correctly", Toast.LENGTH_SHORT)
						.show();
				storeDynamicFormValues.inCorrectFieldsList.clear();
			} else {

				sendEditProfileDataToServer();

			}
		}
	};

	/*
	 * the data of edit profile is sent to the view profile activity
	 */
	public void sendEditProfileDataToServer() {

		UtilityClass.EDIT_PROFILE_WITH_FIELD_NAME = storeDynamicFormValues.displayFieldsMap;
		finish();
	}

}
