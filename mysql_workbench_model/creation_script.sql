-- MySQL Script generated by MySQL Workbench
-- Tue Sep 13 21:59:53 2022
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
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`territorial_sector` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`location` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `district_number` INT NOT NULL,
  `street_name` VARCHAR(127) NOT NULL,
  `height` VARCHAR(15) NOT NULL,
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
  `location_id` INT NOT NULL,
  `territorial_sector_id` INT NOT NULL,
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
-- Table `grupo7`.`notification_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`notification_method` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`notification_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`notification_method` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`contact` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(31) NULL,
  `email` VARCHAR(127) NULL,
  `method_id` INT NOT NULL,
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
    ON UPDATE NO ACTION,
  CONSTRAINT `contact_method_fk`
    FOREIGN KEY (`method_id`)
    REFERENCES `grupo7`.`notification_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`contact` (`id` ASC) VISIBLE;

CREATE INDEX `contact_organization_fk_idx` ON `grupo7`.`contact` (`organization_id` ASC) VISIBLE;

CREATE INDEX `contact_member_fk_idx` ON `grupo7`.`contact` (`member_id` ASC) VISIBLE;

CREATE INDEX `contact_method_fk_idx` ON `grupo7`.`contact` (`method_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`territorial_sector_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`territorial_sector_agent` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`territorial_sector_agent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `territorial_sector_id` INT NOT NULL,
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
  `activity` VARCHAR(63) NULL,
  `scope` VARCHAR(63) NULL,
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
  `organization_id` INT NOT NULL,
  `consumption_type_id` INT NOT NULL,
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
-- Table `grupo7`.`journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`journey` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`journey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `starting_location_id` INT NOT NULL,
  `ending_location_id` INT NOT NULL,
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
-- Table `grupo7`.`carbon_footprint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`carbon_footprint` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`carbon_footprint` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  `date` DATE NOT NULL,
  `organization_id` INT NULL,
  `journey_id` INT NULL,
  `activity_data_id` INT NULL,
  `member_id` INT NULL,
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
  CONSTRAINT `carbon_footprint_journey_fk`
    FOREIGN KEY (`journey_id`)
    REFERENCES `grupo7`.`journey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `carbon_footprint_activity_data_fk`
    FOREIGN KEY (`activity_data_id`)
    REFERENCES `grupo7`.`activity_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`carbon_footprint` (`id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_organization_fk_idx` ON `grupo7`.`carbon_footprint` (`organization_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_member_fk_idx` ON `grupo7`.`carbon_footprint` (`member_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_journey_fk_idx` ON `grupo7`.`carbon_footprint` (`journey_id` ASC) VISIBLE;

CREATE INDEX `carbon_footprint_activity_data_fk_idx` ON `grupo7`.`carbon_footprint` (`activity_data_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`simple_unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`simple_unit` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`simple_unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `base_unit` VARCHAR(15) NOT NULL,
  `exponent` TINYINT(8) NOT NULL,
  `proportionality` VARCHAR(15) NOT NULL,
  `carbon_footprint_id` INT NULL,
  `consumption_type_id` INT NULL,
  `emission_factor_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `simple_unit_carbon_footprint_fk`
    FOREIGN KEY (`carbon_footprint_id`)
    REFERENCES `grupo7`.`carbon_footprint` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `simple_unit_consumption_type_fk`
    FOREIGN KEY (`consumption_type_id`)
    REFERENCES `grupo7`.`consumption_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `simple_unit_emission_factor_fk`
    FOREIGN KEY (`emission_factor_id`)
    REFERENCES `grupo7`.`emission_factor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`simple_unit` (`id` ASC) VISIBLE;

CREATE INDEX `simple_unit_carbon_footprint_fk_idx` ON `grupo7`.`simple_unit` (`carbon_footprint_id` ASC) VISIBLE;

CREATE INDEX `simplie_unit_consumption_type_fk_idx` ON `grupo7`.`simple_unit` (`consumption_type_id` ASC) VISIBLE;

CREATE INDEX `simple_unit_emission_factor_fk_idx` ON `grupo7`.`simple_unit` (`emission_factor_id` ASC) VISIBLE;


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
-- Table `grupo7`.`particular_vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`particular_vehicle` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`particular_vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(15) NOT NULL,
  `fuel` VARCHAR(31) NOT NULL,
  `fuel_consumption_per_km` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`particular_vehicle` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`hired_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`hired_service` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`hired_service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(15) NOT NULL,
  `name` VARCHAR(63) NOT NULL,
  `fuel_consumption_per_km` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`hired_service` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`eco_friendly`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`eco_friendly` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`eco_friendly` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(15) NOT NULL,
  `fuel_consumption_per_km` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`eco_friendly` (`id` ASC) VISIBLE;


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
  `stop_number` SMALLINT NOT NULL,
  `location_id` INT NOT NULL,
  `line_id` INT NOT NULL,
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
-- Table `grupo7`.`public_transport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`public_transport` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`public_transport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fuel_consumption_per_km` DOUBLE NOT NULL,
  `starting_stop_id` INT NOT NULL,
  `ending_stop_id` INT NOT NULL,
  `line_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `public_transport_starting_stop_fk`
    FOREIGN KEY (`starting_stop_id`)
    REFERENCES `grupo7`.`stop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `public_transport_ending_stop`
    FOREIGN KEY (`ending_stop_id`)
    REFERENCES `grupo7`.`stop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `public_transport_line_fk`
    FOREIGN KEY (`line_id`)
    REFERENCES `grupo7`.`line` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`public_transport` (`id` ASC) VISIBLE;

CREATE INDEX `public_transport_starting_stop_fk_idx` ON `grupo7`.`public_transport` (`starting_stop_id` ASC) VISIBLE;

CREATE INDEX `public_transport_ending_stop_idx` ON `grupo7`.`public_transport` (`ending_stop_id` ASC) VISIBLE;

CREATE INDEX `public_transport_line_fk_idx` ON `grupo7`.`public_transport` (`line_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `grupo7`.`leg`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `grupo7`.`leg` ;

