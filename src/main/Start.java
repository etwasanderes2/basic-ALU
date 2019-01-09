package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Start {
	/** saves the calculation length of a register */
	public static final int BITS = 8;
	/** saves the amount of registers */
	public static final int MEMSIZE = 16;

	// TODO: move

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		boolean[][] register = new boolean[MEMSIZE][BITS];
		boolean[] flags = new boolean[3];

		ArrayList<Statement> program = Parser
				.parseFile("/home/lars/Programming/basic_instructions/Version_001/Test_001.txt");

		for (int i = 0; i < program.size(); i++) {
			program.get(i).execute(register, flags);
		}

		System.out.println("Done!");

		for (int i = 0; i < register.length; i++) {
			System.out.println(i + ": "
					+ BinaryConversion.toString(register[i]) + " : "
					+ BinaryConversion.toInt(register[i]));
		}
	}
}
