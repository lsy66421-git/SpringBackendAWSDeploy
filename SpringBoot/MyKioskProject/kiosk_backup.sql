/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: kioskdb
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `writer_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `board_type` varchar(255) NOT NULL DEFAULT 'NOTICE',
  PRIMARY KEY (`id`),
  KEY `FKi57kt4qb1qssjljxotb26a4h0` (`writer_id`),
  CONSTRAINT `FKi57kt4qb1qssjljxotb26a4h0` FOREIGN KEY (`writer_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES
(1,'문의 글쓰기 테스트 1 입니다.',NULL,NULL,'문의 글쓰기 테스트 1',1,'2026-01-05 14:50:09.304329','2026-01-05 14:50:09.304329',NULL,NULL,'INQUIRY'),
(3,'홍길동 글쓰기 테스트 1 수정 입니다.',NULL,NULL,'홍길동 글쓰기 테스트 1 수정',2,'2026-01-05 15:19:12.545063','2026-01-05 15:24:20.523683','cafelatte.png','/uploads/a98de361-8028-4189-8c57-245f4f676de1_cafelatte.png','INQUIRY'),
(4,'이순신 글쓰기 테스트 1 수정 입니다.',NULL,NULL,'이순신 글쓰기 테스트 1 수정',3,'2026-01-06 09:03:00.493681','2026-01-06 09:03:20.474332','caramel.png','/uploads/637a3bc2-eb45-4a9b-8c30-7b0301404eac_caramel.png','INQUIRY'),
(5,'공지사항 글쓰기 테스트 1 입니다.',NULL,NULL,'공지사항 글쓰기 테스트 1',1,'2026-01-06 12:47:16.934493','2026-01-06 12:47:16.934493','[NULL]','[NULL]','NOTICE'),
(6,'관리자 문의 글쓰기 1 입니다.',NULL,NULL,'관리자 문의 글쓰기 1',1,'2026-01-06 12:48:01.330325','2026-01-06 12:48:01.330325','[NULL]','[NULL]','INQUIRY'),
(13,'공지사항 글쓰기 테스트 2 입니다.',NULL,NULL,'공지사항 글쓰기 테스트 2',1,'2026-01-08 10:10:33.082273','2026-01-08 10:10:33.082273','banillaratte.png','/uploads/24a92336-849a-4b84-b7eb-6a7ce19b2be5_berryratte.png','NOTICE'),
(14,'공지사항 글쓰기 테스트 3 입니다.',NULL,NULL,'공지사항 글쓰기 테스트 3',1,'2026-01-08 11:23:00.420816','2026-01-08 11:23:00.420816','cafemoca.png','/uploads/4794f2fe-8bba-4de4-9161-20631a8941b1_cafemoca.png','NOTICE'),
(15,'공지사항 글쓰기 테스트 4 수정 입니다.',NULL,NULL,'공지사항 글쓰기 테스트 4 수정',1,'2026-01-08 11:24:48.748002','2026-01-13 10:08:12.990885','cappuccino.png','/uploads/1768266492967_cappuccino.png','NOTICE'),
(16,'전우치 문의 글쓰기 테스트 1 입니다.',NULL,NULL,'전우치 문의 글쓰기 테스트 1',5,'2026-01-08 12:44:04.315476','2026-01-08 12:44:04.315476','milkcake.png','/uploads/0259f0d4-8cbe-4b26-995c-5f4ec050be83_milkcake.png','INQUIRY'),
(17,'전우치 문의 글쓰기 테스트 2 입니다.',NULL,NULL,'전우치 문의 글쓰기 테스트 2',5,'2026-01-09 09:46:51.618107','2026-01-09 09:46:51.618107','americano.png','/uploads/1767919611614_americano.png','INQUIRY'),
(18,'전우치 문의 글쓰기 테스트 3 입니다.',NULL,NULL,'전우치 문의 글쓰기 테스트 3',5,'2026-01-09 09:54:00.502481','2026-01-09 09:54:00.502481','cafemoca.png','/uploads/1767920040486_cafemoca.png','INQUIRY');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `display_order` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `member_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd7qtd46ngp06lnc19g6wtoh8t` (`member_id`),
  CONSTRAINT `FKd7qtd46ngp06lnc19g6wtoh8t` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES
