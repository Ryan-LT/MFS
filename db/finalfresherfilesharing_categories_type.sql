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
-- Table structure for table `categories_type`
--

DROP TABLE IF EXISTS `categories_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpikl0j3jmyfanl3147grxy6u` (`category_id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKpikl0j3jmyfanl3147grxy6u` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_type`
--

LOCK TABLES `categories_type` WRITE;
/*!40000 ALTER TABLE `categories_type` DISABLE KEYS */;
INSERT INTO `categories_type` VALUES (1,'opeico',1),(2,'docx',1),(3,'me',1),(4,'sub',1),(5,'save',1),(6,'text',1),(7,'log',1),(8,'txt',1),(9,'rtf',1),(10,'doc',1),(11,'wpd',1),(12,'sdw',1),(13,'sxw',1),(14,'sam',1),(15,'xml',1),(16,'xhtml',1),(17,'htm',1),(18,'html',1),(19,'.jpg',2),(20,'jfif',2),(21,'jpeg',2),(22,'exif',2),(23,'bpg',2),(24,'png',2),(25,'gif',2),(26,'svg',2),(27,'tif',2),(28,'tiff',2),(29,'bmp',2),(30,'wpg',2),(31,'pcx',2),(32,'xls',3),(33,'xlsm',3),(34,'xlsb',3),(35,'def',3),(36,'wkq',3),(37,'qpw',3),(38,'sdc',3),(39,'wks',3),(40,'ppt',4),(41,'shw',4),(42,'sdd',4),(43,'wm',5),(44,'wma',5),(45,'wmv',5),(46,'3gp',5),(47,'aac',5),(48,'fla',5),(49,'flac',5),(50,'mkv',5),(51,'flv',5),(52,'mov',5),(53,'mp3',5),(54,'mp4',5),(55,'wav',5),(56,'ps',5),(57,'pdf',5),(58,'mpg',5),(59,'mpeg',5),(60,'zip',6),(61,'bz2',6),(62,'rar',6),(63,'7z',6),(64,'gz',6),(65,'z',6),(66,'Other',7);
/*!40000 ALTER TABLE `categories_type` ENABLE KEYS */;
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
