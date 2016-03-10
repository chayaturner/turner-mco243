package turner.compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompilerMain {

	public static void main(String[] args) throws IOException {

		try {
			// BufferedReader buffer = new BufferedReader(new
			// InputStreamReader(System.in));
			BufferedReader buffer = new BufferedReader(new FileReader("assembly.txt"));
			Compiler compiler = new Compiler();
			String instruction;

			while ((instruction = buffer.readLine()) != null) {
				compiler.process(instruction);
			}

			buffer.close();
			compiler.outputHex();

			for (String s : compiler.getOutput()) {
				System.out.print(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
