package com.greengardens.elevator;

/**
 * Interface Observer para recibir notificaciones de cambios en el ascensor
 */
public interface AscensorObserver {
    
    /**
     * Notifica cuando el ascensor cambia de piso
     * @param pisoAnterior Piso anterior
     * @param pisoActual Piso actual
     */
    void onCambioPiso(int pisoAnterior, int pisoActual);
    
    /**
     * Notifica cuando el ascensor cambia de estado
     * @param estadoAnterior Estado anterior
     * @param estadoActual Estado actual
     */
    void onCambioEstado(EstadoAscensor estadoAnterior, EstadoAscensor estadoActual);
}
