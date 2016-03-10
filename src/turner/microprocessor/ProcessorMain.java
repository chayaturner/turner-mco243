package turner.microprocessor;

import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessorMain {

	public static void main(String[] args) throws IOException {

		Memory memory;
		Processor processor;
		String instruction;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // read
																						// from
																						// command
																						// prompt
		// (new FileReader(new File("mach.in"))); //read directly from the file
		instruction = reader.readLine();
		while (instruction != null) {
			memory = new Memory(instruction);
			processor = new Processor(memory);
			processor.process();

			for (String s : memory.getMemory()) {
				System.out.print(s);
			}
			System.out.println();
			instruction = reader.readLine(); // read next line
		}

		reader.close();
	}
}
