
package com.emergencias.concurrencia;

import com.emergencias.modelo.Emergencia;
import com.emergencias.modelo.EstadoEmergencia;
import com.emergencias.modelo.AsignacionRecursos;

import com.emergencias.servicio.GestorEmergencias;
import com.emergencias.servicio.GestorRecursos;

public class Despachador implements Runnable {

    private final GestorEmergencias gestorEmergencias;
    private final GestorRecursos gestorRecursos;

    public Despachador(
            GestorEmergencias gestorEmergencias,
            GestorRecursos gestorRecursos) {

        this.gestorEmergencias = gestorEmergencias;
        this.gestorRecursos = gestorRecursos;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                // Espera hasta recibir una emergencia.
                Emergencia emergencia = gestorEmergencias.tomarSiguienteEmergencia();

                AsignacionRecursos asignacion = gestorRecursos.asignarRecursos();

                if (asignacion != null) {

                    emergencia.setEstado(EstadoEmergencia.ASIGNADA);

                    Thread equipo = new Thread(
                            new EquipoEmergencia(
                                    emergencia,
                                    gestorRecursos,
                                    asignacion,
                                    gestorEmergencias.getEstadisticas(),
                                    gestorEmergencias));

                    equipo.start();

                } else {

                    System.out.println(
                            "No hay recursos disponibles. "
                                    + "La emergencia vuelve a la cola.");

                    gestorEmergencias.reencolarEmergencia(emergencia);

                    Thread.sleep(2000);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
    }
}
