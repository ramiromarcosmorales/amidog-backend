package com.marcos.amidogbackend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PropietarioRequest {
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nombre;

    public PropietarioRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
