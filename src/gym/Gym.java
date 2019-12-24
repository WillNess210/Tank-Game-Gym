package gym;

import java.util.ArrayList;
import java.util.Map;

import gamerunner.Game;

import java.util.HashMap;

public class Gym {
	private ParameterSet params;
	private Generation currentGeneration;
	public Gym() {
		this.params = new ParameterSet();
		this.currentGeneration = new Generation();
	}
	public void addParam(String name, int min, int max) {
		this.params.addParam(new ParameterInteger(name, min, max));
	}
	public void addParam(String name, double min, double max) {
		this.params.addParam(new ParameterDouble(name, min, max));
	}
	public void runGeneticAlgorithmAndSetPropertyFile() {
		Population bestFound = this.currentGeneration.getBest(this.params);
		Game toWrite = new Game(bestFound.getMap());
		toWrite.loadPropertiesFile();
	}
}
