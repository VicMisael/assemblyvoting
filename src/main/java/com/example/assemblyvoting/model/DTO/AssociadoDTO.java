package com.example.assemblyvoting.model.DTO;

import com.example.assemblyvoting.model.Associado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AssociadoDTO {
    @JsonProperty("name")
    String nome;

    public Associado toAssociado() {
        return Associado.builder().nome(nome).memberSince(LocalDateTime.now()).build();
    }
}
