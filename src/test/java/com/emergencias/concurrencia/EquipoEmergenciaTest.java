package com.emergencias.concurrencia;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.Emergencia;
import com.emergencias.modelo.NivelGravedad;
import com.emergencias.modelo.EstadoEmergencia;
import com.emergencias.modelo.EstadoAmbulancia;
import com.emergencias.modelo.AsignacionRecursos;

import com.emergencias.servicio.GestorEmergencias;
import com.emergencias.servicio.GestorRecursos;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipoEmergenciaTest {

    @Test
    public void debeLiberarRecursosYReencolarEmergencia()
            throws InterruptedException {

        GestorEmergencias gestorEmergencias = new GestorEmergencias();

        GestorRecursos gestorRecursos = new GestorRecursos();

        Ambulancia ambulancia = new Ambulancia(1);
        Medico medico = new Medico(1, "Carlos");

        gestorRecursos.registrarAmbulancia(ambulancia);
        gestorRecursos.registrarMedico(medico);

        gestorEmergencias.registrarEmergencia(
                "Accidente de tránsito",
                NivelGravedad.CRITICO,
                3.0);

        Emergencia emergencia = gestorEmergencias.obtenerSiguienteEmergencia();

        AsignacionRecursos asignacion = gestorRecursos.asignarRecursos();

        assertNotNull(asignacion);

        EquipoEmergencia equipo = new EquipoEmergencia(
                emergencia,
                gestorRecursos,
                asignacion,
                gestorEmergencias.getEstadisticas(),
                gestorEmergencias);

        Thread hilo = new Thread(equipo);

        hilo.start();

        Thread.sleep(300);

        hilo.interrupt();

        hilo.join(3000);

        assertFalse(
                "El hilo debería haber terminado",
                hilo.isAlive());

        assertEquals(
                EstadoEmergencia.PENDIENTE,
                emergencia.getEstado());

        assertEquals(
                EstadoAmbulancia.DISPONIBLE,
                ambulancia.getEstado());

        assertTrue(medico.isDisponible());

        assertEquals(
                1,
                gestorEmergencias.cantidadPendientes());
    }
}