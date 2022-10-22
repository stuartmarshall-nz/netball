package nz.srm.event;

import java.util.*;

public class Match {

	private MatchResult result;
	private MatchSchedule schedule;
	private List<MatchListener> listeners;
	
	public Match() {
		this.listeners = new ArrayList<MatchListener>();
	}

	public boolean isScheduled() {
		return schedule != null;
	}
	
	public void setSchedule(MatchSchedule schedule) {
		this.schedule = schedule;
	}
	
	public boolean isFinished() {
		return result != null;
	}
	
	public void setResult(MatchResult result) {
		this.result = result;
	}
	
	public MatchSchedule getMatchSchedule() {
		return this.schedule;
	}
	
	public MatchResult getMatchResult() {
		return this.result;
	}
	
	public void addListeners(List<MatchListener> listeners) {
		this.listeners.addAll(listeners);
		return;
	}
	
	public void addListener(MatchListener listener) {
		this.listeners.add(listener);
		return;
	}
	
}
