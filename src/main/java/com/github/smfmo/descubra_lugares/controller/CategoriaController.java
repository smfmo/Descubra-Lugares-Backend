package com.github.smfmo.descubra_lugares.controller;

import com.github.smfmo.descubra_lugares.model.Categoria;
import com.github.smfmo.descubra_lugares.service.CategoriaService;
import com.github.smfmo.descubra_lugares.service.HeaderLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoriaController {

    private final CategoriaService service;
    private final HeaderLocationService headerLocationService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        var resultList = service.findAll();
        log.info("Buscando as categorias no Angular {}", resultList.toString());
        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> save(@RequestBody Categoria categoria) {
        Categoria saved = service.save(categoria);

        log.info("Salvando categoria no Front-End {}", saved.toString());

        URI location = headerLocationService.gerarHeaderLocation(categoria.getId());
        var model = EntityModel.of(
                saved,
                linkTo(methodOn(CategoriaController.class).findById(categoria.getId())).withSelfRel().withType("GET"),
                linkTo(methodOn(CategoriaController.class).delete(categoria.getId())).withRel("delete").withType("DELETE"),
                linkTo(methodOn(CategoriaController.class).update(categoria.getId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(CategoriaController.class).partialUpdate(categoria.getId(), null)).withRel("partial_update").withType("PATCH")
        );

        return ResponseEntity.created(location).body(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Optional<Categoria>>> findById(@PathVariable("id") Long id) {
        var categoria = service.findById(id);

        log.info("buscando categoria no Front-End {}", categoria.toString());

        var model = EntityModel.of(
                categoria,
                linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel().withType("GET"),
                linkTo(methodOn(CategoriaController.class).findAll()).withRel("collection").withType("GET"),
                linkTo(methodOn(CategoriaController.class).delete(id)).withRel("delete").withType("DELETE"),
                linkTo(methodOn(CategoriaController.class).update(id, null)).withRel("update").withType("PUT"),
                linkTo(methodOn(CategoriaController.class).partialUpdate(id, null)).withRel("patch").withType("PATCH")
        );

        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> update(@PathVariable("id") Long id,
                                            @RequestBody Categoria categoria) {
        var categoriaAtualizada = service.update(id, categoria);

        log.info("Atualizando a categoria {}", categoriaAtualizada.toString());

        var model = EntityModel.of(
                categoriaAtualizada,
                linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel().withType("GET"),
                linkTo(methodOn(CategoriaController.class).delete(id)).withSelfRel().withType("DELETE")
        );

        return ResponseEntity.ok(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> partialUpdate(@PathVariable("id") Long id,
                                           @RequestBody Categoria categoria) {
        var partialUpdate = service.partialUpdate(id, categoria);

        log.info("Atualização parcial da categoria {}", partialUpdate.toString());
        var model = EntityModel.of(
                partialUpdate,
                linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel().withType("GET"),
                linkTo(methodOn(CategoriaController.class).delete(id)).withRel("delete").withType("DELETE"),
                linkTo(methodOn(CategoriaController.class).partialUpdate(id, null)).withRel("patch").withType("PATCH")
        );
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Deletando categoria {}", id.toString());
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
