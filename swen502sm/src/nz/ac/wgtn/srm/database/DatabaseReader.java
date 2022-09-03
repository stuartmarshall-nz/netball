package nz.ac.wgtn.srm.database;

import java.io.*;
import java.util.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.*;

public class DatabaseReader {

	private Map<String, Player> players;
	private Map<String, Team> teams;
	private File inputFile;
	private Scanner scanner;
	
	public DatabaseReader(String filename) throws FileNotFoundException {
		this.players = new HashMap<String, Player>();
		this.teams = new HashMap<String, Team>();
		this.inputFile = new File(filename);
		this.scanner = new Scanner(this.inputFile);
	}

	public boolean read() {
		if (this.scanner == null) {
			return false;
		}
		this.readPlayers();
		this.readTeams();
		this.readCompetitions();
		return true;
	}
	
	private void readPlayers() {
		int numPlayers = this.scanner.nextInt();
		for (int loop = 0; loop < numPlayers; loop++) {
			Player p = this.readPlayer();
			if (p != null) {
				this.players.put(p.getName(), p);
			}
		}
		return;
	}
	
	private void readTeams() {
		int numTeams = this.scanner.nextInt();
		for (int loop = 0; loop < numTeams; loop++) {
			Team t = this.readTeam();
			if (t != null) {
				this.teams.put(t.getName(), t);
			}
		}
		return;
	}
	
	private void readCompetitions() {
		int numComps = this.scanner.nextInt();
		for (int loop = 0; loop < numComps; loop++) {
			Competition c = this.readCompetition();
		}
		return;
	}
	
	private Player readPlayer() {
		Player p;
		
		String type = this.scanner.next();
		String name = this.scanner.next();
		String country = this.scanner.next();
		int age = this.scanner.nextInt();
		int intercepts = this.scanner.nextInt();
		int skill = this.scanner.nextInt();
		int confidence = this.scanner.nextInt();
		int matches = this.scanner.nextInt();
		
		Country c = Country.fromString(country);
		
		if (type.equals("Defender")) {
			int rebounds = scanner.nextInt();
			p = new Defender(name, c, age, intercepts, skill, confidence, matches, rebounds);
		} else if (type.equals("Midcourter")) {
			int speed = scanner.nextInt();
			p = new Midcourter(name, c, age, intercepts, skill, confidence, matches, speed);
		} else if (type.equals("Attacker")) {
			int goals = scanner.nextInt();
			int shots = scanner.nextInt();
			p = new Attacker(name, c, age, intercepts, skill, confidence, matches, goals, shots);			
		} else {
			return null;
		}
		
		return p;
	}
	
	private Team readTeam() {
		Team t;
		
		String type = scanner.next();
		String teamName = scanner.next();
		String location = scanner.next();
		int year = scanner.nextInt();
		int numPlayers = scanner.nextInt();
		
		Country c = Country.fromString(location);
		
		if (type.equals("International")) {
			t = new InternationalTeam(teamName, c, year);
		} else if (type.equals("Domestic")) {
			t = new DomesticTeam(teamName, c, year);
		} else {
			return null;
		}
		
		for (int loop = 0; loop < numPlayers; loop++) {
			String playerName = this.scanner.next();
			Player p = this.players.get(playerName);
			t.addPlayer(p);
		}
		
		return t;
	}
	
	private Competition readCompetition() {
		return null;
	}
	
}
