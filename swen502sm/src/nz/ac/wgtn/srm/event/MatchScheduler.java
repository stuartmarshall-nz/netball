package nz.ac.wgtn.srm.event;

import java.util.*;

import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.ui.*;
import nz.ac.wgtn.srm.database.*;

public class MatchScheduler {

	private List<MatchListener> listeners;
	private List<ScheduledMatch> matches;
	
	public MatchScheduler() {
		this.listeners = new ArrayList<MatchListener>();
		this.matches = new ArrayList<ScheduledMatch>();
	}
	
	public ScheduledMatch getNextMatch() {
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
		i.addListener(DatabaseWriter.getInstance());
		i.addListener(MainWindow.getInstance());
	}
	
	public void addMatchListener(MatchListener listener) {
		this.listeners.add(listener);
	}
	
	private void notifyMatchListeners(ScheduledMatch result) {
		this.listeners.forEach(l -> l.matchScheduledEvent(result));
	}
	

}
