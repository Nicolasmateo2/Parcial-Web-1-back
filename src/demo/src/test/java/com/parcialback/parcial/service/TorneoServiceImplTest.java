package com.parcialback.parcial.service;

import com.parcialback.parcial.entity.Torneo;
import com.parcialback.parcial.exception.ValidacionException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TorneoServiceImplUnitTest {

    @Mock
    private com.parcialback.parcial.repository.TorneoRepository torneoRepository;

    @Mock
    private com.parcialback.parcial.repository.EquipoRepository equipoRepository;

    @InjectMocks
    private com.parcialback.parcial.service.impl.TorneoServiceImpl torneoService;

    @Test
    @DisplayName("crear: valida campos obligatorios vacios")
    void crearCamposVacios() {
        Torneo torneo = new Torneo(null, "", "Futbol", LocalDate.now().plusDays(10), "Bogota");
        ValidacionException ex = assertThrows(ValidacionException.class,
                () -> torneoService.crear(torneo));
        assertEquals("El nombre del torneo es obligatorio", ex.getMessage());
        verify(torneoRepository, never()).save(torneo);
    }

    @Test
    @DisplayName("crear: valida fecha inicio anterior a 1 dia")
    void crearFechaInicioInvalida() {
        Torneo torneo = new Torneo(null, "Copa America", "Futbol", LocalDate.now(), "Bogota");
        ValidacionException ex = assertThrows(ValidacionException.class,
                () -> torneoService.crear(torneo));
        assertEquals("La fecha de inicio debe ser al menos un dia despues de hoy", ex.getMessage());
        verify(torneoRepository, never()).save(torneo);
    }

    @Test
    @DisplayName("crear: guarda torneo valido")
    void crearValido() throws ValidacionException {
        Torneo torneo = new Torneo(null, "Copa America", "Futbol", LocalDate.now().plusDays(5), "Bogota");
        when(torneoRepository.save(torneo)).thenReturn(new Torneo(1L, "Copa America", "Futbol", LocalDate.now().plusDays(5), "Bogota"));
        Torneo resultado = torneoService.crear(torneo);
        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        verify(torneoRepository).save(torneo);
    }

    @Test
    @DisplayName("buscarPorDeporte: delega al repositorio")
    void buscarPorDeporte() {
        when(torneoRepository.findByDeporte("Futbol"))
                .thenReturn(List.of(new Torneo(1L, "Copa", "Futbol", LocalDate.now().plusDays(3), "Madrid")));
        List<Torneo> resultados = torneoService.buscarPorDeporte("Futbol");
        assertEquals(1, resultados.size());
        assertEquals("Copa", resultados.get(0).getNombre());
        verify(torneoRepository).findByDeporte("Futbol");
    }

    @Test
    @DisplayName("obtenerPorId: lanza excepcion si no existe")
    void obtenerPorIdNoExiste() {
        when(torneoRepository.findById(999L)).thenReturn(java.util.Optional.empty());
        assertThrows(com.parcialback.parcial.exception.RecursoNoEncontradoException.class,
                () -> torneoService.obtenerPorId(999L));
        verify(torneoRepository).findById(999L);
    }

    @Test
    @DisplayName("obtenerPorId: retorna torneo cuando existe")
    void obtenerPorIdExiste() throws com.parcialback.parcial.exception.RecursoNoEncontradoException {
        Torneo torneo = new Torneo(1L, "Copa", "Futbol", LocalDate.now().plusDays(3), "Madrid");
        when(torneoRepository.findById(1L)).thenReturn(java.util.Optional.of(torneo));
        Torneo resultado = torneoService.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals("Copa", resultado.getNombre());
    }

    @Test
    @DisplayName("listarTodos: retorna lista de torneos")
    void listarTodos() {
        when(torneoRepository.findAll())
                .thenReturn(List.of(
                        new Torneo(1L, "Copa", "Futbol", LocalDate.now().plusDays(3), "Madrid"),
                        new Torneo(2L, "NBA", "Basquet", LocalDate.now().plusDays(4), "Lima")));
        List<Torneo> resultados = torneoService.listarTodos();
        assertEquals(2, resultados.size());
        verify(torneoRepository).findAll();
    }

    @Test
    @DisplayName("actualizar: valida fecha inicio cuando edita")
    void actualizarFechaInvalida() throws com.parcialback.parcial.exception.RecursoNoEncontradoException {
        Torneo existente = new Torneo(1L, "Copa", "Futbol", LocalDate.now().plusDays(3), "Madrid");
        when(torneoRepository.findById(1L)).thenReturn(java.util.Optional.of(existente));
        Torneo actualizacion = new Torneo(null, "Copa 2", "Tenis", LocalDate.now(), "Bogota");
        assertThrows(ValidacionException.class,
                () -> torneoService.actualizar(1L, actualizacion));
    }

    @Test
    @DisplayName("eliminar: llama al repositorio cuando existe")
    void eliminarDondeExiste() throws com.parcialback.parcial.exception.RecursoNoEncontradoException {
        Torneo existente = new Torneo(1L, "Copa", "Futbol", LocalDate.now().plusDays(3), "Madrid");
        when(torneoRepository.findById(1L)).thenReturn(java.util.Optional.of(existente));
        torneoService.eliminar(1L);
        verify(torneoRepository).delete(existente);
    }
}
