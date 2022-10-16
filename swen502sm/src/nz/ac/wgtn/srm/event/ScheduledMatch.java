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

	public Team getAway() {
		return away;
	}

	public LocalDate getDate() {
		return this.date;
	}
	
	public void print(PrintStream out) {
		out.print("Month: " + this.date.getMonthValue() + ", ");
		out.print(this.home.getName() + " vs " + this.away.getName());
		out.println();
	}

}
