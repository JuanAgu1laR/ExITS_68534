package com.biblioteca.api_biblioteca.controller;

import com.biblioteca.api_biblioteca.dto.PersonaDTO;
import com.biblioteca.api_biblioteca.model.Persona;
import com.biblioteca.api_biblioteca.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody PersonaDTO personaDTO) {
        Persona nuevaPersona = personaService.crearPersona(personaDTO);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        Optional<Persona> persona = personaService.obtenerPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        Optional<Persona> personaActualizada = personaService.actualizarPersona(id, personaDTO);
        return personaActualizada.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        if (personaService.eliminarPersona(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}