package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Lampe extends Activity implements SeekBar.OnSeekBarChangeListener {

	SeekBar mSeekBar;
	int LightProgress = Home_Control.getLL;
	TextView lampe;
	String light;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out); // Animation
		setContentView(R.layout.activity_lampe);
		lampe = (TextView) findViewById(R.id.edit_Intensite);
		LightProgress = Home_Control.getLL;
		light = LightProgress + "%";
		lampe.setText(light);
		
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setProgress(LightProgress);

	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromTouch) {
		LightProgress = progress;
		lampe.setText(LightProgress+"%");
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		light = LightProgress + "%";
		lampe.setText(light);
		Home_Control.setLevelLight(LightProgress);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_lampe, menu);
		return true;
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent);
	}
}
