package com.example.prototipo.decorator;

import com.example.prototipo.Auto;
import com.example.prototipo.decorator.AddOn;
/**
 * Atributo electrico
 * @author  Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class Electrico extends AddOn {
    public Electrico(Auto a) {
        super(a);
    }

    @Override
    public void type() {
        a.combustible = "Electrico";
    }
}
