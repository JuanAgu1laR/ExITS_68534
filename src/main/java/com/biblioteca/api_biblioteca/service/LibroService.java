package com.biblioteca.api_biblioteca.service;

import com.biblioteca.api_biblioteca.dto.LibroDTO;
import com.biblioteca.api_biblioteca.model.Libro;
import com.biblioteca.api_biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro crearLibro(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        return libroRepository.save(libro);
    }

    public Optional<Libro> actualizarLibro(Long id, LibroDTO libroDTO) {
        Optional<Libro> libroOptional = libroRepository.findById(id);

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            libro.setTitulo(libroDTO.getTitulo());
            libro.setAutor(libroDTO.getAutor());
            return Optional.of(libroRepository.save(libro));
        }

        return Optional.empty();
    }

    public boolean eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}