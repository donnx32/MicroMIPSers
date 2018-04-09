package model;

public enum OpCode {
	LD, SD, DADDIU, DADDU, BC, BNEZC, ORI;

	public String getOpCode() {
		switch (this) {
		case LD:
			return "110111";
		case SD:
			return "111111";
		case DADDIU:
			return "011001";
		case DADDU:
			return "000000";
		case BC:
			return "110010";
		case BNEZC:
			return "111110";
		case ORI:
			return "001101";
		default:
			throw new AssertionError("Unknown operation " + this);
		}
	}
}
