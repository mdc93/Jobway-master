package cripel.jobway.ui.forms;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

import cripel.jobway.dao.*;
import cripel.jobway.model.*;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.SearchableComboBox;

import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.DatePickerUtil;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * The Controller Class FormA is the first form where the person's informations
 * are inputed.
 */
public class FormA extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The combo box file status. */
	@FXML
	private ComboBox<String> comboBoxFileStatus;

	/** The combo box nationality. */
	@FXML
	private SearchableComboBox<Country> comboBoxNationality;

	/** The radio button referent dipa. */
	@FXML
	private RadioButton radioBtRefDipa;

	/** The radio button forem. */
	@FXML
	private RadioButton radioBtForem;

	/** The radio button other. */
	@FXML
	private RadioButton radioBtOther;

	/** The check box nationality belgian. */
	@FXML
	private CheckBox checkBoxNationalityBelgian;

	/** The combo box reunion. */
	@FXML
	private ComboBox<FamilyReunion> comboBoxReunion;

	/** The choice box gender. */
	@FXML
	private ChoiceBox<String> choiceBoxGender;

	/** The combo box situation. */
	@FXML
	private ComboBox<SituationTerritory> comboBoxSituation;

	/** The combo box employee. */
	@FXML
	private ComboBox<Employee> comboBoxEmployee;

	/** The combo box native country. */
	@FXML
	private SearchableComboBox<Country> comboBoxNativeCountry;

	/** The date arriving in belgium. */
	@FXML
	private DatePicker dateArrivingBelgium;

	/** The text field address. */
	@FXML
	private TextField textFieldAdress;

	/** The date picker birth. */
	@FXML
	private DatePicker datePickerBirth;

	/** The text field first name. */
	@FXML
	private TextField textFieldFirstName;

	/** The combo box city. */
	@FXML
	private ComboBox<City> comboBoxCity;

	/** The text field mail. */
	@FXML
	private TextField textFieldMail;

	/** The text field name. */
	@FXML
	private TextField textFieldName;

	/** The text field niss. */
	@FXML
	private TextField textFieldNiss;

	/** The text field other orientation. */
	@FXML
	private TextField textFieldOtherOrientation;

	/** The text field phone number. */
	@FXML
	private TextField textFieldPhoneNumber;

	/** The searchable combo box postal code. */
	@FXML
	private SearchableComboBox<Postalcode> sComboBoxPC;


	/** The text field other situation. */
	@FXML
	private TextField textFieldOtherSituation;

	/** The label other situation. */
	@FXML
	private Label labelOtherSituation;

	/** The searchable combobox reunion nationality */
	@FXML
	private SearchableComboBox<Country> comboBoxReunionNationality;
	/**The gridpane with {@link #comboBoxReunion} and {@link #comboBoxReunionNationality}  */
	@FXML
	private GridPane gridPaneReunion;

	/** The textArea for the orientation's note */
	@FXML
	private TextArea textAreaOrientationNote;

	/** Label to show {@link #textFieldOtherOrientation} remaining char */
	@FXML
	private Label labelRemainingChar;
	
	/**
	 * A class which contains a reference to all Toggles whose selected variables
	 * should be managed such that only a single Toggle within the ToggleGroup may
	 * be selected at any one time.
	 */
	@FXML
	private ToggleGroup toggleGroup;

	/** Label to show {@link #comboBoxNationality} is UE or not */
	@FXML
	private Label labelRessortissant;

	@FXML
	private SearchableComboBox<Dipa> comboBoxDipa;

	@FXML
	private Label labelDipa;


	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	private ObservableList<Dipa> listDipa = FXCollections
			.observableArrayList(new DipaDAO().getListDelete(false));

	/** Observable list of country fetched from the database. */
	private ObservableList<Country> listCountry = FXCollections
			.observableArrayList(new CountryDAO().getListDeleteOrdered(false, "countryName"));

	/** The list situation. */
	private ObservableList<SituationTerritory> listSituation = FXCollections
			.observableArrayList(new SituationTerritoryDAO().getListDelete(false));

	/** The list reunion. */
	private ObservableList<FamilyReunion> listReunion = FXCollections
			.observableArrayList(new FamilyReunionDAO().getListDelete(false));

	/** The list postal code. */
	private ObservableList<Postalcode> listPC = FXCollections.observableArrayList(new PostalCodeDAO()
			.getListCustom("SELECT DISTINCT p FROM Postalcode AS p JOIN FETCH p.cities WHERE p.delete=0 ORDER BY p.postalCodeNumber"));

	/** The list employee. */
	private ObservableList<Employee> listEmployee = FXCollections
			.observableArrayList(new EmployeeDAO().getListCustom("From Employee e WHERE e.empIsDelete=0"));

	/** The list file status. */
	private ObservableList<String> listFileStatus = FXCollections.observableArrayList("Actif", "Archivé");

	/** The person used when modifying the form . */
	private Person thisPerson = null;

	
	private FadeTransition fade=new FadeTransition(Duration.millis(500));


	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************
	/**
	 * Default constructor.
	 */
	public FormA() {
		load();
		setup();
		comboBoxFileStatus.setValue("Actif");
	}

	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormA(Person person) {
		load();
		setup();
		thisPerson = person;
		loadPerson(person);

	}

	/**
	 * Load person's information in the form
	 * @param person
	 */
	private void loadPerson(Person person) {
		comboBoxFileStatus.setValue(person.getFile().getFileStatus());
		if (Boolean.TRUE.equals(person.isPersonIsDelete()))
			comboBoxFileStatus.setDisable(true);

		comboBoxEmployee.setValue(person.getEmployee());
		textFieldFirstName.setText(person.getPersonFirstName());
		textFieldName.setText(person.getPersonLastName());
		choiceBoxGender.setValue(person.getPersonGender());
		textFieldAdress.setText(person.getPersonAddress());
		textFieldNiss.setText(person.getPersonNiss());
		textFieldPhoneNumber.setText(person.getPersonPhone());
		textFieldMail.setText(person.getPersonMail());
		textAreaOrientationNote.setText(person.getPersonOrientationNote());

		comboBoxSituation.setValue(person.getSituationterritory());

		loadPersonSituation(person);

		if (person.getCity() != null) {
			sComboBoxPC.setValue(person.getCity().getPostalcode());
			pcAction();

			comboBoxCity.setValue(person.getCity());
		}

		for (Country country : person.getCountries()) {
			comboBoxNationality.setValue(country);

		}

		datePickerBirth.setValue(DateUtil.convertToLocalDate(person.getPersonBirthDate()));
		dateArrivingBelgium.setValue(DateUtil.convertToLocalDate(person.getPersonArrivalDate()));
		comboBoxNativeCountry.setValue(person.getCountryByIdCountry());
		checkBoxNationalityBelgian.setSelected(person.isPersonIsBelgian());

		if(comboBoxNationality.getSelectionModel().getSelectedItem() != null)
		{
			ObservableStringValue osv = new SimpleStringProperty(comboBoxNationality.getSelectionModel().getSelectedItem().getCountrytype().getCountryTypeName());
			labelRessortissant.setText(osv.getValue());
		}

		if (person.getPersonOrientation() != null) {
			if (person.getPersonOrientation().contains("Forem")) {
				radioBtForem.setSelected(true);
			} else if (!person.getPersonOrientation().contains("Dipa")) {
				radioBtOther.setSelected(true);
				textFieldOtherOrientation.setText(person.getPersonOrientation());
			} else {
				radioBtRefDipa.setSelected(true);
				comboBoxDipa.setValue(person.getDipa());
				}
		}
	}

	/**
	 * Load person's situation in fiels
	 * @param person
	 */
	private void loadPersonSituation(Person person) {
		if (person.getSituationterritory() != null) {
			if (person.getSituationterritory().getSituationTerritoryName().contains("Regroupement familial")) {
				gridPaneReunion.setVisible(true);
				if (!person.getFamilyreunions().isEmpty()) {
					comboBoxReunion.setValue( person.getFamilyreunions().stream().findFirst().get());
					comboBoxReunionNationality.setValue(person.getCountryByIdCountryReunionNationality());
				}
			}

			if (person.getOther().getOtherSituation() != null) {
				textFieldOtherSituation.setText(person.getOther().getOtherSituation());
				textFieldOtherSituation.setVisible(true);
				labelOtherSituation.setVisible(true);
			}

		}
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************
	/**
	 * Method to set up all javafx components with their initial data.
	 */
	public void setup() {
		setupComboBox();
		radioButtonSetup();
		listenerReunion();
		listenerTextFieldNiss();
		listenerTextFieldPhoneNumber();
		focusDatePicker();
		initFade();
		textAreaOrientationNote.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 99)
				textAreaOrientationNote.setText(oldValue);
			labelRemainingChar.setText((100 - newValue.length()) + "");
		});

		radioBtRefDipa.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == true) {
				comboBoxDipa.setVisible(true);
				labelDipa.setVisible(true);
			}
			else {
				comboBoxDipa.setVisible(false);
				labelDipa.setVisible(false);
			}
		});

		comboBoxDipa.setItems(listDipa);
	}

	/**
	 * Method to set up all comboboxes
	 */
	public void setupComboBox() {
		comboBoxNationality.setItems(listCountry);
		comboBoxNativeCountry.getItems().add(null);
		comboBoxNativeCountry.getItems().addAll(listCountry);
		choiceBoxGender.setItems(FXCollections.observableArrayList("H", "F", "X"));
		comboBoxSituation.getItems().add(null);
		comboBoxSituation.getItems().addAll(listSituation);
		comboBoxReunion.getItems().add(null);
		comboBoxReunion.getItems().addAll(listReunion);
		sComboBoxPC.setItems(listPC);
		comboBoxEmployee.getItems().add(null);
		comboBoxEmployee.getItems().addAll(listEmployee);
		comboBoxFileStatus.setItems(listFileStatus);
		comboBoxReunionNationality.setItems(listCountry);

		comboBoxNationality.getSelectionModel().selectedItemProperty()
				.addListener((observableValue, oldValue, newValue) -> {
					if(observableValue.getValue() != null)
						labelRessortissant.setText(observableValue.getValue().getCountrytype().getCountryTypeName());
				});
	}

	/**
	 * Listener to display family situation fields only if it is chosen by the user
	 * or other selected.
	 */
	public void listenerReunion() {
		comboBoxSituation.setOnAction(event -> {
			if (String.valueOf(comboBoxSituation.getValue()).contains("Regroupement familial")) {
				comboBoxReunion.getSelectionModel().clearSelection();
				gridPaneReunion.setVisible(true);
				fade.playFromStart();
				
				labelOtherSituation.setVisible(false);
				textFieldOtherSituation.setVisible(false);
				textFieldOtherSituation.setText("");

			} else if (String.valueOf(comboBoxSituation.getValue()).contains("Autre")) {
				labelOtherSituation.setVisible(true);
				textFieldOtherSituation.setVisible(true);
				gridPaneReunion.setVisible(false);
				comboBoxReunionNationality.getSelectionModel().clearSelection();
			

			} else {
				labelOtherSituation.setVisible(false);
				textFieldOtherSituation.setVisible(false);
				textFieldOtherSituation.setText("");
				comboBoxReunion.getSelectionModel().clearSelection();
				comboBoxReunionNationality.getSelectionModel().clearSelection();
				gridPaneReunion.setVisible(false);
				

			}

		});
	}

	/**
	 * Listener to prohibit user to type something else than numbers and to add
	 * automatically dot and dash.
	 */
	public void listenerTextFieldNiss() {

		formatNiss();
		unfocus();

	}

	/**
	 * Check uniqueness on the {@link TextField#focusedProperty()} it starts checking when unfocused
	 */
	private void unfocus() {
		/**
		 * Listener showing an alert if the NISS already exist in the database
		 */
		textFieldNiss.focusedProperty().addListener((obs, oldValue, newValue) -> {
			int id = 0;
			if (thisPerson != null)
				id = thisPerson.getIdPerson();
			if (Boolean.FALSE.equals(newValue)) {
				if (!textFieldNiss.getText().isEmpty() && !PersonDAO.isNISSUnique(id, textFieldNiss.getText())) {
					Alert alert = new Alert(AlertType.WARNING, "Ce NISS est déjà présent dans la base de donnée.");
					alert.showAndWait();
					textFieldNiss.clear();
				}
				writeDateFromNiss();
				}
		});
	}

	/**
	 * Write the date of birth from the six first number of the NISS in {@link #datePickerBirth}
	 */
	private void writeDateFromNiss() {
		if (datePickerBirth.getValue() == null && textFieldNiss.getText().length() >= 8) {
				try {
					String startNiss = textFieldNiss.getText().substring(0, 8);
					String[] splitNiss = startNiss.split("\\.");
					int year = Integer.parseInt(splitNiss[0]);
					if (year > LocalDate.now().getYear() - 2000)
						year += 1900;
					else
						year += 2000;

					LocalDate date = LocalDate.of(year, Integer.parseInt(splitNiss[1]),
							Integer.parseInt(splitNiss[2]));
					datePickerBirth.setValue(date);
				} catch (DateTimeException e) {
					Notifications.create().title("Info")
							.text("La date de naissance n'a pas pu être trouvée avec ce NISS")
							.showInformation();

				}
			}
	}

	/**
	 * Format Niss to add point and dash while writing. with an {@link UnaryOperator}
	 * Like this 11.11.11-200.11
	 */
	private void formatNiss() {
		UnaryOperator<Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			String oldText = change.getControlText();
			if (newText.isEmpty()) {
				return change;
			}

			else if ((newText.length() == 3 || newText.length() == 6 || newText.length() == 13)
					&& change.getText().matches("[\\.\\d]*")) {
				if (newText.length() > oldText.length() && !change.getText().contains(".")) {
					change.setText("." + change.getText());
					change.setAnchor(change.getAnchor() + 1);
					change.setCaretPosition(change.getCaretPosition() + 1);
				}
				return change;
				
			} else if (newText.length() == 9 && change.getText().matches("[\\-\\d]*")) {
				if (newText.length() > oldText.length() && !change.getText().contains("-")) {
					change.setText("-" + change.getText());
					change.setAnchor(change.getAnchor() + 1);
					change.setCaretPosition(change.getCaretPosition() + 1);
				}
				return change;
			}

			else if (newText.length() > 15 || (!change.getText().matches("[\\d]*") && change.getText().length() == 1)) {
				return null;
			}

			return change;

		};

		textFieldNiss.setTextFormatter(new TextFormatter<>(integerFilter));
	}

	/**
	 * Listener that prohibit user to type something else than digits in the
	 * phoneNumber's textfield.
	 */
	public void listenerTextFieldPhoneNumber() {

		textFieldPhoneNumber.textProperty().addListener((obs,oldValue,newValue)->{
				if (!newValue.matches("\\d*\\+\\s"))
					textFieldPhoneNumber.setText(newValue.replaceAll("[^\\d\\+,\\s]", ""));
		});

	}

	/**
	 * Set the radio button of the page with their respective toggle group.
	 */
	public void radioButtonSetup() {
		toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> 
				textFieldOtherOrientation.setVisible(newToggle == radioBtOther));

	}

	/**
	 * Method used to control the circle's color.
	 *
	 * @return a boolean used for the color of the circle
	 */
	public boolean checkEncodingState() {
		boolean encodingFlag;

		if (textFieldFirstName.getText().isEmpty() || textFieldName.getText().isEmpty()
				|| textFieldAdress.getText().isEmpty() || sComboBoxPC.getSelectionModel().getSelectedItem() == null
				|| comboBoxCity.getSelectionModel().getSelectedItem() == null
				|| textFieldPhoneNumber.getText().isEmpty() || datePickerBirth.getValue() == null
				|| choiceBoxGender.getSelectionModel().getSelectedItem() == null
				|| comboBoxNationality.getSelectionModel().getSelectedItem() == null) {
			encodingFlag = false;
		} else
			encodingFlag = true;

		return encodingFlag;
	}

	/**
	 * Check the value in date picker editor and replace by a valid date if focus is
	 * lost.
	 */
	public void focusDatePicker() {
		DatePickerUtil.listenerValidDate(dateArrivingBelgium, datePickerBirth);

	}

	/**
	 * Method to prohibit a normal user from changing the comboboxEmployee.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		if (!user.getUserlevel().getUserLevelName().contains("Administrateur")) {
			comboBoxEmployee.setDisable(true);
		}
		comboBoxEmployee.setValue(user.getEmployee());

	}

	/**
	 * Method to disable the combo box employee only for normal user.
	 *
	 * @param user the user
	 */
	public void disableEmployeeComboBox(User user) {
		if (!user.getUserlevel().getUserLevelName().contains("Administrateur")) {
			comboBoxEmployee.setDisable(true);
		}
	}

	/**
	 * Add every field of the page in an object Person.
	 *
	 * @param person the person
	 */
	public void saveData(Person person) {

		person.getFile().setFileStatus(comboBoxFileStatus.getSelectionModel().getSelectedItem());
		person.setEmployee(comboBoxEmployee.getSelectionModel().getSelectedItem());
		person.setPersonFirstName(textFieldFirstName.getText());
		person.setPersonLastName(textFieldName.getText());
		person.setPersonGender(choiceBoxGender.getSelectionModel().getSelectedItem());
		person.setPersonAddress(textFieldAdress.getText());
		person.setPersonArrivalDate(DateUtil.convertToDate(dateArrivingBelgium.getValue()));
		person.setPersonBirthDate(DateUtil.convertToDate(datePickerBirth.getValue()));
		person.setPersonNiss(textFieldNiss.getText());
		person.setPersonMail(textFieldMail.getText());
		person.setPersonPhone(textFieldPhoneNumber.getText());
		person.setCountryByIdCountry(comboBoxNativeCountry.getSelectionModel().getSelectedItem());
		person.setCity(comboBoxCity.getSelectionModel().getSelectedItem());
		person.setPersonIsBelgian(checkBoxNationalityBelgian.isSelected());

		//pour ajouter la "nationnalité", ils ont clear la collection country qu'ils
		//ont rempli avec tous les pays. Et il la reremplisse ici avec le pays choisi.
		person.getCountries().clear();
		person.getCountries().add(comboBoxNationality.getSelectionModel().getSelectedItem());
		person.setSituationterritory(comboBoxSituation.getSelectionModel().getSelectedItem());

		person.setPersonOrientationNote(textAreaOrientationNote.getText());

		if (person.getOther() == null) {
			person.setOther(new Other());
		}

		if (comboBoxSituation.getValue() != null && comboBoxSituation.getSelectionModel().getSelectedItem()
				.getSituationTerritoryName().contains("Autre")) {
			person.getOther().setOtherSituation(textFieldOtherSituation.getText());
		} else
			person.getOther().setOtherSituation(null);

		if (radioBtForem.isSelected()) {
			person.setPersonOrientation("Forem");
		} else if (radioBtOther.isSelected()) {
			person.setPersonOrientation(textFieldOtherOrientation.getText());
		} else if (radioBtRefDipa.isSelected()) {
			person.setPersonOrientation("Dipa");
		}

		person.getFamilyreunions().clear();
		person.getFamilyreunions().add(comboBoxReunion.getSelectionModel().getSelectedItem());
		person.setCountryByIdCountryReunionNationality(
				comboBoxReunionNationality.getSelectionModel().getSelectedItem());

	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Javafx listener in a postalcode combobox when a postal code is selected it
	 * will fill the combobox with the associated cities.
	 */
	@FXML
	void pcAction() {

		ObservableList<City> listFilterCity = FXCollections.observableArrayList();
		if (sComboBoxPC.getSelectionModel().getSelectedItem() != null) {
			for (City city : sComboBoxPC.getSelectionModel().getSelectedItem().getCities()) {
				if (!city.isDelete())
					listFilterCity.add(city);
			}
		}

		comboBoxCity.setItems(listFilterCity);
		comboBoxCity.getSelectionModel().select(0);
	}

	// **************************************************************************************************
	// Transition
	// **************************************************************************************************
	
		public void initFade() {
			fade.setNode(gridPaneReunion);
			fade.setFromValue(0.0);
			fade.setToValue(1.0);
			fade.setAutoReverse(true);
		}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller.
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormA.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
