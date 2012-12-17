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

public class MainActivity extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	// VARIABLES
	Switch auto; // switch du mode automatique
	String clickable; 
	private static ImageView bouton, bouton2, meteo;
	private static boolean x = false; // variable permettant d'activer ou d�sactiver le mode automatique
	private static boolean weather;
	TextView Light;
	SeekBar mSeekBarLight; // SeekBar de la luminosit� int�rieure
	static int consigneLight = Home_Control.LightSetpoint; // Consigne luminosit� int�rieure
	private static boolean run = true; // �tat du thread m�t�o

	
	// /////////////////////////////////////////////////
	// OnCREATE
	// /////////////////////////////////////////////////

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// R�cup�ration des �l�ments de la vue
		meteo = (ImageView) findViewById(R.id.imageAndroidBas);
		mSeekBarLight = (SeekBar) findViewById(R.id.seekBar_Light);
		Light = (TextView) findViewById(R.id.edit_Light);

		run = true;
		Home_Control.traitementAuto(); // lancement threads variables restlet

		new traitementMeteo().start(); // lancement du thread des images m�t�o

		// Mise � jour de la seekbar et du text view en fonction de la consigne
		// de luminosit�
		Light.setText(consigneLight + "lux");
		mSeekBarLight.setOnSeekBarChangeListener(this);
		mSeekBarLight.setProgress(consigneLight);
		new setlumiere().start(); // modification de la luminosit� en fonction
									// de la consigne
	}

	public void automatic_Mode(View view) {
		// r�cup�ration des 2 boutons (volet + lumi�re) afin de les bloquer ou
		// non en fonction du mode automatique
		bouton = (ImageView) findViewById(R.id.ImageView02);
		bouton2 = (ImageView) findViewById(R.id.ImageView2);
		// R�cup�ration de l'�tat du switch
		boolean on = ((Switch) view).isChecked();

		if (on) { // si le mode automatique est activ�
			x = true;
			bouton.setClickable(false); // On bloque l'acces � la modification
										// manuelle des volets et lumi�res
			bouton.setImageResource(R.drawable.redbutton);
			bouton2.setClickable(false);// On bloque l'acces � la modification
										// manuelle des volets et lumi�res
			bouton2.setImageResource(R.drawable.redbutton);
			new traitementAutomatique().start(); // on d�marre le mode
													// automatique
		} else {
			x = false; // on arrete le mode automatique (arret du thread)
			bouton.setClickable(true);// on autorise l'acces au mode manuel
										// volets + lumi�res
			bouton.setImageResource(R.drawable.custom_btn);
			bouton2.setClickable(true);// on autorise l'acces au mode manuel
										// volets + lumi�res
			bouton2.setImageResource(R.drawable.custom_btn);
		}
	}

	/**
	 * Traitement thread + Handler M�t�o
	 */
	private static Handler handler = new Handler() {

		// /////////////////////////////////////////////////
		// MISE A JOUR METEO DANS VUE ANDROID
		// /////////////////////////////////////////////////
		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				meteo.setImageResource(R.drawable.android_soleil);
			} else if (msg.what == 1) {
				meteo.setImageResource(R.drawable.android_pluie);
			}

		}
	};

	public static class traitementMeteo extends Thread {
		public void run() {
			while (run) {

				weather = Home_Control.getW; // r�cup�ration �tat m�t�o

				if (weather)
					handler.sendEmptyMessage(0); // s'il fait beau activ�
													// premi�re action
				if (!weather)
					handler.sendEmptyMessage(1); // s'il pleut activit� deuxi�me
													// action
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// onRESUME
	public void onResume() {
		super.onResume();
	}

	// /////////////////////////////////////////////////
	// OnSTART
	//
	// R�cup�ration du mode automatique et
	// Mise � jour des boutons et switch
	// /////////////////////////////////////////////////
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
		
		consigneLight=Home_Control.LightSetpoint;
		mSeekBarLight.setProgress(consigneLight);

		super.onStart();
	}

	public void onRestart() {
		super.onRestart();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onStop() {
		super.onStop();
	}

	/**
	 * Traitement Automatique
	 */
	public class traitementAutomatique extends Thread {

		public void run() {
			while (x) {
				Home_Control.autoShutterState(); // Modification volet
				Home_Control.autoLightLevel(); //Modification Lumi�re
				System.out
						.println("-----MAIN_ACTIVITY-----Traitement Automatique-----");
			}
		}
	}

	/**
	 * SEEK BAR
	 */
	@Override
	// action lors d'un changement de valeursur la seek bar
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		consigneLight = progress;
		Light.setText(consigneLight + "lux"); // Mise � jour du textView

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	// action lors de l'arret sur la seek bar
	public void onStopTrackingTouch(SeekBar seekBar) {
		Light.setText(consigneLight + "lux"); // Mise � jour du textView
		new setlumiere().start();
	}

	/**
	 * Thread mise � jour consigne luminosit� int�rieur + modification volet et
	 * puissance de la lampe
	 */
	public class setlumiere extends Thread {
		public void run() {
			
			Home_Control.setLightSetpoint(consigneLight); //changement de la consigne
			if(!x){
			Home_Control.autoShutterWithoutTemp(); //mise � jour des volets
			Home_Control.autoLightLevel(); // Mise � jour de la luminosit�
			
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
