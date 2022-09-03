package nz.ac.wgtn.srm.event;

import java.util.*;
import nz.ac.wgtn.srm.organisation.*;

public class Competition {

	private String name;
	private int cycle;
	private List<Team> teams;
	
	public Competition(String name, int cycle) {
		this.name = name;
		this.cycle = cycle;
		this.teams = new ArrayList<Team>();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
	}

	public String getName() {
		return this.name;
	}

	public int getCycle() {
		return this.cycle;
	}

	public List<Team> getTeams() {
		return this.teams;
	}
	
}
