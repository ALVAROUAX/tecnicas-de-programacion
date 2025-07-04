package com.greengardens.sensors;

import java.util.Random;

/**
 * Estrategia específica para sensores de viento
 * Patrón Strategy - ConcreteStrategy
 */
public class EstrategiaViento implements EstrategiaMedicion {
    private static final Random random = new Random();
    private static final double MIN_VIENTO = 0.0;
    private static final double MAX_VIENTO = 60.0;
    
    @Override
    public double generarMedida() {
        // Simular velocidad del viento (km/h)
        double base = random.nextDouble() * MAX_VIENTO;
        
        // Simular ráfagas ocasionales
        if (random.nextDouble() < 0.1) { // 10% probabilidad de ráfaga
            base *= 1.5;
        }
        
        double resultado = Math.min(MAX_VIENTO, base);
        return Math.round(resultado * 100.0) / 100.0;
    }
    
    @Override
    public String getNombreEstrategia() {
        return "Estrategia de Medición de Viento";
    }
}
