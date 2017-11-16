-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS Blog_Database_test;

-- -----------------------------------------------------
-- Schema Blog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Blog_Database_test`;
USE `Blog_Database_test` ;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(30) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `Enabled` TINYINT NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `Username` (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Category` (
  `CategoryId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CategoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Status` (
  `StatusId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`StatusId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Blog_Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Blog_Post` (
  `BlogId` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(50) NULL,
  `Content` LONGTEXT NULL,
  `ImagePath` VARCHAR(255) NULL,
  `PublishDate` DATE NULL,
  `ExpDate` DATE NULL,
  `UserId` INT NOT NULL,
  `CategoryId` INT NOT NULL,
  `StatusId` INT NOT NULL,
  PRIMARY KEY (`BlogId`),
    FOREIGN KEY (`UserId`)
    REFERENCES `User` (`UserId`),
    FOREIGN KEY (`CategoryId`)
    REFERENCES `Category` (`CategoryId`),
    FOREIGN KEY (`StatusId`)
    REFERENCES `Status` (`StatusId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tag` (
  `TagId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TagId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Blog_Post_Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Blog_Post_Tag` (
  `BlogId` INT NOT NULL,
  `TagId` INT NOT NULL,
    FOREIGN KEY (`BlogId`)
    REFERENCES `Blog_Post` (`BlogId`),
    FOREIGN KEY (`TagId`)
    REFERENCES `Tag` (`TagId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Static_Page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Static_Page` (
  `StaticPageId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Content` LONGTEXT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`StaticPageId`),
    FOREIGN KEY (`UserId`)
    REFERENCES `User` (`UserId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Authorities` (
  `Username` VARCHAR(30) NOT NULL,
  `Authority` VARCHAR(30) NOT NULL,
    FOREIGN KEY (`Username`)
    REFERENCES `User` (`Username`),
    KEY `Username` (`Username`))
ENGINE = InnoDB;
