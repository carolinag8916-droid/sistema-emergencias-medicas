package com.emergencias.servicio;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.EstadoAmbulancia;
import com.emergencias.modelo.AsignacionRecursos;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {

    private final List<Ambulancia> ambulancias;
    private final List<Medico> medicos;

    public GestorRecursos() {
        ambulancias = new ArrayList<>();
        medicos = new ArrayList<>();
    }

    public synchronized void registrarAmbulancia(Ambulancia ambulancia) {
        ambulancias.add(ambulancia);
    }

    public synchronized void registrarMedico(Medico medico) {
        medicos.add(medico);
    }

    public synchronized AsignacionRecursos asignarRecursos() {

        for (Ambulancia ambulancia : ambulancias) {

            if (ambulancia.getEstado() == EstadoAmbulancia.DISPONIBLE) {

                for (Medico medico : medicos) {

                    if (medico.isDisponible()) {

                        ambulancia.setEstado(EstadoAmbulancia.EN_CAMINO);
                        medico.setDisponible(false);

                        System.out.println("Recursos asignados:");
                        System.out.println(ambulancia);
                        System.out.println(medico);

                        return new AsignacionRecursos(
                                ambulancia,
                                medico);
                    }
                }
            }
        }

        return null;
    }

    public synchronized void liberarRecursos(
            AsignacionRecursos asignacion) {

        if (asignacion == null) {
            return;
        }

        asignacion.getAmbulancia().setEstado(
                EstadoAmbulancia.DISPONIBLE);

        asignacion.getMedico().setDisponible(true);

        System.out.println("Recursos liberados correctamente:");
        System.out.println(asignacion.getAmbulancia());
        System.out.println(asignacion.getMedico());
    }

    public synchronized void mostrarRecursos() {

        System.out.println("\n===== ESTADO DE RECURSOS =====");

        for (Ambulancia ambulancia : ambulancias) {
            System.out.println(ambulancia);
        }

        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }
}