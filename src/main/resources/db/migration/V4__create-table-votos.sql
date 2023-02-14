create table votos(

    id bigint not null auto_increment,
    favoravel tinyint(1) not null,
    data_registro datetime not null,
    votacao_id bigint not null,
    associado_id bigint not null,

    primary key(id),
    foreign key(votacao_id) references votacoes(id),
    foreign key(associado_id) references associados(id)

);