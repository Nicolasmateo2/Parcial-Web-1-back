package com.parcialback.parcial.repository;

import com.parcialback.parcial.entity.Equipo;
import com.parcialback.parcial.entity.Torneo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByTorneoId(Long torneoId);

    List<Equipo> findByTorneo(Torneo torneo);

    List<Equipo> findByCategoria(String categoria);

    boolean existsByTorneoIdAndNombreIgnoreCase(Long torneoId, String nombre);
}
