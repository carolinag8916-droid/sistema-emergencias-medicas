package com.emergencias.modelo;

import java.time.LocalDateTime;

public class Emergencia {

    private int id;
    private String descripcion;
    private NivelGravedad gravedad;
    private EstadoEmergencia estado;
    private double distanciaKm;
    private LocalDateTime fechaRegistro;

    public Emergencia(int id, String descripcion,
            NivelGravedad gravedad, double distanciaKm) {
        this.id = id;
        this.descripcion = descripcion;
        this.gravedad = gravedad;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoEmergencia.PENDIENTE;
        this.fechaRegistro = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NivelGravedad getGravedad() {
        return gravedad;
    }

    public EstadoEmergencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmergencia estado) {
        this.estado = estado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return "Emergencia #" + id +
                " | " + gravedad +
                " | " + descripcion +
                " | Estado: " + estado +
                " | Distancia: " + distanciaKm + " km";
    }
}