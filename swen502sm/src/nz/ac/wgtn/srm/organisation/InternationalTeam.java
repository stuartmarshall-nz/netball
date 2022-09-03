package nz.ac.wgtn.srm.organisation;

import nz.ac.wgtn.srm.Country;
import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.player.*;

public class InternationalTeam extends Team {
	
	private PlayerList<Attacker> attackers;
	private PlayerList<Midcourter> midcourters;
	private PlayerList<Defender> defenders;
	private PlayerList<Player> currentSquad;
	
	public InternationalTeam(String name, Country location, int yearFormed) {
		super(name, location, yearFormed);
		this.attackers = new PlayerList<Attacker>();
		this.midcourters = new PlayerList<Midcourter>();
		this.defenders = new PlayerList<Defender>();
		this.currentSquad = new PlayerList<Player>();
	}

	public boolean isValidPlayer(Player player) {
		return player.getCountry().equals(this.getLocation());
	}
	
	public boolean addPlayer(Player player) {
		if (this.isValidPlayer(player)) {
			if (player instanceof Defender) {
				this.defenders.add((Defender)player);
			} else if (player instanceof Midcourter) {
				this.midcourters.add((Midcourter)player);
			} else if (player instanceof Attacker) {
				this.attackers.add((Attacker)player);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void selectSquad() {
		
	}
	
	public int currentSquadImpact() {
		return 0;
	}
	
}
