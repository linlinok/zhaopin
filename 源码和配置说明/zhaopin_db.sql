/*
SQLyog Job Agent Version 8.32 Copyright(c) Webyog Softworks Pvt. Ltd. All Rights Reserved.


MySQL - 5.1.32-community : Database - sheji_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sheji_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sheji_db`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `adminPassword` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `t_admin` */

insert  into `t_admin` values (3,'admin','admin');

/*Table structure for table `t_baoming` */

DROP TABLE IF EXISTS `t_baoming`;

CREATE TABLE `t_baoming` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banji` varchar(255) DEFAULT NULL,
  `cj1` varchar(255) DEFAULT NULL,
  `cj2` varchar(255) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `leixing` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `result` text,
  `shenhe` varchar(255) DEFAULT NULL,
  `shuoming` text,
  `stime` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `zhuanye` varchar(255) DEFAULT NULL,
  `zshuoming` text,
  `ztime` varchar(255) DEFAULT NULL,
  `zuopin` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `xiangmuid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC2A37A4DC6C6AB` (`xiangmuid`),
  KEY `FKAC2A37A1433B443` (`userid`),
  CONSTRAINT `FKAC2A37A1433B443` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKAC2A37A4DC6C6AB` FOREIGN KEY (`xiangmuid`) REFERENCES `t_xiangmu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_baoming` */

/*Table structure for table `t_dati` */

DROP TABLE IF EXISTS `t_dati`;

CREATE TABLE `t_dati` (
  `daTiId` int(11) NOT NULL AUTO_INCREMENT,
  `shitiId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `userDaan` varchar(255) NOT NULL,
  PRIMARY KEY (`daTiId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_dati` */

insert  into `t_dati` values (16,4,46,'A'),(17,5,46,'B'),(18,6,46,'C');

/*Table structure for table `t_jianli` */

DROP TABLE IF EXISTS `t_jianli`;

CREATE TABLE `t_jianli` (
  `jianliId` int(11) NOT NULL AUTO_INCREMENT,
  `xuexiao` varchar(255) NOT NULL,
  `zhuanye` varchar(255) NOT NULL,
  `jineng` varchar(255) NOT NULL,
  `xiangxi` text NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`jianliId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_jianli` */

insert  into `t_jianli` values (3,'经贸','地理','地理','地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理地理',46);

/*Table structure for table `t_juanti` */

DROP TABLE IF EXISTS `t_juanti`;

CREATE TABLE `t_juanti` (
  `juanTiId` int(11) NOT NULL AUTO_INCREMENT,
  `shijuanId` int(11) NOT NULL,
  `shitiId` int(11) NOT NULL,
  PRIMARY KEY (`juanTiId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_juanti` */

insert  into `t_juanti` values (6,3,4),(7,3,5),(8,3,6);

/*Table structure for table `t_notice` */

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `ncontent` text,
  `ntitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_notice` */

/*Table structure for table `t_pic` */

DROP TABLE IF EXISTS `t_pic`;

CREATE TABLE `t_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `href` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_pic` */

insert  into `t_pic` values (1,'.','展示图片一','pic1.jpg'),(2,'.','展示图片二','pic2.jpg'),(3,'.','展示图片三','pic3.jpg'),(4,'.','展示图片四','pic4.jpg'),(5,'.','展示图片五','pic5.jpg');

/*Table structure for table `t_qiuzhi` */

DROP TABLE IF EXISTS `t_qiuzhi`;

CREATE TABLE `t_qiuzhi` (
  `qiuzhiId` int(11) NOT NULL AUTO_INCREMENT,
  `zhiweiId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `qiuzhiMark` varchar(255) NOT NULL,
  `qiuzhiType` int(11) NOT NULL COMMENT '0未发通知1等待面试2面试完成',
  PRIMARY KEY (`qiuzhiId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_qiuzhi` */

insert  into `t_qiuzhi` values (2,2,46,'我很厉害',1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `roleMark` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `t_role` */

insert  into `t_role` values (1,'公司人员','负责招聘'),(2,'求职人员','找工作');

/*Table structure for table `t_shijuan` */

DROP TABLE IF EXISTS `t_shijuan`;

CREATE TABLE `t_shijuan` (
  `shijuanId` int(11) NOT NULL AUTO_INCREMENT,
  `shijuanName` varchar(255) NOT NULL,
  `shijuanMark` varchar(255) NOT NULL,
  `zhiweiId` int(11) NOT NULL,
  PRIMARY KEY (`shijuanId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_shijuan` */

insert  into `t_shijuan` values (3,'地理','地理',2);

/*Table structure for table `t_shiti` */

DROP TABLE IF EXISTS `t_shiti`;

CREATE TABLE `t_shiti` (
  `shitiId` int(11) NOT NULL AUTO_INCREMENT,
  `shitiName` varchar(255) NOT NULL,
  `shitiD1` varchar(255) NOT NULL,
  `shitiD2` varchar(255) NOT NULL,
  `shitiD3` varchar(255) NOT NULL,
  `shitiD4` varchar(255) NOT NULL,
  `shitiDaan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shitiId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_shiti` */

insert  into `t_shiti` values (4,'中国首都','北京','上海','南京','天津','A'),(5,'世界最大的国家','中国','俄罗斯','美国','加拿大','B'),(6,'地球是什么形状','方形','菱形','圆形','长方形','C');

/*Table structure for table `t_user1` */

DROP TABLE IF EXISTS `t_user1`;

CREATE TABLE `t_user1` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userPassword` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userXingming` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userAge` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userSex` varchar(255) CHARACTER SET utf8 NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=gb2312;

/*Data for the table `t_user1` */

insert  into `t_user1` values (45,'zhaopin1','zhaopin1','招聘1','20','男',1),(46,'qiuzhi1','qiuzhi1','求职1','20','男',2);

/*Table structure for table `t_xiangmu` */

DROP TABLE IF EXISTS `t_xiangmu`;

CREATE TABLE `t_xiangmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctime` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `jieshao` text,
  `logo` varchar(255) DEFAULT NULL,
  `mingchen` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_xiangmu` */

/*Table structure for table `t_zhiwei` */

DROP TABLE IF EXISTS `t_zhiwei`;

CREATE TABLE `t_zhiwei` (
  `zhiweiId` int(11) NOT NULL AUTO_INCREMENT,
  `zhiweiName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `zhiweiMark` varchar(255) CHARACTER SET utf8 NOT NULL,
  `zhiweiNum` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`zhiweiId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `t_zhiwei` */

insert  into `t_zhiwei` values (2,'地理','地理专业',10,45);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
