package nz.ac.wgtn.srm;

import nz.ac.wgtn.srm.organisation.*;

public class Match {

	private Team home;
	private Team away;
	private int homeScore;
	private int awayScore;
	private boolean overtimeResult;
	
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
		this.homeScore = 0;
		this.awayScore = 0;
		this.overtimeResult = false;
	}
	
	public void simulate() {
		int homeTeamImpact = this.home.currentSquadImpact();
		int awayTeamImpact = this.away.currentSquadImpact();
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
	
	

}
