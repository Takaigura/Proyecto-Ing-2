package co.edu.poli.parking.parkinglot;

public class Auto {
    private String placa;
    private String propietario;

    public String getPropietario() {
        return propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public Auto(String placa, String propietario) {
        this.placa = placa;
        this.propietario = propietario;
    }
}
