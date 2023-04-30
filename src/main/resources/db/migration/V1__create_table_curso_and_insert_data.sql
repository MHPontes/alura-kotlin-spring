create table curso(
    id bigint not null,
    nome varchar(50),
    categoria varchar(50),
    primary key (id)
);

insert into curso values (1, 'Kotlin', 'Programacao');