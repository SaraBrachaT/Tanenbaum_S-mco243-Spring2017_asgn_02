
public interface IProcess {

	public int getPid();
	public String getProcName();
	public ProcessState execute(int i);
}
