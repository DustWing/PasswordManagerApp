package com.manager.components;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class ButtonCustom extends javafx.scene.control.Button {

    public ButtonCustom() {

        super();

        this.setBorder(
                new Border(
                        createBorderStroke()
                )
        );

        this.setBackground(
                createBackground()
        );

        this.setTextFill(Paint.valueOf("#bdc1c6"));

    }


    private Background createBackground() {
        return new Background(
                new BackgroundFill(
                        Paint.valueOf("#202124"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        );
    }

    private BorderStroke createBorderStroke() {

        return new BorderStroke(
                Paint.valueOf("grey"),
                new BorderStrokeStyle(
                        StrokeType.CENTERED,
                        StrokeLineJoin.ROUND,
                        StrokeLineCap.ROUND,
                        0,
                        0,
                        null
                ),
                new CornerRadii(2),
                new BorderWidths(2)
        );
    }
}
