package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VotoService {
    public Voto registrarVoto(VotoDTO voto);

    public List<Voto> getListaVotosPauta(Long pautaId);

}
