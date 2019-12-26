import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import gamerunner.Game;
import gamerunner.GameData;
import gym.BruteForceRunner;
import gym.Constants;
import gym.ParameterSet;
import gym.Value;

public class Main_BruteForceRunner {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// SETTING UP PARAMETERS
		ParameterSet ps = new ParameterSet();
		ps.addParam("rovers_per_site", 4, 8);
		ps.addParam("stop_spawning_turn", 75, 201);
		
		// RUNNING BRUTE FORCE RUNNER
		BruteForceRunner bfr = new BruteForceRunner(ps);
		// RUN UNTIL EXIT
		bfr.runGamesUntilInterrupt(50);
		
		// RUN MANUALLY
//		Map<String, Value> bestParams = bfr.runGamesReturnBest(30, 100);
//		// PRINTING RESULTS
//		System.out.println("Best Parameter Set Found:");
//		for(String key : bestParams.keySet()) {
//			System.out.println(key + "=" + bestParams.get(key).getString());
//		} 
	}

}
