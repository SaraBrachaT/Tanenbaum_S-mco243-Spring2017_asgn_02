import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	private static IRandomValueGenerator randGen;
	private static IProcessor processor;
	private static ProcessControlBlock currentPCB;
	private static Queue<ProcessControlBlock> readyProcesses;
	private static List<ProcessControlBlock> blockedProcesses;
	private static int counter;
	
	
	private final static int QUANTUM = 5;
	private final static int NUM_PROCESSES = 10;
	
	public static void main(String[] args)
	{
		randGen = new RandomValueGenerator();
		processor = new SimProcessor(randGen);
		readyProcesses = new LinkedList<ProcessControlBlock>();
		blockedProcesses = new LinkedList<ProcessControlBlock>();
		ProcessState processState = null;
		for(int i = 1; i <= NUM_PROCESSES; i++)
		{
			readyProcesses.add(new ProcessControlBlock(new SimProcess(i, "Process " + i, randGen.getNextInt(300) + 100, randGen)));
		}
		
		setCurrentPCB(readyProcesses.peek());
				
		counter = 0;
		for(int i = 1; i <= 3000; i++) //if pcb != null
		{
			setCurrentPCB(readyProcesses.peek());
			processor.setCurrentProcess(currentPCB.getProcess());
			processor.setCurrentInstruction(currentPCB.getCurrentInstruction());
			System.out.println("Step " + i);
			
			//contextSwitch() after each if
			if(processState == ProcessState.FINISHED)
			{
				System.out.println("***Process Completed***");
				contextSwitch();
				readyProcesses.poll();
				contextSwitchPart2();
				counter = 0;
				processState = null;
			}
			else if(processState == ProcessState.BLOCKED)
			{
				System.out.println("***Process Blocked***");
				contextSwitch();
				blockedProcesses.add(readyProcesses.poll());
				contextSwitchPart2();
				counter=0;
				processState = null;
			}
			else if(counter == QUANTUM)
			{
				System.out.println("***Quantum Expired***");
				contextSwitch();
				readyProcesses.add(readyProcesses.poll());
				contextSwitchPart2();
				counter = 0;
			}
			else
			{
				processState = processor.executeNextInstruction();
				currentPCB.setCurrentInstruction(processor.getCurrentInstruction());
				counter++;
				ArrayList<ProcessControlBlock> remove = new ArrayList<ProcessControlBlock>(); //otherwise get ConcurrentModificationException for trying to remove within loop 
				for(ProcessControlBlock pcb : blockedProcesses)
				{
					if(randGen.getTrueWithProbability(.3)) 
					{
						remove.add(pcb);
						readyProcesses.add(pcb);
					}
				}
				blockedProcesses.removeAll(remove);
			}

		}
		
	}
	
	public static void contextSwitch()
	{
		//processor.setCurrentInstruction(processor.getCurrentInstruction() + 1);
		currentPCB.setCurrentInstruction(processor.getCurrentInstruction());
		
		System.out.println("Context Switch: Saving Process: " + currentPCB.getProcess().getPid());
		System.out.println("\tInstruction: " + currentPCB.getCurrentInstruction() + "- R1: " + currentPCB.getRegister1()
		+ ", R2: " + currentPCB.getRegister2() + ", R3: " + currentPCB.getRegister3() + 
		", R4: " + currentPCB.getRegister4());
	}
	//TODO I can put my toStrings inside?
	//TODO IF FINISHED REMOVE COMPLETELY
	//TODO Does this make any sense???
	//TODO Print step number
	public static void contextSwitchPart2()
	{
		if(readyProcesses.peek() != null) //didn't finish all processes
		{
			setCurrentPCB(readyProcesses.peek());
			processor.setCurrentProcess(currentPCB.getProcess());
			processor.setCurrentInstruction(currentPCB.getCurrentInstruction());
			System.out.println("Context Switch: Restoring Process: " + currentPCB.getProcess().getPid());
			System.out.println("\tInstruction: " + currentPCB.getCurrentInstruction() + "- R1: " + currentPCB.getRegister1()
			+ ", R2: " + currentPCB.getRegister2() + ", R3: " + currentPCB.getRegister3() + 
			", R4: " + currentPCB.getRegister4());
		}
		else
		{
			if(blockedProcesses.isEmpty())
			{
				System.out.println("All processes completed");
			}
			else
			{
				 System.out.println("Ready processes completed, there are blocked processes");
			}
			System.exit(0);
		}

	}
	
	public static void setCurrentPCB(ProcessControlBlock pcb) {
		currentPCB = pcb;
		currentPCB.setCurrentInstruction(pcb.getCurrentInstruction());
		currentPCB.setRegister1(randGen.getNextInt());
		currentPCB.setRegister2(randGen.getNextInt());
		currentPCB.setRegister3(randGen.getNextInt());
		currentPCB.setRegister4(randGen.getNextInt());
	}

}
