package nz.srm.event;

import java.util.*;

import nz.srm.organisation.*;

import java.io.*;

public abstract class Competition implements MatchListener {

	private String name;
	private int started;
	private int frequency;
	protected List<Cycle> cycles;
	private List<Team> teams;
	private List<CompetitionListener> listeners;
	
	public Competition(String name, int started, int frequency) {
		this.name = name;
		this.started = started;
		this.frequency = frequency;
		this.cycles = new ArrayList<Cycle>();
		this.teams = new ArrayList<Team>();
		this.listeners = new ArrayList<CompetitionListener>();
	}
	
	public String getName() {
		return this.name;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public List<Team> getTeams() {
		return this.teams;
	}
	
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	
	public void removeTeam(Team t) {
		this.teams.remove(t);
	}
	
	public void clearTeams() {
		this.teams.clear();
	}
	
	public int getYearStarted() {
		return this.started;
	}
		
	public void addListener(CompetitionListener listener) {
		this.listeners.add(listener);
	}
	
	public abstract Cycle scheduleNewCycle();
	
	public abstract void print(PrintStream out);

	public List<ScheduledMatch> getLatestMatches(int year) {
		int cycle = year - this.started;
		if ((cycle >= 0) && (cycle < this.cycles.size())) {
			List<ScheduledMatch> matches = this.cycles.get(cycle).getMatches();
			return matches;
		} else {
			return null;
		}
	}
	
	public void matchResultEvent(MatchResult match) {
		int numCycles = this.cycles.size();
		if (numCycles > 0) {
			Cycle c = this.cycles.get(numCycles - 1);
			this.notifyCompetitionResult(c);
		}
	}
	
	public void matchScheduledEvent(ScheduledMatch match) {
		return;
	}

	public int getNumberCycles() {
		return this.cycles.size();
	}
	
	private void notifyCompetitionResult(Cycle c) {
		this.listeners.forEach(l -> l.competitionResultEvent(c));
	}
	
}
