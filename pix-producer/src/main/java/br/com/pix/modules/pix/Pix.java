package br.com.pix.modules.pix;

import java.time.LocalDateTime;

import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.modules.pix.enums.PixStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private LocalDateTime dataTransferencia;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getId());
        pix.setChaveDestino(pixDTO.getTargetKey());
        pix.setStatus(pixDTO.getStatus());
        pix.setValor(pixDTO.getValue());
        pix.setDataTransferencia(pixDTO.getTransferDate());
        pix.setChaveOrigem(pixDTO.getOriginKey());
        return pix;
    }
}