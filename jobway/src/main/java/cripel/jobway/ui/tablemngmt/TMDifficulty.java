
package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.DifficultyDAO;
import cripel.jobway.model.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMDifficulty extends TableManagement<Difficulty> {
	private static DifficultyDAO dao = new DifficultyDAO();
	private static ObservableList<Difficulty> list = FXCollections.observableArrayList(dao.getList());
	private Difficulty selected;
	public TMDifficulty() {
		super(list);
		// TODO Auto-generated constructor stub
	}

	

	public void addButtonAction() {
		Difficulty difficulty;
		if (selected == null) {
			difficulty = new Difficulty();
		} else {
			difficulty = selected;
		}

		difficulty.setDifficultyName(addTextField.getText());

		if (!list.contains(difficulty)) {
			list.add(difficulty);
		}

		update(difficulty);
		selected = null;

	}

	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	
	
	@Override
	public void setButtonColumn(Difficulty difficulty) {
		selected = difficulty;
		addTextField.setText(difficulty.getDifficultyName());
		
	}

	@Override
	public void update(Difficulty difficulty) {
		dao.saveOrUpdate(difficulty,true);
		
	}


}