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
import models.Client;
import models.ClientDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientViewUpdate implements Initializable {
    @FXML
    protected TextField nom;
    @FXML
    protected TextField prenom;

    @FXML
    protected TextField tele;
    @FXML
    protected Button ok;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    nom.setText(ClientView.getClient_choisit().getNom());
        prenom.setText(ClientView.getClient_choisit().getPrenom());
        tele.setText(ClientView.getClient_choisit().getNumTelephone());
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        Client cli = new Client(ClientView.getClient_choisit().getId_client(),nom.getText(),prenom.getText(), tele.getText());
        try {
            ClientDAO clidao = new ClientDAO();

            clidao.update(cli);
            Scene scene = ok.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ClientView.fxml"));

                Scene scene1= new Scene(fxmlLoader.load());
                stage.setScene(scene1);
                stage.show();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
