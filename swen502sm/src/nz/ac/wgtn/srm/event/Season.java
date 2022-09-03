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
	
	public void testSchedule() {
		int rounds = 10;
		int numTeams = 6;
		boolean bye = false;
		int[][] scheduled = new int[numTeams][numTeams];
		for (int inner = 0; inner < numTeams; inner++) {
			for (int outer = 0; outer < numTeams; outer++) {
				scheduled[inner][outer] = 0;
			}
		}
		
		System.out.println("scheduling " + rounds + " rounds of " + numTeams + " teams");
		for (int round = 1; round <= rounds; round++) {
			int loop = (round % numTeams) + 1;
			int[] toSchedule = new int[numTeams];
			toSchedule[0] = 1;
			int home, away;
			for (int i = 1; i < loop; i++) {
				toSchedule[i] = i + toSchedule.length - (loop - 1);
			}
			for (int i = loop; i < toSchedule.length; i++) {
				toSchedule[i] = i - (loop - 2);
			}
			
			System.out.print("To schedule: ");
			for (int i = 0; i < toSchedule.length; i++) {
				System.out.print(toSchedule[i] + ", ");
			}
			System.out.println();
			
			home = toSchedule[toSchedule.length - 1];
			if (!bye) {
				away = toSchedule[0];
				System.out.println("round " + loop + ": " + home + " vs " + away);
				scheduled[home - 1][away - 1]++;
				scheduled[away - 1][home - 1]++;
			} else {
				System.out.println("round " + loop + ": team " + home + " has a bye.");
			}

			for (int index = 1; index < (toSchedule.length / 2); index++) {
				int offset = bye ? 0 : 1;
				home = toSchedule[toSchedule.length - 1 - index];
				away = toSchedule[index - 1 + offset];
				System.out.println("round " + loop + ": " + home + " vs " + away);
				scheduled[home - 1][away - 1]++;
				scheduled[away - 1][home - 1]++;
			}
		
		}
		
		for (int inner = 0; inner < numTeams; inner++) {
			for (int outer = 0; outer < numTeams; outer++) {
				System.out.println(inner + " vs " + outer + " scheduled " + scheduled[inner][outer] + " times.");
			}
		}

	}
	
	public void schedule() {
		LocalDate date = LocalDate.now();
		int offset = 1;
		boolean homeFirst = true;
		boolean advance = true;
		for (int round = 0; round < this.rounds; round++) {
			System.out.println("scheduling " + this.matchesPerRound + " matches for round " + round);
			for (int matchup = 0; matchup < this.matchesPerRound; matchup++) {
				int t1index = (matchup) % this.numTeams;
				int t2index = ((matchup + 1) + offset) % this.numTeams;
				Team t1 = this.teams.get(t1index);
				Team t2 = this.teams.get(t2index);
				Match match;
				if (homeFirst) {
					match = new Match(t1, t2, date);
					System.out.println("scheduling: " + t1index + " vs " + t2index);
				} else {
					match = new Match(t2, t1, date);
					System.out.println("scheduling: " + t2index + " vs " + t1index);
				}
				this.matches.add(match);
			}
			date = date.plusWeeks(1);
			if (!byeRound || advance) {
				offset++;
			}
			if (byeRound) {
				advance = !advance;
			}
			
			if (offset == this.numTeams) {
				offset = 1;
				homeFirst = !homeFirst;
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
