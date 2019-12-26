package gym;

import java.util.Random;

public class ValueInteger implements Value<Integer>{
	private int value;
	public ValueInteger(int val) {
		this.value = val;
	}
	@Override
	public String getString() {
		return this.value + "";
	}
	@Override
	public Integer getValue() {
		return this.value;
	}
	@Override
	public Value<Integer> genRandom(Value<Integer> valMin, Value<Integer> valMax) {
		return new ValueInteger((new Random()).nextInt(valMax.getValue() - valMin.getValue() + 1) + valMin.getValue());
	}
	@Override
	public Value<Integer> mate(Value<Integer> partner, Value<Integer>  valMin, Value<Integer>  valMax) {
		int ourAvg = (int) ((this.getValue() + partner.getValue())/2);
		if(Constants.shouldMutate()) {
			int mutatedVal = ourAvg + (int) ((Constants.r.nextBoolean() ? (1.0) : (-1.0)) * Constants.r.nextDouble() * Constants.mutateRange * (valMax.getValue() - valMin.getValue()));
			ourAvg = Math.min(valMax.getValue(), Math.max(valMin.getValue(), mutatedVal));
		}
		return new ValueInteger(ourAvg);
	}
}
