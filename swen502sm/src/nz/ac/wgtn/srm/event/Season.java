package nz.ac.wgtn.srm.event;

import nz.ac.wgtn.srm.organisation.*;
import java.util.*;

public class Season {

	private Season previous;
	private Season next;
	
	private int year;
	private Team champion;
	
	private List<Team> teams;
	
	public Season() {
		this.teams = new ArrayList<Team>();
	}

	public Season getPrevious() {
		return previous;
	}

	public void setPrevious(Season previous) {
		this.previous = previous;
	}

	public Season getNext() {
		return next;
	}

	public void setNext(Season next) {
		this.next = next;
	}
	
	

}
