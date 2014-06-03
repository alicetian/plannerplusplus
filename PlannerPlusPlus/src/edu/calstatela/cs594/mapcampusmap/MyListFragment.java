package edu.calstatela.cs594.mapcampusmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {

	Activity mActivity;
	DBAdapter db;


	String[] stuff = { "ipsum", "laurum", "ultifathom", "mermelon" };

	private ArrayList<String> stuffList = new ArrayList<>(Arrays.asList(stuff));
	ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		db = new DBAdapter(mActivity);
		db.open();
		List<ClassRoom> rooms = db.getClassRooms();
		String[] roomArray = new String[rooms.size()];
		for(int i = 0; i < rooms.size(); i++)
			roomArray[i] = rooms.get(i).getName();
		// stuffList = new ArrayList<>(Arrays.asList(stuff));
		// stuff = stuffList.toArray(new String[0]);
//		adapter = new ArrayAdapter<String>(inflater.getContext(),
//				android.R.layout.simple_list_item_1, 0, stuffList);
		adapter = new ArrayAdapter<String>(inflater.getContext(),
				android.R.layout.simple_list_item_1, 0, roomArray);
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		adapter.remove(stuffList.get(position));
		// Intent i;
	}
	
	

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		db.close();
	}

	public ArrayAdapter<String> getAdapter() {
		return adapter;
	}

	public void setAdapter(ArrayAdapter<String> adapter) {
		this.adapter = adapter;
	}

	public String[] getStuff() {
		return stuff;
	}

	public void setStuff(String[] stuff) {
		this.stuff = stuff;
	}

	public ArrayList<String> getStuffList() {
		return stuffList;
	}

	public void setStuffList(ArrayList<String> stuffList) {
		this.stuffList = stuffList;
	}

}
