package cripel.jobway.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.dao.PersonDAO;
import cripel.jobway.exportexcel.PoiTableViewWord;
import cripel.jobway.model.Availability;
import cripel.jobway.model.Event;
import cripel.jobway.model.Formation;
import cripel.jobway.model.FormationType;
import cripel.jobway.model.IncomeType;
import cripel.jobway.model.LocomotionMean;
import cripel.jobway.model.PerDrL;
import cripel.jobway.model.PerLan;
import cripel.jobway.model.Person;
import cripel.jobway.model.ResidencePermit;
import cripel.jobway.model.WorkExpType;
import cripel.jobway.model.WorkExperience;
import cripel.jobway.model.WorkSector;
import cripel.jobway.model.WorkTask;
import cripel.jobway.utilities.DateUtil;
import cripel.jobway.utilities.fxutil.GridPaneUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

/**
 * The Class person display to display person's informations without the
 * possibility to modify anything
 */
public class PersonDisplay extends BorderPane {

	// **************************************************************************************************
	// FXML FIELDS
	// **************************************************************************************************

	/** The label for the file status */
	@FXML
	private Label labelFileStatus;

	/** The label for the native country */
	@FXML
	private Label countryOrigin;

	/** The label for the resident permit date (beginning date) */
	@FXML
	private Label dateResidentB;

	/** The label for the resident permit date (expiring date) */
	@FXML
	private Label dateResidentE;

	/** The label for the adress */
	@FXML
	private Label labelAdress;

	/** The label for the city */
	@FXML
	private Label labelCity;

	/** The label for the civil status */
	@FXML
	private Label labelCivilStatus;

	/** The label for the birth date */
	@FXML
	private Label labelDateBirth;

	/** The label for the first name */
	@FXML
	private Label labelFirstName;

	/** The label for the mail adress */
	@FXML
	private Label labelMail;

	/** The label for the NISS */
	@FXML
	private Label labelNISS;

	/** The label for the name */
	@FXML
	private Label labelName;

	/** The label for the phone number */
	@FXML
	private Label labelPhone;

	/** The label for the postal code */
	@FXML
	private Label labelPostal;

	/** The label for the resident permit */
	@FXML
	private Label labelResident;

	/** The label for the nationality */
	@FXML
	private Label nationality;

	/** The label for the situation on the territory */
	@FXML
	private Label situation;

	/** The label for the forem subscription date */
	@FXML
	private Label labelForem;

	/** The label for the orientation */
	@FXML
	private Label labelOrientation;

	/** The label for the employee's dipa */
	@FXML
	private Label labelEmployee;

	/** The label for the spouse's nationality */
	@FXML
	private Label labelReunionNationality;

	/** The listview for the income */
	@FXML
	private ListView<IncomeType> listViewIncome;

	/** The tableview for the driver license */
	@FXML
	private TableView<PerDrL> tableViewDriver;

	/** The table column with driver license name */
	@FXML
	private TableColumn<PerDrL, String> columnDriverName;

	/** The table column which display if the license is valid in Belgium */
	@FXML
	private TableColumn<PerDrL, Boolean> columnDriverValid;

	/** The label for the other income specified or not */
	@FXML
	private Label labelOtherIncome;

	/** The label for the vehicle */
	@FXML
	private Label labelVehicle;

	/** The label for the french test */
	@FXML
	private Label labelTestLangage;

	/** The label for the language communication */
	@FXML
	private Label labelComLang;

	/** The label for to specify if the person has the belgian nationality */
	@FXML
	private Label labelBelgianNationality;

	/** The label for the belgium arrival date */
	@FXML
	private Label labelBelgiumArrivalDate;

	/** The label for the number of childrens in the household */
	@FXML
	private Label labelNumberOfChildren;

	/** The label for the number of adults in the household */
	@FXML
	private Label labelNumberOfAdults;

	/** The label to specify if a guard is needed */
	@FXML
	private Label labelGuardNeeded;

	/** The label for the note */
	@FXML
	private Label labelNote;

	/** The label to specify if the guard isp is needed */
	@FXML
	private Label labelGuardIsp;

	/** The label to specify wich kind of guard is needed */
	@FXML
	private Label labelWichGuardMeans;

	/** The label for the healthcare's name */
	@FXML
	private Label labelMutualName;

	/** The label for the locomotion mean */
	@FXML
	private Label labelLocomotionMean;

	/** The label to specify if there is another difficulty */
	@FXML
	private Label labelOtherDifficulty;

