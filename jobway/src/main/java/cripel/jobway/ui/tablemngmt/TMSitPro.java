package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.SituationProfessionnelleDAO;

import cripel.jobway.model.SituationProfessionnelle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSitPro extends TableManagement<SituationProfessionnelle> {
	private static SituationProfessionnelleDAO dao = new SituationProfessionnelleDAO();
	/** List from the database */
	private static ObservableList<SituationProfessionnelle> list = FXCollections.observableArrayList(dao.getList());
	/** Situation professionelle selected when modifying */
	private SituationProfessionnelle selected;

	public TMSitPro() {
		super(list);
	}

	public void addButtonAction() {
		SituationProfessionnelle situation;
		if (selected == null) {
			situation = new SituationProfessionnelle();
		} else {
			situation = selected;
		}

		situation.setSituationProfessionnelleName(addTextField.getText());

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
	public void setButtonColumn(SituationProfessionnelle situation) {
		selected = situation;
		addTextField.setText(situation.getSituationProfessionnelleName());

	}

	@Override
	public void update(SituationProfessionnelle situation) {
		dao.saveOrUpdate(situation,true);
	}
}

