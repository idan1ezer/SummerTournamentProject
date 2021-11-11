package model;

public class Tennis extends Game {
	final private int MAX_SCORE=7;
	private int diff;
	private int totalScoreA = 0;
	private int totalScoreB = 0;

	public Tennis() {
		super();
		baseParts = 3;
		extraParts = 2;
		this.diff = 3;
	}

	@Override
	public int addScoreToTeams(int scoreA, int scoreB) throws Exception {
		checkScoreRules(scoreA, scoreB);
		if (scoreA > scoreB) {
			addScoreToTeam(teamA, 1);
			addScoreToTeam(teamB, 0); 
		} else {
			addScoreToTeam(teamA, 0);
			addScoreToTeam(teamB, 1);
		}
		if (teamA.getScore().size() >= baseParts) {
			if (checkForWinner(teamA.getScore().size())) 
				return 99;
		}
		return teamA.getScore().size();
	}

	private boolean checkScoreRules(int scoreA, int scoreB) throws Exception {
		if ((scoreA >= 0 && scoreA <= MAX_SCORE) && (scoreA >= 0 && scoreA <= MAX_SCORE)) {
			if (scoreA == 6 || scoreB == 6) 
				return true; 
			else 
			throw new Exception("one of the score must be equal to 6");
		}
		else
			throw new Exception("the score entered is not possible, fix the number");
	}

	private void addScoreToTeam(Participant team, int score) {
		if (team.getScore().size() <= baseParts + extraParts - 1) {
			team.addScore(score);
		}
	}

	@Override
	protected boolean checkForWinner(int part) {
		for (int i = 0; i < teamA.getScore().size(); i++) {
			totalScoreA += teamA.getScore().get(i);
			totalScoreB += teamB.getScore().get(i);
		}
		if (totalScoreA - totalScoreB == diff || totalScoreA - totalScoreB == -diff) {
			setWinnerAndLoser();
			return true;
		}
		if (part == baseParts + extraParts) {
			setWinnerAndLoser();
			return true;
		}
		return false;
	}

	@Override
	protected void setWinnerAndLoser() {
		if (totalScoreA > totalScoreB) {
			winner = teamA;
			looser = teamB;
		} 
		else {
			winner = teamB;
			looser = teamA;
		}
	}

	@Override
	public String toString() {
		return "Tennis";
	}

	@Override
	protected boolean draw() {
		clearScores();
		return false;
	}

}
