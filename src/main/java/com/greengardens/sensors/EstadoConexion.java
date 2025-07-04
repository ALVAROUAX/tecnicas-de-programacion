package com.greengardens.sensors;

/**
 * Enumeración que representa el estado de conexión de un sensor
 */
public enum EstadoConexion {
    CONECTADO("Conectado"),
    DESCONECTADO("Desconectado");
    
    private final String descripcion;
    
    EstadoConexion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
