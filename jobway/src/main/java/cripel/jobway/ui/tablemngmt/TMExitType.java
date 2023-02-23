package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.ExitTypeDAO;
import cripel.jobway.model.ExitType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMExitType extends TableManagement<ExitType>{
	
	private static ExitTypeDAO exittypeDAO = new ExitTypeDAO();
	private static ObservableList<ExitType> list=FXCollections.observableArrayList(exittypeDAO.getList());
	private ExitType selected;
	public TMExitType() {
		super(list);
		
	}

	@Override
	public void addButtonAction() {
		ExitType exitType;
		if (selected == null) {
			exitType = new ExitType();
		} else {
			exitType = selected;
		}

		exitType.setExitTypeName(addTextField.getText());

		if (!list.contains(exitType)) {
			list.add(exitType);
		}

		update(exitType);
		selected = null;
		
	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
		
	}

	@Override
	public void setButtonColumn(ExitType exitType) {
		selected = exitType;
		addTextField.setText(exitType.getExitTypeName());
		
	}

	@Override
	public void update(ExitType exitType) {
		exittypeDAO.saveOrUpdate(exitType, true);
		
	}

}
