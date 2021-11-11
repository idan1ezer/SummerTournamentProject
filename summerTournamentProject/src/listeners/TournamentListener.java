package listeners;

import java.util.Vector;

import model.Participant;

public interface TournamentListener {

	void fireParticipantsToUI(Vector<Participant> participants);

	void fireAddedPlayerToUI(String name);

	void startTournamentInModel(String gameType);

	void fireNextTextFieldsToUI(int partNum);

	void fireWinnerToUI(String name, int phaseIndex, int gameIndex);
	
}
