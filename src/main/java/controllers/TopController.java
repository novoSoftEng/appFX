package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopController implements Initializable {
@FXML
    public Button home;
    @FXML
    public Button client;
    @FXML
    public Button produit;
    @FXML
    public Button credit;
    @FXML
    public Button commande;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home.setOnAction(param->{
            Scene scene = home.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/Index.fxml"));
            try {
                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();
        } catch (IOException e) {
                throw new RuntimeException(e);
            }});
        client.setOnAction(param->{
            Scene scene = client.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ClientView.fxml"));
            try {
                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }});
        produit.setOnAction(param->{
            Scene scene = home.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ProductView.fxml"));
            try {
                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }});
        credit.setOnAction(param->{
            Scene scene = home.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/CreditView.fxml"));
            try {
                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }});
        commande.setOnAction(param->{
            Scene scene = home.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/CommandeView.fxml"));
            try {
                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }});
    }
}
