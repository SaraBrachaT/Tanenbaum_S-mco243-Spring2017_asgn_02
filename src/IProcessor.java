
public interface IProcessor {

	public IProcess getCurrentProcess();
	public void setCurrentProcess(IProcess proc);
	
	public int getCurrentInstruction();
	public void setCurrentInstruction(int instruc);
	
	public ProcessState executeNextInstruction();
	
	public void setRegisterValue(int i, int val);
	
	public int getRegisterValue(int i);
	
}
