//idan ezer 315963660 & tor hanan 205665862
import controller.TournamentController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Tournament;
import view.TournamentView;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Tournament model = new Tournament();
		TournamentView view = new TournamentView(primaryStage);
		TournamentController controller = new TournamentController(model, view);
	}
}
