package com.delet_dis.workingwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_list);

	DatabaseHelper databaseHelper = new DatabaseHelper(this);
	MatchInformation matchInformation = new MatchInformation(1, "Bears", "Lions", 2, 5);
	databaseHelper.insert(matchInformation);

	ArrayList<MatchInformation> list = databaseHelper.selectAll();
	Log.d("", "");
  }
}