package com.greengardens.sensors;

/**
 * Enumeración que representa los diferentes tipos de sensores
 */
public enum TipoSensor {
    HUMEDAD_SUELO("Sensor de Humedad del Suelo", "%"),
    TEMPERATURA("Sensor de Temperatura", "°C"),
    LUZ_SOLAR("Sensor de Luz Solar", "lux"),
    PH_SUELO("Sensor de pH del Suelo", "pH"),
    VIENTO("Sensor de Viento", "km/h");
    
    private final String nombre;
    private final String unidadMedida;
    
    TipoSensor(String nombre, String unidadMedida) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getUnidadMedida() {
        return unidadMedida;
    }
}
