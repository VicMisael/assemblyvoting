package com.example.assemblyvoting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
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
