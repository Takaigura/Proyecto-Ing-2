package co.edu.poli.parking.parkinglot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ParkingController implements Initializable {

    @FXML
    private TextField placa_field;

    @FXML
    private Button button_eliminar;

    @FXML
    private Button button_modificar;

    @FXML
    private Button button_registrar;

    @FXML
    private TableColumn<Auto, String> col_placa;

    @FXML
    private TableColumn<Auto, String> col_propietario;

    @FXML
    private TextField propietario_field;

    @FXML
    private TableView<Auto> tabla_principal;

    @FXML
    void eliminarDatos(ActionEvent event) {

    }

    @FXML
    void modificarDatos(ActionEvent event) {

    }
    ObservableList<Auto> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_placa.setCellValueFactory(new PropertyValueFactory<Auto, String>("placa"));
        col_propietario.setCellValueFactory(new PropertyValueFactory<Auto, String>("propietario"));
        tabla_principal.setItems(lista);
    }
    @FXML
    void registrarDatos(ActionEvent event) {
        String placa_nueva = placa_field.getText();
        String propietario_nuevo = propietario_field.getText();
        Auto nuevo = new Auto(placa_nueva, propietario_nuevo);

        lista.add(nuevo);
        tabla_principal.setItems(lista);
    }


}
