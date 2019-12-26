package gym;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import gamerunner.Game;
import gamerunner.GameData;

public class BruteForceRunner {
	private ParameterSet params;
	public BruteForceRunner(ParameterSet params) {
		this.params = params;
	}
	public Map<String, Value> runGamesReturnBest(int popSize, int numAtts) throws IOException, InterruptedException{
		Map<String, Value> bestFound = null;
		double bestScore = -1.0;
		for(int i = 0; i < popSize; i++) {
			Map<String, Value> toTest = this.params.generateRandomProperties();
			this.params.writeToFile(toTest);
			Game newGame = new Game(toTest);
			GameData[] results = newGame.runGamesReturnResults(numAtts);
			double score = 0.0;
			for(GameData gd : results) {
				score += gd.getScore(0);
			}
			score /= toTest.size();
			if(score > bestScore) {
				bestScore = score;
				bestFound = toTest;
			}
			System.out.println("Completed " + (i + 1) + "/" + popSize + " w/ score " + score);
//			for(Parameter p : params.params) {
//				System.out.println(p.getParamName() + "=" + toTest.get(p.getParamName()).getString());
//			}
		}
		return bestFound;
	}
	public void runGamesUntilInterrupt(int popSize) throws IOException, InterruptedException {
		// initialize population
		HashMap<Map<String, Value>, Double> pop = new HashMap<Map<String, Value>, Double>();
		for(int i = 0; i < popSize; i++) {
			Map<String, Value> randProps = this.params.generateRandomProperties();
			pop.put(randProps, 0.0);
		}
		// run games (or until max has been run for each pop)
		for(int i = 1; i <= Constants.MAX_BRUTEFORCE_GAMES_PER_INDIVIDUAL; i++) {
			// run 1 game for each
			Map<String, Value> bestProps = null;
			double bestScore = Double.NEGATIVE_INFINITY;
			for(Map<String, Value> props : pop.keySet()) {
				Game newGame = new Game(props);
				GameData newGameGD = newGame.runGameReturnResults();
				double oldAvg = pop.get(props);
				double newAvg = (oldAvg * (i - 1) + newGameGD.getScore(0)) / i;
				pop.put(props, newAvg);
				if(newAvg > bestScore) {
					bestScore = newAvg;
					bestProps = props;
				}
			}
			// find & print out current best (if it's changed) every X generations
			if(i == 1 || i == Constants.MAX_BRUTEFORCE_GAMES_PER_INDIVIDUAL || (i) % Constants.PRINT_EVERY_X_GENERATIONS == 0) {
				DecimalFormat dfmt = new DecimalFormat(",000.0");
				System.out.println("===== CURRENT BEST AFTER " + i + " GAMES w/ score " + dfmt.format(bestScore) + " " + Constants.propertiesMapToStringTuple(bestProps) + " =====");
				TreeMap popSorted = Constants.sortMapByValue(pop);
				int num = 0;
				for (Iterator it = popSorted.keySet().iterator(); it.hasNext();) {
					Map<String, Value> key = (Map<String, Value>) it.next();
		            System.out.println((num + 1) + ". " + Constants.propertiesMapToStringTuple(key) + " - " + pop.get(key)); 
		            if(++num >= 10) {
		            	break;
		            }
				}
			}
		}
	}
}
