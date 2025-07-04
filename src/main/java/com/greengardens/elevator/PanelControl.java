package com.greengardens.elevator;

/**
 * Panel de control situado fuera del ascensor con botones para todos los pisos
 * Patrón Observer - ConcreteObserver
 * Patrón Command - Invoker
 */
public class PanelControl implements AscensorObserver {
    private final Ascensor ascensor;
    private final String id;
    private boolean testigoMovimiento;
    
    /**
     * Constructor del panel de control
     */
    public PanelControl(Ascensor ascensor) {
        this.ascensor = ascensor;
        this.id = "PANEL-CONTROL";
        this.testigoMovimiento = false;
        this.ascensor.agregarObserver(this); // Patrón Observer - Suscribirse
    }
    
    /**
     * Simula la pulsación de un botón para llamar el ascensor a un piso específico
     * Patrón Command - Crea y ejecuta comando
     */
    public void llamarAscensorAPiso(int piso) {
        System.out.printf("🎛️ [%s] Llamando ascensor al piso %d\n", id, piso);
        
        // Patrón Command - Crear y ejecutar comando
        ComandoPanel comando = new ComandoMoverA(ascensor, piso, id);
        comando.ejecutar();
    }
    
    /**
     * Muestra el estado completo del panel de control
     */
    public void mostrarEstado() {
        String testigo = testigoMovimiento ? "🔴 EN MOVIMIENTO" : "🟢 PARADO";
        
        System.out.printf("🎛️ [%s] Display: Piso %d | Testigo: %s\n", 
            id, ascensor.getPisoActual(), testigo);
        
        System.out.printf("   Botones de llamada: ");
        for (int i = ascensor.getPisoMinimo(); i <= ascensor.getPisoMaximo(); i++) {
            if (i == ascensor.getPisoActual()) {
                System.out.printf("<%d> ", i);
            } else {
                System.out.printf("[%d] ", i);
            }
        }
        System.out.println();
    }
    
    /**
     * Genera un reporte detallado del sistema
     */
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("🎛️ REPORTE DEL SISTEMA DE ASCENSOR\n");
        reporte.append("=================================\n");
        reporte.append(String.format("Piso actual: %d\n", ascensor.getPisoActual()));
        reporte.append(String.format("Estado: %s\n", ascensor.getEstado()));
        reporte.append(String.format("Rango de pisos: %d - %d\n", 
            ascensor.getPisoMinimo(), ascensor.getPisoMaximo()));
        reporte.append(String.format("Testigo de movimiento: %s\n", 
            testigoMovimiento ? "ENCENDIDO" : "APAGADO"));
        
        return reporte.toString();
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de piso
     */
    @Override
    public void onCambioPiso(int pisoAnterior, int pisoActual) {
        System.out.printf("🎛️ [%s] Display actualizado: Piso %d\n", id, pisoActual);
    }
    
    /**
     * Patrón Observer - Reacciona a cambios de estado
     */
    @Override
    public void onCambioEstado(EstadoAscensor estadoAnterior, EstadoAscensor estadoActual) {
        testigoMovimiento = estadoActual.estaEnMovimiento(); // Usar método del patrón State
        System.out.printf("🎛️ [%s] Testigo de movimiento: %s\n", id, 
            testigoMovimiento ? "🔴 ENCENDIDO" : "🟢 APAGADO");
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isTestigoMovimiento() {
        return testigoMovimiento;
    }
}
