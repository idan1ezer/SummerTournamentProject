//idan ezer 315963660 & tor hanan 205665862
package model;

import java.util.Vector;

import javax.swing.JOptionPane;

import listeners.TournamentListener;

public class Tournament {
	final private int TOURNAMENT_PLAYERS_NUMBER=8;
	private static Vector<TournamentListener> allListeners = new Vector<TournamentListener>();
	private Vector<Participant> participants = new Vector<Participant>();
	private Vector<Phase> phase = new Vector<Phase>();
	private Game gameType;
	private int phaseIndex;
	private int gameIndex;
	private int nextPartIndex = 0;

	public Tournament() {
	}

	public void registerListeners(TournamentListener newListener) {
		allListeners.add(newListener);
	}

	public void addParticipantToTournament(String name) throws Exception {
		if (participants.size() < TOURNAMENT_PLAYERS_NUMBER) {
			participants.add(new Participant(name));
			for (TournamentListener l : allListeners) 
				l.fireAddedPlayerToUI(name);	
		} else 
			throw new Exception("There are Already 8 Participants");	
	}

	public void startTournament() throws Exception {
		phase.add(new Phase(4, gameType, participants)); //4--> 4 GAMES IN THAT PHASE
		for (TournamentListener l : allListeners)
			l.fireParticipantsToUI(participants);
		phase.get(0).startPhase();
	}

	public void submitPartScore(int gameIndex, int scoreTeamA, int scoreTeamB) {
		try { 
			if (gameIndex <= 3) { 						// group phase = first 4 games
				phaseIndex = 0;
				this.gameIndex = gameIndex;
			} else if (gameIndex <= 5) { 				// semi finals = next 2 games
				phaseIndex = 1;
				this.gameIndex = gameIndex - 4;
			} else { 									// finals 
				phaseIndex = 2;
				this.gameIndex = 0;
			}
			nextPartIndex = getPhase().get(phaseIndex).getGames().get(this.gameIndex).addScoreToTeams(scoreTeamA,scoreTeamB);
			checkResults();
		} catch (Exception e) {
			reverseTeamsSores();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void reverseTeamsSores() {
		getPhase().get(phaseIndex).getGames().get(this.gameIndex).reverseScoreAfterException();
	}

	private void checkResults() {
		if (nextPartIndex == 99 || nextPartIndex > gameType.getBaseParts() + gameType.getExtraParts() - 1) {
			if (phase.get(phaseIndex).getGames().get(gameIndex).draw()) {
				phase.get(phaseIndex).getGames().get(gameIndex).coinFlip();
			}
			removeLoser(phase.get(phaseIndex).getGames().get(gameIndex).getLoser());
			for (TournamentListener l : allListeners) 
				l.fireWinnerToUI(phase.get(phaseIndex).getGames().get(gameIndex).getWinner().getName(), phaseIndex,gameIndex);
		} else {
			for (TournamentListener l : allListeners)
				l.fireNextTextFieldsToUI(nextPartIndex);
		}
	}

	private void removeLoser(Participant loser) { 
		phase.get(phaseIndex).removeLoseFromPhase(loser);
	}
	
	public void newPhase(int i) {
		if (phase.size() == 1 && i == 1) {
			phase.add(new Phase(2, gameType, phase.get(0).getParticipants()));
			phase.get(1).startPhase();
		}
		if (phase.size() == 2 && i == 2) {
			phase.add(new Phase(1, gameType, phase.get(1).getParticipants()));
			phase.get(2).startPhase();
		}
	}
	

	public void setGameType(String game) throws Exception {
		if (game == null) 
			throw new Exception("must choose game type");
		if (game == "Soccer")
			this.gameType = new Soccer();
		else if (game == "Basketball")
			this.gameType = new Basketball();
		else
			this.gameType = new Tennis();
	}
	

	public Vector<Phase> getPhase() {
		return phase;
	}
	

	public Game getGameType() {
		return gameType;
	}

}
