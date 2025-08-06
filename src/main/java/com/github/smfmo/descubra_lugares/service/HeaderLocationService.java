package com.github.smfmo.descubra_lugares.service;

import com.github.smfmo.descubra_lugares.controller.GenericController;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Service
public class HeaderLocationService implements GenericController {

    @Override
    public URI gerarHeaderLocation(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
