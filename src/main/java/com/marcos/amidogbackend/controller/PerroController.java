package com.marcos.amidogbackend.controller;

import com.marcos.amidogbackend.dto.PerroRequest;
import com.marcos.amidogbackend.dto.PerroResponse;
import com.marcos.amidogbackend.service.PerroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perros")
public class PerroController {
    private final PerroService service;

    public PerroController(PerroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PerroResponse>> getPerros() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerroResponse> getPerro(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PerroResponse> createPerro(@Valid @RequestBody PerroRequest perro) {
        PerroResponse p = service.save(perro);
        URI location = URI.create("/perros/" + p.getId());

        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerroResponse> updatePerro(@PathVariable Long id, @Valid @RequestBody PerroRequest perro) {
        PerroResponse p = service.update(id, perro);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerro(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
