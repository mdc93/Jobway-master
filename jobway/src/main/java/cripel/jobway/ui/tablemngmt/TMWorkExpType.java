package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.WorkExpTypeDAO;
import cripel.jobway.model.WorkExpType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMWorkExpType extends TableManagement<WorkExpType> {
	private static WorkExpTypeDAO dao = new WorkExpTypeDAO();
	/** List from the database */
	private static ObservableList<WorkExpType> list = FXCollections.observableArrayList(dao.getList());
	/** Work exp selected when modifying */
	private WorkExpType selected;

	public TMWorkExpType() {
		super(list);
	}

	@Override
	public void addButtonAction() {
		WorkExpType workExpType;
		if (selected == null) {
			workExpType = new WorkExpType();
		} else {
			workExpType = selected;
		}

		workExpType.setWorkExpTypeName(addTextField.getText());

		if (!list.contains(workExpType)) {
			list.add(workExpType);
		}

		update(workExpType);
		selected = null;

	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(WorkExpType workExpType) {
		selected = workExpType;
		addTextField.setText(workExpType.getWorkExpTypeName());

	}

	@Override
	public void update(WorkExpType exp) {
		dao.saveOrUpdate(exp,true);
	}
}
