package model;

public class Basketball extends Game {
	
	private boolean overtime;

	public Basketball() {
		super();
		baseParts = 4;
		extraParts = 1;
	}

	@Override
	public int addScoreToTeams(int scoreA, int scoreB) throws Exception {
		addScoreToTeam(teamA, scoreA);
		addScoreToTeam(teamB, scoreB);
		if (teamA.getScore().size() >= baseParts) {
			if (checkForWinner(teamA.getScore().size())) 
				return 99;
		}
		return teamA.getScore().size();
	}
	
	private void addScoreToTeam(Participant team, int score) throws Exception {
		if (score >= 0 && score < 100) {
			if (team.getScore().size() <= baseParts+1) 
				team.addScore(score);	
		} 
		else 
			throw new Exception("the score entered is not possible, fix the number");	
	}

	protected boolean checkForWinner(int partNum) {
		if (sumParts(teamA, partNum)!=sumParts(teamB,partNum )) {
			setWinnerAndLoser();
			return true;
		}
		else if (teamA.getScore().get(baseParts - 1) == teamB.getScore().get(baseParts - 1)) 
			overtime = true;
		return false;
	}
	
	protected void setWinnerAndLoser() {
		if (overtime)
			checkAndSet(sumParts(teamA,baseParts+extraParts),sumParts(teamB,baseParts+extraParts));
		else
			checkAndSet(sumParts(teamA,baseParts),sumParts(teamB,baseParts));
	}

	private int sumParts(Participant team, int partsNum) {
		int totalScore =0;
		for (int i = 0; i < partsNum; i++) {
			totalScore+=team.getScore().get(i);
		}	
		return totalScore;
	}

	private void checkAndSet(int finalScoreTeamA, int finalScoreTeamB) {
		if (finalScoreTeamA > finalScoreTeamB) {
				winner = teamA;
				looser = teamB;
		} else {
			winner = teamB;
			looser = teamA;
		}
	}

	@Override
	protected boolean draw() {
		if (overtime&&teamA.getScore().get(baseParts+extraParts-1)==teamB.getScore().get(baseParts+extraParts-1)) {
			clearScores();
			return true;
		}
		clearScores();
		return false;
	}
	
	@Override
	public String toString() {
		return "Basketball";
	}
}
