package nz.ac.wgtn.srm;

import java.util.*;

import nz.ac.wgtn.srm.event.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.player.*;
import nz.ac.wgtn.srm.ui.*;
import nz.ac.wgtn.srm.database.*;
import javafx.collections.*;

public class Scheduler {

	private Collection<Player> players;
	private Collection<Team> teams;
	private Collection<Competition> competitions;
	private List<MatchListener> listeners;
	private ObservableList<ScheduledMatch> matches; 
	private final int numYears = 3;
	
	public Scheduler(Collection<Player> players, Collection<Team> teams, Collection<Competition> competitions) {
		this.players = players;
		this.teams = teams;
		this.competitions = competitions;
		this.matches = FXCollections.observableArrayList();
		this.listeners = new ArrayList<MatchListener>();
	}
	
	public void handleDomestic(Domestic d) {
		d.addListener(DatabaseWriter.getInstance());
		d.addListener(MainWindow.getInstance());
		
		List<Team> compTeams = d.getTeams();
		
		for (Team t: compTeams) {
			t.selectSquad();
		}
			
		for (int loop = 0; loop < this.numYears; loop++) {
			d.newSeason(compTeams, 6);
			List<ScheduledMatch> matches = d.getMatches(loop + 1);
		}
	}
	
	public void handleInternational(International i) {
		i.addListener(DatabaseWriter.getInstance());
		i.addListener(MainWindow.getInstance());

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
	
	public void addMatchListener(MatchListener listener) {
		this.listeners.add(listener);
	}
	
	private void notifyMatchListeners(ScheduledMatch result) {
		this.listeners.forEach(l -> l.matchScheduledEvent(result));
	}
	

}
