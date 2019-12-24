package gym;

import java.util.ArrayList;
import java.util.Random;

public class ParameterInteger implements Parameter<Integer>{
	private int min, max;
	private String name;
	public ParameterInteger(String name, int min, int max) {
		this.min = min;
		this.max = max;
		this.name = name;
	}
	
	@Override
	public Value<Integer> getRandom() {
		return new ValueInteger(this.min + r.nextInt(this.max - this.min + 1), this.min, this.max);
	}

	@Override
	public ArrayList<Integer> getParamSet(int size) {
		ArrayList<Integer> set = new ArrayList<Integer>(size);
		if(min - max + 1 >= size) {
			for(int i = min; i <= max; i++) {
				set.add(i);
			}
		}else {
			int failures = 0;
			while(set.size() < size && failures < size + 10) {
				int toAdd = this.getRandom().getValue();
				if(set.contains(toAdd)) {
					failures++;
				}else {
					set.add(toAdd);
				}
			}
		}
		return set;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
