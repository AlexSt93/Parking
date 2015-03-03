-- MySQL Script generated by MySQL Workbench
-- 01/29/15 16:39:49
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Parking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Parking` ;
CREATE SCHEMA IF NOT EXISTS `Parking` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Parking` ;

-- -----------------------------------------------------
-- Table `Parking`.`Parking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Parking`.`Parking` ;

CREATE TABLE IF NOT EXISTS `Parking`.`Parking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(45) NULL,
  `capacity` INT NOT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Parking`.`Place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Parking`.`Place` ;

CREATE TABLE IF NOT EXISTS `Parking`.`Place` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `status` INT NULL,
  `Area_id` INT NOT NULL,
  `areaName` VARCHAR(45) NOT NULL,
  `rangeArea` INT NOT NULL,
  `Parking_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Area_id`, `Parking_id`),
  INDEX `fk_Place_Parking_idx` (`Parking_id` ASC),
  CONSTRAINT `fk_Place_Parking`
    FOREIGN KEY (`Parking_id`)
    REFERENCES `Parking`.`Parking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
