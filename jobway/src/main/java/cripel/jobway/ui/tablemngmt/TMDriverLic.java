package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.DriverLicenseDAO;
import cripel.jobway.model.DriverLicense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMDriverLic extends TableManagement<DriverLicense> {
	private static DriverLicenseDAO drlDAO = new DriverLicenseDAO();
	/** List from the database */
	private static ObservableList<DriverLicense> list = FXCollections.observableArrayList(drlDAO.getList());
	/** Driver license selected when modifying */
	private DriverLicense selected;

	public TMDriverLic() {
		super(list);

	}

	public void addButtonAction() {
		DriverLicense driverLicense;
		if (selected == null) {
			driverLicense = new DriverLicense();
		} else {
			driverLicense = selected;
		}

		driverLicense.setDriverLicenseType(addTextField.getText());

		if (!list.contains(driverLicense)) {
			list.add(driverLicense);
		}

		update(driverLicense);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(DriverLicense driverLicense) {
		selected = driverLicense;
		addTextField.setText(driverLicense.getDriverLicenseType());

	}

	@Override
	public void update(DriverLicense driverLicense) {
		drlDAO.saveOrUpdate(driverLicense,true);
	}
}
