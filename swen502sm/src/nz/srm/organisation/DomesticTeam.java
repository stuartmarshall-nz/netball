package nz.srm.organisation;

import nz.srm.*;
import nz.srm.event.*;

public class DomesticTeam extends Team {

	private Competition playsIn;
	
	public DomesticTeam(String name, String location, int yearFormed) {
		super(name, location, yearFormed);
		this.playsIn = null;
	}

	public DomesticTeam(String name, String location, int yearFormed, Competition competition) {
		super(name, location, yearFormed);
		this.playsIn = competition;
	}

}
