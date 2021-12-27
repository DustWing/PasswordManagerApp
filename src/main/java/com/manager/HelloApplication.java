package com.manager;

import com.manager.cache.SessionBean;
import com.manager.constants.Images;
import com.manager.constants.Views;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        try {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.PASSWORDS_VIEW));


            //init scene , set size
            Scene scene = new Scene(fxmlLoader.load(), size.getWidth() / 5, size.getHeight() / 3);


            //set title
            stage.setTitle("My Password Manager");


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
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw ex;
        }


    }

    public static void main(String[] args) {
        launch();
    }
}