	/** The label for the work search */
	@FXML
	private Label labelWorkSearch;

	/** The label for the work access */
	@FXML
	private Label labelWorkAccess;

	/** The label to specify if the person wants the newsletter */
	@FXML
	private Label labelNewsLetter;

	/** The label for the person's notes */
	@FXML
	private TextArea textAreaNotes;

	/** The label for the duration of unemployement */
	@FXML
	private Label labelTimeUnemployed;

	/** The label for the other availability */
	@FXML
	private Label labelOtherAvailability;

	/** The label for the person's orientation note */
	@FXML
	private Label labelOrientationNote;

	/** The column for the language's name */
	@FXML
	private TableColumn<PerLan, String> tableColumnLanguage;

	/** The column for the level of the language */
	@FXML
	private TableColumn<PerLan, String> tableColumnLevel;

	/** The tableview Language */
	@FXML
	private TableView<PerLan> tableViewLanguage;

	/** The tableview Formation */
	@FXML
	private TableView<Formation> tableViewFormations;

	/** The column for the discipline's name */
	@FXML
	private TableColumn<Formation, String> columnDiscipline;

	/** The column to specify if the equivalence has been introduced */
	@FXML
	private TableColumn<Formation, Boolean> columnEquiIntro;

	/** The column to specify if the equivalence has been obtained */
	@FXML
	private TableColumn<Formation, Boolean> columnEquiObt;

	/** The column for the formation's name */
	@FXML
	private TableColumn<FormationType, String> columnFormationName;

	/** The tableview of work experience */
	@FXML
	private TableView<WorkExperience> tableviewExpPro;

	/** The column of the job's duration */
	@FXML
	private TableColumn<WorkExperience, Integer> columnJobDuration;

	/** The column of the job's name */
	@FXML
	private TableColumn<WorkExperience, String> columnJobName;

	/** The column of the job's sector */
	@FXML
	private TableColumn<WorkExperience, String> columnJobSector;

	/** The column for the work experience's location */
	@FXML
	private TableColumn<WorkExperience, Boolean> columnBelgium;

	/** The column of the job type */
	@FXML
	private TableColumn<WorkExpType, String> columnJobType;

	/** The list view of work sectors */
	@FXML
	private ListView<WorkSector> listViewWorkSector;

	/** The list view of availabilities */
	@FXML
	private ListView<Availability> listViewAvailability;

	/** The tableview of event */
	@FXML
	private TableView<Event> tableViewTheme;

	/** The column of themes's name */
	@FXML
	private TableColumn<Event, String> columnThemeThematique;

	/** The column of event date */
	@FXML
	private TableColumn<Event, Date> columnThemeDate;

	/** The column of event duration */
	@FXML
	private TableColumn<Event, String> columnThemeDuration;

	/** The column of the employee's name related to that event */
	@FXML
	private TableColumn<Event, String> columnThemeEmployee;

	/** The column of the event's type */
	@FXML
	private TableColumn<Event, String> columnThemeType;

	/** The column of the event's note */
	@FXML
	private TableColumn<Event, String> columnThemeNotes;

	@FXML
	private TableView<WorkTask> tableViewTask;

	@FXML
	private TableColumn<WorkTask, String> columnTaskWorkExp;

	@FXML
	private TableColumn<WorkTask, String> columnTask;

	/** The VBox containing all GridPane */
	@FXML
	private VBox vBox;

	/** The object person which information are displayed */
	@FXML
	private Person selected;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	// **************************************************************************************************
	// FIELDS
	// **************************************************************************************************
	/**
	 * A map of key GridPane with the gridPane children the array coordinate
	 * correspond to the gridpane coordinate
	 */
	private Map<GridPane, Node[][]> listGridPane = new HashMap<>();
	// **************************************************************************************************
	// CONSTRUCTORS
	// **************************************************************************************************

	/**
	 * Default constructor
	 */
	public PersonDisplay() {
		load();
		setup();
	}

