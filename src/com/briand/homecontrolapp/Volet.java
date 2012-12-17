package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Volet extends Activity implements SeekBar.OnSeekBarChangeListener {

	// /////////////////////////////////////////////////
	// VARIABLES
	// /////////////////////////////////////////////////
	SeekBar mSeekBar;
	ImageView voletGrad;
	TextView volet;
	int position = Home_Control.getSS; // récupération position volet

	// /////////////////////////////////////////////////
	// onCREATE
	// /////////////////////////////////////////////////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volet);

		// Récupération des éléments de la vue
		voletGrad = (ImageView) findViewById(R.id.imageVoletGrad);
		volet = (TextView) findViewById(R.id.edit_Volet);
		mSeekBar = (SeekBar) findViewById(R.id.seekBarVolet);

		// MAJ seekBar + image en fonction de la position actuelle
		modificationVolet();
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setProgress(position);
	}

	public void onStop() {
		super.onStop();
	}

	// /////////////////////////////////////////////////
	// MODIF Image Volet en fonction de la position
	// /////////////////////////////////////////////////
	public void modificationVolet() {
		if (position == 0) {
			volet.setText("0%");
			voletGrad.setImageResource(R.drawable.ferme);
		} else if (position == 1) {
			volet.setText("50%");
			voletGrad.setImageResource(R.drawable.semiouvert);
		}
		if (position == 2) {
			volet.setText("100%");
			voletGrad.setImageResource(R.drawable.ouvert);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_volet, menu);
		return true;
	}

	// RETOUR ACCEUIL
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
		position = progress;
		modificationVolet();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		modificationVolet(); // Modification de l'image
		new setShutter().start(); // Lancement du thread pour modifier la
									// position du VR

	}

	// /////////////////////////////////////////////////
	// THREAD MODIFICATION POSITION VOLETS
	// /////////////////////////////////////////////////
	public class setShutter extends Thread {
		public void run() {
			if (position == 0) {
				Home_Control.pullDownShutter();
			} else if (position == 1) {
				Home_Control.setIntermediateShutter();
			} else if (position == 2) {
				Home_Control.pullUpShutter();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Home_Control.setLightSetpoint(Home_Control.getLightInt());

		}
	}
}
