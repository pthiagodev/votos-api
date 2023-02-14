create table votacoes(

    id bigint not null auto_increment,
    pauta_id bigint not null,
    data_final datetime not null,

    primary key(id),
    foreign key(pauta_id) references pautas(id)

);