package nz.srm;

import java.util.*;

import nz.srm.database.DatabaseReader;
import nz.srm.database.DatabaseWriter;
import nz.srm.event.*;
import nz.srm.organisation.Team;
import nz.srm.player.Player;
import nz.srm.ui.MainWindow;

public class NetballSimulator {

	private MatchSimulator simulator;
	private DatabaseReader reader;
	private MatchScheduler scheduler;
		
	private Map<String, Player> players;
	private Map<String, Team> teams;
	private Map<String, Competition> competitions;
	
	public NetballSimulator() {
		String playersFileName = "data/players.csv";
		String teamsFileName = "data/teams.csv";
		String competitionsFileName = "data/competitions.csv";
		
		this.reader = new DatabaseReader(playersFileName, teamsFileName, competitionsFileName);
//		System.out.println(reader.read() ? "import successful\n\n" : "import unsuccessful\n\n");
		
		this.players = reader.getPlayers();
		this.teams = reader.getTeams();
		this.competitions = reader.getCompetitions();
			
		this.scheduler = new MatchScheduler();
		this.simulator = new MatchSimulator();
		
	}
	
	public void scheduleCompetition(String competitionName) {
		Competition c = competitions.get(competitionName);
		if (c != null) {
			this.scheduler.scheduleCycle(c);
		}
	}
	
	public void addNewPlayer() {
		return;
	}

	public void addNewTeam() {
		return;
	}
	
	public void simulateNextMatch() {
		MatchSchedule match = this.scheduler.getNextMatch();
		if (match != null) {
			MatchResult result = this.simulator.simulate(match);
			match.getLadder().addResult(result);
			match.getHome().recordResult(result);
			match.getAway().recordResult(result);
		}
	}

}
