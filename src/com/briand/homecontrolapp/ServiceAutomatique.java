package com.briand.homecontrolapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceAutomatique extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	@Override
	public void onCreate() {
		int i = 1;

		while (i > 0) {
			popUp("tour : " + i);
			Home_Control.autoShutterState();
			Home_Control.autoLightLevel();

		}

		super.onCreate();
	}
	
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

}
