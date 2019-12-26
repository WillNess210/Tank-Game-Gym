package gamerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	private void loadPropertiesFile() {
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
	public void clearReplays() throws IOException {
		//ProcessBuilder procB = new ProcessBuilder("rm", "*.log");
		ProcessBuilder procB = new ProcessBuilder("./removeReplays.sh");
		procB.directory(new File(Constants.REPLAY_FOLDER));
		Process proc = procB.start();
		while(proc.isAlive()) {
			BufferedReader programOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while(programOutput.ready()) {
				programOutput.readLine();
				System.out.println(programOutput.readLine());
			}
		}
		proc.destroy();
	}
	private void runGame() throws IOException, InterruptedException {
		ProcessBuilder procB = new ProcessBuilder("java", "-jar", "tank-engine.jar", "bot", "opp");
		procB.directory(new File(Constants.RUN_ENV_LOC));
		Process proc = procB.start();
		while(proc.isAlive()) {
			BufferedReader programOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while(programOutput.ready()) {
				programOutput.readLine();
				//System.out.println(programOutput.readLine());
			}
		}
		BufferedReader programOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		while(programOutput.ready()) {
			programOutput.readLine();
			//System.out.println(programOutput.readLine());
		}
		proc.destroy();
	}
	private GameData getScoresFromReplayFile() throws FileNotFoundException {
		GameData g = new GameData(Constants.LATEST_REPLAY_FILE, this.props);
		return g;
	}
	public GameData runGameReturnResults() throws IOException, InterruptedException {
		this.loadPropertiesFile();
		this.runGame();
		GameData gd = this.getScoresFromReplayFile();
		this.clearReplays();
		return gd;
	}
	public GameData[] runGamesReturnResults(int numGames) throws IOException, InterruptedException {
		this.loadPropertiesFile();
		GameData[] results = new GameData[numGames];
		for(int i = 0; i < numGames; i++) {
			this.runGame();
			results[i] = this.getScoresFromReplayFile();
		}
		this.clearReplays();
		return results;
	}
}
