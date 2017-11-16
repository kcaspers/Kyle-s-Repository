-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS Blog;

-- -----------------------------------------------------
-- Schema Blog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Blog`;
USE `Blog` ;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(30) NOT NULL,
  `UserPassword` VARCHAR(250) NOT NULL,
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



-- -----------------------------------------------------
-- Data for table `mydb`.`User`
-- -----------------------------------------------------

INSERT INTO `User` (`UserId`, `Username`, `UserPassword`, `Enabled`) VALUES (1, 'admin', 'admin', 1);
INSERT INTO `User` (`UserId`, `Username`, `UserPassword`, `Enabled`) VALUES (2, 'user', 'user', 1);

update user set userpassword = '$2a$10$2pSId1t.cSvoQyo8LacXju0JT3pg.dzU8pAhEUwkh1FV.gQyMpXYe'where userid = 1;
update user set userpassword = '$2a$10$aLP0qh0nZ9fobPpcN6R8keeyauXrLOYh2/5kU8eavt.0v1Bu0hx66'
where userid = 2;
-- -----------------------------------------------------
-- Data for table `mydb`.`Category`
-- -----------------------------------------------------

INSERT INTO `Category` (`CategoryId`, `Name`) VALUES (1, 'News');
INSERT INTO `Category` (`CategoryId`, `Name`) VALUES (2, 'Upcoming events');
INSERT INTO `Category` (`CategoryId`, `Name`) VALUES (3, 'Past Events');



-- -----------------------------------------------------
-- Data for table `Status`
-- -----------------------------------------------------

INSERT INTO `Status` (`StatusId`, `Name`) VALUES (1, 'Approved');
INSERT INTO `Status` (`StatusId`, `Name`) VALUES (2, 'Pending');
INSERT INTO `Status` (`StatusId`, `Name`) VALUES (3, 'Rejected');




-- -----------------------------------------------------
-- Data for table `Tag`
-- -----------------------------------------------------

INSERT INTO `Tag` (`TagId`, `Name`) VALUES (1, 'lorem');
INSERT INTO `Tag` (`TagId`, `Name`) VALUES (2, 'hello');
INSERT INTO `Tag` (`TagId`, `Name`) VALUES (3, 'footbal');
INSERT INTO `Tag` (`TagId`, `Name`) VALUES (4, 'blob');




-- -----------------------------------------------------
-- Data for table `Blog_Post`
-- -----------------------------------------------------

INSERT INTO `Blog_Post` (`BlogId`, `Title`, `Content`, `ImagePath`, `PublishDate`, `ExpDate`, `UserId`, `CategoryId`, `StatusId`) VALUES (1, 'Now at Gallery', 'Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant suo pensitatis immortalem. ', NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `Blog_Post` (`BlogId`, `Title`, `Content`, `ImagePath`, `PublishDate`, `ExpDate`, `UserId`, `CategoryId`, `StatusId`) VALUES (2, 'Tomorrow', 'Ignem ratio fingo istam etc vix una velut veris deo. Teneri habeam perire putavi to cogito sentio me ac. Ad potuisse in ne supponit loquendo me. Sed vixque rei verbis dicunt dat jam. Animal angeli sumpti animum cau exigui sim figura duo ita. Ad attingere objectiva pertinent an si abducerem. ', 'https://www.moma.org/media/W1siZiIsIjQwNjc3MSJdLFsicCIsImNvbnZlcnQiLCItcmVzaXplIDIwMDB4MjAwMFx1MDAzZSJdXQ.jpg?sha=b049afd2b3fc940a', NULL, NULL, 1, 3, 1);
INSERT INTO `Blog_Post` (`BlogId`, `Title`, `Content`, `ImagePath`, `PublishDate`, `ExpDate`, `UserId`, `CategoryId`, `StatusId`) VALUES (3, 'Other', 'Purgantur cupientem mo considero me occurrere. Priusquam voluntate ero dum jam fal cognoscam alligatus efficitur. Ac aequo tangi de manum ignis. Sit qua dici dem gnum unde. Tollentur de ea sanguinem differant to meditatas. Nocturna earundem ha repetere se conceptu assidere im secundum ex. Ita ens volent qualem videam. Imaginari du clausulas se experimur solvendae et opinionis ii devinctam. Tius visu nec tur ulla tam. ', NULL, NULL, NULL, 2, 2, 3);



-- -----------------------------------------------------
-- Data for table `Blog_Post_Tag`
-- -----------------------------------------------------

INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (1, 1);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (1, 2);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (1, 3);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (2, 2);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (2, 4);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (3, 1);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (3, 2);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (3, 3);
INSERT INTO `Blog_Post_Tag` (`BlogId`, `TagId`) VALUES (3, 4);



-- -----------------------------------------------------
-- Data for table `Static_Page`
-- -----------------------------------------------------

INSERT INTO `Static_Page` (`StaticPageId`, `Name`, `Content`, `UserId`) VALUES (1, 'About', 'Dixi sunt apud regi seu uno jam casu. Obnoxius me vi revocari tenebras si. Numerum effectu ad is fallere necesse alteram. Rom possit numeri sic demens sui. Data gi haud agam olim ex esse. Ea quidni fallit gi scient ut. Necessitas offerendum is ea blandisque ut extensarum ab notionibus. Una reliqua cum allatis ponamus ejusque. ', 2);
INSERT INTO `Static_Page` (`StaticPageId`, `Name`, `Content`, `UserId`) VALUES (2, 'News', 'Mea judiciis caeteras tum experiar. Consumerem ob gi mo designabam re respondebo incidissem cogitantem. Procedat eo concludi habuimus id habendae potuisse. Divinae sumamus dicetur ac retinet vi de. Cogitandi argumenti judicarem ex ii. Perciperem attigerint deprehendi mo de realitatis hauriantur gi ob. Ex meditari percipio secundum exsurgit ne. ', 1);

-- -----------------------------------------------------
-- Data for table `Authorities`
-- -----------------------------------------------------

INSERT INTO `Authorities` (`Username`, `Authority`) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `Authorities` (`Username`, `Authority`) VALUES ('admin', 'ROLE_USER');
INSERT INTO `Authorities` (`Username`, `Authority`) VALUES ('user', 'ROLE_USER');

