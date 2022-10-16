package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import java.io.*;
import java.time.LocalDate;

public class ScheduledMatch {

	private LocalDate date;
	private Team home;
	private Team away;
	
	public ScheduledMatch(Team home, Team away, LocalDate date) {
		this.home = home;
		this.away = away;
		this.date = date;
	}
	
	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	
	public void print(PrintStream out) {
		out.print("Month: " + this.date.getMonthValue() + ", ");
		out.print(this.home.getName() + " vs " + this.away.getName());
		out.println();
	}

}
