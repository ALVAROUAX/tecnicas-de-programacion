package com.greengardens.elevator;

/**
 * Interface para el patrón Command
 * Define el contrato para todos los comandos de los paneles
 */
public interface ComandoPanel {
    
    /**
     * Ejecuta el comando
     */
    void ejecutar();
    
    /**
     * Obtiene la descripción del comando
     * @return Descripción del comando
     */
    String getDescripcion();
}
