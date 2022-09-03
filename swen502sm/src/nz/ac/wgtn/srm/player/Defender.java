package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.Country;

public class Defender extends Player {

	private int rebounds;
	private int intercepts;

	
	public Defender(String name, Country country, int age) {
		super(name, country, age);
	}

	public Defender(String name, Country country, int age, Skill skillLevel, Confidence confidenceLevel,
			int matches, int intercepts, int rebounds) {
		super(name, country, age, skillLevel, confidenceLevel, matches);
		this.rebounds = rebounds;
		this.intercepts = intercepts;
	}

	public int getIntercepts() {
		return intercepts;
	}

	public void updateIntercepts(int intercepts) {
		this.intercepts += intercepts;
	}

	
}
