package nz.ac.wgtn.srm.organisation;

import java.util.List;
import java.math.*;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.player.*;

public abstract class Team {

	private String name;
	private Country location;
	private int yearFormed;
	private int wins;
	private int losses;
	protected PlayerList<Attacker> attackers;
	protected PlayerList<Midcourter> midcourters;
	protected PlayerList<Defender> defenders;
	protected PlayerList<Player> currentSquad;
	
	public Team(String name, Country location, int yearFormed) {
		this.name = name;
		this.location = location;
		this.yearFormed = yearFormed;
		this.wins = 0;
		this.losses = 0;
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

	public void recordWin() {
		this.wins++;
		this.currentSquad.forEach(p -> {
			p.incrementMatches();
			if (Math.random() > 0.9) {
				p.gainConfidence();
			}
		});
	}

	public void recordLoss() {
		this.losses++;
		this.currentSquad.forEach(p -> {
			p.incrementMatches();
			if (Math.random() > 0.9) {
				p.loseConfidence();
			}
		});

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

	public void print() {
		System.out.println("Team\n====\nName: " + this.name);
		System.out.println("Wins: " + this.wins);
		System.out.println("Losses: " + this.losses);
		System.out.println("Attackers: " + this.attackers.size());
		System.out.println("Midcourters: " + this.midcourters.size());
		System.out.println("Defenders: " + this.defenders.size());
		if (this.currentSquad != null) {
			System.out.print("Current Squad: ");
			this.currentSquad.forEach(p -> p.print());
			System.out.println("\nCurrent Squad Strength: " + this.currentSquadImpact() + "\n");
		}
	}
	
}
