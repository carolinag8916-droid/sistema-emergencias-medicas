
package com.emergencias.concurrencia;

import com.emergencias.modelo.Emergencia;
import com.emergencias.modelo.EstadoEmergencia;
import com.emergencias.modelo.EstadoAmbulancia;
import com.emergencias.modelo.AsignacionRecursos;

import com.emergencias.servicio.GestorRecursos;
import com.emergencias.servicio.GestorEmergencias;
import com.emergencias.servicio.EstadisticasSistema;

public class EquipoEmergencia implements Runnable {

    private final Emergencia emergencia;
    private final GestorRecursos gestorRecursos;
    private final GestorEmergencias gestorEmergencias;
    private final AsignacionRecursos asignacion;
    private final EstadisticasSistema estadisticas;

    public EquipoEmergencia(
            Emergencia emergencia,
            GestorRecursos gestorRecursos,
            AsignacionRecursos asignacion,
            EstadisticasSistema estadisticas,
            GestorEmergencias gestorEmergencias) {

        this.emergencia = emergencia;
        this.gestorRecursos = gestorRecursos;
        this.asignacion = asignacion;
        this.estadisticas = estadisticas;
        this.gestorEmergencias = gestorEmergencias;
    }

    @Override
    public void run() {

        long inicio = System.currentTimeMillis();
        boolean reintentar = false;

        estadisticas.iniciarAtencion();

        try {

            asignacion.getAmbulancia().setEstado(
                    EstadoAmbulancia.OCUPADA);

            emergencia.setEstado(EstadoEmergencia.EN_ATENCION);

            System.out.println(
                    "Equipo atendiendo emergencia #" + emergencia.getId());

            Thread.sleep(8000);

            emergencia.setEstado(EstadoEmergencia.FINALIZADA);

            long tiempo = System.currentTimeMillis() - inicio;

            estadisticas.registrarFinalizacion(tiempo);

            System.out.println(
                    "Emergencia #" + emergencia.getId() + " finalizada.");

        } catch (InterruptedException e) {

            emergencia.setEstado(EstadoEmergencia.PENDIENTE);

            estadisticas.registrarInterrupcion();

            reintentar = true;

            System.out.println(
                    "Atención interrumpida. Emergencia #"
                            + emergencia.getId() + " será reintentada.");

            Thread.currentThread().interrupt();

        } finally {

            gestorRecursos.liberarRecursos(asignacion);

            if (reintentar) {
                gestorEmergencias.reencolarEmergencia(emergencia);
            }
        }
    }
}
