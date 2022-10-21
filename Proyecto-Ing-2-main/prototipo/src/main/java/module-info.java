module com.example.prototipo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prototipo to javafx.fxml;
    exports com.example.prototipo;
}