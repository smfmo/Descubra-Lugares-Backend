package com.github.smfmo.descubra_lugares.repository;

import com.github.smfmo.descubra_lugares.model.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
    @Query(
            "SELECT l FROM Lugar l WHERE " +
            "(COALESCE(:nome, '') = '' OR l.nome ILIKE '%' || :nome || '%') AND " +
            "(COALESCE(:categoria, '') = '' OR l.categoria.nome ILIKE '%' || :categoria || '%')")
    List<Lugar> buscarPorFiltros(
        @Param("nome") String nome,
        @Param("categoria") String categoria
    );

}
