package turner.microprocessor;

public class Memory {

	private String[] memory;

	public Memory(String instruction) {
		this.memory = instruction.split("");
	}

	public String[] getMemory() {
		return this.memory;
	}

	public void setMemory(String[] instruction) {
		this.memory = instruction;
	}
}
