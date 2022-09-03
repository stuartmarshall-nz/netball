package nz.ac.wgtn.srm.organisation;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.player.*;

public abstract class Team {

	private String name;
	private Country location;
	private int yearFormed;
	
	public Team(String name, Country location, int yearFormed) {
		this.name = name;
		this.location = location;
		this.yearFormed = yearFormed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getLocation() {
		return location;
	}

	public void setLocation(Country location) {
		this.location = location;
	}

	public int getYearFormed() {
		return yearFormed;
	}

	public void setYearFormed(int yearFormed) {
		this.yearFormed = yearFormed;
	}
	
	public abstract boolean addPlayer(Player p);

}
