CREATE TABLE IF NOT EXISTS `Parking`.`Parking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(45) NULL,
  `capacity` INT NOT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

