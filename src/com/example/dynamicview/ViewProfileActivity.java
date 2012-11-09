package com.example.dynamicview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.dynamicview.helpers.ProfileInfoHolder;
import com.example.dynamicview.helpers.UtilityClass;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfileActivity extends Activity {
	Button editProfile;
	ProfileInfoHolder profileInfoHolder;

	ImageView userProfileImage;
	public static final int EDIT_PROFILE_DATA = 1212;
	public static final int CAMERA_UPLOAD = 1313;
	public static final int MEDIA_UPLOAD = 1888;
	TextView detailUserData;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_page_display);
		initViews();

	};

	public void initViews() {
		profileInfoHolder = new ProfileInfoHolder();
		editProfile = (Button) findViewById(R.id.btnEditProfile);

		editProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent editDataIntent = new Intent(ViewProfileActivity.this,
						EditProfileActivity.class);
				startActivityForResult(editDataIntent, EDIT_PROFILE_DATA);

			}
		});
		detailUserData = (TextView) findViewById(R.id.detailUserData);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("Result Code", resultCode + "");
		if (requestCode == EDIT_PROFILE_DATA) {

			String detailInformation = "";
			LinkedHashMap<String, String> userFields = new LinkedHashMap<String, String>();
			userFields = UtilityClass.EDIT_PROFILE_WITH_FIELD_NAME;

			// testing testing
			String fieldValueAndPrivacy = null;// = new
												// ArrayList<String>();
			LinkedHashMap<String, ArrayList<String>> editProfileHashValues = new LinkedHashMap<String, ArrayList<String>>();

			Iterator fieldIterator = userFields.entrySet().iterator();
			while (fieldIterator.hasNext()) {
				Map.Entry fieldPairs = (Map.Entry) fieldIterator.next();
				String fieldName = (String) fieldPairs.getKey();
				String fieldRunTimeValue = (String) fieldPairs.getValue();
				detailInformation += fieldName + " => " + fieldRunTimeValue
						+ "\n";

			}
			detailUserData.setText(detailInformation);

		}
	}
}
