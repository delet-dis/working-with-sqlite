package com.delet_dis.workingwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  private TextInputLayout guestCommandNameLayout;
  private TextInputLayout ownerCommandNameLayout;

  private TextInputLayout guestCommandScoreLayout;
  private TextInputLayout ownerCommandScoreLayout;

  private TextInputEditText guestCommandName;
  private TextInputEditText ownerCommandName;

  private Button addButton;
  private Button viewDBButton;
  private Button clearDBButton;

  private TextInputEditText guestCommandScore;
  private TextInputEditText ownerCommandScore;

  private DatabaseHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	dbHelper = new DatabaseHelper(this);

	findViewElements();

	setViewDbButtonListener();

	setAddButtonListener();

	setClearDbButtonListener();

	guestCommandName.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0) {
		  guestCommandNameLayout.setError(getString(R.string.emptyFieldError));
		} else {
		  guestCommandNameLayout.setError(null);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {

	  }
	});

	ownerCommandName.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0) {
		  ownerCommandNameLayout.setError(getString(R.string.emptyFieldError));
		} else {
		  ownerCommandNameLayout.setError(null);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {

	  }
	});

	guestCommandScore.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0) {
		  guestCommandScoreLayout.setError(getString(R.string.emptyFieldErrorAsStar));
		} else {
		  guestCommandScoreLayout.setError(null);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {

	  }
	});

	ownerCommandScore.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0) {
		  ownerCommandScoreLayout.setError(getString(R.string.emptyFieldErrorAsStar));
		} else {
		  ownerCommandScoreLayout.setError(null);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {

	  }
	});

  }

  private void setClearDbButtonListener() {
	clearDBButton.setOnClickListener(v -> dbHelper.deleteAll());
  }

  private void setAddButtonListener() {
	addButton.setOnClickListener(v -> {
	  if (isPossibleToUseActivityData()) {
		MatchInformation matchInformation = createMatchInformationWithActivityData();

		dbHelper.insert(matchInformation);

		clearInputs();
	  }
	});
  }

  private void setViewDbButtonListener() {
	viewDBButton.setOnClickListener(v -> {
	  Intent activityListIntent = new Intent(this, ListActivity.class);
	  startActivity(activityListIntent);
	});
  }

  private void findViewElements() {
	guestCommandName = findViewById(R.id.guestCommandName);
	ownerCommandName = findViewById(R.id.ownerCommandName);

	guestCommandScore = findViewById(R.id.guestCommandScore);
	ownerCommandScore = findViewById(R.id.ownerCommandScore);

	guestCommandNameLayout = findViewById(R.id.guestCommandNameLayout);
	ownerCommandNameLayout = findViewById(R.id.ownerCommandNameLayout);

	guestCommandScoreLayout = findViewById(R.id.guestCommandScoreLayout);
	ownerCommandScoreLayout = findViewById(R.id.ownerCommandScoreLayout);

	addButton = findViewById(R.id.addButton);
	viewDBButton = findViewById(R.id.viewDBButton);
	clearDBButton = findViewById(R.id.clearDBButton);
  }

  private boolean isPossibleToUseActivityData() {
	return Objects.requireNonNull(guestCommandName.getText()).toString().length() != 0 &&
			Objects.requireNonNull(ownerCommandName.getText()).toString().length() != 0 &&
			Objects.requireNonNull(guestCommandScore.getText()).toString().length() != 0 &&
			Objects.requireNonNull(ownerCommandScore.getText()).toString().length() != 0;
  }

  private void clearInputs() {
	guestCommandName.setText("");
	ownerCommandName.setText("");
	guestCommandScore.setText("");
	ownerCommandScore.setText("");

	guestCommandName.clearFocus();
	ownerCommandName.clearFocus();
	guestCommandScore.clearFocus();
	ownerCommandScore.clearFocus();
  }

  private MatchInformation createMatchInformationWithActivityData() {
	return new MatchInformation(
			Objects.requireNonNull(ownerCommandName.getText()).toString(),
			Objects.requireNonNull(guestCommandName.getText()).toString(),
			Integer.parseInt(Objects.requireNonNull(ownerCommandScore.getText()).toString()),
			Integer.parseInt(Objects.requireNonNull(guestCommandScore.getText()).toString()));
  }
}