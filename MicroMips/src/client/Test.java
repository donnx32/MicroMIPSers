package client;

import model.ORConverter;
import model.OpCode;

public class Test {

	public static void main(String[] args) {
		String t = "012345";
		System.out.println(t.charAt(1));
		
		ORConverter or = new ORConverter();
		
		System.out.println(getHexA("1123ar"));
		System.out.println(OpCode.DADDIU);
	}
	public static String getHexA(String s) {
		return s.replaceAll("[^\\.0123456789]","");
	}
}
