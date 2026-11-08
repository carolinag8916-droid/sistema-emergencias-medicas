package com.emergencias;

import com.emergencias.modelo.Ambulancia;
import com.emergencias.modelo.Medico;
import com.emergencias.modelo.NivelGravedad;

import com.emergencias.servicio.GestorEmergencias;
import com.emergencias.servicio.GestorRecursos;

import com.emergencias.concurrencia.Operador;
import com.emergencias.concurrencia.Despachador;
import com.emergencias.concurrencia.MonitorSistema;

public class App {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" SISTEMA DE GESTIÓN DE EMERGENCIAS");
        System.out.println("======================================");

        GestorEmergencias gestorEmergencias = new GestorEmergencias();

        GestorRecursos gestorRecursos = new GestorRecursos();

        // Registrar ambulancias
        gestorRecursos.registrarAmbulancia(new Ambulancia(1));
        gestorRecursos.registrarAmbulancia(new Ambulancia(2));
        gestorRecursos.registrarAmbulancia(new Ambulancia(3));

        // Registrar médicos
        gestorRecursos.registrarMedico(new Medico(1, "Carlos"));
        gestorRecursos.registrarMedico(new Medico(2, "Laura"));
        gestorRecursos.registrarMedico(new Medico(3, "Andrés"));

        // Registrar emergencias iniciales
        gestorEmergencias.registrarEmergencia(
                "Accidente de tránsito",
                NivelGravedad.CRITICO,
                3.5);

        gestorEmergencias.registrarEmergencia(
                "Dificultad respiratoria",
                NivelGravedad.GRAVE,
                5.0);

        // Crear hilos
        Thread operador = new Thread(
                new Operador(gestorEmergencias));

        Thread despachador = new Thread(
                new Despachador(
                        gestorEmergencias,
                        gestorRecursos));

        Thread monitor = new Thread(
                new MonitorSistema(
                        gestorEmergencias,
                        gestorRecursos));

        // Iniciar ejecución concurrente
        operador.start();
        despachador.start();
        monitor.start();
    }
}