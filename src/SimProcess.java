
public class SimProcess implements IProcess{

	private IRandomValueGenerator iRandom;
	private int pid;
	private String procName;
	private int totalInstructions;
	
	public SimProcess(int pID, String processName, int totInstructs, IRandomValueGenerator rand)
	{
		pid = pID;
		procName = processName;
		totalInstructions = totInstructs;
		iRandom = rand;
	}
	@Override
	public int getPid() {
		return pid;
	}

	@Override
	public String getProcName() {
		return this.procName;
	}

	@Override
	public ProcessState execute(int i) {
		System.out.println(display(i));
		if(i >= this.totalInstructions) return ProcessState.FINISHED;
		else
		{
			if(iRandom.getTrueWithProbability(.15)) return ProcessState.BLOCKED;
			else return ProcessState.READY;
		}
	}

	private String display(int i)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\tPID: ");
		sb.append(pid);
		sb.append("\t\tProcess Name: ");
		sb.append(procName);
		sb.append("\t\tExecuting Instruction Number: ");
		sb.append(i);
		
		return sb.toString();
	}
	
}
