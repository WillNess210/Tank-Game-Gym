package gym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ParameterSet {
	private Random r = new Random();
	private ArrayList<Parameter> set;
	public ParameterSet() {
		this.set = new ArrayList<Parameter>();
	}
	public void addParam(Parameter p) {
		this.set.add(p);
	}
	public Map<String, Value> generateRandomSet(){
		Map<String, Value> map = new HashMap<String, Value>();
		for(Parameter p : this.set) {
			map.put(p.getName(), p.getRandom());
		}
		return map;
	}
	public Map<String, Value> mate(Map<String, Value> s1, Map<String, Value> s2){
		Map<String, Value> result = new HashMap<String, Value>();
		for(String key : s1.keySet()) {
			result.put(key, this.r.nextBoolean() ? s1.get(key) : s2.get(key));
		}
		this.mutateSet(result);
		return result;
	}
	public void mutateSet(Map<String, Value> s) {
		for(String key : s.keySet()) {
			if(Constants.shouldMutate(this.r)) {
				s.put(key, s.get(key).mutate(this.r));
			}
		}
	}
}
