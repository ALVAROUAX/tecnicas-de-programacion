package com.greengardens.elevator;

/**
 * Panel externo ubicado en cada piso para llamar al ascensor
 * Patrón Observer - ConcreteObserver
 * Patrón Command - Invoker
 */
public class PanelExterno implements AscensorObserver {
    private final Ascensor ascensor;
    private final int pisoPanel;
    private final String id;
    private boolean testigoLuminoso;
    
    /**
     * Constructor del panel externo
     */
    public PanelExterno(Ascensor ascensor, int pisoPanel) {
        this.ascensor = ascensor;
        this.pisoPanel = pisoPanel;
        this.id = "PANEL-EXTERNO-P" + pisoPanel;
        this.testigoLuminoso = false;
        this.ascensor.agregarObserver(this); // Patrón Observer - Suscribirse
    }
    
    /**
     * Simula la pulsación del botón para llamar al ascensor
     * Patrón Command - Crea y ejecuta comando
     */
    public void llamarAscensor() {
        System.out.printf("🔔 [%s] Llamando ascensor al piso %d\n", id, pisoPanel);
        
        // Patrón Command - Crear y ejecutar comando
        ComandoPanel comando = new ComandoMoverA(ascensor, pisoPanel, id);
        comando.ejecutar();
    }
    
    /**
     * Muestra el estado actual del panel
     */
    public void mostrarEstado() {
        String testigo = testigoLuminoso ? "🔴 EN MOVIMIENTO" : "🟢 PARADO";
        boolean ascensorEnEstePiso = ascensor.getPisoActual() == pisoPanel;
        String ubicacion = ascensorEnEstePiso ? "AQUÍ" : "Piso " + ascensor.getPisoActual();
        
        System.out.printf("🏢 [%s] Testigo: %s | Ascensor en: %s\n", id, testigo, ubicacion);
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de piso
     */
    @Override
    public void onCambioPiso(int pisoAnterior, int pisoActual) {
        // El panel externo no necesita mostrar cambios de piso específicos
        // pero podría implementar lógica adicional si fuera necesario
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de estado
     */
    @Override
    public void onCambioEstado(EstadoAscensor estadoAnterior, EstadoAscensor estadoActual) {
        testigoLuminoso = estadoActual.estaEnMovimiento(); // Usar método del patrón State
        System.out.printf("🏢 [%s] Testigo luminoso: %s\n", id, 
            testigoLuminoso ? "🔴 ENCENDIDO" : "🟢 APAGADO");
    }
    
    public int getPisoPanel() {
        return pisoPanel;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isTestigoLuminoso() {
        return testigoLuminoso;
    }
}
