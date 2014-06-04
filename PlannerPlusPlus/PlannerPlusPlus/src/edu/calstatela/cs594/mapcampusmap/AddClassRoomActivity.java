package edu.calstatela.cs594.mapcampusmap;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import edu.calstatela.cs594.plannerplusplus.R;

public class AddClassRoomActivity extends Activity implements EntryView.OnItemAddedListener {
	private DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classroom);
		db = new DBAdapter(this);
		db.open();
		/*MyListFragment mList = (MyListFragment) getFragmentManager()
				.findFragmentById(R.id.cr_listFragment);
		if(mList != null) {
			List<ClassRoom> rooms = db.getClassRooms();
			String[] roomArray = new String[rooms.size()];
			for(int i = 0; i < rooms.size(); i++)
				roomArray[i] = rooms.get(i).getName();
			roomArray = new String[3]; 
			roomArray[0] = "0000";
			roomArray[1] = "1111";
			roomArray[2] = "2222";
			mList.setStuff(roomArray);
		}*/
	}

	@Override
	public void onLocationEntered(String name, Double lat, Double lon) {
	
		Log.i("Class Room", "Entry: " + name);
		MyListFragment mList = (MyListFragment) getFragmentManager()
				.findFragmentById(R.id.cr_listFragment);	
		String roomInfo = name + " -- coordinates: [" + lat.toString() + ", " + lon.toString() + "]";
		mList.getAdapter().add(roomInfo);
		mList.getAdapter().notifyDataSetChanged();
		db.insertClassRoom(name, lat, lon);
	}

	@Override
	protected void onStop() {
		super.onStop();
		db.close();
	}

}
