package com.example.prototipo.memento;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CareTaker {

    ArrayList<Memento> mementoList;

    public CareTaker() {
        mementoList =new ArrayList<>();
    }

    public void addAuto(Memento memento){
        mementoList.add(memento);
    }

    public void removeAuto(int indice){
        mementoList.remove(indice);
    }

    public String imprimir(){
        StringBuilder imprimir = new StringBuilder();
        for (Memento au :
                mementoList) {
            imprimir.append("Indice = ").append(mementoList.indexOf(au)).append(" Auto{").append(" ID = '")
                    .append(au.getId_car()).append('\'').append(", Propietario = '").append(au.getPropietario())
                    .append('\'').append(" Placa = '").append(au.getPlaca()).append('\'').append("} \n");
        }
        return imprimir.toString();
    }

    public void addAll(ObservableList<Auto> autos_list) {
        for (Auto au :
                autos_list) {
            mementoList.add(au.saveToMemento());
        }
    }

    public Memento getMemento(int indice){
        return mementoList.get(indice);

    }
}