	/**
	 * Constructor with a person
	 *
	 * @param person
	 */
	public PersonDisplay(Person person) {
		load();
		setup();
		listGridPane = GridPaneUtil.mapListNodeGridPane(vBox.getChildren());
		person = PersonDAO.fetchPerson(person);
		selected = person;
		/////////////////////////////////////////////////////// Orientation | DIPA

		if (person.getFile() != null)
			labelFileStatus.setText(person.getFile().getFileStatus());

		if (person.getEmployee() != null)
			labelEmployee.setText(person.getEmployee().getEmpPseudo());

		labelOrientation.setText(person.getPersonOrientation());

		labelOrientationNote.setText(person.getPersonOrientationNote());

		/////////////////////////////////////////////////////// Situation administrative

		labelFirstName.setText(person.getPersonFirstName());
		labelAdress.setText(person.getPersonAddress());
		labelMail.setText(person.getPersonMail());
		labelNISS.setText(person.getPersonNiss());
		labelName.setText(person.getPersonLastName());
		labelPhone.setText(person.getPersonPhone());

		if (person.getCity() != null) {
			labelCity.setText(person.getCity().toString());
			labelPostal.setText(person.getCity().getPostalcode().getPostalCodeNumber());
		}

		labelBelgiumArrivalDate.setText(dateCheck(person.getPersonArrivalDate()));

		labelBelgianNationality.setText(booleanCheck(person.isPersonIsBelgian()));

		if (person.getCountryByIdCountry() != null)
			countryOrigin.setText(person.getCountryByIdCountry().getCountryName());

		if (!person.getCountries().isEmpty())
			nationality.setText(person.getCountries().stream().findFirst().get().getCountryName());

		if (person.getSituationterritory() != null)
			if (!person.getSituationterritory().getSituationTerritoryName().contains("Autre"))
				situation.setText(person.getSituationterritory().getSituationTerritoryName());
			else {
				situation.setText(person.getOther().getOtherSituation());
			}

		if (!person.getResidencepermits().isEmpty()) {
			ResidencePermit residence = person.getResidencepermits().stream().findFirst().get();
			labelResident.setText(residence.getResidencepermittype().getResidencePermitTypeName());

			if (residence.getResidencePermitAnnex() != null && !residence.getResidencePermitAnnex().isBlank())
				labelResident.setText(
						residence.getResidencePermitAnnex() + "  Annexe: " + residence.getResidencePermitAnnex());

			dateResidentB.setText(dateCheck(residence.getResidencePermitIssueDate()));
			dateResidentE.setText(dateCheck(residence.getResidencePermitValidityDate()));
		}

		/////////////////////////////////////////////////////// Situation familiale

		if (person.getCivilstatus() != null)
			if (!person.getCivilstatus().getCivilStatusName().contains("Autre"))
				labelCivilStatus.setText(person.getCivilstatus().getCivilStatusName());
			else
				labelCivilStatus.setText(person.getOther().getOtherCivilStatus());

		labelDateBirth.setText(dateCheck(person.getPersonBirthDate()));

		if (person.getCountryByIdCountryReunionNationality() != null) {
			labelReunionNationality.setText(person.getCountryByIdCountryReunionNationality().getCountryName());
		}

		labelNumberOfChildren.setText("" + person.getHousehold().getHouseholdNumberChildren());
		labelNumberOfAdults.setText("" + person.getHousehold().getHouseholdNumberAdult());

		if (person.getHousehold().isHouseholdGuardNeeded() != null) {
			labelGuardNeeded.setText(booleanCheck(person.getHousehold().isHouseholdGuardNeeded()));

		}

		if (person.getHousehold().isHouseholdIspNeeded() != null) {
			labelGuardIsp.setText(booleanCheck(person.getHousehold().isHouseholdIspNeeded()));

		}

		if (person.getHousehold().getHouseholdIspName() != null)
			labelWichGuardMeans.setText(person.getHousehold().getHouseholdIspName());

		/////////////////////////////////////////////////////// Situation socio-Eco

		if (person.getPersonHealthcare() != null)
			labelMutualName.setText(person.getPersonHealthcare());
		else
			labelMutualName.setText("Aucune");

		labelTimeUnemployed.setText(person.getPersonUnemployementDuration());

		listViewIncome.getItems().addAll(person.getIncometypes());

		if (person.getOther().getOtherIncome() != null) {
			labelOtherIncome.setText(person.getOther().getOtherIncome());
		}

		if (person.isPersonWorkAccess() != null)
			labelWorkAccess.setText(booleanCheck(person.isPersonWorkAccess()));
		else
			labelWorkAccess.setText("Non précisé");

		if (person.getPersonNotes() != null && !person.getPersonNotes().isEmpty())
			labelNote.setText(person.getPersonNotes());
		else
			labelNote.setText("Non précisé");

		/////////////////////////////////////////////////////// Situation socio-Prof

		labelForem.setText(dateCheck(person.getPersonForemInsDate()));

		tableViewDriver.getItems().addAll(person.getPerDrL());

		labelVehicle.setText(booleanCheck(person.isPersonHasVehicle()));

		for (LocomotionMean loc : person.getLocomotionmeans()) {
			labelLocomotionMean.setText(loc.getLocomotionMeanName());

		}

		if (!person.getDisability().getDisOther().isEmpty())
			labelOtherDifficulty.setText(person.getDisability().getDisOther());
		else
			labelOtherDifficulty.setText("Non précisé");

		/////////////////////////////////////////////////////// Langues

		for (PerLan perLan : person.getPerLans()) {
			if (perLan.isLangCom()) {
				labelComLang.setText(perLan.getLanguage().toString());
			}
		}
		if (person.getFrenchTest() != null) {
			if (person.getOther().getOtherLanguageTest() == null)
				labelTestLangage.setText(person.getFrenchTest().getFrenchTestName());
			else
				labelTestLangage.setText(person.getOther().getOtherLanguageTest());
		}

		tableViewLanguage.getItems().addAll(person.getPerLans());

		/////////////////////////////////////////////////////// Aspect emploi-Formation

		labelWorkSearch.setText(booleanCheck(person.getWorksearch().isSearching()));

		listViewWorkSector.getItems().addAll(person.getWorksearch().getWorkSectors());

		listViewAvailability.getItems().addAll(person.getWorksearch().getAvailabilities());

		if (!person.getWorksearch().getWorkSearchOtherAvailibility().isEmpty())
			labelOtherAvailability.setText(person.getWorksearch().getWorkSearchOtherAvailibility());
		else
			labelOtherAvailability.setText("Aucune");

		labelNewsLetter.setText(booleanCheck(person.isPersonNewsLetterWork()));


		tableViewFormations.getItems().addAll(person.getFormations());

		/////////////////////////////////////////////////////// Expériences
		/////////////////////////////////////////////////////// professionnelles

		tableviewExpPro.getItems().addAll(person.getWorkexperiences());
		for (WorkExperience exp : person.getWorkexperiences()) {
			for (WorkTask task : exp.getWorktasks())
				tableViewTask.getItems().add(task);
		}

		/////////////////////////////////////////////////////// Notes
		textAreaNotes.setText(person.getPersonNotepad());

		////////////////////////////////////////////////////// Themes

		tableViewTheme.getItems().addAll(person.getEvents());

	}