(1,1,'커피','2026-01-05 11:01:07.324626','2026-01-07 09:49:51.156956',1,'1,2,3,5'),
(2,2,'음료','2026-01-05 11:01:07.369835','2026-01-07 09:49:51.157953',1,'1,2,3,5'),
(3,3,'디저트','2026-01-05 11:01:07.374668','2026-01-07 09:49:51.157953',1,'1,2,3,5');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `background_image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','OWNER','USER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `approved_at` datetime(6) DEFAULT NULL,
  `is_approved` int(11) DEFAULT 0,
  `withdrawn_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgc3jmn7c2abyo3wf6syln5t2i` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES
(1,'/images/back/menu-list-bg.gif','관리자','$2a$10$9aQ2VvH1RBoimtNkQe8rUeeIN9SNCHk7.NMN1ZnMcfCqsL824bxLK','ADMIN','admin','2026-01-05 10:16:29.337620','2026-01-05 14:20:27.152735',NULL,1,NULL,'부산진구 전포동','lsy66421@gmail.com','010-1234-5678'),
(2,'/images/back/menu-list-bg-1.gif','홍길동','$2a$10$KlxlsoXRWRygLdzg9k3PkOcOcqgksR1VqTfXlnRGBXk2dZXMFi9du','USER','hong','2026-01-05 14:59:04.941726','2026-01-07 16:22:37.588550','2026-01-06 09:16:41.992308',1,NULL,'금정구 서2동','hong123@gmial.com','010-2345-6780'),
(3,'/images/back/menu-list-bg-2.gif','이순신','$2a$10$tF6Xkyki.NtuVxu3cv4sQ.7Td2j2XGvANcr3Qk9oA3HbyZ3qNXak.','USER','lee','2026-01-05 16:34:55.223671','2026-01-06 09:16:46.251893','2026-01-06 09:16:46.250805',1,NULL,'남구 문현동','lee4567@gmail.com','010-9876-5432'),
(5,'/images/back/menu-list-bg-3.gif','전우치','$2a$10$AtY.c4MuM0MW0Ajqe/KoG.7gxVvA6ltg4.WCoHo0ZVAVkJRSepyuK','USER','jeon','2026-01-07 09:47:33.448377','2026-01-07 09:49:12.298382','2026-01-07 09:49:12.295391',1,NULL,'수영구 광안동','jeon9876@gmail.com','010-4567-3214');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `member_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKww84tou7nixng06lmxawvcre` (`category_id`),
  KEY `FK82xgfxeh1c9jwo6vxl6shfqpy` (`member_id`),
  CONSTRAINT `FK82xgfxeh1c9jwo6vxl6shfqpy` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKww84tou7nixng06lmxawvcre` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES
