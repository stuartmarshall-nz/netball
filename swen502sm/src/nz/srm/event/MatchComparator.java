package nz.srm.event;

import java.util.Comparator;

public class MatchComparator implements Comparator<MatchSchedule> {

	public MatchComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public int compare(MatchSchedule m1, MatchSchedule m2) {
		return m1.getDate().compareTo(m2.getDate());
	}

}
