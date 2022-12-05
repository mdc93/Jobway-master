package cripel.jobway.ui.forms;

import java.io.IOException;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.SearchableComboBox;

import cripel.jobway.dao.AvailabilitiesDAO;
import cripel.jobway.dao.FrenchTestDAO;
import cripel.jobway.dao.LanguageDAO;
import cripel.jobway.dao.WorkSectorDAO;
import cripel.jobway.model.Availability;
import cripel.jobway.model.FrenchTest;
import cripel.jobway.model.Language;
import cripel.jobway.model.PerLan;
import cripel.jobway.model.Person;
import cripel.jobway.model.WorkSearch;
import cripel.jobway.model.WorkSector;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormD is the fourth form where the person's informations are
 * inputed.
 */
public class FormD extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The Label for the other test */
	@FXML
	private Label labelOtherTest;

	/** The textfield for the other test */
	@FXML
	private TextField textFieldOtherTest;

	/** The button to add a new language in the tableview */
	@FXML
	private Button buttonAddLanguage;

	/** The button to add a new sector in the tableview */
	@FXML
	private Button buttonAddSector;

	/** The button to remove a sector from the tableview */
	@FXML
	private Button buttonDeleteSector;

	/** The checkbox for the language test */
	@FXML
	private CheckBox checkBoxLanguageTest;

	/** The checkbox to specify if the person is looking for job */
	@FXML
	private CheckBox checkBoxLookingForJob;

	/** The checkbox for the news letter */
	@FXML
	private CheckBox checkBoxNewsLetter;

	/** The checkbox to specify the person is not looking for job */
	@FXML
	private CheckBox checkBoxNotLookingForJob;

	/** The checkbox for the other disponibility */
	@FXML
	private CheckBox checkBoxOtherDisponibility;

	/** The combobox for the french level */
	@FXML
	private ComboBox<String> comboBoxFrenchLevel;

	/** The combobox for the communication language */
	@FXML
	private ComboBox<PerLan> comboBoxComLanguage;

	/** The combobox for the other language */
	@FXML
	private SearchableComboBox<Language> comboBoxOtherLanguage;

	/** The combobox for the other language level */
	@FXML
	private ComboBox<String> comboBoxOtherLgLevel;

	/** The combobox for the type of test */
	@FXML
	private ComboBox<FrenchTest> comboBoxTpeOfTest;

	/** The searchable combobox for the work sector */
	@FXML
	private SearchableComboBox<WorkSector> comboBoxWorkSector;

	/** The textfield for the other disponibility */
	@FXML
	private TextField textFieldOtherDisponibility;

	/** The textfield to specify why the person is not looking for a job */
	@FXML
	private TextField textFieldWhyNoJob;

	/** The tableview for the languages */
	@FXML
	private TableView<PerLan> tableViewLanguage;

	/** The tablecolumn for the language name */
	@FXML
	private TableColumn<PerLan, String> columnLanguage;

	/** The tablecolumn for the language level */
	@FXML
	private TableColumn<PerLan, String> columnLevel;

	@FXML
	private TableColumn<PerLan, Button> columnDelete;

	/** The listView for the work sectors */
	@FXML
	private ListView<WorkSector> listViewWorkSector;

	/** The checklistview for the disponibilities */
	@FXML
	private CheckListView<Availability> checkListDisponibilities;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<Language> listLanguage = FXCollections
			.observableArrayList(new LanguageDAO().getListDeleteOrdered(false, "languageName"));
	private ObservableList<FrenchTest> listLanguageTest = FXCollections
			.observableArrayList(new FrenchTestDAO().getListDelete(false));

	private ObservableList<WorkSector> listWorkSector = FXCollections
			.observableArrayList(new WorkSectorDAO().getListDelete(false));

	private ObservableList<Availability> listDisponibilities = FXCollections
			.observableArrayList(new AvailabilitiesDAO().getListDelete(false));
	private ObservableList<String> listLanguageLevel = FXCollections.observableArrayList("Aucun", "Débutant (A1/A2)",
			"Intermédiaire (B1,B2)", "Avancé (C1/C2)", "Langue maternelle", "Alpha");
	
	/** String constant for the french language */
	private static final String FRENCH = "français";
	/** String constant for mother language */
	private static final String MOTHER = "maternel";
	
	private static final String OTHER = "Autre";
	

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default Constructor
	 */
	public FormD() {
		load();
		setUp();
	}

	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormD(Person person) {
		load();
		setUp();
		loadPerson(person);

	}

	/**
	 * @param person
	 */
	private void loadPerson(Person person) {
		tableViewLanguage.getItems().clear();
		loadPersonLanguage(person);
		loadWorkPerson(person);
	}

	/**
	 * @param person
	 */
	private void loadWorkPerson(Person person) {
		if (person.getWorksearch() != null) {

			for (Availability avai : person.getWorksearch().getAvailabilities()) {
				checkListDisponibilities.getCheckModel().check(avai);
			}

			if (!person.getWorksearch().getWorkSearchOtherAvailibility().isEmpty()) {
				checkBoxOtherDisponibility.setSelected(true);
				textFieldOtherDisponibility.setText(person.getWorksearch().getWorkSearchOtherAvailibility());
			}

			

			if (Boolean.TRUE.equals(person.getWorksearch().isSearching())) {
				checkBoxLookingForJob.setSelected(true);
			}
			else if (Boolean.FALSE.equals(person.getWorksearch().isSearching())) {
				checkBoxNotLookingForJob.setSelected(true);
				textFieldWhyNoJob.setText(person.getWorksearch().getWorkSearchAnnex());
			}

			listViewWorkSector.getItems().addAll(person.getWorksearch().getWorkSectors());
		}

		if (!listViewWorkSector.getItems().isEmpty()) {
			checkBoxLookingForJob.setSelected(true);
		}
		if (person.isPersonNewsLetterWork() != null && person.isPersonNewsLetterWork()) {
			checkBoxNewsLetter.setSelected(true);
		}
	}

	/**
	 * Load a person's languages in the tableview
	 * @param person
	 */
	private void loadPersonLanguage(Person person) {
		for (PerLan perLan : person.getPerLans()) {
			if (perLan.isLangCom())

			{
				comboBoxComLanguage.setValue(perLan);

			}
			if (perLan.getLanguage().getLanguageName().toLowerCase().contains(FRENCH)) {
				comboBoxFrenchLevel.setValue(perLan.getLangLevel());
			}

			tableViewLanguage.getItems().addAll(perLan);
		}

		if (person.getFrenchTest() != null) {
			checkBoxLanguageTest.setSelected(true);
			comboBoxTpeOfTest.setValue(person.getFrenchTest());

			if (comboBoxTpeOfTest.getSelectionModel().getSelectedItem().getFrenchTestName().contains(OTHER)) {
				labelOtherTest.setVisible(true);
				textFieldOtherTest.setVisible(true);
				textFieldOtherTest.setText(person.getOther().getOtherLanguageTest());
			}

		}
	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to set up all javafx components with their data
	 */
	public void setUp() {
		listenerEnableField();
		setupComboBox();
		setUpTableView();

	}

	/**
	 * Method to set up the tableview columns
	 */
	public void setUpTableView() {
		columnLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
		columnLevel.setCellValueFactory(new PropertyValueFactory<>("langLevel"));
		columnDelete.setCellFactory(
				ButtonTableCell.<PerLan>forTableColumn(null, "button-delete", "fas-trash-alt", (PerLan p) -> {
					if (!p.getLanguage().getLanguageName().startsWith("Français")) {
						tableViewLanguage.getItems().remove(p);
					}
					return p;
				}));
	}

	/**
	 * Method to setup the combobox's content
	 */
	public void setupComboBox() {
		comboBoxOtherLanguage.setItems(listLanguage);
		comboBoxTpeOfTest.setItems(listLanguageTest);
		comboBoxWorkSector.setItems(listWorkSector);
		checkListDisponibilities.setItems(listDisponibilities);
		comboBoxFrenchLevel.setItems(listLanguageLevel);
		comboBoxOtherLgLevel.setItems(listLanguageLevel);

		PerLan tmp = new PerLan();
		Predicate<Language> predicate = language -> language.getLanguageName().toLowerCase().startsWith(FRENCH);

		Language french = IterableUtils.find(listLanguage, predicate);
		tmp.setLanguage(french);
		tmp.setLangLevel(listLanguageLevel.get(0));
		comboBoxOtherLanguage.getItems().remove(french);
		tableViewLanguage.getItems().add(tmp);

		comboBoxComLanguage.setItems(tableViewLanguage.getItems());

	}

	/**
	 * Listener to enable certains fields
	 */
	public void listenerEnableField() {
		checkBoxLanguageTest.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
					if (Boolean.TRUE.equals(newValue)) {
						comboBoxTpeOfTest.setVisible(true);
					} else {
						comboBoxTpeOfTest.setVisible(false);
						labelOtherTest.setVisible(false);
						textFieldOtherTest.setVisible(false);
						comboBoxTpeOfTest.getSelectionModel().clearSelection();
					}

				});

		checkBoxLookingForJob.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
						comboBoxWorkSector.setVisible(newValue);
						buttonAddSector.setVisible(newValue);
						checkBoxNotLookingForJob.setDisable(newValue);
				});

		checkBoxNotLookingForJob.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
					if (Boolean.TRUE.equals(newValue)) {
						textFieldWhyNoJob.setVisible(true);
						checkBoxLookingForJob.setDisable(true);
						checkBoxOtherDisponibility.setDisable(true);
						textFieldOtherDisponibility.setDisable(true);
						checkBoxOtherDisponibility.setSelected(false);
						textFieldOtherDisponibility.clear();
						listViewWorkSector.setDisable(true);
						buttonDeleteSector.setDisable(true);
					} else {
						textFieldWhyNoJob.setVisible(false);
						checkBoxLookingForJob.setDisable(false);
						checkBoxOtherDisponibility.setDisable(false);
						textFieldOtherDisponibility.setDisable(false);
						listViewWorkSector.setDisable(false);
						buttonDeleteSector.setDisable(false);

					}
				});

		checkBoxOtherDisponibility.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> 
						textFieldOtherDisponibility.setVisible(newValue));
	}

	/**
	 * Method to disable the addLanguageButton if no language are selected in the
	 * combobox
	 */
	public void checkLanguageBoxes() {
		if (comboBoxOtherLanguage.getSelectionModel().getSelectedItem() != null
				&& comboBoxOtherLgLevel.getSelectionModel().getSelectedItem() != null) {
			buttonAddLanguage.setDisable(false);
		}
	}

	/**
	 * Add every field of the page in an object Person
	 *
	 * @param person
	 */
	public void saveData(Person person) {
		saveLanguages(person);
		saveWorkSearch(person);
	}

	/**
	 * Save all value inserted by the user in the language part in an object person
	 * @param person
	 */
	private void saveLanguages(Person person) {
		person.getPerLans().clear();

		for (PerLan lan : tableViewLanguage.getItems()) {

			lan.setPerson(person);
			if (lan == comboBoxComLanguage.getSelectionModel().getSelectedItem()) {
				lan.setLangCom(true);
			}

			person.getPerLans().add(lan);

		}

		if (checkBoxLanguageTest.isSelected()) {
			person.setFrenchTest(comboBoxTpeOfTest.getSelectionModel().getSelectedItem());

		}

		if (comboBoxTpeOfTest.getValue() != null
				&& comboBoxTpeOfTest.getSelectionModel().getSelectedItem().getFrenchTestName().contains(OTHER)) {
			person.getOther().setOtherLanguageTest(textFieldOtherTest.getText());

		} else {
			if (person.getOther().getOtherLanguageTest() != null) {
				person.getOther().setOtherLanguageTest(null);
			}
		}
	}

	/**
	 * Save all value in the work search part of the form
	 * @param person
	 */
	private void saveWorkSearch(Person person) {
		WorkSearch work;

		if (person.getWorksearch() == null) {
			work = new WorkSearch();
		} else {
			work = person.getWorksearch();
		}

		work.getWorkSectors().clear();

		if (checkBoxLookingForJob.isSelected()) {
			work.setSearching(true);
			work.getWorkSectors().addAll(listViewWorkSector.getItems());
		} else {
			work.setSearching(null);
		}

		work.getAvailabilities().clear();
		work.getAvailabilities().addAll(checkListDisponibilities.getCheckModel().getCheckedItems());

		if (checkBoxOtherDisponibility.isSelected()) {
			work.setWorkSearchOtherAvailibility(textFieldOtherDisponibility.getText());
		} else {
			work.setWorkSearchOtherAvailibility("");
		}
		if (checkBoxNotLookingForJob.isSelected()) {
			work.setWorkSearchAnnex(textFieldWhyNoJob.getText());
			work.setSearching(false);
		} else {
			work.setWorkSearchAnnex("");
		}
		person.setWorksearch(work);

		if (checkBoxNewsLetter.isSelected()) {
			person.setPersonNewsLetterWork(checkBoxNewsLetter.isSelected());
		} else
			person.setPersonNewsLetterWork(false);
	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method to add a language in the tableview
	 *
	 * @param event
	 */
	@FXML
	void addLanguage(ActionEvent event) {
		Predicate<PerLan> predicateLevel = personLanguage-> personLanguage.getLangLevel().toLowerCase().contains(MOTHER);
			
	

		Predicate<PerLan> predicateLanguage= language-> language.getLanguage().equals(comboBoxOtherLanguage.getValue());
		PerLan lang = IterableUtils.find(tableViewLanguage.getItems(), predicateLanguage);

		if (comboBoxOtherLgLevel.getValue().toLowerCase().contains(MOTHER)
				&& IterableUtils.matchesAny(tableViewLanguage.getItems(), predicateLevel)) {
			Notifications.create().title("Info").text("Déjà une langue maternelle").showInformation();

		} else {
			if (lang == null) {
				lang = new PerLan();
				lang.setLanguage(comboBoxOtherLanguage.getValue());
				tableViewLanguage.getItems().add(lang);
			}
			lang.setLangLevel(comboBoxOtherLgLevel.getValue());
			comboBoxOtherLanguage.getSelectionModel().clearSelection();
			comboBoxOtherLgLevel.getSelectionModel().clearSelection();
			buttonAddLanguage.setDisable(true);
		}

		tableViewLanguage.refresh();

	}

	/**
	 * Listener on the french combobox to chose the french's level and prohibit user
	 * from chosing "Langue maternelle" if it exist already
	 *
	 * @param event
	 */
	@FXML
	void frenchLevel(ActionEvent event) {

		Predicate<PerLan> predicateLevel = language -> language.getLangLevel().toLowerCase().contains(MOTHER);

		Predicate<PerLan> predicateLanguage = language -> language.getLanguage().getLanguageName().toLowerCase()
				.contains(FRENCH);

		PerLan french = IterableUtils.find(tableViewLanguage.getItems(), predicateLanguage);

		if (comboBoxFrenchLevel.getValue().toLowerCase().contains(MOTHER)
				&& IterableUtils.matchesAny(tableViewLanguage.getItems(), predicateLevel)) {
			Notifications.create().title("Info").text("Déjà une langue maternelle").showInformation();
			comboBoxFrenchLevel.setValue(french.getLangLevel());
		} else
			french.setLangLevel(comboBoxFrenchLevel.getValue());

		tableViewLanguage.refresh();

	}

	/**
	 * Listener to check if the combobox (comboBoxOtherLgLevel) is empty, using
	 * checkLanguageBoxes() function
	 *
	 * @param event
	 */
	@FXML
	void checkNullOtherLanguageLevel(ActionEvent event) {
		checkLanguageBoxes();
	}

	/**
	 * Listener to check if the combobox (comboBoxOtherLanguage) is empty, using
	 * checkLanguageBoxes() function
	 *
	 * @param event
	 */
	@FXML
	void checkNullOtherLanguage(ActionEvent event) {
		checkLanguageBoxes();
	}

	/**
	 * Method to add a workSector in the tableview
	 *
	 * @param event
	 */
	@FXML
	void addSector(ActionEvent event) {

		if (!listViewWorkSector.getItems().contains(comboBoxWorkSector.getSelectionModel().getSelectedItem())
				&& comboBoxWorkSector.getSelectionModel().getSelectedItem() != null) {
			listViewWorkSector.getItems().add(comboBoxWorkSector.getSelectionModel().getSelectedItem());
			comboBoxWorkSector.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Method to remove a worksector from the tableview
	 *
	 * @param event
	 */
	@FXML
	void deleteSector(ActionEvent event) {

		int index = listViewWorkSector.getSelectionModel().getSelectedIndex();

		if (index >= 0) {
			listViewWorkSector.getItems().remove(index);
		}
	}

	/**
	 * Check if other is selected in the typeOfTest Combobox and allow user to type
	 * in the textfield to fill it in the database
	 *
	 * @param event
	 */
	@FXML
	void listenerOtherTest(ActionEvent event) {

		if (comboBoxTpeOfTest.getSelectionModel().getSelectedItem() != null) {
			if (comboBoxTpeOfTest.getSelectionModel().getSelectedItem().getFrenchTestName().contains(OTHER)) {
				labelOtherTest.setVisible(true);
				textFieldOtherTest.setVisible(true);
			} else {
				labelOtherTest.setVisible(false);
				textFieldOtherTest.setVisible(false);
			}
		}

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************
	
	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormD.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
