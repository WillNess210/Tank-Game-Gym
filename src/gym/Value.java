package gym;

import java.util.Map;
import java.util.Random;

public interface Value <T>{
	public default void addToMap(Map<String, Value> m, String param_name) {
		m.put(param_name, this);
	}
	public T getValue();
	public Value<T> mutate(Random r);
	public Value<T> mate(Value<T> b, Random r);
	public String getString();
}