	// **************************************************************************************************
	// METHODS
	// **************************************************************************************************

	/**
	 * Method to setup the tableview's columns
	 */
	private void setup() {
		tableColumnLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
		tableColumnLevel.setCellValueFactory(new PropertyValueFactory<>("langLevel"));

		columnJobDuration.setCellValueFactory(new PropertyValueFactory<>("workExpDurationMonth"));
		columnJobSector.setCellValueFactory(new PropertyValueFactory<>("workSector"));
		columnJobType.setCellValueFactory(new PropertyValueFactory<>("workexptype"));
		columnJobName.setCellValueFactory(new PropertyValueFactory<>("workExpName"));
		columnBelgium.setCellValueFactory(new PropertyValueFactory<>("workExpBelgium"));

		columnDriverName.setCellValueFactory(new PropertyValueFactory<>("driverLicense"));
		columnDriverValid.setCellValueFactory(new PropertyValueFactory<>("belgiumValid"));

		columnDiscipline.setCellValueFactory(new PropertyValueFactory<>("formationtype"));
		columnFormationName.setCellValueFactory(new PropertyValueFactory<>("formationName"));
		columnEquiObt.setCellValueFactory(new PropertyValueFactory<>("equObt"));

		columnEquiIntro.setCellValueFactory(new PropertyValueFactory<>("equIntro"));

		columnEquiIntro.setCellFactory(col -> new TableCell<>() {

			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});

		columnEquiObt.setCellFactory(col -> new TableCell<>() {
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
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});

		columnDriverValid.setCellFactory(col -> new TableCell<>() {

			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);
				else
					setText(empty ? null : item ? "Oui" : "Non");
			}

		});

		columnThemeDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
		columnThemeThematique.setCellValueFactory(new PropertyValueFactory<>("theme"));
		columnThemeEmployee.setCellValueFactory(
				cdf -> new SimpleStringProperty(cdf.getValue().getEmployees().toString().replaceAll("\\[|\\]", "")));
		columnThemeDuration.setCellValueFactory(new PropertyValueFactory<>("eventDuration"));
		columnThemeType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
		columnThemeNotes.setCellValueFactory(new PropertyValueFactory<>("eventNote"));

		columnThemeDate.setCellFactory(column -> new TableCell<>() {
			private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

			@Override
			protected void updateItem(Date item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
				} else {
					setText(format.format(item));
				}
			}
		});

		columnTask.setCellValueFactory(new PropertyValueFactory<>("workTaskDescription"));
		columnTaskWorkExp.setCellValueFactory(new PropertyValueFactory<>("workexperience"));

	}

	/**
	 * Check if the date is null, if so, return null
	 *
	 * @param date
	 * @return
	 */
	private String dateCheck(Date date) {
		if (date == null) {
			return "";
		}

		else {
			LocalDate dateLocal = DateUtil.convertToLocalDate(date);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return dateLocal.format(format);
		}
	}

	/**
	 * Check the boolean parameters, if true, write Oui, otherwise, write Non, else
	 * write non précisé
	 *
	 * @param bool
	 * @return
	 */
	private String booleanCheck(Boolean bool) {
		if (bool == null)
			return "Non précisé";
		else if (!bool)
			return "Non";
		else if (bool)
			return "Oui";
		else
			return "";
	}

	// **************************************************************************************************
	// Export Methods
	// **************************************************************************************************
	/**
	 * Button to export the page in a docx
	 *
	 * @throws IOException
	 */
	@FXML
	private void export() {
		try {
			DirectoryChooser dirChooser = new DirectoryChooser();
			File dir = dirChooser.showDialog(this.getScene().getWindow());
			if (dir != null) {
				String fileName = selected.getPersonFirstName() + " " + selected.getPersonLastName();
				String time = DateUtil.dateTimeFile();
				fileName = dir.getAbsolutePath() + File.separator + fileName + time + ".docx";

				XWPFDocument doc = new XWPFDocument();
				XWPFParagraph paragraph = doc.createParagraph();
				XWPFRun run = paragraph.createRun();
				run.setText(selected.getPersonFirstName() + " " + selected.getPersonLastName());
				run.setUnderline(UnderlinePatterns.SINGLE);
				run.setFontSize(22);
				paragraph.setAlignment(ParagraphAlignment.CENTER);
				for (Node node : vBox.getChildren()) {
					if (node instanceof GridPane) {
						exportGridPane(doc, (GridPane) node);
					}
				}

				FileOutputStream output;
				output = new FileOutputStream(fileName);
				doc.write(output);
				doc.close();
				output.close();
				Notifications.create().title("Info").text("Export terminé").showInformation();
			}
		} catch (IOException e) {
			logger.error("IoException", e);
		} finally {

		}

	}

	/**
	 * Export in a XWPFDocument the children node of a grid pane It checks cell per
	 * cell if a node exist and then write it in the document
	 *
	 * @param doc      the XWPFDocument
	 * @param gridPane the Grid Pane
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void exportGridPane(XWPFDocument doc, GridPane gridPane) {
		XWPFTable tab = doc.createTable();
		for (int row = 0; row < gridPane.getRowCount(); row++) {
			XWPFTableRow rowDoc = null;
			int colDoc = 0;
			for (int col = 0; col < gridPane.getColumnCount(); col++) {
				Node node = listGridPane.get(gridPane)[row][col];
				if (node != null && rowDoc == null) {
					rowDoc = tab.createRow();

				}

				if (rowDoc != null && rowDoc.getCell(colDoc) == null && node != null) {
					rowDoc.addNewTableCell();
				}

				if (node instanceof Label) {
					rowDoc.getCell(colDoc).setText(((Label) node).getText());
					if (col == 1 || col == 3) {
						rowDoc.getCell(colDoc).getParagraphs().get(0).getRuns().get(0).setBold(true);

					}
					colDoc++;
				}

				else if (node instanceof ListView) {
					for (Object object : ((ListView) node).getItems()) {
						rowDoc.getCell(colDoc).setText(object.toString() + " ");

					}
					colDoc++;
				}

				else if (node instanceof TableView) {
					new PoiTableViewWord((TableView) node, doc).exportWord();
					colDoc++;
				}

				else if (node instanceof TextArea) {
					rowDoc.getCell(colDoc).setText(((TextArea) node).getText());
					colDoc++;
				}

			}

		}
		tab.createRow();
		tab.removeBorders();
		tab.removeInsideHBorder();
		tab.removeInsideVBorder();

	}

	// **************************************************************************************************
	// LOAD Methods
	// **************************************************************************************************

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonDisplay.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

}
