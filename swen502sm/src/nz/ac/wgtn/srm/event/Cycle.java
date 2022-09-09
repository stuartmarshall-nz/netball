package nz.ac.wgtn.srm.event;

public abstract class Cycle {

	private Cycle previous;
	private Cycle next;
	
	public Cycle() {
		this.previous = null;
		this.next = null;
	}

}
