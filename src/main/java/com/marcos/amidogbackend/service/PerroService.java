package com.marcos.amidogbackend.service;

import com.marcos.amidogbackend.model.Perro;
import com.marcos.amidogbackend.repository.PerroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerroService {
    private final PerroRepository perroRepository;

    public PerroService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    public List<Perro> getPerros() {
        return perroRepository.findAll();
    }

    public Perro getPerroById(Long id) {
        return perroRepository.findById(id).orElse(null);
    }

    public Perro save(Perro perro) {
        return perroRepository.save(perro);
    }

    public Perro update(Perro perro) {
        return perroRepository.save(perro);
    }

    public void delete(Long id) {
        perroRepository.deleteById(id);
    }
}
