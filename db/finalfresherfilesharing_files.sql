-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: finalfresherfilesharing
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `dateupload` datetime DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  `id_type` int(11) NOT NULL,
  `sharing` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_file_idx` (`user_id`),
  KEY `FKaty6roy7josrpv01akx19s3hr` (`id_type`),
  CONSTRAINT `FK_category_file` FOREIGN KEY (`id_type`) REFERENCES `categories_type` (`id`),
  CONSTRAINT `FKaty6roy7josrpv01akx19s3hr` FOREIGN KEY (`id_type`) REFERENCES `categories_type` (`id`),
  CONSTRAINT `FKkw4jwo1uukj6uwrdxsv6u3qjt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_file` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (53,'file1.txt','file1.txt1489676349680',97.65625,31,'2017-03-16 00:00:00',0,1,0,NULL),(54,'file2.txt','file2.txt1489676356233',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(55,'file3.txt','file3.txt1489676366043',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(56,'file5.txt','file5.txt1489676377223',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(57,'file4.txt','file4.txt1489676381357',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(58,'file6.txt','file6.txt1489676388165',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(59,'file7.txt','file7.txt1489676390968',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(60,'file8.txt','file8.txt1489676395058',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(61,'file9.txt','file9.txt1489676398270',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(62,'file10.txt','file10.txt1489676409351',97.65625,31,'2017-03-16 00:00:00',1,1,0,NULL),(63,'file1.txt','file1.txt1489676551794',97.65625,31,'2017-03-16 00:00:00',1,1,1,NULL),(64,'.project','.project1489676571967',0.978515625,31,'2017-03-16 00:00:00',1,1,0,NULL),(65,'.springBeans','.springBeans1489676578395',0.4072265625,31,'2017-03-16 00:00:00',1,1,0,NULL),(80,'file11.jpg','file11.jpg1489676944934',97.65625,32,'2017-03-16 00:00:00',1,1,0,NULL);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-16 22:46:06
