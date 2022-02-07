/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.20 : Database - jdbc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jdbc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `jdbc`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL,
  `name` varchar(32) NOT NULL,
  `age` int NOT NULL,
  `sex` varchar(4) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`age`,`sex`,`address`) values (1,'Pascale',17,'女','Georgia'),(2,'Ryan',18,'女','Huádōng'),(3,'Nathan',17,'男','Huádōng'),(4,'Aretha',17,'男','Michigan'),(5,'Cheryl',16,'女','Iowa'),(6,'Kaseem',17,'女','Dōngběi'),(7,'Catherine',16,'男','Arkansas'),(8,'Hayden',17,'男','Indiana'),(9,'Germaine',16,'男','Idaho'),(10,'Tyrone',18,'男','Xīběi'),(11,'Hector',18,'男','Dōngběi'),(12,'Armando',17,'男','Pennsylvania'),(13,'Ila',16,'男','Maryland'),(14,'Glenna',16,'男','Wyoming'),(15,'Melissa',17,'男','Dōngběi'),(16,'Savannah',18,'男','Colorado'),(17,'Todd',16,'男','Kentucky'),(18,'Baker',17,'女','Virginia'),(19,'Jaquelyn',17,'女','Zhōngnán'),(20,'Gillian',18,'男','Oklahoma'),(21,'Willa',17,'女','Idaho'),(22,'Frances',18,'男','Dōngběi'),(23,'India',16,'男','Wyoming'),(24,'Fuller',18,'男','Montana'),(25,'Zenia',18,'女','Huádōng'),(26,'Abigail',18,'男','Huádōng'),(27,'Dorothy',17,'女','Maryland'),(28,'Quentin',17,'女','Connecticut'),(29,'Cecilia',18,'男','Xīnán'),(30,'Zephr',17,'男','Dōngběi'),(31,'Hedwig',17,'男','Huádōng'),(32,'Hayley',17,'女','Xīběi'),(33,'Hannah',16,'男','Mississippi'),(34,'Justin',17,'女','Xīběi'),(35,'Clare',17,'女','Xīběi'),(36,'MacKenzie',17,'男','Xīběi'),(37,'Simon',17,'男','Iowa'),(38,'Graiden',17,'女','Idaho'),(39,'Hall',17,'女','Zhōngnán'),(40,'Salvador',18,'男','Xīběi'),(41,'Jacob',16,'女','Missouri'),(42,'Yuli',17,'男','Michigan'),(43,'James',16,'男','Xīběi'),(44,'Iris',17,'女','Minnesota'),(45,'Halee',17,'男','Xīnán'),(46,'Hedy',18,'女','Washington'),(47,'Kaye',17,'女','Huádōng'),(48,'Jared',18,'女','Xīnán'),(49,'Melanie',17,'男','Huádōng'),(50,'Dolan',17,'女','Michigan'),(51,'Kylie',17,'男','Nevada'),(52,'Mark',18,'男','Xīnán'),(53,'Rylee',16,'男','Huádōng'),(54,'Norman',17,'男','Pennsylvania'),(55,'Hammett',17,'男','Dōngběi'),(56,'Urielle',18,'男','Xīnán'),(57,'Colin',18,'男','Xīnán'),(58,'Keaton',17,'男','Wisconsin'),(59,'Sandra',16,'女','Ohio'),(60,'Justine',16,'女','Dōngběi'),(61,'Rachel',16,'男','Huádōng'),(62,'Tobias',16,'男','Zhōngnán'),(63,'Hilel',17,'男','Missouri'),(64,'Cole',17,'男','Huádōng'),(65,'Abdul',16,'女','Zhōngnán'),(66,'Kirestin',18,'女','Zhōngnán'),(67,'Neil',18,'女','Nebraska'),(68,'Shelly',17,'男','Wyoming'),(69,'Jesse',18,'男','Huádōng'),(70,'Germane',16,'男','Washington'),(71,'Stone',16,'女','Maryland'),(72,'Rhona',17,'男','Huáběi'),(73,'Robin',16,'男','Huáběi'),(74,'Tad',18,'女','Wisconsin'),(75,'Thomas',17,'男','Virginia'),(76,'Arsenio',16,'女','Xīběi'),(77,'Richard',18,'女','Delaware'),(78,'Darrel',17,'男','Idaho'),(79,'Rhea',17,'男','Huádōng'),(80,'Ruby',17,'男','Dōngběi'),(81,'May',16,'女','Xīnán'),(82,'Dylan',18,'女','Huádōng'),(83,'Camille',17,'女','Delaware'),(84,'Lisandra',17,'女','Zhōngnán'),(85,'Callum',17,'男','Zhōngnán'),(86,'Avye',17,'男','Wisconsin'),(87,'Cora',18,'女','Delaware'),(88,'Chaim',17,'男','Huáběi'),(89,'Pamela',18,'女','Iowa'),(90,'William',16,'女','Kansas'),(91,'Barrett',18,'女','Huádōng'),(92,'Eve',17,'男','Dōngběi'),(93,'Hedley',18,'女','Huádōng'),(94,'Stewart',16,'女','Hawaii'),(95,'Wang',18,'男','Michigan'),(96,'Gillian',18,'女','Huáběi'),(97,'Daquan',18,'女','Montana'),(98,'Ray',17,'男','Florida'),(99,'Haley',16,'男','Xīběi'),(100,'Hiroko',18,'男','Texas'),(101,'Malachi',17,'女','Xīběi'),(102,'Harding',17,'男','Ohio'),(103,'Gisela',17,'女','Oklahoma'),(104,'Kelsey',16,'女','Huádōng'),(105,'Daniel',17,'男','Idaho'),(106,'Tatiana',18,'男','Huádōng'),(107,'Kameko',17,'女','Massachusetts'),(108,'Wang',16,'女','Zhōngnán'),(109,'Keane',17,'女','Xīběi'),(110,'Nichole',17,'男','Zhōngnán'),(111,'Darryl',17,'女','Zhōngnán'),(112,'Allegra',16,'女','Xīběi'),(113,'Adele',17,'女','Dōngběi'),(114,'Rhonda',17,'男','Alaska'),(115,'Lane',17,'女','Huádōng'),(116,'Caldwell',16,'男','Illinois'),(117,'Clark',16,'男','Xīběi'),(118,'Nasim',17,'女','Zhōngnán'),(119,'Scarlett',18,'女','Huádōng'),(120,'Emma',16,'女','Wisconsin'),(121,'Brennan',17,'女','Dōngběi'),(122,'Lynn',16,'男','California'),(123,'Mechelle',17,'男','Dōngběi'),(124,'Yoshi',17,'男','Zhōngnán'),(125,'Rahim',16,'男','Delaware'),(126,'Colby',17,'男','California'),(127,'Fleur',17,'男','Xīběi'),(128,'Libby',17,'女','Dōngběi'),(129,'Gemma',17,'男','Huádōng'),(130,'Addison',17,'女','Xīběi'),(131,'Jana',16,'男','Huádōng'),(132,'Ferris',17,'男','Dōngběi'),(133,'Lavinia',18,'女','Zhōngnán'),(134,'Rajah',16,'男','Zhōngnán'),(135,'Georgia',17,'女','Dōngběi'),(136,'Cameron',16,'男','Missouri'),(137,'Carlos',16,'女','Xīběi'),(138,'Yoshi',17,'男','Xīnán'),(139,'Leila',16,'男','Xīnán'),(140,'Brielle',17,'男','Connecticut'),(141,'Harriet',16,'女','Dōngběi'),(142,'Rahim',18,'男','Huádōng'),(143,'Ashton',17,'女','Minnesota'),(144,'Laura',17,'男','Idaho'),(145,'Fuller',16,'女','Ohio'),(146,'Judith',18,'男','Dōngběi'),(147,'Murphy',17,'男','Mississippi'),(148,'Slade',17,'女','Huáběi'),(149,'Zeph',18,'男','Huádōng'),(150,'Reuben',16,'女','Zhōngnán'),(151,'Imogene',17,'男','Maine'),(152,'Jemima',18,'女','Zhōngnán'),(153,'Finn',18,'男','Dōngběi'),(154,'Alma',16,'男','Zhōngnán'),(155,'Ria',18,'男','Washington'),(156,'Sybill',17,'女','Zhōngnán'),(157,'MacKensie',17,'女','Xīnán'),(158,'May',17,'男','Idaho'),(159,'Quinn',16,'男','Dōngběi'),(160,'Gregory',17,'女','Kentucky'),(161,'Genevieve',17,'男','Xīnán'),(162,'Rebecca',18,'女','Ohio'),(163,'Autumn',17,'男','Minnesota'),(164,'Cameran',18,'女','Zhōngnán'),(165,'Demetrius',17,'男','Tennessee'),(166,'Yeo',17,'女','Utah'),(167,'Emerson',18,'男','Xīnán'),(168,'Jael',17,'女','Zhōngnán'),(169,'Jessamine',17,'男','Colorado'),(170,'Darius',17,'男','Nebraska'),(171,'Neil',18,'女','Zhōngnán'),(172,'Hakeem',17,'女','Maryland'),(173,'Dean',17,'男','Pennsylvania'),(174,'Veda',16,'男','Huáběi'),(175,'Tatum',17,'女','Illinois'),(176,'Benjamin',17,'女','Montana'),(177,'Kadeem',16,'男','Kentucky'),(178,'Justin',16,'男','Huáběi'),(179,'Bernard',16,'男','California'),(180,'Aristotle',16,'男','Xīběi'),(181,'Phyllis',17,'男','Florida'),(182,'Philip',16,'男','Xīnán'),(183,'Mariko',18,'男','Kansas'),(184,'Zena',18,'女','Dōngběi'),(185,'Jada',16,'女','Kansas'),(186,'Xander',17,'男','Zhōngnán'),(187,'Thaddeus',17,'男','Xīběi'),(188,'Joel',17,'男','Huáběi'),(189,'Jakeem',16,'女','Massachusetts'),(190,'Uta',16,'男','Alabama'),(191,'Kirk',18,'男','Dōngběi'),(192,'Yardley',17,'女','Louisiana'),(193,'Jonas',17,'女','Utah'),(194,'Ferris',18,'女','Huádōng'),(195,'Linus',18,'男','Dōngběi'),(196,'Marah',16,'男','Wisconsin'),(197,'Sylvester',17,'男','Hawaii'),(198,'Hector',18,'女','California'),(199,'Kibo',17,'女','Huádōng'),(200,'Brody',17,'女','Dōngběi'),(201,'Addison',18,'男','Xīnán'),(202,'Guy',16,'男','Maine'),(203,'Neil',17,'女','Wisconsin'),(204,'Blaze',18,'女','Zhōngnán'),(205,'Kennan',17,'女','Huádōng'),(206,'Kennan',16,'女','Xīběi'),(207,'Frances',17,'女','Hawaii'),(208,'John',18,'女','Alaska'),(209,'Daquan',18,'女','Maine'),(210,'Julian',17,'女','Colorado'),(211,'Sawyer',17,'男','Xīběi'),(212,'Ross',17,'男','Illinois'),(213,'Irma',17,'男','Utah'),(214,'Harlan',16,'男','Huáběi'),(215,'Hector',17,'女','Oregon'),(216,'Clarke',17,'男','Xīběi'),(217,'Ruth',16,'女','Xīběi'),(218,'Barry',16,'女','Xīběi'),(219,'Paula',17,'女','Huáběi'),(220,'Kaye',17,'女','Dōngběi'),(221,'Zelenia',16,'女','Hawaii'),(222,'Wendy',16,'男','Zhōngnán'),(223,'Vera',18,'男','Connecticut'),(224,'Phillip',17,'男','Zhōngnán'),(225,'Jonah',17,'女','Zhōngnán'),(226,'Amal',18,'男','Vermont'),(227,'Aspen',18,'女','Missouri'),(228,'Teagan',18,'男','Dōngběi'),(229,'Charde',17,'女','Zhōngnán'),(230,'Graiden',17,'女','Huádōng'),(231,'Angelica',18,'女','Washington'),(232,'August',18,'女','Xīběi'),(233,'Noble',17,'女','Huáběi'),(234,'Noelle',17,'男','Texas'),(235,'Shea',17,'女','Zhōngnán'),(236,'Gloria',16,'男','Tennessee'),(237,'Idola',18,'女','Zhōngnán'),(238,'Ralph',17,'男','Huádōng'),(239,'Ferdinand',18,'女','Colorado'),(240,'Seth',16,'男','Arizona'),(241,'Thor',18,'女','Kentucky'),(242,'Kaitlin',16,'女','Dōngběi'),(243,'Lucas',18,'女','Xīnán'),(244,'Dale',16,'男','Xīběi'),(245,'Blake',17,'男','Xīběi'),(246,'Kelly',16,'女','Oklahoma'),(247,'Abel',17,'女','Huáběi'),(248,'Kay',16,'男','Arizona'),(249,'Marsden',17,'男','Huádōng'),(250,'Christen',16,'男','Huáběi'),(251,'Ashely',17,'男','Zhōngnán'),(252,'Harper',18,'男','Zhōngnán'),(253,'Ivan',17,'女','Oklahoma'),(254,'Christen',17,'女','Huádōng'),(255,'Bethany',17,'女','Indiana'),(256,'Carolyn',17,'女','Zhōngnán'),(257,'Raymond',16,'男','Alaska'),(258,'Lavinia',17,'男','Huádōng'),(259,'Phyllis',17,'男','Xīběi'),(260,'Priscilla',17,'男','Xīběi'),(261,'Hanna',18,'女','Idaho'),(262,'Cody',17,'女','Oklahoma'),(263,'Sydney',16,'女','Montana'),(264,'Lionel',17,'男','Wyoming'),(265,'Rhiannon',17,'女','Huáběi'),(266,'Ralph',17,'女','Huáběi'),(267,'Basil',17,'女','Mississippi'),(268,'Brody',18,'男','Huáběi'),(269,'Addison',17,'男','Florida'),(270,'Uriah',17,'女','Zhōngnán'),(271,'Christine',17,'男','Dōngběi'),(272,'Tana',18,'女','Zhōngnán'),(273,'Barrett',16,'女','Montana'),(274,'Amethyst',16,'男','Dōngběi'),(275,'Neil',17,'男','Vermont'),(276,'Price',17,'女','Zhōngnán'),(277,'Aristotle',16,'女','Montana'),(278,'Danielle',17,'男','Oregon'),(279,'Kellie',18,'女','Xīběi'),(280,'Shelly',16,'男','Montana'),(281,'Denise',17,'男','Huáběi'),(282,'Armand',18,'女','Xīběi'),(283,'Shaeleigh',16,'女','Illinois'),(284,'Desiree',16,'女','Huádōng'),(285,'Randall',18,'男','Xīnán'),(286,'Karleigh',17,'女','Xīběi'),(287,'Brett',18,'女','Indiana'),(288,'Kim',17,'男','Texas'),(289,'Nathaniel',18,'女','Iowa'),(290,'Joelle',17,'男','Xīběi'),(291,'Mira',17,'男','Xīběi'),(292,'Hanae',16,'女','Xīběi'),(293,'Keely',18,'男','Georgia'),(294,'Rudyard',18,'男','Zhōngnán'),(295,'Adrian',18,'女','Connecticut'),(296,'Shaine',16,'女','Louisiana'),(297,'Leonard',18,'男','Utah'),(298,'Macon',17,'女','Mississippi'),(299,'Sean',18,'女','Wyoming'),(300,'Chava',17,'男','Xīnán'),(301,'小红',19,'女','北京');

