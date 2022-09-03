package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.*;

public class Player {

	private String name;
	private Country country;
	private int age;
	private int intercepts;
	
	public Player(String name, Country country, int age) {
		this.name = name;
		this.country = country;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}
	
	public void birthday() {
		this.age++;
	}

	public int getIntercepts() {
		return intercepts;
	}

	public void updateIntercepts(int intercepts) {
		this.intercepts += intercepts;
	}

	public int getAge() {
		return age;
	}
	
	public void print() {
		System.out.println("Player\n======\nName: " + this.name);
		System.out.println("Age: " + this.age);
	}

}
