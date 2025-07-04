package com.greengardens.sensors;

import java.util.Random;

/**
 * Estrategia específica para sensores de humedad
 * Patrón Strategy - ConcreteStrategy
 */
public class EstrategiaHumedad implements EstrategiaMedicion {
    private static final Random random = new Random();
    private static final double MIN_HUMEDAD = 20.0;
    private static final double MAX_HUMEDAD = 95.0;
    
    @Override
    public double generarMedida() {
        // Simular lectura realista de humedad del suelo
        double base = MIN_HUMEDAD + random.nextDouble() * (MAX_HUMEDAD - MIN_HUMEDAD);
        
        // Añadir variación temporal (simulando cambios por riego, evaporación, etc.)
        double variacion = (random.nextDouble() - 0.5) * 10.0;
        double resultado = Math.max(MIN_HUMEDAD, Math.min(MAX_HUMEDAD, base + variacion));
        
        return Math.round(resultado * 100.0) / 100.0; // 2 decimales
    }
    
    @Override
    public String getNombreEstrategia() {
        return "Estrategia de Medición de Humedad";
    }
}
