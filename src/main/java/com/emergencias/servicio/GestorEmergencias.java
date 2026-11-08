
package com.emergencias.servicio;

import com.emergencias.modelo.Emergencia;
import com.emergencias.modelo.NivelGravedad;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GestorEmergencias {

    private final PriorityBlockingQueue<Emergencia> emergencias;
    private final AtomicInteger contador;
    private final EstadisticasSistema estadisticas;

    public GestorEmergencias() {

        emergencias = new PriorityBlockingQueue<>(
                20,
                Comparator
                        .comparingInt((Emergencia e) -> obtenerPrioridad(e.getGravedad()))
                        .thenComparing(Emergencia::getFechaRegistro)
                        .thenComparingDouble(Emergencia::getDistanciaKm));

        contador = new AtomicInteger(1);
        estadisticas = new EstadisticasSistema();
    }

    private int obtenerPrioridad(NivelGravedad gravedad) {

        switch (gravedad) {
            case CRITICO:
                return 1;
            case GRAVE:
                return 2;
            case MODERADO:
                return 3;
            case LEVE:
                return 4;
            default:
                return 5;
        }
    }

    public void registrarEmergencia(
            String descripcion,
            NivelGravedad gravedad,
            double distanciaKm) {

        int id = contador.getAndIncrement();

        Emergencia emergencia = new Emergencia(
                id,
                descripcion,
                gravedad,
                distanciaKm);

        emergencias.offer(emergencia);
        estadisticas.registrarEmergencia();

        System.out.println("Emergencia registrada: " + emergencia);
    }

    // Obtiene una emergencia inmediatamente.
    public Emergencia obtenerSiguienteEmergencia() {
        return emergencias.poll();
    }

    // Espera hasta que exista una emergencia disponible.
    public Emergencia tomarSiguienteEmergencia()
            throws InterruptedException {
        return emergencias.take();
    }

    // Devuelve una emergencia a la cola.
    public void reencolarEmergencia(Emergencia emergencia) {
        emergencias.offer(emergencia);
    }

    public int cantidadPendientes() {
        return emergencias.size();
    }

    public EstadisticasSistema getEstadisticas() {
        return estadisticas;
    }

    public void mostrarEmergencias() {

        System.out.println("\n===== EMERGENCIAS PENDIENTES =====");

        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias pendientes.");
            return;
        }

        emergencias.stream()
                .sorted(emergencias.comparator())
                .forEach(System.out::println);
    }
}
