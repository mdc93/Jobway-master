package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.AvailabilitiesDAO;
import cripel.jobway.model.Availability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMAvailability extends TableManagement<Availability> {

	private static AvailabilitiesDAO dao = new AvailabilitiesDAO();
	/** List from the database */
	private static ObservableList<Availability> list = FXCollections.observableArrayList(dao.getList());
	/** Selected availability when modifying */
	private Availability selected;

	public TMAvailability() {
		super(list);
	}

	public void addButtonAction() {
		Availability availability;
		if (selected == null) {
			availability = new Availability();
		} else {
			availability = selected;
		}

		availability.setAvailability(addTextField.getText());

		if (!list.contains(availability)) {
			list.add(availability);
		}

		update(availability);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Availability availability) {
		selected = availability;
		addTextField.setText(availability.getAvailability());
	}

	@Override
	public void update(Availability availability) {
		dao.saveOrUpdate(availability,true);
	}
}