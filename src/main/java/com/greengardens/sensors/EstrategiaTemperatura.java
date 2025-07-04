package com.greengardens.sensors;

import java.util.Random;

/**
 * Estrategia específica para sensores de temperatura
 * Patrón Strategy - ConcreteStrategy
 */
public class EstrategiaTemperatura implements EstrategiaMedicion {
    private static final Random random = new Random();
    private static final double MIN_TEMP = 5.0;
    private static final double MAX_TEMP = 45.0;
    
    @Override
    public double generarMedida() {
        // Simular temperatura ambiente con variaciones diurnas
        double base = MIN_TEMP + random.nextDouble() * (MAX_TEMP - MIN_TEMP);
        
        // Simular variación por hora del día
        double variacionDiurna = Math.sin(System.currentTimeMillis() / 1000.0) * 5.0;
        double resultado = Math.max(MIN_TEMP, Math.min(MAX_TEMP, base + variacionDiurna));
        
        return Math.round(resultado * 100.0) / 100.0;
    }
    
    @Override
    public String getNombreEstrategia() {
        return "Estrategia de Medición de Temperatura";
    }
}
