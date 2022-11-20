package com.example.prototipo.memento;

public class Memento {
    private int id_car;
    private String placa;
    private String propietario;

    public Memento(int id_car, String propietario, String placa) {
        this.id_car = id_car;
        this.propietario = propietario;
        this.placa = placa;
    }

    public int getId_car() {
        return id_car;
    }

    public String getPlaca() {
        return placa;
    }

    public String getPropietario() {
        return propietario;
    }
}
