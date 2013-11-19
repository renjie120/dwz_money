-- MySQL dump 10.13  Distrib 5.5.32, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: dwz4j
-- ------------------------------------------------------
-- Server version	5.5.32

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
-- Table structure for table `cache_t`
--

DROP TABLE IF EXISTS `cache_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache_t` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cacheId` varchar(1000) DEFAULT NULL,
  `cacheName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache_t`
--

LOCK TABLES `cache_t` WRITE;
/*!40000 ALTER TABLE `cache_t` DISABLE KEYS */;
INSERT INTO `cache_t` VALUES (11,'moneyType','金额类型'),(12,'allparamtype','属性类型'),(13,'allparamtypecode','全部参数代码'),(14,'paramtype1','参数:questionSort'),(15,'paramtype2','参数:menulevel'),(16,'paramtype3','参数:planstatus'),(17,'paramtype4','参数:plantype'),(18,'paramtype5','参数:menutarget'),(19,'paramtype6','参数:questionStatus'),(20,'moneyTypeTree','金额类型树');
/*!40000 ALTER TABLE `cache_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `con_file`
--

DROP TABLE IF EXISTS `con_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `con_file` (
  `ID` char(32) NOT NULL,
  `USER_ID` char(32) DEFAULT NULL,
  `FOLDER_ID` char(32) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `EXT` varchar(32) DEFAULT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `SIZE` int(11) DEFAULT NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `RESIZE` set('S','M') DEFAULT NULL,
  `FILE_TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `insert_date` (`INSERT_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `con_file`
--

LOCK TABLES `con_file` WRITE;
/*!40000 ALTER TABLE `con_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `con_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `con_folder`
--

DROP TABLE IF EXISTS `con_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `con_folder` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(128) NOT NULL,
  `LFT` int(11) DEFAULT NULL,
  `RGT` int(11) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `INSERT_DATE` datetime NOT NULL,
  `INSERT_BY` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `con_folder`
--

LOCK TABLES `con_folder` WRITE;
/*!40000 ALTER TABLE `con_folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `con_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inf_news`
--

DROP TABLE IF EXISTS `inf_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inf_news` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(10) unsigned DEFAULT NULL,
  `TYPE` enum('NEWS','NOTICE','ANNOUNCEMENT') NOT NULL,
  `STATUS` enum('PENDING','ACTIVE','DISABLED') NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `AUTHOR` varchar(100) DEFAULT NULL,
  `SOURCE` varchar(100) DEFAULT NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `INSERT_DATE` (`INSERT_DATE`),
  KEY `NewIndex1` (`UPDATE_DATE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inf_news`
--

LOCK TABLES `inf_news` WRITE;
/*!40000 ALTER TABLE `inf_news` DISABLE KEYS */;
INSERT INTO `inf_news` VALUES (1,NULL,'NEWS','ACTIVE','DWZ Java Web 快速开发平台','###DWZ Java Web 快速开发平台',NULL,NULL,'2010-12-22 10:26:09','2010-12-22 10:26:09'),(2,NULL,'NEWS','PENDING','DWZ Java Web 快速开发平台v0.1','<span style=\"font-size:medium;\"></span>DWZ Java Web 快速开发平台v0.1<br />',NULL,NULL,'2011-02-19 20:37:14','2011-02-19 20:37:14');
/*!40000 ALTER TABLE `inf_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_category`
--

DROP TABLE IF EXISTS `inv_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(10) unsigned DEFAULT NULL,
  `lft` int(10) unsigned DEFAULT NULL,
  `rgt` int(10) unsigned DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `meta_title` varchar(100) DEFAULT NULL,
  `meta_keyword` varchar(200) DEFAULT NULL,
  `meta_description` varchar(500) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `product_num` int(11) NOT NULL DEFAULT '0',
  `insert_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_category`
--

LOCK TABLES `inv_category` WRITE;
/*!40000 ALTER TABLE `inv_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `inv_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_product`
--

DROP TABLE IF EXISTS `inv_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `brand_id` int(10) unsigned DEFAULT NULL,
  `category_id` int(10) unsigned DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `sku` varchar(30) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `short_description` text,
  `long_description` text,
  `pic_url` varchar(255) DEFAULT NULL,
  `meta_title` varchar(100) DEFAULT NULL,
  `meta_keyword` text,
  `meta_description` text,
  `regular_price` decimal(8,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT '0',
  `sales_price` decimal(8,2) DEFAULT NULL,
  `show_sales_price` smallint(1) DEFAULT '1',
  `list_price` decimal(8,2) DEFAULT NULL,
  `show_list_price` smallint(1) DEFAULT NULL,
  `show_on_web` smallint(1) DEFAULT '1',
  `call_pricing` smallint(1) DEFAULT '0',
  `call_pricing_message` varchar(100) DEFAULT NULL,
  `cost` decimal(8,2) DEFAULT NULL,
  `sell_count` decimal(5,0) DEFAULT '0',
  `hit_count` decimal(5,0) DEFAULT '0',
  `spec_url` varchar(255) DEFAULT NULL,
  `weight` decimal(6,2) DEFAULT NULL,
  `weight_uom` varchar(10) DEFAULT NULL,
  `insert_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_product`
--

LOCK TABLES `inv_product` WRITE;
/*!40000 ALTER TABLE `inv_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `inv_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_t`
--

DROP TABLE IF EXISTS `menu_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_t` (
  `menuid` int(10) NOT NULL AUTO_INCREMENT,
  `target` varchar(1000) DEFAULT NULL,
  `menuname` varchar(1000) DEFAULT NULL,
  `parentId` varchar(1000) DEFAULT NULL,
  `orderId` int(10) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `level` varchar(1000) DEFAULT NULL,
  `relId` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_t`
--

LOCK TABLES `menu_t` WRITE;
/*!40000 ALTER TABLE `menu_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `money_detail_bigtype_v`
--

DROP TABLE IF EXISTS `money_detail_bigtype_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_bigtype_v` (
  `money` tinyint NOT NULL,
  `BIGtypeSNO` tinyint NOT NULL,
  `BIGtype` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `money_detail_bigtype_year_month_v`
--

DROP TABLE IF EXISTS `money_detail_bigtype_year_month_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_year_month_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_bigtype_year_month_v` (
  `money` tinyint NOT NULL,
  `BIGtypeSNO` tinyint NOT NULL,
  `BIGtype` tinyint NOT NULL,
  `YEAR` tinyint NOT NULL,
  `MONTH` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `money_detail_bigtype_year_v`
--

DROP TABLE IF EXISTS `money_detail_bigtype_year_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_year_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_bigtype_year_v` (
  `money` tinyint NOT NULL,
  `BIGtypeSNO` tinyint NOT NULL,
  `BIGtype` tinyint NOT NULL,
  `YEAR` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `money_detail_t`
--

DROP TABLE IF EXISTS `money_detail_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `money_detail_t` (
  `money_Sno` int(10) NOT NULL AUTO_INCREMENT,
  `money_Time` date DEFAULT NULL,
  `money` double(16,2) DEFAULT NULL,
  `money_Type` varchar(100) DEFAULT NULL,
  `money_Desc` varchar(2000) DEFAULT NULL,
  `shopCard` int(5) DEFAULT NULL,
  `bookType` int(5) DEFAULT NULL,
  `userful` varchar(10) DEFAULT NULL,
  `useful` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`money_Sno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `money_detail_t`
--

LOCK TABLES `money_detail_t` WRITE;
/*!40000 ALTER TABLE `money_detail_t` DISABLE KEYS */;
INSERT INTO `money_detail_t` VALUES (2,'2013-11-15',2222.00,'B1','222222',0,1,'1','1');
/*!40000 ALTER TABLE `money_detail_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `money_detail_type_v`
--

DROP TABLE IF EXISTS `money_detail_type_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_type_v` (
  `money` tinyint NOT NULL,
  `money_type` tinyint NOT NULL,
  `tallytype` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `money_detail_type_year_month_v`
--

DROP TABLE IF EXISTS `money_detail_type_year_month_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_year_month_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_type_year_month_v` (
  `money` tinyint NOT NULL,
  `money_type` tinyint NOT NULL,
  `tallytype` tinyint NOT NULL,
  `year` tinyint NOT NULL,
  `MONTH` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `money_detail_type_year_v`
--

DROP TABLE IF EXISTS `money_detail_type_year_v`;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_year_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_type_year_v` (
  `money` tinyint NOT NULL,
  `money_type` tinyint NOT NULL,
  `tallytype` tinyint NOT NULL,
  `year` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `money_detail_view`
--

DROP TABLE IF EXISTS `money_detail_view`;
/*!50001 DROP VIEW IF EXISTS `money_detail_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `money_detail_view` (
  `MONEY_SNO` tinyint NOT NULL,
  `MONEY_TIME` tinyint NOT NULL,
  `MONEY` tinyint NOT NULL,
  `MONEY_TYPE` tinyint NOT NULL,
  `MONEY_DESC` tinyint NOT NULL,
  `SHOPCARD` tinyint NOT NULL,
  `USEFUL` tinyint NOT NULL,
  `year` tinyint NOT NULL,
  `month` tinyint NOT NULL,
  `BOOKTYPE` tinyint NOT NULL,
  `tallytype` tinyint NOT NULL,
  `bigtypeSNO` tinyint NOT NULL,
  `bigtype` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `organization_t`
--

DROP TABLE IF EXISTS `organization_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_t` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orgName` varchar(1000) DEFAULT NULL,
  `orgcode` varchar(1000) DEFAULT NULL,
  `parentOrg` varchar(1000) DEFAULT NULL,
  `orderid` varchar(1000) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_t`
--

LOCK TABLES `organization_t` WRITE;
/*!40000 ALTER TABLE `organization_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameter_type`
--

DROP TABLE IF EXISTS `parameter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameter_type` (
  `parameter_type_Id` int(10) NOT NULL AUTO_INCREMENT,
  `parameter_type_name` varchar(100) DEFAULT NULL,
  `orderId` int(10) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`parameter_type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameter_type`
--

LOCK TABLES `parameter_type` WRITE;
/*!40000 ALTER TABLE `parameter_type` DISABLE KEYS */;
INSERT INTO `parameter_type` VALUES (1,'问题类别',1,'questionSort'),(2,'菜单级别',2,'menulevel'),(3,'计划状态',3,'planstatus'),(4,'计划类型',4,'plantype'),(5,'菜单目的',5,'menutarget'),(6,'问题状态',6,'questionStatus');
/*!40000 ALTER TABLE `parameter_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `params`
--

DROP TABLE IF EXISTS `params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `params` (
  `parameterID` int(10) NOT NULL AUTO_INCREMENT,
  `parameterType` int(10) DEFAULT NULL,
  `parameterName` varchar(100) DEFAULT NULL,
  `paramvalue` int(10) DEFAULT NULL,
  `usevalue` varchar(100) DEFAULT NULL,
  `orderId` int(10) DEFAULT NULL,
  PRIMARY KEY (`parameterID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `params`
--

LOCK TABLES `params` WRITE;
/*!40000 ALTER TABLE `params` DISABLE KEYS */;
INSERT INTO `params` VALUES (1,1,'简单',1,'0',1),(2,1,'中级',2,'0',2),(3,1,'复杂',3,'0',0),(4,2,'一级菜单',1,'0',0),(5,2,'二级菜单',2,'0',0),(6,2,'三级菜单',3,'0',0),(7,3,'计划中',1,'0',0),(8,3,'执行中',2,'0',0),(9,3,'执行完毕',3,'0',0),(10,3,'废弃',4,'0',0),(11,4,'紧急',1,'0',0),(12,4,'重要',2,'0',0),(13,4,'无足轻重',3,'0',0),(14,6,'待解决',1,'0',0),(15,6,'已经解决',2,'0',0),(16,6,'解决不了',3,'0',0);
/*!40000 ALTER TABLE `params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_t`
--

DROP TABLE IF EXISTS `plan_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_t` (
  `planId` int(10) NOT NULL AUTO_INCREMENT,
  `plandate` date DEFAULT NULL,
  `plandesc` varchar(1000) DEFAULT NULL,
  `planType` int(10) DEFAULT NULL,
  `planStatus` int(10) DEFAULT NULL,
  `userid` int(10) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `realstartdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `realenddate` date DEFAULT NULL,
  PRIMARY KEY (`planId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_t`
--

LOCK TABLES `plan_t` WRITE;
/*!40000 ALTER TABLE `plan_t` DISABLE KEYS */;
INSERT INTO `plan_t` VALUES (1,'2013-11-15','111',13,7,111,'2013-11-15','2013-11-15','2013-11-15','2013-11-15');
/*!40000 ALTER TABLE `plan_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_t`
--

DROP TABLE IF EXISTS `question_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_t` (
  `questionDesc` varchar(1000) DEFAULT NULL,
  `questionDate` date DEFAULT NULL,
  `consoleDate` date DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `submit` varchar(1000) DEFAULT NULL,
  `orderId` int(10) DEFAULT NULL,
  `id` int(10) DEFAULT NULL,
  `tag` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_t`
--

LOCK TABLES `question_t` WRITE;
/*!40000 ALTER TABLE `question_t` DISABLE KEYS */;
INSERT INTO `question_t` VALUES ('33333333333','2013-11-18','2013-11-18','2222222',3,15,'admin',0,3,NULL);
/*!40000 ALTER TABLE `question_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `question_v`
--

DROP TABLE IF EXISTS `question_v`;
/*!50001 DROP VIEW IF EXISTS `question_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `question_v` (
  `id` tinyint NOT NULL,
  `questionDesc` tinyint NOT NULL,
  `questiondate` tinyint NOT NULL,
  `consoledate` tinyint NOT NULL,
  `answer` tinyint NOT NULL,
  `tag` tinyint NOT NULL,
  `sort` tinyint NOT NULL,
  `ORDERID` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `typename` tinyint NOT NULL,
  `statusname` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `set_preference`
--

DROP TABLE IF EXISTS `set_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `set_preference` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(10) unsigned NOT NULL,
  `REF_KEY` varchar(100) NOT NULL,
  `REF_VALUE` text,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `insert_date` (`INSERT_DATE`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_preference`
--

LOCK TABLES `set_preference` WRITE;
/*!40000 ALTER TABLE `set_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `set_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `ID` char(32) NOT NULL,
  `USER_ID` char(32) NOT NULL,
  `LOG_TYPE` enum('LODGE_REPORT','LODGE_EA') NOT NULL,
  `LOG_LEVEL` enum('ERROR','WARN','INFO') NOT NULL,
  `IP_ADDRESS` varchar(64) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CONTENT` text,
  `INSERT_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sys_log_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `ROLE_ID` int(10) unsigned NOT NULL,
  `RESOURCE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RESOURCE_ID`),
  KEY `FK_sys_permission` (`RESOURCE_ID`),
  KEY `FK_sys_permission2` (`ROLE_ID`),
  CONSTRAINT `FK_sys_permission_resource` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `sys_resource` (`ID`),
  CONSTRAINT `FK_sys_permission_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `ID` int(10) unsigned NOT NULL,
  `SN` varchar(50) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `PURVIEW` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `ID` int(10) unsigned NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `STATUS` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'Person','Person','ACTIVE'),(2,'Manager','Manager','ACTIVE');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `ID` char(32) NOT NULL,
  `TITLE` enum('Mr','Mrs','Miss','Ms','Dr','Prof') DEFAULT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `USER_TYPE` enum('PERSON','ADMIN') NOT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `POST_CODE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `STATUS` enum('ACTIVE','INACTIVE','TEST','DELETED') NOT NULL,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `INSERT_BY` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `i_user_name` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('101','Mr','admin','123456','ADMIN','huihua','zhang','100091','zhanghuihua@sohu.com','1234567','ACTIVE','2011-01-01 00:00:00','2010-12-22 00:00:00','2011-09-15 10:36:37','101'),('402881b22d0cf023012d0cf0efc50001','Miss','test','123456','PERSON','xx','x','100091','11dd@11.com','1234567','ACTIVE','2010-01-09 00:00:00','2010-12-19 00:00:00','2011-02-19 11:56:26','101'),('402881b2326af30501326afcd7c50002','Mr','test01','ux71jt','PERSON','huihua','zhang','100027','zhanghuihua1@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-13 10:49:07','2011-09-13 10:49:07','101'),('402881b2326af30501326afd224b0003','Mr','test02','dgxo5k','PERSON','huihua','zhang','100027','zhanghuihua2@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-14 10:49:26','2011-09-14 10:49:26','101'),('402881b2326af30501326afee9fb0004','Mr','test03','ifixla','PERSON','huihua','zhang','100027','zhanghuihua3@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:51:23','2011-09-15 10:51:23','101'),('402881b2326af30501326b00517a0005','Mr','test04','r8t2sy','PERSON','huihua','zhang','100027','zhanghuihua4@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:52:55','2011-09-15 10:52:55','101'),('402881b2326af30501326b0062a10006','Mr','test05','w4fq4s','PERSON','huihua','zhang','100027','zhanghuihua5@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:52:59','2011-09-15 10:52:59','101'),('402881b2326af30501326b006d5f0007','Mr','test06','dec00g','PERSON','huihua','zhang','100027','zhanghuihua6@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:02','2011-09-15 10:53:02','101'),('402881b2326af30501326b0075eb0008','Mr','test07','u6mxw2','PERSON','huihua','zhang','100027','zhanghuihua7@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:04','2011-09-15 10:53:04','101'),('402881b2326af30501326b0088d60009','Mr','test08','jzfndz','PERSON','huihua','zhang','100027','zhanghuihua8@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:09','2011-09-15 10:53:09','101'),('402881b2326af30501326b009171000a','Mr','test09','yb35ag','PERSON','huihua','zhang','100027','zhanghuihua9@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:11','2011-09-15 10:53:11','101'),('402881b2326af30501326b009d68000b','Mr','test10','zyf8hn','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:14','2011-09-15 10:53:14','101'),('402881b2326af30501326b00df72000c','Mr','test11','thvd6b','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:31','2011-09-15 10:53:31','101'),('402881b2326af30501326b00e761000d','Mr','test12','at4caz','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:33','2011-09-15 10:53:33','101'),('402881b2326af30501326b00ee86000e','Mr','test13','cefe11','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:35','2011-09-15 10:53:35','101'),('402881b2326af30501326b00f55c000f','Mr','test14','758jpv','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:37','2011-09-15 10:53:37','101'),('402881b2326af30501326b00fc320010','Mr','test15','ojtxok','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:53:39','2011-09-15 10:53:39','101'),('402881b2326af30501326b0160f60011','Mr','test16','z7jxr3','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:05','2011-09-15 10:54:05','101'),('402881b2326af30501326b0166b30012','Mr','test17','egtvvn','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:06','2011-09-15 10:54:06','101'),('402881b2326af30501326b016c410013','Mr','test18','2yvx3m','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:07','2011-09-15 10:54:07','101'),('402881b2326af30501326b01728a0014','Mr','test19','751lim','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:09','2011-09-15 10:54:09','101'),('402881b2326af30501326b0181300015','Mr','test20','yhw1vc','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:13','2011-09-15 10:54:13','101'),('402881b2326af30501326b0187890016','Mr','test21','oothr3','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:14','2011-09-15 10:54:14','101'),('402881b2326af30501326b0190530017','Mr','test22','aahiti','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:17','2011-09-15 10:54:17','101'),('402881b2326af30501326b01996c0018','Mr','test23','bv15u0','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','ACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:19','2011-09-15 10:54:19','101'),('402881b2326af30501326b01a2160019','Mr','test24','mzk5gg','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','INACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:21','2011-09-15 10:58:51','101'),('402881b2326af30501326b01a840001a','Mr','test25','tti21a','PERSON','huihua','zhang','100027','zhanghuihua@sohu.com','13621397091','INACTIVE','1979-11-13 00:00:00','2011-09-15 10:54:23','2011-09-15 12:54:03','101');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `USER_ID` char(32) NOT NULL,
  `ROLE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_sys_user_role` (`USER_ID`),
  KEY `FK_sys_user_role2` (`ROLE_ID`),
  CONSTRAINT `FK_sys_user_role` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`ID`),
  CONSTRAINT `FK_sys_user_role2` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tally_type_t`
--

DROP TABLE IF EXISTS `tally_type_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tally_type_t` (
  `tally_type_sno` int(10) NOT NULL AUTO_INCREMENT,
  `tally_type_desc` varchar(1000) DEFAULT NULL,
  `money_type` varchar(100) DEFAULT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `type_code` varchar(2000) DEFAULT NULL,
  `orderid` int(5) DEFAULT NULL,
  PRIMARY KEY (`tally_type_sno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tally_type_t`
--

LOCK TABLES `tally_type_t` WRITE;
/*!40000 ALTER TABLE `tally_type_t` DISABLE KEYS */;
INSERT INTO `tally_type_t` VALUES (1,'大型开支','1',NULL,'A',1),(2,'常规支出','1',NULL,'B',2),(3,'房租','B1','B','B1',3);
/*!40000 ALTER TABLE `tally_type_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_t`
--

DROP TABLE IF EXISTS `user_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_t` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(1000) DEFAULT NULL,
  `pass` varchar(1000) DEFAULT NULL,
  `loginId` varchar(1000) DEFAULT NULL,
  `orgId` int(10) DEFAULT NULL,
  `email` varchar(1000) DEFAULT NULL,
  `phone` varchar(1000) DEFAULT NULL,
  `mobile` varchar(1000) DEFAULT NULL,
  `usertype` varchar(1000) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `orderId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_t`
--

LOCK TABLES `user_t` WRITE;
/*!40000 ALTER TABLE `user_t` DISABLE KEYS */;
INSERT INTO `user_t` VALUES (1,'renjie120','`=Ub5.?cIQMR','test',1,'111@11.com',NULL,NULL,'1',NULL,1),(2,'李水清','X:VpHm9Da`Bm2+5SJbDzPt0RFIHS','admin',0,'','11111111111111','1','2','1',1);
/*!40000 ALTER TABLE `user_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_page`
--

DROP TABLE IF EXISTS `web_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_page` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `SEQUENCE` int(5) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `META_KEYWORDS` varchar(255) DEFAULT NULL,
  `META_DESCRIPTION` varchar(500) DEFAULT NULL,
  `CONTENT` text,
  `TARGET` set('header','sidebar','footer') DEFAULT NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_page`
--

LOCK TABLES `web_page` WRITE;
/*!40000 ALTER TABLE `web_page` DISABLE KEYS */;
INSERT INTO `web_page` VALUES (1,'联系我们',1,'联系我们','','','联系我们<br />','header,footer','2012-06-19 16:58:20',NULL),(3,'公司介绍',0,'公司介绍','','','公司介绍','header','2011-02-19 20:41:32',NULL),(2,'服务条款',2,'服务条款','','','服务条款<br />','header,footer','2012-06-19 16:58:25',NULL),(5,'友情链接',3,'友情链接','','','','header,footer','2012-06-19 16:58:29',NULL),(18,'法律声明',0,'法律声明','','','','header','2012-06-19 16:58:04',NULL),(19,'友情链接',0,'友情链接','','','','header','2012-06-19 16:58:15',NULL),(20,'测试页面1',NULL,'测试页面 title','','','','header,footer','2012-06-19 16:57:59',NULL),(21,'测试页面2',NULL,'测试页面2','','','测试页面2','header','2012-06-19 16:59:39',NULL);
/*!40000 ALTER TABLE `web_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_website`
--

DROP TABLE IF EXISTS `web_website`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_website` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) DEFAULT NULL,
  `META_KEYWORDS` varchar(255) DEFAULT NULL,
  `META_DESCRIPTION` varchar(500) DEFAULT NULL,
  `TEMPLATE` varchar(20) DEFAULT NULL,
  `LAYOUT` varchar(20) DEFAULT NULL,
  `THEME` varchar(20) DEFAULT NULL,
  `LOGO` text,
  `AREA_HEADER` text,
  `AREA_SIDEBAR` text,
  `AREA_BANNER` text,
  `AREA_FOOTER` text,
  `ICP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_website`
--

LOCK TABLES `web_website` WRITE;
/*!40000 ALTER TABLE `web_website` DISABLE KEYS */;
INSERT INTO `web_website` VALUES (1,'DWZ Java Web 快速开发平台','DWZ Java RIA jQuery','DWZ Java Web 快速开发平台',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `web_website` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `money_detail_bigtype_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_bigtype_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_bigtype_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`bigtypeSNO` AS `BIGtypeSNO`,`t`.`bigtype` AS `BIGtype` from `money_detail_view` `t` group by `t`.`bigtypeSNO`,`t`.`bigtype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_bigtype_year_month_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_bigtype_year_month_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_year_month_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_bigtype_year_month_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`bigtypeSNO` AS `BIGtypeSNO`,`t`.`bigtype` AS `BIGtype`,`t`.`year` AS `YEAR`,`t`.`month` AS `MONTH` from `money_detail_view` `t` group by `t`.`year`,`t`.`month`,`t`.`bigtypeSNO`,`t`.`bigtype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_bigtype_year_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_bigtype_year_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_bigtype_year_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_bigtype_year_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`bigtypeSNO` AS `BIGtypeSNO`,`t`.`bigtype` AS `BIGtype`,`t`.`year` AS `YEAR` from `money_detail_view` `t` group by `t`.`year`,`t`.`bigtypeSNO`,`t`.`bigtype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_type_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_type_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_type_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`MONEY_TYPE` AS `money_type`,`t`.`tallytype` AS `tallytype` from `money_detail_view` `t` group by `t`.`MONEY_TYPE`,`t`.`tallytype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_type_year_month_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_type_year_month_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_year_month_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_type_year_month_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`MONEY_TYPE` AS `money_type`,`t`.`tallytype` AS `tallytype`,`t`.`year` AS `year`,`t`.`month` AS `MONTH` from `money_detail_view` `t` group by `t`.`year`,`t`.`month`,`t`.`MONEY_TYPE`,`t`.`tallytype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_type_year_v`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_type_year_v`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_type_year_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_type_year_v` AS select sum(`t`.`MONEY`) AS `money`,`t`.`MONEY_TYPE` AS `money_type`,`t`.`tallytype` AS `tallytype`,`t`.`year` AS `year` from `money_detail_view` `t` group by `t`.`year`,`t`.`MONEY_TYPE`,`t`.`tallytype` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `money_detail_view`
--

/*!50001 DROP TABLE IF EXISTS `money_detail_view`*/;
/*!50001 DROP VIEW IF EXISTS `money_detail_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `money_detail_view` AS select `dt`.`money_Sno` AS `MONEY_SNO`,`dt`.`money_Time` AS `MONEY_TIME`,`dt`.`money` AS `MONEY`,`dt`.`money_Type` AS `MONEY_TYPE`,`dt`.`money_Desc` AS `MONEY_DESC`,`dt`.`shopCard` AS `SHOPCARD`,`dt`.`useful` AS `USEFUL`,year(`dt`.`money_Time`) AS `year`,month(`dt`.`money_Time`) AS `month`,`dt`.`bookType` AS `BOOKTYPE`,`tp`.`tally_type_desc` AS `tallytype`,`tp2`.`tally_type_sno` AS `bigtypeSNO`,`tp2`.`tally_type_desc` AS `bigtype` from ((`money_detail_t` `dt` join `tally_type_t` `tp2`) join `tally_type_t` `tp`) where ((`tp`.`parent_code` = `tp2`.`type_code`) and (`dt`.`money_Type` = `tp`.`type_code`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `question_v`
--

/*!50001 DROP TABLE IF EXISTS `question_v`*/;
/*!50001 DROP VIEW IF EXISTS `question_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `question_v` AS select `qt`.`id` AS `id`,`qt`.`questionDesc` AS `questionDesc`,`qt`.`questionDate` AS `questiondate`,`qt`.`consoleDate` AS `consoledate`,`qt`.`answer` AS `answer`,`qt`.`tag` AS `tag`,`qt`.`sort` AS `sort`,`qt`.`orderId` AS `ORDERID`,`qt`.`status` AS `status`,`pm`.`parameterName` AS `typename`,`pm2`.`parameterName` AS `statusname` from ((`question_t` `qt` join `params` `pm`) join `params` `pm2`) where ((`qt`.`sort` = `pm`.`parameterID`) and (`pm`.`parameterType` = 5) and (`qt`.`status` = `pm2`.`parameterID`) and (`pm2`.`parameterType` = 6)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-19 17:51:01
