package com.example.prototipo.decorator;

import com.example.prototipo.Auto;
import com.example.prototipo.decorator.AddOn;

public class Gasolina extends AddOn {

    public Gasolina(Auto a) {
        super(a);
    }

    @Override
    public void type() {
        a.combustible = "Gasolina" ;
    }
}
