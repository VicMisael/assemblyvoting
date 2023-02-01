package com.example.assemblyvoting.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Voto {
    Long id;
    Associado associado;
    Pauta pauta;


}
