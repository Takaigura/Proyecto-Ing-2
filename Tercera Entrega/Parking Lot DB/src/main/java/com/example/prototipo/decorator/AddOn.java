package com.example.prototipo.decorator;

import com.example.prototipo.Auto;

/**
 * Es el modelador de los objetos y de donde se hereda los atributos a auto
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public abstract class AddOn extends Vehiculo {

	protected Auto a;

	public AddOn(Auto a) {
		this.a = a;
	}

	public abstract void type();
}
