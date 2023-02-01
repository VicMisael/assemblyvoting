package com.example.assemblyvoting.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class Associado {
    Long associadoId;

    String nome;

}
