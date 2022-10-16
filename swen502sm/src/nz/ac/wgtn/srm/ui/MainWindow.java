package nz.ac.wgtn.srm.ui;

import nz.ac.wgtn.srm.Scheduler;
import nz.ac.wgtn.srm.database.DatabaseReader;
import nz.ac.wgtn.srm.database.DatabaseWriter;
import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.Team;
import nz.ac.wgtn.srm.player.Player;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;

import java.util.Collection;

import javafx.application.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MainWindow extends Application implements MatchListener, CompetitionListener {

	private static MainWindow instance;
	private MatchSimulator simulator;
	private DatabaseReader reader;
	private Scheduler scheduler;
	
	
	private MainWindow() {
		System.out.println("Match Console:");
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	public void start(Stage stage) {
		stage.setTitle("Netball Simulator");
		
		Button quitApp = new Button("Quit Application");
		
		quitApp.setOnAction(event -> {
			System.out.println("Exiting Netball Simulator");
			System.exit(0);
		});
		
		Pane root = new FlowPane(Orientation.VERTICAL);
		root.getChildren().add(quitApp);
		
		Scene scene = new Scene(root, 300, 300);
		stage.setScene(scene);
		stage.show();
		
		this.setup();
		
	}
	
	@Override
	public void matchResultEvent(MatchResult match) {
		System.out.println(match.toString());
	}

	@Override
	public void matchScheduledEvent(ScheduledMatch match) {
		System.out.println(match.toString());
	}

	@Override
	public void competitionResultEvent(Cycle cycle) {
		System.out.println(cycle.toString());
	}
	
	private void setup() {
		String playersFileName = "data/players.csv";
		String teamsFileName = "data/teams.csv";
		String competitionsFileName = "data/competitions.csv";
		
		this.reader = new DatabaseReader(playersFileName, teamsFileName, competitionsFileName);
		System.out.println(reader.read() ? "import successful\n\n" : "import unsuccessful\n\n");
		
		Collection<Player> players = reader.getPlayers();
		Collection<Team> teams = reader.getTeams();
		Collection<Competition> competitions = reader.getCompetitions();
			
		this.scheduler = new Scheduler();
		this.simulator = new MatchSimulator();
		
		this.scheduler.addMatchListener(this);
		this.scheduler.addMatchListener(DatabaseWriter.getInstance());
		this.simulator.addMatchListener(this);
		this.simulator.addMatchListener(DatabaseWriter.getInstance());
		
	}

}
