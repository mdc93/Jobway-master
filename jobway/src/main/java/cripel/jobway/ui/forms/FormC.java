package cripel.jobway.ui.forms;

import java.io.IOException;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.ListSelectionView;

import cripel.jobway.dao.DriverLicenseDAO;
import cripel.jobway.dao.LocomotionMeanDAO;
import cripel.jobway.model.Disability;
import cripel.jobway.model.DriverLicense;
import cripel.jobway.model.LocomotionMean;
import cripel.jobway.model.PerDrL;
import cripel.jobway.model.Person;
import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.DatePickerUtil;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormC is the third form where the person's informations are
 * inputed.
 */
public class FormC extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The togglebutton to specify that the person has not her driving license */
	@FXML
	private ToggleButton tgbuttonDrivingLNo;

	/** The togglebutton to specify that the person has her driving license */
	@FXML
	private ToggleButton tgbuttonDrivingLYes;

	/** The checkbox for the forem subscription */
	@FXML
	private CheckBox checkBoxForemSubscription;

	/** The checkbox to specify if the person has handicaps */
	@FXML
	private CheckBox checkBoxHandicaps;

	/** The checkbox to specify if the person has another difficulty */
	@FXML
	private CheckBox checkBoxOtherDifficulty;

	/** The checkbox to specify if the person has a vehicle */
	@FXML
	private CheckBox checkBoxVehicle;

	/** The combobox for the locomotion means */
	@FXML
	private ComboBox<LocomotionMean> comboBoxLocomotionMeans;

	/** The checkComboBox for the driving license */
	@FXML
	private CheckComboBox<DriverLicense> checkComboBoxDrivingLicense;

	/** The datepicker for the forum subscription */
	@FXML
	private DatePicker datePickerForemSubscription;

	/** The textfield other precision */
	@FXML
	private TextField textFieldOtherPrecision;

	/** The textfield for the notes */
	@FXML
	private TextField textFieldNotes;

	/** The togglebutton to specify that the person has not access to work */
	@FXML
	private ToggleButton tgbuttonWorkAccessNo;

	/** The togglebutton to specify that the person has access to work */
	@FXML
	private ToggleButton tgbuttonWorkAccessYes;

	/**
	 * The Controlsfx list selection view to select if a driver license is valid in
	 * Belgium Left column source items are valid and right column target items are
	 * not.
	 */
	@FXML
	private ListSelectionView<DriverLicense> listSelectionViewLicense;

	@FXML
	private ComboBox<String> comboBoxSituationPro;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<LocomotionMean> listLocomotionMean = FXCollections
			.observableArrayList(new LocomotionMeanDAO().getListDelete(false));
	private ObservableList<DriverLicense> listDriverLicense = FXCollections
			.observableArrayList(new DriverLicenseDAO().getListDelete(false));

	/**
	 * Togglegroups for togglebuttons
	 */
	ToggleGroup groupDrivingLicense = new ToggleGroup();
	ToggleGroup groupWorkAccess = new ToggleGroup();

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default Constructor
	 */
	public FormC() {
		load();
		setUp();
		listenerCheckComboxDriverLicense();
	}

	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormC(Person person) {
		load();
		setUp();

		datePickerForemSubscription.setValue(DateUtil.convertToLocalDate(person.getPersonForemInsDate()));

		if (person.getPersonForemInsDate() != null) {
			checkBoxForemSubscription.setSelected(true);
		}

		if (person.isPersonWorkAccess() != null) {
			tgbuttonWorkAccessYes.setSelected(person.isPersonWorkAccess());
			tgbuttonWorkAccessNo.setSelected(!person.isPersonWorkAccess());

		}

		textFieldNotes.setText(person.getPersonNotes());

		if (!person.getPerDrL().isEmpty()) {
			for (PerDrL perDrl : person.getPerDrL()) {

				tgbuttonDrivingLYes.setSelected(true);
				checkComboBoxDrivingLicense.getCheckModel().check(perDrl.getDriverLicense());
				checkComboBoxDrivingLicense.setDisable(false);
				if (perDrl.isBelgiumValid()) {
					listSelectionViewLicense.getSourceItems().add(perDrl.getDriverLicense());
				} else if (!perDrl.isBelgiumValid()) {
					listSelectionViewLicense.getTargetItems().add(perDrl.getDriverLicense());
				}
			}
		}

		else {
			tgbuttonDrivingLNo.setSelected(true);
		}

		listenerCheckComboxDriverLicense();
		if (person.isPersonHasVehicle() != null) {
			checkBoxVehicle.setSelected(person.isPersonHasVehicle());
		}
		for (LocomotionMean loco : person.getLocomotionmeans()) {
			comboBoxLocomotionMeans.setValue(loco);
		}

		if (person.getDisability() != null) {
			checkBoxHandicaps.setSelected(person.getDisability().isDisReco());
			textFieldOtherPrecision.setText(person.getDisability().getDisOther());

			if (!textFieldOtherPrecision.getText().equals(""))
				checkBoxOtherDifficulty.setSelected(true);

		}

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to set up all javafx components with their data
	 */
	public void setUp() {
		comboBoxLocomotionMeans.setItems(listLocomotionMean);
		checkComboBoxDrivingLicense.getItems().addAll(listDriverLicense);

		listenerEnableField();
		toggleButtonsSetup();
		focusDatePicker();

		listSelectionViewLicense.setSourceHeader(new Label("Valable en Belgique"));
		listSelectionViewLicense.getSourceHeader().setStyle("-fx-font-weight: bold;");
		listSelectionViewLicense.setTargetHeader(new Label("Non valable en Belgique"));
		listSelectionViewLicense.getTargetHeader().setStyle("-fx-font-weight: bold;");
	}

	/**
	 * Method to add a change listener to the {@link CheckComboBox} if an item is
	 * checked it add it to the source of the {@link #listSelectionViewLicense}, if
	 * it is unchecked it removes it from both source and target
	 */
	private void listenerCheckComboxDriverLicense() {
		checkComboBoxDrivingLicense.getCheckModel().getCheckedItems()
				.addListener((ListChangeListener<? super DriverLicense>) change -> {
					while (change.next()) {

						change.getAddedSubList().forEach(driverLicense -> {
							if (!listSelectionViewLicense.getTargetItems().contains(driverLicense)
									&& !listSelectionViewLicense.getSourceItems().contains(driverLicense))
								listSelectionViewLicense.getSourceItems().add(driverLicense);
						});
						change.getRemoved().forEach(driverLicense -> {
							if (listSelectionViewLicense.getTargetItems().contains(driverLicense))
								listSelectionViewLicense.getTargetItems().remove(driverLicense);
							else if (listSelectionViewLicense.getSourceItems().contains(driverLicense))
								listSelectionViewLicense.getSourceItems().remove(driverLicense);
						});
					}

				});
	}

	/**
	 * Method to set up toggle buttons and their listeners
	 */
	public void toggleButtonsSetup() {
		tgbuttonDrivingLYes.setToggleGroup(groupDrivingLicense);
		tgbuttonDrivingLNo.setToggleGroup(groupDrivingLicense);

		tgbuttonWorkAccessYes.setToggleGroup(groupWorkAccess);
		tgbuttonWorkAccessNo.setToggleGroup(groupWorkAccess);

		groupDrivingLicense.selectedToggleProperty().addListener((obs,oldValue,newValue) -> {
				if (newValue == tgbuttonDrivingLYes) {

					checkComboBoxDrivingLicense.setDisable(false);

				} else {
					checkComboBoxDrivingLicense.setDisable(true);
					checkComboBoxDrivingLicense.getCheckModel().clearChecks();
					listSelectionViewLicense.getTargetItems().clear();
					listSelectionViewLicense.getSourceItems().clear();
				}

			});
	}

	/**
	 * Listener to enable or disable certain fields with
	 * {@link #checkBoxForemSubscription} and {@link #checkBoxOtherDifficulty} with
	 * {@link CheckBox#selectedProperty()}
	 */
	public void listenerEnableField() {

		checkBoxForemSubscription.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
					if (Boolean.TRUE.equals(newValue))
						datePickerForemSubscription.setDisable(false);
					else {
						datePickerForemSubscription.setDisable(true);
						datePickerForemSubscription.setValue(null);
					}

				});

		checkBoxOtherDifficulty.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
					if (Boolean.TRUE.equals(newValue))
						textFieldOtherPrecision.setDisable(false);
					else {
						textFieldOtherPrecision.setDisable(true);
						textFieldOtherPrecision.clear();
					}

				});

	}

	/**
	 * Method used to control the circle's color
	 *
	 * @return a boolean used for the color of the circle
	 */
	public boolean checkEncodingState() {
		boolean encodingFlag;

		if (datePickerForemSubscription.getValue() == null) {
			encodingFlag = false;
		} else
			encodingFlag = true;

		return encodingFlag;
	}

	/**
	 * Check the value in {@link #datePickerForemSubscription} and replace by a
	 * valid date if focus is lost.
	 */
	public void focusDatePicker() {
		DatePickerUtil.listenerValidDate(datePickerForemSubscription);
	}

	/**
	 * Add every field of the page in an object Person
	 *
	 * @param person
	 */
	public void saveData(Person person) {

		person.setPersonForemInsDate(DateUtil.convertToDate(datePickerForemSubscription.getValue()));

		person.setPersonNotes(textFieldNotes.getText());

		person.getPerDrL().clear();
		for (DriverLicense driver : listSelectionViewLicense.getTargetItems()) {
			PerDrL perDrL = new PerDrL();
			perDrL.setDriverLicense(driver);
			perDrL.setBelgiumValid(false);
			perDrL.setPerson(person);
			person.getPerDrL().add(perDrL);
		}

		for (DriverLicense driver : listSelectionViewLicense.getSourceItems()) {
			PerDrL perDrL = new PerDrL();
			perDrL.setDriverLicense(driver);
			perDrL.setBelgiumValid(true);
			perDrL.setPerson(person);
			person.getPerDrL().add(perDrL);
		}

		person.getLocomotionmeans().clear();
		person.getLocomotionmeans().add(comboBoxLocomotionMeans.getValue());
		person.setPersonHasVehicle(checkBoxVehicle.isSelected());

		if (tgbuttonWorkAccessYes.isSelected()) {
			person.setPersonWorkAccess(true);
		} else if (tgbuttonWorkAccessNo.isSelected()) {
			person.setPersonWorkAccess(false);
		} else
			person.setPersonWorkAccess(null);

		Disability dis;
		if (person.getDisability() == null) {
			dis = new Disability();

		} else {
			dis = person.getDisability();
		}

		dis.setDisReco(checkBoxHandicaps.isSelected());
		dis.setDisOther(textFieldOtherPrecision.getText());

		person.setDisability(dis);

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormC.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
