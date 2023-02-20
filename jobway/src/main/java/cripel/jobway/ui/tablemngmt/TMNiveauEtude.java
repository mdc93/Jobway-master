package cripel.jobway.ui.tablemngmt;




import cripel.jobway.dao.NiveauEtudeDAO;
import cripel.jobway.model.NiveauEtude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TMNiveauEtude extends TableManagement<NiveauEtude>{

    
	private static NiveauEtudeDAO dao = new NiveauEtudeDAO();
    /** List from the database */
    private static ObservableList<NiveauEtude> list = FXCollections.observableArrayList(dao.getList());

    private NiveauEtude selected;
    public TMNiveauEtude() {
		super(list);
		
	}
   
	@Override
	public void addButtonAction() {
		NiveauEtude niveauEtude;
		if (selected == null) {
			niveauEtude = new NiveauEtude();
		} else {
			niveauEtude = selected;
		}

		niveauEtude.setNiveauEtudeName(addTextField.getText());

		if (!list.contains(niveauEtude)) {
			list.add(niveauEtude);
		}

		update(niveauEtude);
		selected = null;
		
	}
	@Override
	public void onCancelAction() {
		addTextField.clear();
		buttonAdd.setText("Ajouter");
		selected = null;
		
	}
	@Override
	public void setButtonColumn(NiveauEtude niveauEtude) {
		selected = niveauEtude;
		addTextField.setText(niveauEtude.getNiveauEtudeName());
		
	}
	@Override
	public void update(NiveauEtude niveauEtude) {
		dao.saveOrUpdate(niveauEtude,true);
	}

	
}
