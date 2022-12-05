package cripel.jobway.ui.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.controlsfx.dialog.ProgressDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import cripel.jobway.dao.UsersDAO;
import cripel.jobway.model.User;
import cripel.jobway.utilities.PropertiesUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to connect */
	@FXML
	private Button buttonConnect;

	/** The button to leave */
	@FXML
	private Button buttonLeave;

	/** The textfield to enter the password */
	@FXML
	private PasswordField textFieldPassword;

	/** The textfield to enter the user name */
	@FXML
	private TextField textFieldUserName;

	/** The label to display login's info */
	@FXML
	private Label labelInfoLogin;

	/** The borderpane for the scene */
	@FXML
	private BorderPane borderPane;
	/**Checkbox to remember username when starting */
	@FXML
	private CheckBox checkBoxRememberMe;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/** The boolean to verify if the login is ok */
	private Boolean isOk = false;

	/**
	 * User used in the login
	 */
	private User user;
	
	private Properties prop;
	
	private static Logger logger = LoggerFactory.getLogger(Login.class);
	private static final String PATH = System.getProperty("user.home") + File.separator + "JobWay" + File.separator + "login.properties";

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public Login() {
		load();
		readProperties();
	}

	// **************************************************************************************************
	// FXML METHODS
	// **************************************************************************************************
	/**
	 * Javafx button which that launch a task to {@link #checkConnexion(String, Pbkdf2PasswordEncoder)}
	 * and show the progress of the connexion then send the appropriate message depending on task success
	 */
	@FXML
	void connexion(ActionEvent event) {

		String userName = textFieldUserName.getText();

		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("curry");

		Task<Boolean> task = new Task<>() {

			@Override
			protected Boolean call() throws Exception {
				return checkConnexion(userName, pbkdf2PasswordEncoder);

			}

		};
		task.setOnSucceeded(ev -> {
			if (Boolean.TRUE.equals(task.getValue()))
				((Stage) this.getScene().getWindow()).close();

			else {
				Alert alert = new Alert(AlertType.INFORMATION,
						"Le nom d'utilisateur ou le mot de passe est incorrect.");
				alert.showAndWait();
				logger.info("Connexion failed :");
			}
		});

		task.setOnFailed(ev -> {
			Alert alert = new Alert(AlertType.INFORMATION, "Connexion à la base de données impossible.");
			alert.showAndWait();
		});

		ProgressDialog progressDialog = new ProgressDialog(task);
		progressDialog.setTitle("Connexion");
		progressDialog.setHeaderText(null);
		progressDialog.initOwner(this.getScene().getWindow());
		progressDialog.setContentText("Connexion en cours");
		progressDialog.show();
		Thread thread = new Thread(task);

		thread.start();

	}

	/**
	 * Check if user credential are correct
	 * @param userName
	 * @param pbkdf2PasswordEncoder
	 * @return the connection success state
	 */
	private Boolean checkConnexion(String userName, Pbkdf2PasswordEncoder pbkdf2PasswordEncoder) {
		
		user=UsersDAO.selectSingleByUserName(userName);
		
		if (user!=null && pbkdf2PasswordEncoder.matches(textFieldPassword.getText(), user.getUserPassword())) {
				isOk = true;
				logger.info("Connexion success : " + user.getUserName());
				saveProperties(userName);
				return true;
			} 
		return false;
	}

	/**
	 * Javafx button to leave the application
	 *
	 * @param event unused
	 */
	@FXML
	void leave(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	/**
	 * Javafx button which open a parameters window
	 */
	@FXML
	void toParameters() {
		Scene scene = new Scene(new LoginParameters());
		Stage stage = new Stage();

		stage.setTitle("Paramètres");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	
	
	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method that return isOk when the login is verified
	 *
	 * @return
	 */
	public Boolean verifyLogin() {
		return isOk;
	}
	
	/**
	 * Read login.properties info
	 */
	private void readProperties() {
		
		if (!PropertiesUtil.fileIsCreated(PATH)) {
			prop=PropertiesUtil.loginDefaultConfig();
		}
		else {
			prop=PropertiesUtil.loadProperties(PATH);
		}
		
		if(prop.getProperty("REMEMBERME").equals("true")) {
			checkBoxRememberMe.setSelected(true);
			textFieldUserName.setText(prop.getProperty("USERNAME"));
			Platform.runLater(()->textFieldPassword.requestFocus());
		}
	}
		/**
		 * Save user info after a successful login
		 */
	private void saveProperties(String userName) {
		try(OutputStream output= new FileOutputStream(PATH)) {
		
		prop.setProperty("REMEMBERME", String.valueOf(checkBoxRememberMe.isSelected()));
		if(checkBoxRememberMe.isSelected()) {
			prop.setProperty("USERNAME", userName);
			
		}
		else {
			prop.setProperty("USERNAME", "");
		}
		
			prop.store(output, "Login info");
		} catch (IOException e) {
			
			logger.error("Erreur écriture login.properties",e);
		}
	}


	// **************************************************************************************************
	// LOAD METHODS
	// **************************************************************************************************
	/**
	 * Method to load fxml and set the controller
	 */
	private void load() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	// **************************************************************************************************
	// SETTERS
	// **************************************************************************************************

	public void setUser(User user) {
		this.user = user;
	}

	// **************************************************************************************************
	// GETTERS
	// **************************************************************************************************

	public User getUser() {
		return user;
	}

}
