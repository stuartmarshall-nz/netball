package nz.srm.event;

import java.util.*;

import nz.srm.*;
import nz.srm.organisation.*;

import java.time.*;

public class Round {

	private int roundNum;
	private List<ScheduledMatch> matches;
	private LocalDate date;
	
	public Round(int num, LocalDate date) {
		this.roundNum = num;
		this.date = date;
		this.matches = new ArrayList<ScheduledMatch>();
	}
	
	public List<ScheduledMatch> getMatches() {
		return this.matches;
	}
	
	public void schedule(List<Team> teams, Ladder ladder, int[] toSchedule, boolean homeFirst) {
		int numTeams = teams.size();
		int home, away;
		for (int index = 0; index < (toSchedule.length / 2); index++) {
			home = toSchedule[toSchedule.length - 1 - index];
			away = toSchedule[index];
			
			if ((home < numTeams) && (away < numTeams)) {		
				Team t1 = teams.get(home);
				Team t2 = teams.get(away);

				ScheduledMatch match;
				if (homeFirst) {
					match = new ScheduledMatch(t1, t2, ladder, date);
				} else {
					match = new ScheduledMatch(t2, t1, ladder, date);
				}
				this.matches.add(match);
			} 
		}

		return;
	}
	
}
