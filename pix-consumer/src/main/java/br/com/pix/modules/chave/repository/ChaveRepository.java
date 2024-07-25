package br.com.pix.modules.chave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pix.modules.chave.Chave;

@Repository
public interface ChaveRepository extends JpaRepository<Chave, Integer> {
    Chave findByChave(String chave);
}
