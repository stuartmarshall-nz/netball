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
	private MatchCentre scheduler;
		
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
			
		this.scheduler = new MatchCentre();
		this.simulator = new MatchSimulator();
		
	}
	
	public void scheduleCompetition(String competitionName) {
		Competition c = competitions.get(competitionName);
		if (c != null) {
			this.scheduler.scheduleCycle(c);
		}
	}
	
	public void addNewPlayer(Player newPlayer) {
		this.players.put(newPlayer.getName(), newPlayer);
		return;
	}

	public void addNewTeam(Team newTeam) {
		this.teams.put(newTeam.getName(), newTeam);
		return;
	}
	
	public void simulateNextMatch() {
		Match match = this.scheduler.getNextMatch();
		if (match != null) {
			MatchSchedule schedule = match.getMatchSchedule();
			MatchResult result = this.simulator.simulate(match.getMatchSchedule());
			schedule.getLadder().addResult(result);
			schedule.getHome().recordResult(result);
			schedule.getAway().recordResult(result);
		}
	}

}
