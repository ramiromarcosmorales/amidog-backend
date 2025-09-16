package com.marcos.amidogbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha/hora es obligatoria")
    @FutureOrPresent(message = "La fecha/hora debe ser actual o futura")
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    protected Turno() {}


    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return id != null && id.equals(turno.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
