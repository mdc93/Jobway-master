package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.CivilStatusDAO;
import cripel.jobway.model.CivilStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMCivilStatus extends TableManagement<CivilStatus> {
	private static CivilStatusDAO dao = new CivilStatusDAO();
	/** List from the database */
	private static ObservableList<CivilStatus> list = FXCollections.observableArrayList(dao.getList());
	/** Selected civil status when modifying */
	private CivilStatus selected;

	public TMCivilStatus() {
		super(list);
	}

	public void addButtonAction() {
		CivilStatus civilStatus;
		if (selected == null) {
			civilStatus = new CivilStatus();
		} else {
			civilStatus = selected;
		}

		civilStatus.setCivilStatusName(addTextField.getText());

		if (!list.contains(civilStatus)) {
			list.add(civilStatus);
		}

		update(civilStatus);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(CivilStatus civilStatus) {
		selected = civilStatus;
		addTextField.setText(civilStatus.getCivilStatusName());

	}

	@Override
	public void update(CivilStatus civilStatus) {
		dao.saveOrUpdate(civilStatus,true);
	}
}