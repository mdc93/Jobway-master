package cripel.jobway.ui.tablemngmt;

import cripel.jobway.dao.SituationProDAO;
import cripel.jobway.model.SituationPro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TMSituationPro extends TableManagement<SituationPro> {

    private static SituationProDAO dao = new SituationProDAO();
    /** List from the database */
    private static ObservableList<SituationPro> list = FXCollections.observableArrayList(dao.getList());
    /** Income type selected when modifying */
    private SituationPro selected;

    public TMSituationPro() {
        super(list);
    }

    @Override
    protected void addButtonAction() {
        SituationPro SituationPro;

        if (selected == null) {
            SituationPro = new SituationPro();
        } else {
            SituationPro = selected;
        }

        SituationPro.setnomSituationPro(addTextField.getText());

        if (!list.contains(SituationPro)) {
            list.add(SituationPro);
        }

        update(SituationPro);
        selected = null;

    }

    @Override
    protected void onCancelAction() {
        addTextField.clear();
        buttonAdd.setText("Ajouter");
        selected = null;
    }

    @Override
    public void setButtonColumn(SituationPro SituationPro) {
        selected = SituationPro;
        addTextField.setText(SituationPro.getNomSituationPro());
    }

    @Override
    public void update(SituationPro SituationPro) {
        dao.saveOrUpdate(SituationPro,true);
    }
}
