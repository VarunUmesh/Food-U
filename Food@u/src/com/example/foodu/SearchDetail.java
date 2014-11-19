package com.example.foodu;

import helper.DatabaseHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Eatery;
import model.User;

import com.example.adapters.CustomListAdapter;
import com.example.adapters.ItemModel;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SearchDetail extends ActionBarActivity implements OnClickListener {

	ListView listView;
	ProgressBar quality;
	Button review;
	private List<ItemModel> reviews = new ArrayList<ItemModel>();
	private DatabaseHandler db = new DatabaseHandler(this);
	TextView title;
	Eatery eatery;
	int id;
	private CustomListAdapter custom_adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_detail);
		
		Intent intent = getIntent(); 
		id = intent.getIntExtra("EATERY", 1);
		
		List<model.Review> dbreviews = db.getEateryReviews(id);
		
		
		eatery = db.getEatery(id);
		if(eatery != null)
			setTitle(eatery.getName());
		//Toast.makeText(this, "this is " + eatery.getName(), Toast.LENGTH_LONG).show();
		//setTitle("ll");
		
		
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2" };
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		for(model.Review review : dbreviews)
			if(review.getComment().length() > 0){
				User reviewer = db.getUser(review.getEmail());
				reviews.add(new ItemModel(R.drawable.gopher, reviewer.toString(), review.getComment(), "", df.format(review.getDate()), "99", 0));
			}
		listView = (ListView) findViewById(R.id.reviews);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, values);
	    custom_adapter = new CustomListAdapter(this, reviews);
	    listView.setAdapter(custom_adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_detail, menu);
		
		title = (TextView) findViewById(R.id.heading);
		review = (Button) findViewById(R.id.review);
		quality = (ProgressBar) findViewById(R.id.qly);
		quality.setMax(5);
		//quality.setBackgroundColor(getResources().getColor(R.color.green));
		quality.setProgress(3);
		title.setText(eatery.getName());
		review.setOnClickListener(this);
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
		int id = arg0.getId();
		switch(id){
		case R.id.review:
			Intent i = new Intent(this, UserReview.class);
			i.putExtra("EATERY", eatery.getId());
			
			startActivity(i);
		}
		
	}
}
