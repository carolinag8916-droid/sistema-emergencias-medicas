
package com.emergencias.servicio;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class EstadisticasSistema {

    private final AtomicInteger totalRegistradas = new AtomicInteger(0);
    private final AtomicInteger totalEnAtencion = new AtomicInteger(0);
    private final AtomicInteger totalFinalizadas = new AtomicInteger(0);
    private final AtomicInteger totalCanceladas = new AtomicInteger(0);
    private final AtomicLong tiempoTotalAtencion = new AtomicLong(0);

    public void registrarEmergencia() {
        totalRegistradas.incrementAndGet();
    }

    public void iniciarAtencion() {
        totalEnAtencion.incrementAndGet();
    }

    public void registrarFinalizacion(long tiempo) {
        totalEnAtencion.decrementAndGet();
        totalFinalizadas.incrementAndGet();
        tiempoTotalAtencion.addAndGet(tiempo);
    }

    public void registrarInterrupcion() {
        totalEnAtencion.decrementAndGet();
    }

    public void registrarCancelacion() {
        totalCanceladas.incrementAndGet();
    }

    public void mostrarEstadisticas(int pendientes) {

        System.out.println("\n========== ESTADÍSTICAS DEL SISTEMA ==========");

        System.out.println("Emergencias registradas: " + totalRegistradas.get());
        System.out.println("Emergencias en atención: " + totalEnAtencion.get());
        System.out.println("Emergencias finalizadas: " + totalFinalizadas.get());
        System.out.println("Emergencias canceladas: " + totalCanceladas.get());
        System.out.println("Emergencias pendientes: " + pendientes);

        int finalizadas = totalFinalizadas.get();

        if (finalizadas > 0) {

            double promedio = tiempoTotalAtencion.get()
                    / (double) finalizadas;

            System.out.printf(
                    "Tiempo promedio de atención: %.2f segundos%n",
                    promedio / 1000);

        } else {
            System.out.println("Tiempo promedio: Sin datos");
        }

        System.out.println("==============================================");
    }
}
