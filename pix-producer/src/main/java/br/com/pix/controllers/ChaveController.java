package br.com.pix.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pix.modules.chave.Chave;
import br.com.pix.services.ChaveService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/key")
@RequiredArgsConstructor
public class ChaveController {
	
	private final ChaveService service;
	@PostMapping
	public Chave create(@RequestBody Chave chave) {
		return service.create(chave);
	}
}
