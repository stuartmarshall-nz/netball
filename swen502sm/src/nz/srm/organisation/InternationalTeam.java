package nz.srm.organisation;

import nz.srm.player.*;
import nz.srm.util.Country;

public class InternationalTeam extends Team {
	
	private String nickname;
	
	public InternationalTeam(String name, String nickname, int yearFormed) {
		super(name, name, yearFormed);
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
