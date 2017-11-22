-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema transporte
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `transporte` ;

-- -----------------------------------------------------
-- Schema transporte
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transporte` DEFAULT CHARACTER SET utf8 ;
USE `transporte` ;

-- -----------------------------------------------------
-- Table `transporte`.`Empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`Empresa` ;

CREATE TABLE IF NOT EXISTS `transporte`.`Empresa` (
  `idEmpresa` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(120) NULL,
  `CNPJ` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpresa`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`Motorista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`Motorista` ;

CREATE TABLE IF NOT EXISTS `transporte`.`Motorista` (
  `idMotorista` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(120) NULL,
  `apelido` VARCHAR(100) NULL,
  `login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `idEmpresa` INT NOT NULL,
  PRIMARY KEY (`idMotorista`),
  INDEX `fk_Motorista_Empresa1_idx` (`idEmpresa` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  CONSTRAINT `fk_Motorista_Empresa1`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `transporte`.`Empresa` (`idEmpresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`Passageiro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`Passageiro` ;

CREATE TABLE IF NOT EXISTS `transporte`.`Passageiro` (
  `idPassageiro` INT NOT NULL,
  `Nome` VARCHAR(120) NULL,
  `login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `cpf` VARCHAR(11) NULL,
  `telefone` VARCHAR(13) NULL,
  `idEmpresa` INT NOT NULL,
  PRIMARY KEY (`idPassageiro`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_Passageiro_Empresa1_idx` (`idEmpresa` ASC),
  CONSTRAINT `fk_Passageiro_Empresa1`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `transporte`.`Empresa` (`idEmpresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`rota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`rota` ;

CREATE TABLE IF NOT EXISTS `transporte`.`rota` (
  `idrota` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(120) NULL,
  `idMotorista` INT NOT NULL,
  PRIMARY KEY (`idrota`),
  INDEX `fk_rota_Motorista1_idx` (`idMotorista` ASC),
  CONSTRAINT `fk_rota_Motorista1`
    FOREIGN KEY (`idMotorista`)
    REFERENCES `transporte`.`Motorista` (`idMotorista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`Viagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`Viagem` ;

CREATE TABLE IF NOT EXISTS `transporte`.`Viagem` (
  `idViagem` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NULL,
  `saida` TIME NULL,
  `chegada` TIME NULL,
  `dirigindo` TINYINT NULL,
  `idrota` INT NOT NULL,
  `ida` TINYINT NULL,
  PRIMARY KEY (`idViagem`),
  INDEX `fk_Viagem_rota1_idx` (`idrota` ASC),
  CONSTRAINT `fk_Viagem_rota1`
    FOREIGN KEY (`idrota`)
    REFERENCES `transporte`.`rota` (`idrota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`Passageiro_Viagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte`.`Passageiro_Viagem` ;

CREATE TABLE IF NOT EXISTS `transporte`.`Passageiro_Viagem` (
  `idViagem` INT NOT NULL,
  `idPassageiro` INT NOT NULL,
  `status` INT NULL,
  `confirmacao` TINYINT NULL,
  PRIMARY KEY (`idViagem`, `idPassageiro`),
  INDEX `fk_Viagem_has_Passageiro_Passageiro1_idx` (`idPassageiro` ASC),
  INDEX `fk_Viagem_has_Passageiro_Viagem1_idx` (`idViagem` ASC),
  CONSTRAINT `fk_Viagem_has_Passageiro_Viagem1`
    FOREIGN KEY (`idViagem`)
    REFERENCES `transporte`.`Viagem` (`idViagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viagem_has_Passageiro_Passageiro1`
    FOREIGN KEY (`idPassageiro`)
    REFERENCES `transporte`.`Passageiro` (`idPassageiro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


alter table passageiro modify cpf varchar(20);

alter table passageiro modify telefone varchar(20); 

-- views usadas nas tabelas

-- 1 view
create view view_lista_viagem as select passageiro.idPassageiro, passageiro.nome, passageiro.telefone, status, confirmacao, 
passageiro_viagem.idViagem 
							from passageiro join passageiro_viagem
																on passageiro.idPassageiro = passageiro_viagem.idPassageiro;
                                                                
-- 2 view
create view view_motorista_lista as select passageiro.idPassageiro, passageiro.nome, passageiro.telefone, status, confirmacao,
passageiro_viagem.idViagem, motorista.idMotorista from passageiro join passageiro_viagem
on passageiro.idPassageiro = passageiro_viagem.idPassageiro join empresa on passageiro.idEmpresa = empresa.idEmpresa join motorista on
motorista.idEmpresa = empresa.idEmpresa;

-- trigger para impedir o delete do passageiro
delimiter $$
create trigger trg_impede_delete_passageiro before delete on passageiro for each row
begin
	declare msg varchar(50);
	declare _idpassageiro int;
    declare _countviagens int;
    set _idpassageiro = OLD.idPassageiro;
    select count(*) into _countviagens from passageiro_viagem where _idpassageiro = idpassageiro;
    if _countviagens > 0 then
		set msg = concat('Passageiro tem viagens!!!');
        signal sqlstate '45000' set message_text = msg;
    end if; 
end $$ 
delimiter ;

-- trigger para inserir automaticamente na tabela ida e volta
delimiter $$
create trigger trg_insert_ida_volta after insert on viagem for each row
begin
	declare _ida tinyint;
	declare _id int;
    set _id = new.idViagem;
    select ida into _ida from viagem where idViagem = _id;
    if _ida = 0 then
    insert into viagem_ida_volta(idIda, Idvolta) values(_id-1,_id);
    end if;
end $$
delimiter ;

-- trigger que proibe criar viagens se a data ja tiver passado
delimiter $$
create trigger trg_proibir_criar_viagem_no_passado before insert on viagem for each row
begin
	declare msg varchar(50);
    if new.data < current_date() then
		set msg = concat('Viagens para o passado nao podem ser realizadas');
        signal sqlstate '45000' set message_text = msg;
	end if;		
end $$
delimiter ;

-- function que pega a viagem mais recente do motorista (nao utilizada pq o joao mudou o sistema)
delimiter $$
create function encontrar_viagem(id int)
returns int
begin
	declare _id int;
    select idViagem into _id from viagem join rota on rota.idrota = viagem.idrota where idMotorista = id and data <= current_date() order by data desc limit 1;
    return _id;
end $$
delimiter ;

-- procedure que coloca status nas viagens que esta null
delimiter $$
create procedure atualizar_status()
begin
	declare _idviagem int;
    declare _idpassageiro int;
    declare _status int;
    declare done int default 0;
	declare curs cursor for(
		select idViagem, idPassageiro, status from passageiro_viagem
		);
	declare continue handler for not found set done = 1;
	open curs;
    repeat
    fetch curs into _idviagem, _idpassageiro, _status;
     if not done then
		if _status is null then
			select status into _status from passageiro_viagem where idPassageiro = _idpassageiro and idViagem = _idviagem - 1;
            update passageiro_viagem set status = _status where idPassageiro = _idpassageiro and idViagem = _idviagem;
        end if ;
    end if;
	until done end repeat;
    close curs;
end $$
delimiter ;