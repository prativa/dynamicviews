package com.example.dynamicview.helpers;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class ProfileInfoHolder {
	public static ArrayList<Field> listFieldType = new ArrayList<Field>();
	public static ArrayList<String> nameOfFieldHeader = new ArrayList<String>();
	public static LinkedHashMap<String, ArrayList<Field>> fieldHashValues = new LinkedHashMap<String, ArrayList<Field>>();
	public String userId;
	public String userName;
	public String userEmail;

}
