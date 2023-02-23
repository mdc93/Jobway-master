package cripel.jobway.ui.tablemngmt;
import cripel.jobway.dao.RequiredDAO;
import cripel.jobway.model.Required;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMRequired extends TableManagement<Required>{
	private static RequiredDAO requiredDAO = new RequiredDAO();
	private static ObservableList<Required> list=FXCollections.observableArrayList(requiredDAO.getList());
	private Required selected;
	public TMRequired() {
		super(list);
		
	}
	@Override
	public void addButtonAction() {
		Required required;
		if (selected == null) {
			required = new Required();
		} else {
			required = selected;
		}

		required.setRequiredName(addTextField.getText());

		if (!list.contains(required)) {
			list.add(required);
		}

		update(required);
		selected = null;
		
	}
	@Override
	public void onCancelAction() {
		
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}
	@Override
	public void setButtonColumn(Required required) {
		selected = required;
		addTextField.setText(required.getRequiredName());
		
	}
	@Override
	public void update(Required required) {
		requiredDAO.saveOrUpdate(required, true);
		
	}

}
