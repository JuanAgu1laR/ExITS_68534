package com.biblioteca.api_biblioteca.service;

import com.biblioteca.api_biblioteca.dto.PrestamoDTO;
import com.biblioteca.api_biblioteca.model.Libro;
import com.biblioteca.api_biblioteca.model.Persona;
import com.biblioteca.api_biblioteca.model.Prestamo;
import com.biblioteca.api_biblioteca.repository.LibroRepository;
import com.biblioteca.api_biblioteca.repository.PersonaRepository;
import com.biblioteca.api_biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private LibroRepository libroRepository;

    public List<Prestamo> crearPrestamos(PrestamoDTO prestamoDTO) {
        List<Prestamo> prestamosCreados = new ArrayList<>();
        Optional<Persona> personaOptional = personaRepository.findById(prestamoDTO.getPersonaId());

        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();

            for (Long libroId : prestamoDTO.getLibrosIds()) {
                Optional<Libro> libroOptional = libroRepository.findById(libroId);

                if (libroOptional.isPresent()) {
                    Libro libro = libroOptional.get();

                    Prestamo prestamo = new Prestamo();
                    prestamo.setPersona(persona);
                    prestamo.setLibro(libro);
                    prestamo.setFechaPrestamo(LocalDate.now());

                    prestamosCreados.add(prestamoRepository.save(prestamo));
                }
            }
        }

        return prestamosCreados;
    }

    public List<Prestamo> obtenerPrestamosPorPersonaId(Long personaId) {
        return prestamoRepository.findByPersonaId(personaId);
    }
}