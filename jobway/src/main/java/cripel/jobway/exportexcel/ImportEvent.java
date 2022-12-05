package cripel.jobway.exportexcel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cripel.jobway.model.Employee;
import cripel.jobway.model.Event;
import cripel.jobway.model.EventType;
import cripel.jobway.model.Person;
import cripel.jobway.model.Theme;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Class to import data form an Excel file to a list of {@link Event}
 */
public class ImportEvent {
	private Scene scene;
	private List<Event> listEvent;
	private List<Theme> listTheme;
	private List<Employee> listEmployee;
	private List<EventType> listEventType;
	private Person person;
	/** Save the line which weren't parsed */
	private StringBuilder errorState=new StringBuilder();

	public ImportEvent(Scene scene, List<Event> listEvent, List<Theme> listTheme, List<Employee> listEmployee,
			List<EventType> listEventType, Person person) {
		this.scene = scene;
		this.listEvent = listEvent;
		this.listTheme = listTheme;
		this.listEmployee = listEmployee;
		this.listEventType = listEventType;
		this.person = person;
	}

	/**
	 * Import in an {@link Event} from an Excel file
	 */
	public void importToList() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichier Excel", "*.xlsx"));
		File file = fileChooser.showOpenDialog(scene.getWindow());
		if (file != null) {
			ZipSecureFile.setMinInflateRatio(0);
			try (XSSFWorkbook wb = new XSSFWorkbook(file)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				Iterator<Row> iterator = sheet.rowIterator();
				iterator.next();
				while (iterator.hasNext()) {
					Row row = iterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					Event event = new Event();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						int index = cell.getColumnIndex();
						parseEvent(row, event, cell, index);
					}
					event.setPerson(person);
					if (event.getEventDate() != null && event.getTheme() != null && event.getEventType() != null)
						listEvent.add(event);
					else
						errorState.append("Ligne : " + row.getRowNum()
								+ " ignorée : Vérifiez que les champs sont bien rempli dans l'ordre." + '\n');
				}
			} catch (InvalidFormatException | IllegalArgumentException | IOException e) {
				Alert alert = new Alert(AlertType.WARNING, e.getLocalizedMessage());
				alert.showAndWait();
			}
		}
	}

	/**
	 * Set an {@link Event} parsed attribute.
	 * @param row excel row
	 * @param event the new event
	 * @param cell cell being parsed
	 * @param index index of the column
	 */
	private void parseEvent(Row row, Event event, Cell cell, int index) {
		switch (index) {
		case 0:
			try {
				event.setEventDate(cell.getDateCellValue());
			} catch (IllegalStateException e) {
				errorState.append("Ligne : " + row.getRowNum() + " ignorée : Date invalide." + '\n');
			}
			break;
		case 1:
			try {
				event.setEventDuration((int) cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				errorState.append("Ligne : " + row.getRowNum() + " ignorée : Durée invalide." + '\n');
			}
			break;
		case 2:
			event.setTheme(findTheme(cell));
			break;
		case 3:
			event.setEventType(findEventType(cell));
			break;
		case 4:
			event.getEmployees().addAll(findEmployees(cell));
			break;
		case 5:
			event.setEventNote((cell.getStringCellValue()));
			break;
		default:
			break;
		}
	}

	/**
	 * Cut the error if there is too much for the {@link Alert} and return it
	 *
	 * @return the errorState
	 */
	public String getErrorState() {
		if (String.valueOf(errorState).length() > 400) {
			return StringUtils.abbreviate(errorState.toString(), 400);
		}
		return String.valueOf(errorState.toString());
	}

	/**
	 * Create a new {@link Set} with {@link #listEmployee}. Then set a predicate
	 * with cell value separated by commas and delete all whitespace for the
	 * comparison Then filter the set with
	 * {@link CollectionUtils#filter(Iterable, Predicate)}
	 *
	 * @param cell the cell parsed in excel with Employee separated by commas
	 * @return the filtered employee set
	 */
	private Set<Employee> findEmployees(Cell cell) {
		Set<Employee> set = new HashSet<>();
		set.addAll(listEmployee);
		String employeesString = (cell.getStringCellValue());
		employeesString = StringUtils.deleteWhitespace(employeesString);
		List<String> split = Arrays.asList(employeesString.split("[,]"));
		Predicate<Employee> predicateEmployee= employee->
			 IterableUtils.contains(split, StringUtils.deleteWhitespace(employee.toString()));


		CollectionUtils.filter(set, predicateEmployee);
		return set;
	}

	/**
	 * Set a predicate to find the {@link Theme#toString()} which correspond to the
	 * parsed String in Excel
	 *
	 * @param cell
	 * @return
	 */
	private Theme findTheme(Cell cell) {
		Predicate<Theme> predicateTheme = theme->StringUtils.equals(theme.getThemeName(), (cell.getStringCellValue()));

		return IterableUtils.find(listTheme, predicateTheme);

	}

	/**
	 * Set a predicate to find the {@link EventType#toString()} which correspond to
	 * the parsed String in Excel
	 *
	 * @param cell
	 * @return
	 */
	private EventType findEventType(Cell cell) {
		Predicate<EventType> predicateType = eventType -> StringUtils.equals(eventType.toString(), (cell.getStringCellValue()));

		return IterableUtils.find(listEventType, predicateType);
	}

}
