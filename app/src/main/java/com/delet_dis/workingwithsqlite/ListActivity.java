package com.delet_dis.workingwithsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Collection;

public class ListActivity extends AppCompatActivity {

  private ListAdapter listAdapter;

  private RecyclerView goalsRecyclerView;

  private DatabaseHelper databaseHelper;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_list);
	goalsRecyclerView = findViewById(R.id.goalsRecyclerView);

	databaseHelper = new DatabaseHelper(this);

	listAdapter = new ListAdapter();

	loadGoals();

	goalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
	goalsRecyclerView.setAdapter(listAdapter);
  }


  private void loadGoals() {
	Collection<MatchInformation> matchesInformation = databaseHelper.selectAll();
	listAdapter.setItems(matchesInformation);

  }
}