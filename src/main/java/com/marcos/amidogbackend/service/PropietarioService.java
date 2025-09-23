package com.marcos.amidogbackend.service;

import com.marcos.amidogbackend.dto.PropietarioRequest;
import com.marcos.amidogbackend.dto.PropietarioResponse;
import com.marcos.amidogbackend.exception.NotFoundException;
import com.marcos.amidogbackend.model.Propietario;
import com.marcos.amidogbackend.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {
    private final PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public List<PropietarioResponse> findAll() {
        return propietarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PropietarioResponse findById(Long id) {
        Optional<Propietario> p = propietarioRepository.findById(id);
        if (p.isEmpty()) throw new NotFoundException("Propietario not found");

        return mapToResponse(p.get());
    }

    public PropietarioResponse save(PropietarioRequest dto) {
        Propietario p = new Propietario(dto.getNombre());
        p = propietarioRepository.save(p);
        return mapToResponse(p);
    }

    public PropietarioResponse update(Long id, PropietarioRequest dto) {
        Propietario p = propietarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Propietario no encontrado!"));

        p.setNombre(dto.getNombre());

        Propietario pUpdated = propietarioRepository.save(p);
        return mapToResponse(pUpdated);
    }

    public void delete(Long id) {
        if (!propietarioRepository.existsById(id)) throw new NotFoundException("Propietario no encontrado!");

        propietarioRepository.deleteById(id);
    }

    public PropietarioResponse mapToResponse(Propietario propietario) {
        return new PropietarioResponse(propietario.getId(), propietario.getNombre());
    }
}
