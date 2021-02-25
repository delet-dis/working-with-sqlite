package com.delet_dis.workingwithsqlite;

public class MatchInformation {
  private long id;
  private String teamHome;
  private String teamGuest;
  private int goalsHome;
  private int goalsGuest;

  public MatchInformation(String teamHouse, String teamGuest, int goalsHouse, int goalsGuest) {
	this.teamHome = teamHouse;
	this.teamGuest = teamGuest;
	this.goalsHome = goalsHouse;
	this.goalsGuest = goalsGuest;
  }

  public long getId() {
	return id;
  }

  public String getTeamHome() {
	return teamHome;
  }

  public String getTeamGuest() {
	return teamGuest;
  }

  public int getGoalsHome() {
	return goalsHome;
  }

  public int getGoalsGuest() {
	return goalsGuest;
  }
}
