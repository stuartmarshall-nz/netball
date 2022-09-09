package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.*;
import java.time.*;
import java.util.*;

public class Season extends Cycle {
	
	private int numRounds;
	private int numTeams;
	
	private List<Team> teams;
	private List<Round> rounds;
	
	public Season(List<Team> teams, int year, int numRounds) {
		super(teams, year);
		this.teams = teams;
		this.rounds = new ArrayList<Round>();
		this.numTeams = this.teams.size();
		this.numRounds = numRounds;
		this.schedule();
	}

	
	private int[] createOrder(int offset, boolean containsBye) {
		int[] toSchedule;
		if (containsBye) {
			toSchedule = new int[this.numTeams + 1];
			offset = offset % (toSchedule.length - 1);
		} else {
			toSchedule = new int[this.numTeams];
			offset = offset % (toSchedule.length - 1);
		}
		toSchedule[0] = 0;
			
		for (int i = 1; i <= offset; i++) {
			toSchedule[i] = i + (toSchedule.length - 1) - offset;
		}
		for (int i = (offset + 1); i < toSchedule.length; i++) {
			toSchedule[i] = i - offset;
		}
		return toSchedule;
	}
	
	public void schedule() {
		LocalDate date = LocalDate.now();
		boolean homeFirst = true;
		boolean containsBye = ((this.numTeams % 2) != 0);

		for (int round = 0; round < this.numRounds; round++) {
			int[] toSchedule = this.createOrder(round, containsBye);
			
			System.out.print("To schedule: ");
			for (int i = 0; i < toSchedule.length; i++) {
				System.out.print(toSchedule[i] + ", ");
			}
			System.out.println();

			Round nextRound = new Round(round + 1, date);
			nextRound.schedule(teams, toSchedule, homeFirst);
			this.rounds.add(nextRound);
			
			homeFirst = !homeFirst;
			date = date.plusWeeks(1);
		}
		
	}
	
	public List<Match> getMatches() {
		List<Match> matches = new ArrayList<Match>();
		for (Round r: this.rounds) {
			matches.addAll(r.getMatches());
		}
		return matches;
	}
	
	public void print() {
		System.out.println(getYear() + " Season\n===========");
		this.getMatches().forEach(m -> m.print());
		System.out.println("=====\n");
	}

}
