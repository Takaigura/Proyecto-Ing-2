package com.example.prototipo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private Label welcomeText;
    @FXML
    private Button bton_registrar;
    @FXML
    private Button bton_modificar;
    @FXML
    private Button bton_eliminar;
    @FXML
    private TextField txtf_propietario;
    @FXML
    private TextField txtf_placa;
    @FXML
    private TableColumn<Auto, Integer> col_IdCar;
    @FXML
    private TableView<Auto> tabla_autos;
    @FXML
    private TableColumn<Auto, String> col_Placa;
    @FXML
    private TableColumn<Auto, String> col_Propietario;
    @FXML
    private ObservableList<Auto> autos_list;
    private List<Auto> historial_list = new ArrayList<>();


    @FXML
    private void BotonRegistrar(ActionEvent event) {

        //Imprimir historial con el nuevo auto (No se ha presionado AGREGAR).


        Alert alerta;
        if (txtf_propietario.getText().isEmpty() || txtf_placa.getText().isEmpty()) {
            alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos Faltantes");
            alerta.setHeaderText("Faltan datos por introducir");
            alerta.setContentText("Introduzca todos los datos por favor");
        } else {
            agregarAutosBD();

            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos correctos");
            alerta.setHeaderText("Sus datos han sido guardados correctamente");
            alerta.setContentText("Propietario " + txtf_propietario.getText() + System.lineSeparator() + "Placa del carro " + txtf_placa.getText());
        }
        alerta.showAndWait();
        System.out.println("Historial de ingresos: "+ historial_list.toString());
    }

    private void agregarAutosBD(){
        Connection conn = MysqlConnection.ConnectDb();
        PreparedStatement query = null;
        String query_insert = "insert into cars (propietario, placa) values (?,?)";

        try {
            query = conn.prepareStatement(query_insert);
            query.setString(1, txtf_propietario.getText());
            query.setString(2, txtf_placa.getText());
            query.execute();
            //Añade los registros a la observable list desde la BD y esta lista nueva/refrescada a la tabla
            autos_list = MysqlConnection.getDataCars();
            tabla_autos.setItems(autos_list);

            historial_list.add(autos_list.get(autos_list.size()-1).cloneAuto());

            txtf_placa.setText("");
            txtf_propietario.setText("");

        }catch (SQLException e){
        }
    }
    @FXML
    private void BotonModificar(ActionEvent event) {
        Auto p = this.tabla_autos.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Se debe seleccionar un registro");
            alerta.showAndWait();
        } else {
            String propietario = this.txtf_propietario.getText();
            String placa = this.txtf_placa.getText();

            Auto mod = new Auto(propietario, placa);

            p.setPropietario(mod.getPropietario());
            p.setPlaca(mod.getPlaca());
            historial_list.add(p.cloneAuto());
            System.out.println("Historial de ingresos: "+ historial_list.toString());
            this.tabla_autos.refresh();
        }
    }

    @FXML
    private void BotonEliminar(ActionEvent event) {
        Auto p = this.tabla_autos.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Se debe seleccionar una persona");
            alerta.showAndWait();
        } else {
            eliminarAutosBD(p.getId());

            this.autos_list.remove(p);
            this.tabla_autos.refresh();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle(null);
            alerta.setHeaderText("Eliminacion exitosa");
            alerta.setContentText("Se ha eliminado una persona exitosamente");
            alerta.showAndWait();
        }
        System.out.println("Historial de ingresos: "+ historial_list.toString());

    }

    private void eliminarAutosBD(int id) {
        Connection conn = MysqlConnection.ConnectDb();
        PreparedStatement query = null;
        String query_delete = "delete from cars where car_id = ?";

        try {
            query = conn.prepareStatement(query_delete);
            query.setInt(1, id);
            query.execute();
            //Añade los registros actualizados a la observable list desde la BD y esta lista nueva/refrescada a la tabla
            autos_list = MysqlConnection.getDataCars();
            tabla_autos.setItems(autos_list);
        }catch (SQLException e){
            System.out.println("Error al ejecutar el delete");
        }
    }

    @FXML
    private void seleccion(MouseEvent event) {
        Auto p = this.tabla_autos.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText("Se ha detectado un fallo");
            alerta.setContentText("Selecciona una persona");
            alerta.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.col_IdCar.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("Id"));
        this.col_Placa.setCellValueFactory(new PropertyValueFactory<Auto,String>("Placa"));
        this.col_Propietario.setCellValueFactory(new PropertyValueFactory<Auto,String>("Propietario"));

        autos_list = MysqlConnection.getDataCars();
        //Añade los autos que se encuentren en la BD a la lista de historial
        historial_list.addAll(autos_list);
        this.tabla_autos.setItems(autos_list);
        System.out.println("Historial de ingresos: "+ historial_list.toString());

    }
}