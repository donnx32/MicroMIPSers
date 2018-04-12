package model;

import java.util.ArrayList;

import view.MainView;

public class CodeExecutor {
	private ArrayList<Cycle> cycleList;

	public CodeExecutor() {
		cycleList = new ArrayList<Cycle>();
	}

	public void execute(ArrayList<Instruction> iList) {
		for (Instruction i : iList) {
			String ins = i.getValue();
			String ir = i.getHex();
			String npc = zeroExtend(
					Integer.toString((Integer.parseInt(i.getAddress(), 16) + Integer.parseInt("4", 16)), 16), 16);
			String a = RegisterModel.regList.get(binToDec(i.getBin().substring(7, 11))).getRegisterValue();
			String b = RegisterModel.regList.get(binToDec(i.getBin().substring(12, 16))).getRegisterValue();
			String imm = zeroExtend((i.getHex().substring(4, 8)), 16);
			String alu = "";
			String cond = "";
			String lmd = "";
			String r = "";
			String rn = "";

			if (i.getOpCode().equals(OpCode.DADDIU.getOpCode())) {
				alu = zeroExtend(Integer.toString(Integer.parseInt(a, 16) + Integer.parseInt(imm, 16), 16), 16);
				lmd = "N/A";
				r = "N/A";
				cond = "0";
				rn = alu;
				Register reg;

				reg = RegisterModel.regList.get((binToDec(i.getBin().substring(12, 16))));
				System.out.println(i.getBin().substring(12, 16));

				System.out.println(i.getBin().substring(17, 21));
				reg.setRegisterValue(alu.toUpperCase());
				
				MainView.getrModel().setValueAt(reg.getRegisterValue(), reg.getRegisterNumber(), 1);
			} else if (i.getOpCode().equals(OpCode.ORI.getOpCode())) {
				lmd = "N/A";
				r = "N/A";
				cond = "0";
			}
			else if (i.getOpCode().equals(OpCode.DADDU.getOpCode())) {
				
				Register reg = RegisterModel.regList.get((binToDec(i.getBin().substring(17, 21))));
				reg.setRegisterValue(alu.toUpperCase());
			}

			cycleList.add(new Cycle(ins, ir, npc, a, b, imm, alu, cond, lmd, r, rn));
			System.out.println(ins);
			System.out.println(ir);
			System.out.println(npc);
			System.out.println(a);
			System.out.println(b);
			System.out.println(imm);
			System.out.println(alu);
			System.out.println(cond);
			System.out.println(lmd);
			System.out.println(r);
			System.out.println(rn);
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
			return s.substring(s.length() - n, s.length());
		}
	}

	public int binToDec(String s) {
		return Integer.parseInt(s, 2);
	}

	public ArrayList<Cycle> getCycleList() {
		return cycleList;
	}

}
