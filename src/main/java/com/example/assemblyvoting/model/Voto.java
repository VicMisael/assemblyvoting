package com.example.assemblyvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@Table("assembly_voting.votos")
public class Voto {
    Long id;
    Associado associado;
    @JsonIgnore
    Pauta pauta;

    Opcao opcao;


}
