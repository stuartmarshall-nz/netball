package nz.srm.event;

import java.util.Comparator;

public class MatchComparator implements Comparator<Match> {

	public MatchComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public int compare(Match m1, Match m2) {
		return m1.getMatchSchedule().getDate().compareTo(m2.getMatchSchedule().getDate());
	}

}
