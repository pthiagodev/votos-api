create table associados(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(11) not null unique,

    primary key(id)

);