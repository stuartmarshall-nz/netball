package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.organisation.*;
import java.util.*;
import java.io.*;

public class Domestic extends Competition {

	private String location;
	
	public Domestic(String name, int start, String location) {
		super(name, start, 1);
		this.location = location;
	}
	
	public void newSeason(List<Team> teams, int numRounds) {
		int latestYear = this.getYearStarted() + (this.cycles.size() * this.getFrequency());
		Season season = new Season(teams, latestYear, numRounds);
		this.cycles.add(season);
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void print(PrintStream out) {
		this.cycles.forEach(c -> c.print(out));
	}

}
