package nz.ac.wgtn.srm;

import java.util.*;
import nz.ac.wgtn.srm.player.*;

public class PlayerList<E extends Player> extends ArrayList<E> {
	private static final long serialVersionUID = 1;
	
	public void summarise() {
		super.forEach(p -> p.printLong());
		return;
	}
	
	public List<Player> select(int num) {
		List<Player> bestPlayers = new ArrayList<Player>();
		this.sort(new PlayerComparator());
		for (int loop = 0; loop < num; loop++) {
			bestPlayers.add(this.get(loop));
		}
		return bestPlayers;
	}
	
}
