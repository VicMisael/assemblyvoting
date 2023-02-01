package com.example.assemblyvoting.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class Pauta {
    Long id;

    String nomePauta;
    String descricao;

    LocalDateTime horarioInicio;

    LocalDateTime horarioTermino;

}
