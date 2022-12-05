package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.CountryTypeDAO;
import cripel.jobway.model.CountryType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMCountryType extends TableManagement<CountryType> {
	private static CountryTypeDAO dao = new CountryTypeDAO();
	/** List from the database */
	private static ObservableList<CountryType> list = FXCollections.observableArrayList(dao.getList());
	/** Country type selected when modifying */
	private CountryType selected;

	public TMCountryType() {
		super(list);
	}

	@Override
	public void addButtonAction() {
		CountryType countryType;
		if (selected == null) {
			countryType = new CountryType();
		} else {
			countryType = selected;
		}

		countryType.setCountryTypeName(addTextField.getText());

		if (!list.contains(countryType)) {
			list.add(countryType);
		}

		update(countryType);
		selected = null;

	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(CountryType countryType) {
		selected = countryType;
		addTextField.setText(countryType.getCountryTypeName());

	}

	@Override
	public void update(CountryType countryType) {
		dao.saveOrUpdate(countryType,true);
	}
}