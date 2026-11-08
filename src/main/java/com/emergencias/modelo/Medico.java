package com.emergencias.modelo;

public class Medico {

    private int id;
    private String nombre;
    private boolean disponible;

    public Medico(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Médico #" + id +
                " | Nombre: " + nombre +
                " | Disponible: " + disponible;
    }
}