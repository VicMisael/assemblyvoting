package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Opcao;
import io.r2dbc.spi.Row;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class PautaCustomRepositoryImpl implements PautaCustomRepository {
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Map<Opcao, Integer>> getVotacaoByPauta(Long pautaId) {
        final String sql = "select opcao ,COUNT(opcao) from assembly_voting.votos v where v.pauta_id =" + pautaId + " group by v.opcao ";
        return template.getDatabaseClient().sql(sql).map(VotoQuantidade::fromRow).all()
                .collectMap(VotoQuantidade::getOpcao, VotoQuantidade::getQuantidade);
    }

    @Builder
    @Getter
    private static class VotoQuantidade {
        Opcao opcao;

        Integer quantidade;

        public static VotoQuantidade fromRow(Row row) {
            return VotoQuantidade.builder()
                    .quantidade(row.get("count", Integer.class))
                    .opcao(Objects.requireNonNull(Opcao.valueOf(row.get("opcao", String.class))))
                    .build();
        }
    }
}
