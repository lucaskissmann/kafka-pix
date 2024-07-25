package br.com.pix.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import br.com.pix.enums.PixStatus;

@Getter
@Setter
public class PixDTO {
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private LocalDateTime dataTransferencia;
    private PixStatus status;
}
