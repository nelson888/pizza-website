CREATE DATABASE IF NOT EXISTS website;
USE website;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `address`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `state` varchar(255) NOT NULL,
  `postal_code` int NOT NULL,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `customer`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKg2o3t8h0g17smtr9jgypagdtv` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`address_id`) REFERENCES `address`(`address_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `ingredient`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `ingredient_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `project`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `pizza_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL,
  `price` int NOT NULL,
  `active` bool NOT NULL,
  PRIMARY KEY (`pizza_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `pizza_ingredient`
--

CREATE TABLE `pizza_ingredient` (
  `pizza_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  PRIMARY KEY (`pizza_id`,`ingredient_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`ingredient_id`),
  FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pizza_id`),
  FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` varchar(16) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`order_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `order_pizza`
--

CREATE TABLE `order_pizza` (
  `order_id` int(11) NOT NULL,
  `pizza_id` int(11) NOT NULL,
  FOREIGN KEY (`order_id`) REFERENCES order_table (`order_id`),
  FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pizza_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
