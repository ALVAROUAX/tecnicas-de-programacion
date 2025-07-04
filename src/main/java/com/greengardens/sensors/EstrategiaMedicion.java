package com.greengardens.sensors;

/**
 * Interface para el patrón Strategy - Strategy
 * Define diferentes estrategias de medición según el tipo de sensor
 */
public interface EstrategiaMedicion {
    
    /**
     * Genera una medida según la estrategia específica
     * @return Valor medido
     */
    double generarMedida();
    
    /**
     * Obtiene el nombre de la estrategia
     * @return Nombre descriptivo
     */
    String getNombreEstrategia();
}
