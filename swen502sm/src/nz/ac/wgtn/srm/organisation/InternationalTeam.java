package nz.ac.wgtn.srm.organisation;

import nz.ac.wgtn.srm.Country;
import nz.ac.wgtn.srm.player.*;

public class InternationalTeam extends Team {
	
	public InternationalTeam(String name, Country location, int yearFormed) {
		super(name, location, yearFormed);
	}

	public boolean isValidPlayer(Player player) {
		return player.getCountry().equals(this.getLocation());
	}
	
	public boolean addPlayer(Player player) {
		if (this.isValidPlayer(player)) {
			return super.addPlayer(player);
		} else {
			return false;
		}
	}
	
}
