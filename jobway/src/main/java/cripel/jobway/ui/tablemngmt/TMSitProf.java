
package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.SituationProfDAO;
import cripel.jobway.model.SituationProf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSitProf extends TableManagement<SituationProf> {
	

	private static SituationProfDAO dao = new SituationProfDAO();
	/** List from the database */
	private static ObservableList<SituationProf> list = FXCollections.observableArrayList(dao.getList());
	/** Situation territory selected when modifying */
	private SituationProf selected;

	public TMSitProf() {
		super(list);
	}

	public void addButtonAction() {
		SituationProf situation;
		if (selected == null) {
			situation = new SituationProf();
		} else {
			situation = selected;
		}

		situation.setSituationProfName(addTextField.getText());

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
	public void setButtonColumn(SituationProf situation) {
		selected = situation;
		addTextField.setText(situation.getSituationProfName());

	}

	@Override
	public void update(SituationProf situation) {
		dao.saveOrUpdate(situation,true);
	}


}
