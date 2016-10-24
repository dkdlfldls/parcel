-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: parcel
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `delivery_record`
--

DROP TABLE IF EXISTS `delivery_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_record` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  KEY `delivery_record_state_idx` (`state`),
  KEY `delivery_record_product_idx` (`product`),
  CONSTRAINT `delivery_product` FOREIGN KEY (`product`) REFERENCES `product` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `delivery_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `manager` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  KEY `group_manager_idx` (`manager`),
  KEY `group_product_idx` (`product`),
  KEY `group_state_idx` (`state`),
  CONSTRAINT `group_manager` FOREIGN KEY (`manager`) REFERENCES `user` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_product` FOREIGN KEY (`product`) REFERENCES `product` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_member` (
  `idx` int(11) NOT NULL,
  `group` int(11) NOT NULL,
  `member` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  KEY `member_group_idx` (`group`),
  KEY `member_user_idx` (`member`),
  KEY `member_state_idx` (`state`),
  CONSTRAINT `member_group` FOREIGN KEY (`group`) REFERENCES `group` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `member_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `member_user` FOREIGN KEY (`member`) REFERENCES `user` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `machine` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `machine_name` varchar(45) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  `sign_up_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `machine_name_UNIQUE` (`machine_name`),
  KEY `machine_state_idx` (`state`),
  CONSTRAINT `machine_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `idx` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `message` varchar(100) NOT NULL,
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1',
  `show` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idx`),
  KEY `message_user_idx` (`receiver`),
  KEY `message_state_idx` (`state`),
  CONSTRAINT `message_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `message_user` FOREIGN KEY (`receiver`) REFERENCES `user` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `machine` int(11) NOT NULL,
  `machine_code` varchar(45) NOT NULL,
  `pack_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registrant` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  UNIQUE KEY `machine_code_UNIQUE` (`machine_code`),
  KEY `product_state_idx` (`state`),
  KEY `product_machine_idx` (`machine`),
  KEY `product_user_idx` (`registrant`),
  CONSTRAINT `product_machine` FOREIGN KEY (`machine`) REFERENCES `machine` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_user` FOREIGN KEY (`registrant`) REFERENCES `user` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `receive_record`
--

DROP TABLE IF EXISTS `receive_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receive_record` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `receive_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  KEY `receive_product_idx` (`product`),
  KEY `receive_user_idx` (`receiver`),
  KEY `receive_state_idx` (`state`),
  CONSTRAINT `receive_product` FOREIGN KEY (`product`) REFERENCES `product` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `receive_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `receive_user` FOREIGN KEY (`receiver`) REFERENCES `user` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `label_UNIQUE` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `pw` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `web_authority` int(11) NOT NULL DEFAULT '1',
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idx`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_state_idx` (`state`),
  CONSTRAINT `user_state` FOREIGN KEY (`state`) REFERENCES `state` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-24 17:56:12
