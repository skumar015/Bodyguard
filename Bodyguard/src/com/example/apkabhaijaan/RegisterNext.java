package com.example.apkabhaijaan;




import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.R.array;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterNext extends Activity implements OnClickListener  {
	private static final int Camera_Request=1888;
	TextView tv,tvname;
	Button addcont,trackme,alarm,setting,safetytip,camera,sos;
	ImageView im;
	SharedPreferences sp;
	String myprefer="myprefer";
	 int a=0;  
	//String tonum[]={"7404523909","7206377304"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registernext);
		final MediaPlayer mp1 = MediaPlayer.create(getBaseContext(), R.raw.alarmm); 
		tvname=(TextView)findViewById(R.id.textView2);
		sp=getSharedPreferences(myprefer,Context.MODE_PRIVATE);
		if(sp.contains("Name")){
           tvname.setText("Welcome "+sp.getString("Name", ""));
		
		
		
		
		addcont=(Button)findViewById(R.id.button1);
		trackme=(Button)findViewById(R.id.button2);
		alarm=(Button)findViewById(R.id.button3);
		//setting=(Button)findViewById(R.id.button4);
		//safetytip=(Button)findViewById(R.id.button5);
		camera=(Button)findViewById(R.id.button6);
		sos=(Button)findViewById(R.id.button7);
		
		  im=(ImageView)findViewById(R.id.imageView1);
		addcont.setOnClickListener(this);
		trackme.setOnClickListener(this);
		//		setting.setOnClickListener(this);
		//safetytip.setOnClickListener(this);
		camera.setOnClickListener(this);
		sos.setOnClickListener(this);
		
		tv=(TextView)findViewById(R.id.textView1);
		/*Bundle b1=getIntent().getExtras();
		String n=b1.getString("a");
		t1.setText("location="+n);*/
		alarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(a==1)
				{
				a=0;
				try{
				mp1.stop();
				mp1.prepare(); 
				}
				catch(Exception e)
				{
				//a=1;
				}}
				else{
					a=1;
					mp1.start();
				}
				
			}
		});
		
		
		 
	}}
	
	@Override
	public void onClick(View v) {
		int id=v.getId();
		//int a=0;
		switch (id) {
		case R.id.button1:
			int Pc=1;
			//Intent i= new Intent(this,Addcontacts.class);
			Intent i = new Intent(this, Addcontacts.class);
			startActivity(i);
			
			break;
			
		case R.id.button2:
			try{
			Intent i2=new Intent(RegisterNext.this,Trackme.class);
			startActivityForResult(i2, 2);}
			catch(Exception e)
			{
				
			}
			  
		 
		  
		 
		    /**
		     * function to load map. If map is not created it will create it for you
		     * */
		   
		        
		            /*Marker TP = googleMap.addMarker(new MarkerOptions().
		                    position(loc).title("TutorialsPoint"));*/
		        
		 
		            
		            
		       
		            /*LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		            
		            // Creating a criteria object to retrieve provider
		            Criteria criteria = new Criteria();
		 
		            // Getting the name of the best provider
		            LocationProvider p = locationManager.getProvider(LocationManager.GPS_PROVIDER);
		            String name = p.getName();
		            Location location = locationManager.getLastKnownLocation(name);
		           location.getLatitude();
		           location.getLongitude();
		            String provider = locationManager.getBestProvider(criteria, true);
		            Toast.makeText(this, name, 1000).show();*/
		        
		    
		 
		   

			

			/*@Override
			public void onMapLongClick(LatLng point) {
				// TODO Auto-generated method stub
				tv.setText("New marker added@" + point.toString());           
				googleMap.addMarker(new MarkerOptions().position(point).title(point.toString())); 
		      //  pointfinal = point;
		        Toast.makeText(this, point.toString(), Toast.LENGTH_LONG).show();
			}*/
			break;
		
		//case R.id.button3:
//			Intent pl= new Intent(this,Playalarm.class);
//			startActivity(pl);
//			break;
			
			
			
//	case R.id.button5:
//		Intent s= new Intent(this,Safetytip.class);
//		startActivity(s);
//		break;
//	
		case R.id.button6:
			camera.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent c= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(c, Camera_Request);
						
						
						
					}
				});
		break;
		
		case R.id.button7:
			
		Intent i1 = new Intent(Intent.ACTION_CALL);
		i1.setData(Uri.parse("tel: 1091"));
		startActivity(i1);
		
		 try {
			 Toast.makeText(this, "MSGGG", 2000).show();
			 SharedPreferences sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
			 if (sp.contains("Contact1")) {
				
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(sp.getString("Contact1", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
					Toast.makeText(this, " MSGGG Sent", 2000).show();
			 } 
			 if (sp.contains("Contact2")) {
				SmsManager smsManager = SmsManager.getDefault();
				 Toast.makeText(this, "MSGGG SENT2", 2000).show();
				smsManager.sendTextMessage(sp.getString("Contact2", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
			 }
			 if (sp.contains("Contact3")) {
				 SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(sp.getString("Contact3", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
			 }
			 if (sp.contains("Contact4")) {
				 SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(sp.getString("Contact4", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
			 }
			 if (sp.contains("Contact5")) {
				 SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(sp.getString("Contact5", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
			 }
			 
			 
		 } catch (Exception e) {
				Toast.makeText(getApplicationContext(),	"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
				e.printStackTrace();
			
				}break;
		}
	
	}

	 
protected void onActivityResult(int requestcode,int resultcode,Intent data)
{
	super.onActivityResult(requestcode, resultcode, data);	
	
	if (requestcode==2) {
		String m = data.getStringExtra("add");
		tv.setText(m);
	
}}}

/*@Override
public boolean onLongClick(View v) {
	// TODO Auto-generated method stub
	
	Intent i1 = new Intent(Intent.ACTION_CALL);
	i1.setData(Uri.parse("tel: 1091"));
	startActivity(i1);
	
	 try {
		 SharedPreferences sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
		 if (sp.contains("Contact1")) {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sp.getString("Contact1", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
		 }
		 if (sp.contains("Contact2")) {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(sp.getString("Contact2", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
		 }
		 if (sp.contains("Contact3")) {
			 SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sp.getString("Contact3", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
		 }
		 if (sp.contains("Contact4")) {
			 SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sp.getString("Contact4", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
		 }
		 if (sp.contains("Contact5")) {
			 SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sp.getString("Contact5", ""),null, " I AM IN TROUBLE PLEASE HELP ME"+tv.getText(), null, null);
		 }
		 
		 
	 } catch (Exception e) {
			Toast.makeText(getApplicationContext(),
				"SMS faild, please try again later!",
				Toast.LENGTH_LONG).show();
			e.printStackTrace();
		  }
	
	
	return false;
}}*/
