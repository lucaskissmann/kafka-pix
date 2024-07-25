package br.com.pix.consumidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.pix.modules.key.Key;
import br.com.pix.modules.key.repository.KeyRepository;
import br.com.pix.modules.pix.Pix;
import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.modules.pix.enums.PixStatus;
import br.com.pix.modules.pix.repository.PixRepository;

@Service
public class PixValidator {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private PixRepository pixRepository;

    @KafkaListener(topics = "pix-topic", groupId = "grupo")
    public void processaPix(PixDTO pixDTO) {
        System.out.println("Pix  recebido: " + pixDTO.getId());

        Pix pix = pixRepository.findById(pixDTO.getId()).get();

        Key origem = keyRepository.findByChave(pixDTO.getOriginKey());
        Key destino = keyRepository.findByChave(pixDTO.getTargetKey());

        if (origem == null || destino == null) {
            pix.setStatus(PixStatus.ERRO);
        } else {
            pix.setStatus(PixStatus.PROCESSADO);
        }
        pixRepository.save(pix);
    }

}
