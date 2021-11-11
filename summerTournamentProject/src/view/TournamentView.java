package view;

import java.util.Vector;


import listeners.ViewListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TournamentView extends Application {

	private static Vector<ViewListener> allListeners = new Vector<ViewListener>();
	Label[] participantList;
	Label[] allTournamentNameLabels;
	Label[] teamLabels;
	TextField[] soccerTextFields;
	TextField[] basketballTextFields;
	TextField[] tennisTextFields;
	ToggleGroup tglGames = new ToggleGroup();
	Label lblException = new Label();
	static BorderPane brRoot = new BorderPane();
	static StackPane spRoot = new StackPane();
	int tempIndex = 0;
	boolean textFieldsNotEmpty = false;
	int scoreA;
	int scoreB;

	Stage gameStage = new Stage();
	Scene soccerScene;
	Scene basketballScene;
	Scene tennisScene;

	public Label getLblException() {
		return lblException;
	}

	public TournamentView(Stage theStage) {
		lblException.setVisible(false);
		brRoot.setPadding(new Insets(20));
		brRoot.setCenter(spRoot);
		spRoot.setAlignment(Pos.CENTER);

		Label lblName = new Label("Paticipent Name:");
		TextField tfPlayerName = new TextField();
		Button btnAddParticipet = new Button("Add Participent");
		Button btnStartTournament = new Button("Start Tournament");

		GridPane gpAddPlayers = new GridPane();
		gpAddPlayers.setPadding(new Insets(20));
		gpAddPlayers.setVgap(10);
		gpAddPlayers.setHgap(10);
		gpAddPlayers.setAlignment(Pos.CENTER);
		gpAddPlayers.add(lblName, 0, 0);
		gpAddPlayers.add(tfPlayerName, 1, 0);
		gpAddPlayers.add(btnAddParticipet, 0, 1);
		gpAddPlayers.add(btnStartTournament, 1, 1);

		RadioButton rbSportTypeSoccer = new RadioButton("Soccer");
		RadioButton rbSportTypeBasketball = new RadioButton("Basketball");
		RadioButton rbSportTypeTennis = new RadioButton("Tennis");
		rbSportTypeSoccer.setToggleGroup(tglGames);
		rbSportTypeBasketball.setToggleGroup(tglGames);
		rbSportTypeTennis.setToggleGroup(tglGames);

		VBox vbChooseGame = new VBox();
		vbChooseGame.setPadding(new Insets(20));
		vbChooseGame.setSpacing(20);
		vbChooseGame.setAlignment(Pos.CENTER_LEFT);
		vbChooseGame.getChildren().addAll(rbSportTypeSoccer, rbSportTypeBasketball, rbSportTypeTennis);

		Label lblParticipent1 = new Label();
		Label lblParticipent2 = new Label();
		Label lblParticipent3 = new Label();
		Label lblParticipent4 = new Label();
		Label lblParticipent5 = new Label();
		Label lblParticipent6 = new Label();
		Label lblParticipent7 = new Label();
		Label lblParticipent8 = new Label();

		VBox vbParticipents = new VBox();
		vbParticipents.setPadding(new Insets(20));
		vbParticipents.setSpacing(30);
		vbParticipents.setAlignment(Pos.CENTER_LEFT);

		vbParticipents.getChildren().addAll(lblParticipent1, lblParticipent2, lblParticipent3, lblParticipent4,
				lblParticipent5, lblParticipent6, lblParticipent7, lblParticipent8);

		Label[] lblPlayers8 = { lblParticipent1, lblParticipent2, lblParticipent3, lblParticipent4, lblParticipent5,
				lblParticipent6, lblParticipent7, lblParticipent8 };

		participantList = lblPlayers8;

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(10.0);
		dropShadow.setOffsetX(5.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		for (int i = 0; i < lblPlayers8.length; i++) {
			lblPlayers8[i].setMinWidth(90);
			lblPlayers8[i].setMaxWidth(90);
			lblPlayers8[i].setMinHeight(25);
			lblPlayers8[i].setMaxHeight(25);
			lblPlayers8[i].setAlignment(Pos.CENTER);
			lblPlayers8[i].setTextAlignment(TextAlignment.CENTER);
			lblPlayers8[i].setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			lblPlayers8[i]
					.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			lblPlayers8[i].setTextFill(Color.BLACK);
			lblPlayers8[i].setFont(new Font(STYLESHEET_CASPIAN, 15));
			lblPlayers8[i].setEffect(dropShadow);
		}

		GridPane gpTournamentPhases = new GridPane();
		gpTournamentPhases.setPadding(new Insets(10));
		gpTournamentPhases.setVgap(0);
		gpTournamentPhases.setHgap(40);
		gpTournamentPhases.setAlignment(Pos.CENTER_LEFT);
		gpTournamentPhases.setVisible(false);

		Label lblPhase0Participent1 = new Label("	1	");
		Label lblPhase0Participent2 = new Label("	2	");
		Label lblPhase0Participent3 = new Label("	3	");
		Label lblPhase0Participent4 = new Label("	4	");
		Label lblPhase0Participent5 = new Label("	5	");
		Label lblPhase0Participent6 = new Label("	6	");
		Label lblPhase0Participent7 = new Label("	7	");
		Label lblPhase0Participent8 = new Label("	8	");

		Button btnPhase1PlayGame1 = new Button("Play Game");
		Button btnPhase1PlayGame2 = new Button("Play Game");
		Button btnPhase1PlayGame3 = new Button("Play Game");
		Button btnPhase1PlayGame4 = new Button("Play Game");
		Label lblWinnerPhase1Game1 = new Label("	1	");
		Label lblWinnerPhase1Game2 = new Label("	3	");
		Label lblWinnerPhase1Game3 = new Label("	5	");
		Label lblWinnerPhase1Game4 = new Label("	7	");

		Button btnPhase2PlayGame1 = new Button("Play Game");
		Button btnPhase2PlayGame2 = new Button("Play Game");
		Label lblWinnerPhase2Game1 = new Label("	1	");
		Label lblWinnerPhase2Game2 = new Label("	5	");

		Button btnFinalPlayGame = new Button("Play Game");
		Label lblWinner = new Label("	1	");

		gpTournamentPhases.add(lblPhase0Participent1, 0, 0);
		gpTournamentPhases.add(lblPhase0Participent2, 0, 2);
		gpTournamentPhases.add(lblPhase0Participent3, 0, 4);
		gpTournamentPhases.add(lblPhase0Participent4, 0, 6);
		gpTournamentPhases.add(lblPhase0Participent5, 0, 8);
		gpTournamentPhases.add(lblPhase0Participent6, 0, 10);
		gpTournamentPhases.add(lblPhase0Participent7, 0, 12);
		gpTournamentPhases.add(lblPhase0Participent8, 0, 14);
		gpTournamentPhases.add(btnPhase1PlayGame1, 1, 1);
		gpTournamentPhases.add(btnPhase1PlayGame2, 1, 5);
		gpTournamentPhases.add(btnPhase1PlayGame3, 1, 9);
		gpTournamentPhases.add(btnPhase1PlayGame4, 1, 13);
		gpTournamentPhases.add(lblWinnerPhase1Game1, 2, 1);
		gpTournamentPhases.add(lblWinnerPhase1Game2, 2, 5);
		gpTournamentPhases.add(lblWinnerPhase1Game3, 2, 9);
		gpTournamentPhases.add(lblWinnerPhase1Game4, 2, 13);
		gpTournamentPhases.add(btnPhase2PlayGame1, 3, 3);
		gpTournamentPhases.add(btnPhase2PlayGame2, 3, 11);
		gpTournamentPhases.add(lblWinnerPhase2Game1, 4, 3);
		gpTournamentPhases.add(lblWinnerPhase2Game2, 4, 11);
		gpTournamentPhases.add(btnFinalPlayGame, 5, 7);
		gpTournamentPhases.add(lblWinner, 6, 7);

		Label[] allLabels = { lblPhase0Participent1, lblPhase0Participent2, lblPhase0Participent3,
				lblPhase0Participent4, lblPhase0Participent5, lblPhase0Participent6, lblPhase0Participent7,
				lblPhase0Participent8, lblWinnerPhase1Game1, lblWinnerPhase1Game2, lblWinnerPhase1Game3,
				lblWinnerPhase1Game4, lblWinnerPhase2Game1, lblWinnerPhase2Game2, lblWinner };
		allTournamentNameLabels = allLabels;

		for (int i = 0; i < allTournamentNameLabels.length; i++) {
			allTournamentNameLabels[i].setMinWidth(90);
			allTournamentNameLabels[i].setMaxWidth(90);
			allTournamentNameLabels[i].setMinHeight(25);
			allTournamentNameLabels[i].setMaxHeight(25);
			allTournamentNameLabels[i].setAlignment(Pos.CENTER);
			allTournamentNameLabels[i].setTextAlignment(TextAlignment.CENTER);
			allTournamentNameLabels[i].setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			allTournamentNameLabels[i]
					.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			allTournamentNameLabels[i].setTextFill(Color.BLACK);
			allTournamentNameLabels[i].setFont(new Font(STYLESHEET_CASPIAN, 15));
			allTournamentNameLabels[i].setEffect(dropShadow);
		}
		lblWinner.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

		brRoot.setRight(vbChooseGame);
		brRoot.setLeft(vbParticipents);
		brRoot.setBottom(lblException);
		spRoot.getChildren().addAll(gpAddPlayers, gpTournamentPhases);

//Main stage button handler
		btnAddParticipet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!tfPlayerName.getText().isEmpty()) {
					lblException.setVisible(false);
					for (ViewListener l : allListeners) {
						l.addParticipantToModel(tfPlayerName.getText());
					}
					tfPlayerName.clear();
				} else {
					lblException.setText("name can't be empty");
					lblException.setVisible(true);
				}
			}
		});

		btnStartTournament.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tglGames.getSelectedToggle() != null && have8Players()) {
					for (int i = 0; i < allTournamentNameLabels.length; i++) {
						allTournamentNameLabels[i].setText(null);
					}
					lblException.setVisible(false);
					gpAddPlayers.setVisible(false);
					vbChooseGame.setVisible(false);
					vbParticipents.setVisible(false);
					gpTournamentPhases.setVisible(true);

					for (ViewListener l : allListeners) {
						l.startTournamentInModel(getSelectedGame());
					}
				} else {
					lblException.setText("must choose a game / must have 8 Participants");
					lblException.setVisible(true);
				}
			}
		});
		
		

		Button[] allPlayGameButttons = { btnPhase1PlayGame1, btnPhase1PlayGame2, btnPhase1PlayGame3, btnPhase1PlayGame4,
				btnPhase2PlayGame1, btnPhase2PlayGame2, btnFinalPlayGame };

		for (int i = 0; i < allPlayGameButttons.length; i++) {
			allPlayGameButttons[i].setId("" + i);
		}

		btnPhase1PlayGame1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase1PlayGame1.getId());
			}
		});

		btnPhase1PlayGame2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase1PlayGame2.getId());
			}
		});

		btnPhase1PlayGame3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase1PlayGame3.getId());
			}
		});

		btnPhase1PlayGame4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase1PlayGame4.getId());
			}
		});

		btnPhase2PlayGame1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase2PlayGame1.getId());
			}
		});

		btnPhase2PlayGame2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnPhase2PlayGame2.getId());
			}
		});

		btnFinalPlayGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGameScene(btnFinalPlayGame.getId());
			}
		});

