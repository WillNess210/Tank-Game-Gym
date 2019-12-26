package gym;

public interface Value <T>{
	public String getString();
	public T getValue();
	public Value<T> mate(Value<T> partner, Value<T> valMin, Value<T> valMax);
	public Value<T> genRandom(Value<T> valMin, Value<T> valMax);
}
