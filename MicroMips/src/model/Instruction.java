package model;

public class Instruction {
	private String value;
	private String opCode;
	private String hex;
	private String b31to0;
	private String b31to26;
	private String b25to21;
	private String b20to16;
	private String b15to11;
	private String b10to6;
	private String b5to0;

	public Instruction(String code) {
		this.setValue(code);
	}
	
	public void findOpCode() {
		for (OpCode oc : OpCode.values()) {
			if (getValue().toUpperCase().contains(oc.name())) {
				this.setOpCode(oc.getOpCode());
				break;
			}
		}
	}
	
	public void exec() {
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getB31to0() {
		return b31to0;
	}

	public void setB31to0(String b31to0) {
		this.b31to0 = b31to0;
	}

	public String getB31to26() {
		return b31to26;
	}

	public void setB31to26(String b31to26) {
		this.b31to26 = b31to26;
	}

	public String getB25to21() {
		return b25to21;
	}

	public void setB25to21(String b25to21) {
		this.b25to21 = b25to21;
	}

	public String getB20to16() {
		return b20to16;
	}

	public void setB20to16(String b20to16) {
		this.b20to16 = b20to16;
	}

	public String getB15to11() {
		return b15to11;
	}

	public void setB15to11(String b15to11) {
		this.b15to11 = b15to11;
	}

	public String getB10to6() {
		return b10to6;
	}

	public void setB10to6(String b10to6) {
		this.b10to6 = b10to6;
	}

	public String getB5to0() {
		return b5to0;
	}

	public void setB5to0(String b5to0) {
		this.b5to0 = b5to0;
	}
	
}
