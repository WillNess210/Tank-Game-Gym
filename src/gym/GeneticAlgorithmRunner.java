package gym;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import gamerunner.Game;
import gamerunner.GameData;

public class GeneticAlgorithmRunner {
	private ParameterSet ps;
	public GeneticAlgorithmRunner(ParameterSet ps) {
		this.ps = ps;
	}
	public Map<String, Value> runGamesReturnBest(int popSize, int numGenerations, int matchesPerGenerationPerIndiv) throws IOException, InterruptedException{
		Map<String, Value> bestFound = null;
		double bestFoundScore = Double.NEGATIVE_INFINITY;
		HashMap<Map<String, Value>, Double> pop = new HashMap<Map<String, Value>, Double>();
		// init first population
		for(int i = 0; i < popSize; i++) {
			pop.put(this.ps.generateRandomProperties(), -1.0);
		}
		// generate matchesPerGEnerationPerIndiv worth of random map seeds for every player to use
		long[] mapSeeds = new long[matchesPerGenerationPerIndiv];
		Random r = new Random();
		for(int j = 0; j < mapSeeds.length; j++) {
			mapSeeds[j] = r.nextLong();
		}
		for(int i = 1; i <= numGenerations; i++) {
			// play games & load avg into map
			for(Map<String, Value> indiv : pop.keySet()) {
				this.ps.writeToFile(indiv);
				Game newGame = new Game(indiv);
				GameData[] results = newGame.runGamesReturnResults(matchesPerGenerationPerIndiv, mapSeeds);
				double score = 0.0;
				for(GameData gd : results) {
					score += gd.getScore(0);
				}
				score /= matchesPerGenerationPerIndiv;
				pop.put(indiv, score);
			}
			// sort by double value
			TreeMap<Map<String, Value>, Double> popSortedTM = Constants.sortMapByValue(pop);
			ArrayList<Map<String, Value>> popSorted = new ArrayList<Map<String, Value>>();
			for (Iterator it = popSortedTM.keySet().iterator(); it.hasNext();) {
				Map<String, Value> key = (Map<String, Value>) it.next();
				popSorted.add(key);
			}
			// if max > bestFound, then update bestFound
			double thisGenerationBestScore = pop.get(popSorted.get(0));
			boolean newBestFound = false;
			if(thisGenerationBestScore > bestFoundScore) {
				bestFoundScore = thisGenerationBestScore;
				bestFound = popSorted.get(0);
				newBestFound = true;
			}
			// print to console top 100
			DecimalFormat dfmt = new DecimalFormat(",000.0");
			System.out.println("===== CURRENT BEST AFTER " + i + " GENERATIONS w/ score " + dfmt.format(bestFoundScore) + Constants.propertiesMapToStringTuple(bestFound) + " =====");
			for(int j = 0; j < 100 && j < popSize; j++) {
				System.out.println((j + 1) + ". " + Constants.propertiesMapToStringTuple(popSorted.get(j)) + " - " + pop.get(popSorted.get(j)));
			}
			if(newBestFound) {
				System.out.println("A new best found happened this generation.");
			}
			// if at end, break for loop
			if(i == numGenerations) break;
			
			///////// create new population
			HashMap<Map<String, Value>, Double> nextPop = new HashMap<Map<String, Value>, Double>();
			for(int j = 0; j < popSize; j++) {
				int[] indcs = Constants.getGAIndices(popSize);
				Map<String, Value> p1 = popSorted.get(indcs[0]);
				Map<String, Value> p2 = popSorted.get(indcs[1]);
				Map<String, Value> child = this.ps.mate(p1, p2);
				nextPop.put(child, -1.0);
			}
			pop = nextPop;
		}
		return bestFound;
	}
}
