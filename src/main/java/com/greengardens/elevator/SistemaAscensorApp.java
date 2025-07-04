package com.greengardens.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SistemaAscensorApp {
    private static final int PISO_MINIMO = 0;
    private static final int PISO_MAXIMO = 5;
    private static final int PISO_INICIAL = 0;
    
    public static void main(String[] args) {
        System.out.println("🏢 GREEN GARDEN - Ejercicio de Sistema de Ascensor");
        System.out.println("=================================================");
        System.out.println("📚 Trabajo Académico - Patrón Observer");
        System.out.println();
        
        // Crear ascensor
        Ascensor ascensor = new Ascensor(PISO_MINIMO, PISO_MAXIMO, PISO_INICIAL);
        
        // Crear paneles
        PanelInterno panelInterno = new PanelInterno(ascensor);
        PanelControl panelControl = new PanelControl(ascensor);
        
        // Crear paneles externos para cada piso
        List<PanelExterno> panelesExternos = new ArrayList<>();
        for (int piso = PISO_MINIMO; piso <= PISO_MAXIMO; piso++) {
            panelesExternos.add(new PanelExterno(ascensor, piso));
        }
        
        System.out.printf("✅ Sistema inicializado - Ascensor en piso %d\n", PISO_INICIAL);
        System.out.printf("📋 Pisos disponibles: %d al %d\n", PISO_MINIMO, PISO_MAXIMO);
        
        // Bucle principal de simulación
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;
        
        while (ejecutando) {
            mostrarMenu();
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                
                switch (opcion) {
                    case 1 -> usarPanelInterno(panelInterno, scanner);
                    case 2 -> usarPanelExterno(panelesExternos, scanner);
                    case 3 -> usarPanelControl(panelControl, scanner);
                    case 4 -> mostrarEstadoCompleto(panelInterno, panelControl, panelesExternos);
                    case 5 -> System.out.println(panelControl.generarReporte());
                    case 6 -> simularEscenarioDemo(panelInterno, panelControl, panelesExternos);
                    case 7 -> {
                        ejecutando = false;
                        System.out.println("🚪 Sistema finalizado. ¡Hasta luego!");
                    }
                    default -> System.out.println("❌ Opción no válida");
                }
                
                if (ejecutando) {
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                }
                
            } catch (Exception e) {
                System.err.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🎛️  MENÚ PRINCIPAL");
        System.out.println("=".repeat(40));
        System.out.println("1. 📱 Usar Panel Interno");
        System.out.println("2. 🏢 Usar Panel Externo");
        System.out.println("3. 🎛️ Usar Panel de Control");
        System.out.println("4. 📊 Ver Estado de Todos los Paneles");
        System.out.println("5. 📋 Generar Reporte del Sistema");
        System.out.println("6. 🎬 Ejecutar Simulación Demo");
        System.out.println("7. 🚪 Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void usarPanelInterno(PanelInterno panel, Scanner scanner) {
        System.out.println("\n📱 PANEL INTERNO");
        panel.mostrarEstado();
        System.out.print("Ingrese el piso de destino: ");
        
        try {
            int piso = scanner.nextInt();
            panel.moverA(piso);
            esperarMovimiento();
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void usarPanelExterno(List<PanelExterno> paneles, Scanner scanner) {
        System.out.println("\n🏢 PANELES EXTERNOS");
        System.out.println("Paneles disponibles:");
        
        for (PanelExterno panel : paneles) {
            System.out.printf("%d. Piso %d\n", panel.getPisoPanel(), panel.getPisoPanel());
        }
        
        System.out.print("Seleccione el piso desde donde llamar: ");
        
        try {
            int pisoPanel = scanner.nextInt();
            PanelExterno panelSeleccionado = paneles.stream()
                .filter(p -> p.getPisoPanel() == pisoPanel)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Piso no válido"));
            
            panelSeleccionado.mostrarEstado();
            panelSeleccionado.llamarAscensor();
            esperarMovimiento();
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void usarPanelControl(PanelControl panel, Scanner scanner) {
        System.out.println("\n🎛️ PANEL DE CONTROL");
        panel.mostrarEstado();
        System.out.print("Ingrese el piso de destino: ");
        
        try {
            int piso = scanner.nextInt();
            panel.llamarAscensorAPiso(piso);
            esperarMovimiento();
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void mostrarEstadoCompleto(PanelInterno panelInterno, PanelControl panelControl, 
                                           List<PanelExterno> panelesExternos) {
        System.out.println("\n📊 ESTADO COMPLETO DEL SISTEMA");
        System.out.println("=".repeat(50));
        
        panelInterno.mostrarEstado();
        System.out.println();
        
        panelControl.mostrarEstado();
        System.out.println();
        
        System.out.println("🏢 Paneles Externos:");
        for (PanelExterno panel : panelesExternos) {
            panel.mostrarEstado();
        }
    }
    
    private static void simularEscenarioDemo(PanelInterno panelInterno, PanelControl panelControl, 
                                           List<PanelExterno> panelesExternos) {
        System.out.println("\n🎬 EJECUTANDO SIMULACIÓN DEMO");
        System.out.println("=".repeat(40));
        
        try {
            System.out.println("1️⃣ Llamando ascensor desde piso 3...");
            panelesExternos.get(3).llamarAscensor();
            Thread.sleep(6000);
            
            System.out.println("\n2️⃣ Usando panel interno para ir al piso 5...");
            panelInterno.moverA(5);
            Thread.sleep(4000);
            
            System.out.println("\n3️⃣ Usando panel de control para ir al piso 1...");
            panelControl.llamarAscensorAPiso(1);
            Thread.sleep(7000);
            
            System.out.println("\n✅ Demo completada");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("❌ Demo interrumpida");
        }
    }
    
    private static void esperarMovimiento() {
        try {
            Thread.sleep(2000); // Esperar un poco para ver las notificaciones
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
