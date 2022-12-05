package cripel.jobway.ui;

import java.io.IOException;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import cripel.jobway.dao.UsersDAO;
import cripel.jobway.dao.UsersLevelDAO;
import cripel.jobway.model.Employee;
import cripel.jobway.model.User;
import cripel.jobway.model.UserLevel;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import cripel.jobway.utilities.fxutil.Confirm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * The Class menu users to manage the users
 */
public class MenuUsers extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to add a new user */
	@FXML
	private Button buttonAdd;

	/** The button to cancel the current action */
	@FXML
	private Button buttonCancel;

	/** The button to refresh the tableview */
	@FXML
	private Button buttonRefresh;

	/** The button to save */
	@FXML
	private Button buttonSave;

	/** The checkbox to display deleted users */
	@FXML
	private CheckBox checkboxUserDeleted;

	/** The combobox for the status */
	@FXML
	private ComboBox<UserLevel> comboBoxStatus;

	/** The tableview for users */
	@FXML
	private TableView<User> tableViewUserList;

	/** The textfield for the user's password */
	@FXML
	private TextField textFieldPassword;

	/** The textfield for the username */
	@FXML
	private TextField textFieldUserName;

	/** The column for the user's id */
	@FXML
	private TableColumn<User, Integer> columnId;

	/** The column for the username */
	@FXML
	private TableColumn<User, String> columnUserName;

	/** The column for the user level */
	@FXML
	private TableColumn<UserLevel, String> columnUserLevel;

	/** The column for the button delete */
	@FXML
	private TableColumn<User, Button> columnDel;

	/** The column for the button edit */
	@FXML
	private TableColumn<User, Button> columnEdit;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable lists etched from the database
	 */
	private ObservableList<User> listUser = FXCollections.observableArrayList(UsersDAO.getListUsers());

	private ObservableList<UserLevel> listUserLevel = FXCollections.observableArrayList(new UsersLevelDAO().getList());

	private FilteredList<User> listFil = new FilteredList<>(listUser);

	/**
	 * User currently selected
	 */
	private User selected;

	Confirm confirm = new Confirm();

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public MenuUsers() {
		load();
		setUpTableView();
		setUp();
		setFilter(false);

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup the tableview's columns
	 */
	public void setUpTableView() {

		columnId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
		columnUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		columnUserLevel.setCellValueFactory(new PropertyValueFactory<>("userlevel"));

		columnEdit.setCellFactory(ButtonTableCell.<User>forTableColumn(null, "button-edit", "far-edit", (User u) -> {
			modify(u);
			return u;
		}));

		columnDel.setCellFactory(
				ButtonTableCell.<User>forTableColumn(null, "button-delete", "fas-trash-alt", (User u) -> {
					deleteUser(u);
					return u;
				}));

		tableViewUserList.setItems(listUser);
	}

	/**
	 * Method to setUp the fields and comboboxes
	 */
	public void setUp() {

		selected = null;
		comboBoxStatus.setItems(listUserLevel);

	}

	/**
	 * Method to disable certains fields
	 *
	 * @param value
	 */
	private void setInfoDisable(boolean value) {
		textFieldUserName.setDisable(value);
		textFieldPassword.setDisable(value);
		comboBoxStatus.setDisable(value);

		buttonCancel.setDisable(value);
		buttonSave.setDisable(value);
	}

	/**
	 * Method to disables certains buttons
	 *
	 * @param value
	 */
	private void setListDisable(boolean value) {

		buttonAdd.setDisable(value);
		buttonRefresh.setDisable(value);

	}

	/**
	 * Method to clear certains fields
	 */
	private void clearFields() {
		textFieldUserName.clear();
		textFieldPassword.clear();
		comboBoxStatus.setValue(null);

	}

	/**
	 * Method to check if the user name & password are not empty
	 *
	 * @param bool
	 * @return
	 */
	private boolean checkFields(Boolean bool) {
		if (!textFieldUserName.getText().isEmpty() && !textFieldPassword.getText().isEmpty()
				&& comboBoxStatus.getValue() != null)
			bool = true;
		else
			bool = false;

		return bool;
	}

	/**
	 * Method to set the user's filters
	 *
	 * @param filter
	 */
	public void setFilter(boolean filter) {
		listFil.setPredicate(user -> {
			if (user.isUserIsDelete() && filter) {
				return true;
			} else if (!user.isUserIsDelete() && !filter) {
				return true;
			}
			return false;
		});

		SortedList<User> sortedData = new SortedList<>(listFil);
		sortedData.comparatorProperty().bind(tableViewUserList.comparatorProperty());
		tableViewUserList.setItems(sortedData);

	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method to create a new user
	 *
	 * @param event
	 */
	@FXML
	void addUser(ActionEvent event) {

		setInfoDisable(false);
		clearFields();

	}

	/**
	 * Method to delete the selected user
	 *
	 * @param event
	 */

	void deleteUser(User user) {

		if (confirm.confirmPopUp(true)) {

			if (user.isUserIsDelete()) {
				user.setUserIsDelete(false);
				user.getEmployee().setEmpIsDelete(false);
				new UsersDAO().saveOrUpdate(user,true);
				setFilter(true);
			} else {
				user.setUserIsDelete(true);
				user.getEmployee().setEmpIsDelete(true);
				new UsersDAO().saveOrUpdate(user,true);
				setFilter(false);
			}

			tableViewUserList.refresh();

		}

	}

	/**
	 * Method to unlock the fields needed to modify a user
	 *
	 * @param event
	 */

	void modify(User user) {

		setInfoDisable(false);
		setListDisable(true);

		textFieldUserName.setText(user.getUserName());
		textFieldPassword.setText("password");
		comboBoxStatus.setValue(user.getUserlevel());

		selected = user;

	}

	/**
	 * Method to save or update
	 *
	 * @param event
	 */
	@FXML
	void saveUser(ActionEvent event) {

		Alert alert = new Alert(AlertType.INFORMATION);

		if (checkFields(true)) {
			Employee emp;

			if (selected == null) {
				selected = new User();
				emp = new Employee();
			} else {
				emp = selected.getEmployee();
			}

			emp.setEmpPseudo(textFieldUserName.getText());

			selected.setEmployee(emp);

			selected.setUserName(textFieldUserName.getText());

			if (!textFieldPassword.getText().contains("password")) {
				Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("curry");
				String pbkdf2CryptedPassword = pbkdf2PasswordEncoder.encode(textFieldPassword.getText());
				selected.setUserPassword(pbkdf2CryptedPassword);
			}
			selected.setUserlevel(comboBoxStatus.getValue());

			new UsersDAO().saveOrUpdate(selected,true);

			setInfoDisable(true);
			setListDisable(false);

			selected = null;
			refresh();
		}

		else {
			alert.setTitle("Erreur");
			alert.setHeaderText(null);
			alert.setContentText("Tous les champs ne sont pas complet");

			alert.showAndWait();
		}

	}

	/**
	 * Method to cancel the actual modification
	 *
	 * @param event
	 */
	@FXML
	void cancel(ActionEvent event) {

		setInfoDisable(true);
		setListDisable(false);
		selected = null;

	}

	/**
	 * Method to refresh the user's tableview
	 */
	@FXML
	void refresh() {

		clearFields();
		listUser.clear();

		listUser.addAll(UsersDAO.getListUsers());
	}

	/**
	 * Method to display deleted users
	 *
	 * @param event
	 */
	@FXML
	void displayUserDeleted(ActionEvent event) {

		if (checkboxUserDeleted.isSelected()) {
			columnDel.setCellFactory(
					ButtonTableCell.<User>forTableColumn(null, "button-delete", "fas-trash-restore-alt", (User u) -> {
						deleteUser(u);
						return u;
					}));
			setFilter(true);

		} else {
			columnDel.setCellFactory(
					ButtonTableCell.<User>forTableColumn(null, "button-delete", "fas-trash-alt", (User u) -> {
						deleteUser(u);
						return u;
					}));
			setFilter(false);

		}
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuUsers.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

}
