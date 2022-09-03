package nz.ac.wgtn.srm.organisation;

import java.util.List;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.player.*;

public abstract class Team {

	private String name;
	private Country location;
	private int yearFormed;
	protected PlayerList<Attacker> attackers;
	protected PlayerList<Midcourter> midcourters;
	protected PlayerList<Defender> defenders;
	protected PlayerList<Player> currentSquad;
	
	public Team(String name, Country location, int yearFormed) {
		this.name = name;
		this.location = location;
		this.yearFormed = yearFormed;
		this.attackers = new PlayerList<Attacker>();
		this.midcourters = new PlayerList<Midcourter>();
		this.defenders = new PlayerList<Defender>();
		this.currentSquad = new PlayerList<Player>();
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
		
	public boolean addPlayer(Player player) {
		if (player instanceof Defender) {
			this.defenders.add((Defender)player);
		} else if (player instanceof Midcourter) {
			this.midcourters.add((Midcourter)player);
		} else if (player instanceof Attacker) {
			this.attackers.add((Attacker)player);
		}
		return true;
	}

	public void selectSquad() {
		this.currentSquad.clear();
		List<Player> defenders = this.defenders.select(2);
		List<Player> midcourters = this.midcourters.select(3);
		List<Player> attackers = this.attackers.select(2);
		defenders.forEach(d -> this.currentSquad.add(d));
		midcourters.forEach(m -> this.currentSquad.add(m));
		attackers.forEach(a -> this.currentSquad.add(a));
		return;
	}

	public int currentSquadImpact() {
		int impact = 0;
		for (Player p: this.currentSquad) {
			impact += p.matchImpact();
		}
		return impact;
	}

}
