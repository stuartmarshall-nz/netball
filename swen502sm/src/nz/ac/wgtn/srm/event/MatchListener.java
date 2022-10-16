package nz.ac.wgtn.srm.event;

public interface MatchListener {
	
	public void matchResultEvent(MatchResult match);
	
	public void matchScheduledEvent(ScheduledMatch match);
	
}
