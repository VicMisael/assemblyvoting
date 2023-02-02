package com.example.assemblyvoting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table("assembly_voting.pautas")
@Builder
public class Pauta {
    @Id
    @Column("pauta_id")
    Long id;

    @Column("name_pauta")
    @NonNull
    String nomePauta;
    @Column("descricao")
    String descricao;

    @Column("horario_inicio")
    LocalDateTime horarioInicio;

    @Column("horario_fim")
    LocalDateTime horarioTermino;


}
