package com.biblioteca.api_biblioteca.controller;

import com.biblioteca.api_biblioteca.dto.LibroDTO;
import com.biblioteca.api_biblioteca.model.Libro;
import com.biblioteca.api_biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody LibroDTO libroDTO) {
        Libro nuevoLibro = libroService.crearLibro(libroDTO);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroService.obtenerPorId(id);
        return libro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        Optional<Libro> libroActualizado = libroService.actualizarLibro(id, libroDTO);
        return libroActualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (libroService.eliminarLibro(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}