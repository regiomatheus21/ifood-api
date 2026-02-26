create table cidade (
    id bigint not null auto_increment,
    nome varchar (80) not null,
    estado_id bigint not null,
    primary key (id)
)