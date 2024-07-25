package br.com.pix.modules.pix.dtos;

import java.time.LocalDateTime;

import br.com.pix.modules.pix.enums.PixStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PixDTO {
    private String identifier;
    private String originKey;
    private String targetKey;
    private Double value;
    private LocalDateTime transferDate;
    private PixStatus status;
}