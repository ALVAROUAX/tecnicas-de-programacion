package com.greengardens.sensors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase base que representa un sensor individual
 * Patrón Composite - Leaf (hoja)
 * Patrón Strategy - Context para diferentes estrategias de medición
 */
public class Sensor implements ISensor {
    private final String id;
    private final TipoSensor tipo;
    private EstadoConexion estadoConexion;
    private final double umbral;
    private double medidaActual;
    private LocalDateTime ultimaActualizacion;
    private final EstrategiaMedicion estrategiaMedicion;
    
    /**
     * Constructor para crear un sensor
     * @param id Identificador único del sensor
     * @param tipo Tipo de sensor
     * @param umbral Valor umbral para disparar alarmas
     */
    public Sensor(String id, TipoSensor tipo, double umbral) {
        this.id = Objects.requireNonNull(id, "El ID del sensor no puede ser null");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de sensor no puede ser null");
        this.umbral = umbral;
        this.estadoConexion = EstadoConexion.CONECTADO;
        this.medidaActual = 0.0;
        this.ultimaActualizacion = LocalDateTime.now();
        
        // Patrón Strategy - Asignar estrategia según el tipo
        this.estrategiaMedicion = EstrategiaFactory.crearEstrategia(tipo);
    }
    
    /**
     * Obtiene la medida actual del sensor
     * Patrón Strategy - Delega la medición a la estrategia específica
     * @return Valor actual medido por el sensor
     */
    @Override
    public double obtenerMedidaActual() {
        if (estadoConexion == EstadoConexion.DESCONECTADO) {
            throw new IllegalStateException("No se puede obtener medida de un sensor desconectado");
        }
        
        // Usar estrategia específica para generar la medida
        this.medidaActual = estrategiaMedicion.generarMedida();
        this.ultimaActualizacion = LocalDateTime.now();
        return medidaActual;
    }
    
    /**
     * Verifica si la medida actual supera el umbral establecido
     * @return true si la medida supera el umbral, false en caso contrario
     */
    @Override
    public boolean superaUmbral() {
        if (estadoConexion == EstadoConexion.DESCONECTADO) {
            return false;
        }
        return obtenerMedidaActual() > umbral;
    }
    
    // Getters implementando la interface
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public TipoSensor getTipo() {
        return tipo;
    }
    
    @Override
    public EstadoConexion getEstadoConexion() {
        return estadoConexion;
    }
    
    public void setEstadoConexion(EstadoConexion estadoConexion) {
        this.estadoConexion = Objects.requireNonNull(estadoConexion);
    }
    
    @Override
    public double getUmbral() {
        return umbral;
    }
    
    public double getMedidaActual() {
        return medidaActual;
    }
    
    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Sensor{id='%s', tipo=%s, estado=%s, medida=%.2f %s, umbral=%.2f}",
                id, tipo.getNombre(), estadoConexion, medidaActual, 
                tipo.getUnidadMedida(), umbral);
    }
}
