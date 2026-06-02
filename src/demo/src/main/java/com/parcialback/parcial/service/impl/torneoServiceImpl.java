package com.parcialback.parcial.service.impl;

import com.parcialback.parcial.entity.Torneo;
import com.parcialback.parcial.exception.RecursoNoEncontradoException;
import com.parcialback.parcial.exception.ValidacionException;
import com.parcialback.parcial.repository.EquipoRepository;
import com.parcialback.parcial.repository.TorneoRepository;
import com.parcialback.parcial.service.TorneoService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TorneoServiceImpl implements TorneoService {

    private static final int DIAS_MINIMOS_DURACION = 1;

    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;

    @Override
    public List<Torneo> listarTodos() {
        return torneoRepository.findAll();
    }

    @Override
    public Torneo obtenerPorId(Long id) throws RecursoNoEncontradoException {
        return torneoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Torneo", id));
    }

    @Override
    public Torneo crear(Torneo torneo) throws ValidacionException {
        validarCamposObligatorios(torneo);
        validarReglasNegocio(torneo, null);
        return torneoRepository.save(torneo);
    }

    @Override
    public Torneo actualizar(Long id, Torneo torneo) throws RecursoNoEncontradoException, ValidacionException {
        Torneo existente = obtenerPorId(id);
        validarCamposObligatorios(torneo);
        validarReglasNegocio(torneo, id);
        existente.setNombre(torneo.getNombre());
        existente.setDeporte(torneo.getDeporte());
        existente.setFechaInicio(torneo.getFechaInicio());
        existente.setCiudad(torneo.getCiudad());
        return torneoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws RecursoNoEncontradoException {
        Torneo existente = obtenerPorId(id);
        torneoRepository.delete(existente);
    }

    @Override
    public List<Torneo> buscarPorDeporte(String deporte) {
        return torneoRepository.findByDeporte(deporte);
    }

    @Override
    public List<Torneo> buscarPorCiudad(String ciudad) {
        return torneoRepository.findByCiudad(ciudad);
    }

    @Override
    public List<Long> obtenerIdsEquiposOrdenados(Long torneoId) {
        return equipoRepository.findByTorneoId(torneoId).stream()
                .map(equipo -> equipo.getId())
                .sorted()
                .toList();
    }

    private void validarCamposObligatorios(Torneo torneo) throws ValidacionException {
        if (torneo.getNombre() == null || torneo.getNombre().trim().isBlank()) {
            throw new ValidacionException("El nombre del torneo es obligatorio");
        }
        if (torneo.getDeporte() == null || torneo.getDeporte().trim().isBlank()) {
            throw new ValidacionException("El deporte es obligatorio");
        }
        if (torneo.getFechaInicio() == null) {
            throw new ValidacionException("La fecha de inicio es obligatoria");
        }
        if (torneo.getCiudad() == null || torneo.getCiudad().trim().isBlank()) {
            throw new ValidacionException("La ciudad es obligatoria");
        }
    }

    private void validarReglasNegocio(Torneo torneo, Long idActual) throws ValidacionException {
        if (torneo.getFechaInicio().isBefore(LocalDate.now().plusDays(DIAS_MINIMOS_DURACION))) {
            throw new ValidacionException("La fecha de inicio debe ser al menos un dia despues de hoy");
        }
        if (torneo.getNombre() != null && torneo.getNombre().length() < 2) {
            throw new ValidacionException("El nombre debe tener al menos 2 caracteres");
        }
    }
}
