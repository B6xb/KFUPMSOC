package JDBC;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/kfupm";
		String uname = "root";
		String password = "09Labas09--";
		String query = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement stat = con.createStatement();

			ResultSet result = stat.executeQuery(query);

			while (result.next()) {
				String n = result.getString("match_no");
				System.out.println("Match" + n);
				String x = result.getString("play_stage");
				System.out.println(x);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
