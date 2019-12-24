package gamerunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import gym.Constants;
import gym.Value;

public class Game {
	private Map<String, Value> props;
	public Game(Map<String, Value> props) {
		this.props = props;
	}
	public void loadPropertiesFile() {
		Properties prop = new Properties();
		for(String key : this.props.keySet()) {
			prop.setProperty(key, this.props.get(key).getString());
		}
		try (OutputStream output = new FileOutputStream(Constants.PROPERTIES_PATH)) {
			prop.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void runGame() throws IOException {
		ProcessBuilder procB = new ProcessBuilder("pwd");
		Process proc = procB.start();
		while(proc.isAlive());
		proc.destroy();
	}
	public double getScoreFromGame() {
		this.loadPropertiesFile();
		
		return -1.0;
	}
}
