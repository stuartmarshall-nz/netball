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
	private Map<String, Competition> competitions;
	private File playersFile;
	private File teamsFile;
	private File competitionsFile;
	private Scanner scanner;
	
	public DatabaseReader(String players, String teams, String competitions) {
		this.players = new HashMap<String, Player>();
		this.teams = new HashMap<String, Team>();
		this.competitions = new HashMap<String, Competition>();
		
		this.playersFile = new File(players);
		this.teamsFile = new File(teams);
		this.competitionsFile = new File(competitions);
	}

	public boolean read() {
		try {
			this.readPlayers();
			this.readTeams();
			this.readCompetitions();
			return true;
		} catch (FileNotFoundException exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	public Map<String, Player> getPlayers() {
		return this.players;
	}
	
	public Map<String, Team> getTeams() {
		return this.teams;
	}
	
	public Map<String, Competition> getCompetitions() {
		return this.competitions;
	}
	
	private void readPlayers() throws FileNotFoundException {
		this.scanner = new Scanner(this.playersFile);
		this.scanner.useDelimiter(",|" + System.getProperty("line.separator"));
		while (scanner.hasNext()) {
			Player p = this.readPlayer();
			if (p != null) {
				this.players.put(p.getName(), p);
			}
		}
		this.scanner.close();
		return;
	}
	
	private void readTeams() throws FileNotFoundException {
		this.scanner = new Scanner(this.teamsFile);
		this.scanner.useDelimiter(",|" + System.getProperty("line.separator"));
		while (this.scanner.hasNext()) {
			Team t = this.readTeam();
			if (t != null) {
				this.teams.put(t.getName(), t);
			}
		}
		this.scanner.close();
		return;
	}
	
	private void readCompetitions() throws FileNotFoundException {
		this.scanner = new Scanner(this.competitionsFile);
		this.scanner.useDelimiter(",|" + System.getProperty("line.separator"));
		while (this.scanner.hasNext()) {
			Competition c = this.readCompetition();
			this.competitions.put(c.getName(), c);
		}
		this.scanner.close();
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
		if (type.equals("Defender")) {
			int intercepts = this.scanner.nextInt();
			int rebounds = this.scanner.nextInt();
			p = new Defender(name, country, age, skill, confidence, matches, intercepts, rebounds);
		} else if (type.equals("Midcourter")) {
			int intercepts = this.scanner.nextInt();
			System.out.println(name + " " + intercepts);
			int speed = this.scanner.nextInt();
			p = new Midcourter(name, country, age, skill, confidence, matches, intercepts, speed);
		} else if (type.equals("Attacker")) {
			int goals = this.scanner.nextInt();
			int shots = this.scanner.nextInt();
			p = new Attacker(name, country, age, skill, confidence, matches, goals, shots);			
		} else {
			this.scanner.nextLine();
			return null;
		}
		
		return p;
	}
	
	private Team readTeam() {
		String type = scanner.next();
		String teamName = scanner.next();
		int startYear = scanner.nextInt();
		
		if (type.equals("International")) {
			return this.readInternationalTeam(teamName, startYear);
		} else if (type.equals("Domestic")) {
			return this.readDomesticTeam(teamName, startYear);
		} else {
			this.scanner.nextLine();
			return null;
		}
	}
	
	private Team readInternationalTeam(String teamName, int startYear) {
		String nickname = scanner.next();
		InternationalTeam t = new InternationalTeam(teamName, nickname, startYear);
		for (Player p: this.players.values()) {
			if (t.isValidPlayer(p)) {
				t.addPlayer(p);
			}
		}
		return t;
	}
	
	private Team readDomesticTeam(String teamName, int startYear) {
		String location = scanner.next();
		int numPlayers = scanner.nextInt();
		DomesticTeam t = new DomesticTeam(teamName, location, startYear);
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
			c = new Domestic(competitionName, country, cycle, 6);
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
