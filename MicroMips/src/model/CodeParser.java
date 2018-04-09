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
			i.setBin(i.getOpCode());

			String[] temp = i.getValue().split(" ");

			if (temp[0].equalsIgnoreCase("DADDIU") || temp[0].equalsIgnoreCase("ORI")) {
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[2]))));
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[1]))));
				i.setBin(i.getBin() + "" + zeroExtendI(hexToBin(temp[3].substring(1, 5))));
				i.setHex(zeroExtendH(binToHex(i.getBin())));
			} else if (temp[0].equalsIgnoreCase("LD") || temp[0].equalsIgnoreCase("SD")) {
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[2].substring(5, temp[2].length())))));
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[1]))));
				i.setBin(i.getBin() + "" + zeroExtendI(hexToBin(temp[2].substring(0, 4))));
				i.setHex(zeroExtendH(binToHex(i.getBin())));
			} else if (temp[0].equalsIgnoreCase("DADDU")) {
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[2]))));
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[3]))));
				i.setBin(i.getBin() + "" + zeroExtendR(toBin(getDigit(temp[1]))));
				i.setBin(i.getBin() + "" + "00000101101"); // SA(5) = 00000, FUNC(6) = 101101
				i.setHex(zeroExtendH(binToHex(i.getBin())));
			} else if (temp[0].equalsIgnoreCase("BC")) {
				// to be done when Line names are up e.g L1, L2, etc....
			} else if (temp[0].equalsIgnoreCase("BNEZC")) {
				// to be done when Line names are up e.g L1, L2, etc....
			} else {

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

	public String zeroExtendH(String hex) {
		StringBuilder sb = null;

		if (hex.length() < 8) {
			sb = new StringBuilder();
			int diff = 8 - hex.length();

			for (int i = 0; i < diff; i++) {
				sb.append("0");
			}
		}

		if (sb != null)
			return sb.toString() + "" + hex;

		return hex;
	}

	public String toBin(String s) {
		return Integer.toBinaryString(Integer.parseInt(s));
	}

	public String toHex(String s) {
		return Integer.toHexString(Integer.parseInt(s));
	}

	public String binToHex(String s) {
		long dec = Long.parseLong(s, 2);
		return Long.toString(dec, 16);
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
