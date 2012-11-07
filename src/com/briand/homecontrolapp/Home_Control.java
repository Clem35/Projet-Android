package com.briand.homecontrolapp;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class Home_Control {

	public static int LightSetpoint = 400;
	public static float temperatureSetpoint = 20;
	public static boolean automatic = true;
	public static String address = "localhost";
	public static int getLI = 0;
	public static int getLE = 0;
	public static float getTI = 0;
	public static float getTE = 0;
	public static int getSS = 0;
	public static boolean getW = false;
	public static int getLL = 0;
	public static int getLB = 0;
	
	
	public static void traitementAuto() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					getLI=getLightInt();
					getLE=getLightExt();
					getTI=getTempInt();
					getTE=getTempExt();
					getSS=getShutterState();
					getW=getWeather();
					getLL=getLampeLevel();
					getLB=getLampeBrightness();
				}
			}
		}).start();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getLightInt------ : " + get_light_int);
		light_int.release();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getLightExt------ : " + get_light_ext);
		light_ext.release();
		return get_light_ext;
	}

	/**
	 * getTempInt : obtenir la température intérieure
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static float getTempInt() {
		ClientResource temp_int = new ClientResource("http://" + address
				+ ":9000/intTemp?method=getTemperature");
		float get_temp_int = (float) 78.78;
		try {
			get_temp_int = Float.parseFloat(temp_int.get().getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			get_temp_int = (float) 1.111;
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			get_temp_int = (float) 2.222;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			get_temp_int = (float) 3.333;
		}

		System.out.println("------getTempInt------ : " + get_temp_int);
		temp_int.release();
		return get_temp_int;
	}

	/**
	 * getTempExt : Obtenir la température extérieure
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static float getTempExt() {
		ClientResource temp_ext = new ClientResource("http://" + address
				+ ":9000/extTemp?method=getTemperature");
		float get_temp_ext = 0;
		try {
			get_temp_ext = Float.parseFloat(temp_ext.get().getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getTempExt------ : " + get_temp_ext);
		temp_ext.release();
		return get_temp_ext;
	}

	/**
	 * getWeather : obtenir la météo
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static boolean getWeather() {
		ClientResource weather = new ClientResource("http://" + address
				+ ":9000/extWeather?method=isSunny");
		boolean get_weather = false;
		try {
			get_weather = Boolean.parseBoolean(weather.get().getText());
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getWeather------ : " + get_weather);
		weather.release();
		return get_weather;
	}

	/**
	 * getLampeLevel : Obtenir le niveau de la lampe (entre 0 et 100%)
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int getLampeLevel() {
		ClientResource lampe_level = new ClientResource("http://" + address
				+ ":9000/light?method=getLevel");
		int get_lampe_level = 0;
		try {
			get_lampe_level = Integer.parseInt(lampe_level.get().getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getLampeLevel------ : " + get_lampe_level);
		lampe_level.release();
		return get_lampe_level;
	}

	/**
	 * GetLampeBrightness : obtenir la luminosité de la lampe
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int getLampeBrightness() {
		ClientResource lampe_brightness = new ClientResource("http://"
				+ address + ":9000/light?method=getBrightness");
		int get_lampe_brightness = 0;
		try {
			get_lampe_brightness = Integer.parseInt(lampe_brightness.get()
					.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------getLampeBrightness------ : "
				+ get_lampe_brightness);
		lampe_brightness.release();
		return get_lampe_brightness;
	}

	/**
	 * getShutterState : Obtenir l'état du volet roulant
	 * 
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int getShutterState() {
		ClientResource shutter_state = new ClientResource("http://" + address
				+ ":9000/shutter?method=getState");
		int get_shutter_state = 0;
		try {
			get_shutter_state = Integer.parseInt(shutter_state.get().getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("------getShutterState------ : " + get_shutter_state);
		shutter_state.release();
		return get_shutter_state;
	}

	// Modification de valeurs

	/**
	 * setLevelLight : Modifier le niveau de luminosité de la lampe (0 à 100%)
	 * 
	 * @param valeur
	 */
	public static void setLevelLight(int valeur) {
		ClientResource set_level_light = new ClientResource("http://" + address
				+ ":9000/light?method=setLevel&level=" + valeur);
		// Update value
		set_level_light.get();
		set_level_light.release();
		getLL=valeur;
		System.out.println("------setLevelLight------ : " + valeur);
	}

	/**
	 * pullUpShutter : Monter les volets
	 * 
	 * @throws IOException
	 * @throws ResourceException
	 */
	public static void pullUpShutter() {
		if (getShutterState() != 2) {
			ClientResource updateShutter = new ClientResource("http://"
					+ address + ":9000/shutter?method=pullUp");
			updateShutter.get();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateShutter.release();
			getSS=2;
			System.out.println("------pullUpShutter------ : ");
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
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateShutter.release();
			getSS=0;
			System.out.println("------pullDownShutter------ : ");
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
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateShutter.release();
			getSS=1;
			System.out.println("------setIntermediateShutter------ : ");
		}
	}

	/**
	 * Mise a jour de la consigne de luminosité interieure
	 */
	public static void setLightSetpoint(int light) {
		LightSetpoint = light;
		System.out.println("------setLightSetpoint------ : " + light);

	}

	/**
	 * Mise a jour de la consigne de température intérieure
	 */
	public static void setTempSetpoint(float temp) {
		temperatureSetpoint = temp;
		System.out.println("------setTempSetpoint------ : " + temp);

	}

	/**
	 * Mise a jour de la consigne de prise en charge automatique
	 */
	public static void setAutomatic(boolean x) {
		automatic = x;
		System.out.println("------setAutomatic------ : " + x);
	}

	public static void setAddressIP(String x) {
		address = x;
		System.out.println("------setIP------ : " + address);
	}

	// /////////////////////////
	// //// PARTIE CALCUL //////
	// /////////////////////////

	/**
	 * Calcul automatique du niveau de l'ampoule
	 * 
	 * @param consigne
	 * @return
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static int autoLightLevel2() {
		int LumExt = getLightExt();
		int ShutterState = getShutterState();
		int LightLevel;
		double C;
		// calcul des paramètres
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

	public static int autoLightLevel() {
		int LumExt = getLE;
		int ShutterState = getSS;
		int LightLevel;
		double C;
		// calcul des paramètres
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
		double B = -0.0111;
		double delta;
		delta = ((B * B) - (4 * A * C));
		if (delta < 0) {
			return 0;
		}
		X = (int) Math.ceil((-B + Math.sqrt(delta)) / (2 * A));
		if (X > 100)
			X = 100;
		return X;

	}

	/**
	 * Calcul automatique du niveau des volets roulants
	 * 
	 * @throws IOException
	 * @throws ResourceException
	 */

	// public public static void autoShutterState() throws ResourceException,
	// IOException {
	// int lightExt = getLightExt();
	// int lightInt = getLightInt();
	// float tempInt = getTempInt();
	// float tempExt = getTempExt();
	// boolean weather = getWeather();
	//
	// if (lightExt >= LightSetpoint) {
	// if (lightInt >= LightSetpoint) {
	// if (tempExt > 25) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt < temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// }
	// }
	// } else if (lightInt < LightSetpoint) {
	// if (tempExt > 25) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt < temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// }
	// }
	// }
	//
	// } else if (lightExt < LightSetpoint && lightExt >= 50) {
	// if (lightInt >= LightSetpoint) {
	// if (tempExt > 25) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt < temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// }
	// }
	// } else if (lightInt < LightSetpoint) {
	// if (tempExt > 25) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullUpShutter();
	// } else if (weather == false) {
	// pullUpShutter();
	// }
	// }
	// } else if (tempExt < temperatureSetpoint) {
	// if (tempInt >= temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// } else if (tempInt < temperatureSetpoint) {
	// if (weather == true) {
	// pullDownShutter();
	// } else if (weather == false) {
	// pullDownShutter();
	// }
	// }
	// }
	// }
	//
	// } else if (lightExt < 50) {
	// pullDownShutter();
	// }
	// }

	/**
	 * autoShutterState2 : Calcul automatique de la position du volet
	 * 
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static void autoShutterState2() {
		int lightExt = getLightExt();
		// int lightInt = getLightInt();
		float tempInt = getTempInt();
		float tempExt = getTempExt();
		boolean weather = getWeather();

		if (lightExt >= LightSetpoint) {
			if (tempExt > 25) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
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
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt < temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				}
			}

		} else if (lightExt < 50) {
			pullDownShutter();
		}
	}
	//methode rapide
	public static void autoShutterState() {
		int lightExt = getLE;
		// int lightInt = getLightInt();
		float tempInt = getTI;
		float tempExt = getTE;
		boolean weather = getW;

		if (lightExt >= LightSetpoint) {
			if (tempExt > 25) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
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
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt <= 25 && tempExt >= temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullUpShutter();
					} else if (weather == false) {
						pullUpShutter();
					}
				}
			} else if (tempExt < temperatureSetpoint) {
				if (tempInt >= temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				} else if (tempInt < temperatureSetpoint) {
					if (weather == true) {
						pullDownShutter();
					} else if (weather == false) {
						pullDownShutter();
					}
				}
			}

		} else if (lightExt < 50) {
			pullDownShutter();
		}
	}
}
