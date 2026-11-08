
package com.emergencias.servicio;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.AsignacionRecursos;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.util.List;

public class GestorRecursosConcurrenciaTest {

    @Test
    public void noDebeAsignarLaMismaAmbulanciaADosHilos()
            throws InterruptedException {

        GestorRecursos gestor = new GestorRecursos();

        gestor.registrarAmbulancia(new Ambulancia(1));
        gestor.registrarMedico(new Medico(1, "Carlos"));

        int cantidadHilos = 20;

        CountDownLatch inicio = new CountDownLatch(1);
        CountDownLatch fin = new CountDownLatch(cantidadHilos);

        AtomicInteger asignacionesExitosas = new AtomicInteger(0);
        AtomicReference<AsignacionRecursos> primeraAsignacion = new AtomicReference<>();

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < cantidadHilos; i++) {

            Thread hilo = new Thread(() -> {

                try {
                    inicio.await();

                    AsignacionRecursos asignacion = gestor.asignarRecursos();

                    if (asignacion != null) {
                        asignacionesExitosas.incrementAndGet();
                        primeraAsignacion.compareAndSet(null, asignacion);
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                } finally {
                    fin.countDown();
                }
            });

            hilos.add(hilo);
            hilo.start();
        }

        inicio.countDown();
        fin.await();

        assertEquals(1, asignacionesExitosas.get());

        assertNotNull(primeraAsignacion.get());
    }
}
