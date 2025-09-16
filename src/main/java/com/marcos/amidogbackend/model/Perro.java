package com.marcos.amidogbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "perro")
public class Perro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 80, message = "El nombre no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String nombre;

    @Min(value = 0, message = "La edad debe minima es 0")
    @Max(value = 30, message = "La edad maxima es 30")
    @Column(nullable = false)
    private int edad;

    @NotBlank(message = "La desripcion no puede estar vacia")
    @Size(max = 120, message = "La descripcion no puede superar los 120 caracteres")
    @Column(nullable = false, length = 120)
    private String descripcion;

    protected Perro() {}

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perro perro = (Perro) o;
        return id != null && id.equals(perro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
