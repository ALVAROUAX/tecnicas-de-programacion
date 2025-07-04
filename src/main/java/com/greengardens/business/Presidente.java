package com.greengardens.business;

import java.util.Objects;

/**
 * Clase que representa la información del presidente de la empresa
 */
public class Presidente {
    private final String nombre;
    private final String nif;
    private final String direccionPostal;
    
    /**
     * Constructor para crear un presidente
     * @param nombre Nombre del presidente
     * @param nif NIF del presidente
     * @param direccionPostal Dirección postal del presidente
     */
    public Presidente(String nombre, String nif, String direccionPostal) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.nif = Objects.requireNonNull(nif, "El NIF no puede ser null");
        this.direccionPostal = Objects.requireNonNull(direccionPostal, "La dirección postal no puede ser null");
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getNif() {
        return nif;
    }
    
    public String getDireccionPostal() {
        return direccionPostal;
    }
    
    @Override
    public String toString() {
        return String.format("Presidente{nombre='%s', nif='%s', direccion='%s'}", 
                nombre, nif, direccionPostal);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presidente that = (Presidente) o;
        return Objects.equals(nif, that.nif);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }
}
