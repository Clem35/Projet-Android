package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Consigne extends Activity {
	EditText Temp,Light;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);  //Animation
		setContentView(R.layout.activity_consigne);
		// Temp=(EditText)findViewById(R.id.edit_Temperature);
		// Temp.setText(Home_Control.temperatureSetpoint+"°");
		// Light=(EditText)findViewById(R.id.edit_Light);
		// Light.setText(Home_Control.LightSetpoint+"lux");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_consigne, menu);
		return true;
	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent);
	}

	public void onClick_Edit_Temperature(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_Temperature);
		float x = Float.parseFloat(editText.getText().toString());
		Home_Control.setTempSetpoint(x);
		popUp("Temp : " + x);
	}

	public void onClick_Edit_Light(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_Light);
		int x = Integer.parseInt(editText.getText().toString());
		Home_Control.setLightSetpoint(x);
		popUp("Light : " + x);
	}

}
