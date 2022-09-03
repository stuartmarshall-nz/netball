package nz.ac.wgtn.srm;

import nz.ac.wgtn.srm.organisation.*;
import java.time.LocalDate;
import java.math.*;

public class Match {

	private LocalDate date;
	private Team home;
	private Team away;
	private int homeScore;
	private int awayScore;
	private boolean overtimeResult;
	
	public Match(Team home, Team away, LocalDate date) {
		this.home = home;
		this.away = away;
		this.date = date;
		this.homeScore = 0;
		this.awayScore = 0;
		this.overtimeResult = false;
	}
	
	public void simulate() {
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
			this.home.recordWin();
			this.away.recordLoss();
		} else {
			this.home.recordLoss();
			this.away.recordWin();
		}
		return;
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
	
	public void print() {
		System.out.print("Month: " + this.date.getMonthValue() + ", ");
		System.out.print(this.home.getName() + " " + this.homeScore + ":" + this.awayScore + " " + this.away.getName());
		if (this.overtimeResult) {
			System.out.print("(overtime)");
		}
		System.out.println();
	}

}
