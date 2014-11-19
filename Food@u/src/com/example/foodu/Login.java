package com.example.foodu;

import helper.DatabaseHandler;
import model.EmailValidator;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends ActionBarActivity implements OnClickListener {

	DatabaseHandler db = new DatabaseHandler(this);
	Button loginBtn, createBtn; EditText email, password;
	EmailValidator validateEmail = new EmailValidator();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);


		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		
		
		loginBtn = (Button) findViewById(R.id.loginBtn);
		createBtn = (Button) findViewById(R.id.createBtn);
		loginBtn.setOnClickListener(this);
		createBtn.setOnClickListener(this);
		email = (EditText) findViewById(R.id.emailtxt);
		password = (EditText) findViewById(R.id.passwordtxt);
		loginBtn.setEnabled(false);
		email.addTextChangedListener(new TextWatcher(){
		    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
		    public void onTextChanged(CharSequence s, int start, int before, int count){
		    	if(s.length() > 0 && validateEmail.validate(s.toString()) && password.getText().toString().length() > 0)
		    		loginBtn.setEnabled(true);
		    	else
		    		loginBtn.setEnabled(false);
		    }
			@Override
			public void afterTextChanged(Editable arg0) {
				
			}
		}); 
		password.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(s.length() > 0 && validateEmail.validate(email.getText().toString()))
		    		loginBtn.setEnabled(true);
		    	else
		    		loginBtn.setEnabled(false);
			}
			
		});
		
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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			
			
			return rootView;
		}
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.createBtn:
			Intent cIntent = new Intent(this, CreateAccount.class);
		    startActivity(cIntent);
		    break;
		case R.id.loginBtn:
			boolean isValid = db.validateUser(email.getText().toString().toLowerCase(), password.getText().toString());
			//Toast.makeText(this, "This is " + isValid, Toast.LENGTH_SHORT).show();
			if(isValid)
			{
				Intent intent = new Intent(this, MainActivity.class);
			    startActivity(intent);
			}
			break;
		}
		
	}


}
