package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Commande;
import models.CommandeDAO;
import models.Credit;
import models.CreditDAO;

import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    @FXML
    public DatePicker DateCommande;
    @FXML
    public TableView<Commande> mytab;
    @FXML
    public TableColumn<Commande,Long> col_id;
    @FXML
    public TableColumn<Commande,Long> col_client;
    @FXML
    public TableColumn<Commande,Date> col_date;
    @FXML
    public TableColumn<Commande,Commande> col_delete;
    @FXML
    public TableColumn<Commande,Commande> col_update;

    @FXML

    protected TextField id_client;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }

    @FXML
    protected void onSaveButtonClick() throws SQLException {

        Commande commande = new Commande(Long.parseLong(id_client.getText()),DateCommande.getValue());
        CommandeDAO commandeDAO= new CommandeDAO();

        try {
            commandeDAO.save(commande);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        UpdateTable();

    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        col_client.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_client"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commande, Date>("DateCommande"));
        col_delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col_delete.setCellFactory(param -> new TableCell<Commande,Commande>() {
            private final Button deleteButton = new Button("Delete");
            // @Override
            protected void updateItem(Commande commande, boolean empty) {
                super.updateItem(commande, empty);

                if (commande== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);


                deleteButton.setOnAction(

                        event -> {
                            try {
                                new CommandeDAO().delete(commande);
                                UpdateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

        });
        col_update.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col_update.setCellFactory(param -> new TableCell<Commande,Commande>() {
            private final Button updateButton = new Button("Update");
            // @Override
            protected void updateItem(Commande commande, boolean empty) {
                super.updateItem(commande, empty);

                if (commande== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(updateButton);


                updateButton.setOnAction(

                        event -> {
                            try {
                                new CommandeDAO().delete(commande);
                                UpdateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

        });

        mytab.setItems(getDataCommande());
    }
    public static ObservableList<Commande> getDataCommande(){

       CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for(Commande ettemp : commandeDAO.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

}

