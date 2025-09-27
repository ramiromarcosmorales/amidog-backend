package com.marcos.amidogbackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PerroResponse {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 80, message = "El nombre no puede superar los 80 caracteres")
    private String nombre;

    @Min(value = 0, message = "La edad debe minima es 0")
    @Max(value = 30, message = "La edad maxima es 30")
    private int edad;

    @NotBlank(message = "La desripcion no puede estar vacia")
    @Size(max = 120, message = "La descripcion no puede superar los 120 caracteres")
    private String descripcion;

    public PerroResponse(Long id, String nombre, int edad, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}