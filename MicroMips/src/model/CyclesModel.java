package model;

public class CyclesModel {
	
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
		cycle.setimm(instruction.getBin().substring(17, 32));
		
		//LD
		if (instruction.getOpCode().equals("110111"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getimm());
			
			int result = temp1 + temp2;			
			
			cycle.setaluOutput(String.format ("%016d", result));
			
			cycle.setcond("0");
			
			cycle.setpC(cycle.getnPC());;
		//	LMD = DEPENDE SA MEMORY
			cycle.setRange("N/A");
			cycle.setrN(cycle.getlMD());
			
		}
		
		//SD
		else if (instruction.getOpCode().equals("111111"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getimm());
			
			int result = temp1 + temp2;			
			
			cycle.setaluOutput(String.format ("%016d", result));
			cycle.setcond("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
		// RANGE = HOW MANY SPACES SA ALUPOUTPUT
			
			cycle.setrN("N/A");
		}
		
		//DADDIU
		else if (instruction.getOpCode().equals("011001"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getimm());
			
			int result = temp1 + temp2;			
			
			cycle.setaluOutput(String.format ("%016d", result));
			cycle.setcond("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN(cycle.getaluOutput());
			
		}
		
		//DADDU
		else if (instruction.getOpCode().equals("000000"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getB());
			
			int result = temp1 + temp2;			
			
			cycle.setaluOutput(String.format ("%016d", result));
			cycle.setcond("0");
			
			cycle.setpC(cycle.getnPC());;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN(cycle.getaluOutput());	
		}
		
		//BC
		else if (instruction.getOpCode().equals("110010"))
		{
			int count;
			count = Integer.parseInt(cycle.getimm(), 2);
			int tempNPC = Integer.parseInt(cycle.getnPC());
			
			while (count > 0)
			{
				tempNPC = tempNPC + 4;
			}
			cycle.setaluOutput(Integer.toHexString(tempNPC));
			cycle.setcond("1");
			
			cycle.setpC(cycle.getaluOutput());
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
			
			cycle.setrN("N/A");	
		}
		
		
		//BNEZC
		else if (instruction.getOpCode().equals("111110"))
		{
			int count;
			count = Integer.parseInt(cycle.getimm(), 2);
			int tempNPC = Integer.parseInt(cycle.getnPC());
			
			while (count > 0)
			{
				tempNPC = tempNPC + 4;
			}
			cycle.setaluOutput(Integer.toHexString(tempNPC));
			
			//If A is 0 then tru
			if (cycle.getA().equals("00000"))
			{
				cycle.setcond("0");
				cycle.setnPC(cycle.getnPC());
			}
			else 
			{
				cycle.setcond("1");
				cycle.setpC(cycle.getaluOutput());
			}
					
				
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
				
			cycle.setrN("N/A");	
							
		}
		
		//ORI
		else if (instruction.getOpCode().equals("111110"))
		{
			int temp1 = Integer.parseInt(cycle.getA());
			int temp2 = Integer.parseInt(cycle.getimm());
			
			int result = temp1 + temp2;			
			
			cycle.setaluOutput(String.format ("%016d", result));
			cycle.setcond("0");
			
			//PC = Where the Jump is;
			cycle.setlMD("N/A");
			cycle.setRange("N/A");
				
			cycle.setrN(cycle.getaluOutput());	
							
		}
		
	}
	
	
	public double convertToDec(double address)
	{
		//turns hex to dec
		 if(instruction.getAddress().contains("A") ||instruction.getAddress().contains("B") || 
			instruction.getAddress().contains("C") ||instruction.getAddress().contains("D") || 
			instruction.getAddress().contains("E") ||instruction.getAddress().contains("F"))
		 {   
			 address  = Integer.parseInt(instruction.getAddress(), 16);
			 address = (double) address;
		 }
		 
		 else
		 {
			 address = Double.parseDouble(String.valueOf(instruction.getAddress()));
		 }
		 
		 address = Integer.parseInt(instruction.getAddress(), 16);
		 
		 return address;
	}

}
