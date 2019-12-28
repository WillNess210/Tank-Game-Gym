import java.io.IOException;

import gym.GeneticAlgorithmRunner;
import gym.ParameterSet;

public class Main_GeneticAlgorithmRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		// SETTING UP PARAMETERS
		ParameterSet ps = new ParameterSet();
		ps.addParam("rovers_per_site", 0, 50);
		ps.addParam("stop_spawning_turn", 0, 201);
		ps.addParam("base_rovers", 0, 50);
		
		GeneticAlgorithmRunner gar = new GeneticAlgorithmRunner(ps);
		// run
		gar.runGamesReturnBest(100, 50, 30);
	}
}
