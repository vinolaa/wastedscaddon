package com.vinola.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.vinola.WastedSCA;

public class Database {

	private final static WastedSCA main = WastedSCA.getPlugin(WastedSCA.class);
    
    private static Connection conn = null;

	public static Connection conectar() throws SQLException, IOException {

		if (conn == null) {
			String url = main.getConfig().getString("MySql.url");
			String user = main.getConfig().getString("MySql.username");
			String pw = main.getConfig().getString("MySql.password");
			conn = DriverManager.getConnection(url, user, pw);
		}

		return conn;
	}

	public static void desconectar() throws SQLException {

		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
}
