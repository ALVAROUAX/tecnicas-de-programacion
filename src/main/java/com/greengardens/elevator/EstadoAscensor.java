package com.greengardens.elevator;

/**
 * Enumeración que representa los estados del ascensor
 * Patrón State - Estados concretos
 */
public enum EstadoAscensor {
    PARADO("Parado") {
        @Override
        public boolean puedeIniciarMovimiento() {
            return true;
        }
        
        @Override
        public boolean estaEnMovimiento() {
            return false;
        }
    },
    
    EN_MOVIMIENTO("En Movimiento") {
        @Override
        public boolean puedeIniciarMovimiento() {
            return false;
        }
        
        @Override
        public boolean estaEnMovimiento() {
            return true;
        }
    };
    
    private final String descripcion;
    
    EstadoAscensor(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Patrón State - Comportamiento específico del estado
     * @return true si puede iniciar movimiento
     */
    public abstract boolean puedeIniciarMovimiento();
    
    /**
     * Patrón State - Comportamiento específico del estado
     * @return true si está en movimiento
     */
    public abstract boolean estaEnMovimiento();
    
    @Override
    public String toString() {
        return descripcion;
    }
}
