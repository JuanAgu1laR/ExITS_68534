package com.example.biblioteca.controller;

import com.example.biblioteca.model.Persona;
import com.example.biblioteca.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        return persona.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Optional<Persona> personaOptional = personaService.findById(id);
        if (personaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Persona persona = personaOptional.get();
        persona.setNombre(personaDetails.getNombre());
        persona.setEmail(personaDetails.getEmail());
        
        Persona updatedPersona = personaService.save(persona);
        return ResponseEntity.ok(updatedPersona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}