package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.NiveauEtudeFSEDAO;
import cripel.jobway.model.IncomeType;
import cripel.jobway.model.NiveauEtudeFSE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class TMNiveauEtudeFSE extends TableManagement<NiveauEtudeFSE>{

    private static NiveauEtudeFSEDAO dao = new NiveauEtudeFSEDAO();
    /** List from the database */
    private static ObservableList<NiveauEtudeFSE> list = FXCollections.observableArrayList(dao.getList());
    /** Income type selected when modifying */
    private NiveauEtudeFSE selected;

    public TMNiveauEtudeFSE() {
        super(list);
    }

    @Override
    protected void addButtonAction() {
        NiveauEtudeFSE niveauEtudeFSE;

        if (selected == null) {
            niveauEtudeFSE = new NiveauEtudeFSE();
        } else {
            niveauEtudeFSE = selected;
        }
        niveauEtudeFSE.setNiveauEtude(addTextField.getText());

        if (!list.contains(niveauEtudeFSE)) {
            list.add(niveauEtudeFSE);
        }

        update(niveauEtudeFSE);
        selected = null;
    }

    @Override
    protected void onCancelAction() {
        addTextField.clear();
        buttonAdd.setText("Ajouter");
        selected = null;
    }

    @Override
    public void setButtonColumn(NiveauEtudeFSE niveauEtudeFSE) {
        selected = niveauEtudeFSE;
        addTextField.setText(niveauEtudeFSE.getNiveauEtude());
    }

    @Override
    public void update(NiveauEtudeFSE niveauEtudeFSE) {
        dao.saveOrUpdate(niveauEtudeFSE,true);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
