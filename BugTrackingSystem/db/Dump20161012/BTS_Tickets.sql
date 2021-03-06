-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: BTS
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Tickets`
--

DROP TABLE IF EXISTS `Tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tickets` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `assignedUser` int(11) DEFAULT NULL,
  `reportedUser` int(11) DEFAULT NULL,
  `patch` longblob,
  `creationDate` datetime DEFAULT NULL,
  `component` int(11) DEFAULT NULL,
  `isPatchAttached` bit(1) DEFAULT b'0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `assignedUser_idx` (`assignedUser`),
  KEY `reportedUser_idx` (`reportedUser`),
  KEY `componentTicket_idx` (`component`),
  KEY `statusTicket_idx` (`status`),
  KEY `priorityTicket_idx` (`priority`),
  CONSTRAINT `assignUser` FOREIGN KEY (`assignedUser`) REFERENCES `Users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `componentTicket` FOREIGN KEY (`component`) REFERENCES `Component` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `priorityTicket` FOREIGN KEY (`priority`) REFERENCES `Priority` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reportUser` FOREIGN KEY (`reportedUser`) REFERENCES `Users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `statusTicket` FOREIGN KEY (`status`) REFERENCES `Status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-12  1:51:20
