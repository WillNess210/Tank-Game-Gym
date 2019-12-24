package gym;

import java.util.ArrayList;
import java.util.Random;

public class ParameterDouble implements Parameter<Double>{
	private double min, max;
	private String name;
	public ParameterDouble(String name, double min, double max) {
		this.min = min;
		this.max = max;
		this.name = name;
	}
	@Override
	public Value<Double> getRandom() {
		return new ValueDouble(min + r.nextDouble() * (max - min), this.min, this.max);
	}
	
	@Override
	public ArrayList<Double> getParamSet(int size){
		ArrayList<Double> set = new ArrayList<Double>();
		double step = ((Double)this.max - (Double)this.min) / (size * 1.0);
		for(double i = this.min; i <= this.max; i += step) {
			set.add(i);
		}
		return set;
	}
	@Override
	public String getName() {
		return this.name;
	}

}
