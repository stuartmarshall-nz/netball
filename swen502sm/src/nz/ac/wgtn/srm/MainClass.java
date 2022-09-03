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
			
			for (Team t: teams) {
				t.selectSquad();
			}
			
			Competition c = competitions.get(0);
			if (c instanceof Domestic) {
				Domestic d = (Domestic)c;
				List<Team> compTeams = c.getTeams();
				compTeams.addAll(c.getTeams());
				Season s = new Season(2022, 11, compTeams);
				s.testSchedule();
/*
				s.schedule();
				List<Match> matches = s.getMatches();
				matches.forEach(m -> m.simulate());
				s.print();
*/
			}
/*		
			for (Team t: teams) {
				t.print();
			}
*/			
		} catch (FileNotFoundException exp) {
			exp.printStackTrace();
		}
	}

}
