package com.example.prototipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button Registrar;
    @FXML
    private Button Modificar;
    @FXML
    private Button Eliminar;
    @FXML
    private TextField Propietario;
    @FXML
    private TextField Placa;
    @FXML
    private TableView<Auto> Tabla;
    @FXML
    private TableColumn col_Placa;
    @FXML
    private TableColumn col_Propietario;
    @FXML
    private ObservableList<Auto> Autos;


    @FXML
    private void BotonRegistrar(ActionEvent event) {
        String propietario = this.Propietario.getText();
        String placa = this.Placa.getText();
        Auto p = new Auto(propietario, placa);

        Alert alerta;
        if (propietario.isEmpty() || placa.isEmpty()) {
            alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos Faltantes");
            alerta.setHeaderText("Faltan datos por introducir");
            alerta.setContentText("Introduzca todos los datos por favor");
        } else {
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos correctos");
            alerta.setHeaderText("Sus datos han sido guardados correctamente");
            alerta.setContentText("Propietario " + propietario + System.lineSeparator() + "Placa del carro " + placa);
            this.Autos.add(p);
            this.Tabla.setItems(Autos);
            Propietario.setText("");
            Placa.setText("");
        }
        alerta.showAndWait();
    }

    @FXML
    private void BotonModificar(ActionEvent event) {
        Auto p = this.Tabla.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Se debe seleccionar una persona");
            alerta.showAndWait();
        } else {
            String placa = this.Placa.getText();
            String propietario = this.Propietario.getText();
            Auto mod = new Auto(propietario, placa);
            p.setPlaca(mod.getPlaca());
            p.setPropietario(mod.getPropietario());
            this.Tabla.refresh();
        }
    }

    @FXML
    private void BotonEliminar(ActionEvent event) {
        Auto p = this.Tabla.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Se debe seleccionar una persona");
            alerta.showAndWait();
        } else {
            this.Autos.remove(p);
            this.Tabla.refresh();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle(null);
            alerta.setHeaderText("Eliminacion exitosa");
            alerta.setContentText("Se ha eliminado una persona exitosamente");
            alerta.showAndWait();
        }
    }

    @FXML
    private void seleccion(MouseEvent event) {
        Auto p = this.Tabla.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Selecciona una persona");
            alerta.showAndWait();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Autos = FXCollections.observableArrayList();
        this.col_Placa.setCellValueFactory(new PropertyValueFactory("placa"));
        this.col_Propietario.setCellValueFactory(new PropertyValueFactory("propietario"));
        this.Tabla.setItems(this.Autos);

    }
}