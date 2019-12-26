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
		double ourAvg = (this.value + partner.getValue())/2.0;
		if(Constants.shouldMutate()) {
			double mutatedVal = ourAvg + ((Constants.r.nextBoolean() ? (1.0) : (-1.0)) * Constants.r.nextDouble() * Constants.mutateRange * (valMax.getValue() - valMin.getValue()));
			ourAvg = Math.min(valMax.getValue(), Math.max(valMin.getValue(), mutatedVal));
		}
		return new ValueDouble(ourAvg);
	}
}
