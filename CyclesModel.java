package model;


public class CyclesModel {
	
	String IR;
	String NPC;
	String A, B, IMM;
	String ALUOUTPUT;
	int COND;
	String PC, LMD, RANGE;
	String Rn;
	Instruction instruction;
	
	Cycle cycle = new Cycle();
	
	public CyclesModel(Instruction instruction)
	{
		this.instruction = instruction;
	}
	
	public void Assign()
	{
		double address = 0;
		
		address = convertToDec(address);
		address = address + 4;
		
		cycle.setiR(instruction.getHex());
		cycle.setnPC(Double.toHexString(address));
		cycle.setA(instruction.getBin().substring(7, 11));
		cycle.setB(instruction.getBin().substring(12, 16));
		cycle.setiMM(instruction.getBin().substring(17, 32));
		
		//LD
		if (instruction.getBin().substring(1, 6).equals("110111"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getiMM());
			
			int result = temp1 + temp2;			
			
			cycle.setaLUOUTPUT(String.format ("%016d", result));
			
			cycle.setcOND("0");
			
			cycle.setpC(cycle.getnPC());;
		//	LMD = DEPENDE SA MEMORY
			cycle.setRange("N/A");
			cycle.setrN(cycle.getlMD());
			
		}
		
		//SD
		else if (instruction.getBin().substring(1, 6).equals("111111"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getiMM());
			
			int result = temp1 + temp2;			
			
			cycle.setaLUOUTPUT(String.format ("%016d", result));
			cycle.setcOND("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
		// RANGE = HOW MANY SPACES SA ALUPOUTPUT
			
			cycle.setrN("N/A");
		}
		
		//DADDIU
		else if (instruction.getBin().substring(1, 6).equals("011001"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getiMM());
			
			int result = temp1 + temp2;			
			
			cycle.setaLUOUTPUT(String.format ("%016d", result));
			cycle.setcOND("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN(cycle.getaLUOUTPUT());
			
		}
		
		//DADDU
		else if (instruction.getBin().substring(1, 6).equals("000000"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getB());
			
			int result = temp1 + temp2;			
			
			cycle.setaLUOUTPUT(String.format ("%016d", result));
			cycle.setcOND("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN(cycle.getaLUOUTPUT());	
		}
		
		//BC
		else if (instruction.getBin().substring(1, 6).equals("110010"))
		{
		
		//	ALUOUTPUT = Sir's formula;
			cycle.setcOND("1");
			
		//	PC = Address Where the Jump is;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN("N/A");	
		}
		
		
		//BNEZC
		else if (instruction.getBin().substring(1, 6).equals("111110"))
		{
			//ALUOUTPUT = Sir's formula;
			//If A is 0 then tru
			if (cycle.getA().equals("00000"))
				cycle.setcOND("0");
			else 
				cycle.setcOND("1");;
					
			//PC = Where the Jump is;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
				
			cycle.setrN("N/A");	
							
		}
		
		//ORI
		else if (instruction.getBin().substring(1, 6).equals("111110"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getiMM());
			
			int result = temp1 + temp2;			
			
			cycle.setaLUOUTPUT(String.format ("%016d", result));
			cycle.setcOND("0");
			
			//PC = Where the Jump is;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
				
			cycle.setrN(cycle.getaLUOUTPUT());	
							
		}
		
	}
	
	
	public double convertToDec(double address)
	{
		//turns hex to dec
		 if(instruction.getHex().contains("A") ||instruction.getHex().contains("B") || 
			instruction.getHex().contains("C") || instruction.getHex().contains("D") || 
			instruction.getHex().contains("E") ||instruction.getHex().contains("F"))
		 {   
			 address  = Integer.parseInt(instruction.getHex(), 16);
			 address = (double) address;
		 }
		 
		 else
		 {
			 address = Double.parseDouble(String.valueOf(instruction.getHex()));
		 }
		 
		 return address;
	}

}
