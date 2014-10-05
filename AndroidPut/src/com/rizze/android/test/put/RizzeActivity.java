package com.rizze.android.test.put;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rizze.vcfiscal.tools.S3Bucket;

public class RizzeActivity extends ActionBarActivity {

	
	//TODO REVIEW
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rizze);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rizze, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	public void sendButton(View view){
		Context context = getApplicationContext();
		CharSequence text = "PUT to S3";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		//
		S3Bucket s3=S3Bucket.i();
		URL url=s3.getSecuredURL("RIZZE/DEV/SAOLUIS/"+UUID.randomUUID()+".json");
		int ret=0;
		try {
			ret = s3.putDocument(url, IOUtils.toInputStream("Tu é doido não?","UTF-8"));
		} catch (IOException e) {
			
		}		
		
		toast = Toast.makeText(context, text+"? "+(ret==201?"OK":"KO") +" ret:"+ret, duration);
		toast.show();
		
	}
	
	
	
	
}
