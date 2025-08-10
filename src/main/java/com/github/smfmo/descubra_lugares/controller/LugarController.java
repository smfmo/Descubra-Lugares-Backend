package com.github.smfmo.descubra_lugares.controller;

import com.github.smfmo.descubra_lugares.model.Lugar;
import com.github.smfmo.descubra_lugares.service.HeaderLocationService;
import com.github.smfmo.descubra_lugares.service.LugarService;
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
@RequestMapping("/lugares")
@RequiredArgsConstructor
@Slf4j
public class LugarController {

    private final LugarService lugarService;
    private final HeaderLocationService headerLocationService;

    @GetMapping
    public ResponseEntity<List<Lugar>> findAll() {
        var resultList = lugarService.findAll();
        log.info("Resultado da Lista {}", resultList.toString());
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<Lugar>> findByFilters(
            @RequestParam(value = "nome",
                    required = false) String nome,
            @RequestParam(value = "categoria",
                    required = false) String categoria) {
        var resultFilters = lugarService.findByFilters(nome, categoria);
        log.info("""
                Resultado da filtragem:
                
                 nome pesquisado = {}
                
                 categoria pesquisada = {}
      
                 resultado = {}""", nome, categoria, resultFilters.toString()
        );
        return ResponseEntity.ok(resultFilters);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Lugar>> save(@RequestBody Lugar lugar) {
        var saved = lugarService.save(lugar);
        log.info("Lugar salvo com sucesso: {}", saved.toString());

        URI location = headerLocationService.gerarHeaderLocation(lugar.getId());

        var model = EntityModel.of(
                saved,
                linkTo(methodOn(LugarController.class).findById(lugar.getId())).withSelfRel().withType("GET")
        );
        return ResponseEntity.created(location).body(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Optional<Lugar>>> findById(@PathVariable Long id) {
        var result = lugarService.findById(id);
        log.info("lugar Correspondente ao id: {}", result.toString());

        var model = EntityModel.of(
                result,
                linkTo(methodOn(LugarController.class).findAll()).withSelfRel().withType("GET")
        );
        return ResponseEntity.ok(model);
    }

}
