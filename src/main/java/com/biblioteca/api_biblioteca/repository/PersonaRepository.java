package com.biblioteca.api_biblioteca.repository;

import com.biblioteca.api_biblioteca.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}