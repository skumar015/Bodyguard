package com.example.apkabhaijaan;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {

	public MyDatabase(Context context) {
		super(context, "Registration", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table record(id integer primary key , uname text , umobile integer unique , umail text, upass text )";

		db.execSQL(sql);
 
		String sql1 = "create table contact(id integer primary key, name text, mobile_number text unique)";
		db.execSQL(sql1);
		// TODO Auto-generated method stub

	}

	public long insertRow(String ruser, long _rmobile, String rmail,
			String rpass) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues c = new ContentValues();
		c.put("uname", ruser);
		c.put("umobile", _rmobile);
		c.put("umail", rmail);
		c.put("upass", rpass);

		long cnt = db.insert("record", null, c);

		return cnt;

	}

	public boolean getEDataforLogin(String email, String _password) {
		// TODO Auto-generated method stub

		boolean flag = false;
		SQLiteDatabase db = getReadableDatabase();
		String table = "record";
		String col[] = { "umail", "upass" };
		String where = "umail = ? and upass = ?";
		String[] args = { email, _password };
		Cursor cr = db.query(table, col, where, args, null, null, null);
		String uname1, uname2;
		uname1 = email;
		uname2 = _password;
		String femail, fpassword;
		System.out.println(cr.getCount());
		if (cr.moveToNext())

		{
			femail = cr.getString(cr.getColumnIndex("umail"));
			fpassword = cr.getString(cr.getColumnIndex("upass"));
			if (uname1.equals(femail) && uname2.equals(fpassword))
				;
			{
				flag = true;
			}

		}

		return flag;
	}

	public String getdataforForgot(String mob) {

		SQLiteDatabase db = getReadableDatabase();
		String tableName = "record";
		String[] cols = { "upass" };
		String whereclause = "umobile = ?";
		String args[] = { mob };
		Cursor cr = db.query(tableName, cols, whereclause, args, null, null,
				null);
		String p = null;

		while (cr.moveToNext()) {

			p = cr.getString(0);

		}

		return p;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public boolean forAddContact(String _name, String number)
	{
		boolean flag = false;
		SQLiteDatabase db = getWritableDatabase();
		String table = "contact";
		ContentValues c = new ContentValues();
		c.put("name", _name);
		c.put("mobile_number", number);
		Cursor cc = null;
		String [] col = {"mobile_number"};
		cc=db.query("contact",col,null,null,null,null,null);
		if(cc.getCount()<5)
		{
			flag = true;
			long cnt = db.insert(table, null, c);
			
			
		}
		else
		{
			flag = false;
			Log.d("Contacts"," not more than 5");
			//Toast.makeText(,"Cannot Insert more than 5 contacts", Toast.LENGTH_LONG).show();
		}
		//long cnt = db.insert(table, null, c);
		
		
		
		return flag ;
	}

	public ArrayList<String> toFetch()
	{
		ArrayList<String> temp = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		String [] col = {"name","mobile_number"};
		Cursor c = db.query("contact", col, null, null, null, null, null);
		while(c.moveToNext())
		{
			temp.add(c.getString(1));
			
		}
		
		return temp;
		   
	}
	public int DeleteRow(String number)
	{
		System.out.println("==============="+number);
		SQLiteDatabase db = getWritableDatabase();
		 String table_name = "contact";
		    String where = "mobile_number=?";
		    String [] whereargs ={number}; 
		     int delete = db.delete(table_name, where, whereargs);
		return delete;
	}
	
 
}
