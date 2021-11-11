package model;

import java.util.Vector;

public class Participant {
	private String name;
	private Vector<Integer> score = new Vector<Integer>();
	
	public Participant(String name) {
		this.name = name;
	}
	
	public void addScore(int score) {
		this.score.add(score);
	}

	public String getName() {
		return name;
	}

	public Vector<Integer> getScore() {
		return score;
	}

	
	
	
	
	
	
}
