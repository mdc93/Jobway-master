package cripel.jobway.ui;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.dialog.ProgressDialog;

import cripel.jobway.exportexcel.PersonExport;
import cripel.jobway.exportexcel.PersonExportHeader;
import cripel.jobway.exportexcel.PoiUtil;
import cripel.jobway.model.Person;
import cripel.jobway.utilities.fxutil.DatePickerUtil;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** The Dialog Controller class to export people data to excel */
public class ExcelDialog extends BorderPane {
	/** Checkbox to enable or disable {@link #datePickerBegin} */
	@FXML
	private CheckBox checkBoxDate;

	/** The DatePicker to choose the begin date linked to {@link #datePickerEnd} */
	@FXML
	private DatePicker datePickerBegin;
	/**
	 * The DatePicker to choose the end of the period linked to
	 * {@link #datePickerBegin}
	 */
	@FXML
	private DatePicker datePickerEnd;

	/**
	 * ObservableList to reference the constructor parameter
	 * {@link #ExcelDialog(ObservableList)}
	 */
	private ObservableList<Person> listPerson;

	public ExcelDialog(ObservableList<Person> listPerson) {
		load();
		setupDatePicker();
		this.listPerson = listPerson;
	}

	/** Use {@link DatePickerUtil} methods to setup the datepickers */
	private void setupDatePicker() {
		DatePickerUtil.setLimit(datePickerBegin, datePickerEnd);
		DatePickerUtil.listenerValidDate(datePickerBegin, datePickerEnd);
	}

	/**
	 * Create a {@link Task} and a {@link Service} to display the progress of the
	 * export with a {@link ProgressDialog}
	 *
	 */
//	private void taskExport() {
//		DirectoryChooser dir = new DirectoryChooser();
//		File file = dir.showDialog(this.getScene().getWindow());
//
//		if (file != null) {
//			XSSFWorkbook wb = new XSSFWorkbook();
//			XSSFSheet sheet = wb.createSheet("Data");
//			Service<Void> service = new Service<>() {
//				@Override
//				protected Task<Void> createTask() {
//					return new Task<>() {
//						@Override
//						protected Void call() throws InterruptedException, IOException {
//
//							int progress = 0;
//							int countColumn = PersonExportHeader.createHeader(wb, sheet.createRow(0));
//							updateMessage("Création des titres");
//							for (Person person : listPerson) {
//								PersonExport exp = new PersonExport(person, datePickerBegin.getValue(),
//										datePickerEnd.getValue());
//								PoiUtil.export(exp.getMap(), wb, sheet);
//								updateProgress(progress++, listPerson.size() + 1);
//								updateMessage("Ligne : "+progress);
//
//							}
//							updateMessage("Adaptation de la largeur des colonnes");
//							PoiUtil.resizeColumn(sheet, countColumn);
//							updateProgress(progress++, listPerson.size() + 1);
//
//							try (OutputStream output = new FileOutputStream(createFileName(file))) {
//								wb.write(output);
//
//							} catch (IOException e) {
//								Alert alert = new Alert(AlertType.ERROR, e.getCause().getLocalizedMessage());
//								alert.showAndWait();
//							} finally {
//								wb.close();
//							}
//
//							return null;
//						}
//					};
//				}
//			};
//
//			ProgressDialog dialog = new ProgressDialog(service);
//			dialog.setHeaderText("En cours d'export");
//			dialog.setTitle("Export");
//			dialog.initModality(Modality.NONE);
//			dialog.initOwner(this.getScene().getWindow());
//			dialog.show();
//			service.start();
//
//		}
//	}

	private void taskExport() throws FileNotFoundException {
		DirectoryChooser dir = new DirectoryChooser();
		File file = dir.showDialog(this.getScene().getWindow());
		File fileExport = new File(createFileName(file));

		if (fileExport!=null) {
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Data");

			FileOutputStream output = new FileOutputStream(fileExport);
			Service<Void> service = new Service<>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<>() {
						@Override
						protected Void call() throws InterruptedException, IOException {

							int progress = 0;
							int countColumn = PersonExportHeader.createHeader(wb, sheet.createRow(0));
							updateMessage("Création des titres");
							fillWorkbookExport(wb,sheet);
//							for (Person person : listPerson) {
//							for (Person person : listPerson) {
//								PersonExport exp = new PersonExport(person, datePickerBegin.getValue(),
//										datePickerEnd.getValue());
//								PoiUtil.export(exp.getMap(), wb,  sheet);
//								updateProgress(progress++, listPerson.size() + 1);
//								updateMessage("Ligne : "+progress);
////								try  {
////									wb.write(output);
////
////								} catch (IOException e) {
////									Alert alert = new Alert(AlertType.ERROR, e.getCause().getLocalizedMessage());
////									alert.showAndWait();
////
////								}
//
//							}
							System.out.println("test");

							updateMessage("Adaptation de la largeur des colonnes");
							PoiUtil.resizeColumn((XSSFSheet) sheet, countColumn);
							updateProgress(progress++, listPerson.size() + 1);
//							FileOutputStream output = new FileOutputStream(fileExport);


//							try (OutputStream output = new FileOutputStream(createFileName(file))) {
							try  {
								wb.write(output);

							} catch (IOException e) {
								Alert alert = new Alert(AlertType.ERROR, e.getCause().getLocalizedMessage());
								alert.showAndWait();
							}
							output.close();
							wb.close();

							return null;
						}
					};
				}
			};

			ProgressDialog dialog = new ProgressDialog(service);
			dialog.setHeaderText("En cours d'export");
			dialog.setTitle("Export");
			dialog.initModality(Modality.NONE);
			dialog.initOwner(this.getScene().getWindow());
			dialog.show();
			service.start();

		}
	}

	/**
	 * Create a file name with a datetime pattern
	 *
	 * @param file the folder path
	 * @return The file name
	 */
	private String createFileName(File file) {
		String fileName;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
		String time = ZonedDateTime.now(ZoneId.systemDefault()).format(format);
		fileName = file.getAbsolutePath() + File.separator + "Export-" + time + ".xlsx";
		return fileName;
	}

	/**
	 * Listener on action on the to start the export Task {@link #taskExport()}
	 *
	 * @param event
	 */
	@FXML
	void onActionStart(ActionEvent event) throws FileNotFoundException {
		if (listPerson != null && !listPerson.isEmpty()) {
			taskExport();
		}

		closeDialog(event);
	}

	/**
	 * Listener on {@link #checkBoxDate} to enable or disable the
	 * {@link #datePickerBegin}
	 */
	@FXML
	private void listenerCheckBoxDate() {
		datePickerBegin.setDisable(!checkBoxDate.isSelected());
		if (!checkBoxDate.isSelected()) {
			datePickerBegin.setValue(null);
		}

	}

	/**
	 * Listener on action to close the dialog with the quit button
	 *
	 * @param event
	 */
	@FXML
	private void closeDialog(ActionEvent event) {
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.close();
	}

	/**
	 * Method to load an fxml and set the controller
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExcelDialog.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void fillWorkbookExport(XSSFWorkbook wb, XSSFSheet sheet){

		try{
			for (Person person : listPerson) {
				PersonExport exp = new PersonExport(person, datePickerBegin.getValue(),
						datePickerEnd.getValue());
				PoiUtil.export(exp.getMap(), wb, sheet);
			}
		}
		catch(Exception e){
			e.getMessage();
		}
	}

}
