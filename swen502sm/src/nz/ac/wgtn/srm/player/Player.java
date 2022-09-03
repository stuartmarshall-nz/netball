package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.*;

public class Player {

	public static final int minConfidence = 1;
	public static final int maxConfidence = 10;
	public static final int minSkill = 1;
	public static final int maxSkill = 10;
	
	private String name;
	private Country country;
	private int age;
	private int intercepts;
	private int skillLevel;
	private int confidenceLevel;
	private int matches;
	
	public Player(String name, Country country, int age) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.intercepts = 0;
		this.skillLevel = 5;
		this.confidenceLevel = 5;
	}

	public Player(String name, Country country, int age, int intercepts, int skillLevel, int confidenceLevel,
			int matches) {
		super();
		this.name = name;
		this.country = country;
		this.age = age;
		this.intercepts = intercepts;
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

	public int getIntercepts() {
		return intercepts;
	}

	public void updateIntercepts(int intercepts) {
		this.intercepts += intercepts;
	}

	public int getAge() {
		return age;
	}
	
	public void gainConfidence() {
		if (this.confidenceLevel < maxConfidence) {
			this.confidenceLevel++;
		}
	}
	
	public void loseConfidence() {
		if (this.confidenceLevel > minConfidence) {
			this.confidenceLevel--;
		}
	}
	
	private boolean checkForSkillChange() {
		if (((this.matches % 10) == 0) && (this.skillLevel < maxSkill)) {
			this.skillLevel++;
			return true;
		}
		return false;
	}
	
	public int matchImpact() {
		return this.confidenceLevel + this.skillLevel;
	}
	
	public void print() {
		System.out.println("Player\n======\nName: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.println("Skill Level: " + this.skillLevel);
		System.out.println("Confidence Level: " + this.confidenceLevel);
	}

}
