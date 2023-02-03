package com.example.assemblyvoting.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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
}
