package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.model.Votacao;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface PautaService {
    /**
     * Insere uma nova pauta.
     *
     * @param pauta A pauta para inserção
     * @return A pauta com ID caso a adição seja feita com sucesso, Vazio caso não.
     */
    Mono<Pauta> insertPauta(Pauta pauta);

    /**
     * Inicia a sessão de votação.
     *
     * @param sessao os dados sessão.
     * @return A Pauta atualizada com os dados da sessão, Erro caso o horário de Início seja anterior ao horário de fim.
     */
    Mono<Pauta> iniciaSessao(Sessao sessao);

     /**
      * Retorna todas as pautas.
      * @return A lista das pautas.
      */
    Flux<Pauta> getPautas();

     /**
      * Retorna a pauta pelo seu ID.
      * @param id id da Pauta buscada
      * @return a Pauta
      */
    Mono<Pauta> getPautaById(Long id);
     /**
      * Retorna um Boolean com os dados da existencia da pauta pelo seu ID.
      * @param pautaId id da Pauta buscada
      * @return Verdadeiro caso exista, Falso caso contrário
      */
    Mono<Boolean> existsById(Long pautaId);

    /**
     * Retorna o resultado da votaçao
     * @param pautaId id da pauta da votação
     * @return Os dados da votação, erro
     */
    Mono<Votacao> getResultadoVotacao(Long pautaId);
}
