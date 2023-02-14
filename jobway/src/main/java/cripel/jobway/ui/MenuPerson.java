package cripel.jobway.ui;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import cripel.jobway.dao.EmployeeDAO;
import cripel.jobway.dao.PersonDAO;
import cripel.jobway.dao.WorkSectorDAO;
import cripel.jobway.model.Employee;
import cripel.jobway.model.Person;
import cripel.jobway.model.User;
import cripel.jobway.model.WorkSector;
import cripel.jobway.ui.event.EventManager;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import cripel.jobway.utilities.fxutil.Confirm;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class Menu Person to manage the persons
 */
public class MenuPerson extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The column for the person's id */
	@FXML
	private TableColumn<Person, Integer> columnId;

	/** The column for the person first name */
	@FXML
	private TableColumn<Person, String> columnFirstName;

	/** The column for the person last name */
	@FXML
	private TableColumn<Person, String> columnLastName;

	/** The column for the person niss */
	@FXML
	private TableColumn<Person, Integer> columnNiss;

	/** The column for the employee's name */
	@FXML
	private TableColumn<Person, String> columnEmployee;

	/** The column for the file status */
	@FXML
	private TableColumn<Person, String> columnFileStatus;

	/** The column for the delete button */
	@FXML
	private TableColumn<Person, Button> columnDel;

	/** The column for the edit button */
	@FXML
	private TableColumn<Person, Button> columnEdit;
	@FXML
	private TableColumn<Person, Button> columnPermanentDel;

	/** The column for the person history */
	@FXML
	private TableColumn<Person, Button> columnHist;

	/** The tableview person */
	@FXML
	private TableView<Person> tableViewPerson;

	/** The customtextfield used for the searchBar */
	@FXML
	private CustomTextField searchTextField;

	/** The checkbox to display deleted person */
	@FXML
	private CheckBox checkBoxDeleted;

	/** The button to create a new person */
	@FXML
	private Button buttonCreatePerson;

	/** The checkCombobox to filter by employees's name */
	@FXML
	private CheckComboBox<Employee> comboBoxEmployee;

	/** The checkcombobox to filter by work sectors */
	@FXML
	private CheckComboBox<WorkSector> comboBoxWorkSector;

	/** The combobox to filter by file status */
	@FXML
	private ComboBox<String> comboBoxFileStatus;

	/** The checkbox to filter by people looking for a job */
	@FXML
	private CheckBox checkBoxWorkSearch;

	/** The hbox for the filters */
	@FXML
	private HBox hBoxFilter;

	/** The togglebuton for the filters */
	@FXML
	private ToggleButton toggleFilter;

	/** The button to export people's data in an excel file */
	@FXML
	private Button buttonExport;

	/** The button to import people's data from an excel file */
	@FXML
	private Button buttonImport;
	
	@FXML
	private Button buttonRefresh;
	
	/**Progress indicator to show loading state of data */
    @FXML
    private ProgressIndicator progressIndicator;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Fetch query to avoid a lazily loaded exception
	 */
	private ObservableList<Person> listPerson = FXCollections.observableArrayList();
	private FilteredList<Person> listFil = new FilteredList<>(listPerson);

	/**
	 * The object confirm to create the PopUp Confirm
	 */
	Confirm confirm = new Confirm();

	/**
	 * The object user
	 */
	private User user;
	
	/** Constant for the admin level */
	private static final String ADMIN = "Administrateur";
	/**Constant to set the user denied message */
	private static final String ACCESSDENIED = "Vous n'avez pas les droits pour modifier ce dossier";
	/**Constant with a fetch query for all person */
	private static final String QUERYPERSON = "SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.employee "
			+ "LEFT JOIN FETCH p.file LEFT JOIN FETCH p.worksearch w LEFT JOIN FETCH w.workSectors ORDER BY p.idPerson";

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Constructor with parameter user
	 * @param user the user connected
	 */
	public MenuPerson(User user) {
		this.user = user;
		load();
		setup(user);
	}

	/**
	 * Setup all listener and data in javafx node in the page
	 * @param user the user connected
	 */
	private void setup(User user) {
		setUpTableView();
		setupComboBox();
		setFilter(false);
		hBoxFilter.setVisible(false);
		hBoxFilter.setManaged(false);
		toggleFilter.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if (!toggleFilter.isSelected()) {
				hBoxFilter.setVisible(false);
				hBoxFilter.setManaged(false);
			} else {
				hBoxFilter.setVisible(true);
				hBoxFilter.setManaged(true);
			}
		});

		if (user.getEmployee() != null && user.getUserlevel().getUserLevelName().contains("Utilisateur")) {
			comboBoxEmployee.getCheckModel().check(user.getEmployee());
			comboBoxFileStatus.setValue("Actif");
		}
		refresh();
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup the tableview's columns
	 */
	public void setUpTableView() {

		columnId.setCellValueFactory(new PropertyValueFactory<>("idPerson"));
		columnFirstName.setCellValueFactory(new PropertyValueFactory<>("personFirstName"));
		columnLastName.setCellValueFactory(new PropertyValueFactory<>("personLastName"));
		columnNiss.setCellValueFactory(new PropertyValueFactory<>("personNiss"));
		columnEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
		columnFileStatus.setCellValueFactory(new PropertyValueFactory<>("file"));

		columnEdit
				.setCellFactory(ButtonTableCell.<Person>forTableColumn(null, "button-edit", "far-edit", (Person p) -> {

					modify(p);
					return p;
				}));

		columnDel.setCellFactory(
				ButtonTableCell.<Person>forTableColumn(null, "button-delete", "fas-trash-alt", (Person p) -> {
					delete(p);
					return p;
				}));

		columnHist.setCellFactory(
				ButtonTableCell.<Person>forTableColumn(null, "button-edit", "far-calendar-alt", (Person p) -> {
					toEvent(p);
					return p;
				}));

		columnPermanentDel.setCellFactory(
				ButtonTableCell.<Person>forTableColumn(null, "button-delete", "fas-trash-alt", (Person p) -> {
					if (confirm.confirmPopUp(true)) {
						new PersonDAO().remove(p,true);
						listPerson.remove(p);
					}
					return p;
				}));
		tableViewPerson.setItems(listPerson);

	}
	
	/**
	 * Setup combo box with value from the database
	 */
	private void setupComboBox() {
		comboBoxEmployee.getItems().addAll(new EmployeeDAO().getListCustom("From Employee e WHERE e.empIsDelete=0"));
		comboBoxWorkSector.getItems().addAll(new WorkSectorDAO().getListDelete(false));
		comboBoxFileStatus.getItems().addAll(null, "Actif", "Archivé");

	}

	// **************************************************************************************************
	// Filter Methods
	// **************************************************************************************************

	/**
	 * Method to use in the filter to check if a String is present in first name
	 * surname or NISS of a person
	 *
	 * @param person
	 * @return boolean true or false
	 */
	public boolean setupSearchBar(Person person) {

		if (searchTextField.getText() == null || searchTextField.getText().isEmpty()) {
			return true;
		}

		String lowerCaseFilter = searchTextField.getText().toLowerCase();

		return (String.valueOf(person.getPersonFirstName()).toLowerCase().contains(lowerCaseFilter)
				|| String.valueOf(person.getPersonLastName()).toLowerCase().contains(lowerCaseFilter)
				|| String.valueOf(
						(person.getPersonLastName()).toLowerCase() + " " + person.getPersonFirstName().toLowerCase())
				.contains(lowerCaseFilter)
				|| String.valueOf(
						(person.getPersonFirstName()).toLowerCase() + " " + person.getPersonLastName().toLowerCase())
				.contains(lowerCaseFilter)
				|| String.valueOf(person.getPersonNiss()).toLowerCase().contains(lowerCaseFilter)
				|| String.valueOf(person.getPersonPhone()).toLowerCase()
				.contains(lowerCaseFilter.replaceAll("\\s", "")));

	}

	/**
	 * Method to set the person's filters. It is a if cascade to only display the
	 * persons who check all the filter using the predicate property of a filtered
	 * list
	 *
	 * @param filter
	 */
	public void setFilter(boolean filter) {
		listFil.predicateProperty().bind(Bindings.createObjectBinding(
				//Condition
				() -> person -> ((person.isPersonIsDelete() && filter) || (!person.isPersonIsDelete() && !filter))
						&& (predicateEmployee(person)) && (setupSearchBar(person)) && (predicateWorkSector(person))
						&& (predicateWorkSearch(person)) && (checkFileStatus(person)),
				//Property to bind
				searchTextField.textProperty(), checkBoxDeleted.selectedProperty(),
				comboBoxEmployee.getCheckModel().getCheckedItems(), checkBoxWorkSearch.selectedProperty(),
				comboBoxWorkSector.getCheckModel().getCheckedItems(), comboBoxFileStatus.valueProperty()));

		SortedList<Person> sortedData = new SortedList<>(listFil);
		sortedData.comparatorProperty().bind(tableViewPerson.comparatorProperty());
		tableViewPerson.setItems(sortedData);

	}
	
	
	private boolean predicateEmployee(Person person) {
		return comboBoxEmployee.getCheckModel().getCheckedItems().isEmpty() || (person.getEmployee() != null
				&& comboBoxEmployee.getCheckModel().getCheckedItems().contains(person.getEmployee()));
	}
	
	private boolean predicateWorkSector(Person person) {
		return comboBoxWorkSector.getCheckModel().getCheckedItems().isEmpty()
				|| CollectionUtils.containsAny(comboBoxWorkSector.getCheckModel().getCheckedItems(),
						person.getWorksearch().getWorkSectors());
	}
	private boolean predicateWorkSearch(Person person) {
		return !checkBoxWorkSearch.isSelected() || (checkBoxWorkSearch.isSelected()
				&& person.getWorksearch().isSearching() != null && person.getWorksearch().isSearching());
	}

	private boolean checkFileStatus(Person person) {
		return (comboBoxFileStatus.getValue() == null || person.getFile().getFileStatus() != null
				&& person.getFile().getFileStatus().contains(comboBoxFileStatus.getValue()));

	}


	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method to open a new window to create a new Person
	 *
	 * @see MenuForms
	 * @param event
	 */
	@FXML
	void newPerson(ActionEvent event) {

		Scene scene = new Scene(new MenuForms(user));
		Stage stage = new Stage();

		scene.getStylesheets().addAll(this.getScene().getStylesheets());

		stage.setMinWidth(1100);
		stage.setMinHeight(800);
		stage.setWidth(1100);
		stage.setHeight(800);

		stage.setTitle("Encodage");
		stage.setScene(scene);
		stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
		stage.initOwner((this.getScene().getWindow()));
		stage.setOnCloseRequest((windowEvent) -> {
			if (!Confirm.confirmSave()) {
				windowEvent.consume();
			}

		});
		stage.showAndWait();
		refresh();

	}

	/**
	 * {@link #buttonExport} on action to open a dialog to {@link ExcelDialog}
	 *
	 */
	@FXML
	private void excelExport() {
		Scene scene = new Scene(new ExcelDialog(tableViewPerson.getItems()));
		Stage stage = new Stage();

		scene.getStylesheets().addAll(this.getScene().getStylesheets());
		stage.setResizable(false);

		stage.setTitle("Export");
		stage.setScene(scene);

		stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
		stage.initOwner((this.getScene().getWindow()));

		stage.showAndWait();
	}

	/**
	 * {@link #buttonImport} on action to open a dialog to {@link ExcelDialog}
	 *
	 */
	@FXML
	private void excelImport()
	{

	}

	/**
	 * Method to modify an existing person Open a dialog to
	 * {@link MenuForms#MenuForms(Person, User)}
	 */
	void modify(Person person) {

		if (person.getEmployee() != null
				&& !user.getEmployee().getIdEmployee().equals(person.getEmployee().getIdEmployee())
				&& !user.getUserlevel().getUserLevelName().contains(ADMIN)
				&& !user.getUserlevel().getUserLevelName().contains("Editeur")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText(ACCESSDENIED);
			alert.initOwner((this.getScene().getWindow()));
			alert.showAndWait();
		} else {
			try {

				Scene scene = new Scene(new MenuForms(person, user));
				Stage stage = new Stage();

				scene.getStylesheets().addAll(this.getScene().getStylesheets());

				stage.setMinWidth(1100);
				stage.setMinHeight(800);
				stage.setWidth(1100);
				stage.setHeight(800);

				stage.setTitle("Modification");
				stage.setScene(scene);
				stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
				stage.initOwner(this.getScene().getWindow());
				stage.setOnCloseRequest((windowEvent) -> {
					if (!Confirm.confirmSave()) {
						windowEvent.consume();
					}

				});
				stage.showAndWait();

			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

	}

	/**
	 * Method to open the event window
	 *
	 * @param person
	 */
	void toEvent(Person person) {

		if (person.getEmployee() != null && !user.getEmployee().getIdEmployee().equals(person.getEmployee().getIdEmployee())
				&& !user.getUserlevel().getUserLevelName().contains(ADMIN)
				&& !user.getUserlevel().getUserLevelName().contains("Editeur")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText(ACCESSDENIED);
			alert.initOwner((this.getScene().getWindow()));
			alert.showAndWait();
		} else

		{
			try {
				EventManager eventManager = new EventManager(person);
				Scene scene = new Scene(eventManager);
				Stage stage = new Stage();
				scene.getStylesheets().addAll(this.getScene().getStylesheets());

				stage.setMinWidth(800);
				stage.setMinHeight(600);

				stage.setTitle("Fiche de suivi  - " + person.getPersonFirstName() + " " + person.getPersonLastName());
				stage.setScene(scene);
				stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
				stage.initOwner((this.getScene().getWindow()));
				stage.setOnCloseRequest((windowEvent) -> {
					if (!eventManager.getSaveState() && !Confirm.confirmSave()) {
						windowEvent.consume();
					}
				});
				stage.showAndWait();
				refresh();

			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

	}

	/**
	 * Method to delete an existing person
	 *
	 * @param person
	 */
	void delete(Person person) {

		if (person.getEmployee() != null
				&& !user.getEmployee().getIdEmployee().equals(person.getEmployee().getIdEmployee())
				&& !user.getUserlevel().getUserLevelName().contains(ADMIN)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText(ACCESSDENIED);
			alert.initOwner((this.getScene().getWindow()));
			alert.showAndWait();
		} else {
			if (confirm.confirmPopUp(true)) {
				if (Boolean.TRUE.equals(person.isPersonIsDelete())) {
					person.setPersonIsDelete(false);
					person.getFile().setFileStatus("Actif");
					new PersonDAO().saveOrUpdate(person,true);
					setFilter(true);
				} else {
					person.setPersonIsDelete(true);
					person.getFile().setFileStatus("Supprimé");
					new PersonDAO().saveOrUpdate(person,true);
					setFilter(false);
				}
			}

		}

		tableViewPerson.refresh();

	}

	/**
	 * Method to refresh manually the tableview
	 * 
	 */
	@FXML
	void refresh() {

		progressIndicator.setVisible(true);
		buttonRefresh.setDisable(true);
		Task<Void> taskRefresh = new Task<>() {
			private final List<Person> listPersonTask = FXCollections.observableArrayList();

			@Override
			protected Void call() throws Exception {
				listPersonTask.addAll(FXCollections.observableArrayList(new PersonDAO().getListCustom(QUERYPERSON)));
				Platform.runLater(() -> {
					listPerson.clear();
					listPerson.addAll(listPersonTask);
				});
				return null;
			}

		};
		taskRefresh.setOnSucceeded(ev -> {
			buttonRefresh.setDisable(false);
			progressIndicator.setVisible(false);
			progressIndicator.progressProperty().unbind();
			tableViewPerson.refresh();
		});
		progressIndicator.progressProperty().bind(taskRefresh.progressProperty());
		Thread thread = new Thread(taskRefresh);
		thread.start();

	}

	/**
	 * Method to display a person deleted when checking the checkbox
	 *
	 * @param event
	 */
	@FXML
	void displayPersonDeleted(ActionEvent event) {

		if (checkBoxDeleted.isSelected()) {

			columnDel.setCellFactory(ButtonTableCell.<Person>forTableColumn(null, "button-delete",
					"fas-trash-restore-alt", (Person p) -> {
						delete(p);
						return p;
					}));

			if (user.getUserlevel().getUserLevelName().contains(ADMIN))
				columnPermanentDel.setVisible(true);

			setFilter(true);

		} else {

			columnDel.setCellFactory(
					ButtonTableCell.<Person>forTableColumn(null, "button-delete", "fas-trash-alt", (Person p) -> {
						delete(p);
						return p;
					}));

			columnPermanentDel.setVisible(false);
			setFilter(false);

		}

	}

	/**
	 * Method to clear the searchbar
	 */
	@FXML
	void actionClearSearchBar() {
		searchTextField.clear();
	}

	/**
	 * Clear the filters
	 */
	@FXML
	private void clearFilter() {
		comboBoxEmployee.getCheckModel().clearChecks();
		comboBoxFileStatus.setValue(null);
		comboBoxWorkSector.getCheckModel().clearChecks();
		checkBoxDeleted.setSelected(false);
		checkBoxWorkSearch.setSelected(false);
		setFilter(false);
	}
	
	/**
	 * Event handler that opens {@link Person} information on double click
	 * @param event the mouse event
	 */
	@FXML
	private void openPersonDisplayOnMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && tableViewPerson.getSelectionModel().getSelectedItem() != null) {
			Scene scene = new Scene(new PersonDisplay(tableViewPerson.getSelectionModel().getSelectedItem()));
			Stage stage = new Stage();

			scene.getStylesheets().addAll(this.getScene().getStylesheets());

			stage.setMinWidth(800);
			stage.setMinHeight(800);
			stage.setWidth(800);
			stage.setHeight(800);
			stage.setMaxWidth(800);

			stage.setTitle("Aperçu");
			stage.setScene(scene);
			stage.getIcons().addAll(((Stage) this.getScene().getWindow()).getIcons());
			stage.initOwner((this.getScene().getWindow()));
			stage.showAndWait();

		}
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuPerson.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
