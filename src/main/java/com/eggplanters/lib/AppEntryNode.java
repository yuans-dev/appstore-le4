package com.eggplanters.lib;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class AppEntryNode extends Button {
    public AppEntryNode(AppEntry appEntry) {

        this.setPrefHeight(31);
        this.setMaxWidth(Double.MAX_VALUE);

        HBox buttonGraphic = new HBox();
        buttonGraphic.setPrefSize(200, 100);
        buttonGraphic.setSpacing(12);
        buttonGraphic.setAlignment(Pos.CENTER_LEFT);
        Image image;
        if (appEntry.getImageUrl() == null || appEntry.getImageUrl().isBlank()) {
            image = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/com/eggplanters/app_placeholder.png")));
        } else {
            image = new Image(appEntry.getImageUrl());
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);

        VBox textVBox = new VBox();
        textVBox.setPrefSize(100, 200);
        HBox.setHgrow(textVBox, Priority.ALWAYS);

        Label appNameLabel = new Label(appEntry.getTitle());
        appNameLabel.getStyleClass().add("title");
        appNameLabel.setMaxWidth(Double.MAX_VALUE);

        Label appGenreLabel = new Label(appEntry.getPublisher() + " - " + appEntry.getGenre());
        appGenreLabel.getStyleClass().add("subtitle");
        appGenreLabel.setMaxWidth(Double.MAX_VALUE);

        textVBox.getChildren().addAll(appNameLabel, appGenreLabel);

        buttonGraphic.getChildren().addAll(imageView, textVBox);

        this.setGraphic(buttonGraphic);
    }
}
