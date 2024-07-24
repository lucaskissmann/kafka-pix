package br.com.pix.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.services.PixService;

public class PixController {
	private PixService pixService;

    @PostMapping
    public PixDTO create(@RequestBody PixDTO pixDTO) {
        return pixService.create(pixDTO);
    }
	
}
