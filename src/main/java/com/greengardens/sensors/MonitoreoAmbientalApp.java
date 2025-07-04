package com.greengardens.sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Aplicación principal para demostrar el sistema de monitoreo ambiental
 * Ejercicio académico - Sistema de sensores con patrón Composite y Observer
 */
public class MonitoreoAmbientalApp {
    private static final Logger logger = LoggerFactory.getLogger(MonitoreoAmbientalApp.class);
    
    public static void main(String[] args) {
        System.out.println("🌱 GREEN GARDEN - Sistema de Monitoreo Ambiental");
        System.out.println("================================================");
        
        // Crear sistema de alarma
        SistemaAlarma sistemaAlarma = new SistemaAlarma();
        
        // Agregar listener para mostrar alarmas
        sistemaAlarma.agregarAlarmaListener((sensor, mensaje) -> {
            System.out.println("📧 Notificación enviada: " + sensor.getId());
        });
        
        // Crear sensores individuales
        Sensor sensorHumedad1 = new Sensor("HUM-001", TipoSensor.HUMEDAD_SUELO, 60.0);
        Sensor sensorHumedad2 = new Sensor("HUM-002", TipoSensor.HUMEDAD_SUELO, 60.0);
        Sensor sensorTemp1 = new Sensor("TEMP-001", TipoSensor.TEMPERATURA, 30.0);
        Sensor sensorLuz1 = new Sensor("LUZ-001", TipoSensor.LUZ_SOLAR, 50000.0);
        
        // Crear sensor compuesto de humedad
        SensorCompuesto sensorHumedadCompuesto = new SensorCompuesto("HUM-COMP-001", TipoSensor.HUMEDAD_SUELO, 65.0);
        sensorHumedadCompuesto.agregarSensor(sensorHumedad1);
        sensorHumedadCompuesto.agregarSensor(sensorHumedad2);
        
        // Agregar sensores al sistema
        sistemaAlarma.agregarSensor(sensorHumedadCompuesto);
        sistemaAlarma.agregarSensor(sensorTemp1);
        sistemaAlarma.agregarSensor(sensorLuz1);
        
        // Crear scheduler para monitoreo automático
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // Programar monitoreo cada 5 segundos
        scheduler.scheduleAtFixedRate(() -> {
            try {
                sistemaAlarma.ejecutarCicloMonitoreo();
            } catch (Exception e) {
                logger.error("Error en ciclo de monitoreo: {}", e.getMessage());
            }
        }, 0, 5, TimeUnit.SECONDS);
        
        // Interfaz de usuario simple
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;
        
        while (ejecutando) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver reporte de estado");
            System.out.println("2. Desconectar sensor");
            System.out.println("3. Conectar sensor");
            System.out.println("4. Activar/Desactivar sistema");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                
                switch (opcion) {
                    case 1 -> System.out.println(sistemaAlarma.generarReporteEstado());
                    case 2 -> desconectarSensor(sistemaAlarma, scanner);
                    case 3 -> conectarSensor(sistemaAlarma, scanner);
                    case 4 -> toggleSistema(sistemaAlarma);
                    case 5 -> {
                        ejecutando = false;
                        scheduler.shutdown();
                        System.out.println("Sistema finalizado. ¡Hasta luego!");
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        scanner.close();
    }
    
    private static void desconectarSensor(SistemaAlarma sistema, Scanner scanner) {
        System.out.println("Sensores disponibles:");
        sistema.getSensores().forEach(s -> 
            System.out.println("- " + s.getId() + " (" + s.getEstadoConexion() + ")"));
        
        System.out.print("ID del sensor a desconectar: ");
        String id = scanner.nextLine();
        
        sistema.getSensores().stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .ifPresentOrElse(
                sensor -> {
                    sensor.setEstadoConexion(EstadoConexion.DESCONECTADO);
                    System.out.println("Sensor " + id + " desconectado");
                },
                () -> System.out.println("Sensor no encontrado")
            );
    }
    
    private static void conectarSensor(SistemaAlarma sistema, Scanner scanner) {
        System.out.println("Sensores disponibles:");
        sistema.getSensores().forEach(s -> 
            System.out.println("- " + s.getId() + " (" + s.getEstadoConexion() + ")"));
        
        System.out.print("ID del sensor a conectar: ");
        String id = scanner.nextLine();
        
        sistema.getSensores().stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .ifPresentOrElse(
                sensor -> {
                    sensor.setEstadoConexion(EstadoConexion.CONECTADO);
                    System.out.println("Sensor " + id + " conectado");
                },
                () -> System.out.println("Sensor no encontrado")
            );
    }
    
    private static void toggleSistema(SistemaAlarma sistema) {
        if (sistema.isSistemaActivo()) {
            sistema.desactivarSistema();
            System.out.println("Sistema desactivado");
        } else {
            sistema.activarSistema();
            System.out.println("Sistema activado");
        }
    }
}
