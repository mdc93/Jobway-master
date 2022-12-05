package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.LocomotionMeanDAO;
import cripel.jobway.model.LocomotionMean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMLocomotionMean extends TableManagement<LocomotionMean> {
	private static LocomotionMeanDAO dao = new LocomotionMeanDAO();
	/** List from the database */
	private static ObservableList<LocomotionMean> list = FXCollections.observableArrayList(dao.getList());
	/** Locomotion mean selected when modifying */
	private LocomotionMean selected;

	public TMLocomotionMean() {
		super(list);
	}

	public void addButtonAction() {
		LocomotionMean mean;
		if (selected == null) {
			mean = new LocomotionMean();
		} else {
			mean = selected;
		}

		mean.setLocomotionMeanName(addTextField.getText());

		if (!list.contains(mean)) {
			list.add(mean);
		}

		update(mean);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(LocomotionMean locomotionMean) {
		selected = locomotionMean;
		addTextField.setText(locomotionMean.getLocomotionMeanName());

	}

	@Override
	public void update(LocomotionMean loc) {
		dao.saveOrUpdate(loc,true);
	}
}