package controllers;

import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditControllerUpdate extends CreditController {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etat.setItems(FXCollections.observableArrayList("True","False"));

    }
}
