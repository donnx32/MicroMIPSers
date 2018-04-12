package model;


public class ORConverter {
	
	String a;
	String b;

	public String OR(String A, String IMM)
	{
		String result = "";
		
		
		String aBin = zeroExtend(hexToBin(A), 16);
		String immBin = zeroExtend(hexToBin(IMM), 16);
		System.out.println(aBin);
		System.out.println(immBin);
		
		for(int i = 0; i < IMM.length(); i++)
		{
			
			if (aBin.charAt(i) == '0' && immBin.charAt(i) == '0')
				result = result + "0";
			
			else 
				result = result + "1";

		}
		return zeroExtend(binToHex(result), 16);

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
	
	public String binToHex(String s) {
		long dec = Long.parseLong(s, 2);
		return Long.toString(dec, 16);
	}
	
	public String toBin(String s) {
		return Integer.toBinaryString(Integer.parseInt(s));
	}
	
	public String hexToBin(String s) {
		return toBin(hexToDec(s));
	}
	
	public String hexToDec(String s) {
		return Integer.toString(Integer.parseInt(s, 16));
	}
}
