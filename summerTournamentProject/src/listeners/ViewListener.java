package listeners;



public interface ViewListener {

	public void submitPartScoreInModel(int gameIndex,int scoreTeamA,int scoreTeamB);

	public void addParticipantToModel(String text);

	public void startGameInModel(int gameIndex);

	public void startTournamentInModel(String selectedGame);

}
