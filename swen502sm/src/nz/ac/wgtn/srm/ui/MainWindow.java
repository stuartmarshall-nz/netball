package nz.ac.wgtn.srm.ui;

import nz.ac.wgtn.srm.event.*;

public class MainWindow implements MatchListener {

	private static MainWindow instance;
	
	private MainWindow() {
		System.out.println("Match Result Console");
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	public void notifyMatchResult(Match match) {
		match.print();
	}

}
