CREATE SCHEMA `locacao` ;


CREATE TABLE locacao.unidade(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(100),
endereco VARCHAR(200) NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.instituicao(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(100),
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.usuario (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
matricula VARCHAR(100),
senha VARCHAR(45),
email VARCHAR(100),
telefone VARCHAR(100),
ativo VARCHAR(1),
id_funcao INT UNSIGNED,
status varchar(25),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC) ,
INDEX `id_funcao_idx` (`id_funcao` ASC) ,
	CONSTRAINT `id_funcao_user`
	FOREIGN KEY (`id_funcao` )
	REFERENCES `funcao` (`id` )
);

CREATE TABLE locacao.funcao(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
)ENGINE = InnoDB;


CREATE TABLE locacao.permissao(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
)ENGINE = InnoDB;

CREATE TABLE locacao.funcao_permissao (
id_funcao INT UNSIGNED,
id_permissao int UNSIGNED,
INDEX `id_permissao_idx` (`id_permissao` ASC) ,
	CONSTRAINT `id_permissao`
	FOREIGN KEY (`id_permissao` )
	REFERENCES `permissao` (`id` )
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
    INDEX `id_funcao_idx` (`id_funcao` ASC) ,
	CONSTRAINT `id_funcao`
	FOREIGN KEY (`id_funcao` )
	REFERENCES `funcao` (`id` )
	ON DELETE CASCADE 
    ON UPDATE CASCADE
    
) ENGINE = InnoDB;

CREATE TABLE locacao.tipo_recurso (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.arestricao_recurso(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_tipo_recurso INT NOT NULL,
id_funcao INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.recurso (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
etiqueta VARCHAR(100) NULL,
observacao VARCHAR(100) NULL,
id_unidade INT NOT NULL,
id_tipo_recurso INT NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.reserva (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_responsavel INT NOT NULL,
id_destinatario INT NOT NULL,
id_recurso INT NOT NULL,
data_hora_reserva DATETIME NOT NULL,
data_hora_final DATETIME NOT NULL,
repeticao VARCHAR(100), -- EVENTO UNICO / SEMANALMENTE A CADA SEGUNDA / SEMANALMENTE A CADA TERCA / ...TODOS OS DIAS DA SEMANA / TODOS OS DIAS
status VARCHAR(50), -- ATIVO / PENDENTE / CONCLUIDO
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.repeticao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_reserva INT NOT NULL,
id_responsavel INT NOT NULL,
id_destinatario INT NOT NULL,
data_hora_reserva DATETIME NOT NULL,
data_hora_final DATETIME NOT NULL,
ativo VARCHAR(1), -- ATIVO / INATIVO
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

insert into locacao.unidade (nome, email, telefone, endereco) values('teste1','teste@teste.com','9999-9999','rua');

INSERT INTO locacao.tipo_recurso (nome) values ('cabo');
INSERT INTO locacao.tipo_recurso (nome) values ('teclado');
INSERT INTO locacao.tipo_recurso (nome) values ('mouse');

insert into locacao.recurso (nome, etiqueta, observacao, id_unidade, id_tipo_recurso)
            values('hdmi 2 metros', '1','teste de cadastro',1,1);
insert into locacao.recurso (nome, etiqueta, observacao, id_unidade, id_tipo_recurso)
            values('hdmi 3 metros', '2','teste de cadastro',1,1);  
insert into locacao.recurso (nome, etiqueta, observacao, id_unidade, id_tipo_recurso)
            values('hdmi 4 metros', '3','teste de cadastro',1,1);      
			
			
-- consulta a quantidade de estoque gastos
select count(rs.id_recurso) 
from reserva rs
left join recurso rc on rc.id = rs.id_recurso and rc.id_tipo_recurso = 1
where rs.id_recurso = 1

-- consulta a quantidade de estoque
select count(id_tipo_recurso) 
from recurso 
where id_tipo_recurso = 1

-- traz a qtde disponivel
select 
((select count(rc.id_tipo_recurso) qtde_estoque 
from recurso rc
-- left join reserva rs on rs.id_recurso = rc.id
 )-(select count(rs.id_recurso) qtde_gasto
from reserva rs
left join recurso rc on rc.id = rs.id_recurso 
where rs.data_hora_reserva >= '2017-10-03 23:30:00' 
  and rs.data_hora_final <= '2017-10-21 23:50:00'
)) qtde_disponivel ,
 rs.id codigo_reserva, 
 rs.id_responsavel codigo_usuario_responsavel,
 us1.nome nome_responsavel,
 rs.id_destinatario codigo_usuario_Destinatario,
 us2.nome nome_destinatario,
 rs.id_recurso codigo_recurso,
 tp.nome,
 rs.data_hora_reserva,
 rs.data_hora_final,
 rs.repeticao,
 rs.status 
from reserva rs 
left join recurso rc on rc.id = rs.id_recurso
left join tipo_recurso tp on tp.id = rc.id_tipo_recurso
left join usuario us1 on us1.id = rs.id_responsavel
left join usuario us2 on us2.id = rs.id_destinatario 
where # rs.status = 'ATIVO'
 # and rs.id_recurso = 1 and
   rs.data_hora_reserva >= '2017-10-03 23:30:00' 
  and rs.data_hora_final <= '2017-10-21 23:50:00'
			