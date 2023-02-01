create schema assembly_voting

    create table associados

    (
        associado_id SERIAL UNIQUE,
        name         VARCHAR(100) NOT NULL,
        constraint associado_pk PRIMARY KEY (associado_id)
    )

    create table pautas
    (
        pauta_id   SERIAL UNIQUE,
        name_pauta text,
        constraint pauta_pk PRIMARY KEY (pauta_id)

    )

    create table votos
    (
        voto_id      SERIAL,
        associado_id integer,
        pauta_id     integer,
        opcao        integer,
        constraint voto_pk PRIMARY KEY (voto_id),
        constraint associado_fk FOREIGN KEY (associado_id) references associados (associado_id),
        constraint pauta_fk FOREIGN KEY (pauta_id) references pautas (pauta_id)
    )