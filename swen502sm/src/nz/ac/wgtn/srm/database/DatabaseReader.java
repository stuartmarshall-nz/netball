package nz.ac.wgtn.srm.database;

import java.io.*;

import java.util.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.*;
import java.time.LocalDate;

public class DatabaseReader {

	private Map<String, Player> players;
	private Map<String, Team> teams;
	private List<Competition> competitions;
	private File inputFile;
	private Scanner scanner;
	
	public DatabaseReader(String filename) throws FileNotFoundException {
		this.players = new HashMap<String, Player>();
		this.teams = new HashMap<String, Team>();
		this.competitions = new ArrayList<Competition>();
		this.inputFile = new File(filename);
		this.scanner = new Scanner(this.inputFile);
		this.scanner.useDelimiter(",|\n");
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
	
	public Collection<Player> getPlayers() {
		return this.players.values();
	}
	
	public Collection<Team> getTeams() {
		return this.teams.values();
	}
	
	public List<Competition> getCompetitions() {
		return this.competitions;
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
			this.competitions.add(c);
		}
		return;
	}
	
	private Player readPlayer() {
		Player p;
		
		String type = this.scanner.next();
		String name = this.scanner.next();
		String country = this.scanner.next();
		int age = this.scanner.nextInt();
		int matches = this.scanner.nextInt();
		String skillStr = this.scanner.next();
		String confidenceStr = this.scanner.next();
		
		Skill skill = Skill.valueOf(skillStr);
		Confidence confidence = Confidence.valueOf(confidenceStr);
		Country c = Country.fromString(country);
		
		if (type.equals("Defender")) {
			int intercepts = this.scanner.nextInt();
			int rebounds = this.scanner.nextInt();
			p = new Defender(name, c, age, skill, confidence, matches, intercepts, rebounds);
		} else if (type.equals("Midcourter")) {
			int intercepts = this.scanner.nextInt();
			int speed = this.scanner.nextInt();
			p = new Midcourter(name, c, age, skill, confidence, matches, intercepts, speed);
		} else if (type.equals("Attacker")) {
			int goals = this.scanner.nextInt();
			int shots = this.scanner.nextInt();
			p = new Attacker(name, c, age, skill, confidence, matches, goals, shots);			
		} else {
			this.scanner.nextLine();
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
			this.scanner.nextLine();
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
		Competition c;
		
		String type = this.scanner.next();
		String competitionName = this.scanner.next();
		int cycle = this.scanner.nextInt();
		int numTeams = this.scanner.nextInt();
		
		if (type.equals("Domestic")) {
			String country = this.scanner.next();
			Country loc = Country.fromString(country);
			c = new Domestic(competitionName, cycle, loc);
		} else if (type.equals("International")) {
			c = new International(competitionName, LocalDate.now().getYear(), cycle);
		} else {
			this.scanner.nextLine();
			return null;
		}
		
		for (int loop = 0; loop < numTeams; loop++) {
			String teamName = this.scanner.next();
			Team t = this.teams.get(teamName);
			c.addTeam(t);
		}
		
		return c;
	}
	
}
