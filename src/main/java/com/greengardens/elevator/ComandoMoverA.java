package com.greengardens.elevator;

/**
 * Comando concreto para mover el ascensor a un piso específico
 * Patrón Command - ConcreteCommand
 */
public class ComandoMoverA implements ComandoPanel {
    private final Ascensor ascensor;
    private final int pisoDestino;
    private final String origen;
    
    /**
     * Constructor del comando
     * @param ascensor Ascensor a controlar
     * @param pisoDestino Piso de destino
     * @param origen Origen del comando (para logging)
     */
    public ComandoMoverA(Ascensor ascensor, int pisoDestino, String origen) {
        this.ascensor = ascensor;
        this.pisoDestino = pisoDestino;
        this.origen = origen;
    }
    
    /**
     * Patrón Command - Ejecuta el comando encapsulado
     */
    @Override
    public void ejecutar() {
        try {
            System.out.printf("🎯 [%s] Ejecutando comando: Mover a piso %d\n", origen, pisoDestino);
            ascensor.moverA(pisoDestino);
        } catch (Exception e) {
            System.err.printf("❌ [%s] Error al ejecutar comando: %s\n", origen, e.getMessage());
        }
    }
    
    @Override
    public String getDescripcion() {
        return String.format("Mover ascensor al piso %d desde %s", pisoDestino, origen);
    }
    
    public int getPisoDestino() {
        return pisoDestino;
    }
    
    public String getOrigen() {
        return origen;
    }
}
