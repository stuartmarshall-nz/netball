package nz.ac.wgtn.srm;

import nz.ac.wgtn.srm.database.*;
import nz.ac.wgtn.srm.organisation.*;
import nz.ac.wgtn.srm.event.*;
import java.io.*;
import java.util.*;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (int index = 0; index < args.length; index++) {
			System.out.println("arg[" + index + "] : " + args[index]);
		}

		String filename;
		
		if (args.length > 0) {
			filename = args[0];
		} else {
			filename = "netball_db.csv";
		}
		
		try {
			DatabaseReader reader = new DatabaseReader(filename);
			System.out.println(reader.read() ? "import successful\n\n" : "import unsuccessful\n\n");
			Collection<Team> teams = reader.getTeams();
			List<Competition> competitions = reader.getCompetitions();
			
			Competition c = competitions.get(0);
			if (c instanceof Domestic) {
				Domestic d = (Domestic)c;
				List<Team> compTeams = c.getTeams();
	
				for (Team t: compTeams) {
					t.selectSquad();
				}
				
				d.newSeason(compTeams, 6);

				List<Match> matches = d.getMatches(1);
				matches.forEach(m -> m.simulate());
				c.print();
			}

			for (Team t: teams) {
				t.print();
			}

		} catch (FileNotFoundException exp) {
			exp.printStackTrace();
		}
	}

}
