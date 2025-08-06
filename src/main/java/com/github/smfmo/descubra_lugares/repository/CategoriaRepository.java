package com.github.smfmo.descubra_lugares.repository;

import com.github.smfmo.descubra_lugares.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
