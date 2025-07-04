package com.greengardens.elevator;

/**
 * Panel interno del ascensor con botones para cada piso
 * Patrón Observer - ConcreteObserver
 * Patrón Command - Invoker
 */
public class PanelInterno implements AscensorObserver {
    private final Ascensor ascensor;
    private final String id;
    
    /**
     * Constructor del panel interno
     */
    public PanelInterno(Ascensor ascensor) {
        this.ascensor = ascensor;
        this.id = "PANEL-INTERNO";
        this.ascensor.agregarObserver(this); // Patrón Observer - Suscribirse
    }
    
    /**
     * Simula la pulsación de un botón para moverse a un piso específico
     * Patrón Command - Crea y ejecuta comando
     */
    public void moverA(int piso) {
        System.out.printf("🔘 [%s] Botón piso %d presionado\n", id, piso);
        
        // Patrón Command - Crear y ejecutar comando
        ComandoPanel comando = new ComandoMoverA(ascensor, piso, id);
        comando.ejecutar();
    }
    
    /**
     * Muestra el estado actual del panel
     */
    public void mostrarEstado() {
        System.out.printf("📱 [%s] Piso actual: %d | Estado: %s\n", 
            id, ascensor.getPisoActual(), ascensor.getEstado());
        
        System.out.printf("   Botones disponibles: ");
        for (int i = ascensor.getPisoMinimo(); i <= ascensor.getPisoMaximo(); i++) {
            if (i == ascensor.getPisoActual()) {
                System.out.printf("[%d*] ", i);
            } else {
                System.out.printf("[%d] ", i);
            }
        }
        System.out.println();
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de piso
     */
    @Override
    public void onCambioPiso(int pisoAnterior, int pisoActual) {
        System.out.printf("📱 [%s] Display actualizado: Piso %d\n", id, pisoActual);
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de estado
     */
    @Override
    public void onCambioEstado(EstadoAscensor estadoAnterior, EstadoAscensor estadoActual) {
        System.out.printf("📱 [%s] Estado actualizado: %s\n", id, estadoActual);
    }
    
    public String getId() {
        return id;
    }
}
