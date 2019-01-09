package main;
import main.BinaryOperators;

/**
 * stores opcodes.
 * @author lars
 *
 */
public enum Opcode {

	SET(0, 1, "SET"),
	DEC(1, 1, "DEC"),
	ADD(2, 2, "ADD"),
	ADC(3, 2, "ADC", true),
	SET1(4, 1, "SET1"),
	INC(5, 1, "INC"),
	SUB(6, 2, "SUB"),
	SBC(7, 2, "SBC", true),
	SETF(8, 0, "SETF"),
	SLL(9, 2, "SLL"),
	SLR(10, 2, "SLR"),
	SETT(11, 0, "SETT"),
	NAND(12, 2, "NAND"),
	AND(13, 2, "AND"),
	NOT(14, 1, "NOT"),
	OR(15, 2, "OR");

	private int params;
	private int id;
	private String name;
	private boolean carry;

	/**
	 * initializes.
	 * @param id the number of the opcode
	 * @param params the number of parameters of the opcode
	 * @param name the name we humans give the opcode
	 * @param carry true if the opcode uses the carry in
	 */
	private Opcode(int id, int params, String name, boolean carry) {
		this.params = params;
		this.id = id;
		this.carry = carry;
		this.name = name;
	}

	/**
	 * initializes, no carry.
	 * @param id the number of the opcode
	 * @param params the number of parameters of the opcode
	 * @param name the name we humans give the opcode
	 */
	private Opcode(int id, int params, String name) {
		this(id, params, name, false);
	}

	public int getParams() {
		return params;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean usesCarry() {
		return carry;
	}

	public Result execute() {
		return execute(new boolean[1], new boolean[1]);
		//TODO: make proper
	}

	public Result execute(boolean[]op0) {
		return execute(op0, new boolean[op0.length]);
	}

	public Result execute(boolean[] op0, boolean[] op1) {
		return execute(op0, op1, false);
	}

	public Result execute(final boolean[] op0, final boolean[] op1, final boolean carry) {
		assert op0.length == op1.length : "Different operand lengths!";
		switch (id) {
		case 0:
			return new Result(op0.clone());
		case 1:
			return BinaryOperators.dec(op0);
		case 2:
			return BinaryOperators.add(op0, op1);
		case 3:
			return BinaryOperators.add(op0, op1, carry);
		case 4:
			return new Result(op0.clone());
		case 5:
			return BinaryOperators.inc(op0);
		case 6:
			return BinaryOperators.sub(op0, op1);
		case 7:
			return BinaryOperators.sub(op0, op1, carry);
		case 8:
			boolean[] allFalse = new boolean[Start.BITS];
			//TODO: do properly. with a max bits or sth
			return new Result(allFalse);
		case 9:
			return BinaryOperators.shiftLogicalLeft(op0);
		case 10:
			return BinaryOperators.shiftLogicalRigth(op0);
		case 11:
			boolean[] allTrue = new boolean[Start.BITS];
			//TODO: change, linke with SETF
			for (int i = 0; i < allTrue.length; i++) {
				allTrue[i] = true;
			}
			return new Result(allTrue);
		case 12:
			return BinaryOperators.nand(op0, op1);
		case 13:
			return BinaryOperators.and(op0, op1);
		case 14:
			return BinaryOperators.not(op0);
		case 15:
			return BinaryOperators.or(op0, op1);
		}
		return null;
	}
}
