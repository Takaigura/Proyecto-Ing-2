package com.example.prototipo.memento;

import com.example.prototipo.Auto;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Este es el CareTaker puede rastrear el historial de las modificaciones que se
 * han hecho hacia auto almacenando una pila de mementos.
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class CareTaker {

	ArrayList<Memento> mementoList;

	/**
	 * Crear el historial
	 */
	public CareTaker() {
		mementoList = new ArrayList<>();
	}

	/**
	 * Añadir al historia del memento los autos añadidos
	 * 
	 * @param memento
	 */
	public void addAuto(Memento memento) {
		mementoList.add(memento);
	}

	/**
	 * Añadir al historia de memento los autos removidos
	 * 
	 * @param indice
	 */
	public void removeAuto(int indice) {
		mementoList.remove(indice);
	}

	/**
	 * Imprimir todas las modificaciones que se han hecho
	 * 
	 * @return cadena de texto con las modificaciones
	 */
	public String imprimir() {
		StringBuilder imprimir = new StringBuilder();
		for (Memento au : mementoList) {
			imprimir.append("Indice = ").append(mementoList.indexOf(au)).append(" Auto{").append(" ID = '")
					.append(au.getId_car()).append('\'').append(", Propietario = '").append(au.getPropietario())
					.append('\'').append(" Placa = '").append(au.getPlaca()).append('\'').append(" Combustible = '")
					.append(au.getCombustible()).append("'} \n");
		}
		return imprimir.toString();
	}

	/**
	 * Añadir toda la lista de autos y sus modificaciones y configuraciones u otras
	 * añadidos y tomarlos de la lista de Auto
	 * 
	 * @param autos_list
	 */
	public void addAll(ObservableList<Auto> autos_list) {
		for (Auto au : autos_list) {
			mementoList.add(au.saveToMemento());
		}
	}
	/**
	 * Un get memento nada mas
	 * @param indice
	 * @return la lista de memento
	 */
	public Memento getMemento(int indice) {
		return mementoList.get(indice);

	}
}
