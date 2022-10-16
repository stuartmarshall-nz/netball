package nz.srm.event;

import java.io.*;

public class International extends Competition {

	public International(String name, int start, int cycle) {
		super(name, start, cycle);
	}
	
	public Cycle scheduleNewCycle() {
		return null;
	}
	
	// TODO implement getNumberCycles for International Competitions
	public int getNumberCycles() {
		return 0;
	}
	
	public void print(PrintStream out) {
		return;
	}

}
