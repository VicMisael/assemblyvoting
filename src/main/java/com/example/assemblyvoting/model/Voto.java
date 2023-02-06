package com.example.assemblyvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table("assembly_voting.votos")
public class Voto {

    @Id
    @JsonIgnore
    @Column("voto_id")
    Long votoid;
    @NonNull
    @Column("associado_id")
    Long associadoId;
    @NonNull
    @Column("pauta_id")
    Long pautaId;
    @NonNull
    @Column("opcao")
    Opcao opcao;
}
