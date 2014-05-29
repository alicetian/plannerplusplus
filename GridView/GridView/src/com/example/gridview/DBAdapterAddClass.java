package com.example.gridview;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Classes;
import com.example.model.Homework;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DBAdapter
 * Dan Armendariz
 * Computer Science E-76
 * 
 * Provides an interface through which we can perform queries
 * against the SQLite database.
 */

public class DBAdapterAddClass {

	// define the layout of our table in fields
	// "_id" is used by Android for Content Providers and should
	// generally be an auto-incrementing key in every table.
	public static final String KEY_ROWID = "_id";
	public static final String KEY_Class = "class";
	public static final String KEY_Desc = "description";
	public static final String KEY_Days = "days";
	public static final String KEY_Time = "time";
	
	public static final String HW_ID = "_id";
	public static final String HW_Class = "class";
	public static final String HW_Desc = "description";
	public static final String HW_Time = "time";
	public static final String HW_Date = "dateDue";
	

	// define some SQLite database fields
	// Take a look at your DB on the emulator with:
	// 	adb shell 
	//  sqlite3 /data/data/<pkg_name>/databases/<DB_NAME>
	private static final String DB_NAME = "db_example";
	private static final String DB_TABLE = "classes";
	private static final String DB_TABLE2 = "assignments";
	private static final int    DB_VER = 1;

	// a SQL statement to create a new table
	private static final String DB_Class = 
		"CREATE TABLE classes (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		 "class TEXT NOT NULL, description TEXT NOT NULL, days TEXT NOT NULL, time TEXT NOT NULL);";
	private static final String DB_HW = 
			"CREATE TABLE assignments (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			 "class TEXT NOT NULL, description TEXT NOT NULL, time TEXT NOT NULL, dateDue date NOT NULL);";

	// define an extension of the SQLiteOpenHelper to handle the
	// creation and upgrade of a table
	private static class DatabaseHelper extends SQLiteOpenHelper {

		// Class constructor
		DatabaseHelper(Context c) {
			// instantiate a SQLiteOpenHelper by passing it
			// the context, the database's name, a CursorFactory 
			// (null by default), and the database version.
			super(c, DB_NAME, null, DB_VER);
		}

		// called by the parent class when a DB doesn't exist
		public void onCreate(SQLiteDatabase db) {
			// Execute our DB_CREATE statement
			db.execSQL(DB_Class);
			db.execSQL(DB_HW);
		}
		
		// called by the parent when a DB needs to be upgraded
		public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
			// remove the old version and create a new one.
			// If we were really upgrading we'd try to move data over
			db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
			onCreate(db);
		}
	}


	// useful fields in the class
    private final Context context;	
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private String[] allColumns = { KEY_ROWID, KEY_Class, KEY_Desc, KEY_Days, KEY_Time};
    private String[] hwList = { HW_ID, HW_Class, HW_Desc, HW_Time};

    // DBAdapter class constructor
	public DBAdapterAddClass (Context c) {
		this.context = c;
	}
	
	/** Open the DB, or throw a SQLException if we cannot open
	  * or create a new DB.
	  */ 
	public DBAdapterAddClass open() throws SQLException {
		// instantiate a DatabaseHelper class (see above)
		helper = new DatabaseHelper(context);

		// the SQLiteOpenHelper class (a parent of DatabaseHelper)
		// has a "getWritableDatabase" method that returns an
		// object of type SQLiteDatabase that represents an open
		// connection to the database we've opened (or created).
		db = helper.getWritableDatabase();

		return this;
	}
	
	/** Close the DB
	  */
	public void close() {
		helper.close();
	}

	/** Insert a user and password into the db
	  * 
	  * @param user username (string)
	  * @param pass user's password (string)
	  * @return the row id, or -1 on failure
	 */
	public long insertClass(String name, String desc, String days, String time) {
		ContentValues vals = new ContentValues();
		vals.put(KEY_Class, name);
		vals.put(KEY_Desc, desc);
		vals.put(KEY_Days, days);
		vals.put(KEY_Time, time);
		return db.insert(DB_TABLE, null, vals);
	}
	public long insertHW(String className, String desc, String time, String dateDue) {
		
		ContentValues vals = new ContentValues();
		
		vals.put(HW_Class, className);
		vals.put(HW_Desc, desc);
		vals.put(HW_Time, time);
		vals.put(HW_Date, dateDue);
		
		return db.insert(DB_TABLE2, null, vals);
	}
	
	  public List<Homework> getReviews(int id) {
		    List<Homework> reviews = new ArrayList<Homework>();
		    
		    Cursor cursor = db.query(DB_TABLE2,
		    	hwList, HW_ID + " = " + id , null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Homework review = cursorToHw(cursor);
		      reviews.add(review);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return reviews;
		  }

		  private Homework cursorToHw(Cursor cursor) {
		    Homework hw = new Homework();
		    hw.setId(cursor.getInt(0));
		    hw.setClassName(cursor.getString(1));
		    hw.setDescription(cursor.getString(2));
		    hw.setTime(cursor.getString(3));
		    return hw;
		  }
	/** Authenticate a user by querying the table to see
	  * if that user and password exist. We expect only one row
	  * to be returned if that combination exists, and if so, we
	  * have successfully authenticated.
	  * 
	  * @param user username (string)
	  * @param pass user's password (string)
	  * @return true if authenticated, false otherwise
	 */
	
	  public List<Classes> getAllClasses() {
		    List<Classes> classes = new ArrayList<Classes>();
		    
		    Cursor cursor = db.query(DB_TABLE,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Classes c = cursorToClass(cursor);
		      classes.add(c);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return classes;
		  }

		  private Classes cursorToClass(Cursor cursor) {
		    Classes c = new Classes();
		    c.setId(Integer.parseInt(cursor.getString(0)));
		    c.setClassName(cursor.getString(1));
		    c.setDescription(cursor.getString(2));
		    c.setDays(cursor.getString(3));
		    c.setTime(cursor.getString(4));
		    
		    
		    
		    return c;
		  }
	/*public boolean authenticateUser(String name, String desc, String time, String days ) {
		// Perform a database query
		Cursor cursor = db.query(
				DB_TABLE, // table to perform the query
				new String[] { KEY_Class }, //resultset columns/fields
				KEY_Class + "=? AND " + KEY_Desc + "=? AND " + KEY_Days + "=? AND " +  KEY_Time + "=?" , //condition or selection
				new String[] { name, desc, time, days },  //selection arguments (fills in '?' above)
				null,  //groupBy
				null,  //having
				null   //orderBy
			);

		// if a Cursor object was returned by the query and
		// that query returns exactly 1 row, then we've authenticated
		if(cursor != null && cursor.getCount() == 1) {
			return true;
		}
		
		// The query returned no results or the incorrect
		// number of rows
		return false;
	}*/

}
