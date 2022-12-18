-- MySQL Script generated by MySQL Workbench
-- Sun Dec 18 03:24:58 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema grupo7
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `grupo7` ;

-- -----------------------------------------------------
-- Schema grupo7
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `grupo7` DEFAULT CHARACTER SET utf8 ;
USE `grupo7` ;

-- -----------------------------------------------------
-- Table `grupo7`.`territorial_sector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`territorial_sector` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`territorial_sector` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(63) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`territorial_sector` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`location` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `district_id` INT NULL,
  `street_name` VARCHAR(127) NOT NULL,
  `street_height` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`location` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`organization` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `social_objective` VARCHAR(127) NOT NULL,
  `clasification` VARCHAR(63) NULL,
  `type` VARCHAR(31) NULL,
  `location_id` INT NOT NULL,
  `territorial_sector_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `organization_territorial_sector_fk`
    FOREIGN KEY (`territorial_sector_id`)
    REFERENCES `grupo7`.`territorial_sector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `organization_location_fk`
    FOREIGN KEY (`location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`organization` (`id` ASC) VISIBLE;

CREATE INDEX `organization_territorial_sector_fk_idx` ON `grupo7`.`organization` (`territorial_sector_id` ASC) VISIBLE;

CREATE INDEX `organization_location_fk_idx` ON `grupo7`.`organization` (`location_id` ASC) VISIBLE;

