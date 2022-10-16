package nz.srm.event;

public interface MatchListener {
	
	public void matchResultEvent(MatchResult match);
	
	public void matchScheduledEvent(MatchSchedule match);
	
}
