package nz.ac.wgtn.srm.database;

import nz.ac.wgtn.srm.event.*;
import java.io.*;
import java.time.*;


public class DatabaseWriter implements MatchListener, CompetitionListener {

	private static DatabaseWriter instance;
	private PrintStream matchesFile;
	private PrintStream seasonsFile;
	
	private DatabaseWriter() {
		File matches = new File ("data/matches_" + LocalTime.now());
		File seasons = new File ("data/seasons_" + LocalTime.now());
		try {
			matches.createNewFile();
			seasons.createNewFile();
			this.matchesFile = new PrintStream(matches);
			this.seasonsFile = new PrintStream(seasons);
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
	public void matchResultEvent(MatchResult match) {
		this.matchesFile.print(match.toString());
	}

	@Override
	public void matchScheduledEvent(ScheduledMatch match) {
		this.matchesFile.print(match.toString());
	}

	@Override
	public void competitionResultEvent(Cycle cycle) {
		cycle.print(seasonsFile);
	}
	
	

}
