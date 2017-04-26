package com.example.apkabhaijaan;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity {
	Button btAddContact;
	ArrayAdapter<String> adapter = null;
	ArrayList<String> mobileNoforList = null;
	MyDatabase db = null;
	ListView mylist = null;

	// MyAdapter adapter = new MyAdapter();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new MyDatabase(this);
		btAddContact = (Button) findViewById(R.id.addcontacts);
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
	/* public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)  
	    {  
	            super.onCreateContextMenu(menu, v, menuInfo);  
	            menu.setHeaderTitle("Select The Action");    
	            menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title   
	            menu.add(0, v.getId(), 0, "Cancel");   
	    }   
	    @Override    
	    public boolean onContextItemSelected(MenuItem item){    
	            if(item.getTitle()=="Delete"){  
	                 try{
	        AdapterContextMenuInfo i = (AdapterContextMenuInfo)item.getMenuInfo();
			
	        Toast.makeText(this,  mobileNoforList.get(i.position), Toast.LENGTH_LONG).show();
	        switch (item.getItemId()) {
	            case R.id.delete:
	            	Toast.makeText(this, mobileNoforList.get(i.position), Toast.LENGTH_LONG).show();
	              int d=db.DeleteRow(mobileNoforList.get(i.position));
	              adapter.notifyDataSetChanged();
	            //  mylist.setAdapter(null);
	              Toast.makeText(this, String.valueOf(d), Toast.LENGTH_LONG).show();
	                return true;
	            case R.id.help:
	                //showHelp();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
		 }catch(Exception ex)
		 {
			 Toast.makeText(this, ex.getMessage()+"==================="+mobileNoforList.size(), Toast.LENGTH_LONG).show();
			 
		 
	}    }return true;
	    }*/


	
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
	        Toast.makeText(this, mobileNoforList.get(0), Toast.LENGTH_LONG).show();
	        switch (item.getItemId()) {
	            case R.id.delete:
	            	Toast.makeText(this, mobileNoforList.get(0), Toast.LENGTH_LONG).show();
	              int d=db.DeleteRow(mobileNoforList.get(0));
	              adapter.notifyDataSetChanged();
	              Toast.makeText(this, "helloghg", 12000).show();
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
								this);
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
			break;
		}
	}

	
	}

	

