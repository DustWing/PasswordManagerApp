package com.manager.controllers;

import com.manager.cache.SessionBean;
import com.manager.models.PasswordModel;
import com.manager.services.PasswordServiceLocal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AddPasswordController {


    @FXML
    public TextField username;

    @FXML
    public TextField password;

    @FXML
    public TextField domain;

    @FXML
    public TextArea description;


    public void cancelButtonClick(ActionEvent actionEvent) {
    }

    public void addNewPassButtonClick(ActionEvent actionEvent) {

        final PasswordModel passwordModel = new PasswordModel(UUID.randomUUID().toString(),
                username.getText(),
                password.getText(),
                domain.getText(),
                description.getText(),
                "default");

        SessionBean.getInstance().getPasswordModelList().add(passwordModel);

        new PasswordServiceLocal(
                SessionBean.getInstance().getPassword(),
                SessionBean.getInstance().getUsername().getBytes(StandardCharsets.UTF_8)
        );

        System.out.println(passwordModel);

    }
}
