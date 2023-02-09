package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Voto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import static org.springframework.data.relational.core.query.Criteria.where;


@RequiredArgsConstructor
@Repository
@Slf4j
public class VotoRepositoryImpl implements VotoRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Tuple2<Pauta, Associado>> findPautaAndAssociadoByPautaIdAndAssociadoId(final Voto voto) {
        final Long pautaId = voto.getPautaId();
        final Long associadoId = voto.getAssociadoId();
        final String query = "select * from" +
                " assembly_voting.pautas p join assembly_voting.associados a on p.pauta_id = " + pautaId + " and a.associado_id = " + associadoId;
        return template.getDatabaseClient()
                .sql(query)
                .map(row -> Tuples.of(Pauta.fromRow(row), Associado.fromRow(row)))
                .one();
    }

    @Override
    public Mono<VotoDTO> save(Voto voto) {
        log.debug("Saving voto");
        return template.insert(voto).flatMap(voto1 -> {
                    final String sql = "Select * from assembly_voting.votos as v " +
                            "INNER JOIN assembly_voting.associados as a on v.associado_id=a.associado_id " +
                            "INNER JOIN assembly_voting.pautas as p on v.pauta_id=p.pauta_id where v.associado_id=" + voto.getAssociadoId()
                            + " and v.pauta_id=" + voto.getPautaId();
                    return template.getDatabaseClient().sql(sql).map(VotoDTO::fromRow).one();
                }
        );
    }

    @Override
    public Mono<Boolean> checkRepeatedVote(Voto voto) {
        log.debug("Saving voto");
        return template.exists(Query.query(where("associado_id")
                .is(voto.getAssociadoId())
                .and("pauta_id").is(voto.getPautaId())), Voto.class);
    }

}
