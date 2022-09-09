package nz.ac.wgtn.srm.event;

import java.util.*;
import nz.ac.wgtn.srm.organisation.*;

public class Ladder implements MatchListener {

	private Map<String, LadderRow> rows;
	
	public Ladder(List<Team> teams) {
		this.rows = new HashMap<String, LadderRow>();
		teams.forEach(t -> this.rows.put(t.getName(), new LadderRow(t.getName())));
	}

	@Override
	public void notifyMatchResult(Match match) {
		String homeTeam = match.getHome().getName();
		String awayTeam = match.getAway().getName();
		
		LadderRow homeRow = this.rows.get(homeTeam);
		LadderRow awayRow = this.rows.get(awayTeam);
		
		boolean homeWin = match.isHomeTeamWin();
		homeRow.addResult(homeWin);
		awayRow.addResult(!homeWin);
	}

	public void print() {
		List<LadderRow> sortedRows = new ArrayList<LadderRow>();
		this.rows.values().forEach(r -> sortedRows.add(r));
		Collections.sort(sortedRows);
		Collections.reverse(sortedRows);
		sortedRows.forEach(r -> r.print());
	}
	
}