package cripel.jobway.ui.forms;

import java.io.IOException;
import java.time.LocalDateTime;

import org.controlsfx.control.ListSelectionView;

import cripel.jobway.dao.EventTypeDAO;
import cripel.jobway.dao.ThemeDAO;
import cripel.jobway.model.Event;
import cripel.jobway.model.EventType;
import cripel.jobway.model.Person;
import cripel.jobway.model.Theme;
import cripel.jobway.utilities.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormG is the seventh form where the person's informations are
 * inputed.
 */
public class FormG extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The button to validate the theme */
	@FXML
	private Button buttonValidateTheme;

	/** The textfield for the other theme */
	@FXML
	private TextField textFieldOtherTheme;

	/** The list view for the list of selections */
	@FXML
	private ListSelectionView<Theme> listSelection;
	

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	/**
	 * Observable list fetched from the database
	 */
	private ObservableList<Theme> listTheme = FXCollections.observableArrayList(new ThemeDAO().getListDelete(false));

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public FormG() {
		load();
		setup();
	}
	
	/**
	 * Constructor to set every field with a person's information.
	 *
	 * @param person the person selected
	 */
	public FormG(Person person) {
		load();
		setup();
		ObservableList<Theme> listThemeSelected = FXCollections.observableArrayList();
		for(Event event : person.getEvents()) {
			listThemeSelected.add(event.getTheme());
		}
		listSelection.setTargetItems(listThemeSelected);

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Save all data in a person object
	 *
	 * @param person the new person
	 */
	public void saveData(Person person) {

		person.getEvents().clear();
		for (Theme theme : listSelection.getTargetItems()) {

			Event ev = new Event();
			ev.setEventDate(DateUtil.convertToDate(LocalDateTime.now()));
			EventType eventType = new EventTypeDAO().selectByCriteria("eventTypeName='Demande'");
			ev.setEventType(eventType);
			ev.setEventDuration(0);
			ev.setPerson(person);
			ev.setTheme(theme);
			if (person.getEmployee() != null)
				ev.getEmployees().add(person.getEmployee());
			person.getEvents().add(ev);

		}

	}

	/**
	 * Method to setup the listSelection
	 */
	public void setup() {
		listSelection.getSourceItems().addAll(listTheme);
		listSelection.getActions().remove(1);
		listSelection.setSourceHeader(new Label("Thématique disponible"));
		listSelection.getSourceHeader().setStyle("-fx-font-weight: bold;");
		listSelection.setTargetHeader(new Label("Thématique choisie"));
		listSelection.getTargetHeader().setStyle("-fx-font-weight: bold;");

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormG.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
