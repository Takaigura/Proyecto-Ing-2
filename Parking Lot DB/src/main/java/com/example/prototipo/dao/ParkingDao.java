package com.example.prototipo.dao;

import javafx.collections.ObservableList;

import java.sql.Connection;

import com.example.prototipo.memento.Auto;

public interface ParkingDao {
    public void agregarAutosBD(Auto nuevo_auto);
    public Auto modificarAutoBD(Auto antiguo_auto, Auto nuevo_auto);
    public void eliminarAutoBD(Auto nuevo_auto);
    //public ObservableList getDataCars();
}
