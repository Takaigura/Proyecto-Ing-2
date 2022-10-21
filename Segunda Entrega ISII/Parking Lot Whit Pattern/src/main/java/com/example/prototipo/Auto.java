package com.example.prototipo;

public class Auto implements Item {
    private String placa;
    private String propietario;

    public String getPropietario() {
        return this.propietario;
    }

    public String getPlaca() {
        return this.placa;
    }

    public Auto(String placa, String propietario) {
        this.placa = placa;
        this.propietario = propietario;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Auto{" +" placa = '" + placa + '\'' +
                ", propietario = '" + propietario + '\'' + '}'+"\n";
    }

    @Override
    public Auto cloneAuto() {
        return new Auto(this.placa,this.propietario);
    }
}