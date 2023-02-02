package com.example.assemblyvoting.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class Sessao {
    @NonNull
    Long pautaId;

    @NonNull
    LocalDateTime horarioInicio;

    LocalDateTime horarioTermino;
}
