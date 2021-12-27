package com.manager.components;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class RotatingLoading extends ImageView {

    public RotatingLoading() {
        super();
        rotate();

    }

    private void rotate() {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), this);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }


}
