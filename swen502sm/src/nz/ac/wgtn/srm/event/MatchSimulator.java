package nz.ac.wgtn.srm.event;

import java.util.*;
import nz.ac.wgtn.srm.organisation.*;

public class MatchSimulator {
	
	private List<MatchListener> listeners;

	public MatchSimulator() {
		this.listeners = new ArrayList<MatchListener>();
	}

	public MatchResult simulate(ScheduledMatch match) {		
		Team home = match.getHome();
		Team away = match.getAway();
		
		this.listeners.add(home);
		this.listeners.add(away);
		
		home.selectSquad();
		away.selectSquad();

		home.getCurrentSquad().forEach(p -> this.listeners.add(p));
		away.getCurrentSquad().forEach(p -> this.listeners.add(p));

		int homeTeamImpact = home.currentSquadImpact();
		int awayTeamImpact = away.currentSquadImpact();
		
		int awayScore = awayTeamImpact + (int)(Math.random() * 25);
		int homeScore = homeTeamImpact + (int)(Math.random() * 25);
		boolean overtimeResult = false;
		
		if (homeScore == awayScore) {
			overtimeResult = true;
			if (Math.random() > 0.4) {
				homeScore++;
			} else
				awayScore++;
		}
		
		MatchResult result = new MatchResult(home, away, homeScore, awayScore, overtimeResult);
	
		match.getLadder().matchResultEvent(result);
		
		this.notifyMatchListeners(result);
				
		return result;
	}
	
	public void addMatchListener(MatchListener listener) {
		this.listeners.add(listener);
	}
	
	private void notifyMatchListeners(MatchResult result) {
		this.listeners.forEach(l -> l.matchResultEvent(result));
	}
	
	
}