//Play Game PopUps - Soccer

		GridPane gpSoccer = new GridPane();
		gpSoccer.setPadding(new Insets(10));
		gpSoccer.setVgap(20);
		gpSoccer.setHgap(30);
		gpSoccer.setAlignment(Pos.CENTER_LEFT);

		Label lblSoccerHeadline = new Label("Soccer");
		Label lblSoccerPlayerA = new Label("Team A");
		Label lblSoccerPlayerB = new Label("Team B");
		Label lblSoccerHalf1 = new Label("Half 1");
		Label lblSoccerHalf2 = new Label("Half 2");
		Label lblSoccerOvertime = new Label("Overtime");
		Label lblSoccerPenalties = new Label("Penalties");

		TextField tfSoccerTeamAScoreHalf1 = new TextField();
		TextField tfSoccerTeamBScoreHalf1 = new TextField();
		TextField tfSoccerTeamAScoreHalf2 = new TextField();
		TextField tfSoccerTeamBScoreHalf2 = new TextField();
		TextField tfSoccerTeamAScoreOvertime = new TextField();
		TextField tfSoccerTeamBScoreOvertime = new TextField();
		TextField tfSoccerTeamAScorePenalties = new TextField();
		TextField tfSoccerTeamBScorePenalties = new TextField();
		TextField[] tempSoccerTextFields = { tfSoccerTeamAScoreHalf1, tfSoccerTeamBScoreHalf1, tfSoccerTeamAScoreHalf2,
				tfSoccerTeamBScoreHalf2, tfSoccerTeamAScoreOvertime, tfSoccerTeamBScoreOvertime,
				tfSoccerTeamAScorePenalties, tfSoccerTeamBScorePenalties };
		soccerTextFields = tempSoccerTextFields;

		for (int i = 0; i < soccerTextFields.length; i++) {
			soccerTextFields[i].setMaxWidth(40);
		}

		Button btnSoccerSubmitScore = new Button("Submit Score");

		gpSoccer.add(lblSoccerHeadline, 2, 0, 2, 1);
		gpSoccer.add(lblSoccerPlayerA, 0, 2);
		gpSoccer.add(lblSoccerPlayerB, 0, 3);
		gpSoccer.add(lblSoccerHalf1, 1, 1);
		gpSoccer.add(lblSoccerHalf2, 2, 1);
		gpSoccer.add(lblSoccerOvertime, 3, 1);
		gpSoccer.add(lblSoccerPenalties, 4, 1);

		gpSoccer.add(tfSoccerTeamAScoreHalf1, 1, 2);
		gpSoccer.add(tfSoccerTeamAScoreHalf2, 2, 2);
		gpSoccer.add(tfSoccerTeamAScoreOvertime, 3, 2);
		gpSoccer.add(tfSoccerTeamAScorePenalties, 4, 2);

		gpSoccer.add(tfSoccerTeamBScoreHalf1, 1, 3);
		gpSoccer.add(tfSoccerTeamBScoreHalf2, 2, 3);
		gpSoccer.add(tfSoccerTeamBScoreOvertime, 3, 3);
		gpSoccer.add(tfSoccerTeamBScorePenalties, 4, 3);

		gpSoccer.add(btnSoccerSubmitScore, 1, 4, 2, 1);

