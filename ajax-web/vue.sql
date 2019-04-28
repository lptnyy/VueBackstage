-- --------------------------------------------------------
-- ホスト:                          localhost
-- サーバーのバージョン:                   5.6.41-log - MySQL Community Server (GPL)
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- shirodb のデータベース構造をダンプしています
CREATE DATABASE IF NOT EXISTS `shirodb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `shirodb`;

--  テーブル shirodb.functions の構造をダンプしています
CREATE TABLE IF NOT EXISTS `functions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `type` smallint(6) NOT NULL DEFAULT '0',
  `url` varchar(100) NOT NULL DEFAULT '0',
  `parentId` int(11) NOT NULL DEFAULT '0',
  `stat` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isDel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- テーブル shirodb.functions: ~9 rows (approximately) のデータをダンプしています
DELETE FROM `functions`;
/*!40000 ALTER TABLE `functions` DISABLE KEYS */;
INSERT INTO `functions` (`id`, `name`, `type`, `url`, `parentId`, `stat`, `createTime`, `isDel`) VALUES
	(35, '用户管理', 0, '', 0, 0, '2019-03-12 13:53:52', 0),
	(36, '管理员', 1, '/user', 35, 0, '2019-03-12 13:54:15', 0),
	(37, '角色', 1, '/role', 35, 0, '2019-03-12 13:54:44', 0),
	(38, '栏目', 1, '/functions', 35, 0, '2019-03-12 13:54:59', 0),
	(39, '会员管理', 0, '', 0, 0, '2019-03-13 13:54:38', 1),
	(40, '会员列表', 1, 'www.baidu.com', 39, 0, '2019-03-13 13:55:40', 1),
	(41, '积分列表', 1, 'www.baidu.com', 39, 0, '2019-03-13 13:56:28', 1),
	(42, '商品管理', 0, 'www.baidu.com', 0, 0, '2019-03-15 11:50:47', 0),
	(43, '商品列表', 1, 'www.baidu.com', 42, 0, '2019-03-15 12:07:26', 0),
	(44, '商品添加', 1, 'www.baidu.com', 42, 0, '2019-03-15 13:25:41', 1),
	(45, '哈喽摩托', 1, 'www.baidu.com', 42, 0, '2019-04-28 10:47:43', 0);
/*!40000 ALTER TABLE `functions` ENABLE KEYS */;

--  テーブル shirodb.role の構造をダンプしています
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) NOT NULL DEFAULT '0',
  `stat` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- テーブル shirodb.role: ~4 rows (approximately) のデータをダンプしています
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `roleName`, `stat`, `createTime`) VALUES
	(1, 'admin', 0, '2019-04-28 10:54:59'),
	(10, '哈喽', 0, '2019-04-28 13:22:37'),
	(11, 'web管理员', 0, '2019-04-28 16:52:17'),
	(13, '哈喽2', 0, '2019-04-28 17:46:44');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--  テーブル shirodb.role_functions の構造をダンプしています
CREATE TABLE IF NOT EXISTS `role_functions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL DEFAULT '0',
  `functionId` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `roleId_functionId` (`roleId`,`functionId`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4;

-- テーブル shirodb.role_functions: ~20 rows (approximately) のデータをダンプしています
DELETE FROM `role_functions`;
/*!40000 ALTER TABLE `role_functions` DISABLE KEYS */;
INSERT INTO `role_functions` (`id`, `roleId`, `functionId`, `createTime`) VALUES
	(120, 11, 36, '2019-04-28 17:48:29'),
	(121, 11, 37, '2019-04-28 17:48:29'),
	(122, 11, 38, '2019-04-28 17:48:29'),
	(123, 11, 43, '2019-04-28 17:48:29'),
	(124, 11, 45, '2019-04-28 17:48:29'),
	(125, 10, 36, '2019-04-28 17:48:31'),
	(126, 10, 37, '2019-04-28 17:48:32'),
	(127, 10, 38, '2019-04-28 17:48:32'),
	(128, 10, 43, '2019-04-28 17:48:32'),
	(129, 10, 45, '2019-04-28 17:48:32'),
	(130, 1, 36, '2019-04-28 17:48:34'),
	(131, 1, 37, '2019-04-28 17:48:34'),
	(132, 1, 38, '2019-04-28 17:48:34'),
	(133, 1, 43, '2019-04-28 17:48:34'),
	(134, 1, 45, '2019-04-28 17:48:34'),
	(135, 13, 36, '2019-04-28 17:48:36'),
	(136, 13, 37, '2019-04-28 17:48:36'),
	(137, 13, 38, '2019-04-28 17:48:36'),
	(138, 13, 43, '2019-04-28 17:48:36'),
	(139, 13, 45, '2019-04-28 17:48:36');
/*!40000 ALTER TABLE `role_functions` ENABLE KEYS */;

--  テーブル shirodb.user の構造をダンプしています
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  `userPass` varchar(100) NOT NULL DEFAULT '0',
  `loginNum` int(11) NOT NULL DEFAULT '0',
  `token` varchar(255) NOT NULL DEFAULT '',
  `stat` int(11) NOT NULL DEFAULT '0',
  `lastLloginTime` timestamp NULL DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- テーブル shirodb.user: ~2 rows (approximately) のデータをダンプしています
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `userName`, `roleId`, `userPass`, `loginNum`, `token`, `stat`, `lastLloginTime`, `createTime`) VALUES
	(1, 'admin', 1, 'admin', 57, '1bdd456e-77ec-4796-856d-cb68763d8bbd', 0, '2019-04-28 16:52:01', '2019-03-11 11:53:57'),
	(2, 'user', 2, 'user', 0, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTYyNzM1OTgsInVzZXJuYW1lIjoiYWRtaW4ifQ.FY7R02vHaWKh4FQyTNUIUU0VttR57aQq-SZEnzNYYlA', 0, NULL, '2019-02-25 15:41:39');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--  テーブル shirodb.user_role の構造をダンプしています
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- テーブル shirodb.user_role: ~0 rows (approximately) のデータをダンプしています
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
