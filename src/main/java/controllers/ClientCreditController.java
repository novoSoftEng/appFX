package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ClientDAO;
import models.Credit;
import models.CreditDAO;

import java.sql.SQLException;
import java.util.Date;

public class ClientCreditController extends CreditController {
    @FXML
    public void onSaveButtonClick() throws SQLException {

        Credit credit = new Credit(ClientView.getClient_choisit().getId_client(),
                Double.parseDouble(montant.getText()),
                DateCredit.getValue(),
                getChoice()

        );
        CreditDAO creditDAO = new CreditDAO();
        try {
            creditDAO.save(credit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        UpdateTable();

    }

    @Override
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Credit, Long>("id_credit"));
     //   col_client.setCellValueFactory(new PropertyValueFactory<Credit, Long>("id_client"));
        col_montant.setCellValueFactory(new PropertyValueFactory<Credit, Double>("montant"));
        col_date.setCellValueFactory(new PropertyValueFactory<Credit, Date>("DateCredit"));
        col_etat.setCellValueFactory(new PropertyValueFactory<Credit, Boolean>("etat"));
        col_delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col_delete.setCellFactory(param -> new TableCell<Credit, Credit>() {
            private final Button deleteButton = new Button("Delete");

            // @Override
            protected void updateItem(Credit credit, boolean empty) {
                super.updateItem(credit, empty);

                if (credit == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);


                deleteButton.setOnAction(

                        event -> {
                            try {
                                new CreditDAO().delete(credit);
                                UpdateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

        });


        mytab.setItems(getDataCredit());


    }

    public static ObservableList<Credit> getDataCredit(){

        CreditDAO credao = null;
        ClientDAO clientDAO=null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        try {
            credao = new CreditDAO();
            clientDAO= new ClientDAO();
            for(Credit ettemp : clientDAO.getCredit(ClientView.getClient_choisit()))
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }
}