//Play Game PopUps - Basketball

		GridPane gpBasketball = new GridPane();
		gpBasketball.setPadding(new Insets(10));
		gpBasketball.setVgap(20);
		gpBasketball.setHgap(30);
		gpBasketball.setAlignment(Pos.CENTER_LEFT);

		Label lblBasketballHeadline = new Label("Basketball");
		Label lblBasketballPlayerA = new Label("Team A");
		Label lblBasketballPlayerB = new Label("Team B");
		Label lblBasketballQuarter1 = new Label("Quarter 1");
		Label lblBasketballQuarter2 = new Label("Quarter 2");
		Label lblBasketballQuarter3 = new Label("Quarter 3");
		Label lblBasketballQuarter4 = new Label("Quarter 4");
		Label lblBasketballOvertime = new Label("Overtime");

		TextField tfBasketballTeamAScoreQuarter1 = new TextField();
		TextField tfBasketballTeamBScoreQuarter1 = new TextField();
		TextField tfBasketballTeamAScoreQuarter2 = new TextField();
		TextField tfBasketballTeamBScoreQuarter2 = new TextField();
		TextField tfBasketballTeamAScoreQuarter3 = new TextField();
		TextField tfBasketballTeamBScoreQuarter3 = new TextField();
		TextField tfBasketballTeamAScoreQuarter4 = new TextField();
		TextField tfBasketballTeamBScoreQuarter4 = new TextField();
		TextField tfBasketballTeamAScoreOvertime = new TextField();
		TextField tfBasketballTeamBScoreOvertime = new TextField();
		TextField[] tempBasketballTextFields = { tfBasketballTeamAScoreQuarter1, tfBasketballTeamBScoreQuarter1,
				tfBasketballTeamAScoreQuarter2, tfBasketballTeamBScoreQuarter2, tfBasketballTeamAScoreQuarter3,
				tfBasketballTeamBScoreQuarter3, tfBasketballTeamAScoreQuarter4, tfBasketballTeamBScoreQuarter4,
				tfBasketballTeamAScoreOvertime, tfBasketballTeamBScoreOvertime };
		basketballTextFields = tempBasketballTextFields;

		for (int i = 0; i < basketballTextFields.length; i++) {
			basketballTextFields[i].setMaxWidth(40);
		}

		Button btnBasketballSubmitScore = new Button("Submit Score");

		gpBasketball.add(lblBasketballHeadline, 2, 0, 2, 1);
		gpBasketball.add(lblBasketballPlayerA, 0, 2);
		gpBasketball.add(lblBasketballPlayerB, 0, 3);
		gpBasketball.add(lblBasketballQuarter1, 1, 1);
		gpBasketball.add(lblBasketballQuarter2, 2, 1);
		gpBasketball.add(lblBasketballQuarter3, 3, 1);
		gpBasketball.add(lblBasketballQuarter4, 4, 1);
		gpBasketball.add(lblBasketballOvertime, 5, 1);

		gpBasketball.add(tfBasketballTeamAScoreQuarter1, 1, 2);
		gpBasketball.add(tfBasketballTeamAScoreQuarter2, 2, 2);
		gpBasketball.add(tfBasketballTeamAScoreQuarter3, 3, 2);
		gpBasketball.add(tfBasketballTeamAScoreQuarter4, 4, 2);
		gpBasketball.add(tfBasketballTeamAScoreOvertime, 5, 2);

		gpBasketball.add(tfBasketballTeamBScoreQuarter1, 1, 3);
		gpBasketball.add(tfBasketballTeamBScoreQuarter2, 2, 3);
		gpBasketball.add(tfBasketballTeamBScoreQuarter3, 3, 3);
		gpBasketball.add(tfBasketballTeamBScoreQuarter4, 4, 3);
		gpBasketball.add(tfBasketballTeamBScoreOvertime, 5, 3);

		gpBasketball.add(btnBasketballSubmitScore, 1, 4, 2, 1);

