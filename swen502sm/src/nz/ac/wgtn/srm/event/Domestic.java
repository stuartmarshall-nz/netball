package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.*;

public class Domestic extends Competition {

	private Season latestSeason;
	private Country location;
	
	public Domestic(String name, Country location, int cycle) {
		super(name, cycle);
		this.location = location;
	}
	
	public void updateSeason(Season newSeason) {
		if (this.latestSeason != null) {
			this.latestSeason.setNext(newSeason);
			newSeason.setPrevious(this.latestSeason);
		}
		this.latestSeason = newSeason;
		return;
	}

}
