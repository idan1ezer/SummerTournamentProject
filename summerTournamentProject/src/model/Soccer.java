package model;

public class Soccer extends Game {
	private boolean overtime;
	private boolean penalties;

	public Soccer() {
		super();
		baseParts = 2;
		extraParts = 2;
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
		if (score >= 0) {
			if (team.getScore().size() == 0)
				team.addScore(score);
			else if (team.getScore().size() == 1) {
				if (score >= team.getScore().get(0))
					team.addScore(score);
				else
					throw new Exception("Score must be equal higher than first half");
			}
			else if (team.getScore().size() == 2) {
				if (score >= team.getScore().get(1))
					team.addScore(score);
				else 
					throw new Exception("Score must be equal higher than second half");
			}
			else if (team.getScore().size() == 3) {
				if (score <= 5)
					team.addScore(score);
				else 
					throw new Exception("Penalties score must be lower or equal to 5");
			}
		}
	}

	protected boolean checkForWinner(int partNum) {
		int fixPartNum = partNum - 1;
		if (teamA.getScore().get(fixPartNum) != teamB.getScore().get(fixPartNum)) {
			setWinnerAndLoser();
			return true;
		}
		else if (teamA.getScore().get(baseParts - 1) == teamB.getScore().get(baseParts - 1)) 
			overtime = true;
		if (teamA.getScore().size() == 3 && teamA.getScore().get(baseParts) == teamB.getScore().get(baseParts)) {
			overtime = false;
			penalties = true;
		}
		return false;
	}

	protected void setWinnerAndLoser() {// the number points to the score of the X part.
		if (penalties) 
			checkAndSet(teamA.getScore().get(3), teamB.getScore().get(3));
		else if (overtime)
			checkAndSet(teamA.getScore().get(2), teamB.getScore().get(2));
		else
			checkAndSet(teamA.getScore().get(1), teamB.getScore().get(1));
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
		if (penalties && teamA.getScore().get(baseParts + extraParts - 1) == teamB.getScore().get(baseParts + extraParts - 1)) {
			clearScores();
			return true;
		}
		clearScores();
		return false;
	}
	
	@Override
	public String toString() {
		return "Soccer";
	}

}
