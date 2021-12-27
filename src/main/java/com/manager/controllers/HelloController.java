package com.manager.controllers;

import com.manager.cache.SessionBean;
import com.manager.constants.Images;
import com.manager.constants.Views;
import com.manager.models.PasswordModel;
import com.manager.services.PasswordService;
import com.manager.services.PasswordServiceGrpc;
import com.manager.services.PasswordServiceLocal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class HelloController {

    @FXML
    public VBox mainId;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView loading;

    @FXML
    public void initialize() {

        Button button = new Button("start");

        mainId.getChildren().add(button);

    }


    @FXML
    protected void onLoginBtn() {

        if (username.getText() == null || username.getText().isEmpty()) {

            welcomeText.setText("Username must not be empty");

            username.setBorder(new Border(
                    new BorderStroke(
                            Paint.valueOf("red"),
                            BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY,
                            BorderWidths.DEFAULT
                    )
            ));

            return;
        }

        if (password.getText() == null || password.getText().isEmpty()) {
            welcomeText.setText("Password must not be empty");
            return;
        }

        SessionBean.getInstance().setUsername(username.getText());
        SessionBean.getInstance().setPassword(password.getText().toCharArray());

        //remove username password
        username.setText(null);
        password.setText(null);

        welcomeText.setVisible(false);
        username.setDisable(true);
        password.setDisable(true);
        loginBtn.setDisable(true);
        loading.setVisible(true);

        loading.setImage(new Image(
                        Objects.requireNonNull(
                                this.getClass().getResourceAsStream(Images.MOTH_PNG)),
                        50,
                        50,
                        false,
                        false
                )
        );


        PasswordService<List<PasswordModel>> passwordService;
        switch (SessionBean.getInstance().getEnvironment()) {
            case LOCAL:
                passwordService = new PasswordServiceLocal(
                        SessionBean.getInstance().getPassword(),
                        SessionBean.getInstance().getUsername().getBytes(StandardCharsets.UTF_8)
                );
                break;
            case GRPC:
                passwordService = new PasswordServiceGrpc();
                break;
            default:
                throw new IllegalArgumentException();
        }


        loadPasswordView();


    }

    private void loadPasswordView() {
        try {

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Views.PASSWORDS_VIEW)));

            //init scene , set size
            Scene scene = new Scene(root, size.getWidth() / 2, size.getHeight() / 2);
            Stage stage = new Stage();


            //set title
            stage.setTitle("DW-passwords");


            //add icon
            stage.getIcons().add(
                    new Image(
                            Objects.requireNonNull(
                                    getClass().getResource(Images.MOTH_PNG)
                            ).toString()
                    )
            );

            //set style
            scene.getStylesheets().add(
                    Objects.requireNonNull(
                            this.getClass().getResource(SessionBean.getInstance().getSelectedTheme())
                    ).toString()
            );

            //set scene
            stage.setScene(scene);

            stage.show();


            Stage currentStage = (Stage) loginBtn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}