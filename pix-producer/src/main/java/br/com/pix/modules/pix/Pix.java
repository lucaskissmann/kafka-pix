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
    private String originKey;
    private String targetKey;
    private Double value;
    private LocalDateTime transferDate;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setTargetKey(pixDTO.getTargetKey());
        pix.setStatus(pixDTO.getStatus());
        pix.setValue(pixDTO.getValue());
        pix.setTransferDate(pixDTO.getTransferDate());
        pix.setOriginKey(pixDTO.getOriginKey());
        return pix;
    }
}