package br.com.pix.modules.chave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pix.modules.chave.Chave;

public interface ChaveRepository extends JpaRepository<Chave, Integer> {
	
}