(2,'','/images/menu/b-latte.png','바닐라라떼',5000,1,'2026-01-05 10:35:41.864838','2026-01-08 14:04:45.484555',1,'1,2,3'),
(3,'','/images/menu/caramel-latte.png','카라멜마키야또',5500,1,'2026-01-05 10:35:41.868814','2026-01-08 14:04:45.484555',1,'1,2,3,5'),
(4,'','/images/menu/choke-bang.png','초크도넛',2000,3,'2026-01-05 10:35:41.873456','2026-01-07 15:32:22.073935',1,'1,2'),
(5,'','/images/menu/d-cake.png','딸기케익',3000,3,'2026-01-05 10:35:41.877422','2026-01-07 15:29:44.465048',1,'1,2,5'),
(6,'','/images/menu/d-latte.png','딸기라떼',5000,1,'2026-01-05 10:35:41.882409','2026-01-07 15:29:44.470035',1,'1,2'),
(7,'','/images/menu/k-latte.png','카페라떼',5000,1,'2026-01-05 10:35:41.886423','2026-01-07 15:32:22.073935',1,'1,2,5'),
(8,'','/images/menu/k-moka.png','카페모카',5000,1,'2026-01-05 10:35:41.890387','2026-01-05 10:35:41.890387',1,'1,2,3'),
(9,'','/images/menu/kafuchino.png','카푸치노',5000,1,'2026-01-05 10:35:41.893983','2026-01-07 15:32:06.451306',1,'1,2,5'),
(10,'','/images/menu/krongji.png','크로와상',1000,3,'2026-01-05 10:35:41.897973','2026-01-05 10:35:41.897973',1,'1,2'),
(11,'','/images/menu/m-cake.png','모카케익',3000,3,'2026-01-05 10:35:41.901355','2026-01-07 09:49:51.147347',1,'1,2,5'),
(12,'','/images/menu/milk-cake.png','밀크케익',3000,3,'2026-01-05 10:35:41.905344','2026-01-07 15:29:44.470035',1,'1,2,3'),
(13,'','/images/menu/r-aid.png','레몬에이드',5500,2,'2026-01-05 10:35:41.909124','2026-01-07 09:49:51.147347',1,'1,2,3,5'),
(14,'','/images/menu/r-bang.png','딸기도넛',2000,3,'2026-01-05 10:35:41.912786','2026-01-05 10:35:41.912786',1,'1,2,3'),
(15,'','/images/menu/rice-latte.png','현미라떼',5000,1,'2026-01-05 10:35:41.916418','2026-01-07 09:49:51.147347',1,'1,3,5'),
(16,'','/images/menu/sicke.png','식혜',4500,2,'2026-01-05 10:35:41.921592','2026-01-05 10:35:41.921592',1,'1,3'),
(17,'','/images/menu/tiramishu.png','티라미슈케익',3000,3,'2026-01-05 10:35:41.924992','2026-01-07 09:49:51.147347',1,'1,3,5'),
(18,'','/images/menu/y-latte.png','연유라떼',5000,1,'2026-01-05 10:35:41.928958','2026-01-07 09:49:51.147347',1,'1,3,5'),
(20,'','/images/menu/americano.png','아메리카노',4500,1,'2026-01-08 15:16:14.622529','2026-01-08 15:16:14.622529',1,'1,3,5');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi610j9rwndv2jcwrgyo89q2es` (`menu_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FKi610j9rwndv2jcwrgyo89q2es` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES
(1,3000,1,12,1,'2026-01-05 14:39:05.920737','2026-01-05 14:39:05.920737','밀크케익'),
(2,3000,2,11,1,'2026-01-05 14:39:05.924257','2026-01-05 14:39:05.924257','모카케익'),
(3,1000,1,10,1,'2026-01-05 14:39:05.924257','2026-01-05 14:39:05.924257','크로와상'),
(4,4500,1,20,2,'2026-01-06 09:00:57.334861','2026-01-08 15:14:23.598938','아메리카노'),
(5,5000,1,2,2,'2026-01-06 09:00:57.339114','2026-01-06 09:00:57.339114','바닐라라떼'),
(6,5500,1,3,2,'2026-01-06 09:00:57.341088','2026-01-06 09:00:57.341088','바닐라라떼'),
(7,3000,2,5,3,'2026-01-06 09:06:26.867118','2026-01-06 09:06:26.867118','딸기케익'),
(8,2000,2,14,3,'2026-01-06 09:06:26.867118','2026-01-06 09:06:26.867118','딸기도넛'),
(9,3000,2,17,3,'2026-01-06 09:06:26.868118','2026-01-06 09:06:26.868118','티라미슈케익'),
(10,4500,2,20,4,'2026-01-06 09:29:15.254008','2026-01-08 15:14:23.610518','아메리카노'),
(11,5000,2,2,4,'2026-01-06 09:29:15.256972','2026-01-06 09:29:15.256972','바닐라라떼'),
(12,3000,2,5,4,'2026-01-06 09:29:15.256972','2026-01-06 09:29:15.256972','딸기케익'),
(13,1000,2,10,4,'2026-01-06 09:29:15.257970','2026-01-06 09:29:15.257970','크로와상'),
(14,1000,1,10,5,'2026-01-06 16:36:01.113913','2026-01-06 16:36:01.113913','크로와상'),
(15,5000,1,9,5,'2026-01-06 16:36:01.117559','2026-01-06 16:36:01.117559','카푸치노'),
(16,5000,1,8,6,'2026-01-06 16:38:54.332559','2026-01-06 16:38:54.332559','카페모카'),
(17,5500,1,13,6,'2026-01-06 16:38:54.333556','2026-01-06 16:38:54.333556','레몬에이드'),
(18,3000,2,12,6,'2026-01-06 16:38:54.334554','2026-01-06 16:38:54.334554','밀크케익'),
(19,3000,1,11,7,'2026-01-06 16:39:12.597853','2026-01-06 16:39:12.597853','모카케익'),
(20,5000,1,6,7,'2026-01-06 16:39:12.597853','2026-01-06 16:39:12.597853','딸기라떼'),
(21,4500,1,20,8,'2026-01-07 09:26:26.005871','2026-01-08 15:14:23.610518','아메리카노'),
(22,5000,1,2,8,'2026-01-07 09:26:26.008863','2026-01-07 09:26:26.008863','바닐라라떼'),
(23,5500,1,3,8,'2026-01-07 09:26:26.009861','2026-01-07 09:26:26.009861','바닐라라떼'),
(24,2000,1,4,8,'2026-01-07 09:26:26.010858','2026-01-07 09:26:26.010858','초크도넛'),
(25,3000,1,5,8,'2026-01-07 09:26:26.011856','2026-01-07 09:26:26.011856','딸기케익'),
(26,5000,1,8,9,'2026-01-07 15:28:56.299507','2026-01-07 15:28:56.299507','카페모카'),
(27,5000,1,9,9,'2026-01-07 15:28:56.303496','2026-01-07 15:28:56.303496','카푸치노'),
(28,1000,1,10,9,'2026-01-07 15:28:56.303496','2026-01-07 15:28:56.303496','크로와상'),
(29,5000,1,15,9,'2026-01-07 15:28:56.304494','2026-01-07 15:28:56.304494','현미라떼'),
(30,4500,1,20,10,'2026-01-08 09:10:55.035023','2026-01-08 15:14:23.610518','아메리카노'),
(31,5000,2,2,10,'2026-01-08 09:10:55.037018','2026-01-08 09:10:55.037018','바닐라라떼'),
(32,5500,1,3,10,'2026-01-08 09:10:55.038015','2026-01-08 09:10:55.038015','카라멜마키야또'),
(33,2000,2,4,10,'2026-01-08 09:10:55.039013','2026-01-08 09:10:55.039013','초크도넛'),
(34,3000,1,5,10,'2026-01-08 09:10:55.040010','2026-01-08 09:10:55.040010','딸기케익'),
(35,5500,1,3,11,'2026-01-09 09:45:49.624022','2026-01-09 09:45:49.624022','카라멜마키야또'),
(36,3000,1,5,11,'2026-01-09 09:45:49.627013','2026-01-09 09:45:49.627013','딸기케익'),
(37,4500,1,20,11,'2026-01-09 09:45:49.627013','2026-01-09 09:45:49.627013','아메리카노'),
(38,3000,1,11,11,'2026-01-09 09:45:49.628011','2026-01-09 09:45:49.628011','모카케익');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED') DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpktxwhj3x9m4gth5ff6bkqgeb` (`member_id`),
  CONSTRAINT `FKpktxwhj3x9m4gth5ff6bkqgeb` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES
