package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.FormationTypeDAO;
import cripel.jobway.model.FormationType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class TMFormationType extends TableManagement<FormationType> {
	private static FormationTypeDAO dao = new FormationTypeDAO();
	/** List from the database */
	private static ObservableList<FormationType> list = FXCollections.observableArrayList(dao.getList());
	/** Formation type selected when modifying */
	private FormationType selected;
	/** Checkbox to tell if the formation is not a diploma */
	private CheckBox check;

	public TMFormationType() {
		super(list);
		check = new CheckBox();
		getGridPane().add(new Label("Non diplôme:"), 0, 2);
		getGridPane().add(check, 1, 2);
	}

	public void addButtonAction() {
		FormationType formationType;
		if (selected == null) {
			formationType = new FormationType();
		} else {
			formationType = selected;
		}

		formationType.setFormationTypeName(addTextField.getText());
		formationType.setIsFormation(check.isSelected());

		if (!list.contains(formationType)) {
			list.add(formationType);
		}

		update(formationType);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(FormationType formationType) {
		selected = formationType;
		addTextField.setText(formationType.getFormationTypeName());
		check.setSelected(formationType.isIsFormation());

	}

	@Override
	public void update(FormationType formationType) {
		dao.saveOrUpdate(formationType,true);
	}
}
