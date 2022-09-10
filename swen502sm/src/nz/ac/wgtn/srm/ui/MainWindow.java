package nz.ac.wgtn.srm.ui;

import nz.ac.wgtn.srm.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MainWindow extends Application implements MatchListener, CompetitionListener {

	private static MainWindow instance;
	
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
		
	}
	
	public void matchResultEvent(Match match) {
		match.print(System.out);		
	}

	@Override
	public void competitionResultEvent(Cycle cycle) {
		cycle.print(System.out);
	}

}
