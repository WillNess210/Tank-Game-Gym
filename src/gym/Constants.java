package gym;

import java.util.Random;

public class Constants {
	public static final double mutateOdds = 0.1;
	public static final boolean shouldMutate(Random r) {
		return r.nextDouble() < mutateOdds;
	}
	public static final double doubleMutateRange = 0.25; // radius of mutate range for a [min, max] double value
	public static final double integerMutateRange = doubleMutateRange; // raidus of mutate range for a [min, max] integer value
	
	public static final int populationSize = 200;
	public static final int numGenerations = 100;
	
	public static final String PROPERTIES_FILE_PATH = "game_env/";
}