CREATE UNIQUE INDEX `social_objective_UNIQUE` ON `grupo7`.`organization` (`social_objective` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`member` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(31) NOT NULL,
  `surname` VARCHAR(31) NOT NULL,
  `doc_type` VARCHAR(31) NOT NULL,
  `doc_number` VARCHAR(31) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`member` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`contact` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(31) NULL,
  `email` VARCHAR(127) NULL,
  `method` VARCHAR(15) NOT NULL,
  `organization_id` INT NULL,
  `member_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `contact_organization_fk`
    FOREIGN KEY (`organization_id`)
    REFERENCES `grupo7`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contact_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`contact` (`id` ASC) VISIBLE;

CREATE INDEX `contact_organization_fk_idx` ON `grupo7`.`contact` (`organization_id` ASC) VISIBLE;

CREATE INDEX `contact_member_fk_idx` ON `grupo7`.`contact` (`member_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`territorial_sector_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`territorial_sector_agent` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`territorial_sector_agent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `territorial_sector_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `territorial_sector_agent_territorial_sector_fk`
    FOREIGN KEY (`territorial_sector_id`)
    REFERENCES `grupo7`.`territorial_sector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`territorial_sector_agent` (`id` ASC) VISIBLE;

CREATE INDEX `territorial_sector_agent_territorial_sector_fk_idx` ON `grupo7`.`territorial_sector_agent` (`territorial_sector_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`emission_factor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`emission_factor` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`emission_factor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`emission_factor` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`consumption_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`consumption_type` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`consumption_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(63) NULL,
  `activity` VARCHAR(127) NULL,
  `scope` VARCHAR(127) NULL,
  `emission_factor_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `consumption_type_emission_factor_fk`
    FOREIGN KEY (`emission_factor_id`)
    REFERENCES `grupo7`.`emission_factor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`consumption_type` (`id` ASC) VISIBLE;

CREATE INDEX `consumption_type_emission_factor_fk_idx` ON `grupo7`.`consumption_type` (`emission_factor_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`activity_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`activity_data` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`activity_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `consumption_value` DOUBLE NOT NULL,
  `periodicity` VARCHAR(15) NOT NULL,
  `periodicity_format` VARCHAR(15) NOT NULL,
  `organization_id` INT NULL,
  `consumption_type_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `activity_data_consumption_type_fk`
    FOREIGN KEY (`consumption_type_id`)
    REFERENCES `grupo7`.`consumption_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `activity_data_organization_fk`
    FOREIGN KEY (`organization_id`)
    REFERENCES `grupo7`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`activity_data` (`id` ASC) VISIBLE;

CREATE INDEX `activity_data_consumption_type_fk_idx` ON `grupo7`.`activity_data` (`consumption_type_id` ASC) VISIBLE;

CREATE INDEX `activity_data_organization_fk_idx` ON `grupo7`.`activity_data` (`organization_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`carbon_footprint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`carbon_footprint` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`carbon_footprint` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  `date` DATE NOT NULL,
  `organization_id` INT NULL,
  `member_id` INT NULL,
  `activity_data_id` INT NULL,
  `territorial_sector_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `carbon_footprint_organization_fk`
    FOREIGN KEY (`organization_id`)
    REFERENCES `grupo7`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `carbon_footprint_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `carbon_footprint_activity_data_fk`
    FOREIGN KEY (`activity_data_id`)
    REFERENCES `grupo7`.`activity_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `carbon_footprint_territorial_sector_fk`
    FOREIGN KEY (`territorial_sector_id`)
    REFERENCES `grupo7`.`territorial_sector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`carbon_footprint` (`id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_organization_fk_idx` ON `grupo7`.`carbon_footprint` (`organization_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_member_fk_idx` ON `grupo7`.`carbon_footprint` (`member_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_activity_data_fk_idx` ON `grupo7`.`carbon_footprint` (`activity_data_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_territorial_sector_fk_idx` ON `grupo7`.`carbon_footprint` (`territorial_sector_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`unit` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `base_unit` VARCHAR(15) NOT NULL,
  `exponent` TINYINT(8) NOT NULL,
  `proportionality` VARCHAR(15) NOT NULL,
  `carbon_footprint_id` INT NULL,
  `consumption_type_id` INT NULL,
  `emission_factor_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `unit_carbon_footprint_fk`
    FOREIGN KEY (`carbon_footprint_id`)
    REFERENCES `grupo7`.`carbon_footprint` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `unit_consumption_type_fk`
    FOREIGN KEY (`consumption_type_id`)
    REFERENCES `grupo7`.`consumption_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `unit_emission_factor_fk`
    FOREIGN KEY (`emission_factor_id`)
    REFERENCES `grupo7`.`emission_factor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`unit` (`id` ASC) VISIBLE;

CREATE INDEX `unit_carbon_footprint_fk_idx` ON `grupo7`.`unit` (`carbon_footprint_id` ASC) VISIBLE;

CREATE INDEX `unit_consumption_type_fk_idx` ON `grupo7`.`unit` (`consumption_type_id` ASC) VISIBLE;

CREATE INDEX `unit_emission_factor_fk_idx` ON `grupo7`.`unit` (`emission_factor_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`sector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`sector` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`sector` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(31) NOT NULL,
  `organization_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sector_organization_fk`
    FOREIGN KEY (`organization_id`)
    REFERENCES `grupo7`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`sector` (`id` ASC) VISIBLE;

CREATE INDEX `sector_organization_fk_idx` ON `grupo7`.`sector` (`organization_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`sector_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`sector_member` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`sector_member` (
  `sector_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`sector_id`, `member_id`),
  CONSTRAINT `sector_member_sector_fk`
    FOREIGN KEY (`sector_id`)
    REFERENCES `grupo7`.`sector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sector_member_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `sector_member_member_fk_idx` ON `grupo7`.`sector_member` (`member_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`journey` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`journey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `starting_location_id` INT NULL,
  `ending_location_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `journey_starting_location_fk`
    FOREIGN KEY (`starting_location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `journey_ending_location_fk`
    FOREIGN KEY (`ending_location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`journey` (`id` ASC) VISIBLE;

CREATE INDEX `journey_starting_location_fk_idx` ON `grupo7`.`journey` (`starting_location_id` ASC) VISIBLE;

CREATE INDEX `journey_ending_location_fk_idx` ON `grupo7`.`journey` (`ending_location_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`member_journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`member_journey` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`member_journey` (
  `member_id` INT NOT NULL,
  `journey_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `journey_id`),
  CONSTRAINT `member_journey_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `member_journey_journey_fk`
    FOREIGN KEY (`journey_id`)
    REFERENCES `grupo7`.`journey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `member_journey_journey_fk_idx` ON `grupo7`.`member_journey` (`journey_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`line` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`line` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(63) NOT NULL,
  `type` VARCHAR(31) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`line` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`stop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`stop` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`stop` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `stop_number` SMALLINT NULL,
  `location_id` INT NOT NULL,
  `line_id` INT NULL,
  `km_to_next_stop` DOUBLE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `stop_location_fk`
    FOREIGN KEY (`location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `stop_line_fk`
    FOREIGN KEY (`line_id`)
    REFERENCES `grupo7`.`line` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`stop` (`id` ASC) VISIBLE;

CREATE INDEX `stop_location_fk_idx` ON `grupo7`.`stop` (`location_id` ASC) VISIBLE;

CREATE INDEX `stop_line_fk_idx` ON `grupo7`.`stop` (`line_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`transport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`transport` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`transport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `transport_type` VARCHAR(31) NOT NULL,
  `fuel_consumption_per_km` DOUBLE NULL,
  `hs_type` VARCHAR(15) NULL,
  `hs_name` VARCHAR(31) NULL,
  `ef_type` VARCHAR(15) NULL,
  `pv_type` VARCHAR(15) NULL,
  `pv_fuel_type` VARCHAR(15) NULL,
  `pt_starting_stop_id` INT NULL,
  `pt_ending_stop_id` INT NULL,
  `pt_line_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `transport_pt_starting_stop_fk`
    FOREIGN KEY (`pt_starting_stop_id`)
    REFERENCES `grupo7`.`stop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transport_pt_ending_stop_fk`
    FOREIGN KEY (`pt_ending_stop_id`)
    REFERENCES `grupo7`.`stop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transport_pt_line_fk`
    FOREIGN KEY (`pt_line_id`)
    REFERENCES `grupo7`.`line` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`transport` (`id` ASC) VISIBLE;

CREATE INDEX `transport_pt_starting_stop_fk_idx` ON `grupo7`.`transport` (`pt_starting_stop_id` ASC) VISIBLE;

CREATE INDEX `transport_pt_ending_stop_fk_idx` ON `grupo7`.`transport` (`pt_ending_stop_id` ASC) VISIBLE;

CREATE INDEX `transport_pt_line_fk_idx` ON `grupo7`.`transport` (`pt_line_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`leg`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`leg` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`leg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NULL,
  `order_in_list` INT NULL,
  `starting_location_id` INT NULL,
  `ending_location_id` INT NULL,
  `transport_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `leg_starting_location_fk`
    FOREIGN KEY (`starting_location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_ending_location_fk`
    FOREIGN KEY (`ending_location_id`)
    REFERENCES `grupo7`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_journey_fk`
    FOREIGN KEY (`journey_id`)
    REFERENCES `grupo7`.`journey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_transport_fk`
    FOREIGN KEY (`transport_id`)
    REFERENCES `grupo7`.`transport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`leg` (`id` ASC) VISIBLE;

CREATE INDEX `leg_starting_location_fk_idx` ON `grupo7`.`leg` (`starting_location_id` ASC) VISIBLE;

CREATE INDEX `leg_ending_location_fk_idx` ON `grupo7`.`leg` (`ending_location_id` ASC) VISIBLE;

CREATE INDEX `leg_journey_fk_idx` ON `grupo7`.`leg` (`journey_id` ASC) VISIBLE;

CREATE INDEX `leg_transport_fk_idx` ON `grupo7`.`leg` (`transport_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`user` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(31) NOT NULL,
  `password` VARCHAR(127) NOT NULL,
  `user_type` VARCHAR(31) NOT NULL,
  `member_id` INT NULL,
  `territorial_sector_agent_id` INT NULL,
  `organization_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_territorial_agent_fk`
    FOREIGN KEY (`territorial_sector_agent_id`)
    REFERENCES `grupo7`.`territorial_sector_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_organization_fk`
    FOREIGN KEY (`organization_id`)
    REFERENCES `grupo7`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`user` (`id` ASC) VISIBLE;

CREATE INDEX `user_member_fk_idx` ON `grupo7`.`user` (`member_id` ASC) VISIBLE;

CREATE INDEX `user_territorial_agent_fk_idx` ON `grupo7`.`user` (`territorial_sector_agent_id` ASC) VISIBLE;

CREATE INDEX `user_organization_fk_idx` ON `grupo7`.`user` (`organization_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`work_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`work_application` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`work_application` (
  `sector_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `state` VARCHAR(31) NOT NULL,
  PRIMARY KEY (`sector_id`, `member_id`),
  CONSTRAINT `work_application_sector_fk`
    FOREIGN KEY (`sector_id`)
    REFERENCES `grupo7`.`sector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `work_application_member_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `grupo7`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `sector_member_member_fk_idx` ON `grupo7`.`work_application` (`member_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`country` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`country` (
  `id` INT NOT NULL,
  `name` VARCHAR(127) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `grupo7`.`province`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`province` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`province` (
  `id` INT NOT NULL,
  `name` VARCHAR(127) NULL,
  `country_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `province_country_fk`
    FOREIGN KEY (`country_id`)
    REFERENCES `grupo7`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `province_country_fk_idx` ON `grupo7`.`province` (`country_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`municipality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`municipality` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`municipality` (
  `id` INT NOT NULL,
  `name` VARCHAR(127) NULL,
  `province_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `municipality_province_fk`
    FOREIGN KEY (`province_id`)
    REFERENCES `grupo7`.`province` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `municipality_province_fk_idx` ON `grupo7`.`municipality` (`province_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`district`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`district` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`district` (
  `id` INT NOT NULL,
  `name` VARCHAR(127) NULL,
  `postal_code` INT NULL,
  `municipality_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `district_municipality_fk`
    FOREIGN KEY (`municipality_id`)
    REFERENCES `grupo7`.`municipality` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `district_municipality_fk_idx` ON `grupo7`.`district` (`municipality_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
