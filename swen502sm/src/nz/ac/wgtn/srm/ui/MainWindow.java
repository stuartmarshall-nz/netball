package nz.ac.wgtn.srm.ui;

import nz.ac.wgtn.srm.database.DatabaseReader;
import nz.ac.wgtn.srm.database.DatabaseWriter;
import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;

import javafx.application.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MainWindow extends Application implements MatchListener, CompetitionListener {

	private NetballSimulator simulator;

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
		
		this.simulator = new NetballSimulator();
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
