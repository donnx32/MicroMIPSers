package model;

import java.util.ArrayList;

public class CodeParser {
	private ArrayList<Instruction> instructList;

	public CodeParser(ArrayList<Instruction> instructList) {
		this.instructList = instructList;
	}

	public void parseCode() {
		for (Instruction i : instructList) {
			i.findOpCode();
			i.setB31to0(i.getOpCode());
			//System.out.println(i.getB31to0());

			String[] temp = i.getValue().split(" ");

			if (temp[0].equalsIgnoreCase("DADDIU")) {
				i.setB31to0(i.getB31to0() + "" + zeroExtendR(toBin(getDigit(temp[2]))));
				i.setB31to0(i.getB31to0() + "" + zeroExtendR(toBin(getDigit(temp[1]))));
				i.setB31to0(i.getB31to0() + "" + zeroExtendI(hexToBin(temp[3].substring(1,5))));
				i.setHex(binToHex(i.getB31to0()));
				i.splitBin();
			}
			else if (temp[0].equalsIgnoreCase("LD")) {
				
			}
			else if (temp[0].equalsIgnoreCase("SD")) {
				
			}
			else if (temp[0].equalsIgnoreCase("ORI")) {
	
			}
			else if (temp[0].equalsIgnoreCase("DADDU")) {
	
			}
			else if (temp[0].equalsIgnoreCase("BC")) {
				
			}
			else if (temp[0].equalsIgnoreCase("BNEZC")) {
				
			}
			else {
				
			}
		}
	}

	public void executeInstructions() {

	}

	public String zeroExtendR(String bin) {
		StringBuilder sb = null;

		if (bin.length() < 5) {
			sb = new StringBuilder();
			int diff = 5 - bin.length();

			for (int i = 0; i < diff; i++) {
				sb.append("0");
			}
		}

		if (sb != null)
			return sb.toString() + "" + bin;

		return bin;
	}

	public String zeroExtendI(String bin) {
		StringBuilder sb = null;

		if (bin.length() < 16) {
			sb = new StringBuilder();
			int diff = 16 - bin.length();

			for (int i = 0; i < diff; i++) {
				sb.append("0");
			}
		}

		if (sb != null)
			return sb.toString() + "" + bin;

		return bin;
	}

	public String toBin(String s) {
		return Integer.toBinaryString(Integer.parseInt(s));
	}

	public String toHex(String s) {
		return Integer.toHexString(Integer.parseInt(s));
	}

	public String binToHex(String s) {
		return Integer.toString(Integer.parseInt(binToDec(s)), 16);
	}

	public String binToDec(String s) {
		return Integer.toString(Integer.parseInt(s, 2));
	}

	public String hexToBin(String s) {
		return toBin(hexToDec(s));
	}

	public String hexToDec(String s) {
		return Integer.toString(Integer.parseInt(s, 16));
	}

	public String getDigit(String s) {
		return s.replaceAll("\\D+", "");
	}
}
