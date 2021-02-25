package com.delet_dis.workingwithsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper {

  private static final String DATABASE_NAME = "simple.db";

  private static final int DATABASE_VERSION = 1;
  private static final String TABLE_NAME = "tableMatches";
  private static final String COLUMN_ID = "id";

  private static final String COLUMN_TEAM_HOME = "TeamНоme";
  private static final String COLUMN_TEAM_GUEST = "TeamGuest";
  private static final String COLUMN_GOALS_HOME = "GoalsHome";
  private static final String COLUMN_GOALS_GUEST = "GoalsGuast";
  private static final int NUM_COLUMN_ID = 0;

  private static final int NUM_COLUMN_TEAM_HOME = 1;
  private static final int NUM_COLUMN_TEAM_GUEST = 2;
  private static final int NUM_COLUMN_GOALS_HOME = 3;
  private static final int NUM_COLUMN_GOALS_GUEST = 4;

  private SQLiteDatabase mDataBase;

  DatabaseHelper(Context context) {
	OpenHelper openHelper = new OpenHelper(context);
	mDataBase = openHelper.getWritableDatabase();
  }

  public void insert(MatchInformation matchInformation) {
	String query = "INSERT INTO " + TABLE_NAME +
			" (" + COLUMN_TEAM_HOME + "," +
			COLUMN_TEAM_GUEST + "," +
			COLUMN_GOALS_HOME + "," +
			COLUMN_GOALS_GUEST + ") " +
			"VALUES " + "(" +
			"\"" + matchInformation.getTeamHome() + "\"" + "," +
			"\"" + matchInformation.getTeamGuest() + "\"" + "," +
			matchInformation.getGoalsHome() + "," +
			matchInformation.getGoalsGuest() + ")" +
			";";
	mDataBase.execSQL(query);
  }

  public void deleteAll() {
	mDataBase.delete(TABLE_NAME, null, null);
  }

  public ArrayList<MatchInformation> selectAll() {
	Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

	ArrayList<MatchInformation> arr = new ArrayList<>();
	mCursor.moveToFirst();
	if (!mCursor.isAfterLast()) {
	  do {
		long id = mCursor.getLong(NUM_COLUMN_ID);
		String TeamHome = mCursor.getString(NUM_COLUMN_TEAM_HOME);
		String TeamGuest = mCursor.getString(NUM_COLUMN_TEAM_GUEST);
		int GoalsHome = mCursor.getInt(NUM_COLUMN_GOALS_HOME);
		int GoalsGuest=mCursor.getInt(NUM_COLUMN_GOALS_GUEST);
		arr.add(new MatchInformation(TeamHome, TeamGuest, GoalsHome,GoalsGuest));
	  } while (mCursor.moveToNext());
	}
	return arr;
  }

  private class OpenHelper extends SQLiteOpenHelper {


	OpenHelper(Context context) {
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	  String query = "CREATE TABLE " + TABLE_NAME + " (" +
			  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			  COLUMN_TEAM_HOME + " TEXT, " +
			  COLUMN_TEAM_GUEST + " TEXT, " +
			  COLUMN_GOALS_HOME + " INT," +
			  COLUMN_GOALS_GUEST + " INT);";
	  db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	  onCreate(db);
	}
  }
}