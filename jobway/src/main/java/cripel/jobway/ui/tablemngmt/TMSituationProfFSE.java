package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.SituationProfFSEDAO;
import cripel.jobway.model.SituationProfFSE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSituationProfFSE extends TableManagement<SituationProfFSE> {

    private static SituationProfFSEDAO dao = new SituationProfFSEDAO();
    /** List from the database */
    private static ObservableList<SituationProfFSE> list = FXCollections.observableArrayList(dao.getList());
    /** Income type selected when modifying */
    private SituationProfFSE selected;

    public TMSituationProfFSE() {
        super(list);
    }

    @Override
    protected void addButtonAction() {
        SituationProfFSE situationProfFSE;

        if (selected == null) {
            situationProfFSE = new SituationProfFSE();
        } else {
            situationProfFSE = selected;
        }

        situationProfFSE.setSituationProf(addTextField.getText());

        if (!list.contains(situationProfFSE)) {
            list.add(situationProfFSE);
        }

        update(situationProfFSE);
        selected = null;

    }

    @Override
    protected void onCancelAction() {
        addTextField.clear();
        buttonAdd.setText("Ajouter");
        selected = null;
    }

    @Override
    public void setButtonColumn(SituationProfFSE situationProfFSE) {
        selected = situationProfFSE;
        addTextField.setText(situationProfFSE.getSituationProf());
    }

    @Override
    public void update(SituationProfFSE situationProfFSE) {
        dao.saveOrUpdate(situationProfFSE,true);
    }
}
