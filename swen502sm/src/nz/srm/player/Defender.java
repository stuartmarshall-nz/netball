package nz.srm.player;

import nz.srm.util.Country;

public class Defender extends Player {

	private int rebounds;
	private int intercepts;

	
	public Defender(String name, String country, int age) {
		super(name, country, age);
	}

	public Defender(String name, String country, int age, Skill skillLevel, Confidence confidenceLevel,
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
