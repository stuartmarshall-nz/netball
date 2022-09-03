package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.Country;

public class Defender extends Player {

	private int rebounds;
	
	public Defender(String name, Country country, int age) {
		super(name, country, age);
	}

	public Defender(String name, Country country, int age, int intercepts, int skillLevel, int confidenceLevel,
			int matches, int rebounds) {
		super(name, country, age, intercepts, skillLevel, confidenceLevel, matches);
		this.rebounds = rebounds;
	}

	
	
}
