package br.com.pix.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.services.PixService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {
	
    private final PixService pixService;

    @PostMapping
    public PixDTO create(@RequestBody PixDTO pixDTO) {
        return pixService.create(pixDTO);
    }
	
}
