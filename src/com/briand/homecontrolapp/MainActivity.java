package com.briand.homecontrolapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	Switch auto;
	String clickable;
	private static ImageView bouton, bouton2;
	private static boolean x = false;
	private static boolean weather = true;
	private static ImageView meteo;
	TextView Light;
	SeekBar mSeekBarLight;
	int consigneLight = Home_Control.LightSetpoint;

	// evenements
	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.wave_scale, R.anim.wave_scale); // Animation
		setContentView(R.layout.activity_main);
		meteo = (ImageView) findViewById(R.id.imageAndroidBas);
		// new Home_Control.traitementAuto().start();
		
		Home_Control.traitementAuto();
		
		new traitementMeteo().start();

		Light = (TextView) findViewById(R.id.edit_Light);
		Light.setText(consigneLight + "lux");

		mSeekBarLight = (SeekBar) findViewById(R.id.seekBar_Light);
		mSeekBarLight.setOnSeekBarChangeListener(this);
		mSeekBarLight.setProgress(consigneLight);
	}

	public void automatic_Mode(View view) {
		bouton = (ImageView) findViewById(R.id.ImageView02);
		bouton2 = (ImageView) findViewById(R.id.ImageView2);
		boolean on = ((Switch) view).isChecked();

		if (on) {
			x = true;
			bouton.setClickable(false);
			bouton.setImageResource(R.drawable.redbutton);
			bouton2.setClickable(false);
			bouton2.setImageResource(R.drawable.redbutton);
			new traitementAutomatique().start();
		} else {
			x = false;
			bouton.setClickable(true);
			bouton.setImageResource(R.drawable.custom_btn);
			bouton2.setClickable(true);
			bouton2.setImageResource(R.drawable.custom_btn);
		}
	}

	private static Handler handler = new Handler() {

		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				meteo.setImageResource(R.drawable.android_soleil);
				// System.out.println("SOLEILSOLEILSOLEILSOLEILSOLEILSOLEIL");
			} else if (msg.what == 1) {
				meteo.setImageResource(R.drawable.android_pluie);
				// System.out.println("PLUIEPLUIEPLUIEPLUIEPLUIE");
			}

		};
	};

	public static class traitementMeteo extends Thread {
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true) {
				weather = Home_Control.getW;
				// System.out.println("|||||=======TRAITEMENT METEO =====|||||"
				// + weather);
				if (weather)
					handler.sendEmptyMessage(0);
				if (!weather)
					handler.sendEmptyMessage(1);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onResume() {

		super.onResume();
	}

	public void onStart() {
		bouton = (ImageView) findViewById(R.id.ImageView02);
		bouton2 = (ImageView) findViewById(R.id.ImageView2);
		if (x) {
			bouton.setClickable(false);
			bouton.setImageResource(R.drawable.redbutton);
			bouton2.setClickable(false);
			bouton2.setImageResource(R.drawable.redbutton);
		} else {
			bouton.setClickable(true);
			bouton.setImageResource(R.drawable.custom_btn);
			bouton2.setClickable(true);
			bouton2.setImageResource(R.drawable.custom_btn);
		}
		auto = (Switch) findViewById(R.id.switch1);
		auto.setChecked(x);

		super.onStart();
	}

	public void onRestart() {

		super.onRestart();
	}

	public void onDestroy() {
		Home_Control.run = false;

		super.onDestroy();
	}

	public void onStop() {
		// Home_Control.run = false;

		super.onStop();
	}

	/**
	 * 
	 */
	public class traitementAutomatique extends Thread {

		public void run() {
			while (x) {
				Home_Control.autoShutterState();
				Home_Control.autoLightLevel();
				System.out
						.println("-----MAIN_ACTIVITY-----Traitement Automatique-----");
			}
		}
	}

	// ///////////////////////////////////////////

	// onClick

	// ///////////////////////////////////////////
	public void click_Consigne(View view) {
		Intent intent = new Intent(this, Consigne.class);
		startActivity(intent);
	}

	public void click_Etats(View view) {
		Intent intent = new Intent(this, Etat.class);
		startActivity(intent);
	}

	public void click_Lampe(View view) {
		Intent intent = new Intent(this, Lampe.class);
		startActivity(intent);
	}

	public void click_Volet(View view) {
		Intent intent = new Intent(this, Volet.class);
		startActivity(intent);
	}

	public void click_config(View view) {
		Intent intent = new Intent(this, Configuration.class);
		startActivity(intent);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		consigneLight = progress;
		Light.setText(consigneLight + "lux");

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Light.setText(consigneLight + "lux");
		Home_Control.setLightSetpoint(consigneLight);
		Home_Control.autoShutterWithoutTemp();
		Home_Control.autoLightLevel();
	}

}
