package com.emergencias.modelo;

public class Ambulancia {

    private int id;
    private EstadoAmbulancia estado;

    public Ambulancia(int id) {
        this.id = id;
        this.estado = EstadoAmbulancia.DISPONIBLE;
    }

    public int getId() {
        return id;
    }

    public EstadoAmbulancia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAmbulancia estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ambulancia #" + id + " | Estado: " + estado;
    }
}