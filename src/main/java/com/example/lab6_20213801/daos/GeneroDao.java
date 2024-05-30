package com.example.lab6_20213801.daos;

import com.example.lab6_20213801.beans.Genero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneroDao {
    //Conexión a mySQL
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String username = "root";
    private String password = "root";

    public Genero getGeneroById(int idGenero) {
        Genero genero = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            //QUERY para ubicar mediante genero
            String sql = "SELECT * FROM Genero WHERE idGenero = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGenero);
            rs = stmt.executeQuery();

            if (rs.next()) {
                genero = new Genero(rs.getInt("idGenero"), rs.getString("nombre"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return genero;
    }
}
