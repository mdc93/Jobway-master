package cripel.jobway.ui.forms;

import java.io.IOException;

import cripel.jobway.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * The Class FormH is the eighth form where the person's informations are
 * inputed.
 */
public class FormH extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The text area for the notes of the person */
	@FXML
	private TextArea textAreaNotes;

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************

	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public FormH() {
		load();
	}

	/**
	 * Constructor with person parameter
	 *
	 * @param person
	 */
	public FormH(Person person) {
		load();

		textAreaNotes.setText(person.getPersonNotepad());

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

		person.setPersonNotepad(textAreaNotes.getText());
	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormH.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
