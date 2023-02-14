create table pautas(

    id bigint not null auto_increment,
    tema varchar(150) not null,
    votacao_id bigint not null,

    primary key(id)

);