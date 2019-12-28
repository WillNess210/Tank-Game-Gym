package gym;

import java.util.Random;

public class ValueDouble implements Value<Double>{
	private double value;
	public ValueDouble(double val) {
		this.value = val;
	}
	@Override
	public String getString() {
		return this.value + "";
	}
	@Override
	public Double getValue() {
		return this.value;
	}
	@Override
	public Value<Double> genRandom(Value<Double> valMin, Value<Double> valMax) {
		return new ValueDouble((((new Random()).nextDouble()) * (valMax.getValue() - valMin.getValue())) + valMin.getValue());
	}
	@Override
	public Value<Double> mate(Value<Double> partner, Value<Double> valMin, Value<Double> valMax) {
		double nextVal = (this.value + partner.getValue())/2.0;
		if(Constants.shouldMutate()) {
			Value<Double> nv = new ValueDouble(nextVal);
			return nv.mutate(valMin, valMax);
		}
		return new ValueDouble(nextVal);
	}
	@Override
	public Value<Double> mutate(Value<Double> valMin, Value<Double> valMax) {
		double mutatedVal = this.value + ((Constants.r.nextBoolean() ? (1.0) : (-1.0)) * Constants.r.nextDouble() * Constants.mutateRange * (valMax.getValue() - valMin.getValue()));
		mutatedVal = Math.min(valMax.getValue(), Math.max(valMin.getValue(), mutatedVal));
		return new ValueDouble(mutatedVal);
	}
	@Override
	public Value<Double> mutateWithOdds(Value<Double> valMin, Value<Double> valMax) {
		if(Constants.shouldMutate()) {
			return this.mutate(valMin, valMax);
		}
		return new ValueDouble(this.value);
	}
}
