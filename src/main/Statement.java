package main;

/**
 * Manages Statements.
 * @author lars
 *
 */
public class Statement {
	/** The statements Command */
	private final Opcode command;
	/** The adresses for the command */
	private final int addrRes, addr0, addr1;

	public Statement(Opcode command, int addrRes, int addr0, int addr1) {
		super();
		this.command = command;
		this.addr0 = addr0;
		this.addr1 = addr1;
		this.addrRes = addrRes;
	}

	public Statement(Opcode command, int addrRes, int addr0) {
		this(command, addrRes, addr0, 0);
	}

	public Statement(Opcode command, int addrRes) {
		this(command, addrRes, 0);
	}

	public Opcode getCommand() {
		return command;
	}

	public Integer getAddrRes() {
		return addrRes;
	}

	public Integer getAddr0() {
		return addr0;
	}

	public Integer getAddr1() {
		return addr1;
	}


	public void execute(boolean[][] register, boolean[] flags){
		Result effect = command.execute(register[addr0], register[addr1], flags[0]);
		register[addrRes] = effect.getValue();
		flags = effect.getFlags();
	}

}
