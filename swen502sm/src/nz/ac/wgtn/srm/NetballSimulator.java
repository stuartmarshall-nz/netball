package nz.ac.wgtn.srm;

import java.util.*;

import nz.ac.wgtn.srm.database.DatabaseReader;
import nz.ac.wgtn.srm.database.DatabaseWriter;
import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.Team;
import nz.ac.wgtn.srm.player.Player;
import nz.ac.wgtn.srm.ui.MainWindow;

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
		ScheduledMatch match = this.scheduler.getNextMatch();
		if (match != null) {
			MatchResult result = this.simulator.simulate(match);
			match.getLadder().addResult(result);
		}
	}

}
