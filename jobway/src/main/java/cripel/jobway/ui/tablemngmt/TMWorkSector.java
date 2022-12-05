package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.WorkSectorDAO;
import cripel.jobway.model.WorkSector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMWorkSector extends TableManagement<WorkSector> {
	private static WorkSectorDAO dao = new WorkSectorDAO();
	/** List from the database */
	private static ObservableList<WorkSector> list = FXCollections.observableArrayList(dao.getList());
	/** Work sector selected when modifying */
	private WorkSector selected;

	public TMWorkSector() {
		super(list);
	}

	public void addButtonAction() {
		WorkSector workSector;
		if (selected == null) {
			workSector = new WorkSector();
		} else {
			workSector = selected;
		}

		workSector.setWorkSectorName(addTextField.getText());

		if (!list.contains(workSector)) {
			list.add(workSector);
		}

		update(workSector);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(WorkSector workSector) {
		selected = workSector;
		addTextField.setText(workSector.getWorkSectorName());
	}

	@Override
	public void update(WorkSector sector) {
		dao.saveOrUpdate(sector,true);
	}
}