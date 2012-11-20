package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {

	Switch auto;
	String clickable;
	private static ImageView bouton, bouton2;
	private static boolean x = false;

	// evenements
	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.wave_scale, R.anim.wave_scale); // Animation
		setContentView(R.layout.activity_main);
		// new Home_Control.traitementAuto().start();
		Home_Control.traitementAuto();
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
			// startService(new Intent(MainActivity.this,
			// ServiceAutomatique.class));
		} else {
			x = false;
			bouton.setClickable(true);
			bouton.setImageResource(R.drawable.custom_btn);
			bouton2.setClickable(true);
			bouton2.setImageResource(R.drawable.custom_btn);

			// stopService(new Intent(MainActivity.this,
			// ServiceAutomatique.class));
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
		popUp("------ONDESTROY------");
		super.onDestroy();
	}

	public void onStop() {
		// Home_Control.run=false;
		popUp("------ONSTOP------");
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

}
