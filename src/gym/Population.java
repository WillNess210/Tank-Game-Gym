package gym;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Population implements Comparable<Population>{
	private Map<String, Value> pop;
	private double score = Double.NaN;
	public Population(ParameterSet params) {
		this.pop = params.generateRandomSet();
	}
	public Population(Map<String, Value> pop) {
		this.pop = pop;
	}
	public double getScore() {
		if(this.score != Double.NaN) {
			return this.score;
		}
		this.score = this.runGame();
		return this.score;
	}
	
	public Map<String, Value> getMap(){
		return this.pop;
	}
	
	public Population mate(Population b, Random r) {
		Map<String, Value> nextPop = new HashMap<String, Value>();
		for(String key : this.pop.keySet()) {
			nextPop.put(key, r.nextBoolean() ? this.pop.get(key).mate(, r)); // TODO try averaging instead of choosing one or other
		}
		return new Population(nextPop);
	}
	
	// runs game and returns score
	public double runGame() {
		return -1.0;
	}
	@Override
	public int compareTo(Population o) {
		return this.getScore() > o.getScore() ? 1 : -1;
	}
}