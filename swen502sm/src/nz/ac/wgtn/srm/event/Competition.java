package nz.ac.wgtn.srm.event;

import java.util.*;
import nz.ac.wgtn.srm.organisation.*;

public class Competition {

	private String name;
	private int frequency;
	private Cycle first;
	private List<Team> teams;
	
	public Competition(String name, int frequency) {
		this.name = name;
		this.frequency = frequency;
		this.teams = new ArrayList<Team>();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
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
	
}
