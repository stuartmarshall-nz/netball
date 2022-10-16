package nz.srm.event;

public class Match {

	private MatchResult result;
	private MatchSchedule schedule;
	
	public Match() {
	}

	private boolean isScheduled() {
		return schedule != null;
	}
	
	private boolean isFinished() {
		return result != null;
	}
	
	private MatchSchedule getMatchSchedule() {
		return this.schedule;
	}
	
	private MatchResult getMatchResult() {
		return this.result;
	}
	
}
