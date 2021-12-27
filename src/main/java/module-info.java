module com.manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;


    opens com.manager.components to javafx.fxml;
    exports com.manager.components;

    opens com.manager.controllers to javafx.fxml;
    exports com.manager.controllers;

    opens com.manager to javafx.fxml;
    exports com.manager;

    opens com.manager.models to com.fasterxml.jackson.databind;
    exports com.manager.models;

}