//Play Game PopUps - Tennis

		GridPane gpTennis = new GridPane();
		gpTennis.setPadding(new Insets(10));
		gpTennis.setVgap(20);
		gpTennis.setHgap(30);
		gpTennis.setAlignment(Pos.CENTER_LEFT);

		Label lblTennisHeadline = new Label("Tennis");
		Label lblTennisPlayerA = new Label("Team A");
		Label lblTennisPlayerB = new Label("Team B");
		Label lblTennisSet1 = new Label("Set 1");
		Label lblTennisSet2 = new Label("Set 2");
		Label lblTennisSet3 = new Label("Set 3");
		Label lblTennisSet4 = new Label("Set 4");
		Label lblTennisSet5 = new Label("Set 5");

		TextField tfTennisPlayerAScoreSet1 = new TextField();
		TextField tfTennisPlayerBScoreSet1 = new TextField();
		TextField tfTennisPlayerAScoreSet2 = new TextField();
		TextField tfTennisPlayerBScoreSet2 = new TextField();
		TextField tfTennisPlayerAScoreSet3 = new TextField();
		TextField tfTennisPlayerBScoreSet3 = new TextField();
		TextField tfTennisPlayerAScoreSet4 = new TextField();
		TextField tfTennisPlayerBScoreSet4 = new TextField();
		TextField tfTennisPlayerAScoreSet5 = new TextField();
		TextField tfTennisPlayerBScoreSet5 = new TextField();
		TextField[] tempTennisTextFields = { tfTennisPlayerAScoreSet1, tfTennisPlayerBScoreSet1,
				tfTennisPlayerAScoreSet2, tfTennisPlayerBScoreSet2, tfTennisPlayerAScoreSet3, tfTennisPlayerBScoreSet3,
				tfTennisPlayerAScoreSet4, tfTennisPlayerBScoreSet4, tfTennisPlayerAScoreSet5,
				tfTennisPlayerBScoreSet5 };
		tennisTextFields = tempTennisTextFields;

		for (int i = 0; i < tennisTextFields.length; i++) {
			tennisTextFields[i].setMaxWidth(40);
		}

		Button btnTennisSubmitScore = new Button("Submit Score");

		gpTennis.add(lblTennisHeadline, 2, 0, 2, 1);
		gpTennis.add(lblTennisPlayerA, 0, 2);
		gpTennis.add(lblTennisPlayerB, 0, 3);
		gpTennis.add(lblTennisSet1, 1, 1);
		gpTennis.add(lblTennisSet2, 2, 1);
		gpTennis.add(lblTennisSet3, 3, 1);
		gpTennis.add(lblTennisSet4, 4, 1);
		gpTennis.add(lblTennisSet5, 5, 1);

		gpTennis.add(tfTennisPlayerAScoreSet1, 1, 2);
		gpTennis.add(tfTennisPlayerAScoreSet2, 2, 2);
		gpTennis.add(tfTennisPlayerAScoreSet3, 3, 2);
		gpTennis.add(tfTennisPlayerAScoreSet4, 4, 2);
		gpTennis.add(tfTennisPlayerAScoreSet5, 5, 2);

		gpTennis.add(tfTennisPlayerBScoreSet1, 1, 3);
		gpTennis.add(tfTennisPlayerBScoreSet2, 2, 3);
		gpTennis.add(tfTennisPlayerBScoreSet3, 3, 3);
		gpTennis.add(tfTennisPlayerBScoreSet4, 4, 3);
		gpTennis.add(tfTennisPlayerBScoreSet5, 5, 3);

		gpTennis.add(btnTennisSubmitScore, 1, 4, 2, 1);

