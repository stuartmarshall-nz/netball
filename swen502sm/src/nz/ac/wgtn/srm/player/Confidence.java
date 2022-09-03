package nz.ac.wgtn.srm.player;

public enum Confidence {
	LOW(2), MEDIUM(4), HIGH(5), GOLDEN(7), UNKNOWN(0);
	
	private int value;
	private Confidence(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static Confidence fromString(String str) {
		if (str.equals("low")) {
			return LOW;
		} else if (str.equals("medium")) {
			return MEDIUM;
		} else if (str.equals("high")) {
			return HIGH;
		} else if (str.equals("golden")) {
			return GOLDEN;
		} else {
			return UNKNOWN;
		}
	}
}
