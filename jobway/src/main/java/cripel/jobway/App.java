package cripel.jobway;

import cripel.jobway.model.User;
import cripel.jobway.ui.MainWindow;
import cripel.jobway.ui.login.Login;
import cripel.jobway.utilities.CustomUncaughtExceptionHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage primaryStage) {

		//Thread.setDefaultUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());
		primaryStage.getIcons().addAll(
				new Image(getClass().getResource("ui/icons/picto_appli16x16.jpg").toExternalForm()),
				new Image(getClass().getResource("ui/icons/picto_appli32x32.jpg").toExternalForm()),
				new Image(getClass().getResource("ui/icons/picto_appli48x48.jpg").toExternalForm()),
				new Image(getClass().getResource("ui/icons/picto_appli256x256.jpg").toExternalForm()));

		Login login = new Login();
		Scene scene = new Scene(login);
		Stage stage = new Stage();
		stage.getIcons().addAll(primaryStage.getIcons());
		stage.setTitle("Connexion");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.showAndWait();

		if (Boolean.TRUE.equals(login.verifyLogin())) {
			begin(primaryStage, login.getUser());

		}
	}

	/**
	 * Method main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Method to create the new scene & mainwindow
	 * 
	 * @param primaryStage
	 * @param user
	 */
	private void begin(Stage primaryStage, User user) {

		MainWindow root;
		if (user != null) {
			root = new MainWindow(user);
		} else {
			root = new MainWindow();
		}

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("ui/lightmode.css").toExternalForm());
		primaryStage.setTitle("JobWay App - 1.0.3");
		primaryStage.setMinHeight(993);
		primaryStage.setMinWidth(1185);
		primaryStage.setScene(scene);
		primaryStage.show(); 

	}

}