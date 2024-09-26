package com.eggplanters;

import java.io.File;
import java.io.IOException;

import com.eggplanters.lib.AppEntry;
import com.eggplanters.lib.AppStoreReader;
import com.eggplanters.lib.NotJSONException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class App extends Application {

    private static Scene scene;
    private VBox appList;
    private VBox appDetails;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(getRoot(), 1280, 720);

        stage.setScene(scene);
        stage.getScene().getStylesheets().addAll(
                getClass().getResource("dracula-theme.css").toExternalForm()
                ,getClass().getResource("fontstyle.css").toExternalForm());
        stage.setTitle("App Store");

        loadApps();

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public Parent getRoot() {
        HBox hBox = new HBox();
        hBox.setPrefSize(1280, 720);

        VBox leftVBox = new VBox();
        leftVBox.setPrefSize(300, 400);
        leftVBox.setMinSize(300,Region.USE_COMPUTED_SIZE);
        HBox.setHgrow(leftVBox, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(200, 200);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        appList = new VBox();
        appList.setMaxHeight(Double.MAX_VALUE);
        appList.setMaxWidth(Double.MAX_VALUE);
        appList.setPadding(new Insets(12));
        appList.setSpacing(12);

        scrollPane.setContent(appList);

        leftVBox.getChildren().add(scrollPane);

        //Details
        VBox rightVBox = new VBox();
        HBox.setHgrow(rightVBox,Priority.ALWAYS);
        rightVBox.setPrefSize(700, 400);

        ScrollPane detailsScrollPane = new ScrollPane();
        detailsScrollPane.setFitToWidth(true);

        detailsScrollPane.setPrefSize(200, 200);
        VBox.setVgrow(detailsScrollPane, Priority.ALWAYS);

        appDetails = new VBox();
        appDetails = new VBox();
        appDetails.setMaxHeight(Double.MAX_VALUE);
        appDetails.setMaxWidth(Double.MAX_VALUE);
        appDetails.setPadding(new Insets(12));
        appDetails.setSpacing(12);

        detailsScrollPane.setContent(appDetails);
        rightVBox.getChildren().add(detailsScrollPane);

        hBox.getChildren().addAll(leftVBox, rightVBox); // Empty right VBox for layout
        return hBox;
    }

    public void addEntry(AppEntry appEntry, VBox appList) {
        // Create the Button
        Button button = new Button();
        button.setPrefHeight(31);
        button.setOnAction((e)->{
            setDetails(appEntry);
        });

        button.setMaxWidth(Double.MAX_VALUE);

        HBox buttonGraphic = new HBox();
        buttonGraphic.setPrefSize(200, 100);
        buttonGraphic.setSpacing(12);
        buttonGraphic.setAlignment(Pos.CENTER_LEFT);

        Image image = new Image(getClass().getResourceAsStream("/com/eggplanters/app_placeholder.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);

        VBox textVBox = new VBox();
        textVBox.setPrefSize(100, 200);
        HBox.setHgrow(textVBox,Priority.ALWAYS);

        Label appNameLabel = new Label(appEntry.getTitle());
        appNameLabel.getStyleClass().add("title");
        appNameLabel.setMaxWidth(Double.MAX_VALUE);

        Label appGenreLabel = new Label(appEntry.getPublisher() + " - " + appEntry.getGenre());
        appGenreLabel.getStyleClass().add("subtitle");
        appGenreLabel.setMaxWidth(Double.MAX_VALUE);

        textVBox.getChildren().addAll(appNameLabel, appGenreLabel);

        buttonGraphic.getChildren().addAll(imageView, textVBox);

        button.setGraphic(buttonGraphic);

        appList.getChildren().add(button);
    }

    public void loadApps() {
        File file = new File("src/main/resources/com/eggplanters/appStore.json");
        try {
            AppStoreReader appStoreReader = new AppStoreReader(file);
            var appEntries = appStoreReader.parseJSON();
            for(AppEntry entry : appEntries){
                addEntry(entry, appList);
            }
        } catch (NotJSONException e) {
            System.out.println("File is not JSON");
        }
    }
    public void setDetails(AppEntry entry){
        Label appTitle = new Label(entry.getTitle());
        Label appGenre = new Label(entry.getGenre());
        Label appPublisher = new Label(entry.getPublisher());
        Label appRating = new Label(Double.toString(entry.getStar_rating()));
        Label appDownloads = new Label(Integer.toString(entry.getDownloads()));
        appDetails.getChildren().clear();
        appDetails.getChildren().addAll(appTitle, appGenre,appPublisher,appRating,appDownloads);
    }
}