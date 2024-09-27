package com.eggplanters.lib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Icon extends ImageView {
    public Icon(InputStream imageStream, int size){
        Image image = new Image(imageStream);
        this.setImage(image);
        this.setFitWidth(size);
        this.setFitHeight(size);
    }
}
