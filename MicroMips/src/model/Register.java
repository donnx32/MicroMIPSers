package model;

public class Register {
	private int registerNumber;
	private String registerValue;

	public Register(int num) {
		setRegisterNumber(num);
	}
	
	public Register(int num, String val) {
		setRegisterNumber(num);
		setRegisterValue(val);
	}
	
	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getRegisterValue() {
		return registerValue;
	}

	public void setRegisterValue(String registerValue) {
		this.registerValue = registerValue;
	}
}