(1,'2026-01-05 14:39:05.889615','COMPLETED',10000,1,'2026-01-05 14:39:05.897525','2026-01-05 14:39:05.897525'),
(2,'2026-01-06 09:00:57.250541','COMPLETED',15000,3,'2026-01-06 09:00:57.296418','2026-01-06 09:00:57.296418'),
(3,'2026-01-06 09:06:26.862541','COMPLETED',16000,3,'2026-01-06 09:06:26.865567','2026-01-06 09:06:26.865567'),
(4,'2026-01-06 09:29:15.193525','COMPLETED',27000,2,'2026-01-06 09:29:15.216623','2026-01-06 09:29:15.216623'),
(5,'2026-01-06 16:36:01.010502','COMPLETED',6000,2,'2026-01-06 16:36:01.049963','2026-01-06 16:36:01.049963'),
(6,'2026-01-06 16:38:54.325607','COMPLETED',16500,2,'2026-01-06 16:38:54.330589','2026-01-06 16:38:54.330589'),
(7,'2026-01-06 16:39:12.593318','COMPLETED',8000,2,'2026-01-06 16:39:12.596888','2026-01-06 16:39:12.596888'),
(8,'2026-01-07 09:26:25.933863','COMPLETED',20000,1,'2026-01-07 09:26:25.957124','2026-01-07 09:26:25.957124'),
(9,'2026-01-07 15:28:56.231689','COMPLETED',16000,1,'2026-01-07 15:28:56.254627','2026-01-07 15:28:56.254627'),
(10,'2026-01-08 09:10:54.965211','COMPLETED',27000,1,'2026-01-08 09:10:54.990143','2026-01-08 09:10:54.990143'),
(11,'2026-01-09 09:45:49.494838','COMPLETED',16000,5,'2026-01-09 09:45:49.549700','2026-01-09 09:45:49.549700');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'kioskdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-01-15 11:42:57
