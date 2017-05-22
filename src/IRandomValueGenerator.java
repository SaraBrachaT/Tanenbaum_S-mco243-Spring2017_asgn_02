
public interface IRandomValueGenerator {

	public int getNextInt();
	public boolean getTrueWithProbability(double p);
	public int getNextInt(int limit); //overload
}
