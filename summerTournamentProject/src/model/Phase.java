package model;

import java.util.Vector;

public class Phase {
	private Vector<Game> games = new Vector<Game>();
	private Vector<Participant> participants;

	public Phase(int numOfGames, Game game, Vector<Participant> participants) {
		this.participants = participants;
		if (game instanceof Soccer)
			for (int i = 0; i < numOfGames; i++)
				games.add(new Soccer());
		if (game instanceof Basketball)
			for (int i = 0; i < numOfGames; i++)
				games.add(new Basketball());
		if (game instanceof Tennis)
			for (int i = 0; i < numOfGames; i++)
				games.add(new Tennis()); 
	}

	public void startPhase() {
		for (int i = 0; i < games.size(); i++) 
			games.get(i).setPlayers(participants.get(2 * i), participants.get(2 * i + 1));
	}

	public void removeLoseFromPhase(Participant loser) {
		boolean found = false;
		for (int i = 0; i < participants.size() && !found; i++) {
			if (participants.get(i).equals(loser)) {
				participants.remove(i);
				found = true;
			}
		}
	}

	public Vector<Participant> getParticipants() {
		return participants;
	}

	public Vector<Game> getGames() {
		return games;
	}

}
