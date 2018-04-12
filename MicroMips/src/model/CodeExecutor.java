package model;

import java.util.ArrayList;

import view.MainView;

public class CodeExecutor {
	public static ArrayList<Cycle> cycleList;
	private ORConverter or;
	
	public CodeExecutor() {
		or = new ORConverter();
		cycleList = new ArrayList<Cycle>();
	}

	public void execute(ArrayList<Instruction> iList) {
		for (Instruction i : iList) {
			String ins = i.getValue();
			String ir = i.getHex();
			String npc = zeroExtend(
					Integer.toString((Integer.parseInt(i.getAddress(), 16) + Integer.parseInt("4", 16)), 16), 16);
			String a = RegisterModel.regList.get(binToDec(i.getBin().substring(6, 11))).getRegisterValue();
			String b = RegisterModel.regList.get(binToDec(i.getBin().substring(11, 16))).getRegisterValue();
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
				
				Register reg = RegisterModel.regList.get((binToDec(i.getBin().substring(11, 16))));
				reg.setRegisterValue(alu.toUpperCase());
				MainView.getrModel().setValueAt(reg.getRegisterValue(), reg.getRegisterNumber(), 1);
			} else if (i.getOpCode().equals(OpCode.ORI.getOpCode())) {
				lmd = "N/A";
				r = "N/A";
				cond = "0";
				alu = or.OR(a, imm);
				lmd = "N/A";
				r = "N/A";
				rn = alu;
				
				Register reg = RegisterModel.regList.get((binToDec(i.getBin().substring(11, 16))));
				reg.setRegisterValue(alu.toUpperCase());
				MainView.getrModel().setValueAt(reg.getRegisterValue(), reg.getRegisterNumber(), 1);				
			} else if (i.getOpCode().equals(OpCode.DADDU.getOpCode())) {
				alu = zeroExtend(Integer.toString(Integer.parseInt(a, 16) + Integer.parseInt(b, 16), 16), 16);
				lmd = "N/A";
				r = "N/A";
				cond = "0";
				rn = alu;
				
				Register reg = RegisterModel.regList.get((binToDec(i.getBin().substring(16, 21))));
				reg.setRegisterValue(alu.toUpperCase());
				MainView.getrModel().setValueAt(reg.getRegisterValue(), reg.getRegisterNumber(), 1);
			} else if(i.getOpCode().equals(OpCode.LD.getOpCode())) {
				alu = zeroExtend(Integer.toString(Integer.parseInt(a, 16) + Integer.parseInt(imm, 16), 16), 16);
				r = "N/A";
				cond = "0";
				lmd = zeroExtend(getData(i.getHex().substring(4, 8)), 16);
				
				rn = lmd;
				Register reg = RegisterModel.regList.get((binToDec(i.getBin().substring(11, 16))));
				reg.setRegisterValue(lmd.toUpperCase());
				MainView.getrModel().setValueAt(reg.getRegisterValue(), reg.getRegisterNumber(), 1);
			} else if (i.getOpCode().equals(OpCode.SD.getOpCode())) {
				alu = zeroExtend(Integer.toString(Integer.parseInt(a, 16) + Integer.parseInt(imm, 16), 16), 16);
				cond = "0";
				lmd = "N/A";
				rn = "N/A";
				
				r= i.getHex().substring(4, 8) + " - " + zeroExtend(Integer.toString(Integer.parseInt( i.getHex().substring(4, 8), 16) + Integer.parseInt("7", 16), 16), 4);
				saveData(i.getHex().substring(4, 8), b);
			} else if (i.getOpCode().equals(OpCode.BC.getOpCode())) {
				cond = "1";
				lmd = "N/A";
				rn = "N/A";
				r = "N/A";
			} else if (i.getOpCode().equals(OpCode.BNEZC.getOpCode())) {
				
			}

			cycleList.add(new Cycle(ins, ir, npc, a, b, imm, alu, cond, lmd, r, rn));
			System.out.println(ins);
			System.out.println(ir);
			System.out.println(npc);
			System.out.println("a = " + a);
			System.out.println("b = " + b);
			System.out.println("imm = " + imm);
			System.out.println("alu = " + alu);
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
	
	public String getData(String s) {
		String res = "";
		
		for(int i = 0; i < DataModel.dataList.size(); i++) {
			if(DataModel.dataList.get(i).getAddress().equalsIgnoreCase(s)) {
				for(int j = i; j < i + 8; j++) {
					res = DataModel.dataList.get(j).getRepresentation() + res;
				}
				break;
			}
		}
		System.out.println("res ->" +res);
		return res;
	}
	
	public void saveData(String n, String d) {
		int x = 0;
		for(int i = 0; i < DataModel.dataList.size(); i++) {
			if(DataModel.dataList.get(i).getAddress().equalsIgnoreCase(n)) {
				for(int j = i; j < i + 8; j++) {
					DataModel.dataList.get(j).setRepresentation(d.substring(0 + x, 2 + x));
					MainView.dataModel.setValueAt(DataModel.dataList.get(j).getRepresentation(), j, 1);
					x += 2;
				}
				break;
			}
		}
	}
}
