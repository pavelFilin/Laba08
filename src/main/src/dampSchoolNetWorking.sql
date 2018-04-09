-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.10-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for schoolnetwork2
CREATE DATABASE IF NOT EXISTS `schoolnetwork2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `schoolnetwork2`;

-- Dumping structure for table schoolnetwork2.persons
CREATE TABLE IF NOT EXISTS `persons` (
  `uuid` varchar(36) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `secondName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schoolnetwork2.persons: ~5 rows (approximately)
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` (`uuid`, `firstName`, `middleName`, `secondName`) VALUES
	('0dd89a54-ee68-4439-9306-02223cfd4078', 'addd', 'ddddd', 'dsad'),
	('28b491b3-3f53-47cf-a917-1c895dc0fa80', 'Name', 'Middle Name', 'Second Name'),
	('a309a28c-e14a-4372-b282-a02c821ad2d0', 'aaaaaa', 'aaaaa', 'aaa'),
	('cc27df6f-e2cf-4fdc-bbf3-67a451b31055', 'Pavel', 'Dmitrievich', 'Filin'),
	('da96c0ec-eaec-46ee-8099-61a0f436c3b6', 'Loly', 'Pennindy', 'EndLend');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;

-- Dumping structure for table schoolnetwork2.schools
CREATE TABLE IF NOT EXISTS `schools` (
  `uuid` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schoolnetwork2.schools: ~5 rows (approximately)
/*!40000 ALTER TABLE `schools` DISABLE KEYS */;
INSERT INTO `schools` (`uuid`, `name`) VALUES
	('0dd89a54-ee68-4439-9306-02223cfd4078', 'sc'),
	('28b491b3-3f53-47cf-a917-1c895dc0fa80', 'sch 5'),
	('a309a28c-e14a-4372-b282-a02c821ad2d0', 'sc'),
	('cc27df6f-e2cf-4fdc-bbf3-67a451b31055', 'School 9'),
	('da96c0ec-eaec-46ee-8099-61a0f436c3b6', 'School 9');
/*!40000 ALTER TABLE `schools` ENABLE KEYS */;

-- Dumping structure for table schoolnetwork2.years
CREATE TABLE IF NOT EXISTS `years` (
  `uuid` varchar(36) NOT NULL,
  `attendDate` smallint(6) DEFAULT NULL,
  `endDate` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schoolnetwork2.years: ~6 rows (approximately)
/*!40000 ALTER TABLE `years` DISABLE KEYS */;
INSERT INTO `years` (`uuid`, `attendDate`, `endDate`) VALUES
	('0dd89a54-ee68-4439-9306-02223cfd4078', 2006, 2014),
	('28b491b3-3f53-47cf-a917-1c895dc0fa80', 2006, 2014),
	('a309a28c-e14a-4372-b282-a02c821ad2d0', 2005, 2017),
	('cc27df6f-e2cf-4fdc-bbf3-67a451b31055', 2005, 2016),
	('da96c0ec-eaec-46ee-8099-61a0f436c3b6', 2005, 2017);
/*!40000 ALTER TABLE `years` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
