package turner.microprocessor;

import java.io.IOException;

public class Processor {

	String[] memory;
	private String A; // accumulator
	private String B; // accumulator
	private int current; // current placeholder in memory
	private int tempAddressOne, tempAddressTwo, address;

	public Processor(Memory memory) throws IOException {

		this.memory = memory.getMemory();
		A = "0";
		B = "0";

	}

	// process the instruction set
	public void process() {
		current = 0;

		while (memory[current] != "8") {
			switch (memory[current]) {
			case "0": // load
				// get address to load from instruction
				current++;// move to next instruction
				tempAddressOne = Integer.parseInt(memory[current], 16); // in
																		// hex
				current++;
				tempAddressTwo = Integer.parseInt(memory[current], 16);
				tempAddressOne *= 16; // hex
				address = tempAddressOne + tempAddressTwo;
				A = memory[address];
				current++;
				break;

			case "1": // store

				current++;
				tempAddressOne = Integer.parseInt(memory[current], 16);
				tempAddressOne *= 16;
				current++;
				tempAddressTwo = Integer.parseInt(memory[current], 16);
				address = tempAddressOne + tempAddressTwo;
				memory[address] = A;
				current++;
				break;

			case "2": // swap

				String temp = A;
				A = B;
				B = temp;
				current++;
				break;

			case "3": // add

				// hex
				int numberOne = Integer.parseInt(A, 16);
				int numberTwo = Integer.parseInt(B, 16);
				int num = numberOne + numberTwo;
				// string
				String number = Integer.toHexString(num).toUpperCase();

				if (number.length() == 2) {
					A = number.substring(1);
					B = number.substring(0);
				} else {
					A = number.substring(0);
					B = "0";
				}

				current++;
				break;

			case "4": // increment

				if (A.equals("F")) {
					A = "0";
				} else {
					int val = Integer.parseInt(A, 16);
					val++; // increment
					A = Integer.toHexString(val).toUpperCase();
				}

				current++;
				break;

			case "5": // decrement

				if (A.equals("0")) {
					A = "F";
				} else {
					int val = Integer.parseInt(A, 16);
					val--;
					A = Integer.toHexString(val).toUpperCase();
				}

				current++;
				break;

			case "6": // bz - jump to location if A is zero
				if (A.equals("0")) {
					current++;
					tempAddressOne = Integer.parseInt(memory[current], 16);
					tempAddressOne *= 16;
					current++;
					tempAddressTwo = Integer.parseInt(memory[current], 16);
					current = tempAddressOne + tempAddressTwo;
				} else {
					current += 3; // nothing happens
				}

				break;

			case "7": // br - jump to location

				current++;
				tempAddressOne = Integer.parseInt(memory[current], 16);
				tempAddressOne *= 16;
				current++;
				tempAddressTwo = Integer.parseInt(memory[current], 16);
				tempAddressTwo *= 16;

				current = tempAddressOne + tempAddressTwo;
				break;

			case "8": // stop
				return;
			}
		}

	}

}
