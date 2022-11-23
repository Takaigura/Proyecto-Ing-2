package com.example.prototipo;

/*
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 * 
 */
import com.example.prototipo.dao.MysqlConnection;
import com.example.prototipo.dao.ParkingDaoImplementation;
import com.example.prototipo.decorator.Electrico;
import com.example.prototipo.decorator.Gasolina;
import com.example.prototipo.memento.CareTaker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.*;

/**
 *
 * La clase HelloController, es una clase que funciona como un controlador de
 * funciones para todos los botones que lleva el programa
 *
 */
public class HelloController implements Initializable {
	@FXML
	private Label welcomeText;
	@FXML
	private Button bton_registrar;
	@FXML
	private Button bton_modificar;
	@FXML
	private Button bton_eliminar;
	@FXML
	private Button bton_recuperar;
	@FXML
	private ToggleGroup combustibles;
	@FXML
	private RadioButton rboton_electrico;
	@FXML
	private RadioButton rboton_gasolina;
	@FXML
	private TextField txtf_propietario;
	@FXML
	private TextField txtf_placa;
	@FXML
	private TextField txtf_indice;
	@FXML
	private TextField txtf_combustible;
	@FXML
	private TableColumn<Auto, Integer> col_IdCar;
	@FXML
	private TableView<Auto> tabla_autos;
	@FXML
	private TableColumn<Auto, String> col_Placa;
	@FXML
	private TableColumn<Auto, String> col_Propietario;
	@FXML
	private TableColumn<Auto, String> col_combustible;
	@FXML
	private ObservableList<Auto> autos_list;

	private Auto reutilizado;
	private final CareTaker historial_list = new CareTaker();
	private final ParkingDaoImplementation ejecutador_db = new ParkingDaoImplementation() ;

	Connection conn = MysqlConnection.ConnectDb();
	/**
	 * BotonRegistrar como su nombre lo dice, es el boton que da paso a que se
	 * puedan registrar la placa y el nombre del dueño de la placa de ese carro,
	 * consta de una Alerta por si algun campo de texto esta vacio, se pueda dar un
	 * aviso, otra alerta para confirmar que los datos se registraron bien y cuenta
	 * con una alerta para mostrar los ingresos
	 * 
	 * @param event
	 */
	@FXML
	private void BotonRegistrar(ActionEvent event) {

		//Primero se verifica si los campos estan vacios:
		if (txtf_propietario.getText().isEmpty() || txtf_placa.getText().isEmpty() || !rboton_gasolina.isSelected() || !rboton_electrico.isSelected()) {
			if (!rboton_electrico.isSelected()) {
				//Alerta de cajas de texto vacias:
				alertas(1, null);
			}
		} else {

			reutilizado = new Auto(txtf_propietario.getText().trim(), txtf_placa.getText().trim());
			AjustarCombustible(reutilizado);
			ejecutador_db.agregarAutosBD(reutilizado);

			// Añade los registros a la observable list desde la BD y esta lista nueva/refrescada a la tabla:
			autos_list = MysqlConnection.getDataCars();
			tabla_autos.setItems(autos_list);
			//Ya habiendo agregado el reutilizado a la BD, se puede por fin inicializar el id_car de reutilizado que antes tenia en 0 o nulo.
			reutilizado.setId_car(autos_list.get(autos_list.size() - 1).getId_car());
			historial_list.addAuto(reutilizado.saveToMemento());

			txtf_placa.setText("");
			txtf_propietario.setText("");
			//Alerta de ingreso completado
			alertas(3, reutilizado);
		}
		System.out.println("Historial de Ingresos: \n" + historial_list.imprimir());
	}



	/**
	 * Este es el boton modificar el cual sirve para modificar un auto que se
	 * seleccione en la tabla, cuenta con un Trycath para detectar si no se ha
	 * seleccionado ningun registro en la tabla, adicional a eso, cuenta con un
	 * historial de modificaciones para saber cuales autos se han modificado
	 *
	 * @param event
	 */
	@FXML
	private void BotonModificar(ActionEvent event) {
		Auto seleccionado = this.tabla_autos.getSelectionModel().getSelectedItem();
		//Se verifica que haya seleccionado un registro por medio de un click y que las cajas de texto no esten vacias:
		if (seleccionado == null) {
			//Alerta de seleccion no realizada:
			alertas(2, null);
		} else if (txtf_propietario.getText().isEmpty() || txtf_placa.getText().isEmpty() || !rboton_gasolina.isSelected()) {
			if (!rboton_electrico.isSelected()) {
				//Alerta de cajas de texto vacias:
				alertas(1, null);
			}
		} else {
			reutilizado = new Auto(txtf_propietario.getText().trim(), txtf_placa.getText().trim());
			AjustarCombustible(reutilizado);
			reutilizado = ejecutador_db.modificarAutoBD(seleccionado, reutilizado);

			// Añade los registros a la observable list desde la BD y esta lista nueva/refrescada a la tabla:
			autos_list = MysqlConnection.getDataCars();
			tabla_autos.setItems(autos_list);
			//Añade el clon a la lista que maneja el historial:
			historial_list.addAuto(reutilizado.saveToMemento());

			txtf_placa.setText("");
			txtf_propietario.setText("");

			System.out.println("Historial de Ingresos: \n" + historial_list.imprimir());
		}
	}

