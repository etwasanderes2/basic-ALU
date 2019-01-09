package main;

public class Result {
	private boolean[] value;

	/**
	 * 0: carry. 1: overflow. 2: underflow.
	 */
	private boolean[] flags = new boolean[3];

	public Result(boolean[] value) {
		super();
		this.value = value;
	}

	public Result(boolean[] value, boolean[] flags) {
		super();
		this.value = value;
		this.flags = flags;
	}

	public Result() {
		super();
	}

	public boolean[] getValue() {
		return value;
	}

	public void setValue(boolean[] value) {
		this.value = value;
	}

	public boolean[] getFlags() {
		return flags;
	}

	public void setFlags(boolean[] flags) {
		this.flags = flags;
	}

	public void setCarry(boolean carry) {
		flags[0] = carry;
	}

	public void setOverflow(boolean of) {
		flags[1] = of;
	}

	public void setUnderflow(boolean uf) {
		flags[2] = uf;
	}
}
