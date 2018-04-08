package model;

public class Cycle {
	private Instruction IR;
	private String NPC;
	private String A;
	private String B;
	private String IMM;
	private String ALUOUTPUT;
	private int COND;
	private String PC;
	private String LMD;
	
	public Cycle() {
		
	}
	
	public Instruction getIR() {
		return IR;
	}
	
	public void setIR(Instruction iR) {
		IR = iR;
	}
	
	public String getNPC() {
		return NPC;
	}
	
	public void setNPC(String nPC) {
		NPC = nPC;
	}
	
	public String getA() {
		return A;
	}
	
	public void setA(String a) {
		A = a;
	}
	
	public String getB() {
		return B;
	}
	
	public void setB(String b) {
		B = b;
	}
	
	public String getIMM() {
		return IMM;
	}
	
	public void setIMM(String iMM) {
		IMM = iMM;
	}
	
	public String getALUOUTPUT() {
		return ALUOUTPUT;
	}
	
	public void setALUOUTPUT(String aLUOUTPUT) {
		ALUOUTPUT = aLUOUTPUT;
	}
	
	public int getCOND() {
		return COND;
	}
	
	public void setCOND(int cOND) {
		COND = cOND;
	}
	
	public String getPC() {
		return PC;
	}
	
	public void setPC(String pC) {
		PC = pC;
	}
	
	public String getLMD() {
		return LMD;
	}
	
	public void setLMD(String lMD) {
		LMD = lMD;
	}	
}
