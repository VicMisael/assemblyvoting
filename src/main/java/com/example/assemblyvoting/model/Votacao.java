package com.example.assemblyvoting.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Votacao {
    Pauta pauta;

    Integer votosSim;

    Integer votosNao;

    EstadoSessao estadoSessao;
}
