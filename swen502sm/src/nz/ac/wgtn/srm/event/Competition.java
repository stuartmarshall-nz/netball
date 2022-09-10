package nz.ac.wgtn.srm.event;

import java.util.*;
import nz.ac.wgtn.srm.organisation.*;

public abstract class Competition {

	private String name;
	private int started;
	private int frequency;
	private List<Cycle> cycles;
	private List<Team> teams;
	
	public Competition(String name, int started, int frequency) {
		this.name = name;
		this.started = started;
		this.frequency = frequency;
		this.cycles = new ArrayList<Cycle>();
		this.teams = new ArrayList<Team>();
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
	
	public abstract int getNumberCycles();
	
	public abstract void print();
	
}
