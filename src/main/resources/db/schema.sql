CREATE TABLE IF NOT EXISTS `status_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `created_at` DATE NULL,
  PRIMARY KEY (`id`));