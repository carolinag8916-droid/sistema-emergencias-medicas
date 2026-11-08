package com.emergencias.modelo;

public class AsignacionRecursos {

    private final Ambulancia ambulancia;
    private final Medico medico;

    public AsignacionRecursos(Ambulancia ambulancia, Medico medico) {
        this.ambulancia = ambulancia;
        this.medico = medico;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public Medico getMedico() {
        return medico;
    }
}