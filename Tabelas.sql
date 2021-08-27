
 CREATE DATABASE Action_For_Life;
 
 USE Action_for_life;
 
 CREATE TABLE `Usuário` (
	`id` int NOT NULL AUTO_INCREMENT,
	`Nome completo` varchar(50) NOT NULL,
	`Email` varchar(50) NOT NULL,
	`Senha` int(8) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Produto` (
	`id` int NOT NULL AUTO_INCREMENT,
	`Nome` varchar(50) NOT NULL,
	`Marca` varchar(50) NOT NULL,
	`Descrição` varchar(255) NOT NULL,
	`Preco` DECIMAL(10.2) NOT NULL,
	`id_categoria` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Categoria` (
	`id` int NOT NULL AUTO_INCREMENT,
	`Plantas` varchar(50) NOT NULL,
	`Vestuário` varchar(50) NOT NULL,
	`Bijuterias` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Produto` ADD CONSTRAINT `Produto_fk0` FOREIGN KEY (`id_categoria`) REFERENCES `Categoria`(`id`);

