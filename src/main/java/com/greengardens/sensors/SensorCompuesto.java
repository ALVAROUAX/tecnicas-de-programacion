package com.greengardens.sensors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un sensor compuesto por múltiples sensores individuales.
 * Patrón Composite - Composite
 * Implementa la misma interface que los sensores individuales
 */
public class SensorCompuesto implements ISensor {
    private final String id;
    private final TipoSensor tipo;
    private EstadoConexion estadoConexion;
    private final double umbral;
    private final List<ISensor> sensoresHijos;
    
    /**
     * Constructor para crear un sensor compuesto
     * @param id Identificador único del sensor compuesto
     * @param tipo Tipo de sensor compuesto
     * @param umbral Valor umbral para el promedio de los sensores
     */
    public SensorCompuesto(String id, TipoSensor tipo, double umbral) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser null");
        this.tipo = Objects.requireNonNull(tipo, "El tipo no puede ser null");
        this.umbral = umbral;
        this.estadoConexion = EstadoConexion.CONECTADO;
        this.sensoresHijos = new ArrayList<>();
    }
    
    /**
     * Agrega un sensor hijo al compuesto
     * Patrón Composite - Operación para manejar hijos
     * @param sensor Sensor a agregar
     */
    public void agregarSensor(ISensor sensor) {
        Objects.requireNonNull(sensor, "El sensor no puede ser null");
        
        if (sensor.getTipo() != this.tipo) {
            throw new IllegalArgumentException(
                String.format("El sensor debe ser del mismo tipo: %s", this.tipo));
        }
        
        if (sensor == this) {
            throw new IllegalArgumentException("Un sensor compuesto no puede contenerse a sí mismo");
        }
        
        sensoresHijos.add(sensor);
    }
    
    /**
     * Remueve un sensor del compuesto
     * Patrón Composite - Operación para manejar hijos
     * @param sensor Sensor a remover
     * @return true si fue removido
     */
    public boolean removerSensor(ISensor sensor) {
        return sensoresHijos.remove(sensor);
    }
    
    /**
     * Obtiene la medida actual como el promedio de los sensores hijos conectados
     * Patrón Composite - Operación que se propaga a los hijos
     * @return Valor promedio de las medidas
     */
    @Override
    public double obtenerMedidaActual() {
        if (estadoConexion == EstadoConexion.DESCONECTADO) {
            throw new IllegalStateException("No se puede obtener medida de un sensor desconectado");
        }
        
        List<ISensor> sensoresConectados = sensoresHijos.stream()
                .filter(sensor -> sensor.getEstadoConexion() == EstadoConexion.CONECTADO)
                .toList();
        
        if (sensoresConectados.isEmpty()) {
            throw new IllegalStateException("No hay sensores conectados para calcular el promedio");
        }
        
        double suma = sensoresConectados.stream()
                .mapToDouble(ISensor::obtenerMedidaActual)
                .sum();
        
        return suma / sensoresConectados.size();
    }
    
    /**
     * Verifica si el valor medio supera el umbral
     * Patrón Composite - Operación que se propaga a los hijos
     * @return true si el promedio supera el umbral
     */
    @Override
    public boolean superaUmbral() {
        if (estadoConexion == EstadoConexion.DESCONECTADO) {
            return false;
        }
        
        try {
            return obtenerMedidaActual() > umbral;
        } catch (IllegalStateException e) {
            return false;
        }
    }
    
    // Implementación de la interface ISensor
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
    
    /**
     * Obtiene la lista de sensores hijos (copia defensiva)
     * @return Lista inmutable de sensores
     */
    public List<ISensor> getSensoresHijos() {
        return List.copyOf(sensoresHijos);
    }
    
    /**
     * Obtiene el número de sensores hijos
     * @return Cantidad de sensores en el compuesto
     */
    public int getNumeroSensores() {
        return sensoresHijos.size();
    }
    
    /**
     * Obtiene el número de sensores conectados
     * @return Cantidad de sensores conectados
     */
    public int getNumeroSensoresConectados() {
        return (int) sensoresHijos.stream()
                .filter(sensor -> sensor.getEstadoConexion() == EstadoConexion.CONECTADO)
                .count();
    }
    
    @Override
    public String toString() {
        return String.format("SensorCompuesto{id='%s', tipo=%s, sensores=%d/%d conectados, umbral=%.2f}",
                id, tipo.getNombre(), getNumeroSensoresConectados(), 
                getNumeroSensores(), umbral);
    }
}
