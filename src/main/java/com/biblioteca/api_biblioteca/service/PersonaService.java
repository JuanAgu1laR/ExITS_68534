package com.biblioteca.api_biblioteca.service;

import com.biblioteca.api_biblioteca.dto.PersonaDTO;
import com.biblioteca.api_biblioteca.model.Persona;
import com.biblioteca.api_biblioteca.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Persona crearPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setNombre(personaDTO.getNombre());
        persona.setCorreoElectronico(personaDTO.getCorreoElectronico());
        return personaRepository.save(persona);
    }

    public Optional<Persona> actualizarPersona(Long id, PersonaDTO personaDTO) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            persona.setNombre(personaDTO.getNombre());
            persona.setCorreoElectronico(personaDTO.getCorreoElectronico());
            return Optional.of(personaRepository.save(persona));
        }

        return Optional.empty();
    }

    public boolean eliminarPersona(Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}