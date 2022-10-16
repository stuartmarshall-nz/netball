package nz.srm.organisation;

import java.util.*;

import nz.srm.*;
import nz.srm.event.*;
import nz.srm.player.*;
import nz.srm.util.PlayerList;

public abstract class Team implements MatchListener {

	private String name;
	private String location;
	private int yearFormed;
	private int wins;
	private int losses;
	protected PlayerList<Attacker> attackers;
	protected PlayerList<Midcourter> midcourters;
	protected PlayerList<Defender> defenders;
	protected PlayerList<Player> currentSquad;
	private Map<String, VersusRecord> record;
	
	public Team(String name, String location, int yearFormed) {
		this.name = name;
		this.location = location;
		this.yearFormed = yearFormed;
		this.wins = 0;
		this.losses = 0;
		this.attackers = new PlayerList<Attacker>();
		this.midcourters = new PlayerList<Midcourter>();
		this.defenders = new PlayerList<Defender>();
		this.currentSquad = new PlayerList<Player>();
		this.record = new HashMap<String, VersusRecord>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private void recordResult(String opposition, boolean win) {
		VersusRecord versus = this.record.get(opposition);
		if (versus == null) {
			versus = new VersusRecord(this.name, opposition);
			this.record.put(opposition, versus);
		}
		if (win) {
			versus.incrementWins();
			this.wins++;
		} else {
			versus.incrementLosses();
			this.losses++;
		}
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
		player.addTeam(this);
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
	
	public List<Player> getCurrentSquad() {
		return this.currentSquad;
	}

	public void print() {
		System.out.println("Team\n====\nName: " + this.name);
		System.out.println("Wins: " + this.wins);
		System.out.println("Losses: " + this.losses);
		System.out.println("Attackers: " + this.attackers.size());
		System.out.println("Midcourters: " + this.midcourters.size());
		System.out.println("Defenders: " + this.defenders.size());
		if (this.currentSquad != null) {
			System.out.println("Current Squad: ");
			this.currentSquad.forEach(p -> p.printLong());
			System.out.println("\nCurrent Squad Strength: " + this.currentSquadImpact() + "\n");
		}
		System.out.println("Head to Head");
		this.record.values().forEach(r -> r.print());
	}
	
	public void printSummary() {
		System.out.println("Team: " + this.name);
		System.out.println("Wins: " + this.wins);
		System.out.println("Losses: " + this.losses);
		System.out.println("Attackers: " + this.attackers.size());
		System.out.println("Midcourters: " + this.midcourters.size());
		System.out.println("Defenders: " + this.defenders.size());
		System.out.println("Head to Head");
		this.record.values().forEach(r -> r.print());
	}
	
	public void matchResultEvent(MatchResult match) {
		Team homeTeam = match.getHome();
		Team awayTeam = match.getAway();
		boolean homeWin = match.isHomeTeamWin();
		Team winningTeam = (homeWin) ? homeTeam: awayTeam;
		Team losingTeam = (homeWin) ? awayTeam: homeTeam;
		if (this.equals(winningTeam)) {
			this.recordResult(losingTeam.name, true);
		} else {
			this.recordResult(winningTeam.name, false);
		}
	}
	
	public void matchScheduledEvent(ScheduledMatch match) {
		
	}
	
	public boolean equals(Team t) {
		return this.name.equals(t.getName());
	}
	
}
