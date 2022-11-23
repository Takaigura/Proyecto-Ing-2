package com.example.prototipo.dao;

import com.example.prototipo.Auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParkingDaoImplementation implements ParkingDao{
    Connection conn = MysqlConnection.ConnectDb();
    /**
     *
     * Esta es la coneccion con la base de datos Mysql para poder agregar los Autos
     * a la base de datos
     *
     */
    @Override
    public void agregarAutosBD(Auto nuevo_auto ) {
        PreparedStatement query = null;
        String query_insert = "insert into cars (propietario, placa, combustible) values (?,?,?)";

        try {
            query = conn.prepareStatement(query_insert);
            query.setString(1, nuevo_auto.getPropietario());
            query.setString(2, nuevo_auto.getPlaca());
            query.setString(3, nuevo_auto.getCombustible());
            query.execute();

        } catch (SQLException e) {
        }
    }


    @Override
    public Auto modificarAutoBD(Auto antiguo_auto, Auto nuevo_auto) { //Devuelve un objeto auto, con las modificaciones hechas
        PreparedStatement query = null;
        String query_insert = "update cars set propietario = ?, placa = ?, combustible = ? where car_id = ?;";

        try {
            query = conn.prepareStatement(query_insert);
            query.setString(1, nuevo_auto.getPropietario());
            query.setString(2, nuevo_auto.getPlaca());
            query.setString(3, nuevo_auto.getCombustible());
            query.setInt(4, antiguo_auto.getId_car());
            query.execute();

        } catch (SQLException e) {
        }
        return new Auto(antiguo_auto.getId_car(), nuevo_auto.getPropietario(), nuevo_auto.getPlaca(), nuevo_auto.getCombustible());
    }

    /**
     * Esta accion es para eliminar los Autos de la base de datos con el botn
     * eliminar
     *
     */
    @Override
    public void eliminarAutoBD(Auto eliminado) {
        PreparedStatement query = null;
        String query_delete = "delete from cars where car_id = ?";

        try {
            query = conn.prepareStatement(query_delete);
            query.setInt(1, eliminado.getId_car());
            query.execute();
        } catch (SQLException e) {
        }

    }

}
