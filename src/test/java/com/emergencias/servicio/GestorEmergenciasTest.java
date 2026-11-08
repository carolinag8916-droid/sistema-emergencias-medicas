package com.emergencias.servicio;

import com.emergencias.modelo.Emergencia;
import com.emergencias.modelo.NivelGravedad;

import org.junit.Test;
import static org.junit.Assert.*;

public class GestorEmergenciasTest {

    @Test
    public void debeRegistrarEmergencia() {

        GestorEmergencias gestor = new GestorEmergencias();

        gestor.registrarEmergencia(
                "Accidente de tránsito",
                NivelGravedad.CRITICO,
                3.5);

        assertEquals(1, gestor.cantidadPendientes());
    }

    @Test
    public void debePriorizarEmergenciaCritica() {

        GestorEmergencias gestor = new GestorEmergencias();

        gestor.registrarEmergencia(
                "Emergencia leve",
                NivelGravedad.LEVE,
                2.0);

        gestor.registrarEmergencia(
                "Emergencia crítica",
                NivelGravedad.CRITICO,
                5.0);

        Emergencia primera = gestor.obtenerSiguienteEmergencia();

        assertEquals(
                NivelGravedad.CRITICO,
                primera.getGravedad());
    }

    @Test
    public void debeRetornarEmergenciaALaCola() {

        GestorEmergencias gestor = new GestorEmergencias();

        gestor.registrarEmergencia(
                "Prueba de emergencia",
                NivelGravedad.GRAVE,
                4.0);

        Emergencia emergencia = gestor.obtenerSiguienteEmergencia();

        gestor.reencolarEmergencia(emergencia);

        assertEquals(1, gestor.cantidadPendientes());
    }
}