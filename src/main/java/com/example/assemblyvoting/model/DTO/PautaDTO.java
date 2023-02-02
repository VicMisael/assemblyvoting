package com.example.assemblyvoting.model.DTO;

import com.example.assemblyvoting.model.Pauta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PautaDTO {
    @NonNull
    @JsonProperty("nome_pauta")
    String nomePauta;
    @NonNull
    @JsonProperty("descricao")
    String descricao;

    public Pauta toPauta() {
        return Pauta.builder().nomePauta(nomePauta).descricao(descricao).build();
    }

}
