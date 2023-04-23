create database `crud-jsp`;
create table `eventos_tbl` (
                               `id` int not null auto_increment primary key,
                               `nome` varchar(40) not null,
                               `data` varchar(40) not null,
                                `local` varchar(40) not null
);