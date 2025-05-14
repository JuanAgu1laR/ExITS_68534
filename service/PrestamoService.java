package com.example.biblioteca.service;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Persona;
import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.repository.PersonaRepository;
import com.example.biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public List<Prestamo> prestarLibros(Long personaId, List<Long> librosIds) {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        
        List<Libro> libros = libroRepository.findAllById(librosIds);
        if (libros.size() != librosIds.size()) {
            throw new RuntimeException("Algunos libros no fueron encontrados");
        }
        
        return libros.stream()
            .map(libro -> {
                Prestamo prestamo = new Prestamo(persona, libro);
                return prestamoRepository.save(prestamo);
            })
            .toList();
    }

    public List<Prestamo> findPrestamosActivosByPersona(Long personaId) {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return prestamoRepository.findByPersonaAndFechaDevolucionIsNull(persona);
    }
}