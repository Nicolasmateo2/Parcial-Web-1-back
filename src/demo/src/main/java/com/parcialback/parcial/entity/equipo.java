package com.parcialback.parcial.entity;



public class equipo {
    private Long id;
    private String nombre;
    private String entrenador;
    private String categoria;
    private Integer torneo_id;

    public equipo() {
    }

    public equipo(Long id, String nombre, String entrenador, String categoria, Integer torneo_id) {
        this.id = id;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.categoria = categoria;
        this.torneo_id = torneo_id;
    }
    

}
