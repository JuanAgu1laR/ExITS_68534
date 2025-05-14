package com.example.biblioteca.repository;

import com.example.biblioteca.model.Persona;
import com.example.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByPersona(Persona persona);
    List<Prestamo> findByPersonaAndFechaDevolucionIsNull(Persona persona);
}