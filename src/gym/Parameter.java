package gym;

public class Parameter {
	private String paramName;
	private Value valMin, valMax;
	
	public Parameter(String paramName, Value min, Value max) {
		this.paramName = paramName;
		this.valMin = min;
		this.valMax = max;
	}
	public Value getRandomValue() {
		return valMin.genRandom(valMin, valMax);
	}
	public String getPropertyFileLine(Value v) {
		return this.paramName + "=" + v.getString();
	}
	public String getParamName() {
		return this.paramName;
	}
	public Value getValMin() {
		return valMin;
	}
	public Value getValMax() {
		return valMax;
	}
}
