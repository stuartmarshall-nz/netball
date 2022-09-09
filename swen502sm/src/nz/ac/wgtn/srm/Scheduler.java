package nz.ac.wgtn.srm;

import java.util.*;

import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.ui.*;

public class Scheduler {

	private Collection<Player> players;
	private Collection<Team> teams;
	private Collection<Competition> competitions;
	
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
			
		d.newSeason(compTeams, 6);
		d.newSeason(compTeams, 6);

		for (int loop = 0; loop < d.getNumberCycles(); loop++) {
			List<Match> matches = d.getMatches(loop + 1);
			matches.forEach(m -> {
				m.addMatchListener(MainWindow.getInstance());
				m.simulate();
			});
		}
		
		d.print();

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
			if (c instanceof Domestic) {
				handleDomestic((Domestic)c);
			} else if (c instanceof International) {
				handleInternational((International)c);
			}
		});
	}

}
