package br.com.pix.modules.pix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pix.modules.pix.Pix;

@Repository
public interface PixRepository extends JpaRepository<Pix, Integer> {
    Pix findByIdentifier(String identifier);
}
