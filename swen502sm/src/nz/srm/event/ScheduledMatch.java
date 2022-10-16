package nz.srm.event;

import java.io.*;
import java.time.LocalDate;

import nz.srm.organisation.*;

public class ScheduledMatch {

	private LocalDate date;
	private Team home;
	private Team away;
	private Ladder ladder;
	
	public ScheduledMatch(Team home, Team away, Ladder ladder, LocalDate date) {
		this.home = home;
		this.away = away;
		this.date = date;
		this.ladder = ladder;
	}
	
	public Team getHome() {
		return this.home;
	}

	public Team getAway() {
		return this.away;
	}
	
	public Ladder getLadder() {
		return this.ladder;
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
