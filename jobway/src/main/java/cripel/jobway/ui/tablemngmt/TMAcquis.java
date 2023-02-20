
package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.AcquisDAO;
import cripel.jobway.model.Acquis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMAcquis extends TableManagement<Acquis> {
	private static AcquisDAO dao = new AcquisDAO();
	private static ObservableList<Acquis> list = FXCollections.observableArrayList(dao.getList());
	/** Selected city when modifying */
	private Acquis selected;

	public TMAcquis() { super(list); 
	// TODO Auto-generated constructor stub }
	}

	@Override
	public void addButtonAction() {
		Acquis acquis;
		if (selected == null) {
			acquis = new Acquis();
		} else {
			acquis = selected;
		}

		acquis.setAcquisName(addTextField.getText());

		if (!list.contains(acquis)) {
			list.add(acquis);
		}

		update(acquis);
		selected = null;

	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Acquis acquis) {
		selected = acquis;
		addTextField.setText(acquis.getAcquisName());

	}

	@Override
	public void update(Acquis acquis) {
		dao.saveOrUpdate(acquis, true);

	}

}
