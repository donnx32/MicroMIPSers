package model;

public class Data {
	private static String nextAddress = "0000";
	String address;
	String representation;
	
	public Data() {
		this.setAddress(nextAddress);
		this.setRepresentation("00");
		nextAddress = zeroExtend(Integer.toString((Integer.parseInt(nextAddress, 16) + Integer.parseInt("1", 16)), 16), 4).toUpperCase();
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRepresentation() {
		return representation;
	}
	public void setRepresentation(String representation) {
		this.representation = representation;
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
	
}
