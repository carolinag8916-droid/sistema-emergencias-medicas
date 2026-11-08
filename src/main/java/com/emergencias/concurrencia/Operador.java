package com.emergencias.concurrencia;

import com.emergencias.modelo.NivelGravedad;
import com.emergencias.servicio.GestorEmergencias;

import java.util.Random;

public class Operador implements Runnable {

    private final GestorEmergencias gestor;
    private final Random random = new Random();

    public Operador(GestorEmergencias gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {

        String[] descripciones = {
                "Accidente de tránsito",
                "Dolor torácico",
                "Dificultad respiratoria",
                "Caída de altura",
                "Emergencia médica general"
        };

        NivelGravedad[] niveles = NivelGravedad.values();

        while (!Thread.currentThread().isInterrupted()) {

            try {
                String descripcion = descripciones[random.nextInt(descripciones.length)];

                NivelGravedad gravedad = niveles[random.nextInt(niveles.length)];

                double distancia = 1 + random.nextDouble() * 15;

                gestor.registrarEmergencia(
                        descripcion,
                        gravedad,
                        distancia);

                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}