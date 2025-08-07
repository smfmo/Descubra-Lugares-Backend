package com.github.smfmo.descubra_lugares.controller;

import com.github.smfmo.descubra_lugares.model.Lugar;
import com.github.smfmo.descubra_lugares.service.HeaderLocationService;
import com.github.smfmo.descubra_lugares.service.LugarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lugares")
@RequiredArgsConstructor
public class LugarController {

    private final LugarService lugarService;
    private final HeaderLocationService headerLocationService;

    @GetMapping
    public ResponseEntity<List<Lugar>> findAll() {
        var resultList = lugarService.findAll();
        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity<Lugar> save(@RequestBody Lugar lugar) {
        var saved = lugarService.save(lugar);
        URI location = headerLocationService.gerarHeaderLocation(lugar.getId());
        return ResponseEntity.created(location).body(saved);
    }

}
