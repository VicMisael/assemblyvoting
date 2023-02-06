package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;


@RequiredArgsConstructor
@Repository
public class VotoRepositoryImpl implements VotoRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<VotoDTO> save(Voto voto) {
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
        return template.exists(Query.query(where("associado_id")
                .is(voto.getAssociadoId())
                .and("pauta_id").is(voto.getPautaId())), Voto.class);
    }

}
