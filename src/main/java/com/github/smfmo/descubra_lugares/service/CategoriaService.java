package com.github.smfmo.descubra_lugares.service;

import com.github.smfmo.descubra_lugares.model.Categoria;
import com.github.smfmo.descubra_lugares.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Categoria update(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encontrada")
        );

        categoriaExistente.setNome(categoriaAtualizada.getNome());
        categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());

        return repository.save(categoriaExistente);
    }

    @Transactional
    public Categoria partialUpdate(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encontrada")
        );

        if(categoriaAtualizada.getNome() != null) {
            categoriaExistente.setNome(categoriaAtualizada.getNome());
        }
        if (categoriaAtualizada.getDescricao() != null) {
            categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());
        }

        return repository.save(categoriaExistente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
