package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.IncomeTypeDAO;
import cripel.jobway.model.IncomeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMIncomeType extends TableManagement<IncomeType> {
	private static IncomeTypeDAO dao = new IncomeTypeDAO();
	/** List from the database */
	private static ObservableList<IncomeType> list = FXCollections.observableArrayList(dao.getList());
	/** Income type selected when modifying */
	private IncomeType selected;

	public TMIncomeType() {
		super(list);
	}

	public void addButtonAction() {
		IncomeType incomeType;
		if (selected == null) {
			incomeType = new IncomeType();
		} else {
			incomeType = selected;
		}

		incomeType.setIncomeTypeName(addTextField.getText());

		if (!list.contains(incomeType)) {
			list.add(incomeType);
		}

		update(incomeType);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(IncomeType income) {
		selected = income;
		addTextField.setText(income.getIncomeTypeName());

	}

	@Override
	public void update(IncomeType incomeType) {
		dao.saveOrUpdate(incomeType,true);
	}
}