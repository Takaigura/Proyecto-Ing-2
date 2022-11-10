package com.example.prototipo;

public class Auto implements Item {
    private int id;
    private String placa;
    private String propietario;

    public Auto(String propietario, String placa) {
        this.placa = placa;
        this.propietario = propietario;
    }
    public Auto(int id, String propietario, String placa) {
        this.id = id;
        this.placa = placa;
        this.propietario = propietario;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropietario() {
        return this.propietario;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Auto{" +" ID = '" + id + '\'' +
                ", propietario = '" + propietario + '\''+
                " placa = '" + placa + '\''+ "} \n";
    }

    @Override
    public Auto cloneAuto() {
        return new Auto(this.id,this.propietario,this.placa);
    }
}