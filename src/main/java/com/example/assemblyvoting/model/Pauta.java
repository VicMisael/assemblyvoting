package com.example.assemblyvoting.model;

import io.r2dbc.spi.Row;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("assembly_voting.pautas")
@Builder
public class Pauta {
    @Id
    @Column("pauta_id")
    Long id;

    @Column("name_pauta")
    String nomePauta;
    @Column("descricao")
    String descricao;

    @Column("horario_inicio")
    LocalDateTime horarioInicio;

    @Column("horario_fim")
    LocalDateTime horarioTermino;

    public static Pauta fromRow(Row row) {
        return Pauta.builder()
                .id(row.get("pauta_id", Long.class))
                .nomePauta(row.get("name_pauta", String.class))
                .descricao(row.get("descricao", String.class))
                .horarioInicio(row.get("horario_inicio", LocalDateTime.class))
                .horarioTermino(row.get("horario_fim", LocalDateTime.class)
                ).build();
    }


}
