package com.greengardens.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa la empresa con su estructura organizacional
 * Actúa como el cliente del patrón Composite
 */
public class Empresa {
    private final String nombre;
    private final Presidente presidente;
    private final List<IUnidadNegocio> unidadesDeNegocio;
    
    /**
     * Constructor para crear una empresa
     */
    public Empresa(String nombre, Presidente presidente) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre de la empresa no puede ser null");
        this.presidente = Objects.requireNonNull(presidente, "El presidente no puede ser null");
        this.unidadesDeNegocio = new ArrayList<>();
    }
    
    /**
     * Agrega una unidad de negocio a la empresa
     */
    public void agregarUnidadDeNegocio(IUnidadNegocio unidadDeNegocio) {
        Objects.requireNonNull(unidadDeNegocio, "La unidad de negocio no puede ser null");
        
        if (unidadesDeNegocio.contains(unidadDeNegocio)) {
            throw new IllegalArgumentException("La unidad de negocio ya existe: " + unidadDeNegocio.getNombre());
        }
        
        unidadesDeNegocio.add(unidadDeNegocio);
    }
    
    /**
     * Remueve una unidad de negocio de la empresa
     */
    public boolean removerUnidadDeNegocio(IUnidadNegocio unidadDeNegocio) {
        return unidadesDeNegocio.remove(unidadDeNegocio);
    }
    
    /**
     * Calcula el número total de empleados de toda la empresa
     * Utiliza el patrón Composite - delega a las unidades de negocio
     */
    public int obtenerNumeroTotalEmpleados() {
        return unidadesDeNegocio.stream()
                .mapToInt(IUnidadNegocio::obtenerNumeroTotalEmpleados)
                .sum();
    }
    
    /**
     * Calcula los beneficios totales de toda la empresa
     * Utiliza el patrón Composite - delega a las unidades de negocio
     */
    public double obtenerBeneficiosTotales() {
        return unidadesDeNegocio.stream()
                .mapToDouble(IUnidadNegocio::obtenerBeneficiosTotales)
                .sum();
    }
    
    /**
     * Calcula la inversión total de toda la empresa
     * Utiliza el patrón Composite - delega a las unidades de negocio
     */
    public double obtenerInversionTotal() {
        return unidadesDeNegocio.stream()
                .mapToDouble(IUnidadNegocio::obtenerInversionTotal)
                .sum();
    }
    
    /**
     * Calcula el número medio de contratos de toda la empresa
     * Utiliza el patrón Composite - delega a las unidades de negocio
     */
    public double obtenerNumeroMedioContratos() {
        if (unidadesDeNegocio.isEmpty()) {
            return 0.0;
        }
        
        double sumaContratos = unidadesDeNegocio.stream()
                .mapToDouble(IUnidadNegocio::obtenerNumeroMedioContratos)
                .sum();
        
        return sumaContratos / unidadesDeNegocio.size();
    }
    
    /**
     * Genera un reporte completo de la empresa
     */
    public String generarReporteCompleto() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("🏢 ").append(nombre.toUpperCase()).append(" - REPORTE ORGANIZACIONAL\n");
        reporte.append("=".repeat(50)).append("\n\n");
        
        // Información del presidente
        reporte.append("👤 PRESIDENTE:\n");
        reporte.append("   Nombre: ").append(presidente.getNombre()).append("\n");
        reporte.append("   NIF: ").append(presidente.getNif()).append("\n");
        reporte.append("   Dirección: ").append(presidente.getDireccionPostal()).append("\n\n");
        
        // Resumen ejecutivo
        reporte.append("📈 RESUMEN EJECUTIVO:\n");
        reporte.append(String.format("   Total empleados: %d\n", obtenerNumeroTotalEmpleados()));
        reporte.append(String.format("   Beneficios totales: €%.2f\n", obtenerBeneficiosTotales()));
        reporte.append(String.format("   Inversión total: €%.2f\n", obtenerInversionTotal()));
        reporte.append(String.format("   Contratos promedio: %.2f/semana\n", obtenerNumeroMedioContratos()));
        reporte.append(String.format("   Unidades de negocio: %d\n\n", unidadesDeNegocio.size()));
        
        // Detalle de unidades de negocio usando el patrón Composite
        if (!unidadesDeNegocio.isEmpty()) {
            reporte.append("🏬 UNIDADES DE NEGOCIO:\n");
            for (IUnidadNegocio unidad : unidadesDeNegocio) {
                reporte.append(unidad.generarReporte(0));
                reporte.append("\n");
            }
        } else {
            reporte.append("No hay unidades de negocio registradas.\n");
        }
        
        return reporte.toString();
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public Presidente getPresidente() {
        return presidente;
    }
    
    public List<IUnidadNegocio> getUnidadesDeNegocio() {
        return List.copyOf(unidadesDeNegocio);
    }
    
    @Override
    public String toString() {
        return String.format("Empresa{nombre='%s', presidente='%s', unidades=%d}", 
                nombre, presidente.getNombre(), unidadesDeNegocio.size());
    }
}
