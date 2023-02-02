package com.example.assemblyvoting.model.DTO;

import com.example.assemblyvoting.model.Opcao;
import com.example.assemblyvoting.model.Voto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class VotoDTO {

    @NonNull
    Long usuarioId;
    @NonNull
    Long pautaId;
    @NonNull
    Opcao opcao;
}
