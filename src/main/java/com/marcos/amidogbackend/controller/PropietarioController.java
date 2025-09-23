package com.marcos.amidogbackend.controller;

import com.marcos.amidogbackend.dto.PropietarioRequest;
import com.marcos.amidogbackend.dto.PropietarioResponse;
import com.marcos.amidogbackend.model.Propietario;
import com.marcos.amidogbackend.service.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/propietarios")
public class PropietarioController {
    private final PropietarioService service;

    public PropietarioController(PropietarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PropietarioResponse>> getPropietarios() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioResponse> getPropietario(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PropietarioResponse> createPropietario(@Valid @RequestBody PropietarioRequest prop) {
        PropietarioResponse p = service.save(prop);
        URI location = URI.create("/propietarios/" + p.getId());
        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioResponse> updatePropietario(@PathVariable Long id, @Valid @RequestBody PropietarioRequest prop) {
        PropietarioResponse p = service.update(id, prop);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropietario(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
