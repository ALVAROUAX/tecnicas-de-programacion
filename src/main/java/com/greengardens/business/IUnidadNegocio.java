package com.greengardens.business;

/**
 * Interface que define el contrato para unidades de negocio
 * Patrón Composite - Component
 */
public interface IUnidadNegocio {
    
    /**
     * Obtiene el nombre de la unidad
     * @return Nombre de la unidad
     */
    String getNombre();
    
    /**
     * Obtiene el número total de empleados (incluyendo subunidades)
     * @return Total de empleados
     */
    int obtenerNumeroTotalEmpleados();
    
    /**
     * Obtiene los beneficios totales (incluyendo subunidades)
     * @return Total de beneficios
     */
    double obtenerBeneficiosTotales();
    
    /**
     * Obtiene la inversión total (incluyendo subunidades)
     * @return Total de inversión
     */
    double obtenerInversionTotal();
    
    /**
     * Obtiene el número medio de contratos (promedio con subunidades)
     * @return Promedio de contratos
     */
    double obtenerNumeroMedioContratos();
    
    /**
     * Genera un reporte de la unidad
     * @param nivel Nivel de indentación
     * @return Reporte formateado
     */
    String generarReporte(int nivel);
}
