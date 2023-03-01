package cripel.jobway.ui;

import java.io.IOException;

import cripel.jobway.model.User;
import cripel.jobway.ui.tablemngmt.TableManagementHome;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * The Class Mainwindow to display the whole content
 */
public class MainWindow extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The borderpane for the content */
	@FXML
	private BorderPane borderPaneContent;

	/** The button for the main scene (display people ) */
	@FXML
	private Button buttonDisplay;

	/** The button to create a new encoding */
	@FXML
	private Button buttonEncoding;

	/** The button to manage the users */
	@FXML
	private Button buttonUsers;

	/** The button to manage the tables */
	@FXML
	private Button buttonGestion;

	/** The button to switch between dark and light css theme */
	@FXML
	private Button buttonReso;

	/** The button to close the program */
	@FXML
	private Button buttonClose;
	
	/** The button to see statistique of employees */
	@FXML
	private Button buttonStat;

	/** The label for the username connected */
	@FXML
	private Label labelUserName;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/** The string for the lightmode url */
	private String lightStyle = getClass().getResource("lightmode.css").toExternalForm();

	/** The string for the darkmode url */
	private String darkStyle = getClass().getResource("style.css").toExternalForm();

	/**
	 * User currently selected
	 */
	User userSelected;
	
	

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public MainWindow() {
		load();
	}

	/**
	 * Constructor with a current user
	 *
	 * @param user
	 */
	public MainWindow(User user) {
		load();
		userSelected = user;
		setup();
		toMenuPerson();
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup the label that shows who is currently connected
	 */
	public void setup() {
		checkUserLevel();
		if (userSelected.getEmployee() != null) {
			labelUserName.setText(userSelected.getEmployee().getEmpPseudo() + " ("
					+ userSelected.getUserlevel().getUserLevelName() + ")");
		} else
			labelUserName
					.setText(userSelected.getUserName() + " (" + userSelected.getUserlevel().getUserLevelName() + ")");

	}

	/**
	 * Method to check the user's level
	 */
	public void checkUserLevel() {
		if (userSelected.getUserlevel().getUserLevelName().contains("Administrateur")) {
			buttonGestion.setDisable(false);
			buttonUsers.setDisable(false);
			buttonStat.setDisable(false);
		}
	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method to open the statistic's window
	 */
	@FXML
	public void toStatistic() {
		
		if (borderPaneContent.getCenter() != null) {
			borderPaneContent.setCenter(null);
		}
		borderPaneContent.setCenter(new MenuStat());
		borderPaneContent.getLeft().setViewOrder(1);
		borderPaneContent.getCenter().setViewOrder(2);
	}
	
	/**
	 * Method to open the gestion's window
	 */
	@FXML
	public void toGestion() {
		if (borderPaneContent.getCenter() != null) {
			borderPaneContent.setCenter(null);
		}
		borderPaneContent.setCenter(new TableManagementHome());
		borderPaneContent.getLeft().setViewOrder(1);

	}

	/**
	 * Method to open the user's window
	 *
	 * @param event
	 */
	@FXML
	void toUsers(ActionEvent event) {

		if (borderPaneContent.getCenter() != null) {
			borderPaneContent.setCenter(null);
		}
		borderPaneContent.setCenter(new MenuUsers());
		borderPaneContent.getLeft().setViewOrder(1);
		borderPaneContent.getCenter().setViewOrder(2);
	}

	/**
	 * Method to open the person's display window
	 *
	 * @param event
	 */
	@FXML
	void toMenuPerson() {

		if (borderPaneContent.getCenter() != null) {
			borderPaneContent.setCenter(null);
		}
		borderPaneContent.setCenter(new MenuPerson(userSelected));
		borderPaneContent.getLeft().setViewOrder(1);
		borderPaneContent.getCenter().setViewOrder(2);
	}

	/**
	 * Method to change the stylesheet to DarkTheme/LightTheme
	 *
	 * @param event
	 */
	@FXML
	void changeStyleSheet(ActionEvent event) {

		if (borderPaneContent.getScene().getStylesheets().contains(lightStyle)) {
			borderPaneContent.getScene().getStylesheets().clear();
			borderPaneContent.getScene().getStylesheets().add(darkStyle);
		} else if (borderPaneContent.getScene().getStylesheets().contains(darkStyle)) {
			borderPaneContent.getScene().getStylesheets().clear();
			borderPaneContent.getScene().getStylesheets().add(lightStyle);
		}

	}

	/**
	 * Method to turn off the program
	 *
	 * @param event
	 */
	@FXML
	void onCloseButtonClicked(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

}
