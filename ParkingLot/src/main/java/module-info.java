module co.edu.poli.parking.parkinglot {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens co.edu.poli.parking.parkinglot to javafx.fxml;
    exports co.edu.poli.parking.parkinglot;
}