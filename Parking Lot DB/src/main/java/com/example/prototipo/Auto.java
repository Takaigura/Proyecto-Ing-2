package com.example.prototipo;

import com.example.prototipo.memento.Memento;

/**
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class Auto {
	private int id_car;
	private String placa;
	private String propietario;

	private String tipo;
	private double deuda;
	private double hora_entrada;
	private double hora_salida;

	/**
	 * 
	 * Es la clase que modela el objeto con el que interactua la interfaz gráfica y
	 * la base de datos, para este caso hace referencia a las variables que maneja
	 * un parqueadero cuando recibe diferentes usuarios que posean un carro.
	 * 
	 * @param propietario
	 * @param placa
	 */

	public Auto(String propietario, String placa) {
		this.placa = placa;
		this.propietario = propietario;
	}

	public Auto(int id_car, String propietario, String placa) {
		this.id_car = id_car;
		this.placa = placa;
		this.propietario = propietario;
	}

	public Auto() {

	}

	/**
	 * El metodo get se encarga de mostrar un valor a una propiedad o atributo del
	 * objeto ID
	 * 
	 * @return
	 */
	public int getId_car() {
		return id_car;
	}

	/**
	 * El metodo set se encarga de darle un valor al objeto ID
	 * 
	 * @param id_car
	 */
	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	/**
	 * El metodo get se encarga de mostrar un valor a una propiedad o atributo del
	 * objeto Propietario
	 * 
	 * @return
	 */
	public String getPropietario() {
		return this.propietario;
	}

	/**
	 * El metodo get se encarga de mostrar un valor a una propiedad o atributo del
	 * objeto Placa
	 * 
	 * @return
	 */
	public String getPlaca() {
		return this.placa;
	}

	/**
	 * El metodo set se encarga de darle un valor al objeto Placa
	 * 
	 * @param placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * El metodo set se encarga de darle un valor al objeto Propietario
	 * 
	 * @param propietario
	 */
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public Memento saveToMemento() {
		return new Memento(id_car, propietario, placa);
	}

	/**
	 * @param memento
	 */
	public void restoreStateFromMemento(Memento memento) {
		this.propietario = memento.getPropietario();
		this.placa = memento.getPlaca();
	}
}