package cripel.jobway.ui.event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.dialog.ProgressDialog;

import cripel.jobway.dao.EmployeeDAO;
import cripel.jobway.dao.EventDAO;
import cripel.jobway.dao.EventTypeDAO;
import cripel.jobway.dao.ThemeDAO;
import cripel.jobway.exportexcel.ImportEvent;
import cripel.jobway.exportexcel.PoiTableViewExcel;
import cripel.jobway.model.Employee;
import cripel.jobway.model.Event;
import cripel.jobway.model.EventType;
import cripel.jobway.model.Person;
import cripel.jobway.model.Theme;
import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import cripel.jobway.utilities.fxutil.Confirm;
import cripel.jobway.utilities.fxutil.DatePickerUtil;
import cripel.jobway.utilities.fxutil.DurationPicker;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * The Controller Class Event manager to manage the person's event
 */
public class EventManager extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** Label of sum Duration*/
	/*@FXML
	private Label totalMinuteLabel;
	*/
	
	/** The column for the event's date */
	@FXML
	private TableColumn<Event, Date> columnDate;

	/** The column for the theme's name */
	@FXML
	private TableColumn<Theme, String> columnTheme;

	/** The column for the employee's name */
	@FXML
	private TableColumn<Event, String> tableColumnEmployee;

	/** The column for the event's notes */
	@FXML
	private TableColumn<Event, String> columnNotes;

	/** The column for the button edit */
	@FXML
	private TableColumn<Event, Button> columnEdit;

	/** The column for the button delete */
	@FXML
	private TableColumn<Event, Button> columnDelete;

	/** The tableview Event */
	@FXML
	private TableView<Event> tableView;

	/** The column for the event's duration */
	@FXML
	private TableColumn<Event, String> columnDuration;

	/** The column for the event's type */
	@FXML
	private TableColumn<Event, String> columnType;

	/** The combobox for the theme */
	@FXML
	private ComboBox<Theme> comBoTheme;

	/** The combobox for the theme type */
	@FXML
	private ComboBox<EventType> comboType;

	/** The checkcombobox for the employee */
	@FXML
	private CheckComboBox<Employee> comboEmp;
	
	/** The button to choose is this is the exit event*/
	@FXML
	private RadioButton buttonExit;

	/** The datepicker for the event date */
	@FXML
	private DatePicker datePickerEvent;

	/** The button to add a new theme */
	@FXML
	private Button buttonAddNewTheme;

	/** The buton to add the current theme */
	@FXML
	private Button buttonAddCurrentTheme;

	/** The button to modify the current theme */
	@FXML
	private Button buttonModifyCurentTheme;

	/** The button to save */
	@FXML
	private Button buttonSaveAll;

	/** The spinner for the duration (hours) */
	@FXML
	private Spinner<Integer> spinnerHour;

	/** The spinner for the duration (minutes) */
	@FXML
	private Spinner<Integer> spinnerMinute;

	/** The datepicker for the begin date */
	@FXML
	private DatePicker datePickerBegin;

	/** The date picker for the end date */
	@FXML
	private DatePicker datePickerEnd;

	/** The vbox of the edition area */
	@FXML
	private VBox vBoxEdit;

	/** The vbox that display the content */
	@FXML
	private VBox vBoxDisplay;

	/** The customTextfield for the searchbar */
	@FXML
	private CustomTextField searchBar;

	/** The hbox for the filters */
	@FXML
	private HBox hBoxFilter;

	/** The togglebutton for the filters */
	@FXML
	private ToggleButton toggleFilter;

	/** The textarea for the notes */
	@FXML
	private TextArea textAreaNotes;

	/** The combobox to filter by employee's name */
	@FXML
	private ComboBox<Employee> comboBoxFilterEmployee;

	/** The combobox to filter by theme's name */
	@FXML
	private ComboBox<Theme> comboBoxFilterTheme;

	/** The combobox to filter by event's type */
	@FXML
	private ComboBox<EventType> comboBoxFilterType;
	

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Person owner of the {@link Event}s being managed
	 */
	private Person selected;

	/**
	 * Observable list of event
	 */
	private ObservableList<Event> listEvent = FXCollections.observableArrayList();
	private FilteredList<Event> listFil;

	/**
	 * Object event used for the edit
	 */
	private Event editEvent = null;
	/**
	 * Boolean to know if there is modification without saving
	 */
	private boolean saveState = true;

	/**
	 * The object confirm to create the PopUp Confirm
	 */
	private Confirm confirm = new Confirm();
	
	/**Object that manage the transition */
	private EventManagerTransition transition;

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public EventManager() {
		load();
	}

	/**
	 * Constructor of person's event
	 *
	 * @param person
	 */
	public EventManager(Person person) {
		load();
		loadTransition();
		selected = person;
		setup(person);
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
		
		//sumColumnDuration();
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup a person's fields
	 *
	 * @param person
	 */
	public void setup(Person person) {
		setupTableColumn();
		listEvent.addAll(person.getEvents());
		listFil = new FilteredList<>(listEvent);
		tableView.getItems().addAll(listEvent);
		setupComboBox();
		setupSpinner();
		filterDate();
		vBoxEdit.setManaged(false);
		vBoxEdit.setVisible(false);
		columnDate.setSortType(TableColumn.SortType.ASCENDING);
		tableView.getSortOrder().add(columnDate);
		tableView.sort();
		focusDatePicker();

	}

	/**
	 * Method to fill the event's fields
	 *
	 * @param event
	 */
	private void fillField(Event event) {

		if (event != null) {
			comboEmp.getCheckModel().clearChecks();
			textAreaNotes.setText(event.getEventNote());
			comBoTheme.getSelectionModel().select(event.getTheme());
			datePickerEvent.setValue(DateUtil.convertToLocalDate(event.getEventDate()));

			if (event.getEventDuration() != null) {
				spinnerHour.getValueFactory().setValue(event.getEventDuration() / 60);
				spinnerMinute.getValueFactory().setValue(event.getEventDuration() % 60);
			}

			comboType.getSelectionModel().select(event.getEventType());
			for (Employee emp1 : event.getEmployees()) {
				for (Employee emp2 : comboEmp.getItems()) {
					if (emp1.equals(emp2))
						comboEmp.getCheckModel().check(emp1);
				}
			}

		}
	}

	/**
	 * Method to disable buttons and enable thje new theme fields
	 */
	private void disableButtonsAndEnableNewThemeFields() {
		buttonAddNewTheme.setDisable(true);
		buttonSaveAll.setDisable(true);
	}

	/**
	 * Method to enable buttons and disable the new theme fields
	 */
	private void enableButtonsAndDisableNewThemFields() {
		buttonAddNewTheme.setDisable(false);
		buttonSaveAll.setDisable(false);

	}
	
	private void allFilter() {
		
	}
	
	/**
	 * Fill the tableview if the date filter correspond with the data
	 */
	private void filterDate() {

		listFil.predicateProperty().bind(Bindings.createObjectBinding(() -> event -> {
			if ((datePickerBegin.getValue() == null) ||
			(event.getEventDate().compareTo(DateUtil.convertToDate(datePickerBegin.getValue())) >= 0)
				&& (datePickerEnd.getValue() == null) ||
			event.getEventDate().compareTo(DateUtil.convertToDate(datePickerBegin.getValue())) >= 0
			&& (event.getEventDate().compareTo(DateUtil.convertToDate(datePickerEnd.getValue())) <= 0))
				if (setupSearchBar(event))
					if (filterComboBox(event))
						return true;
		

			return false;
		}, datePickerBegin.valueProperty(), datePickerEnd.valueProperty(), searchBar.textProperty(),
				comboBoxFilterTheme.valueProperty(), comboBoxFilterType.valueProperty(),
				comboBoxFilterEmployee.valueProperty()));

		SortedList<Event> sortedData = new SortedList<>(listFil);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedData);
	}

	/**
	 * Boolean return true if the search bar has at least one letter of the property
	 * in the table view
	 *
	 * @param event
	 * @return boolean
	 */
	private boolean setupSearchBar(Event event) {

		if (searchBar.getText() == null || searchBar.getText().isEmpty()) {
			return true;
		}

		if (String.valueOf(event.getEmployees().toString()).toLowerCase().contains(searchBar.getText().toLowerCase())) {
			return true;
		}

		else if (String.valueOf(event.getEventNote()).toLowerCase().contains(searchBar.getText().toLowerCase())) {
			return true;
		}

		else if (String.valueOf(event.getTheme().getThemeName()).toLowerCase()
				.contains(searchBar.getText().toLowerCase())) {
			return true;
		}

		else if (String.valueOf(event.getEventType()).toLowerCase().contains(searchBar.getText().toLowerCase())) {
			return true;
		}

		return false;

	}

	/**
	 * Method to filter the datas through comboboxes
	 *
	 * @param event
	 * @return
	 */
	private boolean filterComboBox(Event event) {
		if ((comboBoxFilterTheme.getValue() == null) ||  (event.getTheme() != null && comboBoxFilterTheme.getSelectionModel().getSelectedItem()
				.getIdTheme().equals(event.getTheme().getIdTheme())))  {
			if (comboBoxFilterType.getValue() == null || event.getEventType() != null && comboBoxFilterType
					.getSelectionModel().getSelectedItem().getIdEventType() == (event.getEventType().getIdEventType()))
				if (comboBoxFilterEmployee.getValue() == null
						|| event.getEmployees().contains(comboBoxFilterEmployee.getSelectionModel().getSelectedItem()))
					return true;
		}
		return false;
	}

	/**
	 * Check the value in date picker editor and replace by a valid date if focus is
	 * lost
	 */
	public void focusDatePicker() {
		DatePickerUtil.listenerValidDate(datePickerBegin, datePickerEnd, datePickerEvent);
		DatePickerUtil.setLimit(datePickerBegin, datePickerEnd);
	}

	/**
	 * Set value limit to spinner and parse value to avoid inputing non digit
	 */
	private void setupSpinner() {
		spinnerHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 1));
		spinnerMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0, 1));
		DurationPicker.avoidString(spinnerHour,4);
		DurationPicker.avoidString(spinnerMinute, 3);
		DurationPicker.linkSpinner(spinnerMinute, spinnerHour, 60);

	}

	/**
	 * Method to set all table column property
	 */
	private void setupTableColumn() {
		columnDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
		columnTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
		tableColumnEmployee.setCellValueFactory(
				cdf -> new SimpleStringProperty(cdf.getValue().getEmployees().toString().replaceAll("\\[|\\]", "")));
		columnNotes.setCellValueFactory(new PropertyValueFactory<>("eventNote"));
		columnDuration.setCellValueFactory(new PropertyValueFactory<>("eventDuration"));
		columnType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
		columnEdit.setCellFactory(ButtonTableCell.<Event>forTableColumn(null, "button-edit", "far-edit", (Event e) -> {
			vBoxEdit.setManaged(true);
			vBoxEdit.setVisible(true);
			buttonModifyCurentTheme.setVisible(true);
			editEvent = e;
			fillField(e);
			return e;
		}));

		columnDelete.setCellFactory(
				ButtonTableCell.<Event>forTableColumn(null, "button-delete", "fas-trash-alt", (Event e) -> {

					if (confirm.confirmPopUp(true)) {
						listEvent.remove(e);
						e.getEmployees().clear();
					}

					return e;
				}));

		columnDate.setCellFactory(column -> 
			new TableCell<>() {
				private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(format.format(item));
					}
				}
			
		});

	}

	/**
	 * Fill every combo box with data from the database Also add a null to deselect
	 * a filter
	 */
	private void setupComboBox() {
		comboEmp.getItems().addAll(new EmployeeDAO().getListCustom("From Employee e WHERE e.empIsDelete=0"));
		comboBoxFilterEmployee.getItems().add(null);
		comboBoxFilterEmployee.getItems().addAll(comboEmp.getItems());
		comBoTheme.getItems().addAll(new ThemeDAO().getList());
		comboBoxFilterTheme.getItems().add(null);
		comboBoxFilterTheme.getItems().addAll(comBoTheme.getItems());
		comboType.getItems().addAll(new EventTypeDAO().getList());
		comboBoxFilterType.getItems().add(null);
		comboBoxFilterType.getItems().addAll(comboType.getItems());

	}

	/**
	 * Method to clear all fields
	 */
	private void clearFields() {
		datePickerEvent.setValue(null);
		comBoTheme.setValue(null);
		spinnerHour.getValueFactory().setValue(0);
		spinnerMinute.getValueFactory().setValue(0);
		comboType.setValue(null);
		comboEmp.getCheckModel().clearChecks();
		textAreaNotes.clear();
	}

	/**
	 * Compare the person list with the current list to delete removed entry
	 */
	private void deleteEvent(EventDAO dao) {
		for (Event object : CollectionUtils.subtract(selected.getEvents(), listEvent)) {
			dao.remove(object,false);
		}
	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method add a new event
	 */
	@FXML
	void actionAdd() {

		if (datePickerEvent.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");

			alert.setContentText("La date doit être valide !");

			alert.showAndWait();
		} else if (comBoTheme.getValue() == null)

		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");

			alert.setContentText("La thématique ne peut pas être vide");

			alert.showAndWait();
		} else {
			transition.startAddEventAnimation();
			vBoxEdit.setVisible(false);
			vBoxEdit.setManaged(false);
			columnEdit.setVisible(true);
			columnDelete.setVisible(true);

			Event eve = new Event();
			eve.getEmployees().addAll(comboEmp.getCheckModel().getCheckedItems());
			eve.setEventNote(textAreaNotes.getText());

			eve.setEventDate(DateUtil.convertToDate(datePickerEvent.getValue()));
			eve.setEventDuration(spinnerHour.getValue() * 60 + spinnerMinute.getValue());
			eve.setEventType(comboType.getSelectionModel().getSelectedItem());
			eve.setPerson(selected);
			eve.setTheme(comBoTheme.getSelectionModel().getSelectedItem());
			if(buttonExit.isSelected())
				eve.setExit(true);
			listEvent.add(eve);
			tableView.refresh();

			enableButtonsAndDisableNewThemFields();

			clearFields();
			buttonAddCurrentTheme.setVisible(false);
			tableView.getSelectionModel().clearSelection();
			tableView.sort();
			
		}
		
		saveState = false;

	}

	/**
	 * Modify the selected event and then hide the vbox which contains the fields
	 *
	 * @param event unused
	 */
	@FXML
	void actionModify(ActionEvent event) {

		if (datePickerEvent.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");

			alert.setContentText("La date doit être valide !");

			alert.showAndWait();
		} else {

			editEvent.getEmployees().clear();
			editEvent.getEmployees().addAll(comboEmp.getCheckModel().getCheckedItems());
			editEvent.setEventNote(textAreaNotes.getText());
			editEvent.setEventDate(DateUtil.convertToDate(datePickerEvent.getValue()));
			editEvent.setEventDuration(spinnerHour.getValue() * 60 + spinnerMinute.getValue());
			editEvent.setEventType(comboType.getSelectionModel().getSelectedItem());
			editEvent.setPerson(selected);
			editEvent.setTheme(comBoTheme.getSelectionModel().getSelectedItem());

			enableButtonsAndDisableNewThemFields();

			clearFields();
			buttonModifyCurentTheme.setVisible(false);
			tableView.refresh();

			editEvent = null;
			vBoxEdit.setVisible(false);
			vBoxEdit.setManaged(false);
			saveState = false;
		}

	}

	/**
	 * Method to add a new theme
	 *
	 * @param event
	 */
	@FXML
	void addNewTheme(ActionEvent event) {
		columnEdit.setVisible(false);
		columnDelete.setVisible(false);
		clearFields();
		disableButtonsAndEnableNewThemeFields();
		buttonAddCurrentTheme.setVisible(true);
		comboEmp.getCheckModel().check(selected.getEmployee());
		vBoxEdit.setVisible(true);
		vBoxEdit.setManaged(true);
		transition.startAddEventAnimation();
	}

	@FXML
	private void exportExcel() {
		try {
			DirectoryChooser dir = new DirectoryChooser();
			File file = dir.showDialog(this.getScene().getWindow());
			if (file != null) {
				XSSFWorkbook wb = new XSSFWorkbook();
				new PoiTableViewExcel(tableView, wb).exportExcel();
				OutputStream output = new FileOutputStream(
						file.getAbsolutePath() + File.separator + DateUtil.dateTimeFile() + '_' + "Suivi_"
								+ selected.getPersonFirstName() + '_' + selected.getPersonLastName() + ".xlsx");
				wb.write(output);
				wb.close();
				output.close();
				Notifications.create().title("Info").text("Export terminé").showInformation();
			}
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getCause().getLocalizedMessage());
			alert.showAndWait();
		}
	}

	@FXML
	private void importExcel() {
		ImportEvent importEvent = new ImportEvent(this.getScene(), listEvent, comBoTheme.getItems(),
				comboEmp.getItems(), comboType.getItems(), selected);
		importEvent.importToList();
		if (!importEvent.getErrorState().isBlank()) {
			Alert alert = new Alert(AlertType.WARNING, importEvent.getErrorState());
			alert.showAndWait();
		}
		saveState = false;

	}

	/**
	 * Method to save a new event or delete an old one
	 *
	 * @param eve
	 */
	@FXML
	private void actionSave(ActionEvent eve) {
		EventDAO dao = new EventDAO();
		
		Task<Void> task = new Task<>() {

			@Override
			protected Void call() throws Exception {
				int progress=0;
				for (Event event : listEvent) {
					dao.saveOrUpdate(event,false);
					updateProgress(progress++,listEvent.size());
					updateMessage("Ligne : "+progress);
				}
				
				deleteEvent(dao);
				return null;
			}
			
		};
		
		task.setOnSucceeded(event->Notifications.create().title("Info").text("Sauvegarde effectuée").showInformation());
		if(listEvent.size()>20) {
			ProgressDialog progressDialog=new ProgressDialog(task);
			progressDialog.setTitle("Sauvegarde");
			progressDialog.setHeaderText("Sauvegarde en cours");
			progressDialog.show();
		}
		Thread taskThread=new Thread(task);
		taskThread.start();



		Stage s = (Stage) ((Node) eve.getSource()).getScene().getWindow();
		s.close();

		saveState = true;
	}

	/**
	 * Clear the filters
	 */
	@FXML
	private void clearFilter() {
		datePickerBegin.setValue(null);
		datePickerEnd.setValue(null);
		comboBoxFilterTheme.setValue(null);
		comboBoxFilterType.setValue(null);
		comboBoxFilterEmployee.setValue(null);
		searchBar.setText(null);

	}

	/**
	 * Right node {@link CustomTextField} button which clear the search bar
	 */
	@FXML
	private void actionClearSearchBar() {
		searchBar.clear();
	}

	/**
	 * Cancel the current action
	 */
	@FXML
	private void actionCancel() {
		transition.startAddEventAnimation();
		vBoxEdit.setVisible(false);
		vBoxEdit.setManaged(false);
		columnEdit.setVisible(true);
		columnDelete.setVisible(true);
		editEvent = null;
		buttonModifyCurentTheme.setVisible(false);

		buttonAddCurrentTheme.setVisible(false);
		enableButtonsAndDisableNewThemFields();
		clearFields();
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************
	
	public void loadTransition() {
		transition= new EventManagerTransition();
		transition.addEventAnimation(tableView, vBoxEdit);
	}
	
	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventManager.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		
	}

	public boolean getSaveState() {
		return saveState;
	}
	
	/**
	 *  Method to Sum all ColumnDuration divide by 60
	 */
	 
	/*public void sumColumnDuration() {
			
		
		DoubleBinding totalMinute = Bindings.createDoubleBinding(() -> {
		    double total = 0;
		    for (Event event : tableView.getItems()) {
		        total += event.getEventDuration();
		    }
		    return total;
		}, tableView.getItems());
		
		DoubleBinding divisionTotalMinute = totalMinute.divide(60.0);

		totalMinuteLabel.textProperty().bind(divisionTotalMinute.asString());
	}*/
	
}
