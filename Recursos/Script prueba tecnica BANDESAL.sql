create database if not exists blogprueba;
use blogprueba;

-- Creacion de tabla Readers
create table if not exists readers (
idReader int not null primary key auto_increment,
name varchar(8) not null
)engine InnoDB;

-- Creacion de tabla Blogs
create table if not exists blogs (
idBlog int not null primary key auto_increment,
title varchar(50) not null,
description varchar(4000) not null
)engine InnoDB;

-- Creacion de tabla pivote Blogs Readers
create table if not exists blogs_readers (
idBlogReader int not null primary key auto_increment,
idReader int not null,
idBlog int not null,
constraint fkReaders foreign key(idReader) 
references readers(idReader) on delete cascade on update cascade,
constraint fkBlogs foreign key(idBlog) 
references blogs(idBlog) on delete cascade on update cascade
)engine InnoDB;