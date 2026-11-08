package com.emergencias.concurrencia;

import com.emergencias.servicio.GestorEmergencias;
import com.emergencias.servicio.GestorRecursos;

public class MonitorSistema implements Runnable {

    private final GestorEmergencias gestorEmergencias;
    private final GestorRecursos gestorRecursos;

    public MonitorSistema(
            GestorEmergencias gestorEmergencias,
            GestorRecursos gestorRecursos) {

        this.gestorEmergencias = gestorEmergencias;
        this.gestorRecursos = gestorRecursos;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                System.out.println("\n========== MONITOR DEL SISTEMA ==========");

                gestorEmergencias.mostrarEmergencias();

                gestorRecursos.mostrarRecursos();

                gestorEmergencias.getEstadisticas()
                        .mostrarEstadisticas(
                                gestorEmergencias.cantidadPendientes());

                System.out.println("=========================================");

                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}