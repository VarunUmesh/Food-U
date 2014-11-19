package com.example.foodu;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.foodu.R;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NotificationListAdapter extends ArrayAdapter<NotificationData> {
	private final Context context;
	LinkedList<NotificationData> values;
	SparseBooleanArray mSelectedItemsIds;

	public NotificationListAdapter(Context context, LinkedList<NotificationData> values) {
		super(context, R.layout.listrowlayout, values);
		this.context = context;
		this.values = values;
		mSelectedItemsIds = new SparseBooleanArray();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listrowlayout, parent, false);
		TextView title = (TextView) rowView.findViewById(R.id.textView1);
		title.setTextColor(Color.BLACK);
		title.setText(values.get(position).title);

		TextView venue = (TextView) rowView.findViewById(R.id.textView2);
		venue.setTextColor(Color.BLACK);
		venue.setText(values.get(position).venue);

		TextView date = (TextView) rowView.findViewById(R.id.textView3);
		date.setTextColor(Color.BLACK);
		date.setText(values.get(position).date);

		TextView time = (TextView) rowView.findViewById(R.id.textView4);
		time.setTextColor(Color.BLACK);
		time.setText(values.get(position).time);

		rowView.setBackgroundColor(mSelectedItemsIds.get(position) ? 0x9934B5E4
				: Color.TRANSPARENT);

		return rowView;
	}

	@Override
	public void add(NotificationData data) {
		values.add(data);
		notifyDataSetChanged();
	}

	@Override
	public void remove(NotificationData object) {
		// super.remove(object);
		values.remove(object);
		notifyDataSetChanged();
	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);

		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}


}
