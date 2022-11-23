package com.example.prototipo.decorator;

import com.example.prototipo.Auto;

public abstract class AddOn extends Vehiculo {

    protected Auto a;

    public AddOn(Auto a) {
        this.a = a;
    }

    public abstract void type() ;
}
