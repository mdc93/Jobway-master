package cripel.jobway.ui.tablemngmt;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.controlsfx.control.textfield.CustomTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.utilities.fxutil.ButtonTableCell;
import cripel.jobway.utilities.fxutil.Confirm;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * The Class TableManagement.
 *
 * @param <T> the generic type
 */
public abstract class TableManagement<T> extends BorderPane {

	/** The text field to write the name of the field . */
	@FXML
	protected TextField addTextField;

	/** The table view. */
	@FXML
	private TableView<T> tableView;

	/** The column with a the object name. */
	@FXML
	private TableColumn<T, String> column;

	/** The column button edit. */
	@FXML
	private TableColumn<T, Button> columnEdit;

	/** The column button delete. */
	@FXML
	private TableColumn<T, Button> columnDelete;

	/** The search bar. */
	@FXML
	private CustomTextField searchBar;
	/** CheckBox to show deleted items */
	@FXML
	private CheckBox checkBoxShowDelete;

	/** The grid pane which contains textfields and button. */
	@FXML
	private GridPane gridPane;

	/** The button add. */
	@FXML
	protected Button buttonAdd;

	/** The list from the database. */
	private ObservableList<T> list;

	/** The filtered list */
	private FilteredList<T> fil;

	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/**
	 * Instantiates a new table management.
	 *
	 * @param list the list from the database
	 */
	protected TableManagement(ObservableList<T> list) {
		this.list = list;
		load();
		setup();

	}

	/**
	 * Action via Fxml of the button add
	 */
	@FXML
	protected void onAdd() {
		if (addTextField.getText().length() < 50 && addTextField.getText().length() >= 1) {
			addButtonAction();
			buttonAdd.setText("Ajouter");
			addTextField.clear();
			tableView.refresh();
		}

		else if (addTextField.getText().length() > 50) {
			Alert alert = new Alert(AlertType.INFORMATION, "Le nom est trop long");
			alert.show();
		} else if (addTextField.getText().length() < 1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Le nom ne peut pas être vide");
			alert.show();
		}

	}

	/**
	 * Abstract method so the children can use the button Add
	 */
	protected abstract void addButtonAction();

	/**
	 * Action clear search bar.
	 */
	@FXML
	protected void actionClearSearchBar() {
		searchBar.clear();
	}

	/**
	 * Abstract method to clear field and selected item
	 */
	@FXML
	protected abstract void onCancelAction();

	/**
	 * Using Javafx setPredicate property of a list to only display in the tableView
	 * element containing the Strings inputed in the search bar.
	 */
	public void setupSearch() {
		fil = new FilteredList<>(list);
		showDelete(false);

		listenerCheckBoxShowDelete();

		searchBar.textProperty().addListener((obs, oldValue, newValue) -> fil.setPredicate(t -> {
			if (newValue == null || newValue.isEmpty() && !getMethodIsDelete(t)) {
				return true;
			}

			return (String.valueOf(t.toString()).toLowerCase().contains(newValue.toLowerCase()) && !getMethodIsDelete(t));

		}));
		SortedList<T> sortedData = new SortedList<>(fil);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedData);
	}

	/**
	 * Show delete.
	 *
	 * @param filter the filter
	 */
	public void showDelete(boolean filter) {
		fil.setPredicate(t -> {
			if (!getMethodIsDelete(t))
				return !filter;
			return filter;
		});
	}

	/**
	 * Listener that show the element deleted if the checkbox showDelete is checked.
	 * Change
	 */
	protected void listenerCheckBoxShowDelete() {
		checkBoxShowDelete.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.booleanValue()) {
				showDelete(true);
				columnDelete.setCellFactory(
						ButtonTableCell.<T>forTableColumn(null, "button-delete", "fas-trash-restore-alt", (T t) -> {
							delete(t, false);
							return t;
						}));
			} else {
				showDelete(false);
				columnDelete.setCellFactory(
						ButtonTableCell.<T>forTableColumn(null, "button-delete", "fas-trash-alt", (T t) -> {
							delete(t, true);
							return t;
						}));
			}
		});

	}

	/**
	 * Set the table column with a method associated with the to string property.
	 */
	protected void setTableColumn() {
		column.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue().toString()));
	}

	/**
	 * Populate table view.
	 */
	protected void populateTableView() {

		tableView.getItems().addAll(list);
	}

	/**
	 * Abstract method to sets the button column functionality in the children
	 *
	 * @param t the generic type
	 */
	public abstract void setButtonColumn(T t);

	/**
	 * Abstract method to use child dao save or update method
	 *
	 * @param t the generic type
	 */
	public abstract void update(T t);

	/**
	 * Call method setDelete with java reflection.
	 *
	 * @param t the generic class
	 */
	protected void delete(T t, boolean isDelete) {
		Confirm confirm = new Confirm();

		if (confirm.confirmPopUp(true)) {
			try {
				Method method = t.getClass().getMethod("setDelete", boolean.class);
				method.invoke(t, isDelete);
				update(t);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				logger.error("Erreur isDelete non trouvé", e);
			}
		}

		showDelete(!isDelete);
	}

	/**
	 * Gets the method is delete with java reflection.
	 *
	 * @param t the generic class
	 * @return the method is delete
	 */
	private boolean getMethodIsDelete(T t) {
		Method method;
		boolean isDelete = false;
		try {
			method = t.getClass().getMethod("isDelete");
			isDelete = (boolean) method.invoke(t);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			logger.error("Erreur isDelete non trouvé", e);
		}

		return isDelete;
	}

	/**
	 * Setup.
	 */
	public void setup() {
		setTableColumn();
		populateTableView();
		setupSearch();
		columnEdit.setCellFactory(ButtonTableCell.<T>forTableColumn(null, "button-edit", "far-edit", (T t) -> {
			setButtonColumn(t);
			buttonAdd.setText("Modifier");
			return t;
		}));

		columnDelete.setCellFactory(ButtonTableCell.<T>forTableColumn(null, "button-delete", "fas-trash-alt", (T t) -> {
			delete(t, true);
			return t;
		}));

	}

	/**
	 * Load the fxml.
	 */
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableManagement.fxml"));

		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Gets the table view.
	 *
	 * @return the tableView
	 */
	protected TableView<T> getTableView() {
		return tableView;
	}

	/**
	 * Sets the table view.
	 *
	 * @param tableView the tableView to set
	 */
	protected void setTableView(TableView<T> tableView) {
		this.tableView = tableView;
	}

	/**
	 * Gets the adds the text field.
	 *
	 * @return the addTextField
	 */
	public TextField getAddTextField() {
		return addTextField;
	}

	/**
	 * Sets the adds the text field.
	 *
	 * @param addTextField the addTextField to set
	 */
	public void setAddTextField(TextField addTextField) {
		this.addTextField = addTextField;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public ObservableList<T> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the list to set
	 */
	public void setList(ObservableList<T> list) {
		this.list = list;
	}

	/**
	 * Gets the grid pane.
	 *
	 * @return the gridPane
	 */
	public GridPane getGridPane() {
		return gridPane;
	}

	/**
	 * Sets the grid pane.
	 *
	 * @param gridPane the gridPane to set
	 */
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

}
