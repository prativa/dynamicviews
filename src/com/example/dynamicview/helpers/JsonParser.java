package com.example.dynamicview.helpers;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

public class JsonParser {

	public static LinkedHashMap<String, ArrayList<Field>> parseUserProfileDetails(
			String jsonString) throws JSONException {
		LinkedHashMap<String, ArrayList<Field>> hashValuesOfJsonData = new LinkedHashMap<String, ArrayList<Field>>();
		ArrayList<Field> listFieldArrays = new ArrayList<Field>();
		String fieldHeaderName = "nothing";
		ProfileInfoHolder profileDetails = new ProfileInfoHolder();
		JSONObject profileDetailsObj = new JSONObject(jsonString);

		profileDetails.userId = profileDetailsObj.getString("id");
		Log.i("profile uid", profileDetails.userId);
		profileDetails.userName = profileDetailsObj.getString("name");
		Log.i("profile user name", profileDetails.userName);
		profileDetails.userEmail = profileDetailsObj.getString("email");
		Log.i("Profile email", profileDetails.userEmail);
		JSONObject fieldsObject = profileDetailsObj.getJSONObject("fields");
		JSONArray fieldNamesArray = fieldsObject.names();
		// method to parse fields of a category
		for (int i = 0; i < fieldNamesArray.length(); i++) {

			JSONArray fieldsArray = fieldsObject.getJSONArray(fieldNamesArray
					.get(i).toString());
			fieldHeaderName = fieldNamesArray.get(i).toString();

			listFieldArrays = parseCategoryField(fieldsArray);
			Field field = new Field();
			field.editProfileFieldGroup = fieldHeaderName;
			hashValuesOfJsonData.put(fieldHeaderName, listFieldArrays);
		}

		return hashValuesOfJsonData;

	}

	public static ArrayList<Field> parseCategoryField(JSONArray fieldArray)
			throws JSONException {
		ArrayList<Field> fieldArrayList = new ArrayList<Field>();
		int arrLength = fieldArray.length();

		for (int i = 0; i < arrLength; i++) {

			Field field = new Field();
			JSONObject fieldObj = fieldArray.getJSONObject(i);

			field.editProfileFieldId = fieldObj.getString("id");

			field.editProfileFieldType = fieldObj.getString("type");
			// for int
			field.ePFieldIsVisible = fieldObj.getInt("visible");
			field.ePFieldIsRequired = fieldObj.getInt("required");
			field.ePFieldIsSearchable = fieldObj.getInt("searchable");

			field.ePFieldIsRegistered = fieldObj.getInt("registration");

			// for int
			field.ePFieldOrdering = fieldObj.getInt("ordering");
			field.ePFieldMinimumChars = fieldObj.getInt("min");
			field.ePFieldMaximumChars = fieldObj.getInt("max");

			// for strings
			field.editProfileFieldName = fieldObj.getString("name");
			field.editProfileFieldTips = fieldObj.getString("tips");

			field.editProfileFieldValue = fieldObj.getString("value");
			field.editProfileFieldPrivacyAccess = fieldObj.getString("access");

			

			fieldArrayList.add(field);
		}

		return fieldArrayList;
	}

}
