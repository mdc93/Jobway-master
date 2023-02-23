package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.NiveauEtudeDAO;
import cripel.jobway.model.NiveauEtude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMNiveauEtude extends TableManagement<NiveauEtude> {

	private static NiveauEtudeDAO niveauetudeDAO = new NiveauEtudeDAO();
	/** List from the database */
	private static ObservableList<NiveauEtude> list=FXCollections.observableArrayList(niveauetudeDAO.getList());

	/** Theme selected when modifying */
	private NiveauEtude selected;

	public TMNiveauEtude() {
		super(list);
		
	}

	public void addButtonAction() {
		NiveauEtude niveauEtude;
		if (selected == null) {
			niveauEtude = new NiveauEtude();
		} else {
			niveauEtude = selected;
		}

		niveauEtude.setNiveauEtudeName(addTextField.getText());

		if (!list.contains(niveauEtude)) {
			list.add(niveauEtude);
		}

		update(niveauEtude);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(NiveauEtude niveauEtude) {
		selected = niveauEtude;
		addTextField.setText(niveauEtude.getNiveauEtudeName());

	}

	@Override
	public void update(NiveauEtude niveauEtude) {
		niveauetudeDAO.saveOrUpdate(niveauEtude, true);
	}
}
