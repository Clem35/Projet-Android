package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Etat extends Activity {

	private static TextView text, text1, text2, text3, text4, text5, text6,
			text7;
	private static String message, message1, message2, message3, message4,
			message5, message6, message7;
	private static int x = 0;
	private static boolean y = true;
	Thread thread;

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				text.setText(message);
			} else if (msg.what == 1) {
				text1.setText(message1);
			} else if (msg.what == 2) {
				text2.setText(message2);
				System.out.println("--------SET MESSAGE 2");
			} else if (msg.what == 3) {
				text3.setText(message3);
			} else if (msg.what == 4) {
				text4.setText(message4);
			} else if (msg.what == 5) {
				text5.setText(message5);
			} else if (msg.what == 6) {
				text6.setText(message6);
			} else if (msg.what == 7) {
				text7.setText(message7);
			}
			//traitementDesDonnees();

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etat);
		text = (TextView) findViewById(R.id.valeur_etat_tempInt);
		text1 = (TextView) findViewById(R.id.valeur_etat_tempExt);
		text2 = (TextView) findViewById(R.id.valeur_etat_lightInt);
		text3 = (TextView) findViewById(R.id.valeur_etat_lightExt);
		text4 = (TextView) findViewById(R.id.valeur_etat_meteo);
		text5 = (TextView) findViewById(R.id.valeur_etat_PositionVolet);
		text6 = (TextView) findViewById(R.id.valeur_etat_lightLevel);
		text7 = (TextView) findViewById(R.id.valeur_etat_lightBrightness);
		y = true;
		new traitementDesDonnees().start();

	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_etat, menu);
		return true;
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		y = false;
		startActivity(intent);
	}

	public void onClickEtat(View view) {
		traitementDesDonnees();
	}

	private void traitementDesDonnees() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				if (x == 8)
					x = 0;
				if (x == 0)
					message = Home_Control.getTI + "°";
				else if (x == 1)
					message1 = Home_Control.getTE + "°";
				else if (x == 2)
					message2 = Home_Control.getLI + " lux";
				else if (x == 3)
					message3 = Home_Control.getLE + " lux";
				else if (x == 4) {
					boolean v = Home_Control.getW;
					if (v)
						message4 = "Soleil";
					else
						message4 = "Pluie";
				} else if (x == 5) {
					int a = Home_Control.getSS;
					if (a == 0) {
						message5 = "Fermé";
					} else if (a == 1) {
						message5 = "Mi-Hauteur";
					} else {
						message5 = "Ouvert";
					}
				} else if (x == 6)
					message6 = Home_Control.getLL + "%";
				else if (x == 7)
					message7 = Home_Control.getLB + "lux";

				System.out.println("-----Traitement Etats-----");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// if (x == 0)
				// message = Home_Control.getTempInt() + "°";
				// else if (x == 1)
				// message1 = Home_Control.getTempExt() + "°";
				// else if (x == 2)
				// message2 = Home_Control.getLightInt() + " lux";
				// else if (x == 3)
				// message3 = Home_Control.getLightExt() + " lux";
				// else if (x == 4) {
				// boolean v = Home_Control.getWeather();
				// if (v)
				// message4 = "Soleil";
				// else
				// message4 = "Pluie";
				// } else if (x == 5) {
				// int a = Home_Control.getShutterState();
				// if (a == 0) {
				// message5 = "Fermé";
				// } else if (a == 1) {
				// message5 = "Mi-Hauteur";
				// } else {
				// message5 = "Ouvert";
				// }
				// } else if (x == 6)
				// message6 = Home_Control.getLampeLevel() + "%";
				// else if (x == 7)
				// message7 = Home_Control.getLampeBrightness() + "lux";

				handler.sendEmptyMessage(x++);

			}
		}).start();
	}
}
