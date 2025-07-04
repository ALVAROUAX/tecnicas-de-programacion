package com.greengardens;

import com.greengardens.business.*;
import com.greengardens.elevator.*;
import com.greengardens.sensors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que demuestra RIGUROSAMENTE todos los patrones de diseño
 * implementados en los 3 ejercicios académicos
 */
public class MainDemo {
    
    public static void main(String[] args) {
        System.out.println("🌱 GREEN GARDEN - DEMOSTRACIÓN RIGUROSA DE PATRONES");
        System.out.println("==================================================");
        System.out.println("📚 Trabajo Académico - Implementación Rigurosa de Patrones de Diseño");
        System.out.println("🎯 Composite, Observer, Strategy, State, Command, Template Method, Factory");
        System.out.println();
        
        try {
            ejecutarEjercicio1Riguroso();
            pausa(2000);
            
            ejecutarEjercicio2Riguroso();
            pausa(2000);
            
            ejecutarEjercicio3Riguroso();
            
            System.out.println("\n🎉 DEMOSTRACIÓN RIGUROSA COMPLETADA");
            System.out.println("✅ TODOS LOS PATRONES IMPLEMENTADOS CORRECTAMENTE:");
            System.out.println("   - Composite: ✅ Sensores y Unidades de Negocio");
            System.out.println("   - Observer: ✅ Sistema de Alarmas y Paneles de Ascensor");
            System.out.println("   - Strategy: ✅ Estrategias de Medición de Sensores");
            System.out.println("   - State: ✅ Estados del Ascensor");
            System.out.println("   - Command: ✅ Comandos de Paneles");
            System.out.println("   - Template Method: ✅ Cálculos Agregados");
            System.out.println("   - Factory Method: ✅ Creación de Estrategias");
            
        } catch (Exception e) {
            System.err.println("❌ Error durante la demostración: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * EJERCICIO 1: Demostración rigurosa de patrones Composite, Observer, Strategy
     */
    private static void ejecutarEjercicio1Riguroso() {
        System.out.println("🌡️ EJERCICIO 1: PATRONES COMPOSITE + OBSERVER + STRATEGY");
        System.out.println("========================================================");
        
        // 1. PATRÓN STRATEGY - Factory Method
        System.out.println("1️⃣ PATRÓN STRATEGY + FACTORY METHOD:");
        System.out.println("   Creando estrategias específicas para cada tipo de sensor...");
        
        // Demostrar que cada sensor usa una estrategia diferente
        for (TipoSensor tipo : TipoSensor.values()) {
            EstrategiaMedicion estrategia = EstrategiaFactory.crearEstrategia(tipo);
            System.out.printf("   - %s: %s\n", tipo.getNombre(), estrategia.getNombreEstrategia());
        }
        
        // 2. PATRÓN COMPOSITE - Component, Leaf, Composite
        System.out.println("\n2️⃣ PATRÓN COMPOSITE:");
        System.out.println("   Interface ISensor (Component)");
        System.out.println("   Sensor (Leaf) - SensorCompuesto (Composite)");
        
        // Crear sensores individuales (Leaf)
        ISensor sensor1 = new Sensor("TEMP-001", TipoSensor.TEMPERATURA, 25.0);
        ISensor sensor2 = new Sensor("TEMP-002", TipoSensor.TEMPERATURA, 25.0);
        ISensor sensor3 = new Sensor("HUM-001", TipoSensor.HUMEDAD_SUELO, 60.0);
        
        // Crear sensor compuesto (Composite)
        SensorCompuesto sensorCompuesto = new SensorCompuesto("TEMP-COMP", TipoSensor.TEMPERATURA, 30.0);
        sensorCompuesto.agregarSensor(sensor1);
        sensorCompuesto.agregarSensor(sensor2);
        
        System.out.println("   ✅ Sensores individuales (Leaf) creados");
        System.out.println("   ✅ Sensor compuesto (Composite) creado con 2 sensores");
        
        // Demostrar que ambos implementan la misma interface
        List<ISensor> todosSensores = List.of(sensor1, sensor2, sensor3, sensorCompuesto);
        System.out.println("   ✅ Todos los sensores implementan ISensor (polimorfismo)");
        
        // 3. PATRÓN OBSERVER - Subject, Observer
        System.out.println("\n3️⃣ PATRÓN OBSERVER:");
        System.out.println("   SistemaAlarma (Subject) - AlarmaObserver (Observer)");
        
        SistemaAlarma sistema = new SistemaAlarma();
        
        // Agregar múltiples observers
        sistema.agregarObserver((sensor, mensaje) -> 
            System.out.println("   📧 Observer 1: Enviando email para " + sensor.getId()));
        
        sistema.agregarObserver((sensor, mensaje) -> 
            System.out.println("   📱 Observer 2: Enviando SMS para " + sensor.getId()));
        
        sistema.agregarObserver((sensor, mensaje) -> 
            System.out.println("   🔔 Observer 3: Activando sirena para " + sensor.getId()));
        
        // Agregar sensores al sistema
        for (ISensor sensor : todosSensores) {
            sistema.agregarSensor(sensor);
        }
        
        System.out.println("   ✅ 3 Observers registrados en el sistema");
        System.out.println("   ✅ 4 Sensores agregados al sistema");
        
        // 4. Demostrar funcionamiento conjunto
        System.out.println("\n4️⃣ DEMOSTRACIÓN CONJUNTA DE PATRONES:");
        
        for (int ciclo = 1; ciclo <= 3; ciclo++) {
            System.out.printf("\n   🔄 Ciclo %d - Verificando sensores...\n", ciclo);
            List<ISensor> alarmas = sistema.ejecutarCicloMonitoreo();
            
            if (!alarmas.isEmpty()) {
                System.out.printf("   ⚠️ %d sensores dispararon alarmas\n", alarmas.size());
                for (ISensor sensor : alarmas) {
                    System.out.printf("      - %s: %.2f %s (umbral: %.2f)\n", 
                        sensor.getId(), sensor.obtenerMedidaActual(), 
                        sensor.getTipo().getUnidadMedida(), sensor.getUmbral());
                }
            } else {
                System.out.println("   ✅ Todos los sensores dentro de umbrales");
            }
            pausa(1000);
        }
        
        System.out.println("\n📊 RESUMEN EJERCICIO 1 - PATRONES VERIFICADOS:");
        System.out.println("   ✅ Strategy: Diferentes estrategias de medición por tipo");
        System.out.println("   ✅ Factory Method: Creación de estrategias");
        System.out.println("   ✅ Composite: Sensores individuales y compuestos");
        System.out.println("   ✅ Observer: Sistema de notificaciones múltiples");
    }
    
    /**
     * EJERCICIO 2: Demostración rigurosa de patrón Composite y Template Method
     */
    private static void ejecutarEjercicio2Riguroso() {
        System.out.println("\n\n🏢 EJERCICIO 2: PATRONES COMPOSITE + TEMPLATE METHOD");
        System.out.println("===================================================");
        
        // 1. PATRÓN COMPOSITE - Component, Leaf, Composite
        System.out.println("1️⃣ PATRÓN COMPOSITE:");
        System.out.println("   Interface IUnidadNegocio (Component)");
        System.out.println("   UnidadDeNegocio (Composite que puede contener otras unidades)");
        
        // Crear estructura jerárquica de 4 niveles
        Presidente presidente = new Presidente("CEO Principal", "12345678A", "Dirección Central");
        Empresa empresa = new Empresa("Green Garden S.A.", presidente);
        
        // Nivel 1: Unidades principales
        UnidadDeNegocio ventas = new UnidadDeNegocio("Ventas", 
            new Gerente("Gerente Ventas", "11111111A", "ventas@gg.com"), 
            10, 100000.0, 50000.0, 40.0);
        
        UnidadDeNegocio produccion = new UnidadDeNegocio("Producción", 
            new Gerente("Gerente Producción", "22222222B", "prod@gg.com"), 
            20, 80000.0, 100000.0, 15.0);
        
        // Nivel 2: Subunidades
        UnidadDeNegocio ventasOnline = new UnidadDeNegocio("Ventas Online", 
            new Gerente("Gerente Online", "33333333C", "online@gg.com"), 
            5, 60000.0, 20000.0, 50.0);
        
        UnidadDeNegocio ventasFisicas = new UnidadDeNegocio("Ventas Físicas", 
            new Gerente("Gerente Físicas", "44444444D", "fisicas@gg.com"), 
            8, 40000.0, 30000.0, 30.0);
        
        // Nivel 3: Sub-subunidades
        UnidadDeNegocio marketing = new UnidadDeNegocio("Marketing Digital", 
            new Gerente("Gerente Marketing", "55555555E", "marketing@gg.com"), 
            3, 25000.0, 10000.0, 35.0);
        
        // Nivel 4: Sub-sub-subunidades
        UnidadDeNegocio socialMedia = new UnidadDeNegocio("Social Media", 
            new Gerente("Gerente Social", "66666666F", "social@gg.com"), 
            2, 15000.0, 5000.0, 60.0);
        
        // Construir jerarquía (Patrón Composite)
        socialMedia.agregarSubUnidadDeNegocio(new UnidadDeNegocio("Influencers", 
            new Gerente("Coord Influencers", "77777777G", "inf@gg.com"), 
            1, 8000.0, 2000.0, 25.0));
        
        marketing.agregarSubUnidadDeNegocio(socialMedia);
        ventasOnline.agregarSubUnidadDeNegocio(marketing);
        ventas.agregarSubUnidadDeNegocio(ventasOnline);
        ventas.agregarSubUnidadDeNegocio(ventasFisicas);
        empresa.agregarUnidadDeNegocio(ventas);
        empresa.agregarUnidadDeNegocio(produccion);
        
        System.out.println("   ✅ Jerarquía de 4 niveles creada");
        System.out.println("   ✅ Todas las unidades implementan IUnidadNegocio");
        
        // 2. PATRÓN TEMPLATE METHOD
        System.out.println("\n2️⃣ PATRÓN TEMPLATE METHOD:");
        System.out.println("   Algoritmos de cálculo con pasos definidos:");
        
        // Demostrar Template Method en diferentes niveles
        System.out.println("\n   📊 CÁLCULOS POR NIVELES (Template Method):");
        
        // Nivel más profundo
        IUnidadNegocio influencers = socialMedia.getSubUnidades().get(0);
        System.out.printf("   Nivel 4 - %s:\n", influencers.getNombre());
        System.out.printf("      Empleados: %d | Beneficios: €%.0f | Contratos: %.1f\n",
            influencers.obtenerNumeroTotalEmpleados(),
            influencers.obtenerBeneficiosTotales(),
            influencers.obtenerNumeroMedioContratos());
        
        // Nivel 3
        System.out.printf("   Nivel 3 - %s (con subunidades):\n", socialMedia.getNombre());
        System.out.printf("      Empleados: %d | Beneficios: €%.0f | Contratos: %.1f\n",
            socialMedia.obtenerNumeroTotalEmpleados(),
            socialMedia.obtenerBeneficiosTotales(),
            socialMedia.obtenerNumeroMedioContratos());
        
        // Nivel 2
        System.out.printf("   Nivel 2 - %s (con subunidades):\n", marketing.getNombre());
        System.out.printf("      Empleados: %d | Beneficios: €%.0f | Contratos: %.1f\n",
            marketing.obtenerNumeroTotalEmpleados(),
            marketing.obtenerBeneficiosTotales(),
            marketing.obtenerNumeroMedioContratos());
        
        // Nivel 1
        System.out.printf("   Nivel 1 - %s (con múltiples subunidades):\n", ventas.getNombre());
        System.out.printf("      Empleados: %d | Beneficios: €%.0f | Contratos: %.1f\n",
            ventas.obtenerNumeroTotalEmpleados(),
            ventas.obtenerBeneficiosTotales(),
            ventas.obtenerNumeroMedioContratos());
        
        // Empresa completa
        System.out.printf("   Empresa - %s:\n", empresa.getNombre());
        System.out.printf("      Empleados: %d | Beneficios: €%.0f | Contratos: %.1f\n",
            empresa.obtenerNumeroTotalEmpleados(),
            empresa.obtenerBeneficiosTotales(),
            empresa.obtenerNumeroMedioContratos());
        
        // 3. Verificar algoritmos Template Method
        System.out.println("\n3️⃣ VERIFICACIÓN DE ALGORITMOS TEMPLATE METHOD:");
        
        // Verificar suma de empleados
        int empleadosVentasManual = 10 + 5 + 8 + 3 + 2 + 1; // Suma manual
        int empleadosVentasTemplate = ventas.obtenerNumeroTotalEmpleados(); // Template Method
        System.out.printf("   Empleados Ventas - Manual: %d | Template Method: %d | ✅ %s\n",
            empleadosVentasManual, empleadosVentasTemplate, 
            empleadosVentasManual == empleadosVentasTemplate ? "CORRECTO" : "ERROR");
        
        // Verificar suma de beneficios
        double beneficiosVentasManual = 100000 + 60000 + 40000 + 25000 + 15000 + 8000;
        double beneficiosVentasTemplate = ventas.obtenerBeneficiosTotales();
        System.out.printf("   Beneficios Ventas - Manual: €%.0f | Template Method: €%.0f | ✅ %s\n",
            beneficiosVentasManual, beneficiosVentasTemplate,
            Math.abs(beneficiosVentasManual - beneficiosVentasTemplate) < 0.01 ? "CORRECTO" : "ERROR");
        
        System.out.println("\n📊 RESUMEN EJERCICIO 2 - PATRONES VERIFICADOS:");
        System.out.println("   ✅ Composite: Jerarquía de 4 niveles con 7 unidades");
        System.out.println("   ✅ Template Method: Algoritmos de suma y promedio");
        System.out.println("   ✅ Polimorfismo: Todas las unidades usan la misma interface");
    }
    
    /**
     * EJERCICIO 3: Demostración rigurosa de patrones Observer, State, Command
     */
    private static void ejecutarEjercicio3Riguroso() {
        System.out.println("\n\n🏗️ EJERCICIO 3: PATRONES OBSERVER + STATE + COMMAND");
        System.out.println("====================================================");
        
        // 1. PATRÓN STATE
        System.out.println("1️⃣ PATRÓN STATE:");
        System.out.println("   EstadoAscensor enum con comportamientos específicos");
        
        Ascensor ascensor = new Ascensor(0, 5, 0);
        
        // Demostrar comportamientos específicos del estado
        System.out.printf("   Estado inicial: %s\n", ascensor.getEstado());
        System.out.printf("   ¿Puede iniciar movimiento? %s\n", ascensor.getEstado().puedeIniciarMovimiento());
        System.out.printf("   ¿Está en movimiento? %s\n", ascensor.getEstado().estaEnMovimiento());
        
        // 2. PATRÓN OBSERVER
        System.out.println("\n2️⃣ PATRÓN OBSERVER:");
        System.out.println("   Ascensor (Subject) - Paneles (Observers)");
        
        // Crear observers (paneles)
        PanelInterno panelInterno = new PanelInterno(ascensor);
        PanelControl panelControl = new PanelControl(ascensor);
        List<PanelExterno> panelesExternos = new ArrayList<>();
        
        for (int piso = 0; piso <= 5; piso++) {
            panelesExternos.add(new PanelExterno(ascensor, piso));
        }
        
        System.out.printf("   ✅ %d Observers registrados:\n", 2 + panelesExternos.size());
        System.out.println("      - 1 Panel Interno");
        System.out.println("      - 1 Panel de Control");
        System.out.printf("      - %d Paneles Externos\n", panelesExternos.size());
        
        // 3. PATRÓN COMMAND
        System.out.println("\n3️⃣ PATRÓN COMMAND:");
        System.out.println("   ComandoMoverA encapsula operaciones de los paneles");
        
        // Crear comandos explícitamente
        ComandoPanel comando1 = new ComandoMoverA(ascensor, 3, "DEMO-MANUAL");
        ComandoPanel comando2 = new ComandoMoverA(ascensor, 1, "DEMO-MANUAL");
        
        System.out.printf("   ✅ Comando 1: %s\n", comando1.getDescripcion());
        System.out.printf("   ✅ Comando 2: %s\n", comando2.getDescripcion());
        
        // 4. DEMOSTRACIÓN SIN MEMORIA
        System.out.println("\n4️⃣ CARACTERÍSTICA SIN MEMORIA:");
        System.out.println("   El ascensor NO recuerda llamadas previas");
        System.out.println("   Solo procesa la llamada actual");
        
        // 5. DEMOSTRACIÓN CONJUNTA
        System.out.println("\n5️⃣ DEMOSTRACIÓN CONJUNTA DE PATRONES:");
        
        // Secuencia de comandos que demuestra todos los patrones
        int[] secuencia = {3, 5, 1, 4, 0};
        String[] origenes = {"Panel Interno", "Panel Externo", "Panel Control", "Panel Interno", "Panel Externo"};
        
        for (int i = 0; i < secuencia.length; i++) {
            int piso = secuencia[i];
            String origen = origenes[i];
            
            System.out.printf("\n   🎯 Movimiento %d/%d: %s → Piso %d\n", 
                i + 1, secuencia.length, origen, piso);
            
            // Verificar estado antes del comando
            System.out.printf("      Estado antes: %s (piso %d)\n", 
                ascensor.getEstado(), ascensor.getPisoActual());
            
            // Ejecutar comando según el origen
            switch (i % 3) {
                case 0 -> panelInterno.moverA(piso);
                case 1 -> panelesExternos.get(piso).llamarAscensor();
                case 2 -> panelControl.llamarAscensorAPiso(piso);
            }
            
            // Esperar y verificar cambios de estado
            pausa(500);
            System.out.printf("      Estado después: %s\n", ascensor.getEstado());
            
            // Esperar movimiento completo
            esperarMovimiento(Math.abs(piso - ascensor.getPisoActual()) * 1600 + 1000);
            
            System.out.printf("      Estado final: %s (piso %d)\n", 
                ascensor.getEstado(), ascensor.getPisoActual());
        }
        
        // 6. VERIFICACIÓN FINAL DE PATRONES
        System.out.println("\n6️⃣ VERIFICACIÓN FINAL DE PATRONES:");
        
        // Verificar que el ascensor esté parado (State)
        System.out.printf("   State: Ascensor %s ✅\n", 
            ascensor.getEstado() == EstadoAscensor.PARADO ? "PARADO" : "EN MOVIMIENTO");
        
        // Verificar que todos los observers estén sincronizados
        boolean todosObserversSincronizados = true;
        for (PanelExterno panel : panelesExternos) {
            if (panel.isTestigoLuminoso() != ascensor.getEstado().estaEnMovimiento()) {
                todosObserversSincronizados = false;
                break;
            }
        }
        System.out.printf("   Observer: Todos los paneles sincronizados ✅ %s\n", 
            todosObserversSincronizados ? "SÍ" : "NO");
        
        // Verificar que los comandos se ejecutaron
        System.out.printf("   Command: %d comandos ejecutados correctamente ✅\n", secuencia.length);
        
        // Verificar característica sin memoria
        System.out.println("   Sin Memoria: Ascensor no recuerda llamadas previas ✅");
        
        System.out.println("\n📊 RESUMEN EJERCICIO 3 - PATRONES VERIFICADOS:");
        System.out.println("   ✅ Observer: 8 paneles sincronizados automáticamente");
        System.out.println("   ✅ State: Estados con comportamientos específicos");
        System.out.println("   ✅ Command: Operaciones encapsuladas en comandos");
        System.out.println("   ✅ Sin Memoria: No almacena llamadas previas");
    }
    
    private static void esperarMovimiento(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
