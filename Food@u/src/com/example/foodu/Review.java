package com.example.foodu;

import helper.DatabaseHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Eatery;
import model.User;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.adapters.CustomListAdapter;
import com.example.adapters.ItemModel;

public class Review extends ActionBarActivity implements OnItemClickListener {

	private List<ItemModel> movieList = new ArrayList<ItemModel>();
	private DatabaseHandler db = new DatabaseHandler(this);
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		List<model.Review> reviews = new ArrayList<model.Review>();
		reviews = db.getReviews();
		for (model.Review r : reviews)
			if (r.getComment().length() > 0) {
				User user = db.getUser(r.getEmail());
				Eatery eat = db.getEatery(r.getEatery());
				movieList.add(new ItemModel(R.drawable.gopher, eat.getName(), r
						.getComment(), user.toString(), df.format(r.getDate()),
						"", 0));
			}

		// for(int i =0; i< 10 ; i++)
		// movieList.add(new ItemModel(R.drawable.gopher, "Coffman Pizzeria ",
		// "I loved the food.\nGreat service", "lo", "8", 0));
		listView = (ListView) findViewById(R.id.reviews);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.trans_left_out, R.anim.trans_left_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);

		try {
			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			MenuItem searchItem = menu.findItem(R.id.action_search);
			SearchView searchView = (SearchView) MenuItemCompat
					.getActionView(searchItem);
			Log.e("err", "is null " + (searchView == null));
			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getComponentName()));
			searchView.setIconifiedByDefault(false);
			SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String query) {

					System.out.println("on query submit: " + query);
					Intent searchIntent = new Intent(Review.this, Search.class);
					searchIntent.putExtra("SEARCH_MESSAGE", query);
					startActivity(searchIntent);

					return true;
				}

				@Override
				public boolean onQueryTextChange(String newText) {

					return true;
				}
			};
			searchView.setOnQueryTextListener(textChangeListener);
		} catch (Exception e) {
			Log.e("test", e.getMessage() + ", here");

		}
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, UserReview.class);
		startActivity(intent);
	}
}
