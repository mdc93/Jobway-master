package cripel.jobway.ui.tablemngmt;

import org.controlsfx.control.SearchableComboBox;

import cripel.jobway.dao.CityDAO;
import cripel.jobway.dao.PostalCodeDAO;
import cripel.jobway.model.City;
import cripel.jobway.model.Postalcode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TMCity extends TableManagement<City> {
	private static CityDAO dao = new CityDAO();
	private static ObservableList<City> list = FXCollections.observableArrayList(
			dao.getListCustom("SELECT c FROM City c LEFT JOIN FETCH c.postalcode ORDER BY cityName"));
	/** Selected city when modifying */
	private City selected;
	/** ComboBox to link a city to a postalcode */
	private SearchableComboBox<Postalcode> comboBox;

	public TMCity() {
		super(list);
		comboBox = new SearchableComboBox<>();
		comboBox.getItems().addAll(new PostalCodeDAO().getList());
		getGridPane().add(new Label("Type"), 0, 2);
		getGridPane().add(comboBox, 1, 2);
		TableColumn<City, String> columnCP = new TableColumn<>();
		columnCP.setText("Code postal");
		columnCP.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
		getTableView().getColumns().add(1, columnCP);
	}

	public void addButtonAction() {
		City city;
		if (selected == null) {
			city = new City();
		}

		else {
			city = selected;
		}

		city.setCityName(addTextField.getText());
		city.setPostalcode(comboBox.getSelectionModel().getSelectedItem());

		if (!list.contains(selected)) {
			list.add(city);
		}

		update(city);
		comboBox.setValue(null);
		selected = null;
	}

	public void onCancelAction() {
		addTextField.clear();
		comboBox.setValue(null);
		selected = null;
		buttonAdd.setText("Ajouter");
	}

	@Override
	public void setButtonColumn(City city) {
		selected = city;
		comboBox.getSelectionModel().select(city.getPostalcode());
		addTextField.setText(city.getCityName());

	}

	@Override
	public void update(City city) {
		dao.saveOrUpdate(city,true);
	}
}
