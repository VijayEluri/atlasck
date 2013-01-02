SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `visitor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `visitor` ;

CREATE  TABLE IF NOT EXISTS `visitor` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nickname` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `ip_address` VARCHAR(255) NOT NULL ,
  `created_at` DATETIME NULL ,
  `updated_at` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `visitors_email_unique` (`email` ASC) ,
  INDEX `nickname` (`nickname` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE  TABLE IF NOT EXISTS `question` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `visitor` INT(10) UNSIGNED NOT NULL ,
  `title` VARCHAR(255) NOT NULL ,
  `body` TEXT NOT NULL ,
  `visible` TINYINT(1) NULL DEFAULT '1' ,
  `email_answer` TINYINT(1) NULL DEFAULT '0' ,
  `created_at` DATETIME NULL ,
  `updated_at` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `questions_FKIndex1` (`visitor` ASC) ,
  CONSTRAINT `questions_ibfk_1`
    FOREIGN KEY (`visitor` )
    REFERENCES `visitor` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer` ;

CREATE  TABLE IF NOT EXISTS `answer` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `question` INT(10) UNSIGNED NOT NULL ,
  `answer` TEXT NULL DEFAULT NULL ,
  `created_at` DATETIME NULL ,
  `updated_at` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `answers_FKIndex1` (`question` ASC) ,
  CONSTRAINT `answers_ibfk_1`
    FOREIGN KEY (`question` )
    REFERENCES `question` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
