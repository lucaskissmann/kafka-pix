package br.com.pix.consumidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import br.com.pix.exception.KeyNotFoundException;
import br.com.pix.modules.chave.Chave;
import br.com.pix.modules.chave.repository.ChaveRepository;
import br.com.pix.modules.pix.Pix;
import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.modules.pix.enums.PixStatus;
import br.com.pix.modules.pix.repository.PixRepository;

@Service
public class PixValidator {

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private PixRepository pixRepository;

    @RetryableTopic(
        backoff = @Backoff(value = 3000L),
        attempts = "5",
        autoCreateTopics = "true",
        include = KeyNotFoundException.class 
    )
    @KafkaListener(topics = "pix-topic", groupId = "grupo-1")
    public void processaPix(PixDTO pixDTO) {
        System.out.println("Pix  recebido: " + pixDTO.getIdentifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());

        Chave origem = chaveRepository.findByChave(pixDTO.getOriginKey());
        Chave destino = chaveRepository.findByChave(pixDTO.getTargetKey());

        if (origem == null || destino == null) {
            pix.setStatus(PixStatus.ERRO);
            throw new KeyNotFoundException();
        } else {
            pix.setStatus(PixStatus.PROCESSADO);
        }
        
        pixRepository.save(pix);
    }

}
