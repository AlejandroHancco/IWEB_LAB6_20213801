package com.example.lab6_20213801.beans;

public class Actor {
    private int idActor;
    private String Nombre;
    private String Apellido;
    private int AnoNacimiento;
    private boolean premioOscar;

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getAnoNacimiento() {
        return AnoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        AnoNacimiento = anoNacimiento;
    }

    public boolean getPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }
}
