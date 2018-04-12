package model;


import java.util.ArrayList;

public class CodeExecutor {
	private ArrayList<Cycle> cycleList;
	
	public CodeExecutor() {
		cycleList = new ArrayList<Cycle>();
	}
	
	public void execute(ArrayList<Instruction> iList) {
		for(Instruction i : iList) {
			String ins = i.getValue();
			String npc = beautify(zeroExtend(Integer.toString((Integer.parseInt(i.getAddress(), 16) + Integer.parseInt("4", 16)), 16), 16));
			String a = beautify(RegisterModel.regList.get(binToDec(i.getBin().substring(7, 11))).getRegisterValue());
			String b = beautify(RegisterModel.regList.get(binToDec(i.getBin().substring(12, 16))).getRegisterValue());
			String imm = beautify(zeroExtend((i.getBin().substring(17, 32)), 16));
			
			if (i.getOpCode().equals("110111")) {
			
			}
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
	
	public String beautify(String s) {
		return s.substring(0, 3) + " " +s.substring(4, 7) + " " + s.substring(8, 11) + " " + s.substring(12, 15);
	}
	
	public int binToDec(String s) {
		return Integer.parseInt(s, 2);
	}
}
