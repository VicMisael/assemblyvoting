package com.example.assemblyvoting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sessao {
    @JsonProperty("pautaId")
    Long pautaId;

    @NonNull
    @JsonProperty("horarioInicio")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime horarioInicio;

    @JsonProperty("horarioFim")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Nullable
    LocalDateTime horarioFim;
}
