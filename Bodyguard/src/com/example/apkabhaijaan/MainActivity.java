package com.example.apkabhaijaan;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	SharedPreferences sp;
	TextView Signup;
	EditText uname,unum,uaddr;
	Button register;
	String myprefer="myprefer";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        uname=(EditText)findViewById(R.id.editText1);
        unum=(EditText)findViewById(R.id.editText2);
        uaddr=(EditText)findViewById(R.id.editText3);
        sp=getSharedPreferences(myprefer,Context.MODE_PRIVATE);
        
        register=(Button)findViewById(R.id.button1);
        register.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String strname= uname.getText().toString();
		String strno=unum.getText().toString();
		String struadd=uaddr.getText().toString();
		Editor ed=sp.edit();
		ed.putString("Name", strname);
		ed.putString("Phno", strno);
		ed.putString("Address", struadd);
		ed.commit();
		//Toast. makeText(this,sp.getString("Name", ""), 1000).show();
		int id=register.getId();
		if(uname.getText().toString().equals("")|| unum.getText().toString().equals("")|| uaddr.getText().toString().equals(""))
		{
			Toast. makeText(this, "Fields cannot be empty", 10000).show();
		}else if(strno.length()!=10){
			Toast.makeText(this, "Number should be of 10 Digits", 1000).show();
		}
		else{
			Toast. makeText(this, "Successfully Register", 10000).show();
			Intent i = new Intent(this,RegisterNext.class);
			startActivity(i);
		}
	}


   
}
