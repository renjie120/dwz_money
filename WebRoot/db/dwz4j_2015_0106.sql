-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: localhost    Database: dwz4j
-- ------------------------------------------------------
-- Server version	5.0.16-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES gbk */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `cache_t`
--

DROP TABLE IF EXISTS `cache_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache_t` (
  `id` int(10) NOT NULL auto_increment,
  `cacheId` varchar(1000) default NULL,
  `cacheName` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache_t`
--

LOCK TABLES `cache_t` WRITE;
/*!40000 ALTER TABLE `cache_t` DISABLE KEYS */;
INSERT INTO `cache_t` VALUES (31,'moneyType','Ω∂Ó¿‡–Õ'),(32,'allparamtype',' Ù–‘¿‡–Õ'),(33,'allparamtypecode','»´≤ø≤Œ ˝¥˙¬Î'),(34,'paramtype1','≤Œ ˝:questionSort'),(35,'paramtype2','≤Œ ˝:menulevel'),(36,'paramtype3','≤Œ ˝:planstatus'),(37,'paramtype4','≤Œ ˝:plantype'),(38,'paramtype5','≤Œ ˝:menutarget'),(39,'paramtype6','≤Œ ˝:questionStatus'),(40,'paramtype7','≤Œ ˝:nianfen'),(41,'paramtype8','≤Œ ˝:yuefen'),(42,'paramtype9','≤Œ ˝:gongguo_type'),(43,'paramtype10','≤Œ ˝:usertype'),(44,'paramtype11','≤Œ ˝:diarytype'),(45,'paramtype12','≤Œ ˝:dealType'),(46,'paramtype13','≤Œ ˝:orderstatus'),(47,'moneyTypeTree','Ω∂Ó¿‡–Õ ˜'),(50,'menuTree','≤Àµ• ˜'),(49,'orgTree','◊È÷Øª˙ππ ˜');
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
  `USER_ID` char(32) default NULL,
  `FOLDER_ID` char(32) default NULL,
  `NAME` varchar(100) NOT NULL,
  `EXT` varchar(32) default NULL,
  `PATH` varchar(255) default NULL,
  `SIZE` int(11) default NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime default NULL,
  `RESIZE` set('S','M') default NULL,
  `FILE_TYPE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `insert_date` (`INSERT_DATE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `LFT` int(11) default NULL,
  `RGT` int(11) default NULL,
  `PARENT_ID` varchar(32) default NULL,
  `USER_ID` varchar(32) default NULL,
  `INSERT_DATE` datetime NOT NULL,
  `INSERT_BY` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `con_folder`
--

LOCK TABLES `con_folder` WRITE;
/*!40000 ALTER TABLE `con_folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `con_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_t`
--

DROP TABLE IF EXISTS `file_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_t` (
  `fileid` varchar(100) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `filename` text NOT NULL,
  `content` mediumblob,
  `filelen` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_t`
--

LOCK TABLES `file_t` WRITE;
/*!40000 ALTER TABLE `file_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inf_news`
--

DROP TABLE IF EXISTS `inf_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inf_news` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `COMPANY_ID` int(10) unsigned default NULL,
  `TYPE` enum('NEWS','NOTICE','ANNOUNCEMENT') NOT NULL,
  `STATUS` enum('PENDING','ACTIVE','DISABLED') NOT NULL,
  `TITLE` varchar(255) default NULL,
  `CONTENT` text,
  `AUTHOR` varchar(100) default NULL,
  `SOURCE` varchar(100) default NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `INSERT_DATE` (`INSERT_DATE`),
  KEY `NewIndex1` (`UPDATE_DATE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inf_news`
--

LOCK TABLES `inf_news` WRITE;
/*!40000 ALTER TABLE `inf_news` DISABLE KEYS */;
INSERT INTO `inf_news` VALUES (1,NULL,'NEWS','ACTIVE','DWZ Java Web ','###DWZ Java Web ',NULL,NULL,'2010-12-22 10:26:09','2010-12-22 10:26:09'),(2,NULL,'NEWS','PENDING','DWZ Java Web ','<span style=\"font-size:medium;\"></span>DWZ Java Web ',NULL,NULL,'2011-02-19 20:37:14','2011-02-19 20:37:14');
/*!40000 ALTER TABLE `inf_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insured_unit`
--

DROP TABLE IF EXISTS `insured_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insured_unit` (
  `ID` int(10) NOT NULL auto_increment,
  `unit_code` varchar(500) NOT NULL,
  `unit_Name` varchar(1000) NOT NULL,
  `contact_name` varchar(100) default NULL,
  `contact_mobile` varchar(20) default NULL,
  `contact_email` varchar(500) default NULL,
  `unit_parent_id` int(10) default NULL,
  `unit_state` varchar(1) default NULL,
  `unit_address` varchar(500) default NULL,
  `unit_remark` varchar(4000) default NULL,
  `create_user` int(10) default NULL,
  `create_time` varchar(20) default NULL,
  `update_user` int(10) default NULL,
  `update_time` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insured_unit`
--

LOCK TABLES `insured_unit` WRITE;
/*!40000 ALTER TABLE `insured_unit` DISABLE KEYS */;
INSERT INTO `insured_unit` VALUES (4,'12111','1','1','1','1',0,'','','',32,'3232',2323,'2323');
/*!40000 ALTER TABLE `insured_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_category`
--

DROP TABLE IF EXISTS `inv_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_category` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `pid` int(10) unsigned default NULL,
  `lft` int(10) unsigned default NULL,
  `rgt` int(10) unsigned default NULL,
  `name` varchar(100) default NULL,
  `description` text,
  `meta_title` varchar(100) default NULL,
  `meta_keyword` varchar(200) default NULL,
  `meta_description` varchar(500) default NULL,
  `pic_url` varchar(255) default NULL,
  `product_num` int(11) NOT NULL default '0',
  `insert_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `brand_id` int(10) unsigned default NULL,
  `category_id` int(10) unsigned default NULL,
  `status` varchar(32) default NULL,
  `sku` varchar(30) NOT NULL,
  `name` varchar(100) default NULL,
  `short_description` text,
  `long_description` text,
  `pic_url` varchar(255) default NULL,
  `meta_title` varchar(100) default NULL,
  `meta_keyword` text,
  `meta_description` text,
  `regular_price` decimal(8,2) default NULL,
  `quantity` int(11) default '0',
  `sales_price` decimal(8,2) default NULL,
  `show_sales_price` smallint(1) default '1',
  `list_price` decimal(8,2) default NULL,
  `show_list_price` smallint(1) default NULL,
  `show_on_web` smallint(1) default '1',
  `call_pricing` smallint(1) default '0',
  `call_pricing_message` varchar(100) default NULL,
  `cost` decimal(8,2) default NULL,
  `sell_count` decimal(5,0) default '0',
  `hit_count` decimal(5,0) default '0',
  `spec_url` varchar(255) default NULL,
  `weight` decimal(6,2) default NULL,
  `weight_uom` varchar(10) default NULL,
  `insert_date` datetime default NULL,
  `update_date` datetime default NULL,
  PRIMARY KEY  (`id`)
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
  `menuid` int(10) NOT NULL auto_increment,
  `target` varchar(1000) default NULL,
  `menuname` varchar(1000) default NULL,
  `parentId` varchar(1000) default NULL,
  `orderId` int(10) default NULL,
  `url` varchar(1000) default NULL,
  `level` varchar(1000) default NULL,
  `relId` varchar(1000) default NULL,
  PRIMARY KEY  (`menuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_t`
--

LOCK TABLES `menu_t` WRITE;
/*!40000 ALTER TABLE `menu_t` DISABLE KEYS */;
INSERT INTO `menu_t` VALUES (1,NULL,'∞Æ∂ºœµÕ≥','0',0,'null','4','A'),(3,NULL,'ª˘±æ–≈œ¢¡–±Ì','1',0,'','5','A2'),(8,NULL,'≤Àµ•¡–±Ì','14',0,'/money/menu!query.do','5','A2_1'),(14,NULL,'œµÕ≥Œ¨ª§','0',0,'null','4','B'),(15,NULL,'≤Œ ˝¿‡–Õ','14',0,'/money/paramtype!query.do','6','A2_4'),(16,NULL,'≤Œ ˝¡–±Ì','14',0,'/money/param!query.do','5','B1'),(17,NULL,'≤Œ ˝¿‡–Õ','14',0,'/money/paramtype!query.do','5','B2'),(18,NULL,'◊È÷Øª˙ππ','14',0,'/money/org!query.do','5','B3'),(20,NULL,'ª∫¥Ê¡–±Ì','14',0,'/money/cache!query.do','5','B4'),(22,NULL,'”√ªß¡–±Ì','14',0,'/money/myuser!query.do','5','B6'),(70,NULL,'Õ∂±£µ•Œª','1',1,'/money/insuredunit!query.do','5','ido_tbdw'),(28,NULL,'Œƒº˛…œ¥´','14',0,'/upload/test!init.do','5','C5'),(29,NULL,'Œƒº˛…œ¥´2','14',0,'/upload/test2!init.do','5','C6'),(33,NULL,'»®œﬁŒ¨ª§','2',0,'/money/usermenuright!init.do','6','CCC'),(38,NULL,'Ω«…´π‹¿Ì','14',0,'/money/role!query.do','5','DDD'),(44,NULL,'Œƒº˛π‹¿Ì','14',0,'/money/filemanager!query.do','-2','wjgl'),(65,NULL,'ÃÌº”','9',0,'null','66','addShouzhi'),(66,NULL,'…æ≥˝','9',0,'null','66','delShouzhi'),(67,NULL,'–ﬁ∏ƒ','9',0,'null','66','updateShouzhi'),(68,NULL,'µº≥ˆ','9',0,'null','66','exportExcel'),(69,NULL,'µº»Î','9',0,'null','66','importExcel');
/*!40000 ALTER TABLE `menu_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month_t`
--

DROP TABLE IF EXISTS `month_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `month_t` (
  `ID` varchar(2) NOT NULL,
  `VALUE` varchar(2) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month_t`
--

LOCK TABLES `month_t` WRITE;
/*!40000 ALTER TABLE `month_t` DISABLE KEYS */;
INSERT INTO `month_t` VALUES ('1','1'),('10','10'),('11','11'),('12','12'),('2','2'),('3','3'),('4','4'),('5','5'),('6','6'),('7','7'),('8','8'),('9','9');
/*!40000 ALTER TABLE `month_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_t`
--

DROP TABLE IF EXISTS `organization_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_t` (
  `id` int(10) NOT NULL auto_increment,
  `orgName` varchar(1000) default NULL,
  `orgcode` varchar(1000) default NULL,
  `parentOrg` varchar(1000) default NULL,
  `orderid` varchar(1000) default NULL,
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_t`
--

LOCK TABLES `organization_t` WRITE;
/*!40000 ALTER TABLE `organization_t` DISABLE KEYS */;
INSERT INTO `organization_t` VALUES (1,'◊‹≤ø','zb','0',NULL),(2,'±±æ©π´Àæ','BJ','1',NULL),(0,NULL,'0',NULL,NULL),(3,'test','www','2',NULL);
/*!40000 ALTER TABLE `organization_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameter_type`
--

DROP TABLE IF EXISTS `parameter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameter_type` (
  `parameter_type_Id` int(10) NOT NULL auto_increment,
  `parameter_type_name` varchar(100) default NULL,
  `orderId` int(10) default NULL,
  `code` varchar(100) default NULL,
  PRIMARY KEY  (`parameter_type_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameter_type`
--

LOCK TABLES `parameter_type` WRITE;
/*!40000 ALTER TABLE `parameter_type` DISABLE KEYS */;
INSERT INTO `parameter_type` VALUES (1,'Œ Ã‚¿‡–Õ',1,'questionSort'),(2,'≤Àµ•º∂±',2,'menulevel'),(3,'º∆ªÆ◊¥Ã¨',3,'planstatus'),(4,'º∆ªÆ¿‡–Õ',4,'plantype'),(5,'≤Àµ•ƒø±Í',5,'menutarget'),(6,'Œ Ã‚◊¥Ã¨',6,'questionStatus'),(7,'ƒÍ∂»',0,'nianfen'),(8,'‘¬∑›',0,'yuefen'),(9,'π¶π˝¿‡–Õ',10,'gongguo_type'),(10,'”√ªß¿‡–Õ',0,'usertype'),(11,'»’÷æ¿‡–Õ',0,'diarytype'),(12,'Ωª“◊¿‡–Õ',0,'dealType'),(13,'∂©µ•◊¥Ã¨',0,'orderstatus');
/*!40000 ALTER TABLE `parameter_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `params`
--

DROP TABLE IF EXISTS `params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `params` (
  `parameterID` int(10) NOT NULL auto_increment,
  `parameterType` int(10) default NULL,
  `parameterName` varchar(100) default NULL,
  `paramvalue` int(10) default NULL,
  `usevalue` varchar(100) default NULL,
  `orderId` int(10) default NULL,
  PRIMARY KEY  (`parameterID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `params`
--

LOCK TABLES `params` WRITE;
/*!40000 ALTER TABLE `params` DISABLE KEYS */;
INSERT INTO `params` VALUES (7,3,'º∆ªÆ÷–',1,'0',0),(6,2,'»˝º∂≤Àµ•',3,'0',0),(5,2,'∂˛º∂≤Àµ•',2,'0',0),(4,2,'“ªº∂≤Àµ•',1,'0',0),(3,1,'∏¥‘”',3,'0',0),(2,1,'÷–º∂',2,'0',2),(1,1,'ºÚµ•',1,'0',1),(8,3,'÷¥––÷–',2,'0',0),(9,3,'÷¥––ÕÍ±œ',3,'0',0),(10,3,'∑œ∆˙',4,'0',0),(11,4,'ΩÙº±',1,'0',0),(12,4,'÷ÿ“™',2,'0',0),(13,4,'Œﬁ◊„«·÷ÿ',3,'0',0),(14,6,'¥˝Ω‚æˆ',1,'0',0),(15,6,'“—æ≠Ω‚æˆ',2,'0',0),(16,6,'Ω‚æˆ≤ª¡À',3,'0',0),(17,7,'2008',2008,'',0),(18,7,'2009',2009,'',0),(19,7,'2010',2010,'',0),(20,7,'2011',2011,'',0),(21,7,'2012',2012,'',0),(22,7,'2013',2013,'',0),(23,7,'2014',2014,'',0),(24,8,'“ª‘¬',1,'',0),(25,8,'∂˛‘¬',2,'',0),(26,8,'»˝‘¬',3,'',0),(27,8,'Àƒ‘¬',4,'',0),(28,8,'ŒÂ‘¬',5,'',0),(29,8,'¡˘‘¬',6,'',0),(30,8,'∆ﬂ‘¬',7,'',0),(31,8,'∞À‘¬',8,'',0),(32,8,'æ≈‘¬',9,'',0),(33,8,' Æ‘¬',10,'',0),(34,8,' Æ“ª‘¬',11,'',0),(35,8,' Æ∂˛‘¬',12,'',0),(36,9,'—ßœ∞¡Ω–° ±',1,'A',0),(37,9,'≤ªø¥µÁ”∞',2,'B',0),(38,9,'±£≥÷’˚Ω‡',3,'C',0),(39,9,'∂Õ¡∂…ÌÃÂ',4,'D',0),(40,9,'‘Á…œ∆ﬂµ„∆¥≤',5,'E',0),(41,9,'ÕÌ…œ11µ„ÀØæı',6,'F',0),(42,9,'≤ªø¥–¬Œ≈≥¨π˝30∑÷÷”',7,'G',0),(43,9,'≤ª≥‘¡„ ≥',8,'H',0),(44,9,'π§◊˜ ±º‰±•∫Õ',9,'I',0),(45,9,'º·≥÷º«’À',10,'J',0),(46,9,'∂‡”Î»ÀπµÕ®',11,'K',0),(47,9,'±£≥÷—ßœ∞–ƒ',12,'L',0),(48,9,'≤ª∑¢≈≠',13,'M',0),(49,9,'…ŸΩ≤∑œª∞',14,'N',0),(50,9,'◊‹Ω·—ßœ∞–ƒµ√',15,'O',0),(51,9,'≤ªÕÊ”Œœ∑≥¨π˝30∑÷÷”',16,'P',0),(52,9,'º·≥÷º«¬ºπ¶π˝¬º',17,'Q',0),(53,9,'æ≤–ƒ',18,'R',0),(54,9,'∞¥º∆ªÆ÷¥––',19,'S',0),(55,10,'≥¨º∂”√ªß',1,'1',0),(56,10,'π‹¿Ì‘±',2,'2',0),(57,10,'∆’Õ®”√ªß',3,'3',0),(58,11,'∆’Õ®',0,'0',0),(59,11,'¿Ì≤∆',1,'1',0),(60,11,'º∆ªÆ',2,'2',0),(61,12,'¬Ú»Î',1,'1',0),(62,12,'¬Ù≥ˆ',2,'2',0),(63,13,'Œ¥…˙≤˙',1,'1',0),(64,13,'‘⁄÷∆∆∑',2,'2',0),(65,13,'“—ÕÍ≥…',3,'3',0),(66,2,'∞¥≈•',4,'4',0);
/*!40000 ALTER TABLE `params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `push_user`
--

DROP TABLE IF EXISTS `push_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `push_user` (
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(10) default NULL,
  `token` varchar(300) default NULL,
  `devicetype` varchar(10) default NULL,
  `syscode` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `push_user`
--

LOCK TABLES `push_user` WRITE;
/*!40000 ALTER TABLE `push_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `push_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rep_user`
--

DROP TABLE IF EXISTS `rep_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rep_user` (
  `id` int(11) NOT NULL auto_increment,
  `brandname` varchar(200) default NULL,
  `brandtype` varchar(10) default NULL,
  `area` float default NULL,
  `address` varchar(1000) default NULL,
  `price` float default NULL,
  `people_flownum_work` varchar(10) default NULL,
  `people_flownum_weekend` varchar(10) default NULL,
  `phone` varchar(15) default NULL,
  `password` varchar(500) default NULL,
  `lng_north` varchar(100) default NULL,
  `lat_east` varchar(100) default NULL,
  `param1` varchar(100) default NULL,
  `param2` varchar(100) default NULL,
  `work_time` varchar(100) default NULL COMMENT 'Ëê•‰∏öÊó∂Èó¥Â≠óÁ¨¶‰∏≤',
  `location` varchar(1000) default NULL,
  `work_time_num` float default NULL COMMENT 'ËÆ°ÁÆóËê•‰∏öÊó∂Èó¥',
  `indate` date default NULL COMMENT 'Ê≥®ÂÜåÊó∂Èó¥',
  `year` int(11) default NULL,
  `month` int(11) default NULL,
  `day` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rep_user`
--

LOCK TABLES `rep_user` WRITE;
/*!40000 ALTER TABLE `rep_user` DISABLE KEYS */;
INSERT INTO `rep_user` VALUES (19,'≤È','123',NULL,NULL,12,'1','1','12345678903','123','31.023081','121.451713',NULL,NULL,'1','…œ∫£ –',NULL,'2014-06-06',2014,5,6),(21,'∆ﬂ∆•¿«','ƒ–◊∞',NULL,NULL,12,'123','123','12345678901','123','31.206017','121.262471',NULL,NULL,'12','…œ∫£ –',NULL,'2014-06-06',2014,5,6),(5,'¿Óƒ˛','–¨',NULL,NULL,NULL,NULL,NULL,'18123474157',NULL,NULL,NULL,NULL,NULL,NULL,'Àƒ¥®',NULL,'2014-05-06',2014,5,6),(6,'∞≤Ã§','–¨',NULL,NULL,NULL,NULL,NULL,'18345874157',NULL,NULL,NULL,NULL,NULL,NULL,'Àƒ¥®',NULL,'2014-05-05',2014,5,5),(7,'Ãÿ≤Ω','–¨',NULL,NULL,NULL,NULL,NULL,'18345674157',NULL,NULL,NULL,NULL,NULL,NULL,'∫˛±±',NULL,'2014-05-04',2014,5,4),(8,'√¿ΩÚ≈®','–¨',NULL,NULL,NULL,NULL,NULL,'18235534157',NULL,NULL,NULL,NULL,NULL,NULL,'∫˛±±',NULL,'2014-05-02',2014,5,2),(9,'‘∆√Œ','–¨',NULL,NULL,NULL,NULL,NULL,'18616324537',NULL,NULL,NULL,NULL,NULL,NULL,'∫˛±±',NULL,'2014-04-02',2014,4,2),(10,'≈£ΩÚ','–¨',NULL,NULL,NULL,NULL,NULL,'18616874345',NULL,NULL,NULL,NULL,NULL,NULL,'…œ∫£',NULL,'2014-02-10',2014,2,10),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'15000000000','K)(8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-05-29',2014,4,29),(12,'yYY','tf',NULL,NULL,23,'125','458','12345678911','qqq','31.19706882','121.25906828',NULL,NULL,'10','…œ∫£ –',NULL,'2014-05-29',2014,4,29),(13,'PPTV','jhh',NULL,NULL,25,'258','588','33333333333','WI%o','31.19706882','121.25906828',NULL,NULL,'10','…œ∫£ –',NULL,'2014-05-29',2014,4,29),(14,'ªÿ¿¥','ªÿ¿¥',NULL,NULL,1,'12','12','12312312312','WI%o','31.201197','121.270798',NULL,NULL,'12','…œ∫£ –',NULL,'2014-06-05',2014,5,5),(15,'¡À','¡À',NULL,NULL,55,'25','58','15915915921','WI$=','4.9E-324','4.9E-324',NULL,NULL,'5','',NULL,'2014-06-05',2014,5,5),(16,'µƒ','ø¥ø¥',NULL,NULL,58,'55','25','22222222222','lCcN','31.201197','121.270798',NULL,NULL,'55','…œ∫£ –',NULL,'2014-06-05',2014,5,5),(17,'GXG','ƒ–◊∞',NULL,NULL,980,'120','300','18621268801','WI$=','31.16891','121.347863',NULL,NULL,'12','…œ∫£ –',NULL,'2014-06-05',2014,5,5),(20,'≤‚ ‘','≤‚ ‘',NULL,NULL,180,'123','123','18616818351','WI$=','31.206017','121.262471',NULL,NULL,'12','…œ∫£ –',NULL,'2014-06-06',2014,5,6),(22,'À˚','“¬∑˛',NULL,NULL,12,'12','12','98765432101','987','31.201086','121.270833',NULL,NULL,'12','…œ∫£ –',NULL,'2014-06-07',2014,5,7);
/*!40000 ALTER TABLE `rep_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu_right`
--

DROP TABLE IF EXISTS `role_menu_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu_right` (
  `roleid` int(11) default NULL,
  `menuid` int(11) default NULL,
  `id` int(15) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu_right`
--

LOCK TABLES `role_menu_right` WRITE;
/*!40000 ALTER TABLE `role_menu_right` DISABLE KEYS */;
INSERT INTO `role_menu_right` VALUES (6,4,1),(3,42,164),(3,44,163),(3,40,162),(3,39,161),(3,38,160),(3,32,159),(3,31,158),(22,1,41),(22,2,42),(22,3,43),(22,4,44),(22,5,45),(22,6,46),(22,7,47),(22,8,48),(22,9,49),(22,10,50),(22,11,51),(22,12,52),(22,13,53),(22,14,54),(22,15,55),(22,16,56),(22,17,57),(22,18,58),(22,19,59),(22,20,60),(22,21,61),(22,22,62),(22,23,63),(22,24,64),(22,25,65),(22,26,66),(22,27,67),(22,28,68),(22,29,69),(22,30,70),(22,31,71),(22,32,72),(22,33,73),(22,34,74),(22,35,75),(22,36,76),(22,37,77),(22,38,78),(22,39,79),(3,30,157),(3,29,156),(3,28,155),(3,27,154),(3,26,153),(3,25,152),(3,24,151),(3,23,150),(3,22,149),(3,21,148),(3,20,147),(3,18,146),(3,17,145),(3,16,144),(3,14,143),(3,53,142),(3,52,141),(3,13,140),(3,12,139),(3,4,138),(3,51,137),(3,19,136),(3,15,135),(3,11,134),(3,10,133),(3,9,132),(3,8,131),(3,3,130),(3,37,129),(3,35,128),(3,34,127),(3,33,126),(3,7,125),(3,6,124),(3,5,123),(3,2,122),(3,1,121),(3,0,120),(2,40,119),(2,39,118),(2,38,117),(2,37,116),(2,36,115),(2,35,114),(2,34,113),(2,33,112),(2,32,111),(2,31,110),(2,30,109),(2,29,108),(2,28,107),(2,27,106),(2,26,105),(2,25,104),(2,24,103),(2,23,102),(2,22,101),(2,21,100),(2,20,99),(2,19,98),(2,18,97),(2,17,96),(2,16,95),(2,15,94),(2,14,93),(2,13,92),(2,12,91),(2,11,90),(2,10,89),(2,9,88),(2,8,87),(2,7,86),(2,6,85),(2,5,84),(2,4,83),(2,3,82),(2,2,81),(2,1,80);
/*!40000 ALTER TABLE `role_menu_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_t`
--

DROP TABLE IF EXISTS `role_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_t` (
  `rolename` varchar(100) default NULL,
  `roledesc` varchar(100) default NULL,
  `id` int(15) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_t`
--

LOCK TABLES `role_t` WRITE;
/*!40000 ALTER TABLE `role_t` DISABLE KEYS */;
INSERT INTO `role_t` VALUES (' ≤√¥»®œﬁ∂º”–','≥¨º∂π‹¿Ì‘±',2),('∆’Õ®”√ªß','À˘”–µƒ»À∂º”–µƒΩ«…´√Ë ˆ–≈œ¢£°',3);
/*!40000 ALTER TABLE `role_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_preference`
--

DROP TABLE IF EXISTS `set_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `set_preference` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `COMPANY_ID` int(10) unsigned NOT NULL,
  `REF_KEY` varchar(100) NOT NULL,
  `REF_VALUE` text,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `insert_date` (`INSERT_DATE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `IP_ADDRESS` varchar(64) default NULL,
  `NAME` varchar(100) default NULL,
  `CONTENT` text,
  `INSERT_DATE` datetime NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_log_user` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  PRIMARY KEY  (`ROLE_ID`,`RESOURCE_ID`),
  KEY `FK_sys_permission` (`RESOURCE_ID`),
  KEY `FK_sys_permission2` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(200) default NULL,
  `PURVIEW` tinyint(4) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `DESCRIPTION` varchar(200) default NULL,
  `STATUS` enum('ACTIVE','INACTIVE') default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `TITLE` enum('Mr','Mrs','Miss','Ms','Dr','Prof') default NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(50) default NULL,
  `USER_TYPE` enum('PERSON','ADMIN') NOT NULL,
  `FIRST_NAME` varchar(50) default NULL,
  `LAST_NAME` varchar(50) default NULL,
  `POST_CODE` varchar(50) default NULL,
  `EMAIL` varchar(100) default NULL,
  `PHONE` varchar(30) default NULL,
  `STATUS` enum('ACTIVE','INACTIVE','TEST','DELETED') NOT NULL,
  `BIRTH_DATE` datetime default NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `INSERT_BY` varchar(32) NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `i_user_name` (`USER_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  PRIMARY KEY  (`USER_ID`,`ROLE_ID`),
  KEY `FK_sys_user_role` (`USER_ID`),
  KEY `FK_sys_user_role2` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_menu_right`
--

DROP TABLE IF EXISTS `user_menu_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_menu_right` (
  `userid` int(11) default NULL,
  `menuid` int(11) default NULL,
  `id` int(15) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_menu_right`
--

LOCK TABLES `user_menu_right` WRITE;
/*!40000 ALTER TABLE `user_menu_right` DISABLE KEYS */;
INSERT INTO `user_menu_right` VALUES (2,43,382),(2,42,381),(2,40,380),(2,39,379),(2,38,378),(2,37,377),(2,35,376),(2,34,375),(2,33,374),(2,32,373),(2,31,372),(2,30,371),(2,29,370),(2,28,369),(2,27,368),(2,26,367),(2,25,366),(2,24,365),(2,23,364),(2,22,363),(2,21,362),(2,20,361),(2,19,360),(2,18,359),(2,17,358),(2,16,357),(2,15,356),(2,14,355),(2,13,354),(2,12,353),(2,11,352),(2,10,351),(2,9,350),(2,8,349),(2,7,348),(2,6,347),(2,5,346),(2,4,345),(2,3,344),(2,2,343),(2,1,342),(3,2,149),(3,3,150),(3,4,151),(3,5,152),(3,6,153),(3,7,154),(3,8,155),(3,9,156),(3,10,157),(3,11,158),(3,12,159),(3,13,160),(3,15,161),(3,16,162),(3,17,163),(3,18,164),(3,19,165),(3,20,166),(3,21,167),(3,22,168);
/*!40000 ALTER TABLE `user_menu_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_right`
--

DROP TABLE IF EXISTS `user_role_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_right` (
  `roleid` int(11) default NULL,
  `userid` int(11) default NULL,
  `id` int(15) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_right`
--

LOCK TABLES `user_role_right` WRITE;
/*!40000 ALTER TABLE `user_role_right` DISABLE KEYS */;
INSERT INTO `user_role_right` VALUES (2,2,23),(2,4,21),(3,4,22);
/*!40000 ALTER TABLE `user_role_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_t`
--

DROP TABLE IF EXISTS `user_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_t` (
  `id` int(10) NOT NULL auto_increment,
  `userName` varchar(1000) default NULL,
  `pass` varchar(1000) default NULL,
  `loginId` varchar(1000) default NULL,
  `orgId` int(10) default NULL,
  `email` varchar(1000) default NULL,
  `phone` varchar(1000) default NULL,
  `mobile` varchar(1000) default NULL,
  `usertype` varchar(1000) default NULL,
  `address` varchar(1000) default NULL,
  `orderId` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_t`
--

LOCK TABLES `user_t` WRITE;
/*!40000 ALTER TABLE `user_t` DISABLE KEYS */;
INSERT INTO `user_t` VALUES (1,'renjie120','KI0Q','test',1111,'111@11.com','18616818351','1111111','56','11111111111',1),(2,'π‹¿Ì‘±','KI0Q','admin',1,'','11111111111111','1','55','1',1),(3,'test2','KI0Q','test2',2,'123@111.com','','','57','',0),(4,'erp','KI0Q','erp',2,'','','','56','',0);
/*!40000 ALTER TABLE `user_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_page`
--

DROP TABLE IF EXISTS `web_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_page` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(100) default NULL,
  `SEQUENCE` int(5) default NULL,
  `TITLE` varchar(100) default NULL,
  `META_KEYWORDS` varchar(255) default NULL,
  `META_DESCRIPTION` varchar(500) default NULL,
  `CONTENT` text,
  `TARGET` set('header','sidebar','footer') default NULL,
  `INSERT_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_page`
--

LOCK TABLES `web_page` WRITE;
/*!40000 ALTER TABLE `web_page` DISABLE KEYS */;
INSERT INTO `web_page` VALUES (1,'',1,'','','','','header,footer','2012-06-19 16:58:20',NULL),(3,'',0,'','','','','header','2011-02-19 20:41:32',NULL),(2,'',2,'','','','','header,footer','2012-06-19 16:58:25',NULL),(5,'',3,'','','','','header,footer','2012-06-19 16:58:29',NULL),(18,'',0,'','','','','header','2012-06-19 16:58:04',NULL),(19,'',0,'','','','','header','2012-06-19 16:58:15',NULL),(20,'',NULL,'','','','','header,footer','2012-06-19 16:57:59',NULL),(21,'',NULL,'','','','','header','2012-06-19 16:59:39',NULL);
/*!40000 ALTER TABLE `web_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_website`
--

DROP TABLE IF EXISTS `web_website`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_website` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `TITLE` varchar(100) default NULL,
  `META_KEYWORDS` varchar(255) default NULL,
  `META_DESCRIPTION` varchar(500) default NULL,
  `TEMPLATE` varchar(20) default NULL,
  `LAYOUT` varchar(20) default NULL,
  `THEME` varchar(20) default NULL,
  `LOGO` text,
  `AREA_HEADER` text,
  `AREA_SIDEBAR` text,
  `AREA_BANNER` text,
  `AREA_FOOTER` text,
  `ICP` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_website`
--

LOCK TABLES `web_website` WRITE;
/*!40000 ALTER TABLE `web_website` DISABLE KEYS */;
INSERT INTO `web_website` VALUES (1,'DWZ Java Web ','DWZ Java RIA jQuery','DWZ Java Web ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `web_website` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `year_t`
--

DROP TABLE IF EXISTS `year_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `year_t` (
  `ID` varchar(4) NOT NULL,
  `VALUE` varchar(4) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `year_t`
--

LOCK TABLES `year_t` WRITE;
/*!40000 ALTER TABLE `year_t` DISABLE KEYS */;
INSERT INTO `year_t` VALUES ('2008','2008'),('2009','2009'),('2010','2010'),('2011','2011'),('2012','2012'),('2013','2013'),('2014','2014'),('2015','2015');
/*!40000 ALTER TABLE `year_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-06 21:06:32
