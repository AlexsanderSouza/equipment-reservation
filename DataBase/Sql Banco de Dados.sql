CREATE SCHEMA `locacao` ;

CREATE TABLE locacao.usuario_logado(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
usuario_id INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);
INSERT INTO usuario_logado (usuario_id) values(0);

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
ALTER TABLE tipo_recurso ADD descricao varchar(100);

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
INDEX `id_instituicao_idx` (`id_instituicao` ASC) , 
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
INDEX `id_funcao_idx` (`id_funcao` ASC) ,
	CONSTRAINT `id_funcao_user` FOREIGN KEY (`id_funcao` ) REFERENCES `funcao` (`id` )
);

CREATE TABLE locacao.funcao_permissao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_funcao INT UNSIGNED,
id_permissao int UNSIGNED,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_permissao_idx` (`id_permissao` ASC) ,
	CONSTRAINT `id_permissao` FOREIGN KEY (`id_permissao` ) REFERENCES `permissao` (`id` )
	ON DELETE NO ACTION ON UPDATE NO ACTION,
INDEX `id_funcao_idx` (`id_funcao` ASC) , 
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
INDEX `id_tipo_recurso_idx` (`id_tipo_recurso` ASC) , 
	CONSTRAINT `id_tipo_recurso` FOREIGN KEY (`id_tipo_recurso` ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_unidade_idx` (`id_unidade` ASC) , 
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
INDEX `id_recurso_idx` (`id_recurso` ASC) , 
	CONSTRAINT `id_recurso` FOREIGN KEY (`id_recurso` ) REFERENCES `recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_destinatario_idx` (`id_destinatario` ASC) , 
	CONSTRAINT `id_destinatario` FOREIGN KEY (`id_destinatario` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,	
INDEX `id_responsavel_idx` (`id_responsavel` ASC) , 
	CONSTRAINT `id_responsavel` FOREIGN KEY (`id_responsavel` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE locacao.restricao_recurso(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_tipo_recurso2 INT UNSIGNED NOT NULL,
id_funcao2 INT UNSIGNED NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_tipo_recurso2_idx` (id_tipo_recurso2 ASC) , 
	CONSTRAINT id_tipo_recurso2 FOREIGN KEY (id_tipo_recurso2 ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_funcao2_idx` (id_funcao2 ASC) , 
	CONSTRAINT id_funcao2 FOREIGN KEY (id_funcao2 ) REFERENCES `funcao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE locacao.repeticao (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_reserva2 INT UNSIGNED NOT NULL,
id_responsavel2 INT UNSIGNED NOT NULL,
id_destinatario2 INT UNSIGNED NOT NULL,
data_hora_reserva DATETIME NOT NULL,
data_hora_final DATETIME NOT NULL,
ativo VARCHAR(1), -- ATIVO / INATIVO
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_reserva2_idx` (id_reserva2 ASC) , 
	CONSTRAINT id_reserva2 FOREIGN KEY (id_reserva2 ) REFERENCES `reserva` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_destinatario2_idx` (`id_destinatario2` ASC) , 
	CONSTRAINT `id_destinatario2` FOREIGN KEY (`id_destinatario2` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,	
INDEX `id_responsavel2_idx` (`id_responsavel2` ASC) , 
	CONSTRAINT `id_responsavel2` FOREIGN KEY (`id_responsavel2` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);