package com.delet_dis.workingwithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.GoalsViewHolder> {

  private List<MatchInformation> matchInformationList = new ArrayList<>();

  public void setItems(Collection<MatchInformation> matchInformations) {
	matchInformationList.addAll(matchInformations);
	notifyDataSetChanged();
  }

  public void clearItems() {
	matchInformationList.clear();
	notifyDataSetChanged();
  }

  @NonNull
  @Override
  public GoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
	View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.list_item, parent, false);
	return new GoalsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull GoalsViewHolder holder, int position) {
	holder.bind(matchInformationList.get(position));
  }

  @Override
  public int getItemCount() {
    return matchInformationList.size();
  }

  static class GoalsViewHolder extends RecyclerView.ViewHolder {
	private TextView teamHomeName;
	private TextView teamGuestName;

	private TextView teamHomeGoals;
	private TextView teamGuestGoals;

	public GoalsViewHolder(View view) {
	  super(view);

	  teamHomeName = view.findViewById(R.id.teamHomeName);
	  teamGuestName = view.findViewById(R.id.teamGuestName);

	  teamHomeGoals = view.findViewById(R.id.teamHomeGoals);
	  teamGuestGoals = view.findViewById(R.id.teamGuestGoals);
	}

	public void bind(MatchInformation matchInformation) {
	  teamHomeName.setText(matchInformation.getTeamHome());
	  teamGuestName.setText(matchInformation.getTeamGuest());

	  teamHomeGoals.setText(Integer.toString(matchInformation.getGoalsHome()));
	  teamGuestGoals.setText(Integer.toString(matchInformation.getGoalsGuest()));
	}
  }
}
