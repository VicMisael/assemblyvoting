create schema assembly_voting

    create table associados

    (
        associado_id SERIAL UNIQUE,
        name         VARCHAR(100) NOT NULL,
        membro_desde timestamp    not null,
        constraint associado_pk PRIMARY KEY (associado_id)
    )

    create table pautas
    (
        pauta_id       SERIAL UNIQUE,
        name_pauta     text NOT NULL,
        descricao      text,
        horario_inicio timestamp,
        horario_fim    timestamp,
        constraint pauta_pk PRIMARY KEY (pauta_id)

    )

    create table votos
    (
        voto_id      SERIAL,
        associado_id integer    NOT NULL,
        pauta_id     integer    NOT NULL,
        opcao        varchar(3) NOT NULL,
        constraint voto_pk PRIMARY KEY (voto_id),
        constraint associado_fk FOREIGN KEY (associado_id) references associados (associado_id),
        constraint pauta_fk FOREIGN KEY (pauta_id) references pautas (pauta_id),
        constraint associado_pauta_uk UNIQUE (associado_id, pauta_id)
    )