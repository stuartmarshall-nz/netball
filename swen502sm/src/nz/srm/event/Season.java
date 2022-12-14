package nz.srm.event;

import java.time.*;
import java.util.*;

import nz.srm.organisation.*;

import java.io.*;

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

			Round nextRound = new Round(round + 1, date);
			nextRound.schedule(this.teams, this.getLadder(), toSchedule, homeFirst);
			this.rounds.add(nextRound);
			
			homeFirst = !homeFirst;
			date = date.plusWeeks(1);
		}

		for (Round r: this.rounds) {
			super.addMatches(r.getMatches());
		}
		
	}
	
}
