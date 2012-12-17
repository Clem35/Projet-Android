package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Consigne extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	// /////////////////////////////////////////////////
	// VARIABLES
	// /////////////////////////////////////////////////
	TextView Temp, Light;
	float consigneTemp = Home_Control.temperatureSetpoint; // Récupération
															// consigne
															// température
	int consigneLight = Home_Control.LightSetpoint; // Récupération consigne
													// luminosité
	int consigneLightProgress, consigneTempProgress;
	SeekBar mSeekBarTemp, mSeekBarLight;

	// /////////////////////////////////////////////////
	// onCREATE
	// /////////////////////////////////////////////////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consigne);

		// Récupération des éléments de la vue
		Temp = (TextView) findViewById(R.id.edit_Temperature);
		Light = (TextView) findViewById(R.id.edit_Light);
		mSeekBarLight = (SeekBar) findViewById(R.id.seekBar_Light);
		mSeekBarTemp = (SeekBar) findViewById(R.id.seekBar_Temp);

		// MAJ des text view
		Temp.setText(consigneTemp + "°");
		Light.setText(consigneLight + "lux");

		// MAJ des seek bars
		mSeekBarLight.setOnSeekBarChangeListener(this);
		mSeekBarLight.setProgress(consigneLight);

		mSeekBarTemp.setOnSeekBarChangeListener(this);
		mSeekBarTemp.setProgress((int) consigneTemp * 10);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_consigne, menu);
		return true;
	}

	// RETOUR A l'ACCEUIL
	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	// /////////////////////////////////////////////////
	// SEEKBARS
	// /////////////////////////////////////////////////
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		if (seekBar.equals(mSeekBarTemp)) { // Si modification de la seekbar
											// Temp.
			consigneTemp = (float) (progress / 10.0); // MAJ variable
			Temp.setText(consigneTemp + "°"); // MAJ Text View
		} else if (seekBar.equals(mSeekBarLight)) {// Si modification de la
													// seekbar Lum.
			consigneLight = progress;// MAJ variable
			Light.setText(consigneLight + "lux"); // MAJ Text View
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

		if (seekBar.equals(mSeekBarTemp)) { // Si stop sur seekbar Temp.
			Temp.setText(consigneTemp + "°");// MAJ Text View
			Home_Control.setTempSetpoint(consigneTemp); // MAJ de la consigne

		} else if (seekBar.equals(mSeekBarLight)) { // Si stop sur seekbar Lum.
			Light.setText(consigneLight + "lux");// MAJ Text View
			Home_Control.setLightSetpoint(consigneLight);// MAJ de la consigne
		}
	}

}
