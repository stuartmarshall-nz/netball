package nz.ac.wgtn.srm.event;

import java.util.*;
import java.io.*;
import nz.ac.wgtn.srm.organisation.*;

public abstract class Cycle {

	private boolean completed;
	private int year;
	private boolean scheduled;
	private Team champion;
	private List<Team> teams; 
	
	protected Cycle(List<Team> teams, int year) {
		this.year = year;
		this.completed = false;
		this.scheduled = false;
		this.champion = null;
		this.teams = teams;
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
	
	public Team getChampion() {
		return this.champion;
	}

	public void setChampion(Team champion) {
		this.champion = champion;
	}
	
	public abstract List<ScheduledMatch> getMatches();
	
	public abstract void print(PrintStream out);
	

}
