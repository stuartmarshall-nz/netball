package nz.ac.wgtn.srm;

import java.util.*;
import nz.ac.wgtn.srm.database.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.event.*;
//import nz.ac.wgtn.srm.ui.MainWindow;
//import javafx.application.Application;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (int index = 0; index < args.length; index++) {
			System.out.println("arg[" + index + "] : " + args[index]);
		}

		String playersFileName = "data/players.csv";
		String teamsFileName = "data/teams.csv";
		String competitionsFileName = "data/competitions.csv";
		
		/*
		if (args.length > 0) {
			filename = args[0];
		} else {
			filename = "netball_db.csv";
		}
		*/
		
//		Application.launch(MainWindow.class, args);

		DatabaseReader reader = new DatabaseReader(playersFileName, teamsFileName, competitionsFileName);
		System.out.println(reader.read() ? "import successful\n\n" : "import unsuccessful\n\n");
		
		Collection<Player> players = reader.getPlayers();
		Collection<Team> teams = reader.getTeams();
		Collection<Competition> competitions = reader.getCompetitions();
			
		Scheduler scheduler = new Scheduler(players, teams, competitions);
		scheduler.schedule();

		for (Team t: teams) {
			t.printSummary();
		}
	}

}
