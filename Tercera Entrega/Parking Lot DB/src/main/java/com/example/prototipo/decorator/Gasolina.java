package com.example.prototipo.decorator;

import com.example.prototipo.Auto;
import com.example.prototipo.decorator.AddOn;
/**
 * Atributo Gasolina
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class Gasolina extends AddOn {

    public Gasolina(Auto a) {
        super(a);
    }

    @Override
    public void type() {
        a.combustible = "Gasolina" ;
    }
}
