package com.example.foodu;

import helper.DatabaseHandler;

import java.util.Date;

import model.Eatery;
import model.Review;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class UserReview extends Activity implements OnClickListener {

	DatabaseHandler db = new DatabaseHandler(this);
	TextView title, address1, address2;
	RatingBar food,ambience,economy, cleanliness,service;
	EditText comments;
	Eatery e;
	Button submitReview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_review);
		
		Intent intent = getIntent(); 
		int id = intent.getIntExtra("EATERY", 1);
		//Toast.makeText(this, "th " + id, Toast.LENGTH_LONG).show();
		e = db.getEatery(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_review, menu);
		
		title = (TextView) findViewById(R.id.review_restaurant_name);
		address1 = (TextView) findViewById(R.id.review_rest_address1);
		address2 = (TextView) findViewById(R.id.review_rest_address2);
		title.setText(e.getName());
		address1.setText(e.getAddress1());
		address2.setText(e.getAddress2() + "\n" + e.getAddress3());
		food = (RatingBar)findViewById(R.id.food_stars);
		ambience = (RatingBar)findViewById(R.id.ambience_stars);
		economy = (RatingBar)findViewById(R.id.economical_stars);
		cleanliness = (RatingBar)findViewById(R.id.hygiene_stars);
		service = (RatingBar)findViewById(R.id.service_stars);
		comments = (EditText)findViewById(R.id.et_comments);
		submitReview = (Button)findViewById(R.id.btn_submit);
		submitReview.setOnClickListener(this);
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
	
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btn_submit:
			db.addReview(new Review("shahx118@umn.edu", 1, food.getRating(),
					ambience.getRating(),
					economy.getRating(),
					cleanliness.getRating(),
					service.getRating(),
					comments.getEditableText().toString(), new Date()));
			Intent intent = new Intent(this, com.example.foodu.Review.class);
		    startActivity(intent); 
			break;
		}
	}
}
