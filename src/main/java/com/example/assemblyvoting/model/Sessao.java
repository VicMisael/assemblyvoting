package com.example.assemblyvoting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class Sessao {


    public Sessao(final Long pautaId, final LocalDateTime horarioInicio, @Nullable LocalDateTime horarioFim) {
        if (horarioInicio == null) {
            throw new NullPointerException("horarioInicio is marked non-null but is null");
        } else {
            if (horarioFim == null) {
                horarioFim = horarioInicio.plusMinutes(1);
            }
            this.pautaId = pautaId;
            this.horarioInicio = horarioInicio;
            this.horarioFim = horarioFim;
        }
    }

    @JsonProperty("pautaId")
    Long pautaId;

    @NonNull
    @JsonProperty("horarioInicio")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime horarioInicio;

    @JsonProperty("horarioFim")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NonNull
    LocalDateTime horarioFim;
}
