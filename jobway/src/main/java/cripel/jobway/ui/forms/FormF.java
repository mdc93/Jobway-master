package cripel.jobway.ui.forms;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.controlsfx.control.SearchableComboBox;

import cripel.jobway.dao.WorkExpTypeDAO;
import cripel.jobway.dao.WorkSectorDAO;
import cripel.jobway.model.Person;
import cripel.jobway.model.WorkExpType;
import cripel.jobway.model.WorkExperience;
import cripel.jobway.model.WorkSector;
import cripel.jobway.model.WorkTask;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import cripel.jobway.utilities.fxutil.DurationPicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * The Class FormF is the sixth form where the person's informations are
 * inputed.
 */
public class FormF extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to add work experience to the tableview */
	@FXML
	private Button buttonAddWorkExp;

	/** The combobox for the work experience type */
	@FXML
	private ComboBox<WorkExpType> comboBoxWorkExpType;

	/** The searchableCombobox for the work experience sector */
	@FXML
	private SearchableComboBox<WorkSector> comboBoxWorkExpSector;

	/** The spinner for the work duration in months */
	@FXML
	private Spinner<Integer> spinnerWorkDurationMonth;
	
	/** The spinner for the work duration in years */
	@FXML
	private Spinner<Integer> spinnerWorkDurationYear;

	/** The text area for the tasks */
	@FXML
	private TextArea textAreaTasks;

	/** The textfield for the work experience's name */
	@FXML
	private TextField textfieldWorkExpName;

	/** The tableview for the work sectors */
	@FXML
	private TableView<WorkExperience> tableViewWorkSectors;

	/** The column for the duration of the work experience */
	@FXML
	private TableColumn<WorkExperience, Integer> columnDuration;

	/** The column for the sector's name */
	@FXML
	private TableColumn<WorkExperience, String> columnSector;

	/** The column for the type of work experience */
	@FXML
	private TableColumn<WorkExpType, String> columnType;

	/** The for the work experience's name */
	@FXML
	private TableColumn<WorkExperience, String> columnExpName;

	/** The column for the experience's place */
	@FXML
	private TableColumn<WorkExperience, Boolean> columnBelgium;


	/** The column for the tasks history */
	@FXML
	private TableColumn<WorkExperience, String> columnTaches;


	/** The column for the button delete */
	@FXML
	private TableColumn<WorkExperience, Button> columnDel;

	/** The column for the button eddit */
	@FXML
	private TableColumn<WorkExperience, Button> columnEdit;

	/** The togglebuton to specify the work experience wasn't in Belgium */
	@FXML
	private ToggleButton tgbuttonBelgiumNo;

	/** The togglebuton to specify the work experience was in Belgium */
	@FXML
	private ToggleButton tgbuttonBelgiumYes;

	/** The gridpane for the task */
	@FXML
	private GridPane gridPaneTask;

	/** The button to add a task */
	@FXML
	private Button buttonTask;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<WorkSector> listWorkSector = FXCollections
			.observableArrayList(new WorkSectorDAO().getListDelete(false));
	private ObservableList<WorkExpType> listWorkExpType = FXCollections
			.observableArrayList(new WorkExpTypeDAO().getListDelete(false));

	private LinkedHashMap<Button, TextField> listTextAreaTask = new LinkedHashMap<>();
	private WorkExperience edit;

	/**
	 * Togglegroup for togglebuttons
	 */
	ToggleGroup buttonBelgium = new ToggleGroup();

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public FormF() {
		load();
		setup();
	}

	/**
	 * Constructor to fill a person work experience
	 *
	 * @param person the person loaded
	 */
	public FormF(Person person) {
		load();
		setup();
		tableViewWorkSectors.getItems().addAll(person.getWorkexperiences());
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Set the table column with the right object and set the page combobox with a
	 * list
	 */
	private void setup() {
		comboBoxWorkExpSector.setItems(listWorkSector);
		comboBoxWorkExpType.setItems(listWorkExpType);
		spinnerWorkDurationMonth.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12));
		spinnerWorkDurationYear.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
		DurationPicker.avoidString(spinnerWorkDurationMonth, 2);
		DurationPicker.avoidString(spinnerWorkDurationYear, 3);
		DurationPicker.linkSpinner(spinnerWorkDurationMonth, spinnerWorkDurationYear, 12);
		toggleButtonSetup();
		setUpTableView();
	}

	/**
	 * Method to setup the tableview's column
	 */
	private void setUpTableView() {
		columnDuration.setCellValueFactory(new PropertyValueFactory<>("workExpDurationMonth"));
		columnSector.setCellValueFactory(new PropertyValueFactory<>("workSector"));
		columnType.setCellValueFactory(new PropertyValueFactory<>("workexptype"));
		columnExpName.setCellValueFactory(new PropertyValueFactory<>("workExpName"));
		columnBelgium.setCellValueFactory(new PropertyValueFactory<>("workExpBelgium"));

		columnTaches.setCellValueFactory(new PropertyValueFactory<>("worktasks"));

		columnDel.setCellFactory(ButtonTableCell.<WorkExperience>forTableColumn(null, "button-delete", "fas-trash-alt",
				(WorkExperience w) -> {
					tableViewWorkSectors.getItems().remove(w);
					clearFields();
					return w;

				}));

		columnEdit.setCellFactory(ButtonTableCell.<WorkExperience>forTableColumn(null, "button-delete", "far-edit",
				(WorkExperience w) -> {

					tableViewEdit(w);
					buttonAddWorkExp.setText("Modifier");
					return w;

				}));

		columnBelgium.setCellFactory(col -> new TableCell<>() {

			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});
	}

	/**
	 * Method to setup the togglebuttons
	 */
	private void toggleButtonSetup() {
		tgbuttonBelgiumNo.setToggleGroup(buttonBelgium);
		tgbuttonBelgiumYes.setToggleGroup(buttonBelgium);
	}

	/**
	 * Save all data in a person object
	 *
	 * @param person the new person
	 */
	public void saveData(Person person) {
		tableViewWorkSectors.getItems().forEach(exp -> exp.setPerson(person));
		person.getWorkexperiences().clear();
		person.getWorkexperiences().addAll(tableViewWorkSectors.getItems());

	}

	/**
	 * Clear all fields of the page
	 */
	private void clearFields() {
		comboBoxWorkExpType.getSelectionModel().clearSelection();
		comboBoxWorkExpType.setValue(null);
		comboBoxWorkExpSector.getSelectionModel().clearSelection();
		spinnerWorkDurationMonth.getValueFactory().setValue(0);
		spinnerWorkDurationYear.getValueFactory().setValue(0);
		textfieldWorkExpName.clear();
		tgbuttonBelgiumYes.setSelected(false);
		tgbuttonBelgiumNo.setSelected(false);
		gridPaneTask.getChildren().clear();
		gridPaneTask.add(buttonTask, 0, 0);
		listTextAreaTask.clear();
		buttonAddWorkExp.setText("Ajouter");
		edit = null;
	}

	/**
	 * Set tableView info to edit
	 */
	private void tableViewEdit(WorkExperience workExp) {

		if (workExp != null) {
			clearFields();
			edit = workExp;
			for (WorkTask task : workExp.getWorktasks()) {
				addTextFieldTask(task.getWorkTaskDescription());
			}
			comboBoxWorkExpType.getSelectionModel().select(workExp.getWorkexptype());
			for (WorkSector sec : comboBoxWorkExpSector.getItems()) {
				if (sec.getWorkSectorName().contains(workExp.getWorkSector())) {
					comboBoxWorkExpSector.getSelectionModel().select(sec);
				}
			}

			spinnerWorkDurationYear.getValueFactory().setValue(workExp.getWorkExpDurationMonth()/12);
			spinnerWorkDurationMonth.getValueFactory().setValue(workExp.getWorkExpDurationMonth() % 12);
			textfieldWorkExpName.setText(workExp.getWorkExpName());

			if (workExp.isWorkExpBelgium() != null) {
				tgbuttonBelgiumYes.setSelected(workExp.isWorkExpBelgium());
				tgbuttonBelgiumNo.setSelected(!workExp.isWorkExpBelgium());
			}

	

		}

	}

	/**
	 * Listener on button to prohibit user to add a work experience if comboboxes
	 * are null
	 */
	private void buttonAddListener() {
		boolean condition = (comboBoxWorkExpSector.getValue() != null && comboBoxWorkExpType.getValue() != null);
		buttonAddWorkExp.setDisable(!condition);
	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Add a work experience to the table view with the filled fields
	 */
	@FXML
	void onActionAdd(ActionEvent event) {
		WorkExperience workExp;
		if (edit == null) {
			workExp = new WorkExperience();
		} else {
			workExp = edit;
		}
		workExp.setWorkExpName(textfieldWorkExpName.getText());
		workExp.setWorkSector(comboBoxWorkExpSector.getSelectionModel().getSelectedItem().getWorkSectorName());
		workExp.setWorkExpDurationMonth(spinnerWorkDurationMonth.getValue()+spinnerWorkDurationYear.getValue()*12);
		workExp.setWorkexptype(comboBoxWorkExpType.getSelectionModel().getSelectedItem());

		if (tgbuttonBelgiumYes.isSelected()) {
			workExp.setWorkExpBelgium(true);
		} else if (tgbuttonBelgiumNo.isSelected()) {
			workExp.setWorkExpBelgium(false);
		} else {
			workExp.setWorkExpBelgium(null);
		}

		workExp.getWorktasks().clear();
		for (TextField textField : listTextAreaTask.values()) {
			WorkTask task = new WorkTask();
			task.setWorkexperience(workExp);
			if (textField.getText() == null) {
				task.setWorkTaskDescription("");
			} else {
				task.setWorkTaskDescription(textField.getText());

			}
			workExp.getWorktasks().add(task);
		}

		if (!tableViewWorkSectors.getItems().contains(workExp))
			tableViewWorkSectors.getItems().add(workExp);

		clearFields();

		tableViewWorkSectors.refresh();
	}

	/**
	 * Action Button to cancel modification and clear fields
	 */
	@FXML
	private void onActionCancel() {
		clearFields();

	}

	/**
	 * Listener on combobox (comboBoxWorkExpSector) to check if the combobox is not
	 * empty, using the method buttonAddListener()
	 *
	 * @param event
	 */
	@FXML
	void checkSectorNotNull(ActionEvent event) {
		buttonAddListener();
	}

	/**
	 * Listener on combobox (comboBoxWorkExpType) to check if the combobox is not
	 * empty, using the method buttonAddListener()
	 *
	 * @param event
	 */
	@FXML
	void checkTypeNotNull(ActionEvent event) {
		buttonAddListener();
	}

	/**
	 * Button action to add a TextField in the grid pane
	 */
	@FXML
	void addTaskAction() {
		addTextFieldTask(null);

	}

	/**
	 * Add a textField and a button to remove it in the gridpane
	 *
	 * @param text the content of the textField
	 */
	void addTextFieldTask(String text) {
		TextField textField = new TextField(text);
		gridPaneTask.getChildren().remove(buttonTask);
		gridPaneTask.add(textField, 0, gridPaneTask.getRowCount());
		Button button = new Button("-");
		listTextAreaTask.put(button, textField);
		button.setOnAction(action -> {
			gridPaneTask.getChildren().remove(button);
			gridPaneTask.getChildren().remove(listTextAreaTask.get(button));
			listTextAreaTask.remove(button);
		});
		gridPaneTask.add(button, 1, gridPaneTask.getRowCount() - 1);
		gridPaneTask.add(buttonTask, 0, gridPaneTask.getRowCount());
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */

	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormF.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
