package main;

public abstract class BinaryOperators {

	/**
	 *
	 * @param bit0
	 * @param bit1
	 * @param carry
	 * @return 0: result. 1: carry
	 */
	static public boolean[] fullAdder(boolean bit0, boolean bit1, boolean carry) {
		boolean[] result = new boolean[2];
		result[0] = bit0 ^ bit1 ^ carry;
		result[1] = (bit0 && bit1) || (bit1 && carry) || (carry && bit0);
		return result;
	}

	static public Result add(boolean[] op0, boolean[] op1) {
		return add(op0, op1, false);
	}

	static public Result add(boolean[] op0, boolean[] op1, boolean carry) {
		assert op0.length == op1.length : "Different operand lengths!";
		boolean[] resultData = new boolean[op0.length];
		for (int i = 0; i < op0.length; i++) {
			boolean[] addR = fullAdder(op0[i], op1[i], carry);
			resultData[i] = addR[0];
			carry = addR[1];
		}
		Result result = new Result(resultData);
		result.setCarry(carry);

		boolean safetyBit = fullAdder(op0[op0.length - 1], op1[op1.length - 1], carry)[0];

		result.setUnderflow(safetyBit && !resultData[resultData.length - 1]);
		result.setOverflow(!safetyBit && resultData[resultData.length - 1]);

		return result;
	}

	static public Result sub(boolean[] op0, boolean[] op1) {
		return sub(op0, op1, true);
	}

	static public Result sub(boolean[] op0, boolean[] op1, boolean carry) {
		boolean[] complement = not(op1).getValue();
		return add(op0, complement, carry);
	}

	public static Result dec(boolean[] op0) {
		boolean[] one = new boolean[op0.length];
		one[0] = true;
		return sub(op0, one);
	}

	public static Result inc(boolean[] op0) {
		boolean[] one = new boolean[op0.length];
		one[0] = true;
		return add(op0, one);
	}

	public static Result shiftLogicalLeft(boolean[] op0) {
		boolean[] shifted = new boolean[op0.length];
		for (int i = 0; i < op0.length - 1; i++) {
			assert i + 1 < shifted.length : "Array index out of bounds!";
			shifted[i + 1] = op0[i];
		}
		return new Result(shifted);
	}

	public static Result shiftLogicalRigth(boolean[] op0) {
		boolean[] shifted = new boolean[op0.length];
		for (int i = 1; i < op0.length; i++) {
			assert i - 1 >= 0 : "Array index out of bounds!";
			shifted[i - 1] = op0[i];
		}
		return new Result(shifted);
	}

	public static Result nand(boolean[] op0, boolean[] op1) {
		return not(and(op0, op1));
	}

	public static Result and(boolean[] op0, boolean[] op1) {
		assert op0.length == op1.length : "Different array lengths!";
		boolean[] result = new boolean[op0.length];
		for (int i = 0; i < op0.length; i++) {
			result[i] = (op0[i] && op1[i]);
		}
		return new Result(result);
	}

	private static Result not(Result res0) {
		return not(res0.getValue());
	}

	static public Result not(boolean[] op0) {
		boolean[] result = new boolean[op0.length];
		for (int i = 0; i < op0.length; i++) {
			result[i] = !op0[i];
		}
		return new Result(result);
	}

	public static Result or(boolean[] op0, boolean[] op1) {
		assert op0.length == op1.length : "Different array lengths!";
		boolean[] result = new boolean[op0.length];
		for (int i = 0; i < op0.length; i++) {
			result[i] = (op0[i] || op1[i]);
		}
		return new Result(result);
	}
}
