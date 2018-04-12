package model;

import java.util.ArrayList;

public class CodeParser {
	private ArrayList<Instruction> instructList;

	public CodeParser(ArrayList<Instruction> instructList) {
		this.instructList = instructList;
	}

	public void parseCode() {
		labelLines();

		for (Instruction i : instructList) {
			// System.out.println("OUTER");
			// System.out.println(i);
			i.findOpCode();
			i.setBin(i.getOpCode());

			String[] temp = i.getValue().split(" ");

			int x = 0;
			if (i.getLabel() != null) {
				x = 1;
			}

			if (temp[0 + x].equalsIgnoreCase("DADDIU") || temp[0 + x].equalsIgnoreCase("ORI")) {
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[2 + x])), 5));
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[1 + x])), 5));
				i.setBin(i.getBin() + zeroExtend(hexToBin(getHex(temp[3 + x])), 16));
				i.setHex(zeroExtend(binToHex(i.getBin()), 8));
			} else if (temp[0 + x].equalsIgnoreCase("LD") || temp[0 + x].equalsIgnoreCase("SD")) {
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[2 + x].substring(5, temp[2].length()))), 5));
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[1 + x])), 5));
				i.setBin(i.getBin() + zeroExtend(hexToBin(temp[2 + x].substring(0, 4)), 16));
				i.setHex(zeroExtend(binToHex(i.getBin()), 8));
			} else if (temp[0 + x].equalsIgnoreCase("DADDU")) {
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[2 + x])), 5));
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[3 + x])), 5));
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[1 + x])), 5));
				i.setBin(i.getBin() + "00000101101"); // SA(5) = 00000, FUNC(6) = 101101
				i.setHex(zeroExtend(binToHex(i.getBin()), 8));
			} else if (temp[0 + x].equalsIgnoreCase("BC")) {
				for (Instruction in : instructList) {
					if (in.getLabel() != null) {
						// System.out.println(in);
						if (in.getLabel().equals(temp[1 + x])) {
							int diff = ((Integer.parseInt(in.getAddress(), 16) - Integer.parseInt(i.getAddress(), 16))
									/ Integer.parseInt("4", 16)) - 1;
							//System.out.println("diff" + toBin(Integer.toString(diff)));
							i.setBin(i.getBin() + zeroExtend(toBin(Integer.toString(diff)), 26));
							break;
						}
					}
				}
				//System.out.println(i.getBin());
				i.setHex(zeroExtend(binToHex(i.getBin()), 8));
			} else if (temp[0 + x].equalsIgnoreCase("BNEZC")) {
				i.setBin(i.getBin() + zeroExtend(toBin(getDigit(temp[1 + x])), 5));
				for (Instruction in : instructList) {
					if (in.getLabel() != null) {
						if (in.getLabel().equals(temp[2 + x])) {
							int diff = ((Integer.parseInt(in.getAddress(), 16) - Integer.parseInt(i.getAddress(), 16))
									/ Integer.parseInt("4", 16)) - 1;

							i.setBin(i.getBin() + zeroExtend(toBin(Integer.toString(diff)), 21));
							break;
						}
					}
				}
				i.setHex(zeroExtend(binToHex(i.getBin()), 8));
				;
			} else {

			}
		}
	}

	public void executeInstructions() {

	}

	public void labelLines() {
		for (Instruction i : instructList) {
			String[] temp = i.getValue().split(" ");

			if (temp[0].contains(":"))
				i.setLabel(temp[0].substring(0, temp[0].length() - 1));
		}
	}

	public String zeroExtend(String s, int n) {
		StringBuilder sb = null;

		if (s.length() < n) {
			sb = new StringBuilder();
			int diff = n - s.length();

			for (int i = 0; i < diff; i++) {
				sb.append("0");
			}

			return sb.toString() + "" + s;
		} else {
			return s.substring(s.length() - n,s.length());
		}
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
	
	public String getHex(String s) {
		return s.replaceAll("[^\\0123456789ABCDEF]","");
	}
}
