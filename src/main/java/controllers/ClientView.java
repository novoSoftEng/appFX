package controllers;

import com.example.appfx.HelloApplication;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Client;
import models.ClientDAO;
import models.Credit;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;




public class ClientView implements Initializable {

    @FXML
    protected TextField nom;
    @FXML
    protected TextField prenom;

    @FXML
    protected TextField tele;
    protected static Client client_choisit;


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
    public TableColumn<Client,Client> col_update;
    @FXML
    public TableColumn<Client,Double> col_credit;

    public static Client getClient_choisit() {
        return client_choisit;
    }

    public static void setClient_choisit(Client client_choisit) {
        ClientView.client_choisit = client_choisit;
    }

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
        col_credit.setCellValueFactory(celldata->{
            ClientDAO clientDAO= null;
            try {
                clientDAO = new ClientDAO();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {

              double creditTotal=  clientDAO.getCreditTotal(celldata.getValue());
                return (ObservableValue<Double>) new SimpleDoubleProperty(creditTotal).asObject();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });


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
        col_update.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col_update.setCellFactory(param -> new TableCell<Client,Client>() {
            private final Button updateButton = new Button("Update");
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (client== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(updateButton);


                updateButton.setOnAction(

                        event -> {
                            client_choisit=client;
                            Scene scene = updateButton.getScene();
                            Window window = scene.getWindow();
                            Stage stage = (Stage) window;
                            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ClientViewUpdate.fxml"));
                            try {
                               Scene scene1= new Scene(fxmlLoader.load());
                                stage.setScene(scene1);
                                stage.show();
                            } catch (IOException e) {
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

