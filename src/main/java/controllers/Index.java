package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import models.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

public class Index {
    @FXML
    private TextField pass;
    @FXML
    private TextField user;


    public void onLogin() throws SQLException, IOException {
        User user1= new User(user.getText(),pass.getText());
        UserDAO userDAO=new UserDAO();
        if (userDAO.inspect(user1)){
            Scene scene = pass.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader=  new FXMLLoader(Index.class.getResource("/com/example/appfx/ClientView.fxml"));
            Scene scene1= new Scene(fxmlLoader.load());
            stage.setScene(scene1);
            stage.show();


        }
    }
}
