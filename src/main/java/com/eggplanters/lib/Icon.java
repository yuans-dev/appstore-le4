package com.eggplanters.lib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class Icon extends ImageView {
    public Icon(InputStream imageStream, int size) {
        Image image = new Image(imageStream);
        this.setImage(image);
        this.setFitWidth(size);
        this.setFitHeight(size);
    }

    public Icon(String imageUrl, int size) {
        Image image = new Image(imageUrl);
        this.setImage(image);
        this.setFitWidth(size);
        this.setFitHeight(size);
    }

    public void setBorderRadius(int radius) {
        Rectangle clip = new Rectangle(
                this.getFitWidth(), this.getFitHeight());
        clip.setArcWidth(radius);
        clip.setArcHeight(radius);
        this.setClip(clip);
    }
}
