package com.example.assemblyvoting.model.DTO;

import com.example.assemblyvoting.model.Voto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class VotoDTO {
    Long usuarioId;
    Long pautaId;
}
