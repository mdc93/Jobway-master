package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.LanguageDAO;
import cripel.jobway.model.Language;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMLanguage extends TableManagement<Language> {
	private static LanguageDAO dao = new LanguageDAO();
	/** List from the database */
	private static ObservableList<Language> list = FXCollections.observableArrayList(dao.getList());
	/** Language selected when modifying */
	private Language selected;

	public TMLanguage() {
		super(list);
	}

	public void addButtonAction() {
		Language language;
		if (selected == null) {
			language = new Language();
		} else {
			language = selected;
		}

		language.setLanguageName(addTextField.getText());

		if (!list.contains(language)) {
			list.add(language);
		}

		update(language);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Language language) {
		selected = language;
		addTextField.setText(language.getLanguageName());
	}

	@Override
	public void update(Language language) {
		dao.saveOrUpdate(language,true);
	}
}
