package gym;

import java.util.Random;

public class ValueInteger implements Value<Integer>{
	private int val, min, max;
	
	public ValueInteger(int val, int min, int max) {
		this.val = val;
		this.min = min;
		this.max = max;
	}

	@Override
	public Integer getValue() {
		return this.val;
	}

	@Override
	public Value<Integer> mutate(Random r) {
		int range = (int) (r.nextDouble() * Constants.integerMutateRange);
		return new ValueInteger(Math.min(this.max, Math.max(this.min, r.nextBoolean() ? (this.val - range) : (this.val + range))), this.min, this.max);
	}

	@Override
	public String getString() {
		return "" + this.val;
	}

	@Override
	public Value<Integer> mate(Value<Integer> b, Random r) {
		return new ValueInteger((this.val + b.getValue())/2, this.min, this.max);
	}
}
