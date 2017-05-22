
public class SimProcessor implements IProcessor{

	private IRandomValueGenerator random;
	private IProcess currentProcess;
	private int[] registers;
	private int currInstruction;
	
	private final int NUM_REGISTERS = 4;
	
	public SimProcessor(IRandomValueGenerator randGen)
	{
		registers = new int[NUM_REGISTERS]; 
		random = randGen;
	}

	
	@Override
	public IProcess getCurrentProcess() {
		return currentProcess;
	}
	@Override
	public void setCurrentProcess(IProcess proc) {
		currentProcess = proc;
		
	}
	@Override
	public int getCurrentInstruction() {
		return currInstruction;
	}
	@Override
	public void setCurrentInstruction(int instruc) {
		currInstruction = instruc;
		
	}
	@Override
	public ProcessState executeNextInstruction() {
		return currentProcess.execute(currInstruction++);
	}
	
	@Override
	public void setRegisterValue(int registerID, int registerVal) {
		registers[registerID] = registerVal;
		
	}
	@Override
	public int getRegisterValue(int i) {
		return random.getNextInt();
	}

}
