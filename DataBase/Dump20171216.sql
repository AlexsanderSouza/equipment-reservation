-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: locacao
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao`
--

LOCK TABLES `funcao` WRITE;
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` VALUES (1,'Secretaria','professor',1),(2,'Estoque','acessos ',1);
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcao_permissao`
--

DROP TABLE IF EXISTS `funcao_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcao_permissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_funcao` int(10) unsigned DEFAULT NULL,
  `id_permissao` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_permissao_funcao_permissao` (`id_permissao`),
  KEY `id_funcao_funcao_permissao` (`id_funcao`),
  CONSTRAINT `id_funcao` FOREIGN KEY (`id_funcao`) REFERENCES `funcao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_permissao` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao_permissao`
--

LOCK TABLES `funcao_permissao` WRITE;
/*!40000 ALTER TABLE `funcao_permissao` DISABLE KEYS */;
INSERT INTO `funcao_permissao` VALUES (1,1,1),(2,2,2),(3,2,1);
/*!40000 ALTER TABLE `funcao_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituicao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituicao`
--

LOCK TABLES `instituicao` WRITE;
/*!40000 ALTER TABLE `instituicao` DISABLE KEYS */;
INSERT INTO `instituicao` VALUES (1,'Faculdade Alfa','teste@teste.com','(62) 99999-9999',NULL);
/*!40000 ALTER TABLE `instituicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES (1,'Professor','Acessos',1),(2,'Administrador','Acessos',1);
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `etiqueta` varchar(100) DEFAULT NULL,
  `observacao` varchar(100) DEFAULT NULL,
  `id_unidade` int(10) unsigned NOT NULL,
  `id_tipo_recurso` int(10) unsigned NOT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_tipo_recurso_recurso` (`id_tipo_recurso`),
  KEY `id_unidade_recurso` (`id_unidade`),
  CONSTRAINT `id_tipo_recurso` FOREIGN KEY (`id_tipo_recurso`) REFERENCES `tipo_recurso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_unidade` FOREIGN KEY (`id_unidade`) REFERENCES `unidade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'1','primeiro cadastro',1,1,'1'),(2,'2','segundo cadastro',1,1,'1'),(3,'3','terceiro cadastro',1,1,'1'),(4,'1','primeiro cadastro',1,2,'1'),(5,'2','segundo cadastro',1,2,'1'),(6,'1','primeiro cadastro g',1,3,'1');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repeticao`
--

DROP TABLE IF EXISTS `repeticao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repeticao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_reserva_origem` int(10) unsigned NOT NULL,
  `id_reserva_new` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_reserva_origem_repeticao` (`id_reserva_origem`),
  KEY `id_reserva_new_repeticao` (`id_reserva_new`),
  CONSTRAINT `id_reserva_new` FOREIGN KEY (`id_reserva_new`) REFERENCES `reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_reserva_origem` FOREIGN KEY (`id_reserva_origem`) REFERENCES `reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repeticao`
--

LOCK TABLES `repeticao` WRITE;
/*!40000 ALTER TABLE `repeticao` DISABLE KEYS */;
/*!40000 ALTER TABLE `repeticao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_responsavel` int(10) unsigned NOT NULL,
  `id_destinatario` int(10) unsigned NOT NULL,
  `id_recurso` int(10) unsigned NOT NULL,
  `data_hora_reserva` datetime NOT NULL,
  `data_hora_final` datetime NOT NULL,
  `repeticao` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `data_reserva` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fim` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_recurso_reserva` (`id_recurso`),
  KEY `id_destinatario_reserva` (`id_destinatario`),
  KEY `id_responsavel_reserva` (`id_responsavel`),
  CONSTRAINT `id_destinatario` FOREIGN KEY (`id_destinatario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_recurso` FOREIGN KEY (`id_recurso`) REFERENCES `recurso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_responsavel` FOREIGN KEY (`id_responsavel`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (30,3,3,1,'2017-12-11 08:00:00','2017-12-11 09:00:00','EVENTO UNICO','ATIVO',NULL,NULL,NULL),(31,3,3,1,'2017-12-15 09:00:00','2017-12-15 10:00:00','EVENTO UNICO','ATIVO',NULL,NULL,NULL),(32,3,3,2,'2017-12-11 08:00:00','2017-12-11 09:00:00','EVENTO UNICO','ATIVO',NULL,NULL,NULL);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restricao_recurso`
--

DROP TABLE IF EXISTS `restricao_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restricao_recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipo_recurso2` int(10) unsigned NOT NULL,
  `id_funcao2` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_tipo_recurso2_restricao_recurso` (`id_tipo_recurso2`),
  KEY `id_funcao2_restricao_recurso` (`id_funcao2`),
  CONSTRAINT `id_funcao2` FOREIGN KEY (`id_funcao2`) REFERENCES `funcao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_tipo_recurso2` FOREIGN KEY (`id_tipo_recurso2`) REFERENCES `tipo_recurso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restricao_recurso`
--

LOCK TABLES `restricao_recurso` WRITE;
/*!40000 ALTER TABLE `restricao_recurso` DISABLE KEYS */;
INSERT INTO `restricao_recurso` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `restricao_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_recurso`
--

