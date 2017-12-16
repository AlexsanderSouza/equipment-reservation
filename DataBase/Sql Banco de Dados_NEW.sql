DROP SCHEMA `locacao`;

CREATE SCHEMA `locacao` ;

CREATE TABLE usuario_logado(
id INT NOT NULL AUTO_INCREMENT,
usuario_id INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);
CREATE TABLE instituicao(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(100),
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE funcao(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE permissao(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(100),
ativo boolean,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE tipo_recurso(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

CREATE TABLE unidade(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(100),
endereco VARCHAR(200) NULL,
ativo VARCHAR(1),
id_instituicao INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_instituicao_unidade` (`id_instituicao` ASC) , 
	CONSTRAINT `id_instituicao_unidade` FOREIGN KEY (`id_instituicao` ) REFERENCES `instituicao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE  
);

CREATE TABLE usuario(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
matricula VARCHAR(100),
senha VARCHAR(45),
email VARCHAR(100),
telefone VARCHAR(100),
ativo VARCHAR(1),
id_funcao INT NOT NULL,
status varchar(25),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC) ,
INDEX `id_funcao_usuario` (`id_funcao` ASC) ,
	CONSTRAINT `id_funcao_usuario` FOREIGN KEY (`id_funcao` ) REFERENCES `funcao` (`id` )
);

CREATE TABLE funcao_permissao(
id INT NOT NULL AUTO_INCREMENT,
id_funcao INT,
id_permissao INT,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_permissao_funcao_permissao` (`id_permissao` ASC) ,
	CONSTRAINT `id_permissao_funcao_permissao` FOREIGN KEY (`id_permissao` ) REFERENCES `permissao` (`id` )
	ON DELETE NO ACTION ON UPDATE NO ACTION,
INDEX `id_funcao_funcao_permissao` (`id_funcao` ASC) , 
	CONSTRAINT `id_funcao_funcao_permissao` FOREIGN KEY (`id_funcao` ) REFERENCES `funcao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE recurso(
id INT NOT NULL AUTO_INCREMENT,
etiqueta VARCHAR(100) NULL,
observacao VARCHAR(100) NULL,
id_unidade INT NOT NULL,
id_tipo_recurso INT NOT NULL,
ativo VARCHAR(1),
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_tipo_recurso_recurso` (`id_tipo_recurso` ASC) , 
	CONSTRAINT `id_tipo_recurso_recurso` FOREIGN KEY (`id_tipo_recurso` ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_unidade_recurso` (`id_unidade` ASC) , 
	CONSTRAINT `id_unidade_recurso` FOREIGN KEY (`id_unidade` ) REFERENCES `unidade` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reserva(
id INT NOT NULL AUTO_INCREMENT,
id_responsavel INT NOT NULL,
id_destinatario INT NOT NULL,
id_recurso INT NOT NULL,
data_hora_reserva DATETIME NOT NULL,
data_hora_final DATETIME NOT NULL,
repeticao VARCHAR(100), -- EVENTO UNICO / SEMANALMENTE A CADA SEGUNDA / SEMANALMENTE A CADA TERCA / ...TODOS OS DIAS DA SEMANA / TODOS OS DIAS
status VARCHAR(50), -- ATIVO / PENDENTE / CONCLUIDO
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_recurso_reserva` (`id_recurso` ASC) , 
	CONSTRAINT `id_recurso_reserva` FOREIGN KEY (`id_recurso` ) REFERENCES `recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_destinatario_reserva` (`id_destinatario` ASC) , 
	CONSTRAINT `id_destinatario_reserva` FOREIGN KEY (`id_destinatario` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,	
INDEX `id_responsavel_reserva` (`id_responsavel` ASC) , 
	CONSTRAINT `id_responsavel_reserva` FOREIGN KEY (`id_responsavel` ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE restricao_recurso(
id INT NOT NULL AUTO_INCREMENT,
id_tipo_recurso2 INT NOT NULL,
id_funcao2 INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_tipo_recurso2_restricao_recurso` (id_tipo_recurso2 ASC) , 
	CONSTRAINT `id_tipo_recurso2_restricao_recurso` FOREIGN KEY (id_tipo_recurso2 ) REFERENCES `tipo_recurso` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_funcao2_restricao_recurso` (id_funcao2 ASC) , 
	CONSTRAINT `id_funcao2_restricao_recurso` FOREIGN KEY (id_funcao2 ) REFERENCES `funcao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE repeticao(
id INT NOT NULL AUTO_INCREMENT,
id_reserva_origem INT NOT NULL,
id_reserva_new INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_reserva_origem_repeticao` (id_reserva_origem ASC) , 
	CONSTRAINT `id_reserva_origem_repeticao` FOREIGN KEY (id_reserva_origem ) REFERENCES `reserva` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_reserva_new_repeticao` (id_reserva_new ASC) , 
	CONSTRAINT `id_reserva_new_repeticao` FOREIGN KEY (id_reserva_new ) REFERENCES `reserva` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usuario_permissao(
id INT NOT NULL AUTO_INCREMENT,
id_usuario2 INT DEFAULT NULL,
id_permissao2 INT DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC),
INDEX `id_usuario2_usuario_permissao` (id_usuario2 ASC) , 
	CONSTRAINT `id_usuario2_usuario_permissao` FOREIGN KEY (id_usuario2 ) REFERENCES `usuario` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `id_permissao2_usuario_permissao` (id_permissao2 ASC) , 
	CONSTRAINT `id_permissao2_usuario_permissao` FOREIGN KEY (id_permissao2 ) REFERENCES `permissao` (`id` )
	ON DELETE CASCADE ON UPDATE CASCADE	
);

ALTER TABLE tipo_recurso ADD descricao varchar(100);

INSERT INTO usuario_logado (usuario_id) values(0);

DROP TABLE repeticao;

CREATE TABLE locacao.repeticao (
id INT NOT NULL AUTO_INCREMENT,
id_reserva_origem INT NOT NULL,
id_reserva_new INT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id` ASC)
);

ALTER TABLE reserva ADD COLUMN data_reserva date;
ALTER TABLE reserva ADD COLUMN hora_inicio time;
ALTER TABLE reserva ADD COLUMN hora_fim time;