package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.Country;

public class Attacker extends Player {
	
	private int shots;
	private int goals;
	
	public Attacker(String name, Country country, int age) {
		super(name, country, age);
		this.goals = 0;
		this.shots = 0;
	}

	public Attacker(String name, Country country, int age, int intercepts, int skillLevel, int confidenceLevel,
			int matches, int goals, int shots) {
		super(name, country, age, intercepts, skillLevel, confidenceLevel, matches);
		this.goals = goals;
		this.shots = shots;
	}
	
	public float shotAccuracy() {
		return this.goals / this.shots;
	}
	
	public void addShots(int shots) {
		this.shots += shots;
	}
	
	public void addGoals(int goals) {
		this.goals += goals;
	}
	
	public void print() {
		super.print();
		System.out.println("Goals: " + goals + " / " + this.shots + " (" + this.shotAccuracy() + ")");
	}

}
