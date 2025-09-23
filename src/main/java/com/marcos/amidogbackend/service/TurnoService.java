package com.marcos.amidogbackend.service;

import com.marcos.amidogbackend.model.Turno;
import com.marcos.amidogbackend.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    public Turno findById(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Turno update(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void delete(Long id) {
        turnoRepository.deleteById(id);
    }
}
