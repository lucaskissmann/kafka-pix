package br.com.pix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.pix.modules.pix.Pix;
import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.modules.pix.enums.PixStatus;
import br.com.pix.modules.pix.repository.PixRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PixService {

    @Autowired
    private final PixRepository pixRepository;

    @Autowired
    private final KafkaTemplate<String, PixDTO>  kafkaTemplate;

    public PixDTO create(PixDTO pixDTO) {
		pixDTO.setId(UUID.randomUUID().toString());
        pixDTO.setTransferDate(LocalDateTime.now());
        pixDTO.setStatus(PixStatus.EM_PROCESSAMENTO);
		
        pixRepository.save(Pix.toEntity(pixDTO));
        kafkaTemplate.send("pix-topic", pixDTO.getId(), pixDTO);
        return pixDTO;
    }

}