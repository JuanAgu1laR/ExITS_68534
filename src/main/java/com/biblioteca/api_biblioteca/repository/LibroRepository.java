package com.biblioteca.api_biblioteca.repository;

import com.biblioteca.api_biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}