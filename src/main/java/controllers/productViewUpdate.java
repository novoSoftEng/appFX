package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Produit;
import models.ProduitDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class productViewUpdate extends ProductController {
    @FXML
    public Button ok;



    public void onUpdateButtonClick() throws SQLException, IOException {
        Produit produit =new Produit(produit_choisit.getId_produit(),nom.getText(),fornisseur.getText());
        ProduitDAO produitDAO = new ProduitDAO();
        produitDAO.update(produit);
        Scene scene = ok.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ProductView.fxml"));

        Scene scene1= new Scene(fxmlLoader.load());
        stage.setScene(scene1);
        stage.show();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     nom.setText(produit_choisit.getNomProduit());
     fornisseur.setText(produit_choisit.getFornisseur());
    }

}
