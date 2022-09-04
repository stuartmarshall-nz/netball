package nz.ac.wgtn.srm.event;

import java.util.*;
import java.time.*;
import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.organisation.*;

public class Round {

	private int roundNum;
	private List<Match> matches;
	private LocalDate date;
	
	public Round(int num, LocalDate date) {
		this.roundNum = num;
		this.date = date;
		this.matches = new ArrayList<Match>();
	}
	
	public List<Match> getMatches() {
		return this.matches;
	}
	
	public void schedule(List<Team> teams, int[] toSchedule, boolean homeFirst) {
		int numTeams = teams.size();
		int home, away;
		for (int index = 0; index < (toSchedule.length / 2); index++) {
			home = toSchedule[toSchedule.length - 1 - index];
			away = toSchedule[index];
			
			if ((home < numTeams) && (away < numTeams)) {
				System.out.println("round " + this.roundNum + ": " + home + " vs " + away);
		
				Team t1 = teams.get(home);
				Team t2 = teams.get(away);
				System.out.println(t1.getName() + " - " + t2.getName());

				Match match;
				if (homeFirst) {
					match = new Match(t1, t2, date);
				} else {
					match = new Match(t2, t1, date);
				}
				this.matches.add(match);
			} else {
				System.out.println("Bye match");
			}
			
		}

		return;
	}
	
	public void simulate() {
		return;
	}

}
