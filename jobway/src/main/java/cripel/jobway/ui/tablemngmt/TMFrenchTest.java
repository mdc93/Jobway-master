package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.FrenchTestDAO;
import cripel.jobway.model.FrenchTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMFrenchTest extends TableManagement<FrenchTest> {
	private static FrenchTestDAO dao = new FrenchTestDAO();
	/** List from the database */
	private static ObservableList<FrenchTest> list = FXCollections.observableArrayList(dao.getList());
	/** French test selected when modifying */
	private FrenchTest selected;

	public TMFrenchTest() {
		super(list);
	}

	public void addButtonAction() {
		FrenchTest frenchTest;
		if (selected == null) {
			frenchTest = new FrenchTest();
		} else {
			frenchTest = selected;
		}

		frenchTest.setFrenchTestName(addTextField.getText());

		if (!list.contains(frenchTest)) {
			list.add(frenchTest);
		}

		update(frenchTest);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(FrenchTest languageTest) {
		selected = languageTest;
		addTextField.setText(languageTest.getFrenchTestName());

	}

	@Override
	public void update(FrenchTest test) {
		dao.saveOrUpdate(test,true);
	}
}
