package com.example.assemblyvoting.model.DTO;

import com.example.assemblyvoting.model.Opcao;
import com.example.assemblyvoting.model.Pauta;
import io.r2dbc.spi.Row;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
public class VotoDTO {
    Long id;


    Pauta pauta;

    Opcao opcao;

    public static VotoDTO fromRow(Row row) {
        return VotoDTO.builder()
                .id(row.get("voto_id", Long.class))
                .pauta(Pauta.builder()
                        .id(row.get("pauta_id", Long.class))
                        .nomePauta(row.get("name_pauta", String.class))
                        .descricao(row.get("descricao", String.class))
                        .horarioInicio(row.get("horario_inicio", LocalDateTime.class))
                        .horarioTermino(row.get("horario_fim", LocalDateTime.class)
                        ).build())
                .opcao(Objects.requireNonNull(Opcao.valueOf(row.get("opcao", String.class))))
                .build();

    }

}
