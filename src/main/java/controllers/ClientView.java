package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Client;
import models.ClientDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientView implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    @FXML
    private TextField tele;


    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, Long> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;
    @FXML
    private TableColumn<Client, String> col_prenom;

    @FXML
    private TableColumn<Client, String> col_tele;
    @FXML
    public TableColumn<Client,Client> col_delete;


    @FXML
    protected void onSaveButtonClick(){

        Client cli = new Client(nom.getText(),nom.getText(), tele.getText());
        try {
            ClientDAO clidao = new ClientDAO();

            clidao.save(cli);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }








        UpdateTable();

    }



    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Client,Long>("id_client"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("NumTelephone"));
        col_delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col_delete.setCellFactory(param -> new TableCell<Client,Client>() {
            private final Button deleteButton = new Button("Delete");
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (client== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);


                deleteButton.setOnAction(

                        event -> {
                            try {
                                new ClientDAO().delete(client);
                                UpdateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

        });
        mytab.setItems(getDataClients());

    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clidao = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            for(Client ettemp : clidao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}

