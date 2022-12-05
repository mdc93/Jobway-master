package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.FamilyReunionDAO;
import cripel.jobway.model.FamilyReunion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMFamReun extends TableManagement<FamilyReunion> {
	private static FamilyReunionDAO dao = new FamilyReunionDAO();
	/** List from the database */
	private static ObservableList<FamilyReunion> list = FXCollections.observableArrayList(dao.getList());
	/** Family reunion selected when modifying */
	private FamilyReunion selected;

	public TMFamReun() {
		super(list);
	}

	public void addButtonAction() {
		FamilyReunion familyReunion;
		if (selected == null) {
			familyReunion = new FamilyReunion();
		} else {
			familyReunion = selected;
		}

		familyReunion.setFamilyReunionType(addTextField.getText());

		if (!list.contains(familyReunion)) {
			list.add(familyReunion);
		}

		update(familyReunion);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(FamilyReunion familyReunion) {
		selected = familyReunion;
		addTextField.setText(familyReunion.getFamilyReunionType());

	}

	@Override
	public void update(FamilyReunion familyReunion) {
		dao.saveOrUpdate(familyReunion,true);
	}
}
