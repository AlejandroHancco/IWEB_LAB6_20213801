package com.example.lab6_20213801.daos;

import com.example.lab6_20213801.beans.Actor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    //Conexión a mySQL
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";

    public ArrayList<Actor> getActoresByIdPelicula(int idPelicula) {
        ArrayList<Actor> listaActores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            //QUERY para ubicar mediante IdPelicula
            String sql = "SELECT a.idActor, a.Nombre, a.Apellido, a.AnoNacimiento, a.premioOscar " +
                    "FROM Actor a " + "INNER JOIN Protagonistas p ON a.idActor = p.idActor " + "WHERE p.idPelicula = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPelicula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setIdActor(rs.getInt("idActor"));
                actor.setNombre(rs.getString("Nombre"));
                actor.setApellido(rs.getString("Apellido"));
                actor.setAnoNacimiento(rs.getInt("AnoNacimiento"));
                actor.setPremioOscar(rs.getBoolean("premioOscar"));
                listaActores.add(actor);
            }
        } catch (SQLException e) {
            //Sirve para analizar la falla
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

        return listaActores;
    }
}
