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
		for(Parameter p : this.params) {
			String paramName = p.getParamName();
			Value valMin = p.getValMin();
			Value valMax = p.getValMax();
			Value p1Val = p1.get(paramName);
			Value p2Val = p2.get(paramName);
			Value nextVal = p1Val.mate(p2Val, valMin, valMax);
			child.put(paramName, nextVal);
		}
		return child;
	}
}
