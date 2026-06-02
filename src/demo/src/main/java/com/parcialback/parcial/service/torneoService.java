package com.parcialback.parcial.service;

import com.parcialback.parcial.entity.Torneo;
import com.parcialback.parcial.exception.RecursoNoEncontradoException;
import com.parcialback.parcial.exception.ValidacionException;
import java.time.LocalDate;
import java.util.List;

public interface TorneoService {

    List<Torneo> listarTodos();

    Torneo obtenerPorId(Long id) throws RecursoNoEncontradoException;

    Torneo crear(Torneo torneo) throws ValidacionException;

    Torneo actualizar(Long id, Torneo torneo) throws RecursoNoEncontradoException, ValidacionException;

    void eliminar(Long id) throws RecursoNoEncontradoException;

    List<Torneo> buscarPorDeporte(String deporte);

    List<Torneo> buscarPorCiudad(String ciudad);

    List<Long> obtenerIdsEquiposOrdenados(Long torneoId);
}
