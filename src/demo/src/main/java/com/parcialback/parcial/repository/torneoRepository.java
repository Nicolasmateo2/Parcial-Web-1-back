package com.parcialback.parcial.repository;

import com.parcialback.parcial.entity.Torneo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TorneoRepository extends JpaRepository<Torneo, Long> {

    List<Torneo> findByDeporte(String deporte);

    List<Torneo> findByCiudad(String ciudad);

    @Query("SELECT t FROM Torneo t WHERE LOWER(t.deporte) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Torneo> buscarPorTermino(@Param("term") String term);
}
