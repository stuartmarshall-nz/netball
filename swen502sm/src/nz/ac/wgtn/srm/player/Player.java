package nz.ac.wgtn.srm.player;

import nz.ac.wgtn.srm.*;
import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import java.util.*;

public class Player implements MatchListener {

	private String name;
	private Country country;
	private int age;
	private Skill skillLevel;
	private Confidence confidenceLevel;
	private Set<Team> teams;
	private int matches;
	
	public Player(String name, Country country, int age) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.matches = 0;
		this.skillLevel = Skill.ROOKIE;
		this.confidenceLevel = Confidence.MEDIUM;
		this.teams = new HashSet<Team>();
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
		this.teams = new HashSet<Team>();
	}
	
	public void notifyMatchResult(Match match) {
		this.incrementMatches();
		Team winningTeam = match.getWinningTeam();
		if (this.teams.contains(winningTeam)) {
			if (Math.random() > 0.9) {
				this.gainConfidence();
			}
		} else {
			if (Math.random() > 0.9) {
				this.loseConfidence();
			}
		}
	}
	
	public void addTeam(Team newTeam) {
		this.teams.add(newTeam);
	}

	private void incrementMatches() {
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
	
	public void printShort() {
		System.out.println(this.name + "(" + this.skillLevel + "/" + this.confidenceLevel + ")");
	}
	
	public void printLong() {
		System.out.println("Name: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.println("Skill Level: " + this.skillLevel);
		System.out.println("Confidence Level: " + this.confidenceLevel);
	}

}
