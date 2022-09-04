package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.*;
import java.time.*;
import java.util.*;

public class Season {

	private Season previous;
	private Season next;
	
	private int year;
	private Team champion;
	private int rounds;
	private int numTeams;
	private int matchesPerRound;
	private boolean byeRound;
	
	private List<Team> teams;
	private List<Match> matches;
	
	public Season(int year, int rounds, List<Team> teams) {
		this.teams = teams;
		this.matches = new ArrayList<Match>();
		this.numTeams = this.teams.size();
		this.matchesPerRound = this.numTeams / 2;
		this.rounds = rounds;
		this.year = year;
		this.byeRound = ((this.numTeams % 2) == 1);
	}

	public Season getPrevious() {
		return previous;
	}

	public void setPrevious(Season previous) {
		this.previous = previous;
	}

	public Season getNext() {
		return next;
	}

	public void setNext(Season next) {
		this.next = next;
	}
	
	public void schedule() {
		LocalDate date = LocalDate.now();
		boolean homeFirst = true;

		int[][] scheduled = new int[this.numTeams][this.numTeams];
		for (int inner = 0; inner < this.numTeams; inner++) {
			for (int outer = 0; outer < this.numTeams; outer++) {
				scheduled[inner][outer] = 0;
			}
		}
		
		System.out.println("scheduling " + rounds + " rounds of " + numTeams + " teams");
		for (int round = 0; round < this.rounds; round++) {
			int loop;
			if (this.byeRound) {
				loop = (round % (numTeams)) + 1;
			} else {
				loop = (round % (numTeams - 1)) + 1;
			}
			int[] toSchedule;
			if (this.byeRound) {
				toSchedule = new int[numTeams + 1];
			} else {
				toSchedule = new int[numTeams];
			}
			toSchedule[0] = 0;
			int home, away;
			for (int i = 1; i < loop; i++) {
				toSchedule[i] = i + toSchedule.length - (loop);
			}
			for (int i = (loop); i < toSchedule.length; i++) {
				toSchedule[i] = i - (loop - 1);
			}
			
			System.out.print("To schedule: ");
			for (int i = 0; i < toSchedule.length; i++) {
				System.out.print(toSchedule[i] + ", ");
			}
			System.out.println();
	
			for (int index = 0; index < (toSchedule.length / 2); index++) {
				home = toSchedule[toSchedule.length - 1 - index];
				away = toSchedule[index];
				
				if ((home < this.numTeams) && (away < this.numTeams)) {
					System.out.println("round " + loop + ": " + home + " vs " + away);
			
					Team t1 = this.teams.get(home);
					Team t2 = this.teams.get(away);
					System.out.println(t1.getName() + " - " + t2.getName());

					Match match;
					if (homeFirst) {
						match = new Match(t1, t2, date);
						scheduled[home][away]++;
					} else {
						match = new Match(t2, t1, date);
						scheduled[away][home]++;
					}
					this.matches.add(match);
				} else {
					System.out.println("Bye match");
				}
				
			}
			homeFirst = !homeFirst;
			date = date.plusWeeks(1);
		}
		
		for (int inner = 0; inner < numTeams; inner++) {
			for (int outer = 0; outer < numTeams; outer++) {
				System.out.println(inner + " vs " + outer + " scheduled " + scheduled[inner][outer] + " times.");
			}
		}

	}
	
	public List<Match> getMatches() {
		return this.matches;
	}
	
	public void print() {
		System.out.println(this.year + " Season\n===========");
		this.matches.forEach(m -> m.print());
		System.out.println("=====\n");
	}

}
