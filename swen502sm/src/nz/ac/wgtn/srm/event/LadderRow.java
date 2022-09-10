package nz.ac.wgtn.srm.event;

import java.io.*;

public class LadderRow implements Comparable<LadderRow> {

	private String team;
	private int played;
	private int wins;
	private int losses;
	
	public LadderRow(String team) {
		this.team = team;
		this.played = 0;
		this.wins = 0;
		this.losses = 0;
	}

	public int compareTo(LadderRow other) {
		return this.wins - other.wins;
	}
	
	public void addResult(boolean win) {
		if (win) {
			this.wins++;
		} else {
			this.losses++;
		}
		this.played++;
	}
	
	public void print(PrintStream out) {
		out.println(this.team + ": " + this.played + "; " + this.wins + "; " + this.losses);
	}
	
}
