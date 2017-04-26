package com.example.apkabhaijaan;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Trackme extends Activity implements OnMapClickListener {

	TextView tr;
	GoogleMap googleMap = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trackme);
		tr=(TextView)findViewById(R.id.textView1);

		initilizeMap();
		googleMap.setOnMapClickListener(this);
	}

	private void initilizeMap()
	{
		try{
		if (googleMap == null) 
		{
			MapFragment fr = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map));
			
			googleMap = fr.getMap();

			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.setMyLocationEnabled(true);
			
		}
		}catch(Exception ex)
		{
			
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
	public void onBackPressed()
	{
		Toast.makeText(this, "please not press back", Toast.LENGTH_SHORT).show();
	}
	

	@Override
	public void onMapClick(LatLng arg1)
	{
		try{
		LocationManager mngr = (LocationManager)getSystemService(LOCATION_SERVICE);
		Geocoder g =new Geocoder(this, Locale.getDefault());
		List<Address> l = g.getFromLocation(arg1.latitude, arg1.longitude, 1);
		for(Address adr : l)
		{
			String countyName = adr.getLocality();
			String countyCode = adr.getAddressLine(0);
			LatLng cloc = new LatLng(0,0);
			
			
		
		Double lati=adr.getLatitude();
		Double longi=adr.getLongitude();
		String url="http://maps.google.com/maps?q="+lati+","+longi;
			String c=adr.getAdminArea();
			String location=countyName+" "+countyCode+" "+c+"URL="+url;
			String d="url="+url;
			tr.setText(location);
			Toast.makeText(this,"Location ::  "+countyName+"  "+countyCode+"state="+c, Toast.LENGTH_LONG).show();
			
			/*Intent i= new Intent(this,RegisterNext.class);
			i.putExtra("a",location);
			setResult(RESULT_OK, i); 
			
			finish();
		*/
		
			String m=tr.getText().toString();
			Intent i= new Intent();
			i.putExtra("add", m);
			setResult(2,i);
			finish();
		/*MarkerOptions mrkop = new MarkerOptions();
		mrkop.position(new LatLng(arg1.latitude, arg1.longitude));
		mrkop.title(countyName);
		googleMap.addMarker(mrkop);*/
		}
		}catch(IOException ex)
		{
			
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		
	}


}
