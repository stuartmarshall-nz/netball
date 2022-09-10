package nz.ac.wgtn.srm;

import java.util.*;

import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.ui.*;
import nz.ac.wgtn.srm.database.*;

public class Scheduler {

	private Collection<Player> players;
	private Collection<Team> teams;
	private Collection<Competition> competitions;
	private final int numYears = 3;
	
	public Scheduler(Collection<Player> players, Collection<Team> teams, Collection<Competition> competitions) {
		this.players = players;
		this.teams = teams;
		this.competitions = competitions;
	}
	
	private void handleDomestic(Domestic d) {
		List<Team> compTeams = d.getTeams();
		
		for (Team t: compTeams) {
			t.selectSquad();
		}
			
		for (int loop = 0; loop < this.numYears; loop++) {
			d.newSeason(compTeams, 6);
			List<Match> matches = d.getMatches(loop + 1);
			matches.forEach(m -> {
				m.addMatchListener(MainWindow.getInstance());
				m.addMatchListener(DatabaseWriter.getInstance());
			});
			matches.get(matches.size() - 1).addMatchListener(d);
			matches.forEach(m -> {
				m.simulate();
			});
		}


	}
	
	public void handleInternational(International i) {
		this.players.forEach(p -> {
			this.teams.forEach(t -> {
				boolean added = false;
				if (!added && (t instanceof InternationalTeam)) {
					if (((InternationalTeam) t).isValidPlayer(p)) {
						t.addPlayer(p);
						added = true;
					}
				}
			});
		});
	}
	
	public void schedule() {
		this.competitions.forEach(c -> {
			c.addListener(DatabaseWriter.getInstance());
			c.addListener(MainWindow.getInstance());
			if (c instanceof Domestic) {
				handleDomestic((Domestic)c);
			} else if (c instanceof International) {
				handleInternational((International)c);
			}
		});
	}

}
