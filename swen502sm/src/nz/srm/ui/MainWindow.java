package nz.srm.ui;

import javafx.stage.*;
import nz.srm.*;
import nz.srm.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.collections.*;

public class MainWindow extends Application implements MatchListener, CompetitionListener {

	private NetballSimulator simulator;
	private ObservableList<String> scheduledList;
	private ObservableList<String> resultsList;
	private ObservableList<String> playersList;

	public void start(Stage stage) {
		stage.setTitle("Netball Simulator");
				
		Pane root = new VBox();
		
		final HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0, 10, 0, 10));
        hbox.getChildren().addAll(
        		this.setupControls(),
        		this.setupPlayerList(),
        		this.setupMatchList());
        
		root.getChildren().addAll(this.setupMenu(), hbox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		this.simulator = new NetballSimulator();
	}
	
	public Pane setupPlayerList() {	
		Pane playerPane = new VBox();
		
		Label playersLabel = new Label("Players");
		
		ListView<String> players = new ListView<String>();
		players.setItems(this.playersList);
		
		playerPane.getChildren().addAll(playersLabel, players);
		
		return playerPane;
	}
	
	public Pane setupControls() {
		Pane controls = new VBox();
		
		Label controlsLabel = new Label("Controls");
		
		Button addPlayer = new Button("Add Player");
		Button schedule = new Button("Schedule");
		Button simulate = new Button("Simulate");
		
		addPlayer.setOnAction(event -> {
			new AddPlayerWindow();
		});
		
		controls.getChildren().addAll(controlsLabel, addPlayer, schedule, simulate);
		
		return controls;
	}
	
	public Pane setupMatchList() {
		
		Label scheduledLabel = new Label("Matches Scheduled");
		Label resultsLabel = new Label("Match Results");
		
		this.resultsList = FXCollections.observableArrayList();
		this.scheduledList = FXCollections.observableArrayList();
		
		ListView<String> scheduled = new ListView<String>();
		ListView<String> results = new ListView<String>();
		
		scheduled.setItems(this.scheduledList);
		results.setItems(this.resultsList);
		
		Pane matchPane = new VBox();
		
		matchPane.getChildren().addAll(scheduledLabel, scheduled, resultsLabel, results);
		
		return matchPane;
	}
	
	public MenuBar setupMenu() {
		MenuBar bar = new MenuBar();
		Menu file = new Menu("System");
		
		MenuItem loadPlayer = new MenuItem("Load Players");
		MenuItem loadTeams = new MenuItem("Load Teams");
		MenuItem loadComps = new MenuItem("Load Competitions");
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(event -> {
			System.out.println("Exiting Netball Simulator");
			System.exit(0);			
		});
		
		
		bar.getMenus().addAll(file);
		file.getItems().addAll(loadPlayer, loadTeams, loadComps, quit);
		return bar;

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
	

}
