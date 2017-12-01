DROP SCHEMA `locacao`;

CREATE SCHEMA `locacao` ;

CREATE TABLE locacao.usuario_logado(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
usuario_id INT NOT NULL,
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

CREATE TABLE locacao.funcao(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.permissao(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.tipo_recurso (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE locacao.unidade(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(100),
endereco VARCHAR(200) NULL,
ativo VARCHAR(1),
id_instituicao INT UNSIGNED NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_instituicao_unidade` (`id_instituicao` ASC) , 
	CONSTRAINT `id_instituicao` FOREIGN KEY (`id_instituicao` ) REFERENCES `instituicao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE  
);

CREATE TABLE locacao.usuario (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
matricula VARCHAR(100),
senha VARCHAR(45),
email VARCHAR(100),
telefone VARCHAR(100),
ativo VARCHAR(1),
id_funcao INT UNSIGNED NOT NULL,
status varchar(25),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC) ,
INDEX `id_funcao_usuario` (`id_funcao` ASC) ,
	CONSTRAINT `id_funcao_user` FOREIGN KEY (`id_funcao` ) REFERENCES `funcao` (`id` )
);

CREATE TABLE locacao.funcao_permissao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_funcao INT UNSIGNED,
id_permissao int UNSIGNED,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_permissao_funcao_permissao` (`id_permissao` ASC) ,
	CONSTRAINT `id_permissao` FOREIGN KEY (`id_permissao` ) REFERENCES `permissao` (`id` )
	ON DELETE NO ACTION ON UPDATE NO ACTION,
INDEX `id_funcao_funcao_permissao` (`id_funcao` ASC) , 
	CONSTRAINT `id_funcao` FOREIGN KEY (`id_funcao` ) REFERENCES `funcao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE    
);

CREATE TABLE locacao.recurso (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
etiqueta VARCHAR(100) NULL,
observacao VARCHAR(100) NULL,
id_unidade INT UNSIGNED NOT NULL,
id_tipo_recurso INT UNSIGNED NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_tipo_recurso_recurso` (`id_tipo_recurso` ASC) , 
	CONSTRAINT `id_tipo_recurso` FOREIGN KEY (`id_tipo_recurso` ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_unidade_recurso` (`id_unidade` ASC) , 
	CONSTRAINT `id_unidade` FOREIGN KEY (`id_unidade` ) REFERENCES `unidade` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE locacao.reserva (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_responsavel INT UNSIGNED NOT NULL,
id_destinatario INT UNSIGNED NOT NULL,
id_recurso INT UNSIGNED NOT NULL,
data_hora_reserva DATETIME NOT NULL,
data_hora_final DATETIME NOT NULL,
repeticao VARCHAR(100), -- EVENTO UNICO / SEMANALMENTE A CADA SEGUNDA / SEMANALMENTE A CADA TERCA / ...TODOS OS DIAS DA SEMANA / TODOS OS DIAS
status VARCHAR(50), -- ATIVO / PENDENTE / CONCLUIDO
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_recurso_reserva` (`id_recurso` ASC) , 
	CONSTRAINT `id_recurso` FOREIGN KEY (`id_recurso` ) REFERENCES `recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_destinatario_reserva` (`id_destinatario` ASC) , 
	CONSTRAINT `id_destinatario` FOREIGN KEY (`id_destinatario` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,	
INDEX `id_responsavel_reserva` (`id_responsavel` ASC) , 
	CONSTRAINT `id_responsavel` FOREIGN KEY (`id_responsavel` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE locacao.restricao_recurso(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_tipo_recurso2 INT UNSIGNED NOT NULL,
id_funcao2 INT UNSIGNED NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_tipo_recurso2_restricao_recurso` (id_tipo_recurso2 ASC) , 
	CONSTRAINT id_tipo_recurso2 FOREIGN KEY (id_tipo_recurso2 ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_funcao2_restricao_recurso` (id_funcao2 ASC) , 
	CONSTRAINT id_funcao2 FOREIGN KEY (id_funcao2 ) REFERENCES `funcao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE locacao.repeticao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_reserva_origem INT UNSIGNED NOT NULL,
id_reserva_new INT UNSIGNED NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_reserva_origem_repeticao` (id_reserva_origem ASC) , 
	CONSTRAINT id_reserva_origem FOREIGN KEY (id_reserva_origem ) REFERENCES `reserva` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_reserva_new_repeticao` (id_reserva_new ASC) , 
	CONSTRAINT id_reserva_new FOREIGN KEY (id_reserva_new ) REFERENCES `reserva` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE locacao.usuario_permissao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_usuario2 INT UNSIGNED DEFAULT NULL,
id_permissao2 INT UNSIGNED DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_usuario2_usuario_permissao` (id_usuario2 ASC) , 
	CONSTRAINT id_usuario2 FOREIGN KEY (id_usuario2 ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_permissao2_usuario_permissao` (id_permissao2 ASC) , 
	CONSTRAINT id_permissao2 FOREIGN KEY (id_permissao2 ) REFERENCES `permissao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE		
);

ALTER TABLE tipo_recurso ADD descricao varchar(100);

INSERT INTO usuario_logado (usuario_id) values(0);

INSERT INTO locacao.funcao(id, nome, descricao, ativo) VALUES
(1, 'Secretaria', 'professor', 1);
INSERT INTO locacao.funcao(id, nome, descricao, ativo) VALUES
(2, 'Estoque', 'acessos ', 1);

INSERT INTO locacao.funcao_permissao(id, id_funcao, id_permissao) VALUES
(1, 1, 1);
INSERT INTO locacao.funcao_permissao(id, id_funcao, id_permissao) VALUES
(2, 2, 2);
INSERT INTO locacao.funcao_permissao(id, id_funcao, id_permissao) VALUES
(3, 2, 1);

INSERT INTO locacao.instituicao(id, nome, email, telefone, ativo) VALUES
(1, 'Faculdade Alfa', 'teste@teste.com', '(62) 99999-9999', NULL);

INSERT INTO locacao.permissao(id, nome, descricao, ativo) VALUES
(1, 'Professor', 'Acessos', 1);
INSERT INTO locacao.permissao(id, nome, descricao, ativo) VALUES
(2, 'Administrador', 'Acessos', 1);

INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(1, '1', 'primeiro cadastro', 1, 1, '1');
INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(2, '2', 'segundo cadastro', 1, 1, '1');
INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(3, '3', 'terceiro cadastro', 1, 1, '1');
INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(4, '1', 'primeiro cadastro', 1, 2, '1');
INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(5, '2', 'segundo cadastro', 1, 2, '1');
INSERT INTO locacao.recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso, ativo) VALUES
(6, '1', 'primeiro cadastro g', 1, 3, '1');

INSERT INTO locacao.reserva(id, id_responsavel, id_destinatario, id_recurso, data_hora_reserva, data_hora_final, repeticao, status) VALUES
(7, 2, 1, 1, '2017-11-15 02:18:00', '2017-11-15 02:18:00', 'EVENTO UNICO', 'ATIVO');

INSERT INTO locacao.restricao_recurso(id, id_tipo_recurso2, id_funcao2) VALUES
(1, 1, 1);
INSERT INTO locacao.restricao_recurso(id, id_tipo_recurso2, id_funcao2) VALUES
(2, 2, 1);

INSERT INTO locacao.tipo_recurso(id, nome, ativo, descricao) VALUES
(1, 'Cabo Hdmi 2 metros', '1', 'Resistente a agua');
INSERT INTO locacao.tipo_recurso(id, nome, ativo, descricao) VALUES
(2, 'Mouse', '1', 'gamer');
INSERT INTO locacao.tipo_recurso(id, nome, ativo, descricao) VALUES
(3, 'Teclado', '1', 'Normal');

INSERT INTO locacao.unidade(id, nome, email, telefone, endereco, ativo, id_instituicao) VALUES
(1, 'Perimetral Norte', 'teste2@teste.com', '(62) 99999-9999', 'av perimetral', '1', 1);
INSERT INTO locacao.unidade(id, nome, email, telefone, endereco, ativo, id_instituicao) VALUES
(2, 'Bueno', 'teste3@teste.com', '(62) 99999-9999', 'Av Multirão', '1', 1);

INSERT INTO locacao.usuario(id, nome, matricula, senha, email, telefone, ativo, id_funcao, status) VALUES
(1, 'Wigor Paulo', '123', '123', 'teste@teste.com', '(62) 99999-9999', '1', 2, 'Ok');
INSERT INTO locacao.usuario(id, nome, matricula, senha, email, telefone, ativo, id_funcao, status) VALUES
(2, 'Wigor Paulo', '123', '123', 'teste@teste.com', '(62) 99999-9999', '1', 2, 'Ok');
INSERT INTO locacao.usuario(id, nome, matricula, senha, email, telefone, ativo, id_funcao, status) VALUES
(3, 'Danubia', '123', '123', 'teste@teste.com', '(99) 99999-9999', '1', 1, 'Ok');

