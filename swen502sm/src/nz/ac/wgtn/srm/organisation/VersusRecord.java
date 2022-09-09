package nz.ac.wgtn.srm.organisation;

public class VersusRecord {

	private String team;
	private String opposition;
	private int played;
	private int won;
	private int lost;
	
	public VersusRecord(String team, String opposition) {
		this.team = team;
		this.opposition = opposition;
		this.played = 0;
		this.won = 0;
		this.lost = 0;
	}

	public String getTeam() {
		return team;
	}

	public String getOpposition() {
		return opposition;
	}

	public int getPlayed() {
		return played;
	}

	public int getWon() {
		return won;
	}

	public int getLost() {
		return lost;
	}
	
	public void incrementWins() {
		this.played++;
		this.won++;
	}
	
	public void incrementLosses() {
		this.played++;
		this.lost++;
	}
	
	public void print() {
		System.out.println(this.team + " vs " + this.opposition + ": " + this.played + ", " + this.won + ", " + this.lost);
	}
	
}
