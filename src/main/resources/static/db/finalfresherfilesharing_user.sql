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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rank_id` int(11) DEFAULT '1',
  `active` int(11) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@gmail.com','$2a$10$BLMrQvHR7Eb6/vWZD7jxTOgpxLchu6kmaY7ZbKg0X4DcQokXeRcCG','admin',2,1,''),(2,'member@gmail.com','$2a$10$Y/PnG/xHP157r6R1zwUPX.hhvmqRuh/Fg3qOvkqRuyNDJIUIaX3MK','member',2,1,''),(4,'vuong@gmail.com','$2a$10$C/y6blVJ39LhEaJdHorDV.G2V4mSVqmijep1xaiHlj8wAF6OR0Gmi','vuong',0,1,'vu'),(5,'v@gmail.com','$2a$10$LhWpi2sGyHJskLZkEjG0XO7ZXgEGe0PeazbhaDoCDm/cnaaLFiiW6','v',0,1,'vu'),(6,'vv@gmail.com','$2a$10$ma5D3Tum4F.KlB2yA/2w..YRFOekBalAbYukf9cCZrcLW/H6pVvTq','vv',0,1,'vv'),(7,'tn@gmail.com','$2a$10$KCnnZIJoZ49TsuhXNJTuPOCHNwLKxTzuJRSgdaMpPgK6fSGG.dkzq','Thinh',0,1,'Nu'),(8,'km@gmail.com','$2a$10$i9GxNtRmUJeBFaSlWFFh5eI9sHcGNwA0OoolXC0/dhLpyJ5s5cFl.','Kim Ni',0,1,'Anh Anh');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-10 13:33:24
