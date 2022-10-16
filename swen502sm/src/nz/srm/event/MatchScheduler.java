package nz.srm.event;

import java.util.*;

import nz.srm.database.*;
import nz.srm.organisation.*;
import nz.srm.ui.*;

public class MatchScheduler {

	private List<MatchListener> listeners;
	private List<MatchSchedule> matches;
	
	public MatchScheduler() {
		this.listeners = new ArrayList<MatchListener>();
		this.matches = new ArrayList<MatchSchedule>();
	}
	
	public MatchSchedule getNextMatch() {
		return this.matches.remove(0);
	}
	
	public void scheduleCycle(Competition c) {
		List<Team> compTeams = c.getTeams();
		Cycle cycle = c.scheduleNewCycle();
		this.matches.addAll(cycle.getMatches());
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
	

}
