package com.emergencias.servicio;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.EstadoAmbulancia;
import com.emergencias.modelo.AsignacionRecursos;

import org.junit.Test;
import static org.junit.Assert.*;

public class GestorRecursosDisponibilidadTest {

    @Test
    public void noDebeAsignarSiNoHayAmbulancias() {

        GestorRecursos gestor = new GestorRecursos();

        gestor.registrarMedico(new Medico(1, "Carlos"));

        AsignacionRecursos asignacion = gestor.asignarRecursos();

        assertNull(asignacion);
    }

    @Test
    public void noDebeAsignarSiNoHayMedicos() {

        GestorRecursos gestor = new GestorRecursos();

        Ambulancia ambulancia = new Ambulancia(1);
        gestor.registrarAmbulancia(ambulancia);

        AsignacionRecursos asignacion = gestor.asignarRecursos();

        assertNull(asignacion);

        assertEquals(
                EstadoAmbulancia.DISPONIBLE,
                ambulancia.getEstado());
    }

    @Test
    public void debePermitirReasignarRecursosLiberados() {

        GestorRecursos gestor = new GestorRecursos();

        gestor.registrarAmbulancia(new Ambulancia(1));
        gestor.registrarMedico(new Medico(1, "Laura"));

        AsignacionRecursos primera = gestor.asignarRecursos();

        assertNotNull(primera);

        gestor.liberarRecursos(primera);

        AsignacionRecursos segunda = gestor.asignarRecursos();

        assertNotNull(segunda);
    }
}