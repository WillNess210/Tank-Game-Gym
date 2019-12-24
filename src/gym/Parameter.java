package gym;

import java.util.ArrayList;
import java.util.Random;

public interface Parameter<T> {
	public static final Random r = new Random();
	
	public String getName();
	public Value<T> getRandom();
	public default ArrayList<T> getParamSet(int size){
		ArrayList<T> set = new ArrayList<T>(size);
		int failures = 0;
		while(set.size() < size && failures < size + 10) {
			T toAdd = this.getRandom().getValue();
			if(set.contains(toAdd)) {
				failures++;
			}else {
				set.add(toAdd);
			}
		}
		return set;
	}
}
