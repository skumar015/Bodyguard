package com.example.apkabhaijaan;



import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Playalarm extends Activity {
	 Button start,stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playalarm);
		
		  start=(Button)findViewById(R.id.button1);  
	       
	        stop=(Button)findViewById(R.id.button2);  
	        
	        final MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.alarmm);  
	        
	          
	        start.setOnClickListener(new OnClickListener() {  
	            @Override  
	            public void onClick(View v) { 
	            	try{  
	 	               // you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3  
	 	       // mp.setDataSource("/res/raw/alarmm.mp3");  
	 	        mp.stop();
	 	        mp.prepare();  
	 	        }catch(Exception e){e.printStackTrace();}  
	                mp.start();  
	            }

				
	        });  
	       
	        stop.setOnClickListener(new OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                mp.stop();  
	            }  
	        });  
	    }

	
	}  