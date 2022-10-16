package nz.srm.event;

import java.util.Comparator;

public class MatchComparator implements Comparator<ScheduledMatch> {

	public MatchComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public int compare(ScheduledMatch m1, ScheduledMatch m2) {
		return m1.getDate().compareTo(m2.getDate());
	}

}