	/**
	 * Este es el boton Eliminar el cual sirve para eliminar los autos creados, al
	 * igual que el boton de modificar, cuenta con un Trycath para detectar si no se
	 * ha seleccionado ningun registro en la tabla, y un aviso de que la
	 * eliminiacion fue exitosa
	 * 
	 * @param event
	 */
	@FXML
	private void BotonEliminar(ActionEvent event) {
		reutilizado = this.tabla_autos.getSelectionModel().getSelectedItem();
		//Se verifica que haya seleccionado un registro por medio de un click:
		if (reutilizado == null) {
			//Alerta de seleccion no realizada:
			alertas(2, null);
		} else {
			ejecutador_db.eliminarAutoBD(reutilizado);
			// Añade los registros actualizados a la observable list desde la BD y esta
			// lista nueva/refrescada a la tabla
			autos_list = MysqlConnection.getDataCars();
			tabla_autos.setItems(autos_list);
			//Alerta de eliminacion completado
			alertas(4, reutilizado);
		}
	}

	@FXML
	private void BotonRecuperar(ActionEvent event){
		try {
			if (txtf_indice.getText().isEmpty()){
				alertas(5, null);
			} else {reutilizado = new Auto();
				reutilizado.restoreStateFromMemento(historial_list.getMemento(Integer.parseInt(txtf_indice.getText().trim())));
				txtf_propietario.setText(reutilizado.getPropietario());
				txtf_placa.setText(reutilizado.getPlaca());

				txtf_indice.setText("");
			}
		}catch (NumberFormatException | IndexOutOfBoundsException e){
				alertas(6,null);
		}

	}
	static void alertas(int num_alerta, Auto auto) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);

		switch (num_alerta){
			case 1:
				alerta.setTitle("Datos Incompletos");
				alerta.setHeaderText("Casillas de texto en blanco");
				alerta.setContentText("Introduzca datos las casillas (Propietario y Placa). \nTambien seleccione un tipo de combustible");
				alerta.showAndWait();
			break;
			case 2:
				alerta.setTitle("Seleccionar Registro");
				alerta.setHeaderText("Ningún registro ha sido seleccionado ");
				alerta.setContentText("Elija un registro haciendole click para realizar esta operacion");
				alerta.showAndWait();
			break;
			case 3:
				alerta.setTitle("Registro Completado");
				alerta.setHeaderText("Sus datos han sido guardados correctamente");
				alerta.setContentText("Propietario: " + auto.getPropietario() + System.lineSeparator()
						+ "Placa del carro: " + auto.getPlaca() + System.lineSeparator() + "Combustible: "
						+ auto.getCombustible());
				alerta.showAndWait();
			break;
			case 4:
				alerta.setTitle("Eliminacion Completada");
				alerta.setHeaderText("El registro ha sido eliminado correctamente");
				alerta.setContentText("Propietario: " + auto.getPropietario()  + System.lineSeparator()
					+ "Placa del carro: " + auto.getPlaca() + System.lineSeparator() + "Combustible: "
						+ auto.getCombustible());
				alerta.showAndWait();
			break;
			case 5:
				alerta.setTitle("Datos Incompletos");
				alerta.setHeaderText("Casilla de historial en blanco");
				alerta.setContentText("Introduzca el numero del indice del registro deseado. Lo puede ver en consola.");
				alerta.showAndWait();
			break;
			case 6:
				alerta.setTitle("Formato Incorrecto");
				alerta.setHeaderText("Casilla de historial mal utilizada");
				alerta.setContentText("Unicamente debe introducir NUMEROS en la casilla del historial."+ System.lineSeparator()
						+ "No MENORES ni MAYORES a la cantidad de datos del historial de consola."+ System.lineSeparator());
				alerta.showAndWait();
		}
	}

	@FXML
	public void SeleccionarCombustible(ActionEvent event) {
		if (rboton_gasolina.isSelected()){
			txtf_combustible.setText("Gasolina");
		} else if (rboton_electrico.isSelected()) {
			txtf_combustible.setText("Electrico");
		}
	}

	public void AjustarCombustible(Auto a){
		if (rboton_gasolina.isSelected()){
			Gasolina g = new Gasolina(a);
			g.type();
		} else if (rboton_electrico.isSelected()) {
			Electrico e = new Electrico(a);
			e.type();
		}
	}
	/**
	 * Este es el inicializador el cual funciona para crear la union entre el
	 * programa y el controlador
	 * 
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		this.col_IdCar.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("Id_car"));
		this.col_Placa.setCellValueFactory(new PropertyValueFactory<Auto, String>("Placa"));
		this.col_Propietario.setCellValueFactory(new PropertyValueFactory<Auto, String>("Propietario"));
		this.col_combustible.setCellValueFactory(new PropertyValueFactory<Auto, String>("Combustible"));

		autos_list = MysqlConnection.getDataCars();
		// Añade los autos que se encuentren en la BD a la lista de historial
		historial_list.addAll(autos_list);
		this.tabla_autos.setItems(autos_list);
		System.out.println("Historial de ingresos: \n" + historial_list.imprimir());

	}
}


/*
 * Esta accion es la que nos permite mantener un control sobre la tabla para
 * poder seleccionar los registros y podemos modificarlos o eliminarlos, cuenta
 * con un Trycatch por si, se intenta seleccionar un campo vacio
 *
 * @param event
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
 }*/