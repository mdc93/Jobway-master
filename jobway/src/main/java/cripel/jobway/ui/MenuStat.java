package cripel.jobway.ui;

import java.io.IOException;

import cripel.jobway.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MenuStat extends BorderPane {

	
	/**
	 * The object user
	 */
	private User user;

	public MenuStat() {
		load();
	}
	
	/**
	 * Constructor with parameter user
	 * @param user the user connected
	 */
	
	
	private void setup(User user2) {
		// TODO Auto-generated method stub
		
	}

	public void load() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuStat.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
	}
}
