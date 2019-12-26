package gamerunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import gym.Value;

public class GameData {
	private int winner;
	private int[] scores;
	private Map<String, Value> props;
	public GameData(String filepath_to_replay_file, Map<String, Value> props) throws FileNotFoundException {
		this.props = props;
		// parse replay file
		Scanner sc = new Scanner(new File(filepath_to_replay_file));
		this.winner = sc.nextInt();
		this.scores = new int[2];
		this.scores[0] = sc.nextInt();
		this.scores[1] = sc.nextInt();
	}
	public int getWinner() {
		return this.winner;
	}
	public int getScore(int player) {
		return this.scores[player];
	}
	public int[] getScores() {
		return this.scores;
	}
}
