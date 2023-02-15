package cripel.jobway.ui.tablemngmt;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * The Class TableManagementHome is a javafx interface to access all the
 * management of all table the user can change in the database.
 */
public class TableManagementHome extends BorderPane {

	/** The tab pane. */
	@FXML
	private TabPane tabPane;

	/**
	 * Instantiates a new table management home.
	 */
	public TableManagementHome() {
		load();
		setup();
	}

	/**
	 * Setup all tabs in the Tab Pane
	 */
	public void setup() {
		tabPane.getTabs().add(new Tab("Thematique", new TMTheme()));
		tabPane.getTabs().add(new Tab("Type de tâche", new TMEventType()));
		tabPane.getTabs().add(new Tab("Permis co.", new TMDriverLic()));
		tabPane.getTabs().add(new Tab("Ville", new TMCity()));
		tabPane.getTabs().add(new Tab("Code postal", new TMPostal()));
		tabPane.getTabs().add(new Tab("Langue", new TMLanguage()));
		tabPane.getTabs().add(new Tab("Pays", new TMCountry()));
		tabPane.getTabs().add(new Tab("Pays type", new TMCountryType()));
		tabPane.getTabs().add(new Tab("Etat civil", new TMCivilStatus()));
		tabPane.getTabs().add(new Tab("Permis sé.", new TMResPerm()));
		tabPane.getTabs().add(new Tab("Revenu", new TMIncomeType()));
//		tabPane.getTabs().add(new Tab("Situation professionnelle FSE ", new TMSituationProfFSE()));
		tabPane.getTabs().add(new Tab("Langue", new TMLanguage()));
		tabPane.getTabs().add(new Tab("Test langue", new TMFrenchTest()));
		tabPane.getTabs().add(new Tab("Transport", new TMLocomotionMean()));
		tabPane.getTabs().add(new Tab("Situation", new TMSitTerr()));
		tabPane.getTabs().add(new Tab("Exp Pro", new TMWorkExpType()));
		tabPane.getTabs().add(new Tab("Secteur", new TMWorkSector()));
		tabPane.getTabs().add(new Tab("Dispon.", new TMAvailability()));
		tabPane.getTabs().add(new Tab("Regroup", new TMFamReun()));
		tabPane.getTabs().add(new Tab("Formation", new TMFormationType()));
//		tabPane.getTabs().add(new Tab(" Niveau d'études FSE", new TMNiveauEtudeFSE()));
	}

	/**
	 * Load.
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableManagementHome.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
