package gym;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParameterSet {
	ArrayList<Parameter> params;
	public ParameterSet() {
		this.params = new ArrayList<Parameter>();
	}
	public void addParam(String paramName, double min, double max) {
		this.params.add(new Parameter(paramName, new ValueDouble(min), new ValueDouble(max)));
	}
	public void addParam(String paramName, int min, int max) {
		this.params.add(new Parameter(paramName, new ValueInteger(min), new ValueInteger(max)));
	}
	public ArrayList<Map<String, Value>> generateSpaced(int maxResolutionPerParameter){
		ArrayList<Map<String, Value>> toReturn = new ArrayList<Map<String, Value>>();
		
		return toReturn;
	}
	public Map<String, Value> generateRandomProperties(){
		Map<String, Value> props = new HashMap<String, Value>();
		for(Parameter p : this.params) {
			props.put(p.getParamName(), p.getRandomValue());
		}
		return props;
	}
	public void writeToFile(Map<String, Value> props) throws IOException {
		// PROPERTIES_PATH
		FileWriter fileWriter = new FileWriter(Constants.PROPERTIES_PATH);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for(Parameter p : this.params) {
			printWriter.print(p.getPropertyFileLine(props.get(p.getParamName())) + "\n");
		}
		printWriter.close();
	}
	public Map<String, Value> mate(Map<String, Value> p1, Map<String, Value> p2){
		Map<String, Value> child = new HashMap<String, Value>();
		int crossOverIndex = Constants.r.nextInt(this.params.size() >= 1 ? this.params.size() - 1 : this.params.size());
		for(int i = 0; i < this.params.size(); i++) {
			Parameter p = this.params.get(i);
			String paramName = p.getParamName();
			Value nextVal = null;
			Value valMin = p.getValMin();
			Value valMax = p.getValMax();
			if(Constants.shouldAvg()) {
				Value p1Val = p1.get(paramName);
				Value p2Val = p2.get(paramName);
				nextVal = p1Val.mate(p2Val, valMin, valMax);
			}else if(i <= crossOverIndex) {
				nextVal = p1.get(paramName).mutateWithOdds(valMin, valMax);
			}else{
				nextVal = p2.get(paramName).mutateWithOdds(valMin, valMax);
			}
			child.put(paramName, nextVal);
		}
		return child;
	}
}
