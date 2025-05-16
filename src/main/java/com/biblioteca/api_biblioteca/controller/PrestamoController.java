package com.biblioteca.api_biblioteca.controller;

import com.biblioteca.api_biblioteca.dto.PrestamoDTO;
import com.biblioteca.api_biblioteca.model.Libro;
import com.biblioteca.api_biblioteca.model.Prestamo;
import com.biblioteca.api_biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<List<Prestamo>> crearPrestamos(@RequestBody PrestamoDTO prestamoDTO) {
        List<Prestamo> prestamosCreados = prestamoService.crearPrestamos(prestamoDTO);

        if (prestamosCreados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(prestamosCreados, HttpStatus.CREATED);
    }

    @GetMapping("/personas/{id}/prestamos")
    public ResponseEntity<List<Libro>> obtenerLibrosPrestadosPorPersonaId(@PathVariable Long id) {
        List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorPersonaId(id);

        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Libro> libros = prestamos.stream()
                .map(Prestamo::getLibro)
                .collect(Collectors.toList());

        return ResponseEntity.ok(libros);
    }
}