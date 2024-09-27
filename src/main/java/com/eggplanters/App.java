package com.eggplanters;

import java.io.File;
import java.util.Objects;

import com.eggplanters.lib.AppEntry;
import com.eggplanters.lib.AppEntryNode;
import com.eggplanters.lib.AppStoreReader;
import com.eggplanters.lib.DetailsPane;
import com.eggplanters.lib.NotJSONException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private VBox appList;
    private VBox appDetails;

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(createRoot(), 640, 480);
        stage.setScene(scene);

        // Font faces are from Google Fonts
        Font.loadFont(getClass().getResourceAsStream("/com/eggplanters/Poppins.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/com/eggplanters/Poppins-Bold.ttf"), 12);

        stage.getScene().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("dracula-theme.css")).toExternalForm());
        // Stylesheet provided by https://github.com/mkpaz/atlantafx, modified to fit
        // the app

        stage.setTitle("Eggplanters Store");
        stage.getIcons().add(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/eggplanters/app_icon.png"))));
        loadApps();

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public Parent createRoot() {
        // This method creates the base UI wherein the appStore.json file will populate
        // it.
        HBox hBox = new HBox();

        VBox leftVBox = new VBox();
        leftVBox.setPrefSize(300, 400);
        leftVBox.setMinSize(300, Region.USE_COMPUTED_SIZE);
        leftVBox.setMaxSize(400, Region.USE_COMPUTED_SIZE);
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

        // Details
        VBox rightVBox = new VBox();
        HBox.setHgrow(rightVBox, Priority.ALWAYS);
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

        hBox.getChildren().addAll(leftVBox, rightVBox);

        VBox parent = new VBox();
        parent.getChildren().addAll(hBox);
        VBox.setVgrow(hBox, Priority.ALWAYS);
        parent.getStyleClass().add("parent");
        return parent;
    }

    public void addEntry(AppEntry appEntry, VBox appList) {

        AppEntryNode node = new AppEntryNode(appEntry);
        node.setOnAction((e) -> {
            setDetails(appEntry);

            // Sets the clicked entry as selected
            for (var appEntries : appList.getChildren()) {
                appEntries.getStyleClass().removeIf((s) -> s.equals("selected"));
            }
            node.getStyleClass().add("selected");
        });

        appList.getChildren().add(node);
    }

    public void loadApps() {

        File file = new File("src/main/resources/com/eggplanters/appStore.json");
        try {
            AppStoreReader appStoreReader = new AppStoreReader(file);
            var appEntries = appStoreReader.parseJSON();
            for (AppEntry entry : appEntries) {

                addEntry(entry, appList);
            }
        } catch (NotJSONException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDetails(AppEntry entry) {
        appDetails.getChildren().clear();
        appDetails.getChildren().add(new DetailsPane(entry));
    }
}