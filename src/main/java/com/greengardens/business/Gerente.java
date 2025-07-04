package com.greengardens.business;

import java.util.Objects;

/**
 * Clase que representa la información del gerente de una unidad de negocio
 */
public class Gerente {
    private final String nombre;
    private final String nif;
    private final String email;
    
    /**
     * Constructor para crear un gerente
     * @param nombre Nombre del gerente
     * @param nif NIF del gerente
     * @param email Email del gerente
     */
    public Gerente(String nombre, String nif, String email) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.nif = Objects.requireNonNull(nif, "El NIF no puede ser null");
        this.email = Objects.requireNonNull(email, "El email no puede ser null");
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getNif() {
        return nif;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return String.format("Gerente{nombre='%s', nif='%s', email='%s'}", 
                nombre, nif, email);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gerente gerente = (Gerente) o;
        return Objects.equals(nif, gerente.nif);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }
}
