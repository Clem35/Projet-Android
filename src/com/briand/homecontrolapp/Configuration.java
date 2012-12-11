package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Configuration extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	 overridePendingTransition(R.anim.shake, R.anim.shake);  //Animation
		setContentView(R.layout.activity_configuration);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_configuration, menu);
		return true;
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	public void onClick_Edit_Address(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_Address);
		String x = editText.getText().toString();
		Home_Control.setAddressIP(x);
		popUp("adresse : " + x);
	}

}
