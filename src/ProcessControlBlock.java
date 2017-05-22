
public class ProcessControlBlock {

	private IProcess process;
	private int currInstruction;
	
	private int register1;
	private int register2;
	private int register3;
	private int register4;
	
	public ProcessControlBlock(IProcess proc)
	{
		process = proc;
		currInstruction = 1;
	}
	
	public IProcess getProcess()
	{
		return process;
	}
	
	public int getCurrentInstruction()
	{
		return currInstruction;
	}
	
	public void setCurrentInstruction(int instruc)
	{
		currInstruction = instruc;
	}
	
	public int getRegister1() {
		return register1;
	}

	public void setRegister1(int register1) {
		this.register1 = register1;
	}

	public int getRegister2() {
		return register2;
	}

	public void setRegister2(int register2) {
		this.register2 = register2;
	}

	public int getRegister3() {
		return register3;
	}

	public void setRegister3(int register3) {
		this.register3 = register3;
	}

	public int getRegister4() {
		return register4;
	}

	public void setRegister4(int register4) {
		this.register4 = register4;
	}

	//toString for debugger 
	public String toString()
	{
		return "Process: " +this.process.getProcName() + " Instruction: " + this.currInstruction;
	}
}
