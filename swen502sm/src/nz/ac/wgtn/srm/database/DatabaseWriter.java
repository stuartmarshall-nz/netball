package nz.ac.wgtn.srm.database;

import nz.ac.wgtn.srm.event.*;
import java.io.*;
import java.time.*;


public class DatabaseWriter implements MatchListener {

	private static DatabaseWriter instance;
	private PrintStream matchesFile;
	private PrintStream seasonsFile;
	
	private DatabaseWriter() {
		File matchesFile = new File ("data/matches_" + LocalTime.now());
		try {
			matchesFile.createNewFile();
			this.matchesFile = new PrintStream(matchesFile);
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}
	
	public static DatabaseWriter getInstance() {
		if (instance == null) {
			instance = new DatabaseWriter();
		}
		return instance;
	}

	@Override
	public void notifyMatchResult(Match match) {
		match.print(this.matchesFile);
	}
	
	

}
