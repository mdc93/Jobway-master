package cripel.jobway.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import cripel.jobway.dao.PersonDAO;
import cripel.jobway.model.Event;
import cripel.jobway.model.File;
import cripel.jobway.model.Person;
import cripel.jobway.model.User;
import cripel.jobway.ui.forms.FormA;
import cripel.jobway.ui.forms.FormB;
import cripel.jobway.ui.forms.FormC;
import cripel.jobway.ui.forms.FormD;
import cripel.jobway.ui.forms.FormE;
import cripel.jobway.ui.forms.FormF;
import cripel.jobway.ui.forms.FormG;
import cripel.jobway.ui.forms.FormH;
import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.Confirm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class Menu forms to manage the different forms
 */
public class MenuForms extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to go to the next tab */
	@FXML
	private Button next;

	/** The button to go the previous tab */
	@FXML
	private Button previous;

	/** The tabPane */
	@FXML
	private TabPane tabPane;

	/** The button to save all the forms */
	@FXML
	private Button buttonSave;

	/** The circle that display if the FSE datas are correctly completed */
	@FXML
	private Circle circleState;

	/** The label for the FSE */
	@FXML
	private Label lblFSE;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/** The person object */
	private Person newPerson;

	/** The private forms */
	private FormA formA;
	private FormB formB;
	private FormC formC;
	private FormD formD;
	private FormE formE;
	private FormF formF;
	private FormG formG;
	private FormH formH;

	Tooltip toolTipFse = new Tooltip("Champs nécessaires : Nom, prénom, adresse, code postal, ville, n°tel," + '\n'
			+ "date de naissance, sexe, nationalité, situation prof, date inscription forem, durée innocupation, diplômes, ménage, difficultés "

	);

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public MenuForms() {
		load();
		setup();
	}

	/**
	 * Constructor with a user
	 *
	 * @param user {@link User}
	 */
	public MenuForms(User user) {
		load();
		setup();
		formA.setUser(user);
		formA.disableEmployeeComboBox(user);
		setUpToolTip();
		listenerButtons();
	}

	/**
	 * Constructor with a person and a user
	 *
	 * @param person {@link Person}
	 * @param user   {@link User}
	 */
	public MenuForms(Person person, User user) {
		load();
		person = PersonDAO.fetchPerson(person);
		setup(person);
		newPerson = person;
		checkEncoding();
		formA.disableEmployeeComboBox(user);
		setUpToolTip();
		listenerButtons();

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Load all forms in Tabs
	 */
	public void setup() {
		tabPane.getTabs().add(new Tab("Page 1", formA = new FormA()));
		tabPane.getTabs().add(new Tab("Page 2", formB = new FormB()));
		tabPane.getTabs().add(new Tab("Page 3", formC = new FormC()));
		tabPane.getTabs().add(new Tab("Page 4", formD = new FormD()));
		tabPane.getTabs().add(new Tab("Page 5", formE = new FormE()));
		tabPane.getTabs().add(new Tab("Page 6", formF = new FormF()));
		tabPane.getTabs().add(new Tab("Page 7", formG = new FormG()));
		tabPane.getTabs().add(new Tab("Page 8", formH = new FormH()));

	}

	/**
	 * Load all forms in Tabs with a {@link Person} information
	 *
	 * @param person selected
	 */
	public void setup(Person person) {
		tabPane.getTabs().add(new Tab("Page 1", formA = new FormA(person)));
		tabPane.getTabs().add(new Tab("Page 2", formB = new FormB(person)));
		tabPane.getTabs().add(new Tab("Page 3", formC = new FormC(person)));
		tabPane.getTabs().add(new Tab("Page 4", formD = new FormD(person)));
		tabPane.getTabs().add(new Tab("Page 5", formE = new FormE(person)));
		tabPane.getTabs().add(new Tab("Page 6", formF = new FormF(person)));
		tabPane.getTabs().add(new Tab("Page 7", formG = new FormG(person)));
		tabPane.getTabs().add(new Tab("Page 8", formH = new FormH(person)));
	}

	/**
	 * Method to change the color of the circle if FSE data are missing
	 */
	public void checkEncoding() {
		if (!formA.checkEncodingState() || !formB.checkEncodingState() || !formC.checkEncodingState()
				|| !formE.checkEncodingState()) {
			String fseLabelContent = "Données FSE incomplètes page(s): ";
			String pageNotComplete = "";

			if (!formA.checkEncodingState())
				pageNotComplete += "1/";

			if (!formB.checkEncodingState())
				pageNotComplete += "2/";

			if (!formC.checkEncodingState())
				pageNotComplete += "3/";

			if (!formE.checkEncodingState())
				pageNotComplete += "5";

			circleState.setFill(Color.RED);
			lblFSE.setText(fseLabelContent + pageNotComplete);

		} else {
			circleState.setFill(Color.GREEN);
			lblFSE.setText("Données FSE complètes");
		}

	}

	/**
	 * Method to set up the {@link Tooltip} for the FSE datas
	 */
	public void setUpToolTip() {

		lblFSE.setTooltip(toolTipFse);
		toolTipFse.setShowDelay(Duration.seconds(1));
		toolTipFse.setPrefWidth(100);
		toolTipFse.setWrapText(true);
	}

	/**
	 * Method to save all data of a {@link Person} currently saved
	 *
	 * @param person {@link Person}
	 */
	public void saveAllData(Person person) {
		if (person.isPersonIsDelete() == null) {
			person.setPersonIsDelete(false);
		}
		if (person.getFile() == null) {
			person.setFile(new File());
		}

		if (person.getFile().getRegistrationDate() == null) {
			person.getFile().setRegistrationDate(DateUtil.convertToDate(LocalDate.now()));
		}
		person.getFile().setModificationDate(DateUtil.convertToDate(LocalDate.now()));
		formA.saveData(person);
		formB.saveData(person);
		formC.saveData(person);
		formD.saveData(person);
		formE.saveData(person);
		formF.saveData(person);
		formH.saveData(person);
		if (formG != null)
			formG.saveData(person);

		Set<Event> events = person.getEvents();
		person.setEvents(events);

		new PersonDAO().saveOrUpdate(person,true);

	}

	/**
	 * Method to check if the {@link Tab} is the first or last one of
	 * {@link #tabPane} and disable the {@link #next}/{@link #previous} button
	 */
	public void checkButtons() {
		next.setDisable(tabPane.getSelectionModel().getSelectedIndex() == tabPane.getTabs().size() - 1);
		previous.setDisable(tabPane.getSelectionModel().getSelectedIndex() == 0);

	}

	/**
	 * Listener used to disable the buttons when the {@link #tabPane} index the last
	 * or first page
	 */
	public void listenerButtons() {
		tabPane.getSelectionModel().selectedItemProperty().addListener(change -> checkButtons());

	}

	// **************************************************************************************************
	// FXML Methods
	// **************************************************************************************************

	/**
	 * On action listener of {@link #next} to go the next {@link Tab}
	 *
	 * @param event
	 */
	@FXML
	void toNext(ActionEvent event) {
		tabPane.getSelectionModel().selectNext();
		checkEncoding();
		checkButtons();
	}

	/**
	 * On action listener of {@link #previous} to go to the previous {@link Tab}
	 *
	 * @param event
	 */
	@FXML
	void toPrevious(ActionEvent event) {
		tabPane.getSelectionModel().selectPrevious();
		checkEncoding();
		checkButtons();

	}

	/**
	 * Method from {@link #buttonSave} it saves the {@link Person} data and then
	 * close the dialog
	 *
	 * @param event
	 */
	@FXML
	void saveAction(ActionEvent event) {
		if (newPerson == null) {
			newPerson = new Person();
		}

		saveAllData(newPerson);

		if (Confirm.confirmQuit()) {
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			s.close();
		}

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuForms.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
