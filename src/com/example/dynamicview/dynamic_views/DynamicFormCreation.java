package com.example.dynamicview.dynamic_views;

import java.util.ArrayList;

import com.example.dynamicview.helpers.Field;
import com.example.dynamicview.helpers.UtilityClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author ajit
 * 
 */
public class DynamicFormCreation {

	public int extraFieldId;

	private Context context;
	int intToDpi1, intToDpi2, intToDpi3, intToDpi4, intToDpi5, intToDpi45,
			intToDpi0, intToDpi175, intToDpi125, intToDpi20, intToDpi80;
	public ImageView imageViewToUpload;

	private LinearLayout.LayoutParams layoutParamsBasic;

	public DynamicFormCreation(Context context) {
		this.context = context;
		initDpiData();
	}

	public void initDpiData() {
		intToDpi1 = UtilityClass.intToDpi(1, context);
		intToDpi2 = UtilityClass.intToDpi(2, context);
		intToDpi3 = UtilityClass.intToDpi(3, context);
		intToDpi4 = UtilityClass.intToDpi(4, context);
		intToDpi5 = UtilityClass.intToDpi(5, context);
		intToDpi45 = UtilityClass.intToDpi(45, context);
		intToDpi0 = UtilityClass.intToDpi(0, context);
		intToDpi175 = UtilityClass.intToDpi(175, context);
		intToDpi125 = UtilityClass.intToDpi(125, context);
		intToDpi20 = UtilityClass.intToDpi(20, context);
		intToDpi80 = UtilityClass.intToDpi(80, context);

	}

	/**
	 * This method checks type of the field to be created and calls appropriate
	 * method to create field of the type
	 * 
	 * @param FieldFields
	 *            Object of field being created
	 * @param lastFieldId
	 *            id of last Field filed
	 * @return created view at the runtime
	 */
	public View getRuntimeView(Field formFields, int lastFieldId) {
		View mView = null;
		mView = getTextFields(formFields, lastFieldId);
		return mView;
	}

	public View getTextFields(Field formFields, int parentId) {

		LinearLayout rel = UtilityClass.getLinearLayoutHorizontal(context);

		TextView titleTextView = createTextView(context, formFields, parentId);
		rel.addView(titleTextView);
		parentId++;

		EditText editTextField = createEditText(
				formFields.editProfileFieldTips, context, parentId);
		if (formFields.editProfileFieldType.equals("url")) {
			Log.i("Enters here", "tag is set");
			editTextField.setTag("urlTextfield");
		}

		if (formFields.editProfileFieldType.equals("email")) {
			editTextField.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		} else if (formFields.editProfileFieldType.equals("url")) {
			editTextField.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_URI);
		}

		parentId++;
		editTextField.setTag("editText");
		if (formFields.editProfileFieldType.equals("label")) {
			editTextField.setTag("labelEditText");
			editTextField.setEnabled(false);
		}
		rel.addView(editTextField);

		if (formFields.ePFieldIsRequired == 1) {
			editTextField.addTextChangedListener(new CustomTextChangedListener(
					context, editTextField, titleTextView,
					formFields.editProfileFieldType));
		}

		extraFieldId = parentId++;
		rel.setId(extraFieldId);
		return rel;
	}

	public TextView createTextView(Context context, Field formField,
			int parentId) {
		TextView textView = new TextView(context);
		LinearLayout.LayoutParams layoutParamsBasic = UtilityClass
				.getLayoutParams();
		layoutParamsBasic.width = intToDpi80;
		textView.setLayoutParams(layoutParamsBasic);
		String text = formField.editProfileFieldName;
		textView.setText(text);
		if (formField.ePFieldIsRequired == 1) {
			textView.setText(text + "*");
		} else {
			textView.setText(text);
		}

		extraFieldId = parentId++;

		textView.setPadding(intToDpi1, intToDpi1, intToDpi1, intToDpi1);
		textView.setShadowLayer(1, 1, 1, Color.WHITE);
		textView.setId(extraFieldId);
		return textView;
	}

	public EditText createEditText(String hint, Context context, int parentId) {
		EditText editText = new EditText(context);
		editText.setTextSize(10);

		editText.setPadding(intToDpi2, intToDpi2, intToDpi2, intToDpi2);
		editText.setWidth(UtilityClass.intToDpi(175, context));
		editText.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams layoutParamsBasic = UtilityClass
				.getLayoutParams();
		editText.setLayoutParams(layoutParamsBasic);

		extraFieldId = parentId++;
		editText.setId(extraFieldId);
		if (!hint.equals(null))
			editText.setHint(hint);

		return editText;
	}

}
