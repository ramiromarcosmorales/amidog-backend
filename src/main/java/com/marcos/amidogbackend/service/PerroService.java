package com.marcos.amidogbackend.service;

import com.marcos.amidogbackend.dto.PerroRequest;
import com.marcos.amidogbackend.dto.PerroResponse;
import com.marcos.amidogbackend.exception.NotFoundException;
import com.marcos.amidogbackend.model.Perro;
import com.marcos.amidogbackend.repository.PerroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerroService {
    private final PerroRepository perroRepository;

    public PerroService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    public List<PerroResponse> findAll() {
         return perroRepository.findAll().stream()
                 .map(this::mapToResponse)
                 .toList();
    }

    public PerroResponse findById(Long id) {
        Optional<Perro> p = perroRepository.findById(id);
        if (p.isEmpty()) throw new NotFoundException("Perro not found");

        return mapToResponse(p.get());
    }

    public PerroResponse save(PerroRequest dto) {
        Perro p = new Perro(dto.getNombre(), dto.getEdad(), dto.getDescripcion());
        p = perroRepository.save(p);

        return mapToResponse(p);
    }

    public PerroResponse update(Long id, PerroRequest dto) {
        Perro p = perroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Perro not found"));

        p.setNombre(dto.getNombre());
        p.setEdad(dto.getEdad());
        p.setDescripcion(dto.getDescripcion());

        Perro pUpdate = perroRepository.save(p);
        return mapToResponse(pUpdate);
    }

    public void delete(Long id) {
        if (!perroRepository.existsById(id)) throw new NotFoundException("Perro not found");

        perroRepository.deleteById(id);
    }

    public PerroResponse mapToResponse(Perro perro) {
        return new PerroResponse(perro.getId(), perro.getNombre(), perro.getEdad(), perro.getDescripcion());
    }
}
