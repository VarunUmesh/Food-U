package com.example.foodu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SearchView;

public class MainActivity extends ActionBarActivity implements OnClickListener  {

	ImageButton logo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		try{
		SearchManager searchManager =
		           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		  SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		Log.e("err", "is null " + (searchView == null));
		    searchView.setSearchableInfo(
		            searchManager.getSearchableInfo(getComponentName()));
		    searchView.setIconifiedByDefault(false); 
		    SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
	        {
	            @Override
	            public boolean onQueryTextSubmit(String query)
	            {
	                
	                System.out.println("on query submit: "+query);
	                Intent searchIntent = new Intent(MainActivity.this, Search.class);
	                searchIntent.putExtra("SEARCH_MESSAGE", query);
	                startActivity(searchIntent);

	                return true;
	            }

	            @Override
	            public boolean onQueryTextChange(String newText)
	            {
	                
	                return true;
	            }
	        };
	        searchView.setOnQueryTextListener(textChangeListener);
		}
		catch(Exception e){
			Log.e("test", e.getMessage() + ", herej" );
			
		}
		
		logo = (ImageButton) findViewById(R.id.logobtn);
		logo.setOnClickListener(this);
		
		Button profileBtn = (Button) findViewById(R.id.profile);
		profileBtn.setOnClickListener(this);
		
		Button recommendationBtn = (Button) findViewById(R.id.recommendation);
		recommendationBtn.setOnClickListener(this);
		
		Button notificationBtn = (Button) findViewById(R.id.notification);
		notificationBtn.setOnClickListener(this);
		
		Button reviewBtn = (Button) findViewById(R.id.review);
		reviewBtn.setOnClickListener(this);
		
		
		//return true;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
			case R.id.action_settings:
				return true;
			case R.id.action_search:
				//Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
			case R.id.profile:
				intent = new Intent(this, Login.class);
			    startActivity(intent);
			    break;
			case R.id.recommendation:
				intent = new Intent(this, Recommendation.class);
			    startActivity(intent);
			    break;
			case R.id.notification:
				intent = new Intent(this, Notification.class);
			    startActivity(intent);
			    break;
			case R.id.review:
				intent = new Intent(this, Review.class);
			    startActivity(intent);
			    break;
			case R.id.logobtn:
				//set up dialog
               /* final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.about);
                //dialog.setTitle("This is my custom dialog box");
                dialog.setCancelable(true);*/
                //there are a lot of settings, for dialog, check them all out!
 
                //set up text
                
               LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
               View popupView = layoutInflater.inflate(R.layout.about, null);  
               final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);  
                  
               //set up button

	            Button btnDismiss = (Button)popupView.findViewById(R.id.Button01);
	            btnDismiss.setOnClickListener(new Button.OnClickListener() 
	            //set up button
               {
                @Override
                    public void onClick(View v) {
                	popupWindow.dismiss();
                    }
                });

	            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

				/*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
				alertDialogBuilder.setTitle("About Food@U");
				alertDialogBuilder
				.setMessage("Description of the application goes here")
				.setCancelable(true)
				.setIcon(R.drawable.ic_launcher)
				.setPositiveButton("Ok",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					          //dismiss the dialog  
					        }
					    });
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				int textViewId = alertDialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
				TextView tv = (TextView) alertDialog.findViewById(textViewId);
				tv.setTextColor(getResources().getColor(R.color.red));*/
				break;
		}
		
	}

}
