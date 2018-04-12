
public class ORConverter {
	
	String a;
	String b;

	public String OR (String A, String IMM)
	{
		String result = null;
		
		int a = Integer.valueOf(A);
		int b = Integer.valueOf(IMM);
		
		String aBin = Integer.toBinaryString(a);
		String immBin = Integer.toBinaryString(b);
		
		aBin = String.format ("%016d", aBin);
		immBin = String.format("%016", immBin);
		
		for(int i = 0; i < IMM.length(); i++)
		{
			
			if (aBin.charAt(i) == '0' && immBin.charAt(i) == '0')
				result = "0" + result;
			
			else 
				result = "1" + result;

		}
		return result;
		
	}
}
