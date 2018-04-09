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
	
	public CyclesModel(Instruction instruction)
	{
		this.instruction = instruction;
	}
	
	public void Assign()
	{
		IR = instruction.getHex();
	//  NPC = get memory+4;
		A = instruction.getB25to21();
		B = instruction.getB20to16();
		IMM = instruction.getB15to11() + instruction.getB10to6() + instruction.getB5to0();
		
		//LD
		if (instruction.equals("110111"))
		{
			int temp1 = Integer.parseInt(A);
			int temp2 = Integer.parseInt(IMM);
			
			int result = temp1 + temp2;			
			
			ALUOUTPUT = String.format ("%016d", result);
			
			COND = 0;
			
			PC = NPC;
		//	LMD = DEPENDE SA MEMORY
			RANGE = "N/A";
		//  Rn = LMD 
			
		}
		
		//SD
		else if (instruction.equals("111111"))
		{
			int temp1 = Integer.parseInt(A);
			int temp2 = Integer.parseInt(IMM);
			
			int result = temp1 + temp2;			
			
			ALUOUTPUT = String.format ("%016d", result);
			
			COND = 0;
			
			PC = NPC;
			LMD = "N/A";
		// RANGE = HOW MANY SPACES SA ALUPOUTPUT
		  Rn = "N/A";
			
		}
		
		//DADDIU
		else if (instruction.equals("011001"))
		{
			int temp1 = Integer.parseInt(A);
			int temp2 = Integer.parseInt(IMM);
			
			int result = temp1 + temp2;			
			
			ALUOUTPUT = String.format ("%016d", result);
			
			COND = 0;
			
			PC = NPC;
			LMD = "N/A";
			RANGE = "N/A";
			Rn = ALUOUTPUT;
			
			
		}
		
		//DADDU
		else if (instruction.equals("000000"))
		{
			int temp1 = Integer.parseInt(A);
			int temp2 = Integer.parseInt(B);
			
			int result = temp1 + temp2;			
			
			ALUOUTPUT = String.format ("%016d", result);
			
			COND = 0;
			
			PC = NPC;
			LMD = "N/A";
			RANGE = "N/A";
			Rn = ALUOUTPUT;
			
		}
		
		//BC
		else if (instruction.equals("110010"))
		{
		
		//	ALUOUTPUT = Sir's formula;
			
			COND = 1;
			
		//	PC = Address Where the Jump is;
			LMD = "N/A";
			RANGE = "N/A";
			Rn = "N/A";	
		}
		
		//BNEZC
		else if (instruction.equals("111110"))
		{
//			ALUOUTPUT = Sir's formula;
			
			if (instruction.getB25to21().equals("00000"))
				COND = 0;
			
			else 
				COND = 1;
					
		//	PC = Where the Jump is;
			LMD = "N/A";
			RANGE = "N/A";
			Rn = "N/A";	
							
		}
		
		//ORI
		else if (instruction.equals("111110"))
		{
			int temp1 = Integer.parseInt(A);
			int temp2 = Integer.parseInt(IMM);
			
			int result = temp1 + temp2;			
			
			ALUOUTPUT = String.format ("%016d", result);
			
			COND = 0;
			
			PC = NPC;
			LMD = "N/A";
			RANGE = "N/A";
			Rn = ALUOUTPUT;
							
		}
		
	}
	
	

}
