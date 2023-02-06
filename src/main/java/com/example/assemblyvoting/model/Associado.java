package com.example.assemblyvoting.model;

import io.r2dbc.spi.Row;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@Table("assembly_voting.associados")
public class Associado {
    @Id
    Long associadoId;

    @NonNull
    @Column("name")
    String nome;

    @NonNull
    @Column("membro_desde")
    LocalDateTime memberSince;

    public static Associado fromRow(Row row) {
        return Associado.builder()
                .associadoId(row.get("associado_id", Long.class))
                .memberSince(Objects.requireNonNull(row.get("membro_desde", LocalDateTime.class)))
                .nome(Objects.requireNonNull(row.get("membro_desde", String.class))).build();


    }
}
