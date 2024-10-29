CREATE DATABASE  IF NOT EXISTS `clothingshop2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clothingshop2`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: clothingshop2
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `role` enum('admin','staff') DEFAULT 'staff',
  `createddate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'user4','12345','Nguyễn Văn F','user4@example.com','0123456785','staff','2024-10-15 18:14:03'),(2,'user5','12345','Nguyễn Văn G','user5@example.com','0123456784','staff','2024-10-15 18:14:03'),(3,'user6','password6','Nguyễn Văn H','user6@example.com','0123456783','staff','2024-10-15 18:14:03'),(4,'user7','password7','Nguyễn Văn I','user7@example.com','0123456782','staff','2024-10-15 18:14:03'),(5,'user8','password8','Nguyễn Văn J','user8@example.com','0123456781','staff','2024-10-15 18:14:03'),(6,'admin','123456','Tuấn','tuan@gmail.com','0987654321','admin','2024-10-18 15:10:33'),(7,'tuan','123456','Tuấn','tuan12@gmail.com','1234567890','staff','2024-10-23 07:54:38');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `accountId` int DEFAULT NULL,
  `productId` int DEFAULT NULL,
  `sizeId` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `total` decimal(10,2) GENERATED ALWAYS AS ((`quantity` * `price`)) STORED,
  `addeddate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `productId` (`productId`),
  KEY `sizeId` (`sizeId`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`sizeId`) REFERENCES `size` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `accountId`, `productId`, `sizeId`, `quantity`, `price`, `addeddate`) VALUES (1,3,4,8,2,400000.00,'2024-10-15 18:14:07'),(2,4,5,9,2,70000.00,'2024-10-15 18:14:07'),(3,5,6,11,3,500000.00,'2024-10-15 18:14:07'),(5,2,8,18,2,600000.00,'2024-10-15 18:14:07'),(6,3,4,8,2,400000.00,'2024-10-15 18:16:21'),(8,5,6,11,3,500000.00,'2024-10-15 18:16:21'),(10,2,8,18,2,600000.00,'2024-10-15 18:16:21'),(19,1,1,2,1,150000.00,'2024-10-22 14:07:08'),(20,1,2,4,1,250000.00,'2024-10-22 15:41:04'),(21,1,2,4,1,250000.00,'2024-10-22 15:42:10'),(22,1,1,1,1,150000.00,'2024-10-22 15:52:28');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commune`
--

DROP TABLE IF EXISTS `commune`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commune` (
  `id` int NOT NULL AUTO_INCREMENT,
  `communename` varchar(100) NOT NULL,
  `district_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `district_id` (`district_id`),
  CONSTRAINT `commune_ibfk_1` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commune`
--

LOCK TABLES `commune` WRITE;
/*!40000 ALTER TABLE `commune` DISABLE KEYS */;
INSERT INTO `commune` VALUES (1,'Phường 1',1),(2,'Phường 2',1),(3,'Phường 3',2),(4,'Phường 4',2),(5,'Phường 5',3),(6,'Phường 6',4),(7,'Phường 7',5),(8,'Phường 8',6),(9,'Phường 9',7),(10,'Phường 10',8),(11,'Phường 11',9),(12,'Phường 12',10),(13,'Phường 13',11),(14,'Phường 14',12),(15,'Phường 15',13),(16,'Phường 16',14),(17,'Phường 17',15),(18,'Phường 18',16),(19,'Phường 19',17),(20,'Phường 20',18),(21,'Phường 21',19),(22,'Phường 22',20),(23,'Phường 23',21),(24,'Phường 24',22),(25,'Phường 25',23);
/*!40000 ALTER TABLE `commune` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` int NOT NULL AUTO_INCREMENT,
  `districtname` varchar(100) NOT NULL,
  `province_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `province_id` (`province_id`),
  CONSTRAINT `district_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'Ba Đình',1),(2,'Hai Bà Trưng',1),(3,'Đống Đa',1),(4,'Cầu Giấy',1),(5,'Thanh Xuân',1),(6,'1',2),(7,'3',2),(8,'5',2),(9,'7',2),(10,'Thủ Đức',2),(11,'Liên Chiểu',3),(12,'Cẩm Lệ',3),(13,'Ngũ Hành Sơn',3),(14,'Hải Châu',3),(15,'Sơn Trà',3),(16,'Ngô Quyền',4),(17,'Lê Chân',4),(18,'Hồng Bàng',4),(19,'Kiến An',4),(20,'Đồ Sơn',4),(21,'Ninh Kiều',5),(22,'Bình Thủy',5),(23,'Cái Răng',5),(24,'Ô Môn',5),(25,'Thốt Nốt',5);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `accountId` int DEFAULT NULL,
  `orderdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `productId` int DEFAULT NULL,
  `sizeId` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `total` decimal(10,2) GENERATED ALWAYS AS ((`quantity` * `price`)) STORED,
  `status` enum('pending','confirmed') DEFAULT 'pending',
  `province` int DEFAULT NULL,
  `district` int DEFAULT NULL,
  `commune` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `productId` (`productId`),
  KEY `sizeId` (`sizeId`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`sizeId`) REFERENCES `size` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `accountId`, `orderdate`, `productId`, `sizeId`, `quantity`, `price`, `status`, `province`, `district`, `commune`, `address`, `phonenumber`, `notes`) VALUES (1,3,'2024-10-15 18:14:14',4,8,2,400000.00,'pending',1,2,3,'111 Đường STU','0933456788','Giao hàng cẩn thận'),(2,4,'2024-10-15 18:14:14',5,9,1,70000.00,'pending',1,2,3,'222 Đường VWX','0987654311','Liên hệ trước khi giao'),(3,5,'2024-10-15 18:14:14',6,11,3,500000.00,'pending',1,2,3,'333 Đường YZ','0965432100','Giao vào buổi sáng'),(4,1,'2024-10-15 18:14:14',7,15,1,200000.00,'confirmed',1,2,3,'444 Đường ABC','0923456787','Gọi trước khi đến'),(5,2,'2024-10-15 18:14:14',8,18,2,600000.00,'pending',1,2,3,'555 Đường DEF','0912345678','Giao nhanh trong ngày'),(21,1,'2024-10-25 15:36:23',5,9,5,70000.00,'confirmed',1,2,3,'74','9876543',NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productname` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `categoryId` int DEFAULT NULL,
  `supplierId` int DEFAULT NULL,
  `productimage` varchar(255) DEFAULT NULL,
  `description` text,
  `entrydate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  KEY `supplierId` (`supplierId`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `productcategory` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Quần short',150000.00,2,1,'quan.jpg','Quần short thoải mái','2024-10-15 18:13:48'),(2,'Áo sơ mi',250000.00,1,2,'ao.jpg','Áo sơ mi lịch sự','2024-10-15 18:13:48'),(3,'Quần tây',300000.00,2,3,'quan.jpg','Quần tây công sở','2024-10-15 18:13:48'),(4,'Áo sơ mi',400000.00,1,4,'Capture001.png','Áo khoác ấm áp','2024-10-15 18:13:48'),(5,'Mũ bảo hiểm',70000.00,4,1,'mu.jpg','Mũ bảo hiểm an toàn','2024-10-15 18:13:48'),(6,'Giày da',500000.00,3,2,'giay.jpg','Giày da sang trọng','2024-10-15 18:13:48'),(7,'Phụ kiện ví',200000.00,5,3,'phukien.jpg','Ví da thời trang','2024-10-15 18:13:48'),(8,'Áo blazer',600000.00,1,4,'ao.jpg','Áo blazer thanh lịch','2024-10-15 18:13:48'),(9,'Quần lót',50000.00,2,1,'quan.jpg','Quần lót thoáng mát','2024-10-15 18:13:48'),(10,'Giày chạy bộ',400000.00,3,2,'giay.jpg','Giày chạy bộ chuyên dụng','2024-10-15 18:13:48'),(11,'Quần áo mùa đông',9999999.00,5,1,'Capture001.png','Sp','2024-10-15 19:45:19');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcategory`
--

DROP TABLE IF EXISTS `productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `categoryname` (`categoryname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (1,'Áo'),(3,'Giày'),(4,'Mũ'),(5,'Phụ kiện'),(2,'Quần');
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `provincename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `provincename` (`provincename`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (6,'Bắc Giang'),(7,'Bắc Kạn'),(8,'Bạc Liêu'),(9,'Bắc Ninh'),(10,'Bến Tre'),(11,'Bình Định'),(12,'Bình Dương'),(13,'Bình Phước'),(14,'Bình Thuận'),(15,'Cà Mau'),(5,'Cần Thơ'),(16,'Cao Bằng'),(3,'Đà Nẵng'),(17,'Đắk Lắk'),(18,'Đắk Nông'),(19,'Điện Biên'),(20,'Đồng Nai'),(21,'Đồng Tháp'),(22,'Gia Lai'),(23,'Hà Giang'),(24,'Hà Nam'),(1,'Hà Nội'),(25,'Hà Tĩnh'),(26,'Hải Dương'),(4,'Hải Phòng'),(27,'Hậu Giang'),(2,'Hồ Chí Minh'),(28,'Hòa Bình'),(29,'Hưng Yên'),(30,'Khánh Hòa'),(31,'Kiên Giang'),(32,'Kon Tum'),(33,'Lai Châu'),(34,'Lâm Đồng'),(35,'Lạng Sơn'),(36,'Lào Cai'),(37,'Long An'),(38,'Nam Định'),(39,'Nghệ An'),(40,'Ninh Bình'),(41,'Ninh Thuận'),(42,'Phú Thọ'),(43,'Phú Yên'),(44,'Quảng Bình'),(45,'Quảng Nam'),(46,'Quảng Ngãi'),(47,'Quảng Ninh'),(48,'Quảng Trị'),(49,'Sóc Trăng'),(50,'Sơn La'),(51,'Tây Ninh'),(52,'Thái Bình'),(53,'Thái Nguyên'),(54,'Thanh Hóa'),(55,'Thừa Thiên-Huế'),(56,'Tiền Giang'),(57,'Trà Vinh'),(58,'Tuyên Quang'),(59,'Vĩnh Long'),(60,'Vĩnh Phúc'),(61,'Yên Bái');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesreport`
--

DROP TABLE IF EXISTS `salesreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesreport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productId` int NOT NULL,
  `quantitySold` int NOT NULL,
  `unitPrice` decimal(15,2) NOT NULL,
  `totalSales` decimal(15,2) GENERATED ALWAYS AS ((`quantitySold` * `unitPrice`)) STORED,
  `reportDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productId` (`productId`,`reportDate`),
  CONSTRAINT `salesreport_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesreport`
--

LOCK TABLES `salesreport` WRITE;
/*!40000 ALTER TABLE `salesreport` DISABLE KEYS */;
INSERT INTO `salesreport` (`id`, `productId`, `quantitySold`, `unitPrice`, `reportDate`) VALUES (1,1,20,100000.00,'2024-10-15 18:14:19'),(2,2,15,200000.00,'2024-10-15 18:14:19'),(3,3,12,300000.00,'2024-10-15 18:14:19'),(4,4,25,400000.00,'2024-10-15 18:14:19'),(5,5,18,70000.00,'2024-10-15 18:14:19'),(6,6,30,500000.00,'2024-10-15 18:14:19'),(7,7,10,200000.00,'2024-10-15 18:14:19'),(8,8,22,600000.00,'2024-10-15 18:14:19'),(9,9,8,50000.00,'2024-10-15 18:14:19'),(10,10,14,400000.00,'2024-10-15 18:14:19'),(11,1,30,100000.00,'2024-10-15 18:47:20');
/*!40000 ALTER TABLE `salesreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sizename` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `productId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `size_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'S',15,1),(2,'M',25,1),(3,'L',20,2),(4,'XL',10,2),(5,'S',18,3),(6,'M',22,3),(7,'L',25,4),(8,'XL',12,4),(9,'S',20,5),(10,'M',30,5),(11,'38',10,6),(12,'39',8,6),(13,'40',12,6),(14,'41',5,6),(15,'One Size',40,7),(16,'S',5,8),(17,'M',15,8),(18,'L',10,8),(19,'XL',8,8),(20,'M',0,11),(21,'L',0,11),(22,'XL',0,11),(23,'XXL',0,11),(24,'XXXL',0,11),(25,'KHAC',0,11);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `suppliername` varchar(100) NOT NULL,
  `province` int DEFAULT NULL,
  `district` int DEFAULT NULL,
  `commune` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `province` (`province`),
  KEY `district` (`district`),
  KEY `commune` (`commune`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`province`) REFERENCES `province` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `supplier_ibfk_2` FOREIGN KEY (`district`) REFERENCES `district` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `supplier_ibfk_3` FOREIGN KEY (`commune`) REFERENCES `commune` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Nhà Cung Cấp 1',1,1,1,'123 Đường ABC','0123456789','nhacungcap1@example.com'),(2,'Nhà Cung Cấp 2',2,2,7,'456 Đường DEF','0987654321','nhacungcap2@example.com'),(3,'Nhà Cung Cấp 3',3,11,12,'789 Đường GHI','0123456798','nhacungcap3@example.com'),(4,'Nhà Cung Cấp 4',4,16,17,'321 Đường JKL','0987654322','nhacungcap4@example.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-29 10:10:46
