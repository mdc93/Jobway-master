package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.EventTypeDAO;
import cripel.jobway.model.EventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMEventType extends TableManagement<EventType> {
	private static EventTypeDAO dao = new EventTypeDAO();
	/** List from the database */
	private static ObservableList<EventType> list = FXCollections.observableArrayList(dao.getList());
	/** Event type selected when modifying */
	private EventType selected;

	public TMEventType() {
		super(list);
	}

	public void addButtonAction() {
		EventType eventType;
		if (selected == null) {
			eventType = new EventType();
		} else {
			eventType = selected;
		}

		eventType.setEventTypeName(addTextField.getText());

		if (!list.contains(eventType)) {
			list.add(eventType);
		}

		update(eventType);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(EventType eventType) {
		selected = eventType;
		addTextField.setText(eventType.getEventTypeName());

	}

	@Override
	public void update(EventType eventType) {
		dao.saveOrUpdate(eventType,true);
	}
}