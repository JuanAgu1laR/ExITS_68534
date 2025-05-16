package com.biblioteca.api_biblioteca.repository;

import com.biblioteca.api_biblioteca.model.Persona;
import com.biblioteca.api_biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByPersonaId(Long personaId);
}
