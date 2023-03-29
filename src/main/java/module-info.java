module com.example.appfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.appfx to javafx.fxml;
    exports com.example.appfx;
    exports controllers;
    opens controllers to javafx.fxml;
}