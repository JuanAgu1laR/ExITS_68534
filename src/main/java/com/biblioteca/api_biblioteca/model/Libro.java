package com.biblioteca.api_biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo> prestamos;
}