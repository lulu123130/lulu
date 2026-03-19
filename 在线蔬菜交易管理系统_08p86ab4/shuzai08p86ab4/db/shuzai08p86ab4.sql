-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: shuzai08p86ab4
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `shuzai08p86ab4`
--

/*!40000 DROP DATABASE IF EXISTS `shuzai08p86ab4`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `shuzai08p86ab4` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `shuzai08p86ab4`;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `address` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地址',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人',
  `phone` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `isdefault` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'2026-03-10 04:19:45',11,'宇宙银河系金星1号','金某','13823888881','是'),(2,'2026-03-10 04:19:45',12,'宇宙银河系木星1号','木某','13823888882','是'),(3,'2026-03-10 04:19:45',13,'宇宙银河系水星1号','水某','13823888883','是'),(4,'2026-03-10 04:19:45',14,'宇宙银河系火星1号','火某','13823888884','是'),(5,'2026-03-10 04:19:45',15,'宇宙银河系土星1号','土某','13823888885','是'),(6,'2026-03-10 04:19:45',16,'宇宙银河系月球1号','月某','13823888886','是'),(7,'2026-03-10 04:19:45',17,'宇宙银河系黑洞1号','黑某','13823888887','是'),(8,'2026-03-10 04:19:45',18,'宇宙银河系地球1号','地某','13823888888','是');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'chanpinxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` double DEFAULT NULL COMMENT '单价',
  `maijiazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商户名称',
  PRIMARY KEY (`id`),
  KEY `price` (`price`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`price`) REFERENCES `chanpinxinxi` (`price`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chandi`
--

DROP TABLE IF EXISTS `chandi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chandi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chandi` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产地',
  PRIMARY KEY (`id`),
  UNIQUE KEY `chandi` (`chandi`),
  KEY `chandi_4p85` (`chandi`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产地';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chandi`
--

LOCK TABLES `chandi` WRITE;
/*!40000 ALTER TABLE `chandi` DISABLE KEYS */;
INSERT INTO `chandi` VALUES (1,'2026-03-10 04:19:45','湖南'),(2,'2026-03-10 04:19:45','江西'),(3,'2026-03-10 04:19:45','福建'),(4,'2026-03-10 04:19:45','四川'),(5,'2026-03-10 04:19:45','河南'),(6,'2026-03-10 04:19:45','浙江'),(7,'2026-03-10 04:19:45','江苏'),(8,'2026-03-10 04:19:45','湖北');
/*!40000 ALTER TABLE `chandi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chanpinfenlei`
--

DROP TABLE IF EXISTS `chanpinfenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chanpinfenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chanpinfenlei` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `chanpinfenlei` (`chanpinfenlei`),
  KEY `chanpinfenlei_0ov3` (`chanpinfenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chanpinfenlei`
--

LOCK TABLES `chanpinfenlei` WRITE;
/*!40000 ALTER TABLE `chanpinfenlei` DISABLE KEYS */;
INSERT INTO `chanpinfenlei` VALUES (1,'2026-03-10 04:19:45','豆荚类'),(2,'2026-03-10 04:19:45','瓜类'),(3,'2026-03-10 04:19:45','嫩茎类'),(4,'2026-03-10 04:19:45','茄果类'),(5,'2026-03-10 04:19:45','根茎类'),(6,'2026-03-10 04:19:45','笋类'),(7,'2026-03-10 04:19:45','十字花科类'),(8,'2026-03-10 04:19:45','水生类');
/*!40000 ALTER TABLE `chanpinfenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chanpinxinxi`
--

DROP TABLE IF EXISTS `chanpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chanpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chanpinmingcheng` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `chanpinfenlei` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `zongjinghanliang` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '总净含量',
  `zhucuntiaojian` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贮存条件',
  `shengzhangjijie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生长季节',
  `shengzhangzhouqi` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '生长周期',
  `chandi` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产地',
  `chanpinxiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '产品详情',
  `maijiazhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '卖家账号',
  `onelimittimes` int(11) DEFAULT NULL COMMENT '单限',
  `alllimittimes` int(11) DEFAULT NULL COMMENT '库存',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `totalscore` double DEFAULT '0' COMMENT '评分',
  `price` double NOT NULL COMMENT '价格',
  `onshelves` int(11) DEFAULT '1' COMMENT '是否上架',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  KEY `chanpinxinxi_price` (`price`),
  KEY `chandi` (`chandi`),
  KEY `chanpinfenlei` (`chanpinfenlei`),
  CONSTRAINT `chanpinxinxi_ibfk_1` FOREIGN KEY (`chandi`) REFERENCES `chandi` (`chandi`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chanpinxinxi_ibfk_2` FOREIGN KEY (`chanpinfenlei`) REFERENCES `chanpinfenlei` (`chanpinfenlei`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chanpinxinxi`
--

LOCK TABLES `chanpinxinxi` WRITE;
/*!40000 ALTER TABLE `chanpinxinxi` DISABLE KEYS */;
INSERT INTO `chanpinxinxi` VALUES (1,'2026-03-10 04:19:45','豆角','豆荚类','upload/chanpinxinxi_豆角1.jpg,upload/chanpinxinxi_豆角2.jpg,upload/chanpinxinxi_豆角3.jpg','＜500g','冷藏','一级','60-80天','湖南','淡绿表皮光滑肉质鲜嫩适合清炒','105',1,99,'2026-03-10 12:19:45',0,0,99.9,1,1),(2,'2026-03-10 04:19:45','南瓜','瓜类','upload/chanpinxinxi_南瓜1.jpg,upload/chanpinxinxi_南瓜2.jpg,upload/chanpinxinxi_南瓜3.jpg','＜500g','冷藏','一级','80-100天','江西','表皮黄褐肉质淡黄辛辣芳香去腥提味','102',2,99,'2026-03-10 12:19:45',0,0,99.9,1,2),(3,'2026-03-10 04:19:45','芦笋','嫩茎类','upload/chanpinxinxi_芦笋1.jpg,upload/chanpinxinxi_芦笋2.jpg,upload/chanpinxinxi_芦笋3.jpg','＜500g','冷藏','二级','120-150天','福建','表皮青绿肉质雪白清淡爽口利尿消肿','107',3,99,'2026-03-10 12:19:45',0,0,99.9,1,3),(4,'2026-03-10 04:19:45','青椒','茄果类','upload/chanpinxinxi_青椒1.jpg,upload/chanpinxinxi_青椒2.jpg,upload/chanpinxinxi_青椒3.jpg','＜500g','冷藏','一级','80-100天','四川','叶片直立脆嫩多汁适合沙拉','101',4,99,'2026-03-10 12:19:45',0,0,99.9,1,4),(5,'2026-03-10 04:19:45','胡萝卜','根茎类','upload/chanpinxinxi_胡萝卜1.jpg,upload/chanpinxinxi_胡萝卜2.jpg,upload/chanpinxinxi_胡萝卜3.jpg','＜500g','冷藏','一级','70-100天','河南','通体雪白肉质脆嫩水分充足','106',5,99,'2026-03-10 12:19:45',0,0,99.9,1,5),(6,'2026-03-10 04:19:45','竹笋','笋类','upload/chanpinxinxi_竹笋1.jpg,upload/chanpinxinxi_竹笋2.jpg,upload/chanpinxinxi_竹笋3.jpg','＜500g','冷藏','一级','40-60天','浙江','橙黄表皮果肉厚实香甜粉糯营养丰富','103',6,99,'2026-03-10 12:19:45',0,0,99.9,1,6),(7,'2026-03-10 04:19:45','西兰花','十字花科类','upload/chanpinxinxi_西兰花1.jpg,upload/chanpinxinxi_西兰花2.jpg,upload/chanpinxinxi_西兰花3.jpg','＜500g','冷藏','特级','90-120天','江苏','红透圆润酸甜多汁沙瓤口感','104',7,99,'2026-03-10 12:19:45',0,0,99.9,1,7),(8,'2026-03-10 04:19:45','莲藕','水生类','upload/chanpinxinxi_莲藕1.jpg,upload/chanpinxinxi_莲藕2.jpg,upload/chanpinxinxi_莲藕3.jpg','＜500g','冷藏','一级','120-150天','湖北','紫色球叶质地脆嫩富含花青素','108',8,99,'2026-03-10 12:19:45',0,0,99.9,1,8);
/*!40000 ALTER TABLE `chanpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chargerecord`
--

DROP TABLE IF EXISTS `chargerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chargerecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
  `amount` double NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='充值记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chargerecord`
--

LOCK TABLES `chargerecord` WRITE;
/*!40000 ALTER TABLE `chargerecord` DISABLE KEYS */;
INSERT INTO `chargerecord` VALUES (1,'2026-03-10 04:19:45',1,'用户名1','角色1',1),(2,'2026-03-10 04:19:45',2,'用户名2','角色2',2),(3,'2026-03-10 04:19:45',3,'用户名3','角色3',3),(4,'2026-03-10 04:19:45',4,'用户名4','角色4',4),(5,'2026-03-10 04:19:45',5,'用户名5','角色5',5),(6,'2026-03-10 04:19:45',6,'用户名6','角色6',6),(7,'2026-03-10 04:19:45',7,'用户名7','角色7',7),(8,'2026-03-10 04:19:45',8,'用户名8','角色8',8);
/*!40000 ALTER TABLE `chargerecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chatmessage`
--

DROP TABLE IF EXISTS `chatmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chatmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `fid` bigint(20) NOT NULL COMMENT '好友用户ID',
  `content` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `format` int(11) DEFAULT NULL COMMENT '格式(1:文字，2:图片)',
  `isread` int(11) DEFAULT '0' COMMENT '消息已读(0:未读，1:已读)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatmessage`
--

LOCK TABLES `chatmessage` WRITE;
/*!40000 ALTER TABLE `chatmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','',NULL,3),(25,'bHomeLogo','',NULL,3),(26,'fLoginBackgroundImg','',NULL,3),(27,'fRegisterBackgroudImg','',NULL,3),(28,'fTopLogo','',NULL,3);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discusschanpinxinxi`
--

DROP TABLE IF EXISTS `discusschanpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discusschanpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `score` double DEFAULT NULL COMMENT '评分',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品信息评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discusschanpinxinxi`
--

LOCK TABLES `discusschanpinxinxi` WRITE;
/*!40000 ALTER TABLE `discusschanpinxinxi` DISABLE KEYS */;
/*!40000 ALTER TABLE `discusschanpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `fid` bigint(20) NOT NULL COMMENT '好友用户ID',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `alias` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '别名',
  `type` int(11) DEFAULT '0' COMMENT '类型(0:好友申请，1:好友，2:消息)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人',
  `headportrait` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='蔬菜资讯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2026-03-10 04:19:45','西葫芦','upload/news_西葫芦1.jpg,upload/news_西葫芦2.jpg,upload/news_西葫芦3.jpg','瓜类','淡绿表皮光滑肉质鲜嫩适合清炒','张明','upload/news_headportrait1.jpg'),(2,'2026-03-10 04:19:45','生姜','upload/news_生姜1.jpg,upload/news_生姜2.jpg,upload/news_生姜3.jpg','根茎类','表皮黄褐肉质淡黄辛辣芳香去腥提味','孙伟','upload/news_headportrait2.jpg'),(3,'2026-03-10 04:19:45','冬瓜','upload/news_冬瓜1.jpg,upload/news_冬瓜2.jpg,upload/news_冬瓜3.jpg','瓜类','表皮青绿肉质雪白清淡爽口利尿消肿','周雪','upload/news_headportrait3.jpg'),(4,'2026-03-10 04:19:45','罗马生菜','upload/news_罗马生菜1.jpg,upload/news_罗马生菜2.jpg,upload/news_罗马生菜3.jpg','叶菜类','叶片直立脆嫩多汁适合沙拉','孙雨晴','upload/news_headportrait4.jpg'),(5,'2026-03-10 04:19:45','白萝卜','upload/news_白萝卜1.jpg,upload/news_白萝卜2.jpg,upload/news_白萝卜3.jpg','根茎类','通体雪白肉质脆嫩水分充足','吴磊','upload/news_headportrait5.jpg'),(6,'2026-03-10 04:19:45','南瓜','upload/news_南瓜1.jpg,upload/news_南瓜2.jpg,upload/news_南瓜3.jpg','瓜类','橙黄表皮果肉厚实香甜粉糯营养丰富','李静','upload/news_headportrait6.jpg'),(7,'2026-03-10 04:19:45','西红柿','upload/news_西红柿1.jpg,upload/news_西红柿2.jpg,upload/news_西红柿3.jpg','茄果类','红透圆润酸甜多汁沙瓤口感','高雪','upload/news_headportrait7.jpg'),(8,'2026-03-10 04:19:45','紫甘蓝','upload/news_紫甘蓝1.jpg,upload/news_紫甘蓝2.jpg,upload/news_紫甘蓝3.jpg','甘蓝类','紫色球叶质地脆嫩富含花青素','马超','upload/news_headportrait8.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `buynumber` int(11) DEFAULT NULL COMMENT '购买数量',
  `price` double DEFAULT NULL COMMENT '单价',
  `total` double DEFAULT NULL COMMENT '总价',
  `type` int(11) DEFAULT NULL COMMENT '支付类型',
  `status` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'chanpinxinxi' COMMENT '商品表名',
  `logistics` longtext COLLATE utf8mb4_unicode_ci COMMENT '物流',
  `maijiazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商户名称',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户角色',
  `returnreason` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退货原因',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderid` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shangjia`
--

DROP TABLE IF EXISTS `shangjia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shangjia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `maijiazhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卖家账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `maijiaxingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `yingyezhizhao` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '营业执照',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `youxiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `lianxidianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `maijiazhanghao` (`maijiazhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商家';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shangjia`
--

LOCK TABLES `shangjia` WRITE;
/*!40000 ALTER TABLE `shangjia` DISABLE KEYS */;
INSERT INTO `shangjia` VALUES (21,'2026-03-10 04:19:45','105','123456','张明','upload/shangjia_yingyezhizhao1.jpg','upload/shangjia_touxiang1.jpg','男','773890001@qq.com','13823888881','是','',200),(22,'2026-03-10 04:19:45','102','123456','孙伟','upload/shangjia_yingyezhizhao2.jpg','upload/shangjia_touxiang2.jpg','男','773890002@qq.com','13823888882','是','',200),(23,'2026-03-10 04:19:45','107','123456','周雪','upload/shangjia_yingyezhizhao3.jpg','upload/shangjia_touxiang3.jpg','男','773890003@qq.com','13823888883','是','',200),(24,'2026-03-10 04:19:45','101','123456','孙雨晴','upload/shangjia_yingyezhizhao4.jpg','upload/shangjia_touxiang4.jpg','男','773890004@qq.com','13823888884','是','',200),(25,'2026-03-10 04:19:45','106','123456','吴磊','upload/shangjia_yingyezhizhao5.jpg','upload/shangjia_touxiang5.jpg','男','773890005@qq.com','13823888885','是','',200),(26,'2026-03-10 04:19:45','103','123456','李静','upload/shangjia_yingyezhizhao6.jpg','upload/shangjia_touxiang6.jpg','男','773890006@qq.com','13823888886','是','',200),(27,'2026-03-10 04:19:45','104','123456','高雪','upload/shangjia_yingyezhizhao7.jpg','upload/shangjia_touxiang7.jpg','男','773890007@qq.com','13823888887','是','',200),(28,'2026-03-10 04:19:45','108','123456','马超','upload/shangjia_yingyezhizhao8.jpg','upload/shangjia_touxiang8.jpg','男','773890008@qq.com','13823888888','是','',200);
/*!40000 ALTER TABLE `shangjia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(500) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,26,'103','shangjia','商家','umuslxvit81a9p0bou0zp74ipmvls96i','2026-03-10 04:21:13','2026-03-10 05:21:13');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-03-10 04:19:45','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `youxiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yonghuming` (`yonghuming`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (11,'2026-03-10 04:19:44','005','123456','张明','男','upload/yonghu_touxiang1.jpg',1,'773890001@qq.com','13823888881',200),(12,'2026-03-10 04:19:44','003','123456','孙伟','男','upload/yonghu_touxiang2.jpg',2,'773890002@qq.com','13823888882',200),(13,'2026-03-10 04:19:44','008','123456','周雪','男','upload/yonghu_touxiang3.jpg',3,'773890003@qq.com','13823888883',200),(14,'2026-03-10 04:19:44','004','123456','孙雨晴','男','upload/yonghu_touxiang4.jpg',4,'773890004@qq.com','13823888884',200),(15,'2026-03-10 04:19:44','001','123456','吴磊','男','upload/yonghu_touxiang5.jpg',5,'773890005@qq.com','13823888885',200),(16,'2026-03-10 04:19:44','002','123456','李静','男','upload/yonghu_touxiang6.jpg',6,'773890006@qq.com','13823888886',200),(17,'2026-03-10 04:19:44','006','123456','高雪','男','upload/yonghu_touxiang7.jpg',7,'773890007@qq.com','13823888887',200),(18,'2026-03-10 04:19:44','007','123456','马超','男','upload/yonghu_touxiang8.jpg',8,'773890008@qq.com','13823888888',200);
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-10 23:28:31
