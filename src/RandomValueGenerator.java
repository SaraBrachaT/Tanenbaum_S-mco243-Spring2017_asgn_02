import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{

	private Random randGen;
	
	public RandomValueGenerator()
	{
		randGen = new Random(System.currentTimeMillis());
	}
	public int getNextInt() {
		return randGen.nextInt();
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		if(getNextInt(100)+1 <= (p*100)) return true;
		return false;
	}

	@Override
	public int getNextInt(int limit) {
		return randGen.nextInt(limit);
	}

}
