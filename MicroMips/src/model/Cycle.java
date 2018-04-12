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

	public Cycle(String instr, String iR, String nPC, String a, String b, String imm, String aluOutput, String cond, String lMD, String range, String rN) {
		setInstr(instr);
		setiR(iR);
		setnPC(beautify(nPC));
		setA(beautify(a));
		setB(beautify(b));
		setimm(beautify(imm));
		setaluOutput(beautify(aluOutput));
		setcond(cond);
		setpC(nPC);
		setlMD(lMD);
		setRange(range);
		setrN(rN);
	}
	
	public String beautify(String s) {
		return s.substring(0, 4) + " " +s.substring(4, 8) + " " + s.substring(8, 12) + " " + s.substring(12, 16);
	}
	
	public String getInstr() {
		return instr.toUpperCase();
	}

	public void setInstr(String instr) {
		this.instr = instr.toUpperCase();
	}

	public String getiR() {
		return iR;
	}

	public void setiR(String iR) {
		this.iR = iR.toUpperCase();
	}

	public String getnPC() {
		return nPC;
	}

	public void setnPC(String nPC) {
		this.nPC = nPC.toUpperCase();
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a.toUpperCase();
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b.toUpperCase();
	}

	public String getimm() {
		return imm;
	}

	public void setimm(String imm) {
		this.imm = imm.toUpperCase();
	}

	public String getaluOutput() {
		return aluOutput;
	}

	public void setaluOutput(String aluOutput) {
		this.aluOutput = aluOutput.toUpperCase();
	}

	public String getcond() {
		return cond;
	}

	public void setcond(String cond) {
		this.cond = cond.toUpperCase();
	}

	public String getpC() {
		return pC;
	}

	public void setpC(String pC) {
		this.pC = pC.toUpperCase();
	}

	public String getlMD() {
		return lMD;
	}

	public void setlMD(String lMD) {
		this.lMD = lMD.toUpperCase();
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range.toUpperCase();
	}

	public String getrN() {
		return rN;
	}

	public void setrN(String rN) {
		this.rN = rN.toUpperCase();
	}
}
