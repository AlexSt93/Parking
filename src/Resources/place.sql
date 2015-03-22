CREATE TABLE IF NOT EXISTS `Parking`.`Place` (
  `id` INT NOT NULL,
  `status` INT NULL,
  `areaName` VARCHAR(45) NULL,
  `areaRange` INT NULL,
  `placeSize` INT NULL,
  `placePosition` INT NULL,
  `parkingId` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;