DROP TABLE IF EXISTS `tipo_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_recurso`
--

LOCK TABLES `tipo_recurso` WRITE;
/*!40000 ALTER TABLE `tipo_recurso` DISABLE KEYS */;
INSERT INTO `tipo_recurso` VALUES (1,'Cabo Hdmi 2 metros','1','Resistente a agua'),(2,'Mouse','1','gamer'),(3,'Teclado','1','Normal');
/*!40000 ALTER TABLE `tipo_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidade`
--

DROP TABLE IF EXISTS `unidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidade` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  `id_instituicao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_instituicao_unidade` (`id_instituicao`),
  CONSTRAINT `id_instituicao` FOREIGN KEY (`id_instituicao`) REFERENCES `instituicao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade`
--

LOCK TABLES `unidade` WRITE;
/*!40000 ALTER TABLE `unidade` DISABLE KEYS */;
INSERT INTO `unidade` VALUES (1,'Perimetral Norte','teste2@teste.com','(62) 99999-9999','av perimetral','1',1),(2,'Bueno','teste3@teste.com','(62) 99999-9999','Av Multirão','1',1);
/*!40000 ALTER TABLE `unidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `matricula` varchar(100) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  `id_funcao` int(10) unsigned NOT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_funcao_usuario` (`id_funcao`),
  CONSTRAINT `id_funcao_user` FOREIGN KEY (`id_funcao`) REFERENCES `funcao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Wigor Paulo','12345','123','teste@te.c.o.m','(62) 99999-999','1',1,'Ok'),(2,'Wigor Paulo','1234','123','teste@teste.com','(62) 99999-9999','1',2,'Ok'),(3,'Danubia','123','123','teste@.com','(99) 99999-9999','1',1,'Ok'),(4,'asdfazsd','51646','123','alexsander@.','(65) 46464-615','1',1,'Ok'),(5,'Alexsander','123456','123456','aaaaaa@aaaaa.com','(99) 99999-9999','1',1,'Ok');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_logado`
--

DROP TABLE IF EXISTS `usuario_logado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_logado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_logado`
--

LOCK TABLES `usuario_logado` WRITE;
/*!40000 ALTER TABLE `usuario_logado` DISABLE KEYS */;
INSERT INTO `usuario_logado` VALUES (1,3);
/*!40000 ALTER TABLE `usuario_logado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_permissao`
--

DROP TABLE IF EXISTS `usuario_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_permissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_usuario2` int(10) unsigned DEFAULT NULL,
  `id_permissao2` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_usuario2_usuario_permissao` (`id_usuario2`),
  KEY `id_permissao2_usuario_permissao` (`id_permissao2`),
  CONSTRAINT `id_permissao2` FOREIGN KEY (`id_permissao2`) REFERENCES `permissao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_usuario2` FOREIGN KEY (`id_usuario2`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_permissao`
--

LOCK TABLES `usuario_permissao` WRITE;
/*!40000 ALTER TABLE `usuario_permissao` DISABLE KEYS */;
INSERT INTO `usuario_permissao` VALUES (1,4,1),(2,4,1),(9,3,1),(22,1,1),(23,5,1),(24,5,2);
/*!40000 ALTER TABLE `usuario_permissao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-16 19:46:58
