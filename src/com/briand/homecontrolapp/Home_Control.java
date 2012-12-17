package com.briand.homecontrolapp;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class Home_Control {

	// Variables
	public static int LightSetpoint = 400;
	public static float temperatureSetpoint = 20;
	public static boolean automatic = true;
	public static String address = "10.0.2.2";
	public static int getLI = 800;
	public static int getLE = 800;
	public static float getTI = (float) 20.4;
	public static float getTE = (float) 20.4;
	public static int getSS = 2;
	public static boolean getW = true;
	public static int getLL = 0;
	public static int getLB = 0;
	public static boolean run = true;

	/**
	 * Thread permettant la mise a jour des variables continuellement
	 */
	// lancement des thread
	public static void traitementAuto() {
		new traitementAutoLI().start();
		new traitementAutoW().start();
	}

	/**
	 * Destruction des threads
	 */
	public static void threadDown() {
		run = false;
	}

	/**
	 * Thread mettant à jour Luminosité Intérieure, Extérieure, Température
	 * Intérieure, Extérieure
	 * 
	 */
	public static class traitementAutoLI extends Thread {

		public void run() {
			while (run) {	
				getLE = getLightExt();
				delay100();
				getLI = getLightInt();		
				delay100();
				getTI = getTempInt();
				delay100();
				getTE = getTempExt();
				delay100();

			}
		}
	}

	public static void delay100() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delay1000() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Thread mettant à jour Météo, état du volet roulant, brightness de la
	 * lampte et le niveau de luminosité de la lampe
	 * 
	 */
	public static class traitementAutoW extends Thread {

		public void run() {
			while (run) {
				getSS = getShutterState();	
				delay100();
				getW = getWeather();
				delay100();
				getLB = getLampeBrightness();
				delay100();
				getLL = getLampeLevel();
				delay100();

			}
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Récupération de valeurs

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * getLightInt : Obtenir la luminosité à l'intérieur
	 * 
	 * @return valeur de la luminosité
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int getLightInt() {
		ClientResource light_int = new ClientResource("http://" + address
				+ ":9000/intLight?method=getBrightness");
		int get_light_int = 0;
		try {
			get_light_int = Integer.parseInt(light_int.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		light_int.release();
		getLI = get_light_int;
		return get_light_int;
	}

	/**
	 * getLightInt : Obtenir la luminosité à l'intérieur
	 * 
	 * @return valeur de la luminosité
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int getLightExt() {
		ClientResource light_ext = new ClientResource("http://" + address
				+ ":9000/extLight?method=getBrightness");
		int get_light_ext = 0;
		try {
			get_light_ext = Integer.parseInt(light_ext.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		light_ext.release();
		return get_light_ext;
	}

	/**
	 * getTempInt : obtenir la température intérieure
	 * 
	 * @return température intérieure
	 */
	public static float getTempInt() {
		ClientResource temp_int = new ClientResource("http://" + address
				+ ":9000/intTemp?method=getTemperature");
		float get_temp_int = (float) 78.78;
		try {
			get_temp_int = Float.parseFloat(temp_int.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
			get_temp_int = (float) 1.111;
		} catch (ResourceException e) {

			e.printStackTrace();
			get_temp_int = (float) 2.222;
		} catch (IOException e) {

			e.printStackTrace();
			get_temp_int = (float) 3.333;
		}

		temp_int.release();
		return get_temp_int;
	}

	/**
	 * getTempExt : Obtenir la température extérieure
	 * 
	 * @return température extérieure
	 */
	public static float getTempExt() {
		ClientResource temp_ext = new ClientResource("http://" + address
				+ ":9000/extTemp?method=getTemperature");
		float get_temp_ext = 0;
		try {
			get_temp_ext = Float.parseFloat(temp_ext.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		temp_ext.release();
		return get_temp_ext;
	}

	/**
	 * getWeather : obtenir la météo
	 * 
	 * @return météo
	 */
	public static boolean getWeather() {
		ClientResource weather = new ClientResource("http://" + address
				+ ":9000/extWeather?method=isSunny");
		boolean get_weather = false;
		try {
			get_weather = Boolean.parseBoolean(weather.get().getText());
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		weather.release();
		return get_weather;
	}

	/**
	 * getLampeLevel : Obtenir le niveau de la lampe (entre 0 et 100%)
	 * 
	 * @return niveau de la lampe
	 */
	public static int getLampeLevel() {
		ClientResource lampe_level = new ClientResource("http://" + address
				+ ":9000/light?method=getLevel");
		int get_lampe_level = 0;
		try {
			get_lampe_level = Integer.parseInt(lampe_level.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		lampe_level.release();
		return get_lampe_level;
	}

	/**
	 * GetLampeBrightness : obtenir la luminosité de la lampe
	 * 
	 * @return luminosité lampe
	 */
	public static int getLampeBrightness() {
		ClientResource lampe_brightness = new ClientResource("http://"
				+ address + ":9000/light?method=getBrightness");
		int get_lampe_brightness = 0;
		try {
			get_lampe_brightness = Integer.parseInt(lampe_brightness.get()
					.getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		lampe_brightness.release();
		return get_lampe_brightness;
	}

	/**
	 * getShutterState : Obtenir l'état du volet roulant
	 * 
	 * @return état du volet roulant
	 */
	public static int getShutterState() {
		ClientResource shutter_state = new ClientResource("http://" + address
				+ ":9000/shutter?method=getState");
		int get_shutter_state = 0;
		try {
			get_shutter_state = Integer.parseInt(shutter_state.get().getText());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (ResourceException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		shutter_state.release();
		return get_shutter_state;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////

	// Modification de valeurs

	// /////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * setLevelLight : Modifier le niveau de luminosité de la lampe (0 à 100%)
	 * 
	 * @param valeur
	 */
	public static void setLevelLight(int valeur) {
		getLL = valeur;
		if (valeur > 100)
			valeur = 100;
		if (valeur < 0)
			valeur = 0;
		ClientResource set_level_light = new ClientResource("http://" + address
				+ ":9000/light?method=setLevel&level=" + valeur);
		// Update value
		set_level_light.get();
		set_level_light.release();
	}

	/**
	 * pullUpShutter : Monter les volets
	 */
	public static void pullUpShutter() {
		if (getShutterState() != 2) {
			ClientResource updateShutter = new ClientResource("http://"
					+ address + ":9000/shutter?method=pullUp");
			updateShutter.get();

			updateShutter.release();
			getSS = 2;
		}
	}

	/**
	 * pullDownShutter : Descendre les volets
	 * 
	 * @throws IOException
	 * @throws ResourceException
	 */
	public static void pullDownShutter() {
		if (getShutterState() != 0) {
			ClientResource updateShutter = new ClientResource("http://"
					+ address + ":9000/shutter?method=pullDown");
			updateShutter.get();

			updateShutter.release();
			getSS = 0;
		}
	}

	/**
	 * setIntermediateShutter : Mettre à 50% les volets
	 * 
	 * @throws IOException
	 * @throws ResourceException
	 */
	public static void setIntermediateShutter() {
		if (getShutterState() != 1) {
			ClientResource updateShutter = new ClientResource("http://"
					+ address + ":9000/shutter?method=setIntermediate");
			updateShutter.get();

			updateShutter.release();
			getSS = 1;
		}
	}

	/**
	 * Mise a jour de la consigne de luminosité interieure
	 */
	public static void setLightSetpoint(int light) {
		LightSetpoint = light;
	}

	/**
	 * Mise a jour de la consigne de température intérieure
	 */
	public static void setTempSetpoint(float temp) {
		temperatureSetpoint = temp;
	}

	/**
	 * Mise a jour de la consigne de prise en charge automatique
	 */
	public static void setAutomatic(boolean x) {
		automatic = x;
	}

	/**
	 * Mise a jour de l'adresse IP
	 * 
	 * @param x
	 *            = nouvelle adresse ip
	 */
	public static void setAddressIP(String x) {
		address = x;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	// PARTIE CALCUL

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Calcul automatique du niveau de l'ampoule
	 * 
	 * @param consigne
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int autoLightLevel() {
		int LumExt = getLE;
		int ShutterState = getSS;
		int LightLevel;
		double C;
		// calcul des paramètres afin de trouver la valeur du niveau de
		// luminosité à mettre
		if (ShutterState == 0) {
			C = -LightSetpoint;
			LightLevel = calculLightLevel(C);
		} else if (ShutterState == 1) {
			C = (LumExt / 2) - LightSetpoint;
			LightLevel = calculLightLevel(C);
		} else /* ShutterState==2 */{
			C = LumExt - LightSetpoint;
			LightLevel = calculLightLevel(C);
		}
		setLevelLight(LightLevel);
		return LightLevel;

	}

	/**
	 * calcul de X (valeur du niveau de l'eclairage) d'une équation second degré
	 * 
	 * @param C
	 * @return
	 */
	public static int calculLightLevel(double C) {
		int X;
		double A = 0.0501;
		double B = -0.0132;
		double delta;
		if (C == 0)
			return 0;
		delta = ((B * B) - (4 * A * C));
		if (delta < 0) {
			return 0;
		}
		X = (int) Math.ceil((-B + Math.sqrt(delta)) / (2 * A));
		if (X > 100)
			X = 100;
		return X;

	}

	public static void autoShutterWithoutTemp() {

		if ((getLE / 2) > LightSetpoint) {
			pullDownShutter();
		} else if ((getLE) > LightSetpoint) {
			setIntermediateShutter();
		} else {
			pullUpShutter();
		}

	}

	/**
	 * autoShutterState : Calcul automatique de la position du volet
	 * 
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static void autoShutterState() {
		int lightExt = getLE;
		float tempInt = getTI;
		float tempExt = getTE;
		boolean weather = getW;

		if (lightExt >= LightSetpoint) {
			if (tempExt > 25) {
				if (tempInt >= temperatureSetpoint) {
					pullDownShutter();

					// if (weather == true) {
					// pullDownShutter();
					// } else if (weather == false) {
					// pullDownShutter();
					// }
				} else if (tempInt < temperatureSetpoint) {
					pullUpShutter();

					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					pullUpShutter();

					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				} else if (tempInt < temperatureSetpoint) {
					pullUpShutter();
					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				}
			} else if (tempExt < temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				}
			}

		} else if (lightExt < LightSetpoint && lightExt >= 50) {

			if (tempExt > 25) {
				if (tempInt >= temperatureSetpoint) {
					pullDownShutter();
					// if (weather == true) {
					// pullDownShutter();
					// } else if (weather == false) {
					// pullDownShutter();
					// }
				} else if (tempInt < temperatureSetpoint) {
					pullUpShutter();
					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					pullUpShutter();
					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				} else if (tempInt < temperatureSetpoint) {
					pullUpShutter();
					// if (weather == true) {
					// pullUpShutter();
					// } else if (weather == false) {
					// pullUpShutter();
					// }
				}
			} else if (tempExt < temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					pullDownShutter();
					// if (weather == true) {
					// pullDownShutter();
					// } else if (weather == false) {
					// pullDownShutter();
					// }
				} else if (tempInt < temperatureSetpoint) {
					pullDownShutter();
					// if (weather == true) {
					// pullDownShutter();
					// } else if (weather == false) {
					// pullDownShutter();
					// }
				}
			}

		} else if (lightExt < 50) {
			pullDownShutter();
		}
	}
}
