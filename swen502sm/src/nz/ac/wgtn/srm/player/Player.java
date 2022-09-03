package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.*;

public class Player {

	private String name;
	private Country country;
	private int age;
	private Skill skillLevel;
	private Confidence confidenceLevel;
	private int matches;
	
	public Player(String name, Country country, int age) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.matches = 0;
		this.skillLevel = Skill.ROOKIE;
		this.confidenceLevel = Confidence.MEDIUM;
	}

	public Player(String name, Country country, int age, Skill skillLevel, Confidence confidenceLevel,
			int matches) {
		super();
		this.name = name;
		this.country = country;
		this.age = age;
		this.skillLevel = skillLevel;
		this.confidenceLevel = confidenceLevel;
		this.matches = matches;
	}

	public void incrementMatches() {
		this.matches++;
		this.checkForSkillChange();
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

	public int getAge() {
		return age;
	}
	
	public void gainConfidence() {
		switch (this.confidenceLevel) {
		case LOW: {this.confidenceLevel = Confidence.MEDIUM; break;}
		case MEDIUM: {this.confidenceLevel = Confidence.HIGH; break;}
		case HIGH: {this.confidenceLevel = Confidence.GOLDEN; break;}
		default: {}
		}
	}
	
	public void loseConfidence() {
		switch (this.confidenceLevel) {
		case MEDIUM: {this.confidenceLevel = Confidence.LOW; break;}
		case HIGH: {this.confidenceLevel = Confidence.MEDIUM; break;}
		case GOLDEN: {this.confidenceLevel = Confidence.HIGH; break;}
		default: {}
		}
	}
	
	private boolean checkForSkillChange() {
		if ((this.matches == 20) && (this.skillLevel == Skill.ROOKIE)) {
			this.skillLevel = Skill.EXPERIENCED;
			return true;
		}
		return false;
	}
	
	public int matchImpact() {
		return this.confidenceLevel.getValue() + this.skillLevel.getValue();
	}
	
	public void print() {
		System.out.println("Player\n======\nName: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.println("Skill Level: " + this.skillLevel);
		System.out.println("Confidence Level: " + this.confidenceLevel);
	}

}
