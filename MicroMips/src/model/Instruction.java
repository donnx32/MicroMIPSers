package model;

public class Instruction {
	private String value;
	private String opCode;
	private String hex;
	private String bin;
	private String address;
	private static String nextAddress = "100";

	public Instruction(String code) {
		this.setValue(code);
		this.setAddress(nextAddress);
		nextAddress = Integer.toString((Integer.parseInt(nextAddress, 16) + Integer.parseInt("4", 16)), 16);
	}
	
	public void findOpCode() {
		for (OpCode oc : OpCode.values()) {
			if (getValue().toUpperCase().contains(oc.name())) {
				this.setOpCode(oc.getOpCode());
				break;
			}
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value.toUpperCase();
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode.toUpperCase();
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex.toUpperCase();
	}
	
	public void setBin(String bin) {
		this.bin = bin.toUpperCase();
	}
	
	public String getBin() {
		return bin;
	}
	
	public void setAddress(String address) {
		this.address = address.toUpperCase();
	}
	
	public String getAddress() {
		return address;
	}
}