/*Table structure for table `tb_brand` */

DROP TABLE IF EXISTS `tb_brand`;

CREATE TABLE `tb_brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(20) DEFAULT NULL,
  `company_name` varchar(20) DEFAULT NULL,
  `ordered` int DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_brand` */

insert  into `tb_brand`(`id`,`brand_name`,`company_name`,`ordered`,`description`,`status`) values (1,'三只松鼠','三只松鼠股份有限公司',5,'好吃不上火',0),(2,'华为','华为技术有限公司',100,'华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界',1),(3,'小米','小米科技有限公司',50,'are you ok',1),(5,'小米','小米',100,'小米,尽在小米',1),(6,'小米','小米',100,'尽在小米',1),(7,'小米','小米',100,'尽在小米',1);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `login_time` varchar(32) NOT NULL,
  `end_time` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_login` */

insert  into `user_login`(`id`,`username`,`login_time`,`end_time`) values (1,'root','2021-12-10 16:53:57','2021-12-10 16:54:01'),(2,'root','2021-12-11 20:14:36','2021-12-11 20:17:34'),(3,'root','2021-12-11 20:18:05','2021-12-11 20:19:37'),(4,'root','2021-12-11 20:20:37','2021-12-11 20:23:57'),(5,'root','2021-12-11 20:28:40','2021-12-11 20:28:54');

/*Table structure for table `user_system` */

DROP TABLE IF EXISTS `user_system`;

CREATE TABLE `user_system` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_system` */

insert  into `user_system`(`id`,`username`,`user_password`) values (1,'root','123456'),(5,'admin','123456'),(9,'dw','123456'),(10,'2144957066','021216');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
