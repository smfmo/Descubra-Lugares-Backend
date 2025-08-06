package com.github.smfmo.descubra_lugares.controller;

import com.github.smfmo.descubra_lugares.model.Categoria;
import com.github.smfmo.descubra_lugares.service.CategoriaService;
import com.github.smfmo.descubra_lugares.service.HeaderLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;
    private final HeaderLocationService headerLocationService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        var resultList = service.findAll();
        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> save(@RequestBody Categoria categoria) {
        Categoria saved = service.save(categoria);

        URI location = headerLocationService.gerarHeaderLocation(categoria.getId());

        EntityModel<Categoria> model = EntityModel.of(
                saved,
                linkTo(methodOn(CategoriaController.class).findById(categoria.getId())).withSelfRel());

        return ResponseEntity.created(location).body(model);
    }

    @GetMapping("/{id}")
    public EntityModel<Optional<Categoria>> findById(@PathVariable("id") Long id) {
        var categoria = service.findById(id);

        return EntityModel.of(
                categoria,
                linkTo(methodOn(CategoriaController.class).findById(id)).withRel("self"),
                linkTo(methodOn(CategoriaController.class).findAll()).withRel("collection"),
                linkTo(methodOn(CategoriaController.class).delete(id)).withRel("delete"),
                linkTo(methodOn(CategoriaController.class).update(id, null)).withRel("update"),
                linkTo(methodOn(CategoriaController.class).partialUpdate(id, null)).withRel("patch")
        );
    }
/*
     @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable("id") Long id) {
        var result = service.findById(id);
        return ResponseEntity.ok().body(result);
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> update(@PathVariable("id") Long id,
                                            @RequestBody Categoria categoria) {
        var categoriaAtualizada = service.update(id, categoria);

        EntityModel<Categoria> model = EntityModel.of(
                categoriaAtualizada,
                linkTo(methodOn(CategoriaController.class).findById(id)).withRel("self"),
                linkTo(methodOn(CategoriaController.class).delete(id)).withRel("delete")
        );

        return ResponseEntity.ok(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> partialUpdate(@PathVariable("id") Long id,
                                           @RequestBody Categoria categoria) {
        var partialUpdate = service.partialUpdate(id, categoria);
        return ResponseEntity.ok(partialUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
