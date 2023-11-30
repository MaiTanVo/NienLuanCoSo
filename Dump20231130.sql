-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: nienluan
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `car_id` int NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `status` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  `date_remove` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (2,123,'ádsad','BMC',123213,'Dangban','2023-10-06','2023-11-25','2023-11-25'),(3,132132,'asdasda','saeqwe',122,'daban','2023-10-07',NULL,NULL),(4,12312,'asdasda','asdas',11,'daban','2023-10-07',NULL,NULL),(8,1231231,'ádas','ádasd',123,'Dangban','2023-11-25',NULL,NULL),(9,123,'ádsad','BMC',123213,'Dangban','2023-11-25','2023-11-25','2023-11-25'),(10,123,'ádsad','BMC',123213,'Dangban','2023-11-25','2023-11-25','2023-11-25'),(11,123,'ádsad','BMC',123213,'Dangban','2023-11-25','2023-11-25','2023-11-25'),(12,11111,'adsadas','ádasda',111,'Dangban','2023-11-25','2023-11-25',NULL),(13,123,'ádsad','BMC',123213,'Dangban','2023-11-25',NULL,'2023-11-25'),(14,1231,'ádsad','BMC',123213,'Dangban','2023-11-25','2023-11-25',NULL);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `car_id` int NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `total` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (9246,'maitanvo1','nam',921939084,123123,'maitanvo','ádasdsad',22222,'2023-11-12'),(13869,'maitanvo','nam',921939084,123123,'maitanvo1231','ádasdsad',22222,'2023-11-12'),(18492,'maitanvo','nam',921939084,123123,'maitanvo1','ádasdsad',22222,'2023-11-12'),(23115,'maitanvo1','nam',12312321,12312321,'12312321','ádsa',123,'2023-11-14'),(27738,'maitanvo','nam',921939084,123123,'maitanvo1231áda','ádasdsadadsad',22222,'2023-11-20'),(32361,'maitanvo1','nam',921939084,123123,'maitanvo1231','ádasdsad',22222,'2023-11-24'),(36984,'maitanvo12','nam',921939084,123123,'maitanvo','ádasdsad',22222,'2023-11-24');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `cn` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `user_id` int NOT NULL,
  `ngaythem` date DEFAULT NULL,
  `hoten` varchar(150) DEFAULT NULL,
  `gioitinh` varchar(100) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `ngaycapnhat` date DEFAULT NULL,
  `ngayxoa` date DEFAULT NULL,
  `trangthai` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (7,'maitanvo','maitanvo','Seller','maitanvo@gmail.com',20230001,'2023-10-05',NULL,NULL,NULL,NULL,NULL,'nguoiban'),(8,'maitanvo1','maitanvo','Seller','asmdasdasmkda@gmail.co',20230008,'2023-10-05',NULL,NULL,NULL,NULL,NULL,'nguoiban'),(9,'maitanvo2','maitanvo','Seller','asdasdas221321@',20234621,'2023-10-05',NULL,NULL,NULL,NULL,'2023-11-25','nguoiban'),(10,'admin','maitanvo','Admin',NULL,0,NULL,NULL,NULL,NULL,NULL,'2023-10-06',NULL),(12,'ckynet','maitanvo','Seller',NULL,0,NULL,NULL,NULL,NULL,NULL,'2023-10-06','nguoiban'),(13,'ckynet','maitanvo','Seller',NULL,202300010,NULL,NULL,NULL,NULL,NULL,'2023-10-06','nguoiban'),(14,'ckynet','maitanvo','Seller',NULL,202300010,NULL,NULL,NULL,NULL,NULL,'2023-10-06','nguoiban'),(15,'ckyentvp','maitanvo','Admin',NULL,123123,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'asdasda','asdasd','Admin','',1231231,'2023-11-12','',NULL,NULL,NULL,NULL,NULL),(17,'VoAdmin','maitanvo','Admin',NULL,0,'2023-11-24',NULL,NULL,NULL,NULL,NULL,NULL),(18,'VoAdmin1','maitanvo','Admin',NULL,0,'2023-11-24',NULL,NULL,NULL,NULL,NULL,NULL),(19,'test','maitanvo','Seller','sieunhanxam123',20234635,'2023-11-25',NULL,NULL,NULL,'2023-11-25',NULL,'Nguoiban'),(20,'testseller','maitanvo','Seller','sieunhanxam123',20234640,'2023-11-25',NULL,NULL,NULL,NULL,'2023-11-25','yeucau'),(21,'TestThem','maitanvo','Admin','',4622021,'2023-11-25','',NULL,NULL,NULL,NULL,NULL),(22,'testAdmin','maitanvo','Admin','',123444,'2023-11-25','',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30 11:17:33
