package com.greengardens.sensors;

/**
 * Interface que define el contrato para todos los sensores
 * Parte del patrón Composite - Component
 */
public interface ISensor {
    
    /**
     * Obtiene la medida actual del sensor
     * @return Valor actual medido
     */
    double obtenerMedidaActual();
    
    /**
     * Verifica si la medida supera el umbral
     * @return true si supera el umbral
     */
    boolean superaUmbral();
    
    /**
     * Obtiene el ID del sensor
     * @return Identificador único
     */
    String getId();
    
    /**
     * Obtiene el tipo de sensor
     * @return Tipo del sensor
     */
    TipoSensor getTipo();
    
    /**
     * Obtiene el estado de conexión
     * @return Estado actual de conexión
     */
    EstadoConexion getEstadoConexion();
    
    /**
     * Obtiene el umbral configurado
     * @return Valor del umbral
     */
    double getUmbral();
}
