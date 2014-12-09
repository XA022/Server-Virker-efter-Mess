CREATE DATABASE  IF NOT EXISTS `cbscalendar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cbscalendar`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: cbscalendar
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `isPrivate` tinyint(4) NOT NULL COMMENT '1 = public\n	2 = private',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (3,'krri13ab@student.cbs.dk_cbs_calendar',1,5,0),(4,'test',1,5,1),(5,'mynewcalendat',1,5,0),(6,'ribbes',1,5,0),(7,'demo',1,5,0),(8,'mago13af@student.cbs.dk_cbs_calendar',1,26,0);
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyupdate`
--

DROP TABLE IF EXISTS `dailyupdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailyupdate` (
  `date` datetime NOT NULL,
  `apparentTemperature` double DEFAULT NULL,
  `summary` text,
  `qotd` varchar(500) NOT NULL,
  `windspeed` double DEFAULT NULL,
  `msg_type` varchar(100) NOT NULL,
  `update_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`date`),
  UNIQUE KEY `date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyupdate`
--

LOCK TABLES `dailyupdate` WRITE;
/*!40000 ALTER TABLE `dailyupdate` DISABLE KEYS */;
INSERT INTO `dailyupdate` VALUES ('2014-12-09 02:08:06',15,'long day','Man needs, for his happiness, not only the enjoyment of this or that, but hope and enterprise and change.',10,'1','2014-12-09 01:08:06'),('2014-12-09 02:08:08',15,'long day','Forgiveness is the remission of sins. For it is by this that what has been lost, and was found, is saved from being lost again.',10,'1','2014-12-09 01:08:08'),('2014-12-09 02:08:41',15,'long day','China, in the future, is going to have even more nuclear capability than it has had in the past. I don\'t believe that they have anything to fear from the United States, and I frankly don\'t believe they do fear the United States.',10,'1','2014-12-09 01:08:41'),('2014-12-09 02:08:50',15,'long day','Music is the melody whose text is the world.',10,'1','2014-12-09 01:08:50'),('2014-12-09 02:54:10',15,'long day','If a tree dies, plant another in its place.',10,'1','2014-12-09 01:54:10'),('2014-12-09 02:54:14',15,'long day','Hubert Humphrey talks so fast that listening to him is like trying to read Playboy magazine with your wife turning the pages.',10,'1','2014-12-09 01:54:14'),('2014-12-09 02:54:20',15,'long day','I\'m an independent. I\'m a centrist. A new generation is arriving that has grown up with a multiplicity of choice in every aspect of their lives, and yet politics is the last place that they are told that they should be satisfied with a choice between brand A and brand B. It doesn\'t fit the way they think. It doesn\'t fit the way they live.',10,'1','2014-12-09 01:54:20'),('2014-12-09 02:54:48',15,'long day','When you\'re nearing 35, going, \'Hey Dad, I can\'t make these payments,\' just isn\'t cool.',10,'1','2014-12-09 01:54:48');
/*!40000 ALTER TABLE `dailyupdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `active` int(11) NOT NULL,
  `customevent` tinyint(1) DEFAULT NULL COMMENT 'Decides wether the event is an import-event or user created\n',
  `calendarId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `calendarID` (`calendarId`),
  KEY `location` (`location`),
  KEY `events_ibfk_3` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3159 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (3069,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-08-04 09:50:00','2014-08-04 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3070,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-08-11 09:50:00','2014-08-11 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3071,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-08-18 09:50:00','2014-08-18 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3072,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-09-02 09:50:00','2014-09-02 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3073,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-09-09 09:50:00','2014-09-09 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3074,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-09-23 09:50:00','2014-09-23 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3075,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-10-06 09:50:00','2014-10-06 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3076,'Lecture','SPs14',26,'2014-10-06 11:40:00','2014-10-06 13:20:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3077,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-10-13 09:50:00','2014-10-13 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3078,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-10-20 09:50:00','2014-10-20 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3079,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-10-27 09:50:00','2014-10-27 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3080,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-11-04 09:50:00','2014-11-04 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3081,'Lecture','SPs05',26,'2014-11-04 11:40:00','2014-11-04 13:20:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',1,0,8),(3082,'Lecture','SPs14',26,'2014-08-05 08:00:00','2014-08-05 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3083,'Lecture','SPs14',26,'2014-08-12 08:00:00','2014-08-12 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3084,'Lecture','SPs14',26,'2014-08-19 08:00:00','2014-08-19 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3085,'Lecture','SPs08 Nykredit Aud.',26,'2014-08-26 08:00:00','2014-08-26 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3086,'Lecture','SPs08 Nykredit Aud.',26,'2014-09-03 08:00:00','2014-09-03 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3087,'Lecture','SPs14',26,'2014-09-10 08:00:00','2014-09-10 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3088,'Exercise','SPs14',26,'2014-09-10 09:50:00','2014-09-10 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3089,'Lecture','SPs14',26,'2014-09-24 08:00:00','2014-09-24 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3090,'Exercise','SPs14',26,'2014-09-24 09:50:00','2014-09-24 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3091,'Lecture','SPs14',26,'2014-10-01 08:00:00','2014-10-01 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3092,'Exercise','SPs14',26,'2014-10-01 09:50:00','2014-10-01 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3093,'Lecture','SPs14',26,'2014-10-07 08:00:00','2014-10-07 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3094,'Exercise','SPs14',26,'2014-10-07 09:50:00','2014-10-07 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3095,'Lecture','SPs07 British American Tobacco Aud.',26,'2014-10-14 08:00:00','2014-10-14 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3096,'Exercise','Ks43',26,'2014-10-14 10:45:00','2014-10-14 12:25:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3097,'Lecture','SPs14',26,'2014-10-21 08:00:00','2014-10-21 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3098,'Exercise','SPs14',26,'2014-10-21 09:50:00','2014-10-21 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3099,'Lecture','SPs14',26,'2014-10-28 08:00:00','2014-10-28 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3100,'Exercise','SPs14',26,'2014-10-28 09:50:00','2014-10-28 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3101,'Lecture','SPs14',26,'2014-11-05 08:00:00','2014-11-05 09:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3102,'Exercise','SPs14',26,'2014-11-05 09:50:00','2014-11-05 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',1,0,8),(3103,'Lecture','SP202 Carlsberg Group Aud.',26,'2014-09-20 09:50:00','2014-09-20 11:30:00','BINTO1035U.LA_E14','Makroøkonomi (LA)',1,0,8),(3104,'Lecture','SPs05',26,'2014-09-27 09:50:00','2014-09-27 11:30:00','BINTO1035U.LA_E14','Makroøkonomi (LA)',1,0,8),(3105,'Exercise','SP213',26,'2014-08-15 08:00:00','2014-08-15 09:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3106,'Lecture','Falkoner_Bio_Sal_1',26,'2014-08-22 08:00:00','2014-08-22 10:35:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3107,'Exercise','SPs16',26,'2014-08-22 12:35:00','2014-08-22 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3108,'Exercise','SP113',26,'2014-08-25 14:25:00','2014-08-25 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3109,'Exercise','SPs16',26,'2014-08-29 12:35:00','2014-08-29 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3110,'Lecture','D1Ø041',26,'2014-08-29 15:20:00','2014-08-29 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3111,'Exercise','SPs07 British American Tobacco Aud.',26,'2014-09-02 15:20:00','2014-09-02 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3112,'Exercise','FH_C1',26,'2014-09-03 12:35:00','2014-09-03 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3113,'Exercise','FH_C1',26,'2014-09-06 15:20:00','2014-09-06 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3114,'Exercise','SP213',26,'2014-09-09 12:35:00','2014-09-09 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3115,'Exercise','SPs07 British American Tobacco Aud.',26,'2014-09-09 15:20:00','2014-09-09 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3116,'Exercise','FH_C1',26,'2014-09-10 12:35:00','2014-09-10 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3117,'Exercise','SP213',26,'2014-09-13 08:55:00','2014-09-13 22:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3118,'Exercise','SP213',26,'2014-09-16 08:55:00','2014-09-16 22:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3119,'Exercise','SP213',26,'2014-09-20 15:20:00','2014-09-20 17:00:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3120,'Exercise','SP213',26,'2014-09-23 12:35:00','2014-09-23 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3121,'Exercise','FH_C2',26,'2014-09-27 12:35:00','2014-09-27 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3122,'Exercise','HOW601',26,'2014-09-30 12:35:00','2014-09-30 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3123,'Exercise','SP213',26,'2014-10-10 14:25:00','2014-10-10 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3124,'Exercise','SP113',26,'2014-10-13 14:25:00','2014-10-13 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3125,'Exercise','FH_C2',26,'2014-10-17 12:35:00','2014-10-17 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3126,'Exercise','SP213',26,'2014-10-20 12:35:00','2014-10-20 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3127,'Exercise','SP213',26,'2014-11-08 08:00:00','2014-11-08 09:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3128,'Exercise','FH_C2',26,'2014-11-08 12:35:00','2014-11-08 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',1,0,8),(3129,'Exercise','SP114',26,'2014-08-23 12:35:00','2014-08-23 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3130,'Exercise','SP114',26,'2014-08-30 12:35:00','2014-08-30 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3131,'Exercise','SP114',26,'2014-09-07 12:35:00','2014-09-07 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3132,'Exercise','SP212',26,'2014-09-21 12:35:00','2014-09-21 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3133,'Exercise','SP114',26,'2014-09-28 12:35:00','2014-09-28 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3134,'Exercise','SP212',26,'2014-10-04 12:35:00','2014-10-04 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3135,'Exercise','SP114',26,'2014-10-11 12:35:00','2014-10-11 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3136,'Exercise','SP212',26,'2014-10-18 12:35:00','2014-10-18 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3137,'Exercise','SP213',26,'2014-10-25 12:35:00','2014-10-25 14:15:00','BINTO1056U.XA_E14','Ledelse af IS - forandring, innovation og viden (XA)',1,0,8),(3138,'Lecture','SP214',26,'2014-08-02 08:00:00','2014-08-02 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3139,'Lecture','SP214',26,'2014-08-09 08:00:00','2014-08-09 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3140,'Lecture','DSØ041',26,'2014-08-16 08:00:00','2014-08-16 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3141,'Lecture','SP214',26,'2014-08-23 08:00:00','2014-08-23 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3142,'Exercise','PH110',26,'2014-08-29 08:00:00','2014-08-29 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3143,'Lecture','DSØ041',26,'2014-08-30 08:00:00','2014-08-30 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3144,'Exercise','PH110',26,'2014-09-06 08:00:00','2014-09-06 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3145,'Lecture','SPs08 Nykredit Aud.',26,'2014-09-07 08:00:00','2014-09-07 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3146,'Exercise','Ks48',26,'2014-09-20 08:00:00','2014-09-20 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3147,'Lecture','DSØ041',26,'2014-09-21 08:00:00','2014-09-21 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3148,'Exercise','Ks48',26,'2014-09-27 08:00:00','2014-09-27 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3149,'Lecture','DSØ041',26,'2014-09-28 08:00:00','2014-09-28 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3150,'Exercise','PH110',26,'2014-10-03 08:00:00','2014-10-03 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3151,'Lecture','SPs08 Nykredit Aud.',26,'2014-10-04 08:00:00','2014-10-04 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3152,'Exercise','PH110',26,'2014-10-10 08:00:00','2014-10-10 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3153,'Lecture','HOW601',26,'2014-10-11 08:00:00','2014-10-11 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3154,'Exercise','PH110',26,'2014-10-17 08:00:00','2014-10-17 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3155,'Lecture','SP207',26,'2014-10-18 08:00:00','2014-10-18 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3156,'Exercise','PH110',26,'2014-10-24 08:00:00','2014-10-24 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3157,'Lecture','SP214',26,'2014-10-25 08:00:00','2014-10-25 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8),(3158,'Lecture','SP214',26,'2014-11-02 08:00:00','2014-11-02 09:40:00','BINTO1035U.XA_E14','Makroøkonomi (XA)',1,0,8);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locationdata`
--

DROP TABLE IF EXISTS `locationdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locationdata` (
  `locationdataid` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` int(11) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  PRIMARY KEY (`locationdataid`),
  UNIQUE KEY `latitude` (`latitude`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locationdata`
--

LOCK TABLES `locationdata` WRITE;
/*!40000 ALTER TABLE `locationdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `locationdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventId` int(11) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `text` text,
  `dateTime` datetime NOT NULL,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `eventId` (`eventId`),
  KEY `notes_ibfk_2` (`userId`),
  CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`eventid`) REFERENCES `events` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `notes_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `type` varchar(200) NOT NULL,
  PRIMARY KEY (`roleid`),
  KEY `userid` (`userid`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,1,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userevents`
--

DROP TABLE IF EXISTS `userevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userevents` (
  `userid` bigint(20) NOT NULL,
  `calendarID` int(11) NOT NULL,
  KEY `calendarID` (`calendarID`),
  KEY `userid` (`userid`),
  CONSTRAINT `userevents_ibfk_1` FOREIGN KEY (`calendarID`) REFERENCES `calender` (`calendarID`) ON UPDATE NO ACTION,
  CONSTRAINT `userevents_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userevents`
--

LOCK TABLES `userevents` WRITE;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test@test.test',1,'2014-11-14 22:15:01','********'),(3,'test',1,'2014-11-14 22:15:01','test'),(4,'minmail',1,'2014-11-15 11:09:35','********'),(5,'krri13ab@student.cbs.dk',1,'2014-11-15 13:35:39','test'),(26,'mago13af@student.cbs.dk',1,'2014-12-09 02:41:46','pass1234'),(27,'made13ah@student.cbs.dk',1,'2014-12-09 02:42:14','pass1234'),(28,'nini13ab@student.cbs.dk',1,'2014-12-09 02:42:31','pass1234');
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

-- Dump completed on 2014-12-09  2:56:43
