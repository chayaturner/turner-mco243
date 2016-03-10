package turner.compiler;

public class Compiler {

	private String[] output; // in hex
	private int index;

	public Compiler() {
		this.output = new String[256];
		this.index = 0;
	}

	public void process(String input) {

		String[] splitInput = input.split(" ");
		String instruction = splitInput[0];
		String data = null;

		if (splitInput.length == 2) {
			data = splitInput[1];
		}

		switch (instruction) {

		case "LD":
			output[index] = "0";
			index++;
			String hex = Integer.toHexString(Integer.parseInt(data)).toUpperCase();
			if (hex.length() == 2) {
				output[index] = String.valueOf(hex.charAt(0));
				index++;
				output[index] = String.valueOf(hex.charAt(1));
				index++;
			} else {
				output[index] = "0";
				index++;
				output[index] = String.valueOf(hex.charAt(0));
				index++;
			}
			break;
		case "ST":
			output[index] = "1";
			index++;
			hex = Integer.toHexString(Integer.parseInt(data)).toUpperCase();
			if (hex.length() == 2) {
				output[index] = String.valueOf(hex.charAt(0));
				index++;
				output[index] = String.valueOf(hex.charAt(1));
				index++;
			} else {
				output[index] = "0";
				index++;
				output[index] = String.valueOf(hex.charAt(0));
				index++;
			}
			break;
		case "SWP":
			output[index] = "2";
			index++;
			break;
		case "ADD":
			output[index] = "3";
			index++;
			break;
		case "INC":
			output[index] = "4";
			index++;
			break;
		case "DEC":
			output[index] = "5";
			index++;
			break;
		case "BZ":
			output[index] = "6";
			index++;
			hex = Integer.toHexString(Integer.parseInt(data)).toUpperCase();
			if (hex.length() == 2) {
				output[index] = String.valueOf(hex.charAt(0));
				index++;
				output[index] = String.valueOf(hex.charAt(1));
				index++;
			} else {
				output[index] = "0";
				index++;
				output[index] = String.valueOf(hex.charAt(0));
				index++;
			}
			break;
		case "BR":
			output[index] = "7";
			index++;
			hex = Integer.toHexString(Integer.parseInt(data)).toUpperCase();
			if (hex.length() == 2) {
				output[index] = String.valueOf(hex.charAt(0));
				index++;
				output[index] = String.valueOf(hex.charAt(1));
				index++;
			} else {
				output[index] = "0";
				index++;
				output[index] = String.valueOf(hex.charAt(0));
				index++;
			}
			break;
		case "STP":
			output[index] = "8";
			index++;
			break;
		case "DATA":
			String[] dataArray = data.split("");
			for (String d : dataArray) {
				output[index] = d;
				index++;
			}
			break;
		}

	}

	public void outputHex() {
		if (index < output.length) {
			for (int i = index; i < output.length; i++) {
				output[index] = "0";
			}
		}

	}

	public String[] getOutput() {
		return output;
	}
}
