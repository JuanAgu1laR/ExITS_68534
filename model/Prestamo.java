package com.example.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;
    
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;
    
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    // Constructores, getters y setters
    public Prestamo() {}
    
    public Prestamo(Persona persona, Libro libro) {
        this.persona = persona;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
}