CREATE TABLE IF NOT EXISTS `grupo7`.`leg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL,
  `starting_location_id` INT NOT NULL,
  `ending_location_id` INT NOT NULL,
  `particular_vehicle_id` INT NULL,
  `hired_service_id` INT NULL,
  `eco_friendly_id` INT NULL,
  `public_transport_id` INT NULL,
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
  CONSTRAINT `leg_particular_vehicle_fk`
    FOREIGN KEY (`particular_vehicle_id`)
    REFERENCES `grupo7`.`particular_vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_hired_service_fk`
    FOREIGN KEY (`hired_service_id`)
    REFERENCES `grupo7`.`hired_service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_eco_friendly_fk`
    FOREIGN KEY (`eco_friendly_id`)
    REFERENCES `grupo7`.`eco_friendly` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_public_transport_fk`
    FOREIGN KEY (`public_transport_id`)
    REFERENCES `grupo7`.`public_transport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `leg_journey_fk`
    FOREIGN KEY (`journey_id`)
    REFERENCES `grupo7`.`journey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `grupo7`.`leg` (`id` ASC) VISIBLE;

CREATE INDEX `leg_starting_location_fk_idx` ON `grupo7`.`leg` (`starting_location_id` ASC) VISIBLE;

CREATE INDEX `leg_ending_location_fk_idx` ON `grupo7`.`leg` (`ending_location_id` ASC) VISIBLE;

CREATE INDEX `leg_particular_vehicle_fk_idx` ON `grupo7`.`leg` (`particular_vehicle_id` ASC) VISIBLE;

CREATE INDEX `leg_hired_service_fk_idx` ON `grupo7`.`leg` (`hired_service_id` ASC) VISIBLE;

CREATE INDEX `leg_eco_friendly_fk_idx` ON `grupo7`.`leg` (`eco_friendly_id` ASC) VISIBLE;

CREATE INDEX `leg_public_transport_fk_idx` ON `grupo7`.`leg` (`public_transport_id` ASC) VISIBLE;

CREATE INDEX `leg_journey_fk_idx` ON `grupo7`.`leg` (`journey_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
