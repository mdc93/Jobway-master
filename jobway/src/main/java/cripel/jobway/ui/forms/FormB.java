package cripel.jobway.ui.forms;

import java.io.IOException;

import org.controlsfx.control.CheckListView;

import cripel.jobway.dao.CivilStatusDAO;
import cripel.jobway.dao.IncomeTypeDAO;
import cripel.jobway.dao.ResidencePermitTypeDAO;
import cripel.jobway.model.CivilStatus;
import cripel.jobway.model.Household;
import cripel.jobway.model.IncomeType;
import cripel.jobway.model.Person;
import cripel.jobway.model.ResidencePermit;
import cripel.jobway.model.ResidencePermitType;
import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.DatePickerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormB is the second form where the person's informations are
 * inputed.
 */
public class FormB extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The label mutual/healthcare */
	@FXML
	private Label labelMutual;

	/** The label otherResidentPermit */
	@FXML
	private Label labelOtherResidentPermit;

	/** The label spouse nationality */
	@FXML
	private Label labelSpouseNationality;

	/** The label other civil status */
	@FXML
	private Label labelOtherCivilStatus;

	/** The textfield other civil status */
	@FXML
	private TextField textFieldOtherCivilStatus;

	/** The checkbox other income */
	@FXML
	private CheckBox checkBoxOtherIncome;

	/** The combobox civil status */
	@FXML
	private ComboBox<CivilStatus> comboBoxCivilStatus;

	/** the combobox resident permit */
	@FXML
	private ComboBox<ResidencePermitType> comboBoxResidencePermit;

	/** The combobox unemployement duration */
	@FXML
	private ComboBox<String> comboBoxUnemployementDuration;

	/** The datepicker ending date validity */
	@FXML
	private DatePicker dateEndingValidity;

	/** The datepicker starting date validity */
	@FXML
	private DatePicker dateStartingValidity;

	/** The textfield mutual name / healthcare */
	@FXML
	private TextField textFieldMutualName;

	/** The spinner for the number of adults */
	@FXML
	private Spinner<Integer> spinnerNumberOfAdults;

	/** The spinner for the number of children */
	@FXML
	private Spinner<Integer> spinnerNumberOfChildren;

	/** The textfield for the other income */
	@FXML
	private TextField textFieldOtherIncome;

	/** the textfield for the other residence permit */
	@FXML
	private TextField textFieldOtherResidencePermit;

	/** The textfield to know wich guard is needed */
	@FXML
	private TextField textFieldWichGuard;

	/** The checklistview for the list of income */
	@FXML
	private CheckListView<IncomeType> checkListIncome;

	/** The togglebutton to specify that guard is not needed */
	@FXML
	private ToggleButton tgbuttonGuardNo;

	/** The togglebutton to specify that guard is needed */
	@FXML
	private ToggleButton tgbuttonGuardYes;

	/** The togglebutton to specify that isp is not needed */
	@FXML
	private ToggleButton tgbuttonIspNo;

	/** The togglebutton to specify that isp is needed */
	@FXML
	private ToggleButton tgbuttonIspYes;

	/** The togglebutton to specify that the person has a healthcare */
	@FXML
	private ToggleButton tgbuttonMutualYes;

	/** The togglebutton to specify that the person has not a healthcare */
	@FXML
	private ToggleButton tgbuttonMutualNo;
	
	/**
	 * Togglegroups for togglebuttons
	 */
    @FXML
    private ToggleGroup groupGuardNeeded;

    @FXML
    private ToggleGroup groupIspNeeded;

    @FXML
    private ToggleGroup groupMutualNeeded;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<ResidencePermitType> listResidencePermitType = FXCollections
			.observableArrayList(new ResidencePermitTypeDAO().getListDelete(false));
	private ObservableList<CivilStatus> listCivilStatus = FXCollections
			.observableArrayList(new CivilStatusDAO().getListDelete(false));
	private ObservableList<IncomeType> listIncome = FXCollections
			.observableArrayList(new IncomeTypeDAO().getListDelete(false));
	private ObservableList<String> listUnemployementDuration = FXCollections.observableArrayList("Moins de 12 mois",
			"Entre 12 et 24 mois", "Plus de 24 mois");



	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default Constructor
	 */
	public FormB() {
		load();
		setup();

	}

	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormB(Person person) {
		load();
		setup();

		for (ResidencePermit res : person.getResidencepermits()) {
			if (res.getResidencePermitAnnex() != null) {
				labelOtherResidentPermit.setVisible(true);
				textFieldOtherResidencePermit.setVisible(true);
				textFieldOtherResidencePermit.setText(res.getResidencePermitAnnex());
			}
			comboBoxResidencePermit.setValue(res.getResidencepermittype());
			dateStartingValidity.setValue(DateUtil.convertToLocalDate(res.getResidencePermitIssueDate()));
			dateEndingValidity.setValue(DateUtil.convertToLocalDate(res.getResidencePermitValidityDate()));

		}

		comboBoxCivilStatus.setValue(person.getCivilstatus());
		if (person.getOther().getOtherCivilStatus() != null) {

			textFieldOtherCivilStatus.setText(person.getOther().getOtherCivilStatus());
			textFieldOtherCivilStatus.setVisible(true);
			labelOtherCivilStatus.setVisible(true);

		}

		if (person.getHousehold() != null) {
			spinnerNumberOfChildren.getValueFactory().setValue(person.getHousehold().getHouseholdNumberChildren());
			spinnerNumberOfAdults.getValueFactory().setValue(person.getHousehold().getHouseholdNumberAdult());

			if (Boolean.TRUE.equals(person.getHousehold().isHouseholdGuardNeeded()))
				tgbuttonGuardYes.setSelected(true);
			else if (Boolean.FALSE.equals(person.getHousehold().isHouseholdGuardNeeded()))
				tgbuttonGuardNo.setSelected(true);

			if (Boolean.TRUE.equals(person.getHousehold().isHouseholdIspNeeded()))
				tgbuttonIspYes.setSelected(true);
			else if (Boolean.FALSE.equals(person.getHousehold().isHouseholdIspNeeded()))
				tgbuttonIspNo.setSelected(true);
		}

		if (person.getHousehold() != null) {
			textFieldWichGuard.setText(person.getHousehold().getHouseholdIspName());
		}

		textFieldMutualName.setText(person.getPersonHealthcare());

		if (textFieldMutualName.getText() != null) {
			if (textFieldMutualName.getText().contains("Aucune")) {
				tgbuttonMutualNo.setSelected(true);
			} else if (!textFieldMutualName.getText().contains("Aucune")) {
				tgbuttonGuardYes.setSelected(true);
				textFieldMutualName.setVisible(true);
			}
		}

		comboBoxUnemployementDuration.setValue(person.getPersonUnemployementDuration());

		for (IncomeType inc : listIncome) {
			for (IncomeType inc2 : person.getIncometypes()) {
				if (inc.getIdIncomeType().equals(inc2.getIdIncomeType()))
					checkListIncome.getCheckModel().check(inc);
			}
		}

		if (person.getOther().getOtherIncome() != null) {
			checkBoxOtherIncome.setSelected(true);
			textFieldOtherIncome.setText(person.getOther().getOtherIncome());
		}

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to set up all javafx components with their data
	 */
	public void setup() {

		comboBoxResidencePermit.setItems(listResidencePermitType);
		comboBoxCivilStatus.setItems(listCivilStatus);
		comboBoxUnemployementDuration.setItems(listUnemployementDuration);
		spinnerNumberOfAdults.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
		spinnerNumberOfChildren.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
		checkListIncome.setItems(listIncome);

		listenerEnableField();
		toggleButtonsSetup();
		focusDatePicker();
	}

	/**
	 * Method to set up toggle buttons's listener
	 */
	public void toggleButtonsSetup() {
		groupIspNeeded.selectedToggleProperty().addListener((obs,oldValue,newValue)->
				
					textFieldWichGuard.setDisable(newValue != tgbuttonIspYes));

		groupMutualNeeded.selectedToggleProperty().addListener((obs,oldValue,newValue)-> {

				if (newValue == tgbuttonMutualYes) {
					labelMutual.setVisible(true);
					textFieldMutualName.setVisible(true);
				} else {
					labelMutual.setVisible(false);
					textFieldMutualName.setVisible(false);
				}
		});

	}

	/**
	 * Check the value in date picker editor and replace by a valid date if focus is
	 * lost
	 */
	public void focusDatePicker() {
		DatePickerUtil.listenerValidDate(dateEndingValidity, dateStartingValidity);
		DatePickerUtil.setLimit(dateStartingValidity, dateEndingValidity);
	}

	/**
	 * Listener to enable or disable certains fields
	 */
	public void listenerEnableField() {

		checkBoxOtherIncome.selectedProperty()
				.addListener((obs,oldValue,newValue) -> textFieldOtherIncome.setVisible(newValue));
	}

	/**
	 * Method used to control the circle's color
	 *
	 * @return a boolean used for the color of the circle
	 */
	public boolean checkEncodingState() {
		boolean encodingFlag;

		if (comboBoxUnemployementDuration.getSelectionModel().getSelectedItem() == null
				|| checkListIncome.getCheckModel().getCheckedItems().isEmpty()) {
			encodingFlag = false;
		} else
			encodingFlag = true;

		return encodingFlag;
	}

	/**
	 * Add every field of the page in an object Person
	 *
	 * @param person
	 */
	public void saveData(Person person) {

		saveResidentPermit(person);

		person.setCivilstatus(comboBoxCivilStatus.getSelectionModel().getSelectedItem());

		if (comboBoxCivilStatus.getValue() != null
				&& comboBoxCivilStatus.getSelectionModel().getSelectedItem().getCivilStatusName().contains("Autre")) {
			person.getOther().setOtherCivilStatus(textFieldOtherCivilStatus.getText());

		} else {
			if (person.getOther().getOtherCivilStatus() != null) {
				person.getOther().setOtherCivilStatus(null);
			}
		}

		saveHousehold(person);

		if (tgbuttonMutualYes.isSelected()) {
			person.setPersonHealthcare(textFieldMutualName.getText());
		} else if (tgbuttonMutualNo.isSelected()) {
			person.setPersonHealthcare("Aucune");
		} else
			person.setPersonHealthcare(null);

		person.setPersonUnemployementDuration(comboBoxUnemployementDuration.getSelectionModel().getSelectedItem());

		person.getIncometypes().clear();
		person.getIncometypes().addAll(checkListIncome.getCheckModel().getCheckedItems());

		if (checkBoxOtherIncome.isSelected()) {

			person.getOther().setOtherIncome(textFieldOtherIncome.getText());

		} else {
			if (person.getOther().getOtherIncome() != null) {
				person.getOther().setOtherIncome(null);
			}
		}

	}

	/**
	 *  Save or update in an object {@link Person} their {@link Household} information
	 *  from the form
	 * @param person
	 */
	private void saveHousehold(Person person) {
		Household household;
		if (person.getHousehold() == null) {
			household = new Household();
		}

		else {
			household = person.getHousehold();
		}

		household.setHouseholdNumberAdult(spinnerNumberOfAdults.getValue());
		household.setHouseholdNumberChildren(spinnerNumberOfChildren.getValue());

		if (tgbuttonGuardYes.isSelected()) {
			household.setHouseholdGuardNeeded(true);
		} else if (tgbuttonGuardNo.isSelected()) {
			household.setHouseholdGuardNeeded(false);
		} else {
			household.setHouseholdGuardNeeded(null);
		}

		if (tgbuttonIspYes.isSelected()) {
			household.setHouseholdIspNeeded(true);
			household.setHouseholdIspName(textFieldWichGuard.getText());
		} else if (tgbuttonIspNo.isSelected()) {
			household.setHouseholdIspNeeded(false);
			household.setHouseholdIspName(null);
		} else {
			household.setHouseholdIspNeeded(null);
			household.setHouseholdIspName(null);
		}

		person.setHousehold(household);
	}

	/**
	 * Save or update in an object {@link Person} his {@link ResidencePermit} from the form
	 * @param person
	 */
	private void saveResidentPermit(Person person) {
		ResidencePermit residencePermit;

		if (person.getResidencepermits().stream().findAny().isEmpty()) {
			residencePermit = new ResidencePermit();
		}

		else {
			residencePermit = person.getResidencepermits().stream().findAny().get();
		}

		residencePermit.setResidencepermittype(comboBoxResidencePermit.getSelectionModel().getSelectedItem());

		if (comboBoxResidencePermit.getValue() != null) {
			if (residencePermit.getResidencepermittype().isResidencePermitNeedAnnex()) {
				residencePermit.setResidencePermitAnnex(textFieldOtherResidencePermit.getText());
			} else {
				residencePermit.setResidencePermitAnnex(null);
			}

			residencePermit.setResidencePermitIssueDate(DateUtil.convertToDate(dateStartingValidity.getValue()));
			residencePermit.setResidencePermitValidityDate(DateUtil.convertToDate(dateEndingValidity.getValue()));
			residencePermit.setPerson(person);
			person.getResidencepermits().add(residencePermit);

		}
	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	
	/**
	 * Listener to lock/unlock the spouse nationality if needed and that makes
	 * othersCivilStatus's fields available
	 */
	@FXML
	void listenerComboBoxCivilStatus(ActionEvent event) {

		if (String.valueOf(comboBoxCivilStatus.getSelectionModel().getSelectedItem()).contains("Autre")) {
			labelOtherCivilStatus.setVisible(true);
			textFieldOtherCivilStatus.setVisible(true);
		} else {
			labelOtherCivilStatus.setVisible(false);
			textFieldOtherCivilStatus.setVisible(false);
			textFieldOtherCivilStatus.setText("");
		}

	}

	/**
	 * Listener to make label&textfield visible if the combobox selected item needs
	 * an annex
	 *
	 * @param event
	 */
	@FXML
	void ifOther(ActionEvent event) {
		if (comboBoxResidencePermit.getSelectionModel().getSelectedItem().isResidencePermitNeedAnnex()) {
			labelOtherResidentPermit.setVisible(true);
			textFieldOtherResidencePermit.setVisible(true);
		} else {
			labelOtherResidentPermit.setVisible(false);
			textFieldOtherResidencePermit.setVisible(false);

		}
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormB.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
