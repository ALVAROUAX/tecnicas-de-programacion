package com.greengardens.sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sistema de alarma que implementa el patrón Observer
 * Actúa como Subject/Observable que notifica a los observers cuando se disparan alarmas
 */
public class SistemaAlarma {
    private static final Logger logger = LoggerFactory.getLogger(SistemaAlarma.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final List<ISensor> sensores;
    private final List<AlarmaObserver> observers; // Patrón Observer - Lista de observers
    private boolean sistemaActivo;
    
    /**
     * Constructor del sistema de alarma
     */
    public SistemaAlarma() {
        this.sensores = new CopyOnWriteArrayList<>();
        this.observers = new CopyOnWriteArrayList<>();
        this.sistemaActivo = true;
    }
    
    /**
     * Agrega un sensor al sistema de monitoreo
     * @param sensor Sensor a agregar
     */
    public void agregarSensor(ISensor sensor) {
        Objects.requireNonNull(sensor, "El sensor no puede ser null");
        
        if (sensores.contains(sensor)) {
            throw new IllegalArgumentException("El sensor ya existe en el sistema: " + sensor.getId());
        }
        
        sensores.add(sensor);
        logger.info("Sensor agregado al sistema: {}", sensor.getId());
    }
    
    /**
     * Remueve un sensor del sistema
     * @param sensor Sensor a remover
     * @return true si fue removido
     */
    public boolean removerSensor(ISensor sensor) {
        boolean removido = sensores.remove(sensor);
        if (removido) {
            logger.info("Sensor removido del sistema: {}", sensor.getId());
        }
        return removido;
    }
    
    /**
     * Agrega un observer para recibir notificaciones de alarmas
     * Patrón Observer - Método para suscribir observers
     * @param observer Observer a agregar
     */
    public void agregarObserver(AlarmaObserver observer) {
        Objects.requireNonNull(observer, "El observer no puede ser null");
        observers.add(observer);
    }
    
    /**
     * Remueve un observer
     * Patrón Observer - Método para desuscribir observers
     * @param observer Observer a remover
     * @return true si fue removido
     */
    public boolean removerObserver(AlarmaObserver observer) {
        return observers.remove(observer);
    }
    
    /**
     * Método de conveniencia para mantener compatibilidad
     */
    public void agregarAlarmaListener(AlarmaListener listener) {
        agregarObserver(listener::onAlarmaDisparada);
    }
    
    /**
     * Ejecuta un ciclo de monitoreo verificando todos los sensores
     * @return Lista de sensores que dispararon alarmas
     */
    public List<ISensor> ejecutarCicloMonitoreo() {
        if (!sistemaActivo) {
            logger.warn("Sistema de alarma desactivado");
            return List.of();
        }
        
        List<ISensor> sensoresConAlarma = new ArrayList<>();
        
        for (ISensor sensor : sensores) {
            try {
                if (sensor.getEstadoConexion() == EstadoConexion.CONECTADO && sensor.superaUmbral()) {
                    sensoresConAlarma.add(sensor);
                    notificarAlarma(sensor);
                }
            } catch (Exception e) {
                logger.error("Error al verificar sensor {}: {}", sensor.getId(), e.getMessage());
            }
        }
        
        return sensoresConAlarma;
    }
    
    /**
     * Notifica a todos los observers sobre una alarma
     * Patrón Observer - Método para notificar a todos los observers
     * @param sensor Sensor que disparó la alarma
     */
    private void notificarAlarma(ISensor sensor) {
        String mensaje = String.format(
            "ALARMA DISPARADA - Sensor: %s, Tipo: %s, Valor: %.2f %s, Umbral: %.2f, Tiempo: %s",
            sensor.getId(),
            sensor.getTipo().getNombre(),
            sensor.obtenerMedidaActual(),
            sensor.getTipo().getUnidadMedida(),
            sensor.getUmbral(),
            LocalDateTime.now().format(FORMATTER)
        );
        
        logger.warn(mensaje);
        System.out.println("🚨 " + mensaje);
        
        // Notificar a todos los observers
        for (AlarmaObserver observer : observers) {
            try {
                observer.onAlarmaDisparada(sensor, mensaje);
            } catch (Exception e) {
                logger.error("Error al notificar observer: {}", e.getMessage());
            }
        }
    }
    
    /**
     * Genera un reporte del estado actual de todos los sensores
     * @return Reporte detallado del sistema
     */
    public String generarReporteEstado() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DEL SISTEMA DE ALARMA ===\n");
        reporte.append(String.format("Estado del sistema: %s\n", sistemaActivo ? "ACTIVO" : "INACTIVO"));
        reporte.append(String.format("Total de sensores: %d\n", sensores.size()));
        reporte.append(String.format("Tiempo de reporte: %s\n\n", LocalDateTime.now().format(FORMATTER)));
        
        for (ISensor sensor : sensores) {
            reporte.append(String.format("Sensor ID: %s\n", sensor.getId()));
            reporte.append(String.format("  Tipo: %s\n", sensor.getTipo().getNombre()));
            reporte.append(String.format("  Estado: %s\n", sensor.getEstadoConexion()));
            reporte.append(String.format("  Umbral: %.2f %s\n", sensor.getUmbral(), sensor.getTipo().getUnidadMedida()));
            
            if (sensor.getEstadoConexion() == EstadoConexion.CONECTADO) {
                try {
                    double medida = sensor.obtenerMedidaActual();
                    boolean alarma = sensor.superaUmbral();
                    reporte.append(String.format("  Medida actual: %.2f %s %s\n", 
                        medida, sensor.getTipo().getUnidadMedida(), alarma ? "⚠️ ALARMA" : "✅ OK"));
                } catch (Exception e) {
                    reporte.append("  Error al leer medida: ").append(e.getMessage()).append("\n");
                }
            }
            
            if (sensor instanceof SensorCompuesto sensorCompuesto) {
                reporte.append(String.format("  Sensores individuales: %d/%d conectados\n",
                    sensorCompuesto.getNumeroSensoresConectados(), sensorCompuesto.getNumeroSensores()));
            }
            
            reporte.append("\n");
        }
        
        return reporte.toString();
    }
    
    // Getters y métodos de control
    public List<ISensor> getSensores() {
        return List.copyOf(sensores);
    }
    
    public boolean isSistemaActivo() {
        return sistemaActivo;
    }
    
    public void activarSistema() {
        this.sistemaActivo = true;
        logger.info("Sistema de alarma activado");
    }
    
    public void desactivarSistema() {
        this.sistemaActivo = false;
        logger.info("Sistema de alarma desactivado");
    }
    
    /**
     * Interface Observer para el patrón Observer
     */
    @FunctionalInterface
    public interface AlarmaObserver {
        void onAlarmaDisparada(ISensor sensor, String mensaje);
    }
    
    /**
     * Interface para mantener compatibilidad con el código existente
     */
    @FunctionalInterface
    public interface AlarmaListener {
        void onAlarmaDisparada(ISensor sensor, String mensaje);
    }
}
