package br.com.pix.modules.pix.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import br.com.pix.modules.pix.enums.PixStatus;

@Getter
@Setter
@NoArgsConstructor
public class PixDTO {
    private Integer id;
    private String originKey;
    private String targetKey;
    private Double value;
    private LocalDateTime transferDate;
    private PixStatus status;
}
