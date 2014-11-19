package com.example.foodu;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Recommendation extends ActionBarActivity implements OnClickListener {

	Button cuisine, profile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommendation);
		
		//TextView recommendation = (TextView)findViewById(R.id.recommendation);
		//recommendation.setText("Orange chicken" + System.getProperty("line.separator") + "abs Restaurant");

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		cuisine = (Button) findViewById(R.id.cuisine);
		profile = (Button) findViewById(R.id.profile);
		cuisine.setOnClickListener(this);
		profile.setOnClickListener(this);
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommendation, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_recommendation,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.cuisine:
			final CharSequence[] items = {"American", "Cafes", "Chinese", "Greek", "Indian", "Italian", "Mexican", "MiddleEast", "Thai", "Vietnamese"};
		    final boolean[] states = new boolean[items.length];
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle("Which cuisine do you want?");
		    builder.setMultiChoiceItems(items, states, new DialogInterface.OnMultiChoiceClickListener(){
		        public void onClick(DialogInterface dialogInterface, int item, boolean state) {
		        }
		    });
		    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            SparseBooleanArray CheCked = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
		            List<String> result = new ArrayList<String>();
		            for (int i = 0; i < items.length; i++) {
	                    if (states[i]) {
	                    	result.add((String) items[i]);
	                    }
	                }
		            //Toast.makeText(getBaseContext(), Arrays.toString(result.toArray()) + " checked!", Toast.LENGTH_LONG).show();
		            if(result.size() >0){
		            	Intent userrecommendation = new Intent(Recommendation.this, CuisineRecommendation.class);
		            	startActivity(userrecommendation);
		            }
		        }
		    });
		    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		             dialog.cancel();
		        }
		    });
		    
		    builder.create().show();
			break;
		case R.id.profile:
			Intent userrecommendation = new Intent(Recommendation.this, UserRecommendation.class);
		    startActivity(userrecommendation);
			break;
		}
		
	}

}
