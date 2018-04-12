package model;

public class Cycle {
	private String instr;
	private String iR;
	private String nPC;
	private String a;
	private String b;
	private String imm;
	private String aluOutput;
	private String cond;
	private String pC;
	private String lMD;
	private String range;
	private String rN;
	
	public Cycle() {
	}

	public Cycle(String instr, String iR, String nPC, String a, String b, String imm, String aluOutput, String cond, String pC, String lMD, String range, String rN) {
		this.instr = instr;
		this.iR = iR;
		this.nPC = nPC;
		this.a = a;
		this.b = b;
		this.imm = imm;
		this.aluOutput = aluOutput;
		this.cond = cond;
		this.pC = pC;
		this.lMD = lMD;
		this.range = range;
		this.rN = rN;
	}
	
	public String getInstr() {
		return instr;
	}

	public void setInstr(String instr) {
		this.instr = instr;
	}

	public String getiR() {
		return iR;
	}

	public void setiR(String iR) {
		this.iR = iR;
	}

	public String getnPC() {
		return nPC;
	}

	public void setnPC(String nPC) {
		this.nPC = nPC;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getimm() {
		return imm;
	}

	public void setimm(String imm) {
		this.imm = imm;
	}

	public String getaluOutput() {
		return aluOutput;
	}

	public void setaluOutput(String aluOutput) {
		this.aluOutput = aluOutput;
	}

	public String getcond() {
		return cond;
	}

	public void setcond(String cond) {
		this.cond = cond;
	}

	public String getpC() {
		return pC;
	}

	public void setpC(String pC) {
		this.pC = pC;
	}

	public String getlMD() {
		return lMD;
	}

	public void setlMD(String lMD) {
		this.lMD = lMD;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getrN() {
		return rN;
	}

	public void setrN(String rN) {
		this.rN = rN;
	}
}
