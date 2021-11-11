package controller;

import java.util.Vector;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Participant;
import model.Tournament;
import view.TournamentView;
import listeners.TournamentListener;
import listeners.ViewListener;

public class TournamentController implements TournamentListener, ViewListener {
	private Tournament tournament;
	private TournamentView view;

	public TournamentController(Tournament tournament, TournamentView view) {
		this.tournament = tournament;
		this.view = view;
		view.registerListeners(this);
		tournament.registerListeners(this);
	}

	@Override
	public void addParticipantToModel(String name) {
		try {
			tournament.addParticipantToTournament(name);
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}
	
	@Override
	public void fireAddedPlayerToUI(String name) {
		boolean flag = false;
		for (int i = 0; i < view.getParticipantList().length && !flag; i++) {
			if (view.getParticipantList()[i].getText().isEmpty()) {
				flag = true;
				view.getParticipantList()[i].setText(name);
			}
		}
	}

	@Override
	public void fireParticipantsToUI(Vector<Participant> participants) {
		for (int i = 0; i < participants.size(); i++) 
			view.getAllTournamentNameLabels()[i].setText(participants.get(i).getName());
	}
	
	@Override
	public void startTournamentInModel(String gameType) {
		try {
			tournament.setGameType(gameType);
			tournament.startTournament();
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}

	@Override
	public void submitPartScoreInModel(int gameIndex, int scoreTeamA, int scoreTeamB) {
		tournament.submitPartScore(gameIndex, scoreTeamA, scoreTeamB);
	}

	@Override
	public void startGameInModel(int gameIndex) {
		String participantA = null;
		String participantB = null;
		if (gameIndex <= 3) {
			participantA = tournament.getPhase().get(0).getGames().get(gameIndex).getTeamA().getName();
			participantB = tournament.getPhase().get(0).getGames().get(gameIndex).getTeamB().getName();
		} else if (gameIndex <= 5) {
			tournament.newPhase(1);
			participantA = tournament.getPhase().get(1).getGames().get(gameIndex - 4).getTeamA().getName();
			participantB = tournament.getPhase().get(1).getGames().get(gameIndex - 4).getTeamB().getName();
		} else if (gameIndex <= 6) {
			tournament.newPhase(2);
			participantA = tournament.getPhase().get(2).getGames().get(0).getTeamA().getName();
			participantB = tournament.getPhase().get(2).getGames().get(0).getTeamB().getName();
		}
		setParticipantsLabels(participantA, participantB);
	}

	private void setParticipantsLabels(String A, String B) {
		if (tournament.getGameType().getClass().getName() == "model.Soccer") {
			view.getTeamLabels()[0].setText(A);
			view.getTeamLabels()[1].setText(B);
		} else if (tournament.getGameType().getClass().getName() == "model.Basketball") {
			view.getTeamLabels()[2].setText(A);
			view.getTeamLabels()[3].setText(B);
		} else {
			view.getTeamLabels()[4].setText(A);
			view.getTeamLabels()[5].setText(B);
		}
	}

	@Override
	public void fireNextTextFieldsToUI(int partNum) {
		enableGameNextTextFields(partNum);
	}

	private void enableGameNextTextFields(int partNum) {
		if (tournament.getGameType().toString() == ("Soccer")) {
			for (int i = 0; i < view.getSoccerTextFields().length; i++) {
				view.getSoccerTextFields()[i].setDisable(true);
			}
			view.getSoccerTextFields()[partNum * 2].setDisable(false);
			view.getSoccerTextFields()[partNum * 2 + 1].setDisable(false);
		} 
		else if (tournament.getGameType().toString()==("Basketball")) {
			for (int i = 0; i < view.getBasketballTextFields().length; i++) {
				view.getBasketballTextFields()[i].setDisable(true);
			}
			view.getBasketballTextFields()[partNum * 2].setDisable(false);
			view.getBasketballTextFields()[partNum * 2 + 1].setDisable(false);
		}
		else {
			for (int i = 0; i < view.getTennisTextFields().length; i++) {
				view.getTennisTextFields()[i].setDisable(true);
			}
			view.getTennisTextFields()[partNum * 2].setDisable(false);
			view.getTennisTextFields()[partNum * 2 + 1].setDisable(false);
		}
	}

	@Override
	public void fireWinnerToUI(String name,int phase,int game) {
		paintWinnerAndLoser(name);
		if (phase==0) 
			view.getAllTournamentNameLabels()[game+8].setText(name);
		if (phase==1) 
			view.getAllTournamentNameLabels()[game+12].setText(name);
		
		if (phase==2) 
			view.getAllTournamentNameLabels()[14].setText(name);
	}

	private void paintWinnerAndLoser(String name) {
		for (int i = 0; i < view.getTeamLabels().length; i++) {
			if (name==view.getTeamLabels()[i].getText()) {
				view.getTeamLabels()[i].setBorder(new Border(
						new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				if (i%2==0) {
					view.getTeamLabels()[i+1].setBorder(new Border(
							new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				}
				else {
					view.getTeamLabels()[i-1].setBorder(new Border(
							new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				}
			}
		}
		
	}

}
