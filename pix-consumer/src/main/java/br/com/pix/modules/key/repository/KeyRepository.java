package br.com.pix.modules.key.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pix.modules.key.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByKey(String key);
}
