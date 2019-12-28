package gym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Constants {
	public static final String RUN_ENV_LOC = "/home/will/Documents/Competitions/TankGame/Tank-Game-Gym/run_env";
	public static final String PROPERTIES_PATH = RUN_ENV_LOC + "/bot/parameters.properties";
	public static final String REPLAY_FOLDER = RUN_ENV_LOC + "/replays";
	public static final String LATEST_REPLAY_FILE = REPLAY_FOLDER + "/latest_replay.log";
	
	public static final int MAX_BRUTEFORCE_GAMES_PER_INDIVIDUAL = 200;
	public static final int PRINT_EVERY_X_GENERATIONS = 1;
	
	public static final Random r = new Random();
	public static final double mutateOdds = 0.05;
	public static final double mutateRange = 0.5;
	public static final boolean shouldMutate() {
		return r.nextDouble() <= mutateOdds;
	}
	public static final double avgOdds = 0.33;
	public static final boolean shouldAvg() {
		return r.nextDouble() <= avgOdds;
	}
	
	
	public static final int getGAIndex(int size) {
		return (int) Math.floor((r.nextDouble() * r.nextDouble() * r.nextDouble()) * size);
	}
	public static final int[] getGAIndices(int size) {
		int indOne =  getGAIndex(size);
		int indTwo = getGAIndex(size);
		while(indOne == indTwo) indTwo = getGAIndex(size);
		int[] inds = new int[2];
		inds[0] = indOne;
		inds[1] = indTwo;
		return inds;
	}
	
	public static final String propertiesMapToStringTuple(Map<String, Value> props) {
		String toReturn = "(";
		String[] keys = new String[props.size()];
		int load = 0;
		for(String key : props.keySet()) {
			keys[load++] = key;
		}
		for(int i = 0; i < keys.length; i++) {
			if(i != 0) {
				toReturn += ", ";
			}
			toReturn += keys[i] + "=" + props.get(keys[i]).getString();
		}
		return toReturn + ")";
	}
	public static final TreeMap<Map<String, Value>, Double> sortMapByValue(HashMap<Map<String, Value>, Double> map){
		Comparator<Map<String, Value>> comparator = new ValueComparator(map);
		//TreeMap is a map sorted by its keys. 
		//The comparator is used to sort the TreeMap by keys. 
		TreeMap<Map<String, Value>, Double> result = new TreeMap<Map<String, Value>, Double>(comparator);
		result.putAll(map);
		return result;
	}
}

//a comparator that compares Strings
class ValueComparator implements Comparator<Map<String, Value>>{

	HashMap<Map<String, Value>, Double>  map = new HashMap<Map<String, Value>, Double>();

	public ValueComparator(HashMap<Map<String, Value>, Double>  map){
		this.map.putAll(map);
	}

	@Override
	public int compare(Map<String, Value> s1, Map<String, Value> s2) {
		if(map.get(s1) >= map.get(s2)){
			return -1;
		}else{
			return 1;
		}	
	}
}
