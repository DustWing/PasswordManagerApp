package com.manager.controllers;

import com.manager.models.PasswordModel;
import com.manager.models.PasswordModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class PasswordsController {

    @FXML
    public TableView<PasswordModel> passwordTable;

    @FXML
    private StackPane mainId;


    @FXML
    public void initialize() {


        final ObservableList<PasswordModel> password = FXCollections.observableArrayList();
        password.add(PasswordModelFactory.createForTest(1));
        password.add(PasswordModelFactory.createForTest(2));
        password.add(PasswordModelFactory.createForTest(3));


        passwordTable.setItems(password);

        passwordTable.setPrefWidth(600);

        passwordTable.getColumns().forEach(e ->
                e.setPrefWidth(passwordTable.getPrefWidth() / passwordTable.getColumns().size())
        );

        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(null);

    }

    @FXML
    public double getValue(double value) {
        return passwordTable.getPrefWidth() / value;
    }
}
