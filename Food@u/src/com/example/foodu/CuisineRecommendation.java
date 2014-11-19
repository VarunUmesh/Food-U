package com.example.foodu;

import model.Eatery;
import helper.DatabaseHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CuisineRecommendation extends Activity implements OnClickListener {

	DatabaseHandler db = new DatabaseHandler(this);
	Button like, dislike;
	TextView title, address;
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuisine_recommendation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cuisine_recommendation, menu);
		
		like = (Button) findViewById(R.id.like);
		dislike = (Button) findViewById(R.id.dislike);
		like.setOnClickListener(this);
		dislike.setOnClickListener(this);
		
		title = (TextView) findViewById(R.id.title);
		address = (TextView) findViewById(R.id.button_text);
		
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

	@Override
	public void onClick(View arg0) {
		int u = arg0.getId();
		switch(u){
		case R.id.like:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			break;
		case R.id.dislike:
			if(count < 2){
				count++;
				Eatery e = db.getEatery(count+1);
				title.setText("How about " + e.getName());
				address.setText(e.getAddress1() + "\n" + e.getAddress2() + "\n" + e.getAddress3());
			}
			else{
		        this.setTheme(R.style.CustomAlertDialog);
				new AlertDialog.Builder(this)
			    .setTitle("Please try again")
			    .setMessage("Sorry you did not like our recommendation.")
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	Intent i = new Intent(getApplication(), MainActivity.class);
						startActivity(i);
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
			}
				
			break;
		}
	}
}
