package com.example.dynamicview.dynamic_views;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class CustomTextChangedListener implements TextWatcher {

	private boolean correctFlag;
	private String type;
	private EditText editText;
	private TextView titleTextView;

	private Pattern pattern;
	private Matcher matcher;

	private Context context;

	public CustomTextChangedListener(Context context, EditText editText,
			TextView titleTextView, String type) {
		this.editText = editText;
		this.titleTextView = titleTextView;
		this.context = context;
		this.type = type;

		if (type.equals("email")) {
			pattern = Pattern.compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

		if (!editText.getText().toString().equals("")) {
			correctFlag = true;
			if (type.equals("email")) {
				matcher = pattern.matcher(editText.getText().toString());
				if (!matcher.matches())
					correctFlag = false;
			}
			/*if (correctFlag) {
				titleTextView.setTextColor(context.getResources().getColor(
						R.color.label_textColor));
				if (CategoryLevelSpinners.inCorrectFieldsListFromWatcher
						.contains(titleTextView)) {

					CategoryLevelSpinners.inCorrectFieldsListFromWatcher
							.remove(titleTextView);
					Log.i("IncorrectFileds",
							+CategoryLevelSpinners.inCorrectFieldsListFromWatcher
									.size() + ", Remove View: " + titleTextView.getText().toString());
				}
			}*/

		} else {
			titleTextView.setTextColor(Color.RED);
			/*if (!CategoryLevelSpinners.inCorrectFieldsListFromWatcher
					.contains(titleTextView))
				CategoryLevelSpinners.inCorrectFieldsListFromWatcher.add(titleTextView);

			Log.i("IncorrectFileds",
					+CategoryLevelSpinners.inCorrectFieldsListFromWatcher.size() + "");
*/
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}