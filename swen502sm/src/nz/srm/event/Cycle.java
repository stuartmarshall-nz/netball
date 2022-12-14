package nz.srm.event;

import java.util.*;

import nz.srm.organisation.*;

import java.io.*;

public abstract class Cycle {

	private boolean completed;
	private int year;
	private boolean scheduled;
	private Team champion;
	private List<Team> teams; 
	private List<MatchSchedule> matches;
	private Ladder ladder;
	
	protected Cycle(List<Team> teams, int year) {
		this.year = year;
		this.completed = false;
		this.scheduled = false;
		this.champion = null;
		this.teams = teams;
		this.matches = new ArrayList<MatchSchedule>();
		this.ladder = new Ladder(this.teams);
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public int getYear() {
		return this.year;
	}
	
	public Ladder getLadder() {
		return this.ladder;
	}
	
	public Team getChampion() {
		return this.champion;
	}

	public void setChampion(Team champion) {
		this.champion = champion;
	}
	
	public void addMatches(List<MatchSchedule> newMatches) {
		this.matches.addAll(newMatches);
	}
	
	public void clearMatches() {
		this.matches.clear();
	}
	
	public List<MatchSchedule> getMatches() {
		return this.matches;
	}
	
	public void print(PrintStream out) {
		out.println(getYear() + " Season\n===========");
		this.ladder.print(out);
		out.println("=====\n");
	}

}
	
