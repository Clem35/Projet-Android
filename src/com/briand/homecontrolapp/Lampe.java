package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Lampe extends Activity implements SeekBar.OnSeekBarChangeListener {

	// /////////////////////////////////////////////////
	// VARIABLES
	// /////////////////////////////////////////////////
	SeekBar mSeekBar; // seekBar lampe
	int LightProgress; // progression puissance lampe
	TextView lampe;
	ImageView lampeGrad;

	// /////////////////////////////////////////////////
	// onCREATE
	// /////////////////////////////////////////////////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lampe);
		// Récupération des éléments de la vue
		lampe = (TextView) findViewById(R.id.edit_Intensite);
		lampeGrad = (ImageView) findViewById(R.id.imageLampeGrad);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);

		// MAJ de la seekBar et du textView en fonction du niveau de puissance
		LightProgress = Home_Control.getLL;
		lampe.setText(LightProgress + "%");
		modificationImage(); // moification de l'image

		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setProgress(LightProgress);

	}

	public void onStop() {
		super.onStop();
	}

	// /////////////////////////////////////////////////
	// SEEKBAR LAMPE
	// /////////////////////////////////////////////////

	// Action lors de changement sur la seekbar
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromTouch) {
		LightProgress = progress; // mise à jour de la variable
		lampe.setText(LightProgress + "%"); // MAJ du textView
		modificationImage(); // MAJ image
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	// Action lors de l'arret d'actions sur la seekbar
	public void onStopTrackingTouch(SeekBar seekBar) {
		lampe.setText(LightProgress + "%"); // MAJ du textView
		new setLevelLight().start(); // MAJ du niveau de la lampe
	}

	// /////////////////////////////////////////////////
	// MODIF Image : modification ampoule en
	// fonction du niveau de la lampe
	// /////////////////////////////////////////////////
	public void modificationImage() {
		if (LightProgress < 20) {
			lampeGrad.setImageResource(R.drawable.lampe1);
		} else if (LightProgress >= 20 & LightProgress < 40) {
			lampeGrad.setImageResource(R.drawable.lampe2);
		} else if (LightProgress >= 40 & LightProgress < 60) {
			lampeGrad.setImageResource(R.drawable.lampe3);
		} else if (LightProgress >= 60 & LightProgress < 80) {
			lampeGrad.setImageResource(R.drawable.lampe4);
		} else if (LightProgress >= 80) {
			lampeGrad.setImageResource(R.drawable.lampe5);
		}
	}

	// Thread de MAJ du niveau de la lampe
	public class setLevelLight extends Thread {
		public void run() {
			Home_Control.setLevelLight(LightProgress);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Home_Control.setLightSetpoint(Home_Control.getLightInt());
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_lampe, menu);
		return true;
	}

	// RETOUR ACCEUIL
	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
