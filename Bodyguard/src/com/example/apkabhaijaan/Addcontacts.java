package com.example.apkabhaijaan;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Addcontacts extends Activity {
	Button btAddContact;
	ArrayAdapter<String> adapter = null;
	ArrayList<String> mobileNoforList = null;
	MyDatabase db = null;
	ListView mylist = null;
	SharedPreferences sp;
	String myprefer="myprefer";

	// MyAdapter adapter = new MyAdapter();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcontact);
		db = new MyDatabase(this);
		btAddContact = (Button) findViewById(R.id.addcontacts);
		//btdelete = (Button) findViewById(R.id.button1);
		mylist = (ListView) findViewById(R.id.listView1);
		registerForContextMenu(mylist);
		btAddContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						Phone.CONTENT_URI);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

				startActivityForResult(intent, 101);
			}
		});
		
	}

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu){

	         MenuInflater inflater = getMenuInflater();
	         inflater.inflate(R.menu.main, menu);
	          return true;
	 }
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		 try{
	        //AdapterContextMenuInfo i = (AdapterContextMenuInfo)item.getMenuInfo();
			 //mobileNoforList.get(i.position);
	        //Toast.makeText(this, mobileNoforList.get(0), Toast.LENGTH_LONG).show();
	        switch (item.getItemId()) {
	            case R.id.delete:
	            	Toast.makeText(this,"Deleted " +mobileNoforList.get(0), Toast.LENGTH_LONG).show();
	              int d=db.DeleteRow(mobileNoforList.get(0));
	              adapter.notifyDataSetChanged();
	            //  Toast.makeText(this, "helloghg", 12000).show();
	              mobileNoforList = db.toFetch();
	      		adapter = new ArrayAdapter<String>(this,
	      				android.R.layout.simple_expandable_list_item_1, mobileNoforList);
	      		mylist.setAdapter(adapter);
	              Toast.makeText(this, String.valueOf(d), Toast.LENGTH_LONG).show();
	             
	                return true;
	            
	            default:
	                return super.onOptionsItemSelected(item);
	        }
		 }catch(Exception ex)
		 {
			 Toast.makeText(this, ex.getMessage()+"==================="+mobileNoforList.size(), Toast.LENGTH_LONG).show();
			 
		 }
		 return true;
	        }


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mobileNoforList = db.toFetch();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, mobileNoforList);
		mylist.setAdapter(adapter);
		
	}

	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);

		switch (arg0) {
		case (101):
			if (arg1 == Activity.RESULT_OK) {
				Uri contactData = arg2.getData();
				ContentResolver cur = getContentResolver();
				Cursor c = cur.query(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					String name = c
							.getString(c
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					String number = c
							.getString(c
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					ListView list = (ListView) findViewById(R.id.listView1);
					list.setAdapter(adapter);

					
					Toast.makeText(getApplicationContext(),
							"" + name + " " + number, 4000).show();
					boolean cnt = db.forAddContact(name, number);
					if (cnt) {

						mobileNoforList.add(number);
						
						Toast.makeText(getApplicationContext(),
								"Store" + mobileNoforList.size(), 4000).show();
						adapter.notifyDataSetChanged();

					} else {

						AlertDialog.Builder builder = new AlertDialog.Builder(
								Addcontacts.this);
						builder.setMessage("Your Can't Add more than 5 contacts");
						builder.setCancelable(false);
						builder.setPositiveButton("Okay",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
						AlertDialog alert = builder.create();
						alert.setTitle("Message");
						alert.show();
					}
				}
			}

		sp=getSharedPreferences(myprefer, Context.MODE_PRIVATE);
		Editor ed= sp.edit();
		if (mobileNoforList.size() == 1) {
			ed.putString("Contact1", mobileNoforList.get(0));
		}
		if (mobileNoforList.size() == 2) {
			ed.putString("Contact2", mobileNoforList.get(1));
		}
		if (mobileNoforList.size() == 3) {
			ed.putString("Contact3", mobileNoforList.get(2));
		}
		if (mobileNoforList.size() == 4) {
			ed.putString("Contact4", mobileNoforList.get(3));
		}
		if (mobileNoforList.size() == 5) {
			ed.putString("Contact5", mobileNoforList.get(4));
		}
		ed.commit();
		
		/*Toast.makeText(this, sp.getString("Contact1", ""), 12000).show();
		if (sp.contains("Contact2")) {
			Toast.makeText(this, sp.getString("Contact2", ""), 12000).show();
		}*/
			break;
		}
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return v;
		}

	}

	
}
