package nz.ac.wgtn.srm.event;

import java.util.*;
import java.io.*;
import nz.ac.wgtn.srm.organisation.*;

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
	
	public int getYearStarted() {
		return this.started;
	}
		
	public void addListener(CompetitionListener listener) {
		this.listeners.add(listener);
	}
	
	public abstract void print(PrintStream out);

	public List<Match> getMatches(int cycle) {
		cycle--;
		if ((cycle >= 0) && (cycle < this.cycles.size())) {
			List<Match> matches = this.cycles.get(cycle).getMatches();
			return matches;
		} else {
			return null;
		}
	}
	
	public void matchResultEvent(Match match) {
		int numCycles = this.cycles.size();
		if (numCycles > 0) {
			Cycle c = this.cycles.get(numCycles - 1);
			this.notifyCompetitionResult(c);
		}
	}

	public int getNumberCycles() {
		return this.cycles.size();
	}
	
	private void notifyCompetitionResult(Cycle c) {
		this.listeners.forEach(l -> l.competitionResultEvent(c));
	}
	
}
