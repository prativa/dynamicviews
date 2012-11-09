package com.example.dynamicview.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public class Field implements Serializable {

	public String editProfileFieldId;
	public String editProfileFieldType = null;
	public String editProfileFieldGroup;
	public int ePFieldIsPublished;
	public int ePFieldIsSearchable;
	public int ePFieldIsVisible;
	public int ePFieldIsRequired;
	public int ePFieldIsRegistered;
	public int ePFieldMinimumChars;
	public int ePFieldMaximumChars;
	public int ePFieldOrdering;
	public String editProfileFieldName = null;
	public String editProfileFieldTips = null;
	public String editProfileFieldCode = null;
	public String editProfileFieldValue = null;
	public String editProfileFieldPrivacyAccess = null;

}
