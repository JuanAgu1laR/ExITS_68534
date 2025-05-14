package com.example.biblioteca.controller;

import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public List<Prestamo> createPrestamo(@RequestBody PrestamoRequest request) {
        return prestamoService.prestarLibros(request.getPersonaId(), request.getLibrosIds());
    }

    @GetMapping("/personas/{id}/prestamos")
    public List<Prestamo> getPrestamosActivosByPersona(@PathVariable Long id) {
        return prestamoService.findPrestamosActivosByPersona(id);
    }

    // Clase interna para manejar el request
    public static class PrestamoRequest {
        private Long personaId;
        private List<Long> librosIds;

        // Getters y setters
        public Long getPersonaId() { return personaId; }
        public void setPersonaId(Long personaId) { this.personaId = personaId; }
        public List<Long> getLibrosIds() { return librosIds; }
        public void setLibrosIds(List<Long> librosIds) { this.librosIds = librosIds; }
    }
}