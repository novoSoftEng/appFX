package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Client;
import models.Credit;
import models.CreditDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
    static Credit credit_choisit;

    @FXML
    public TextField montant;
    @FXML
    public TextField id_client;
    @FXML
    public DatePicker DateCredit;
    @FXML
    protected TableView<Credit> mytab;
    @FXML
    public ChoiceBox etat;
    @FXML
    public TableColumn<Credit, Long> col_id;
    @FXML
    public TableColumn<Credit, Long> col_client;
    @FXML
    public TableColumn<Credit, Double> col_montant;
    @FXML
    public TableColumn<Credit, Date> col_date;
    @FXML
    public TableColumn<Credit,Boolean> col_etat;
    @FXML
    protected TableColumn<Credit,Credit> col_delete;
    @FXML
    protected TableColumn<Credit,Credit> col_update;
    public Boolean getChoice(){
       String valeur= (String) etat.getValue();
        if (Objects.equals(valeur, "Payés"))
            return Boolean.TRUE;
        else if (Objects.equals(valeur, "Non Payés")) {
            return Boolean.FALSE;
        }
        else return Boolean.FALSE;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    etat.setItems(FXCollections.observableArrayList("Payés","Non Payés"));
        UpdateTable();

    }


    @FXML
    protected void onSaveButtonClick() throws SQLException {

     Credit credit = new Credit(Long.parseLong(id_client.getText()),
             Double.parseDouble(montant.getText()),
             DateCredit.getValue(),
             getChoice()

             );
     CreditDAO creditDAO= new CreditDAO();
        try {
            creditDAO.save(credit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


         UpdateTable();

    }



    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Credit,Long>("id_credit"));
        col_client.setCellValueFactory(new PropertyValueFactory<Credit,Long>("id_client"));
        col_montant.setCellValueFactory(new PropertyValueFactory<Credit,Double>("montant"));
        col_date.setCellValueFactory(new PropertyValueFactory<Credit,Date>("DateCredit"));
        col_etat.setCellValueFactory(new PropertyValueFactory<Credit,Boolean>("etat"));
        col_update.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col_delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col_delete.setCellFactory(param -> new TableCell<Credit,Credit>() {
            private final Button deleteButton = new Button("Delete");
           // @Override
            protected void updateItem(Credit credit, boolean empty) {
                super.updateItem(credit, empty);

                if (credit== null) {
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
        col_update.setCellFactory(param -> new TableCell<Credit,Credit>() {
            private final Button updateButton = new Button("Update");
            @Override
            protected void updateItem(Credit credit, boolean empty) {
                super.updateItem(credit, empty);

                if (credit== null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(updateButton);


                updateButton.setOnAction(

                        event -> {
                            credit_choisit=credit;
                            Scene scene = updateButton.getScene();
                            Window window = scene.getWindow();
                            Stage stage = (Stage) window;
                            FXMLLoader fxmlLoader=  new FXMLLoader(CreditController.class.getResource("/com/example/appfx/CreditViewUpdate.fxml"));
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


    mytab.setItems(getDataCredit());
    }

    public static ObservableList<Credit> getDataCredit(){

        CreditDAO credao = null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        try {
            credao = new CreditDAO();
            for(Credit ettemp : credao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

}
