package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Consigne extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	TextView Temp, Light;
	float consigneTemp = Home_Control.temperatureSetpoint;
	int consigneLight = Home_Control.LightSetpoint;
	int consigneLightProgress, consigneTempProgress;
	SeekBar mSeekBarTemp, mSeekBarLight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out); // Animation
		setContentView(R.layout.activity_consigne);
		Temp = (TextView) findViewById(R.id.edit_Temperature);
		Temp.setText(consigneTemp + "°");
		Light = (TextView) findViewById(R.id.edit_Light);
		Light.setText(consigneLight + "lux");

		mSeekBarLight = (SeekBar) findViewById(R.id.seekBar_Light);
		mSeekBarLight.setOnSeekBarChangeListener(this);
		mSeekBarLight.setProgress(consigneLight);

		mSeekBarTemp = (SeekBar) findViewById(R.id.seekBar_Temp);
		mSeekBarTemp.setOnSeekBarChangeListener(this);
		mSeekBarTemp.setProgress((int)consigneTemp*10);
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		if (seekBar.equals(mSeekBarTemp)) {
			consigneTemp = (float) (progress / 10.0);
			Temp.setText(consigneTemp + "°");
		} else if (seekBar.equals(mSeekBarLight)) {
			consigneLight = progress;
			Light.setText(consigneLight + "lux");
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

		if (seekBar.equals(mSeekBarTemp)) {
			Temp.setText(consigneTemp + "°");
			Home_Control.setTempSetpoint(consigneTemp);
			
		} else if (seekBar.equals(mSeekBarLight)) {
			Light.setText(consigneLight + "lux");
			Home_Control.setLightSetpoint(consigneLight);
		}
	}

}
