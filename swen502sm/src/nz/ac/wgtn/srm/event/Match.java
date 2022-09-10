package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import nz.ac.wgtn.srm.ui.*;

public class Match {

	private LocalDate date;
	private Team home;
	private Team away;
	private Team winning;
	private Team losing;
	private boolean played;
	private boolean homeTeamWin;
	private int homeScore;
	private int awayScore;
	private boolean overtimeResult;
	private List<MatchListener> listeners;
	
	public Match(Team home, Team away, LocalDate date) {
		this.home = home;
		this.away = away;
		this.date = date;
		this.homeScore = 0;
		this.awayScore = 0;
		this.played = false;
		this.overtimeResult = false;
		this.listeners = new ArrayList<MatchListener>();
		this.listeners.add(home);
		this.listeners.add(away);
	}
	
	public void addMatchListener(MatchListener listener) {
		this.listeners.add(listener);
	}
	
	public boolean isHomeTeamWin() {
		return this.homeTeamWin;
	}
	
	public boolean isPlayed() {
		return this.played;
	}
	
	public void simulate() {
		this.home.selectSquad();
		this.away.selectSquad();

		this.home.getCurrentSquad().forEach(p -> this.listeners.add(p));
		this.away.getCurrentSquad().forEach(p -> this.listeners.add(p));

		int homeTeamImpact = this.home.currentSquadImpact();
		int awayTeamImpact = this.away.currentSquadImpact();
		
		this.awayScore = awayTeamImpact + (int)(Math.random() * 25);
		this.homeScore = homeTeamImpact + (int)(Math.random() * 25);
		
		if (this.homeScore == this.awayScore) {
			this.overtimeResult = true;
			if (Math.random() > 0.4) {
				this.homeScore++;
			} else
				this.awayScore++;
		}
		if (this.homeScore > this.awayScore) {
			this.homeTeamWin = true;
			this.winning = this.home;
			this.losing = this.away;
		} else {
			this.homeTeamWin = false;
			this.winning = away;
			this.losing = home;
		}
		this.played = true;
	
		this.notifyMatchListeners();
		
		return;
	}
	
	private void notifyMatchListeners() {
		this.listeners.forEach(l -> l.matchResultEvent(this));
	}
	
	public Team getWinningTeam() {
		return this.winning;
	}
	
	public Team getLosingTeam() {
		return this.losing;
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

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public boolean isOvertimeResult() {
		return overtimeResult;
	}

	public void setOvertimeResult(boolean overtimeResult) {
		this.overtimeResult = overtimeResult;
	}
	
	public void print(PrintStream out) {
		out.print("Month: " + this.date.getMonthValue() + ", ");
		out.print(this.home.getName() + " " + this.homeScore + ":" + this.awayScore + " " + this.away.getName());
		if (this.overtimeResult) {
			out.print("(overtime)");
		}
		out.println();
	}

}
