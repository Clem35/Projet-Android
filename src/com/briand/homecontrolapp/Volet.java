package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Volet extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);  //Animation
		setContentView(R.layout.activity_volet);
	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	public void onClick_Edit_Volet(View view) {
		RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup1);
		int radioId = radio.getCheckedRadioButtonId();
		if (radioId == R.id.radio0) {
			Home_Control.pullDownShutter();
		} else if (radioId == R.id.radio1) {
			Home_Control.setIntermediateShutter();
		} else if (radioId == R.id.radio2) {
			Home_Control.pullUpShutter();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_volet, menu);
		return true;
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent);
	}
}
