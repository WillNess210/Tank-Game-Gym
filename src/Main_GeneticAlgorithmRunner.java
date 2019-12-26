import java.io.IOException;

import gym.GeneticAlgorithmRunner;
import gym.ParameterSet;

public class Main_GeneticAlgorithmRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		// SETTING UP PARAMETERS
		ParameterSet ps = new ParameterSet();
		ps.addParam("rovers_per_site", 4, 8);
		ps.addParam("stop_spawning_turn", 75, 201);
		
		GeneticAlgorithmRunner gar = new GeneticAlgorithmRunner(ps);
		// run
		gar.runGamesReturnBest(30, 20, 10);
	}
}
