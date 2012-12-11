package com.briand.homecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Volet extends Activity implements SeekBar.OnSeekBarChangeListener{
	SeekBar mSeekBar;
	ImageView voletGrad;
	TextView volet;
	int position = Home_Control.getSS;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	 overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);  //Animation
		setContentView(R.layout.activity_volet);
		voletGrad=(ImageView)findViewById(R.id.imageVoletGrad);
		volet=(TextView)findViewById(R.id.edit_Volet);
		modificationVolet();
		
		mSeekBar = (SeekBar) findViewById(R.id.seekBarVolet);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setProgress(position);
	}

	public void popUp(String message) {
		Toast.makeText(this, message, 1).show();
	}

	public void modificationVolet(){
		if(position==0){
			volet.setText("0%");
			voletGrad.setImageResource(R.drawable.ferme);
		}else if(position==1){
			volet.setText("50%");
			voletGrad.setImageResource(R.drawable.semiouvert);
		}if(position==2){
			volet.setText("100%");
			voletGrad.setImageResource(R.drawable.ouvert);
		}
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_volet, menu);
		return true;
	}

	public void Home(View view) {
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		position=progress;
		modificationVolet();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		modificationVolet();
		
		if(position==0){
			Home_Control.pullDownShutter();
		}else if(position==1){
			Home_Control.setIntermediateShutter();
		}if(position==2){
			Home_Control.pullUpShutter();
		}
	}
}
