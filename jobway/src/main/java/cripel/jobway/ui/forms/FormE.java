package cripel.jobway.ui.forms;

import java.io.IOException;

import org.controlsfx.control.SearchableComboBox;

import cripel.jobway.dao.FormationTypeDAO;
import cripel.jobway.model.Formation;
import cripel.jobway.model.FormationType;
import cripel.jobway.model.Person;
import cripel.jobway.utilities.fxutil.ButtonTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormE is the fifth form where the person's informations are
 * inputed.
 */
public class FormE extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to add a new diploma in the tableview */
	@FXML
	private Button buttonAddDiploma;

	/** The button to delete a formation */
	@FXML
	private Button buttonDeleteFormation;

	/** The button to add a new formation in the tableview */
	@FXML
	private Button buttonAddFormation;

	/** The searchableCombobox for the diploma's name */
	@FXML
	private SearchableComboBox<FormationType> comboBoxDiplomaName;

	/** The combobox for the type of fomration */
	@FXML
	private ComboBox<FormationType> comboBoxTypeFormation;

	/** The textfield for the diploma's subject */
	@FXML
	private TextField textFieldDiplomaSubject;

	/** The textfield for the formation's subject */
	@FXML
	private TextField textFieldFormationSubject;

	/** The radio button to specify the diploma is from Belgium */
	@FXML
	private RadioButton radioButtonDiplomaBE;

	/** The radio button to specify the diploma is foreign */
	@FXML
	private RadioButton radioButtonDiplomaForeign;

	/** The togglebuton to specify the diploma is from UE */
	@FXML
	private ToggleButton tgbuttonEU;

	/** The togglebuton to specify the diploma is from outisde UE */
	@FXML
	private ToggleButton tgbuttonOutsideEU;

	/** The togglebutton to specify the equivalence hasn't been asked */
	@FXML
	private ToggleButton tgbuttonEquiAskNo;

	/** The togglebutton to specify the equivalence has been asked */
	@FXML
	private ToggleButton tgbuttonEquiAskYes;

	/** The togglebutton to specify the equivalence hasn't been obtained */
	@FXML
	private ToggleButton tgbuttonEquiObtNo;

	/** The togglebuton to specify the equivalence has been obtained */
	@FXML
	private ToggleButton tgbuttonEquiObtYes;

	/** The tableview of the diplomas/formations */
	@FXML
	private TableView<Formation> tableViewDiploma;

	/** The tablecolumn for the diploma's name */
	@FXML
	private TableColumn<FormationType, String> columnDiplomaName;

	/** The tablecolumn for the diploma's subject */
	@FXML
	private TableColumn<Formation, String> columnDiplomaSubject;

	/** The tablecolumn for the delete button */
	@FXML
	private TableColumn<Formation, Button> columnDel;

	/** The tablecolumn for the eddit button */
	@FXML
	private TableColumn<Formation, Button> columnEdit;

	/** The tablecolumn for the equivalence asked boolean */
	@FXML
	private TableColumn<Formation, Boolean> columnEquiAsked;

	/** The tablecolumn for the equivalence obtained boolean */
	@FXML
	private TableColumn<Formation, Boolean> columnEquiObtained;

	/** The tablecolumn for the belgium's validity diploma boolean */
	@FXML
	private TableColumn<Formation, Boolean> columnBelgium;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<FormationType> listFormationTypeDiploma = FXCollections
			.observableArrayList(FormationTypeDAO.getListFormationTypeDiploma());
	private ObservableList<FormationType> listFormationTypeFormation = FXCollections
			.observableArrayList(FormationTypeDAO.getListFormationTypeFormation());

	/**
	 * Selected formation
	 */
	private Formation formationSelected;
	
	private static final String ADD="Ajouter";

	/**
	 * Togglegroups for togglebuttons
	 */
	ToggleGroup radioGroup = new ToggleGroup();
	ToggleGroup toggleGroupEU = new ToggleGroup();
	ToggleGroup toggleGroupEquivalence = new ToggleGroup();
	ToggleGroup toggleGroupEquivalenceObt = new ToggleGroup();

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public FormE() {
		load();
		setUp();

	}

	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormE(Person person) {
		load();
		setUp();

		tableViewDiploma.getItems().addAll(person.getFormations());

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to set up all javafx components with their data
	 */
	private void setUp() {
		formationSelected = null;
		comboBoxDiplomaName.setItems(listFormationTypeDiploma);

		comboBoxTypeFormation.setItems(listFormationTypeFormation);

		radioButtonDiplomaBE.setSelected(true);

		radioButtonSetup();
		toggleButtonSetup();
		setUpTableView();

	}

	/**
	 * Tableview's listener to set buttons&textfield
	 *
	 * @param formation
	 */
	private void listenerTableView(Formation formation) {

		clearField();
		if (formation != null) {
			if (!formation.getFormationtype().isIsFormation()) {
				buttonAddDiploma.setDisable(false);
				radioButtonDiplomaForeign.setSelected(formation.isForeignFormation());

				radioButtonDiplomaBE.setSelected(!formation.isForeignFormation());

				if (formation.isForeignFormation()) {
					if (formation.isForeignHUE() != null) {
						tgbuttonEU.setSelected(!formation.isForeignHUE());
						tgbuttonOutsideEU.setSelected(formation.isForeignHUE());
					}

					if (formation.isEquIntro() != null) {
						tgbuttonEquiAskYes.setSelected(formation.isEquIntro());
						tgbuttonEquiAskNo.setSelected(!formation.isEquIntro());
					}
					if (formation.isEquObt() != null) {
						tgbuttonEquiObtYes.setSelected(formation.isEquObt());
						tgbuttonEquiObtNo.setSelected(!formation.isEquObt());
					}
				}

				comboBoxDiplomaName.setValue(formation.getFormationtype());

				textFieldDiplomaSubject.setText(formation.getFormationName());

				buttonAddFormation.setDisable(true);
			} else {
				buttonAddFormation.setDisable(false);

				comboBoxTypeFormation.setValue(formation.getFormationtype());

				textFieldFormationSubject.setText(formation.getFormationName());

				buttonAddDiploma.setDisable(true);
			}

		}
	}

	/**
	 * Method to clear fields when needed
	 */
	private void clearField() {
		comboBoxDiplomaName.getSelectionModel().clearSelection();
		comboBoxDiplomaName.valueProperty().set(null);
		textFieldDiplomaSubject.clear();
		radioButtonDiplomaBE.setSelected(true);
		radioButtonDiplomaForeign.setSelected(false);

		tgbuttonEU.setSelected(false);
		tgbuttonOutsideEU.setSelected(false);

		tgbuttonEquiAskYes.setSelected(false);
		tgbuttonEquiAskNo.setSelected(false);

		tgbuttonEquiObtYes.setSelected(false);
		tgbuttonEquiObtNo.setSelected(false);

		comboBoxTypeFormation.getSelectionModel().clearSelection();
		comboBoxTypeFormation.valueProperty().set(null);
		textFieldFormationSubject.clear();
	}

	/**
	 * Method to setup the radiobuttons
	 */
	private void radioButtonSetup() {
		radioButtonDiplomaBE.setToggleGroup(radioGroup);
		radioButtonDiplomaForeign.setToggleGroup(radioGroup);

		radioGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {

			if (newToggle == radioButtonDiplomaForeign) {
				tgbuttonEU.setDisable(false);
				tgbuttonOutsideEU.setDisable(false);

				tgbuttonEquiAskYes.setDisable(false);
				tgbuttonEquiAskNo.setDisable(false);

				tgbuttonEquiObtYes.setDisable(false);
				tgbuttonEquiObtNo.setDisable(false);

				tgbuttonEquiObtYes.setSelected(false);
				tgbuttonEquiObtNo.setSelected(false);

			} else {
				tgbuttonEU.setDisable(true);
				tgbuttonEU.setSelected(false);
				tgbuttonOutsideEU.setDisable(true);
				tgbuttonOutsideEU.setSelected(false);

				tgbuttonEquiAskYes.setSelected(false);
				tgbuttonEquiAskYes.setDisable(true);
				tgbuttonEquiAskNo.setSelected(false);
				tgbuttonEquiAskNo.setDisable(true);

				tgbuttonEquiObtYes.setSelected(false);
				tgbuttonEquiObtNo.setSelected(false);
				tgbuttonEquiObtYes.setDisable(true);
				tgbuttonEquiObtNo.setDisable(true);
			}
		});

	}

	/**
	 * Method to setup the togglebuttons
	 */
	private void toggleButtonSetup() {

		tgbuttonEU.setToggleGroup(toggleGroupEU);
		tgbuttonOutsideEU.setToggleGroup(toggleGroupEU);

		tgbuttonEquiAskYes.setToggleGroup(toggleGroupEquivalence);
		tgbuttonEquiAskNo.setToggleGroup(toggleGroupEquivalence);

		tgbuttonEquiObtYes.setToggleGroup(toggleGroupEquivalenceObt);
		tgbuttonEquiObtNo.setToggleGroup(toggleGroupEquivalenceObt);
	}

	/**
	 * Method to setup the tableview's columns
	 */
	public void setUpTableView() {

		columnDiplomaName.setCellValueFactory(new PropertyValueFactory<>("formationtype"));
		columnDiplomaSubject.setCellValueFactory(new PropertyValueFactory<>("formationName"));
		columnEquiObtained.setCellValueFactory(new PropertyValueFactory<>("equObt"));
		columnBelgium.setCellValueFactory(new PropertyValueFactory<>("foreignFormation"));

		columnEquiAsked.setCellValueFactory(new PropertyValueFactory<>("equIntro"));

		columnEquiAsked.setCellFactory(col -> new TableCell<>() {

			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});

		columnEquiObtained.setCellFactory(col -> new TableCell<>() {
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});

		columnBelgium.setCellFactory(col -> new TableCell<>() {
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Etranger" : "Belgique");
			}

		});

		columnDel.setCellFactory(
				ButtonTableCell.<Formation>forTableColumn(null, "button-delete", "fas-trash-alt", (Formation f) -> {
					tableViewDiploma.getItems().remove(f);
					return f;
				}));
		
		columnEdit.setCellFactory(
				ButtonTableCell.<Formation>forTableColumn(null, "button-edit", "far-edit", (Formation f) -> {
					if (!f.getFormationtype().isIsFormation()) {
						buttonAddDiploma.setText("Editer");
					} else {
						buttonAddFormation.setText("Editer");
					}
					listenerTableView(f);
					formationSelected = f;
					return f;
				}));
	}

	/**
	 * Method used to control the circle's color
	 *
	 * @return a boolean used for the color of the circle
	 */
	public boolean checkEncodingState() {
		boolean encodingFlag;

		if (tableViewDiploma.getItems().isEmpty()) {
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
		person.getFormations().clear();
		person.getFormations().addAll(tableViewDiploma.getItems());

	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * Method to add a diploma in the tableView
	 *
	 * @param event
	 */
	@FXML
	void addDiploma(ActionEvent event) {
		Formation form = null;

		if (formationSelected == null) {
			form = new Formation();
		} else {
			form = formationSelected;
		}

		form.setFormationName(textFieldDiplomaSubject.getText());

		form.setFormationtype(comboBoxDiplomaName.getSelectionModel().getSelectedItem());

		form.setForeignFormation(radioButtonDiplomaForeign.isSelected());

		if (tgbuttonEU.isSelected()) {
			form.setForeignHUE(false);
		} else if (tgbuttonOutsideEU.isSelected()) {
			form.setForeignHUE(true);
		} else {
			form.setForeignHUE(null);
		}

		if (tgbuttonEquiObtYes.isSelected()) {
			form.setEquObt(true);
		} else if (tgbuttonEquiObtNo.isSelected()) {
			form.setEquObt(false);
		} else {
			form.setEquObt(null);

		}

		if (tgbuttonEquiAskYes.isSelected()) {
			form.setEquIntro(true);
		} else if (tgbuttonEquiAskNo.isSelected())
			form.setEquIntro(false);
		else
			form.setEquIntro(null);

		if (!tableViewDiploma.getItems().contains(form) && comboBoxDiplomaName.getValue() != null
				&& !form.getFormationtype().isIsFormation()) {
			tableViewDiploma.getItems().add(form);
		}

		formationSelected = null;
		buttonAddDiploma.setText(ADD);
		tableViewDiploma.refresh();
		clearField();
		buttonAddFormation.setDisable(false);

	}

	/**
	 * Method to cancel a diploma addition
	 *
	 * @param event
	 */
	@FXML
	void cancelDiploma(ActionEvent event) {
		formationSelected = null;
		buttonAddDiploma.setText(ADD);
		buttonAddFormation.setText(ADD);
		clearField();
		buttonAddDiploma.setDisable(false);
		buttonAddFormation.setDisable(false);
	}

	/**
	 * Method to add a formation in the tableview
	 *
	 * @param event
	 */
	@FXML
	void addFormation(ActionEvent event) {
		Formation form = null;

		if (formationSelected == null) {
			form = new Formation();
		} else {
			form = formationSelected;
		}

		form.setFormationName(textFieldFormationSubject.getText());
		form.setFormationtype(comboBoxTypeFormation.getSelectionModel().getSelectedItem());
		if (!tableViewDiploma.getItems().contains(form)
				&& comboBoxTypeFormation.getSelectionModel().getSelectedItem() != null
				&& form.getFormationtype().isIsFormation()) {
			tableViewDiploma.getItems().add(form);
		}

		clearField();
		buttonAddDiploma.setDisable(false);
	}

	/**
	 * Method to cancel a formation addition
	 *
	 * @param event
	 */
	@FXML
	void onFormationCancel(ActionEvent event) {
		cancelDiploma(event);
	}

	/**
	 * Method to remove a formation from the tableview
	 *
	 * @param event
	 */
	@FXML
	void onActionDelete(ActionEvent event) {
		int index = tableViewDiploma.getSelectionModel().getSelectedIndex();
		tableViewDiploma.getItems().remove(index);

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormE.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
