package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Credit;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditController implements Initializable {

    @FXML
    public TextField montant;
    @FXML
    public DatePicker DateCredit;
    @FXML
    public ChoiceBox etat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    etat.setItems(FXCollections.observableArrayList("True","False"));
    }

    public void onSaveButtonClick() {

    }
}
