package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    static Produit produit_choisit;
    @FXML
    protected TableColumn col_id;
    @FXML
    protected TableColumn col_nom;
    @FXML
    protected TableColumn col_fornisseur;
    @FXML
    protected TableColumn<Produit,Produit> col_delete;
    @FXML
    protected TableColumn<Produit,Produit> col_update;
    @FXML
    protected TableView<Produit> mytab;
    @FXML
    protected TextField fornisseur;
    @FXML
    protected TextField nom;
    

  


    public void onSaveButtonClick() throws SQLException {
        Produit produit =new Produit(nom.getText(),fornisseur.getText());
        ProduitDAO produitDAO = new ProduitDAO();
        produitDAO.save(produit);
        UpdateTable();

    }
    private void UpdateTable() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
        col_fornisseur.setCellValueFactory(new PropertyValueFactory<Produit,String>("fornisseur"));
        col_update.setCellValueFactory(param->new ReadOnlyObjectWrapper<>(param.getValue()));

        col_delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col_delete.setCellFactory(param -> new TableCell<Produit,Produit>() {
            private final Button deleteButton = new Button("Delete");
            // @Override
            protected void updateItem(Produit produit, boolean empty) {
                super.updateItem(produit, empty);

                if (produit== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);


                deleteButton.setOnAction(

                        event -> {
                            try {
                                new ProduitDAO().delete(produit);
                                UpdateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

        });

        col_update.setCellFactory(param -> new TableCell<Produit,Produit>() {
            private final Button updateButton = new Button("Update");
            @Override
            protected void updateItem(Produit produit, boolean empty) {
                super.updateItem(produit, empty);

                if (produit== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(updateButton);


                updateButton.setOnAction(

                        event -> {
                            produit_choisit=produit;
                            Scene scene = updateButton.getScene();
                            Window window = scene.getWindow();
                            Stage stage = (Stage) window;
                            FXMLLoader fxmlLoader=  new FXMLLoader(ClientView.class.getResource("/com/example/appfx/ProductViewUpdate.fxml"));
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
        mytab.setItems(getDataproduit());
    }

    private ObservableList<Produit> getDataproduit() throws SQLException {
        ProduitDAO produitDAO= new ProduitDAO();
        ObservableList<Produit> listfx = FXCollections.observableArrayList();
        for(Produit ettemp : produitDAO.getAll())
            listfx.add(ettemp);
        return listfx;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
