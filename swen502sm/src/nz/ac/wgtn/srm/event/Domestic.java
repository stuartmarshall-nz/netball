package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.organisation.*;
import java.util.*;

public class Domestic extends Competition {

	private List<Season> seasons;
	private String location;
	
	public Domestic(String name, int start, String location) {
		super(name, start, 1);
		this.location = location;
		this.seasons = new ArrayList<Season>();
	}
	
	public void newSeason(List<Team> teams, int numRounds) {
		int latestYear = this.getYearStarted() + (this.seasons.size() * this.getFrequency());
		Season season = new Season(teams, latestYear, numRounds);
		this.seasons.add(season);
	}
	
	public List<Match> getMatches(int cycle) {
		cycle--;
		if ((cycle >= 0) && (cycle < this.seasons.size())) {
			return this.seasons.get(cycle).getMatches();
		} else {
			return null;
		}
	}
	
	public int getNumberCycles() {
		return this.seasons.size();
	}
	
	public void print() {
		this.seasons.forEach(s -> s.print());
	}

}
