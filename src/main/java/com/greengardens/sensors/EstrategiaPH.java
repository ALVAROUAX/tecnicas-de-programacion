package com.greengardens.sensors;

import java.util.Random;

/**
 * Estrategia específica para sensores de pH
 * Patrón Strategy - ConcreteStrategy
 */
public class EstrategiaPH implements EstrategiaMedicion {
    private static final Random random = new Random();
    private static final double MIN_PH = 4.0;
    private static final double MAX_PH = 10.0;
    
    @Override
    public double generarMedida() {
        // Simular pH del suelo
        double base = MIN_PH + random.nextDouble() * (MAX_PH - MIN_PH);
        
        // Pequeña variación para simular cambios graduales
        double variacion = (random.nextDouble() - 0.5) * 0.5;
        double resultado = Math.max(MIN_PH, Math.min(MAX_PH, base + variacion));
        
        return Math.round(resultado * 100.0) / 100.0;
    }
    
    @Override
    public String getNombreEstrategia() {
        return "Estrategia de Medición de pH";
    }
}
