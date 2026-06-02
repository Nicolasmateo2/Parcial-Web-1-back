package com.parcialback.parcial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El entrenador es obligatorio")
    @Size(max = 100, message = "El entrenador no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String entrenador;

    @NotBlank(message = "La categoria es obligatoria")
    @Size(max = 50, message = "La categoria no puede exceder 50 caracteres")
    @Column(nullable = false, length = 50)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    @JsonIgnore
    private Torneo torneo;
}
