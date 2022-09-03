package nz.ac.wgtn.srm;

import nz.ac.wgtn.srm.database.*;
import java.io.*;

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
			System.out.println(reader.read() ? "import successful" : "import unsuccessful");
		} catch (FileNotFoundException exp) {
			exp.printStackTrace();
		}
	}

}
