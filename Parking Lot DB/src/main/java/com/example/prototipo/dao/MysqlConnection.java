package com.example.prototipo.dao;

/**
 * Esta es toda la coneccion a la base de datos Mysql
 */
import com.example.prototipo.Auto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MysqlConnection {
	private static Connection conn = null;
	static{
		String url = "jdbc:mysql://localhost:3306/parking";
		String user = "root";
		String password = "dankdb";
		try {
			Class.forName("com.example.prototipo.dao.MysqlConnection");
			conn = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection ConnectDb() {
		return conn;
	}

	public static ObservableList<Auto> getDataCars() {
		ObservableList<Auto> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from cars");
			ResultSet respuesta = ps.executeQuery();
			while (respuesta.next()) {
				list.add(new Auto(respuesta.getInt(1), respuesta.getString(2), respuesta.getString(3)));
			}
		} catch (Exception e) {
		}
		return list;
	}
}
