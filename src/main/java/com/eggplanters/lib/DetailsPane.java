package com.eggplanters.lib;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class DetailsPane extends VBox {
    public DetailsPane(AppEntry appEntry) {
        Label appTitle = new Label(appEntry.getTitle());
        appTitle.getStyleClass().add("detail-title");
        Label appGenre = new Label(appEntry.getGenre());
        appGenre.getStyleClass().add("detail-subtitle");
        Label appPublisher = new Label("Published by " + appEntry.getPublisher());
        appPublisher.getStyleClass().add("detail-subtitle");
        Label appMetricsText = new Label(
                appEntry.getStar_rating() + " - " + formatNumber(appEntry.getDownloads()) + "+ downloads");
        appMetricsText.getStyleClass().add("detail-subtitle");
        HBox appMetrics = new HBox(12);
        appMetrics.getChildren().addAll(
                new Icon(Objects.requireNonNull(getClass().getResourceAsStream("/com/eggplanters/star.png")), 18),
                appMetricsText);

        HBox headerPane = new HBox(12);

        Icon appImage;
        if (appEntry.getImageUrl() == null || appEntry.getImageUrl().isBlank()) {
            appImage = new Icon(
                    Objects.requireNonNull(getClass().getResourceAsStream("/com/eggplanters/app_placeholder.png")),
                    156);
        } else {
            appImage = new Icon(appEntry.getImageUrl(), 156);
        }

        appImage.setBorderRadius(12);

        VBox headerText = new VBox(12);
        headerText.getChildren().addAll(appTitle, appPublisher, appGenre, appMetrics);
        headerPane.getChildren().addAll(appImage, headerText);

        TextFlow description = new TextFlow();
        description.getChildren().add(new Text(appEntry.getDescription()));
        description.getStyleClass().add("description");
        description.setPadding(new Insets(24));
        description.setTextAlignment(TextAlignment.JUSTIFY);
        description.setLineSpacing(4);

        Label descriptionLabel = new Label("Description");
        descriptionLabel.setPadding(new Insets(12, 12, 4, 12));
        descriptionLabel.getStyleClass().add("description-label");

        this.setSpacing(12);
        this.getChildren().addAll(headerPane, descriptionLabel, description);
    }

    private String formatNumber(int number) {
        // For the number of downloads, as show in the sample picture
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.SHORT);
        return fmt.format(number);
    }
}
