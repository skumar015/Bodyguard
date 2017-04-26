package com.example.apkabhaijaan;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends Activity {
	SharedPreferences sp;
	String myprefer="myprefer";
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.splashscreen);
	 new Handler().postDelayed(new Runnable() {
         
         // Using handler with postDelayed called runnable run method

         @Override
         public void run() {
        	  sp=getSharedPreferences(myprefer,Context.MODE_PRIVATE);
        	  if(sp.contains("Name")){
             Intent i = new Intent(Splashscreen.this, RegisterNext.class);
             startActivity(i);
        	  }else{
        		  Intent i = new Intent(Splashscreen.this, MainActivity.class);
                  startActivity(i);
        	  }
             // close this activity
             finish();
         }
     }, 2*1000);
}
}
