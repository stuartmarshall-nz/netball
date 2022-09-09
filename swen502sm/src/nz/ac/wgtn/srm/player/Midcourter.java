package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.Country;

public class Midcourter extends Player {

	private int speed;
	private int intercepts;
	
	public Midcourter(String name, String country, int age, int speed) {
		super(name, country, age);
		this.speed = speed;
	}

	public Midcourter(String name, String country, int age, Skill skillLevel, Confidence confidenceLevel,
			int matches, int intercepts, int speed) {
		super(name, country, age, skillLevel, confidenceLevel, matches);
		this.speed = speed;
		this.intercepts = intercepts;
	}

	public Midcourter(String name, String country, int age) {
		super(name, country, age);
	}
	
	public int getIntercepts() {
		return intercepts;
	}

	public void updateIntercepts(int intercepts) {
		this.intercepts += intercepts;
	}


	
	
}
