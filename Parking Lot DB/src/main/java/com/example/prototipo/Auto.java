package com.example.prototipo;

/**
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class Auto implements Item {
	private int id;
	private String placa;
	private String propietario;

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

	public Auto(int id, String propietario, String placa) {
		this.id = id;
		this.placa = placa;
		this.propietario = propietario;
	}

	/**
	 * El metodo get se encarga de mostrar un valor a una propiedad o atributo del
	 * objeto ID
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * El metodo set se encarga de darle un valor al objeto ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * Se utiliza para convertir a String
	 */
	@Override
	public String toString() {
		return "Auto{" + " ID = '" + id + '\'' + ", propietario = '" + propietario + '\'' + " placa = '" + placa + '\''
				+ "} \n";
	}

	/**
	 * Este metodo funciona para clonar el auto y darle los atributos de la
	 * clonacion, ID, PROPIETARIO, PLACA
	 */
	@Override
	public Auto cloneAuto() {
		return new Auto(this.id, this.propietario, this.placa);
	}
}