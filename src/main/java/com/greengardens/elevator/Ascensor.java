package com.greengardens.elevator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Clase que representa un ascensor sin memoria
 * Patrón Observer - Subject/Observable
 * Patrón State - Context que mantiene el estado actual
 */
public class Ascensor {
    private EstadoAscensor estado; // Patrón State - Estado actual
    private int pisoActual;
    private final int pisoMinimo;
    private final int pisoMaximo;
    private final List<AscensorObserver> observers; // Patrón Observer - Lista de observers
    
    /**
     * Constructor del ascensor
     */
    public Ascensor(int pisoMinimo, int pisoMaximo, int pisoInicial) {
        if (pisoMinimo >= pisoMaximo) {
            throw new IllegalArgumentException("El piso mínimo debe ser menor que el máximo");
        }
        if (pisoInicial < pisoMinimo || pisoInicial > pisoMaximo) {
            throw new IllegalArgumentException("El piso inicial debe estar entre el mínimo y máximo");
        }
        
        this.pisoMinimo = pisoMinimo;
        this.pisoMaximo = pisoMaximo;
        this.pisoActual = pisoInicial;
        this.estado = EstadoAscensor.PARADO; // Patrón State - Estado inicial
        this.observers = new CopyOnWriteArrayList<>();
    }
    
    /**
     * Patrón Observer - Método para suscribir observers
     */
    public void agregarObserver(AscensorObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    /**
     * Patrón Observer - Método para desuscribir observers
     */
    public boolean removerObserver(AscensorObserver observer) {
        return observers.remove(observer);
    }
    
    /**
     * Mueve el ascensor al piso especificado
     * Patrón State - Delega la validación al estado actual
     * Sin memoria - No recuerda llamadas previas, solo procesa la actual
     */
    public synchronized void moverA(int pisoDestino) {
        validarPiso(pisoDestino);
        
        // Patrón State - Verificar si el estado actual permite movimiento
        if (!estado.puedeIniciarMovimiento()) {
            throw new IllegalStateException("El ascensor ya está en movimiento");
        }
        
        if (pisoDestino == pisoActual) {
            System.out.printf("🔔 El ascensor ya está en el piso %d\n", pisoDestino);
            return;
        }
        
        // Sin memoria - Procesar inmediatamente sin recordar otras llamadas
        Thread movimientoThread = new Thread(() -> ejecutarMovimiento(pisoDestino));
        movimientoThread.setName("AscensorMovimiento-" + System.currentTimeMillis());
        movimientoThread.start();
    }
    
    /**
     * Ejecuta el movimiento del ascensor paso a paso
     * Patrón State - Cambia estados durante el movimiento
     */
    private void ejecutarMovimiento(int pisoDestino) {
        // Patrón State - Cambiar a estado EN_MOVIMIENTO
        cambiarEstado(EstadoAscensor.EN_MOVIMIENTO);
        
        System.out.printf("🚀 Ascensor iniciando movimiento del piso %d al piso %d\n", pisoActual, pisoDestino);
        
        // Determinar dirección
        int direccion = pisoDestino > pisoActual ? 1 : -1;
        
        // Moverse piso por piso (sin memoria - solo hacia el destino actual)
        while (pisoActual != pisoDestino) {
            try {
                // Simular tiempo de movimiento entre pisos
                TimeUnit.MILLISECONDS.sleep(1500);
                
                int pisoAnterior = pisoActual;
                pisoActual += direccion;
                
                System.out.printf("🏢 Ascensor pasando por el piso %d\n", pisoActual);
                
                // Patrón Observer - Notificar cambio de piso
                notificarCambioPiso(pisoAnterior, pisoActual);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("❌ Movimiento del ascensor interrumpido");
                return;
            }
        }
        
        // Patrón State - Cambiar a estado PARADO
        cambiarEstado(EstadoAscensor.PARADO);
        System.out.printf("✅ Ascensor llegó al piso %d\n", pisoActual);
    }
    
    /**
     * Cambia el estado del ascensor
     * Patrón State - Método para cambiar estados
     */
    private void cambiarEstado(EstadoAscensor nuevoEstado) {
        EstadoAscensor estadoAnterior = this.estado;
        this.estado = nuevoEstado;
        
        if (estadoAnterior != nuevoEstado) {
            // Patrón Observer - Notificar cambio de estado
            notificarCambioEstado(estadoAnterior, nuevoEstado);
        }
    }
    
    /**
     * Patrón Observer - Notifica cambio de piso a todos los observers
     */
    private void notificarCambioPiso(int pisoAnterior, int pisoActual) {
        for (AscensorObserver observer : observers) {
            try {
                observer.onCambioPiso(pisoAnterior, pisoActual);
            } catch (Exception e) {
                System.err.printf("❌ Error al notificar observer: %s\n", e.getMessage());
            }
        }
    }
    
    /**
     * Patrón Observer - Notifica cambio de estado a todos los observers
     */
    private void notificarCambioEstado(EstadoAscensor estadoAnterior, EstadoAscensor estadoActual) {
        for (AscensorObserver observer : observers) {
            try {
                observer.onCambioEstado(estadoAnterior, estadoActual);
            } catch (Exception e) {
                System.err.printf("❌ Error al notificar observer: %s\n", e.getMessage());
            }
        }
    }
    
    /**
     * Valida que el piso esté dentro del rango permitido
     */
    private void validarPiso(int piso) {
        if (piso < pisoMinimo || piso > pisoMaximo) {
            throw new IllegalArgumentException(
                String.format("Piso inválido: %d. Debe estar entre %d y %d", piso, pisoMinimo, pisoMaximo));
        }
    }
    
    // Getters
    public boolean isParado() {
        return estado == EstadoAscensor.PARADO;
    }
    
    public int getPisoActual() {
        return pisoActual;
    }
    
    public int getPisoMinimo() {
        return pisoMinimo;
    }
    
    public int getPisoMaximo() {
        return pisoMaximo;
    }
    
    public EstadoAscensor getEstado() {
        return estado;
    }
    
    @Override
    public String toString() {
        return String.format("Ascensor{piso=%d, estado=%s}", pisoActual, estado);
    }
}
