package com.greengardens.business;

public class GestionEmpresarialApp {
    
    public static void main(String[] args) {
        System.out.println("🌱 GREEN GARDEN - Ejercicio de Gestión Empresarial");
        System.out.println("================================================");
        System.out.println("📚 Trabajo Académico - Patrón Composite");
        System.out.println();
        
        // Crear presidente
        Presidente presidente = new Presidente(
            "María González Rodríguez", 
            "12345678A", 
            "Av. Jardines Verdes 123, 28001 Madrid"
        );
        
        // Crear empresa
        Empresa greenGarden = new Empresa("Green Garden S.A.", presidente);
        
        // Crear gerentes
        Gerente gerenteVentas = new Gerente("Carlos Martín", "23456789B", "carlos.martin@greengardens.com");
        Gerente gerenteProduccion = new Gerente("Ana López", "34567890C", "ana.lopez@greengardens.com");
        Gerente gerenteLogistica = new Gerente("Pedro Sánchez", "45678901D", "pedro.sanchez@greengardens.com");
        Gerente gerenteVentasOnline = new Gerente("Laura García", "56789012E", "laura.garcia@greengardens.com");
        Gerente gerenteVentasFisicas = new Gerente("Miguel Torres", "67890123F", "miguel.torres@greengardens.com");
        
        // Crear unidades de negocio principales
        UnidadDeNegocio unidadVentas = new UnidadDeNegocio(
            "Ventas", gerenteVentas, 15, 250000.0, 50000.0, 45.5
        );
        
        UnidadDeNegocio unidadProduccion = new UnidadDeNegocio(
            "Producción", gerenteProduccion, 35, 180000.0, 150000.0, 12.3
        );
        
        UnidadDeNegocio unidadLogistica = new UnidadDeNegocio(
            "Logística", gerenteLogistica, 20, 120000.0, 80000.0, 28.7
        );
        
        // Crear subunidades de ventas
        UnidadDeNegocio ventasOnline = new UnidadDeNegocio(
            "Ventas Online", gerenteVentasOnline, 8, 150000.0, 25000.0, 52.1
        );
        
        UnidadDeNegocio ventasFisicas = new UnidadDeNegocio(
            "Ventas Físicas", gerenteVentasFisicas, 12, 100000.0, 35000.0, 38.9
        );
        
        // Establecer jerarquía
        unidadVentas.agregarSubUnidadDeNegocio(ventasOnline);
        unidadVentas.agregarSubUnidadDeNegocio(ventasFisicas);
        
        // Agregar unidades principales a la empresa
        greenGarden.agregarUnidadDeNegocio(unidadVentas);
        greenGarden.agregarUnidadDeNegocio(unidadProduccion);
        greenGarden.agregarUnidadDeNegocio(unidadLogistica);
        
        // Mostrar reporte completo
        System.out.println(greenGarden.generarReporteCompleto());
        
        // Demostrar cálculos específicos
        System.out.println("🔍 ANÁLISIS DETALLADO:");
        System.out.println("=====================");
        
        System.out.println("\n📊 Unidad de Ventas (con subunidades):");
        System.out.printf("   Empleados directos: %d\n", unidadVentas.getNumeroEmpleados());
        System.out.printf("   Empleados totales: %d\n", unidadVentas.obtenerNumeroTotalEmpleados());
        System.out.printf("   Beneficios directos: €%.2f\n", unidadVentas.getBeneficiosUltimoTrimestre());
        System.out.printf("   Beneficios totales: €%.2f\n", unidadVentas.obtenerBeneficiosTotales());
        System.out.printf("   Contratos promedio: %.2f/semana\n", unidadVentas.obtenerNumeroMedioContratos());
        
        System.out.println("\n🏢 Totales de la Empresa:");
        System.out.printf("   Total empleados: %d\n", greenGarden.obtenerNumeroTotalEmpleados());
        System.out.printf("   Total beneficios: €%.2f\n", greenGarden.obtenerBeneficiosTotales());
        System.out.printf("   Total inversión: €%.2f\n", greenGarden.obtenerInversionTotal());
        System.out.printf("   Promedio contratos: %.2f/semana\n", greenGarden.obtenerNumeroMedioContratos());
    }
}
