package com.example.lab6_20213801.daos;

import com.example.lab6_20213801.beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao {
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public ArrayList<Pelicula> list(int limit, int offset) {
        ArrayList<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pelicula LIMIT ? OFFSET ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(1));
                    pelicula.setTitulo(rs.getString(2));
                    pelicula.setDirector(rs.getString(3));
                    pelicula.setAnoPublicacion(rs.getString(4));
                    pelicula.setRating(rs.getString(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    pelicula.setIdGenero(rs.getInt(7));
                    lista.add(pelicula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar peliculas", e);
        }
        return lista;
    }
    public Pelicula getPeliculaById(int idPelicula) {
        Pelicula pelicula = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM Pelicula WHERE idPelicula = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPelicula);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getString("anoPublicacion"));
                pelicula.setRating(rs.getString("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));
                // Añade más atributos según tu modelo de Pelicula
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

        return pelicula;
    }


    public ArrayList<Pelicula> searchByTitulo(String titulo, int limit, int offset) {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT * FROM Pelicula WHERE LOWER(titulo) LIKE ? LIMIT ? OFFSET ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + titulo.toLowerCase() + "%");
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(1));
                    pelicula.setTitulo(rs.getString(2));
                    pelicula.setDirector(rs.getString(3));
                    pelicula.setAnoPublicacion(rs.getString(4));
                    pelicula.setRating(rs.getString(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    pelicula.setIdGenero(rs.getInt(7));
                    listaPeliculas.add(pelicula);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar peliculas por título", e);
        }
        return listaPeliculas;
    }
}
