package com.greengardens.sensors;

import java.util.Random;

/**
 * Estrategia específica para sensores de luz solar
 * Patrón Strategy - ConcreteStrategy
 */
public class EstrategiaLuzSolar implements EstrategiaMedicion {
    private static final Random random = new Random();
    private static final double MIN_LUZ = 0.0;
    private static final double MAX_LUZ = 120000.0;
    
    @Override
    public double generarMedida() {
        // Simular intensidad de luz solar (lux)
        double base = random.nextDouble() * MAX_LUZ;
        
        // Simular variación por nubosidad
        double factorNubosidad = 0.3 + random.nextDouble() * 0.7; // 30% - 100%
        double resultado = base * factorNubosidad;
        
        return Math.round(resultado * 100.0) / 100.0;
    }
    
    @Override
    public String getNombreEstrategia() {
        return "Estrategia de Medición de Luz Solar";
    }
}
