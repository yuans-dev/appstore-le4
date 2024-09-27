module com.eggplanters {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;

    exports com.eggplanters;
    exports com.eggplanters.lib;

    opens com.eggplanters to javafx.fxml;
    opens com.eggplanters.lib to com.google.gson;
}