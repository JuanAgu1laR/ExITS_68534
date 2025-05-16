package com.biblioteca.api_biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correoElectronico;

    @OneToMany(mappedBy = "persona")
    private List<Prestamo> prestamos;
}