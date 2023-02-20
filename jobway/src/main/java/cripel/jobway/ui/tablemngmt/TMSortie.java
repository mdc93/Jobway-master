package cripel.jobway.ui.tablemngmt;
import cripel.jobway.dao.SortieDAO;
import cripel.jobway.model.Sortie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSortie extends TableManagement<Sortie>{
	private static SortieDAO dao = new SortieDAO();
	private static ObservableList<Sortie> list = FXCollections.observableArrayList(
			dao.getList());
	/** Selected city when modifying */
	private Sortie selected;
	

	public TMSortie() {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addButtonAction() {
		Sortie sortie;
		if (selected == null) {
			sortie = new Sortie();
		} else {
			sortie = selected;
		}

		sortie.setSortieName(addTextField.getText());

		if (!list.contains(sortie)) {
			list.add(sortie);
		}

		update(sortie);
		selected = null;

	}

	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
	}

	@Override
	public void setButtonColumn(Sortie sortie) {
		selected = sortie;
		addTextField.setText(sortie.getSortieName());

		
	}

	@Override
	public void update(Sortie sortie) {
		dao.saveOrUpdate(sortie,true);
		
	}

}
