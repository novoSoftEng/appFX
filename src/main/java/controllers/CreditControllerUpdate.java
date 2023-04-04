package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Commande;
import models.CommandeDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditControllerUpdate extends CreditController {
@FXML
    public Button ok;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etat.setItems(FXCollections.observableArrayList("Payés","Non Payés"));

    }

    public void onUpdateButtonClick() throws SQLException, IOException {
        Commande commande = new Commande(CreditController.credit_choisit.getId_credit(), (long) Double.parseDouble( montant.getText()), Date.valueOf(DateCredit.getValue()));
        CommandeDAO commandeDAO= new CommandeDAO();
        commandeDAO.update(commande);
        Scene scene = ok.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/CreditView.fxml"));

        Scene scene1= new Scene(fxmlLoader.load());
        stage.setScene(scene1);
        stage.show();
    }
}
