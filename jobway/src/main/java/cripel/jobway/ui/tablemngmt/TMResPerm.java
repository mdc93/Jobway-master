package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.ResidencePermitTypeDAO;
import cripel.jobway.model.ResidencePermitType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class TMResPerm extends TableManagement<ResidencePermitType> {
	private static ResidencePermitTypeDAO dao = new ResidencePermitTypeDAO();
	/** List from the database */
	private static ObservableList<ResidencePermitType> list = FXCollections.observableArrayList(dao.getList());
	/** Residence permit type selected when modifying */
	private ResidencePermitType selected;
	/**
	 * Check box to tell if the Residence permit needs an annex or other information
	 */
	private CheckBox check;

	public TMResPerm() {
		super(list);
		check = new CheckBox();
		Tooltip tooltip = new Tooltip("Cochez cette case si ce permis a besoin d'une annexe ou d'autre information");
		Label label = new Label("Besoin information:");
		getGridPane().add(label, 0, 2);
		getGridPane().add(check, 1, 2);
		label.setTooltip(tooltip);
		check.setTooltip(tooltip);
	}

	public void addButtonAction() {
		ResidencePermitType permitType;
		if (selected == null) {
			permitType = new ResidencePermitType();
		} else {
			permitType = selected;
		}

		permitType.setResidencePermitTypeName(addTextField.getText());
		permitType.setResidencePermitNeedAnnex(check.isSelected());

		if (!list.contains(permitType)) {
			list.add(permitType);
		}

		update(permitType);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(ResidencePermitType residencePermit) {
		selected = residencePermit;
		addTextField.setText(residencePermit.getResidencePermitTypeName());
		check.setSelected(residencePermit.isResidencePermitNeedAnnex());

	}

	@Override
	public void update(ResidencePermitType permit) {
		dao.saveOrUpdate(permit,true);
	}
}