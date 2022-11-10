package com.example.prototipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MysqlConnection {

    public static Connection ConnectDb(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "dankdb");
        }catch (SQLException e){ return null;}

    }
    public static ObservableList<Auto> getDataCars()  {
        ObservableList<Auto> list = FXCollections.observableArrayList();
        try {
            Connection con = ConnectDb();
            PreparedStatement ps = con.prepareStatement("select * from cars");
            ResultSet respuesta = ps.executeQuery();
            while (respuesta.next()) {
                list.add(
                        new Auto(respuesta.getInt(1),
                                respuesta.getString(2),
                                respuesta.getString(3)));
            }
        }catch (Exception e){
        }
        return list;
    }
}
