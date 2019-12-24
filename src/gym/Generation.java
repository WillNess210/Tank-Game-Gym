package gym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Generation {
	private ArrayList<Population> generation;
	public Generation() {
		this.generation = new ArrayList<Population>();
	}
	private void generateRandom(ParameterSet params) {
		this.generation.clear();
		for(int i = 0; i < Constants.populationSize; i++) {
			this.generation.add(new Population(params));
		}
		this.runGenerationGames();
	}
	private void runGenerationGames() {
		for(Population pop : this.generation) {
			pop.getScore();
		}
	}
	public Population getBest(ParameterSet params, Random r) {
		this.generateRandom(params);
		Population bestFound = null;
		for(int i = 0; i < Constants.numGenerations; i++) {
			ArrayList<Population> nextGeneration = new ArrayList<Population>();
			// TODO generate new population
			for(int j = 0; j < Constants.populationSize; j++) {
				int i1 = r.nextInt(this.generation.size());
				int i2 = r.nextInt(this.generation.size());
				while(i1 == i2) {
					i2 = r.nextInt(this.generation.size());
				}
				Population p1 = this.generation.get(i1);
				Population p2 = this.generation.get(i2);
				nextGeneration.add(p1.mate(p2, r));
			}
			Collections.sort(nextGeneration);
			this.generation = nextGeneration;
			if(bestFound == null || this.generation.get(0).getScore() > bestFound.getScore()) {
				bestFound = this.generation.get(0);
			}
		}
		return bestFound;
	}
}
