package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import java.util.*;
import java.io.*;

public class Domestic extends Competition {

	private String location;
	private int numRounds;
	private List<Team> teams;
	
	public Domestic(String name, String location, int start, int numRounds) {
		super(name, start, 1);
		this.location = location;
		this.numRounds = numRounds;
		this.teams = new ArrayList<Team>();
	}
	
	public Cycle scheduleNewCycle() {
		int latestYear = this.getYearStarted() + (this.getFrequency() * this.getNumberCycles());
		Season season = new Season(this.teams, latestYear, this.numRounds);
		season.schedule();
		this.cycles.add(season);
		return season;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void print(PrintStream out) {
		this.cycles.forEach(c -> c.print(out));
	}

}
