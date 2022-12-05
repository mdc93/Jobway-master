package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.PostalCodeDAO;
import cripel.jobway.model.Postalcode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMPostal extends TableManagement<Postalcode> {
	private static PostalCodeDAO postalDAO = new PostalCodeDAO();
	/** List from the database */
	private static ObservableList<Postalcode> list = FXCollections.observableArrayList(postalDAO.getList());
	/** Postal code selected when modifying */
	private Postalcode selected;

	public TMPostal() {
		super(list);
	}

	@Override
	public void addButtonAction() {
		Postalcode postalCode;
		if (selected == null) {
			postalCode = new Postalcode();
		} else {
			postalCode = selected;
		}

		postalCode.setPostalCodeNumber(addTextField.getText());

		if (!list.contains(postalCode)) {
			list.add(postalCode);
		}

		update(postalCode);
		selected = null;

	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Postalcode postalCode) {
		selected = postalCode;
		addTextField.setText(postalCode.getPostalCodeNumber());

	}

	@Override
	public void update(Postalcode code) {
		postalDAO.saveOrUpdate(code,true);
	}
}
