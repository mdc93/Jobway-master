package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.CountryDAO;
import cripel.jobway.dao.CountryTypeDAO;
import cripel.jobway.model.Country;
import cripel.jobway.model.CountryType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class TMCountry extends TableManagement<Country> {

	private static CountryDAO countryDAO = new CountryDAO();
	/** List from the database */
	private static ObservableList<Country> list = FXCollections
			.observableArrayList(countryDAO.getListCustom("SELECT c FROM Country c JOIN FETCH c.countrytype"));
	/** Combobox to select the country type */
	private ChoiceBox<CountryType> comboBox;
	/** Country selected when modifying */
	private Country selected;

	public TMCountry() {
		super(list);
		comboBox = new ChoiceBox<>();
		comboBox.getItems().addAll(new CountryTypeDAO().getListDelete(false));
		getGridPane().add(new Label("Type"), 0, 2);
		getGridPane().add(comboBox, 1, 2);

	}

	@Override
	public void addButtonAction() {
		Country country;
		if (selected == null) {
			country = new Country();
		} else {
			country = selected;
		}

		country.setCountryName(addTextField.getText());
		country.setCountrytype(comboBox.getValue());

		if (!list.contains(country)) {
			list.add(country);
		}

		update(country);
		selected = null;
		comboBox.setValue(null);
	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		comboBox.getSelectionModel().clearSelection();
		comboBox.setValue(null);
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Country country) {
		selected = country;
		comboBox.getSelectionModel().clearSelection();
		comboBox.valueProperty().set(null);
		addTextField.setText(country.getCountryName());
		comboBox.getSelectionModel().select(country.getCountrytype());
		comboBox.setValue(country.getCountrytype());

	}

	@Override
	public void update(Country country) {
		countryDAO.saveOrUpdate(country,true);
	}
}
