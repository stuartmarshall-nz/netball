package nz.ac.wgtn.srm;

public enum Country {
	NEW_ZEALAND, AUSTRALIA, ENGLAND, JAMAICA, SOUTH_AFRICA, UGANDA, WALES, SCOTLAND, BARBADOS, MALAWI, NORTHERN_IRELAND, TRINIDAD_AND_TOBAGO, OTHER;

	public static Country fromString(String str) {
		if (str.equals("New Zealand")) {
			return NEW_ZEALAND;
		} else if (str.equals("Australia")) {
			return AUSTRALIA;
		} else if (str.equals("ENGLAND")) {
			return ENGLAND;
		} else {
			return OTHER;
		}
	}
}
