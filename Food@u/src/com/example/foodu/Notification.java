package com.example.foodu;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import com.example.foodu.R;
import com.example.foodu.R.drawable;
import com.example.foodu.R.id;
import com.example.foodu.R.layout;
import com.example.foodu.R.menu;
import com.google.android.gcm.GCMRegistrar;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Notification extends ActionBarActivity {

	LinkedList<NotificationData> notificationList = new LinkedList<NotificationData>();
	ListView listView = null;
	NotificationListAdapter adaptor;
	ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		
		listView = (ListView) findViewById(R.id.listView1);
		
		// Retrive the data from GCMIntentService.java
        Intent i = getIntent();
        String message = i.getStringExtra("message");
		//getDataForDisplay();
        if(message!=null)
        {
        	parseData(message);
        }else{
        getDataToDisplay();
        }
		adaptor = new NotificationListAdapter(getApplicationContext(), notificationList);
		listView.setAdapter(adaptor);
		TextView emptyText = (TextView) findViewById(R.id.empty);
		emptyText.setText("No Events Yet!");
		listView.setEmptyView(emptyText);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				onListitemSelect(position);
				view.setSelected(true);
				return true;
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
        adaptor.notifyDataSetChanged();
	}	
        
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	void writeToFile(){
		FileOutputStream fos;
		try {
		fos = openFileOutput("varun", Context.MODE_PRIVATE);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(notificationList);
		oos.close();
	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void readFromFile(){
		try{
		FileInputStream fis = openFileInput("varun");
		ObjectInputStream ois = new ObjectInputStream(fis);
		LinkedList<NotificationData> local = (LinkedList<NotificationData>) ois.readObject();
		ois.close();
		
		for (int i = 0; i < local.size(); i++) {
			notificationList.add(local.get(i));
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	private void getDataToDisplay() {
		// TODO Auto-generated method stub
		readFromFile();
	}

	private void parseData(String message) {
		try {
			int len = 0;
			String[] stringArr = new String[100];
			StringTokenizer st = new StringTokenizer(message, ".");
			len = st.countTokens();
			for (int i = 0; i < len; i++) {
				if (st.hasMoreTokens()) {
					stringArr[i] = st.nextToken();
				}
			}

			NotificationData data = new NotificationData();
			data.title = stringArr[0];
			data.venue = stringArr[1];
			data.date = stringArr[2];
			data.time = stringArr[3];
			notificationList.add(data);
			readFromFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getDateToDisplay() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		writeToFile();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.notificationmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id == R.id.action_register){
			registerDevice();
			return  true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void registerDevice() {
		try {
			GCMRegistrar.checkDevice(this);
			GCMRegistrar.checkManifest(this);

			GCMRegistrar
					.register(Notification.this, GCMIntentService.SENDER_ID);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.notificationcontext, menu);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

			switch (item.getItemId()) {
			case R.id.menu_calender:
				addToCalender();
				mode.finish();
				return true;
			case R.id.menu_delete:
				//deleteData();
				showAlertBox();
				return false;
			case R.id.menu_share:
				shareDate();
				mode.finish();
				return true;
			case R.id.menu_copy:
				copyToClip();
				mode.finish();
				return true;
			default:
				return false;
			}
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode = null;
			adaptor.removeSelection();
		}
	};

	void onListitemSelect(int position) {
		adaptor.toggleSelection(position);
		boolean hasCheckedItems = adaptor.getSelectedCount() > 0;

		if (hasCheckedItems && mActionMode == null) {
			mActionMode = startActionMode((Callback) mActionModeCallback);
		} else if (!hasCheckedItems && mActionMode != null) {
			mActionMode.finish();
		}

		if (mActionMode != null)
			mActionMode.setTitle(String.valueOf(adaptor.getSelectedCount()));

	}

	protected void showAlertBox() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder1 = new AlertDialog.Builder(Notification.this);
        builder1.setMessage("Delete " + adaptor.getSelectedIds().size()+ " events?");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.alert);
        builder1.setTitle("Caution");
        builder1.setIcon(android.R.drawable.ic_dialog_alert);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteData();
                mActionMode.finish();
            }
        });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
	}

	protected void copyToClip() {

		StringBuilder shareText = new StringBuilder();
		for (int i = 0; i < adaptor.getSelectedIds().size(); i++) {
			NotificationData data = notificationList
					.get(adaptor.getSelectedIds().keyAt(i));
			shareText.append(data.title + " " + data.venue + " " + data.date
					+ " " + data.time);
			shareText.append("\n");
		}

		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("Notification App", shareText);
		clipboard.setPrimaryClip(clip);

		Toast.makeText(getApplicationContext(), "Data copied to ClipBoard",
				Toast.LENGTH_LONG).show();
	}

	protected void shareDate() {

		StringBuilder shareText = new StringBuilder();
		for (int i = 0; i < adaptor.getSelectedIds().size(); i++) {
			NotificationData data = notificationList
					.get(adaptor.getSelectedIds().keyAt(i));
			shareText.append(data.title + " " + data.venue + " " + data.date
					+ " " + data.time);
			shareText.append("\n");
		}
		String share = shareText.toString();
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, share);
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
	}

	protected void deleteData() {
		int count = 0;
		int startPoint = adaptor.getSelectedIds().keyAt(0);
		for (int i = 0; i < adaptor.getSelectedIds().size(); i++) {
			adaptor.remove(notificationList.get(startPoint));
			count++;
		}
		
		String message = " Event";
		if(count>1)
		{
			message = " Events";
		}
		Toast.makeText(getApplicationContext(),
				count + message+" deleted", Toast.LENGTH_LONG)
				.show();
		
	}

	private void addToCalender() {

		try {
			int count = 0;
			for (int i = 0; i < adaptor.getSelectedIds().size(); i++) {
				NotificationData data = notificationList
						.get(adaptor.getSelectedIds().keyAt(i));

				ContentResolver cr = getApplicationContext()
						.getContentResolver();
				ContentValues values = new ContentValues();

				String myDate = data.date + " " + data.time;

				String timeArr[] = data.time.split("to");

				SimpleDateFormat sfd = new SimpleDateFormat(
						"' Date: 'MM/dd/yyyy  'Time: 'hh a", Locale.getDefault());

				long time = sfd.parse(myDate).getTime();
				values.put(CalendarContract.Events.DTSTART, time);
				if (timeArr.length > 0) {
					String endTime = timeArr[1];
					SimpleDateFormat timeFormat = new SimpleDateFormat(
							"' Date: 'MM/dd/yyyy hh a", Locale.getDefault());
					long endtime = timeFormat.parse(data.date + " " + endTime)
							.getTime();
					values.put(CalendarContract.Events.DTEND, endtime);
				}

				values.put(CalendarContract.Events.TITLE, data.title);
				values.put(CalendarContract.Events.DESCRIPTION, data.venue);
				TimeZone timeZone = TimeZone.getDefault();
				values.put(CalendarContract.Events.EVENT_TIMEZONE,
						timeZone.getID());
				values.put(CalendarContract.Events.CALENDAR_ID, 1);
				Uri uri = cr
						.insert(CalendarContract.Events.CONTENT_URI, values);
				count++;
			}

			String message = " Event";
			if(count>1)
			{
				message = " Events";
			}
			
			Toast.makeText(getApplicationContext(),
					count + message + " added to Calender", Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
