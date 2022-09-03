package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.Country;

public class Midcourter extends Player {

	private int speed;
	
	public Midcourter(String name, Country country, int age, int speed) {
		super(name, country, age);
		this.speed = speed;
	}

}
