package com.emergencias.servicio;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.EstadoAmbulancia;
import com.emergencias.modelo.AsignacionRecursos;

import org.junit.Test;
import static org.junit.Assert.*;

public class GestorRecursosTest {

    @Test
    public void debeAsignarRecursosDisponibles() {

        GestorRecursos gestor = new GestorRecursos();

        Ambulancia ambulancia = new Ambulancia(1);
        Medico medico = new Medico(1, "Carlos");

        gestor.registrarAmbulancia(ambulancia);
        gestor.registrarMedico(medico);

        AsignacionRecursos asignacion = gestor.asignarRecursos();

        assertNotNull(asignacion);

        assertEquals(
                EstadoAmbulancia.EN_CAMINO,
                ambulancia.getEstado());

        assertFalse(medico.isDisponible());
    }

    @Test
    public void noDebeAsignarUnRecursoDosVeces() {

        GestorRecursos gestor = new GestorRecursos();

        gestor.registrarAmbulancia(new Ambulancia(1));
        gestor.registrarMedico(new Medico(1, "Laura"));

        AsignacionRecursos primera = gestor.asignarRecursos();

        AsignacionRecursos segunda = gestor.asignarRecursos();

        assertNotNull(primera);
        assertNull(segunda);
    }

    @Test
    public void debeLiberarLosRecursosAsignados() {

        GestorRecursos gestor = new GestorRecursos();

        Ambulancia ambulancia = new Ambulancia(1);
        Medico medico = new Medico(1, "Andrés");

        gestor.registrarAmbulancia(ambulancia);
        gestor.registrarMedico(medico);

        AsignacionRecursos asignacion = gestor.asignarRecursos();

        gestor.liberarRecursos(asignacion);

        assertEquals(
                EstadoAmbulancia.DISPONIBLE,
                ambulancia.getEstado());

        assertTrue(medico.isDisponible());
    }
}