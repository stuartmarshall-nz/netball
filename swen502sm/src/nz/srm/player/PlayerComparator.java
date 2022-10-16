package nz.srm.player;

import java.util.*;

import nz.srm.player.*;

public class PlayerComparator implements Comparator<Player> {

	public int compare(Attacker a1, Attacker a2) {
		return a1.matchImpact() - a2.matchImpact();
	}

	public int compare(Defender d1, Defender d2) {
		return d1.matchImpact() - d2.matchImpact();
	}

	public int compare(Midcourter m1, Midcourter m2) {
		return m1.matchImpact() - m2.matchImpact();
	}

	public int compare(Player p1, Player p2) {
		return p1.matchImpact() - p2.matchImpact();
	}
	
}
