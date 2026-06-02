package com.parcialback.parcial.entity;




public class torneo {
  
    private Long id;
    private String nombre;
    private String deporte;
    private String fechaInicio;
    private String ciudad;

    public torneo() {
    }

    public torneo(Long id, String nombre, String deporte, String fechaInicio, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.deporte = deporte;
        this.fechaInicio = fechaInicio;
        this.ciudad = ciudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
