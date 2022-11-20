module com.example.prototipo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.prototipo to javafx.fxml;
    exports com.example.prototipo;
    exports com.example.prototipo.dao;
    opens com.example.prototipo.dao to javafx.fxml;
    exports com.example.prototipo.memento;
    opens com.example.prototipo.memento to javafx.fxml;
}