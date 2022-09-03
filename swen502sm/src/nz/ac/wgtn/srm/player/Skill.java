package nz.ac.wgtn.srm.player;

public enum Skill {
	ROOKIE(2), EXPERIENCED(3), INTERNATIONAL(3), SUPERSTAR(4), UNKNOWN(0);
	
	private int value;
	private Skill(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
		
	public static Skill fromString(String str) {
		if (str.equals("rookie")) {
			return ROOKIE;
		} else if (str.equals("experienced")) {
			return EXPERIENCED;
		} else if (str.equals("international")) {
			return INTERNATIONAL;
		} else if (str.equals("superstar")) {
			return SUPERSTAR;
		} else {
			return UNKNOWN;
		}
	}
}
