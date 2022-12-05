package cripel.jobway.ui.login;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import cripel.jobway.utilities.HibernateUtil;
import cripel.jobway.utilities.PropertiesUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginParameters extends Pane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The textfield for the database's url */
	@FXML
	private TextField textFieldDbUrl;

	/** The textfield for the database's password */
	@FXML
	private PasswordField textFieldPassword;

	/** The textfield for the database's username */
	@FXML
	private TextField textFieldUserName;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************
	
	private static final String PATH = System.getProperty("user.home") + File.separator + "JobWay" + File.separator + "config.properties";
	private Properties prop;
	
	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public LoginParameters() {
		load();
		setup();

	}

	// **************************************************************************************************
	// FXML METHODS
	// **************************************************************************************************

	/**
	 * Method that cancel the current action
	 *
	 * @param event
	 */
	@FXML
	void cancelAction(ActionEvent event) {
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.close();
	}

	/**
	 * Method that save the Database properties
	 *
	 * @param event
	 */
	@FXML
	void saveAction(ActionEvent event) {
		PropertiesUtil.saveDb(prop,textFieldDbUrl.getText(), textFieldUserName.getText(), textFieldPassword.getText());
		cancelAction(event);
		if (HibernateUtil.getSessionfactory() == null) {
			HibernateUtil.restart();
		}

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup the database's properties
	 */
	public void setup() {
		if (!PropertiesUtil.fileIsCreated(PATH)) {
			prop=PropertiesUtil.defaultConfig();

		} else {
			prop=PropertiesUtil.loadProperties(PATH);

		}
		textFieldDbUrl.setText(prop.getProperty("DBURL"));
		textFieldUserName.setText(prop.getProperty("DBUSER"));
		textFieldPassword.setText(prop.getProperty("DBPASSWORD"));

	}

	// **************************************************************************************************
	// LOAD METHODS
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginParameters.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
