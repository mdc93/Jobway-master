package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.SituationTerritoryDAO;
import cripel.jobway.model.SituationTerritory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSitTerr extends TableManagement<SituationTerritory> {
	private static SituationTerritoryDAO dao = new SituationTerritoryDAO();
	/** List from the database */
	private static ObservableList<SituationTerritory> list = FXCollections.observableArrayList(dao.getList());
	/** Situation territory selected when modifying */
	private SituationTerritory selected;

	public TMSitTerr() {
		super(list);
	}

	public void addButtonAction() {
		SituationTerritory situation;
		if (selected == null) {
			situation = new SituationTerritory();
		} else {
			situation = selected;
		}

		situation.setSituationTerritoryName(addTextField.getText());

		if (!list.contains(situation)) {
			list.add(situation);
		}

		update(situation);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(SituationTerritory situation) {
		selected = situation;
		addTextField.setText(situation.getSituationTerritoryName());

	}

	@Override
	public void update(SituationTerritory situation) {
		dao.saveOrUpdate(situation,true);
	}
}
