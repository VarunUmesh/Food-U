package com.example.foodu;

import helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import model.Eatery;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.adapters.CustomListAdapter;
import com.example.adapters.ItemModel;

public class Search extends ActionBarActivity implements OnItemClickListener {

	private List<ItemModel> movieList = new ArrayList<ItemModel>();
	DatabaseHandler db = new DatabaseHandler(this);
    private ListView listView;
    private CustomListAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		final Intent queryIntent = getIntent();
		String message= queryIntent.getStringExtra("SEARCH_MESSAGE").toLowerCase();
		setTitle(message);
		
		List<Eatery> eateries = db.getEateries();
		List<Eatery> result = new ArrayList<Eatery>();
		for (Eatery e : eateries) {
		  if (e.getName().toLowerCase().contains(message)) {
		    result.add(e);
		  }
		}
		 
		for(Eatery e : result)
			movieList.add(new ItemModel(R.drawable.gopher, e.getName(), e.getAddress1() + "\n" + e.getAddress2(),"",  "", "", e.getId()));
		listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
		else if(id == R.id.action_filter){
			final Dialog dialog = new Dialog(this);
	           dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	           dialog.setContentView(R.layout.filter_search);
	           Button cancel = (Button)dialog.findViewById(R.id.cancel);
	           Button refine = (Button)dialog.findViewById(R.id.refine);
	           cancel.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
				}});
	           refine.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						dialog.dismiss();
						
					}});
	           //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	           dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ItemModel item = (ItemModel) adapter.getItem(arg2);
		Intent intent = new Intent(this, SearchDetail.class);
		intent.putExtra("EATERY", item.getKey());
	    startActivity(intent);
	}
}
