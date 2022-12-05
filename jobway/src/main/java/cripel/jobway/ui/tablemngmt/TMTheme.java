package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.ThemeDAO;
import cripel.jobway.model.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMTheme extends TableManagement<Theme> {
	private static ThemeDAO themeDAO = new ThemeDAO();
	/** List from the database */
	private static ObservableList<Theme> list = FXCollections.observableArrayList(themeDAO.getList());
	/** Theme selected when modifying */
	private Theme selected;

	public TMTheme() {
		super(list);

	}

	public void addButtonAction() {
		Theme theme;
		if (selected == null) {
			theme = new Theme();
		} else {
			theme = selected;
		}

		theme.setThemeName(addTextField.getText());

		if (!list.contains(theme)) {
			list.add(theme);
		}

		update(theme);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Theme theme) {
		selected = theme;
		addTextField.setText(theme.getThemeName());

	}

	@Override
	public void update(Theme theme) {
		themeDAO.saveOrUpdate(theme,true);
	}
}
