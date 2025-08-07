package com.github.smfmo.descubra_lugares.repository;

import com.github.smfmo.descubra_lugares.model.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
}
