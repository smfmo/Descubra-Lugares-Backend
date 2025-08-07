package com.github.smfmo.descubra_lugares.service;

import com.github.smfmo.descubra_lugares.model.Lugar;
import com.github.smfmo.descubra_lugares.repository.LugarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarService {

    private final LugarRepository repository;
    private final CategoriaService categoriaService;

    @Autowired
    public LugarService(LugarRepository repository,
                        CategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    public List<Lugar> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Lugar save(Lugar lugar) {
        var categoria = categoriaService.findByNome(lugar.getCategoria().getNome());
        lugar.setCategoria(categoria);

        return repository.save(lugar);
    }

}