//Game stage button handler

		btnSoccerSubmitScore.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < soccerTextFields.length - 1; i += 2) {
					if (!(soccerTextFields[i].isDisable()) && !(soccerTextFields[i + 1].isDisable())
							&& checkFields(soccerTextFields, i, i + 1)) {
						textFieldsNotEmpty = true;
						scoreA = Integer.parseInt(soccerTextFields[i].getText());
						scoreB = Integer.parseInt(soccerTextFields[i + 1].getText());
					}
				}
				if (textFieldsNotEmpty) {
					textFieldsNotEmpty = false;
					for (ViewListener l : allListeners) {
						l.submitPartScoreInModel(tempIndex, scoreA, scoreB);
					}
				}
			}
		});

		btnBasketballSubmitScore.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < basketballTextFields.length - 1; i++) {
					if (!(basketballTextFields[i].isDisable()) && !(basketballTextFields[i + 1].isDisable())
							&& checkFields(basketballTextFields, i, i + 1)) {
						textFieldsNotEmpty = true;
						scoreA = Integer.parseInt(basketballTextFields[i].getText());
						scoreB = Integer.parseInt(basketballTextFields[i + 1].getText());
					}
				}
				if (textFieldsNotEmpty) {
					textFieldsNotEmpty = false;
					for (ViewListener l : allListeners) {
						l.submitPartScoreInModel(tempIndex, scoreA, scoreB);
					}
				}
			}
		});

		btnTennisSubmitScore.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < tennisTextFields.length - 1; i++) {
					if (!(tennisTextFields[i].isDisable()) && !(tennisTextFields[i + 1].isDisable())
							&& checkFields(tennisTextFields, i, i + 1)) {
						textFieldsNotEmpty = true;
						scoreA = Integer.parseInt(tennisTextFields[i].getText());
						scoreB = Integer.parseInt(tennisTextFields[i + 1].getText());
					}
				}
				if (textFieldsNotEmpty) {
					textFieldsNotEmpty = false;
					for (ViewListener l : allListeners) {
						l.submitPartScoreInModel(tempIndex, scoreA, scoreB);
					}
				}

			}
		});

		// Creating gameStage & Scenes

		Label[] tempTeamLabels = { lblSoccerPlayerA, lblSoccerPlayerB, lblBasketballPlayerA, lblBasketballPlayerB,
				lblTennisPlayerA, lblTennisPlayerB };
		teamLabels = tempTeamLabels;
		for (int i = 0; i < teamLabels.length; i++) {
			teamLabels[i].setMinWidth(50);
			teamLabels[i].setMaxWidth(50);
			teamLabels[i].setMinHeight(20);
			teamLabels[i].setMaxHeight(20);
			teamLabels[i].setAlignment(Pos.CENTER);
			teamLabels[i].setTextAlignment(TextAlignment.CENTER);
		}

		Scene tempSoccerScene = new Scene(gpSoccer, 500, 300);
		Scene tempBasketballScene = new Scene(gpBasketball, 500, 300);
		Scene tempTennisScene = new Scene(gpTennis, 500, 300);

		soccerScene = tempSoccerScene;
		basketballScene = tempBasketballScene;
		tennisScene = tempTennisScene;

		Scene scene = new Scene(brRoot, 1200, 700);
		theStage.setScene(scene);
		theStage.setResizable(false);
		gameStage.setResizable(false);
		theStage.show();

	}

	@Override
	public void start(Stage theStage) throws Exception {
		// TODO Auto-generated method stub

	}

	public Label[] getAllTournamentNameLabels() {
		return allTournamentNameLabels;
	}

	public Label[] getParticipantList() {
		return participantList;
	}

	public Label[] getTeamLabels() {
		return teamLabels;
	}

	public void moveLabelsToGame() {

	}

	public String getSelectedGame() {
		RadioButton selected = (RadioButton) tglGames.getSelectedToggle();
		String selectedGame = selected.getText();
		return selectedGame;
	}

	public void startGameScene(String btnID) {
		String game = getSelectedGame();
		tempIndex = Integer.parseInt(btnID);
		for (int i = 0; i < teamLabels.length; i++) {
			teamLabels[i].setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		if (game == "Soccer") {
			gameStage.setScene(soccerScene);
			for (int i = 0; i < soccerTextFields.length; i++) {
				soccerTextFields[i].clear();
				soccerTextFields[i].setDisable(true);
			}
			soccerTextFields[0].setDisable(false);
			soccerTextFields[1].setDisable(false);
		}

		else if (game == "Basketball") {
			gameStage.setScene(basketballScene);
			for (int i = 0; i < basketballTextFields.length; i++) {
				basketballTextFields[i].clear();
				basketballTextFields[i].setDisable(true);
			}
			basketballTextFields[0].setDisable(false);
			basketballTextFields[1].setDisable(false);
		}

		else {
			gameStage.setScene(tennisScene);
			for (int i = 0; i < tennisTextFields.length; i++) {
				tennisTextFields[i].clear();
				tennisTextFields[i].setDisable(true);
			}
			tennisTextFields[0].setDisable(false);
			tennisTextFields[1].setDisable(false);
		}
		gameStage.show();
		for (ViewListener l : allListeners)
			l.startGameInModel(tempIndex);
	}

	private boolean checkFields(TextField[] textFields, int i, int j) {
		if (textFields[i].getText().trim().isEmpty() || textFields[j].getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public TextField[] getSoccerTextFields() {
		return soccerTextFields;
	}

	public TextField[] getBasketballTextFields() {
		return basketballTextFields;
	}

	public TextField[] getTennisTextFields() {
		return tennisTextFields;
	}

	public void registerListeners(ViewListener newListener) {
		allListeners.add(newListener);
	}
	private boolean have8Players() {
		for(Label p: participantList) 
			if (p.getText().isEmpty()) 
				return false;
		return true;
	}
}
