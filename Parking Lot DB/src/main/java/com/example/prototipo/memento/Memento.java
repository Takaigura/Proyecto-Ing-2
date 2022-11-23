package com.example.prototipo.memento;

public class Memento {
    private int id_car;
    private String placa;
    private String propietario;
    private String combustible;

    public Memento(int id_car, String propietario, String placa, String combustible) {
        this.id_car = id_car;
        this.propietario = propietario;
        this.placa = placa;
        this.combustible = combustible;
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

    public String getCombustible() {
        return combustible;
    }
}
