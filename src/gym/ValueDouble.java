package gym;

import java.util.Random;

public class ValueDouble implements Value<Double>{
	private double val, min, max;
	public ValueDouble(double val, double min, double max) {
		this.val = val;
		this.min = min;
		this.max = max;
	}
	@Override
	public Double getValue() {
		return this.val;
	}
	@Override
	public Value<Double> mutate(Random r) {
		return new ValueDouble(Math.min(Math.max(this.min, (this.val + (r.nextBoolean() ? 1 : -1) * (r.nextDouble() * Constants.doubleMutateRange) * (this.max - this.min))), this.max), this.min, this.max);
	}
	@Override
	public String getString() {
		return "" + this.val;
	}
	@Override
	public Value<Double> mate(Value<Double> b, Random r) {
		return new ValueDouble((this.val + b.getValue())/2.0, this.min, this.max);
	}
}
