package com.example.lab6_20213801.beans;
import java.text.NumberFormat;
import java.util.Locale;


public class Pelicula {
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(String anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public String getRating() {
        return rating+"/10";
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    private int idPelicula;
    private String titulo;
    private String director;
    private String anoPublicacion;
    private String  rating;
    private double boxOffice;
    private int idGenero;
    private Genero genero;

    public String getBoxOfficeDolar() {
        double boxOfficeValue = boxOffice;
        //Sirve para convertir a dolares
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);
        return currencyFormat.format(boxOfficeValue);
    }
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
