package nz.srm.event;

import java.util.*;

import nz.srm.database.*;
import nz.srm.organisation.*;
import nz.srm.ui.*;

public class MatchCentre {

	private List<MatchListener> listeners;
	private List<Match> matches;
	
	public MatchCentre() {
		this.listeners = new ArrayList<MatchListener>();
		this.matches = new ArrayList<Match>();
	}
	
	public Match getNextMatch() {
		if (this.matches.size() > 0) {
			return this.matches.remove(0);
		} else {
			return null;
		}
	}
	
	public void scheduleCycle(Competition c) {
		List<Team> compTeams = c.getTeams();
		Cycle cycle = c.scheduleNewCycle();
		for (MatchSchedule s: cycle.getMatches()) {
			Match match = this.generateMatch(s);
			this.matches.add(match);
		}
		this.sort();
	}
	
	private void sort() {
		this.matches.sort(new MatchComparator());
	}
	
	public void handleInternational(International i) {
		return;
	}
	
	public void addMatchListener(MatchListener listener) {
		this.listeners.add(listener);
	}
	
	private void notifyMatchListeners(MatchSchedule result) {
		this.listeners.forEach(l -> l.matchScheduledEvent(result));
	}
	
	private Match generateMatch(MatchSchedule schedule) {
		Match match = new Match();
		match.addListeners(this.listeners);
		match.setSchedule(schedule);
		return match;
	}
	

}
