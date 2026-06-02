package com.parcialback.parcial.controller;

import com.parcialback.parcial.entity.Torneo;
import com.parcialback.parcial.service.TorneoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping
    public ResponseEntity<List<Torneo>> listar() {
        return ResponseEntity.ok(torneoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(torneoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Torneo> crear(@RequestBody Torneo torneo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.crear(torneo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneo> actualizar(@PathVariable Long id, @RequestBody Torneo torneo) {
        return ResponseEntity.ok(torneoService.actualizar(id, torneo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        torneoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Torneo>> buscar(
            @RequestParam(required = false) String deporte,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String termino) {

        if (deporte != null && !deporte.isBlank()) {
            return ResponseEntity.ok(torneoService.buscarPorDeporte(deporte));
        }
        if (ciudad != null && !ciudad.isBlank()) {
            return ResponseEntity.ok(torneoService.buscarPorCiudad(ciudad));
        }
        if (termino != null && !termino.isBlank()) {
            return ResponseEntity.ok(torneoService.buscarPorDeporte(termino));
        }
        return ResponseEntity.ok(torneoService.listarTodos());
    }
}
