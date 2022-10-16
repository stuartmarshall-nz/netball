package nz.srm.event;

import nz.srm.organisation.Team;

public class MatchResult {

	private Team home;
	private Team away;
	private int homeScore;
	private int awayScore;
	private boolean overtimeResult;
	
	public MatchResult(Team home, Team away, int homeScore, int awayScore, boolean overtimeResult) {
		super();
		this.home = home;
		this.away = away;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.overtimeResult = overtimeResult;
	}
	
	public Team getHome() {
		return home;
	}

	public Team getAway() {
		return away;
	}
	
	public int getHomeScore() {
		return homeScore;
	}
	
	public int getAwayScore() {
		return awayScore;
	}



	public boolean isHomeTeamWin() {
		return this.homeScore > this.awayScore;
	}
	
	public String toString() {
		return home.getName() + " " + homeScore + ":" + awayScore + (overtimeResult ? " (OT) " : " ") + away.getName();
	}
	

}
