package cripel.jobway.ui;

import cripel.jobway.model.Employee;
import cripel.jobway.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Management extends BorderPane implements Initializable {

    @FXML
    private TableColumn<Employee, String> columnEmployee;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
