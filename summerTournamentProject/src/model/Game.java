package model;

import java.util.Random;

public abstract class Game {
	protected int baseParts;
	protected int extraParts;
	protected Participant teamA;
	protected Participant teamB;
	protected Participant winner;
	protected Participant looser;

	public Game() {
	}

	public void setPlayers(Participant player1, Participant player2) {
		teamA = player1;
		teamB = player2;
	}

	public abstract int addScoreToTeams(int scoreA, int scoreB) throws Exception;

	protected abstract boolean checkForWinner(int part);

	protected abstract boolean draw();

	public void coinFlip() {
		Random rd = new Random();
		boolean coin = rd.nextBoolean();
		if (coin) {
			winner = teamA;
			looser = teamB;
		} else {
			winner = teamB;
			looser = teamA;
		}
	}

	public void reverseScoreAfterException() {
		if (teamA.getScore().size() > teamB.getScore().size()) 
			teamA.getScore().remove(teamA.getScore().lastElement()); 
		else if (teamA.getScore().size() < teamB.getScore().size()) 
			teamB.getScore().remove(teamB.getScore().lastElement());	
	}

	protected void clearScores() {
		teamA.getScore().clear();
		teamB.getScore().clear();
	}

	@Override
	public abstract String toString();
	
	protected abstract void setWinnerAndLoser();

	public Participant getTeamA() {
		return teamA;
	}

	public Participant getTeamB() {
		return teamB;
	}

	public int getBaseParts() {
		return baseParts;
	}

	public int getExtraParts() {
		return extraParts;
	}

	public Participant getWinner() {
		return winner;
	}

	public Participant getLoser() {
		return looser;
	}

}
