package com.example.dynamicview.dynamic_views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.dynamicview.helpers.Field;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoreDynamicFormValues {
	public ArrayList<TextView> inCorrectFieldsList;
	public LinkedHashMap<String, String> postFieldsMap;
	public LinkedHashMap<String, String> displayFieldsMap;
	public LinkedHashMap<String, String> privacyFieldsMap;
	private TextView textTitle;
	private EditText editTextRunTime;
	private Pattern pattern;
	String hypertextSelected;
	private Matcher matcher;

	public void getRegistrationFormFields(LinearLayout editPageLayout,
			HashMap<String, Field> displayedExtraFieldsMap) {

		inCorrectFieldsList = new ArrayList<TextView>();
		privacyFieldsMap = new LinkedHashMap<String, String>();
		postFieldsMap = new LinkedHashMap<String, String>();
		displayFieldsMap = new LinkedHashMap<String, String>();
		getFormFields(editPageLayout,
				(LinkedHashMap<String, Field>) displayedExtraFieldsMap);

	}

	private void getFormFields(LinearLayout layoutCoreFields,
			LinkedHashMap<String, Field> coreFieldsMap) {

		Field runTimeFormFields = null;

		for (int i = 0; i < layoutCoreFields.getChildCount(); i++) {
			View view = layoutCoreFields.getChildAt(i);

			if (view instanceof LinearLayout) {
				LinearLayout rel = (LinearLayout) view;
				for (int j = 0; j < rel.getChildCount(); j++) {

					View mView = (View) rel.getChildAt(j);

					if (mView instanceof LinearLayout) {
						LinearLayout runTimeUserView = (LinearLayout) mView;
						textTitle = (TextView) runTimeUserView.getChildAt(0);

						runTimeFormFields = coreFieldsMap.get(String
								.valueOf(runTimeUserView.getId()));
					

						for (int p = 0; p < runTimeUserView.getChildCount(); p++) {
							View runTimeView = runTimeUserView.getChildAt(p);
							if (runTimeView instanceof EditText) {
								editTextRunTime = (EditText) runTimeView;
								String text = editTextRunTime.getText()
										.toString();
								if ((text.equals("") && editTextRunTime
										.isEnabled())) {
									textTitle.setTextColor(Color.RED);
									inCorrectFieldsList.add(textTitle);
								}

								runTimeFormFields.editProfileFieldValue = text;

							}
							postFieldsMap.put(
									runTimeFormFields.editProfileFieldId,
									runTimeFormFields.editProfileFieldValue);
							displayFieldsMap.put(
									runTimeFormFields.editProfileFieldName,
									runTimeFormFields.editProfileFieldValue);

						}
					}
				}
			}
		}
	}
}
