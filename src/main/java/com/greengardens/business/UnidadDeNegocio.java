package com.greengardens.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una unidad de negocio que puede contener subunidades.
 * Patrón Composite - Composite
 * Implementa Template Method para los cálculos agregados
 */
public class UnidadDeNegocio implements IUnidadNegocio {
    private final String nombre;
    private final Gerente gerente;
    private final int numeroEmpleados;
    private final double beneficiosUltimoTrimestre;
    private final double inversionEdificios;
    private final double numeroMedioContratos;
    private final List<IUnidadNegocio> subUnidades;
    
    /**
     * Constructor para crear una unidad de negocio
     */
    public UnidadDeNegocio(String nombre, Gerente gerente, int numeroEmpleados, 
                          double beneficiosUltimoTrimestre, double inversionEdificios, 
                          double numeroMedioContratos) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.gerente = Objects.requireNonNull(gerente, "El gerente no puede ser null");
        this.numeroEmpleados = validarNumeroPositivo(numeroEmpleados, "número de empleados");
        this.beneficiosUltimoTrimestre = beneficiosUltimoTrimestre;
        this.inversionEdificios = validarNumeroPositivo(inversionEdificios, "inversión en edificios");
        this.numeroMedioContratos = validarNumeroPositivo(numeroMedioContratos, "número medio de contratos");
        this.subUnidades = new ArrayList<>();
    }
    
    /**
     * Agrega una subunidad de negocio
     * Patrón Composite - Operación para manejar hijos
     */
    public void agregarSubUnidadDeNegocio(IUnidadNegocio subUnidad) {
        Objects.requireNonNull(subUnidad, "La subunidad no puede ser null");
        
        if (subUnidad == this) {
            throw new IllegalArgumentException("Una unidad no puede contenerse a sí misma");
        }
        
        if (subUnidades.contains(subUnidad)) {
            throw new IllegalArgumentException("La subunidad ya existe: " + subUnidad.getNombre());
        }
        
        subUnidades.add(subUnidad);
    }
    
    /**
     * Remueve una subunidad de negocio
     * Patrón Composite - Operación para manejar hijos
     */
    public boolean removerSubUnidadDeNegocio(IUnidadNegocio subUnidad) {
        return subUnidades.remove(subUnidad);
    }
    
    /**
     * Template Method para calcular empleados totales
     * Patrón Template Method - Algoritmo que suma empleados propios + subunidades
     */
    @Override
    public int obtenerNumeroTotalEmpleados() {
        int totalEmpleados = obtenerEmpleadosDirectos();
        
        for (IUnidadNegocio subUnidad : subUnidades) {
            totalEmpleados += subUnidad.obtenerNumeroTotalEmpleados();
        }
        
        return totalEmpleados;
    }
    
    /**
     * Template Method para calcular beneficios totales
     * Patrón Template Method - Algoritmo que suma beneficios propios + subunidades
     */
    @Override
    public double obtenerBeneficiosTotales() {
        double totalBeneficios = obtenerBeneficiosDirectos();
        
        for (IUnidadNegocio subUnidad : subUnidades) {
            totalBeneficios += subUnidad.obtenerBeneficiosTotales();
        }
        
        return totalBeneficios;
    }
    
    /**
     * Template Method para calcular inversión total
     * Patrón Template Method - Algoritmo que suma inversiones propias + subunidades
     */
    @Override
    public double obtenerInversionTotal() {
        double totalInversion = obtenerInversionDirecta();
        
        for (IUnidadNegocio subUnidad : subUnidades) {
            totalInversion += subUnidad.obtenerInversionTotal();
        }
        
        return totalInversion;
    }
    
    /**
     * Template Method para calcular número medio de contratos
     * Patrón Template Method - Algoritmo que promedia contratos de todas las unidades
     */
    @Override
    public double obtenerNumeroMedioContratos() {
        if (subUnidades.isEmpty()) {
            return obtenerContratosDirectos();
        }
        
        double sumaContratos = obtenerContratosDirectos();
        int totalUnidades = 1;
        
        for (IUnidadNegocio subUnidad : subUnidades) {
            sumaContratos += subUnidad.obtenerNumeroMedioContratos() * contarUnidadesTotales(subUnidad);
            totalUnidades += contarUnidadesTotales(subUnidad);
        }
        
        return sumaContratos / totalUnidades;
    }
    
    /**
     * Métodos primitivos del Template Method - pueden ser sobrescritos por subclases
     */
    protected int obtenerEmpleadosDirectos() {
        return numeroEmpleados;
    }
    
    protected double obtenerBeneficiosDirectos() {
        return beneficiosUltimoTrimestre;
    }
    
    protected double obtenerInversionDirecta() {
        return inversionEdificios;
    }
    
    protected double obtenerContratosDirectos() {
        return numeroMedioContratos;
    }
    
    /**
     * Cuenta el número total de unidades recursivamente
     */
    private int contarUnidadesTotales(IUnidadNegocio unidad) {
        int total = 1;
        if (unidad instanceof UnidadDeNegocio unidadConcreta) {
            for (IUnidadNegocio subUnidad : unidadConcreta.subUnidades) {
                total += contarUnidadesTotales(subUnidad);
            }
        }
        return total;
    }
    
    /**
     * Genera un reporte detallado de la unidad y sus subunidades
     * Patrón Composite - Operación que se propaga a los hijos
     */
    @Override
    public String generarReporte(int nivel) {
        StringBuilder reporte = new StringBuilder();
        String indentacion = "  ".repeat(nivel);
        
        reporte.append(String.format("%s📊 %s\n", indentacion, nombre));
        reporte.append(String.format("%s   Gerente: %s\n", indentacion, gerente.getNombre()));
        reporte.append(String.format("%s   Empleados directos: %d\n", indentacion, numeroEmpleados));
        reporte.append(String.format("%s   Empleados totales: %d\n", indentacion, obtenerNumeroTotalEmpleados()));
        reporte.append(String.format("%s   Beneficios directos: €%.2f\n", indentacion, beneficiosUltimoTrimestre));
        reporte.append(String.format("%s   Beneficios totales: €%.2f\n", indentacion, obtenerBeneficiosTotales()));
        reporte.append(String.format("%s   Inversión directa: €%.2f\n", indentacion, inversionEdificios));
        reporte.append(String.format("%s   Inversión total: €%.2f\n", indentacion, obtenerInversionTotal()));
        reporte.append(String.format("%s   Contratos promedio: %.2f/semana\n", indentacion, obtenerNumeroMedioContratos()));
        
        if (!subUnidades.isEmpty()) {
            reporte.append(String.format("%s   Subunidades:\n", indentacion));
            for (IUnidadNegocio subUnidad : subUnidades) {
                reporte.append(subUnidad.generarReporte(nivel + 2));
            }
        }
        
        return reporte.toString();
    }
    
    /**
     * Valida que un número sea positivo
     */
    private int validarNumeroPositivo(int numero, String campo) {
        if (numero < 0) {
            throw new IllegalArgumentException("El " + campo + " no puede ser negativo: " + numero);
        }
        return numero;
    }
    
    private double validarNumeroPositivo(double numero, String campo) {
        if (numero < 0) {
            throw new IllegalArgumentException("El " + campo + " no puede ser negativo: " + numero);
        }
        return numero;
    }
    
    // Getters
    @Override
    public String getNombre() {
        return nombre;
    }
    
    public Gerente getGerente() {
        return gerente;
    }
    
    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }
    
    public double getBeneficiosUltimoTrimestre() {
        return beneficiosUltimoTrimestre;
    }
    
    public double getInversionEdificios() {
        return inversionEdificios;
    }
    
    public double getNumeroMedioContratosDirecto() {
        return numeroMedioContratos;
    }
    
    public List<IUnidadNegocio> getSubUnidades() {
        return List.copyOf(subUnidades);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadDeNegocio that = (UnidadDeNegocio) o;
        return Objects.equals(nombre, that.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    
    @Override
    public String toString() {
        return String.format("UnidadDeNegocio{nombre='%s', empleados=%d, subunidades=%d}", 
                nombre, numeroEmpleados, subUnidades.size());
    }
}
