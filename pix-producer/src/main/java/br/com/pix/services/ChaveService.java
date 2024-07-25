package br.com.pix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pix.modules.chave.Chave;
import br.com.pix.modules.chave.repository.ChaveRepository;

@Service
public class ChaveService {
	
	@Autowired
	private ChaveRepository repository;

	public Chave create(Chave chave) {
		if(chave.getChave() == null) {
			throw new IllegalArgumentException("Chave não pode ser nula");
		}
		
		return repository.save(chave);
	}
}
