package com.marcos.amidogbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PropietarioResponse {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 80)
    private String nombre;

    public PropietarioResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
