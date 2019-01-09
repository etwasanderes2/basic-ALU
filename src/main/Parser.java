package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Parser {
	public static Statement parseLine(String line) {
		line = line.trim();

		String[] parts = line.split("\\s+");

		if (parts.length == 0) {
			throw new IllegalArgumentException("Could not parse: '" + line
					+ "' ! (Disection empty)");
		}

		Opcode command = null;

		for (Opcode each : Opcode.values()) {
			if (each.getName().equals(parts[0])) {
				command = each;
			}
		}

		if (command == null) {
			throw new IllegalArgumentException("Could not parse: '" + line
					+ "' ! (Unknown opcode: '" + parts[0] + "')");
		}

		switch (parts.length) {
		case 4:
			return new Statement(command, Integer.valueOf(parts[1]),
					Integer.valueOf(parts[2]), Integer.valueOf(parts[3]));
		case 3:
			return new Statement(command, Integer.valueOf(parts[1]),
					Integer.valueOf(parts[2]));
		case 2:
			return new Statement(command, Integer.valueOf(parts[1]));
		case 1:
			throw new IllegalArgumentException("Could not parse: '" + line
					+ "' ! (No parameters)");
		}

		return null;
	}

	public static ArrayList<Statement> parseText(String text) {
		String[] lines = text.split("\\n");
		ArrayList<Statement> statementList = new ArrayList<Statement>();
		for (String each : lines) {
			each = each.trim();
			if (!commented(each)) {
				statementList.add(parseLine(each));
			}
		}
		return statementList;
	}

	/**
	 *
	 * @param filename
	 * @return null if error?
	 */
	public static ArrayList<Statement> parseFile(String filename) {
		ArrayList<Statement> statementList = new ArrayList<Statement>();

		//stolen from Internet
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {

				System.out.println(line);

				line = line.trim();
				if (!commented(line)) {
					statementList.add(parseLine(line));
				}
			}
		}
		catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
		    e.printStackTrace();
		    return null;
		}

		return statementList;
	}

	private static boolean commented(String x) {
		return x.startsWith("#") || x.startsWith("//") || x.equals("");
	}

}
