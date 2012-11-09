package com.example.dynamicview.helpers;

import java.util.LinkedHashMap;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class UtilityClass {
	public static LinkedHashMap<String, String> EDIT_PROFILE_RUNTIME_INPUT = new LinkedHashMap<String, String>();
	public static LinkedHashMap<String, String> EDIT_PROFILE_WITH_FIELD_NAME = new LinkedHashMap<String, String>();

	public static Button getButton(Context context, String text) {
		Button button = new Button(context);
		LinearLayout.LayoutParams layoutParamsBasic = getLayoutParams();
		layoutParamsBasic.gravity = Gravity.RIGHT;
		layoutParamsBasic.width = intToDpi(55, context);
		layoutParamsBasic.height = intToDpi(40, context);

		button.setLayoutParams(layoutParamsBasic);
		button.setText(text);
		button.setTextSize(12);
		button.setGravity(Gravity.CENTER);
		button.setShadowLayer(1, 1, 1, Color.WHITE);
		int intToDpi2 = intToDpi(2, context);
		button.setPadding(intToDpi2, intToDpi2, intToDpi2, intToDpi2);
		return button;
	}

	
	public static int intToDpi(int integer, Context context) {
		return (int) (integer * context.getResources().getDisplayMetrics().density);
	}

	public static LinearLayout.LayoutParams getLayoutParams() {
		return new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	public static LinearLayout.LayoutParams getLayoutParamsFillParentWidth() {
		return new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
	}
	
	public static LinearLayout getLinearLayoutHorizontal(Context context) {
		LinearLayout linearLayout = new LinearLayout(context);
		int intToDpi5 = intToDpi(5, context);
		linearLayout.setPadding(intToDpi5, intToDpi5, intToDpi5, intToDpi5);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layoutParamsBasic = getLayoutParams();
		linearLayout.setLayoutParams(layoutParamsBasic);

		return linearLayout;
	}

	



}
