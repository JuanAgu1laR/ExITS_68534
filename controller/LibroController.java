package com.example.biblioteca.controller;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroService.findAll();
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroService.save(libro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroService.findById(id);
        return libro.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libroOptional = libroService.findById(id);
        if (libroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Libro libro = libroOptional.get();
        libro.setTitulo(libroDetails.getTitulo());
        libro.setAutor(libroDetails.getAutor());
        
        Libro updatedLibro = libroService.save(libro);
        return ResponseEntity.ok(updatedLibro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}