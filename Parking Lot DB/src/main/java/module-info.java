module com.example.prototipo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.prototipo to javafx.fxml;
    exports com.example.prototipo;
}