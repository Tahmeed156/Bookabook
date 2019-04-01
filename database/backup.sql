-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: bookabook
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(32) NOT NULL,
  `available` int(11) DEFAULT NULL,
  `book_info` text,
  `deposit` double DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `rent` double DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  `times_rented` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8oixnbmawbivwggwk27w1m1cm` (`owner_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'George R R Martin',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',300,'Game of Thrones',20,'2019-01-01 00:00:00','Fantasy',1,1),(2,'JK Rowling',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',200,'Harry Potter',10,'2019-01-02 00:00:00','Fantasy',1,1),(3,'George Orwell',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',100,'Animal Farm',10,'2019-01-02 00:10:00','Fiction',1,3),(4,'George Orwell',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',200,'1984',15,'2019-01-02 00:20:00','Fiction',2,4),(5,'Issac Assimov',0,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',300,'Foundation',20,'2019-01-02 00:30:00','Fantasy',2,6),(6,'Rick Riordan',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',400,'Percy Jackson',10,'2019-01-02 00:40:00','Fantasy',9,3),(7,'George Orwell',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',100,'animalFarm',15,'2019-01-02 00:50:00','Fiction',2,2),(8,'Chuck Palahniuk',0,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',200,'Fight Club',10,'2019-01-02 01:00:00','Fiction',1,3),(9,'J R R Tolkein',1,'{\"print\":\"White Print\",\"condition\":\"A little bit of tear\",\"review\":\"A great homage to the high fantasy genre.\\nThe book has a little bit of tear\",\"year_bought\":\"2017\"}',300,'Lord of the rings',15,'2019-02-09 11:30:10','Fiction',1,5),(10,'Suzanne Collins',1,'{\"print\":\"White Print\",\"condition\":\"Some tear\",\"review\":\"Starting of a brilliant adventure.\\nThe book is in mint condition\",\"year_bought\":\"2016\"}',100,'Hunger Games',15,'2019-02-11 17:00:41','Fantasy',2,2),(11,'Suzanne Collins',1,'{\"print\":\"Original\",\"condition\":\"Perfect\",\"review\":\"Very nice book.\\nRecommend to all.\\nBook in good condition.\",\"year_bought\":\"2018\"}',300,'Catching Fire',20,'2019-02-13 00:53:38','Fantasy',14,1),(12,'Suzanna Collins',1,'{\"print\":\"Original\",\"condition\":\"Perfect\",\"review\":\"GREAT ENDING to a masterful series\",\"year_bought\":\"2018\"}',200,'Mockingjay',15,'2019-02-13 02:28:38','Fantasy',15,1),(13,'John Green',1,'{\"print\":\"White Print\",\"condition\":\"Some tear\",\"review\":\"Good intro to Green\",\"year_bought\":\"2014\"}',100,'Paper Towns',15,'2019-02-13 02:44:15','Young-Adult',1,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `body` varchar(256) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  `sender_name` varchar(255) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (12,'DOBBY IS A FREE ELFFFFFFFFFFFFFF',1,'Najib','2019-02-07 17:18:09','text'),(11,'maybe',1,'Najib','2019-02-07 17:16:14','text'),(10,'lualallalalalal',1,'Najib','2019-02-07 17:16:04','text'),(9,'I am fine',1,'Najib','2019-02-07 17:14:32','text'),(8,'How are you?',1,'Najib','2019-02-07 17:12:45','text'),(7,'HEllooo',1,'Najib','2019-02-07 17:11:51','text'),(13,'I AM THE GREATEST SWORDSMAN ALIVE',1,'Sadat','2019-02-07 17:19:58','text'),(14,'SADU IS NIGAHIGA',1,'Sadat','2019-02-07 17:23:36','text'),(15,'1',1,'Sadat','2019-02-07 17:25:08','text'),(16,'',1,'Sadat','2019-02-07 17:25:08','text'),(17,'',1,'Sadat','2019-02-07 17:25:09','text'),(18,'',1,'Sadat','2019-02-07 17:25:10','text'),(19,'2',1,'Sadat','2019-02-07 17:25:13','text'),(20,'3',1,'Sadat','2019-02-07 17:25:14','text'),(21,'4',1,'Sadat','2019-02-07 17:25:15','text'),(22,'5',1,'Sadat','2019-02-07 17:25:15','text'),(23,'6',1,'Sadat','2019-02-07 17:28:52','text'),(24,'7',1,'Sadat','2019-02-07 17:29:52','text'),(25,'8',1,'Sadat','2019-02-07 17:32:09','text'),(26,'9',1,'Sadat','2019-02-07 17:35:12','text'),(27,'Hey niga',1,'Najib','2019-02-07 19:49:02','text'),(28,'I AM HERE',1,'Najib','2019-02-08 00:55:09','text'),(29,'Or am I?',1,'Najib','2019-02-08 00:59:27','text'),(30,'Just Kidding',1,'Najib','2019-02-08 01:00:20','text'),(31,'',1,'Najib','2019-02-08 21:39:05','text'),(32,'HEY NOW LETS GET THE SHOW STARTED',1,'Najib','2019-02-08 21:39:18','text'),(33,'hehe me here too',12,'Azminator','2019-02-09 02:24:46','text'),(34,'HULLLLA',1,'Tahmeed','2019-02-09 15:46:21','text'),(35,'Hey',1,'Tahmeed','2019-02-09 15:53:55','text'),(36,'HEYYYYYYYYYYYYYYYYYYYYY',1,'Tahmeed','2019-02-09 15:56:25','text'),(37,'hello',1,'Tahmeed','2019-02-09 21:37:25','text'),(38,'Here I am',1,'Tahmeed','2019-02-09 21:41:39','text'),(39,'This is me',2,'Najib','2019-02-09 21:41:47','text'),(40,'Maybe this IS me',1,'Tahmeed','2019-02-09 21:45:33','text'),(41,'Oasis is the best',1,'Tahmeed','2019-02-09 21:48:20','text'),(42,'I agree',2,'Najib','2019-02-09 21:48:25','text'),(43,'hey',1,'Tahmeed','2019-02-09 21:58:46','text'),(44,'Hey guys I am here too',9,'Sadat','2019-02-09 22:04:15','text'),(45,'Sadat here',9,'Sadat','2019-02-09 22:05:59','text'),(46,'Tahmeed here',1,'Tahmeed','2019-02-09 22:06:08','text'),(47,'Najib here',2,'Najib','2019-02-09 22:06:15','text'),(48,'hey mate',9,'Sadat','2019-02-10 20:12:13','text'),(49,'hello from najib',2,'Najib','2019-02-10 20:14:58','text'),(50,'hello from tahmeed',1,'Tahmeed','2019-02-10 20:15:04','text'),(51,'hello from sadat',9,'Sadat','2019-02-10 20:15:12','text'),(52,'Starting over ',2,'Najib','2019-02-11 11:33:02','text'),(53,'Over here too',1,'Tahmeed','2019-02-11 11:33:11','text'),(54,'LAWL THIS WORKS',2,'Najib','2019-02-11 20:47:33','text'),(55,'Kotha shona jaeee',1,'Tahmeed','2019-02-12 20:17:13','text'),(56,'now firewall is off',1,'Tahmeed','2019-02-12 20:18:08','text'),(57,'WORKING CHECK',1,'Tahmeed','2019-02-12 21:33:10','text'),(58,'Nice work',15,'apurba','2019-02-13 02:32:05','text'),(59,'Congrats',1,'Tahmeed','2019-02-13 02:33:04','text'),(60,'Kirre mama',1,'Tahmeed','2019-02-13 10:36:46','text'),(61,'khobor ki',1,'Tahmeed','2019-02-13 10:36:52','text'),(62,'asi mama',2,'Najib','2019-02-13 10:36:55','text'),(63,'khela happen?',2,'Najib','2019-02-13 10:37:06','text'),(64,'yu',1,'Tahmeed','2019-02-13 10:38:42','text'),(65,'row',1,'Tahmeed','2019-02-13 10:38:46','text'),(66,'bark',2,'Najib','2019-02-13 10:38:48','text'),(67,'bark bark',1,'Tahmeed','2019-02-13 10:38:52','text'),(68,'who let the dogs out',2,'Najib','2019-02-13 10:38:58','text');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `rentee_id` int(11) NOT NULL,
  `renter_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrrha5eemtxt1g2smvjf5xsuuy` (`book_id`),
  KEY `FKdsab7fdf8r5nvdcybx2tgr26x` (`rentee_id`),
  KEY `FKbbmgps6xhfg1eaxwvr6ir8oup` (`renter_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
INSERT INTO `rent` VALUES (11,'2019-02-09','2019-02-16',3,2,1,'returned'),(16,'2019-02-11','2019-02-18',10,1,2,'returned'),(21,'2019-02-13','2019-02-27',5,1,2,'rented');
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `body` varchar(256) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `reviewer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70yrt09r4r54tcgkrwbeqenbs` (`book_id`),
  KEY `FKt58e9mdgxpl7j90ketlaosmx4` (`reviewer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'Great Book','2019-02-07 11:22:14',1,1),(2,'Mediocre','2019-02-07 11:26:14',1,1),(3,'Best ever Fantasy','2019-02-07 12:00:47',1,1),(4,'গন্ধ আসে','2019-02-07 12:01:39',1,1),(5,' hi there','2019-02-07 12:31:42',1,1),(6,'Tor boi e gonduuuuuuuuuuuuuuuu','2019-02-09 13:39:11',1,2),(7,'A really Great Book','2019-02-10 02:23:37',1,2),(8,'A really great book','2019-02-10 02:24:02',2,2),(9,'FINALLY WORKS','2019-02-10 20:37:00',1,2),(10,'This is a really great book','2019-02-10 20:38:47',1,2),(11,'Master of sci fi','2019-02-10 20:40:45',5,2),(12,'This is good','2019-02-13 00:56:44',11,1);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `contact_no` varchar(32) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `full_name` varchar(64) DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `work` varchar(64) DEFAULT NULL,
  `books_rented` int(11) NOT NULL DEFAULT '0',
  `books_shared` int(11) NOT NULL DEFAULT '0',
  `wallet` int(11) NOT NULL DEFAULT '1000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Tahmeed','94864654','2019-01-05','tahmeedtarek@gmail.com','Tahmeed Tarek','Male','Cantonment','ramu',0,'Dont want to',1,0,4060),(3,'Antik','0165513416','2019-01-02','ayanantikkhan@gmail.com','Ayan Antik Khan','Male','Haq Garden','hey',0,'CSE 108 project developer',0,0,1000),(4,'Sadia','014646546469','2019-01-11','mushtarisadia@gmail.com','Mushtari Sadia','Female ','Grame','hey',0,'Gram bashi ami',0,0,1000),(2,'Najib','01927152595','1997-07-11','nhsarker.bd@gmail.com','Najibul Haque Sarker','Male','House no 36/1, Road no 4, Dhanmondi, Dhaka','ndc',0,'Grand Maester at OldTown',0,1,5340),(5,'Ramia','0351654161','2019-01-02','ramiaKhanom@gmail.com','Ramia Alam','Female','Jigatola','tt',0,'Blockchain shiksilam',0,0,1000),(6,'Purbasha','1651651651','2019-01-02','tahmeedtarek@gmail.com','Purbasha Nishat','Female','Sylheti','hey',0,'Khai ar ghumai',0,0,1000),(7,'Jon','151651651','2019-01-01','kingofthenorth@gmail.com','Jon Snow','Male','Winterfell','hey',0,'King of the north',0,0,1000),(8,'EthanITargaryen','615165165','2019-02-05','skazizul55@gmail.com','Sheikh Azizul Hakim','Male','Demra','qwerty',0,'CR at cse 104',0,0,1000),(9,'Sadat','0175465315545','2019-02-09','sadat.shahriyar@gmail.com','Sadat Shahriyar','Male','Mirpur Lawl','sadu',0,'Doing Stuff',0,0,1000),(13,'apurba','6846445457','2019-02-22','diponsaha007@gmail.com','Apurba Saha','Male','Natore','hey',0,'Student at Buet',0,0,1000),(12,'Azminator','01923545489645','2019-02-06','azminazran@gmail.com','Azmin Azran','Male','Adabor','123',0,'Sub-Editor at Shout',0,0,1000),(14,'parisa','6516161651','2018-12-01','nhsarker.bd@gmail.com','Parisa','Female','Dhanmondi','parisa',NULL,'SHMC',0,0,1020),(15,'apurba','6544345','2019-01-31','nhsarker.bd@gmail.com','Apurba Saha','Male','Natore','hey',NULL,'pool games project',0,0,600);
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

-- Dump completed on 2019-02-13 10:52:25
