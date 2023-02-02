package com.example.assemblyvoting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sessao {
    @JsonProperty("id_pauta")
    Long pautaId;

    @NonNull
    @JsonProperty("horario_inicio")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime horarioInicio;

    @JsonProperty("horario_fim")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime horarioTermino;
}
