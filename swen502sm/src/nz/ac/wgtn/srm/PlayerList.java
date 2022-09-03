package nz.ac.wgtn.srm;

import java.util.ArrayList;
import nz.ac.wgtn.srm.player.*;

public class PlayerList<E extends Player> extends ArrayList<E> {
	private static final long serialVersionUID = 1;
	
	public void summarise() {
		super.forEach(p -> p.print());
		return;
	}
	
}
