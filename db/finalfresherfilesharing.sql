-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2017 at 01:57 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalfresherfilesharing`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(5) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Text'),
(2, 'Graphic'),
(3, 'Spreadsheet'),
(4, 'Presentation'),
(5, 'Multimedia'),
(6, 'Compression'),
(7, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `categories_type`
--

CREATE TABLE `categories_type` (
  `id` int(11) NOT NULL,
  `file_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories_type`
--

INSERT INTO `categories_type` (`id`, `file_type`, `category_id`) VALUES
(1, 'txt', 1),
(2, 'rtf', 1),
(3, 'doc', 1),
(4, 'wpd', 1),
(5, 'sdw', 1),
(6, 'sxw', 1),
(7, 'sam', 1),
(8, 'xml', 1),
(9, 'xhtml', 1),
(10, 'htm', 1),
(11, 'html', 1),
(12, 'jpg', 2),
(13, 'png', 2),
(14, 'gif', 2),
(15, 'svg', 2),
(16, 'tif', 2),
(17, 'tiff', 2),
(18, 'bmp', 2),
(19, 'wpg', 2),
(20, 'pcx', 2),
(26, 'xls', 3),
(27, 'wkq', 3),
(28, 'qpw', 3),
(29, 'sdc', 3),
(30, 'wks', 3),
(31, 'ppt', 4),
(32, 'shw', 4),
(33, 'sdd', 4),
(34, 'zip', 6),
(43, 'ps', 5),
(44, 'pdf', 5),
(45, 'mpg', 5),
(46, 'mpeg', 5),
(47, 'wm', 5),
(48, 'wma', 5),
(49, 'mp3', 5),
(50, 'wav', 5),
(51, 'docx', 1),
(52, 'Other', 7);

-- --------------------------------------------------------

--
-- Table structure for table `download`
--

CREATE TABLE `download` (
  `id` int(11) NOT NULL,
  `id_file` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `datedownload` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `download`
--

INSERT INTO `download` (`id`, `id_file`, `id_user`, `datedownload`) VALUES
(1, 7, 20, '0000-00-00'),
(2, 7, 16, '0000-00-00'),
(3, 4, 16, '2017-03-02'),
(4, 4, 20, '2017-03-10'),
(5, 4, 19, '2017-03-08'),
(6, 6, 16, '2017-03-08'),
(7, 8, 19, '2017-03-15'),
(8, 9, 16, '0000-00-00'),
(9, 9, 19, '0000-00-00'),
(10, 7, 19, '2017-03-09'),
(11, 6, 19, '2017-03-09'),
(12, 6, 19, '2017-03-12'),
(13, 6, 19, '2017-03-11');

-- --------------------------------------------------------

--
-- Table structure for table `files`
--

CREATE TABLE `files` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `dateupload` datetime DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  `id_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `files`
--

INSERT INTO `files` (`id`, `name`, `path`, `size`, `user_id`, `dateupload`, `active`, `id_type`) VALUES
(4, 'abc.docx', NULL, 9878, 20, '2017-03-01 00:00:00', 1, 51),
(6, 'abc', NULL, 123123, 20, '2017-03-09 00:00:00', 1, 52),
(7, 'demo.txt', NULL, 65, 20, '2017-03-10 00:00:00', 1, 1),
(8, 'test.zip', NULL, 1237, 0, '2017-03-08 00:00:00', 1, 34),
(9, 'kiem.zip', NULL, 6755, 0, '2017-03-08 00:00:00', 1, 34);

-- --------------------------------------------------------

--
-- Table structure for table `rank`
--

CREATE TABLE `rank` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sizeupload` double DEFAULT NULL,
  `sizedownload` double DEFAULT NULL,
  `sizerank` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rank`
--

INSERT INTO `rank` (`id`, `name`, `sizeupload`, `sizedownload`, `sizerank`) VALUES
(1, 'Silver', 10, 70, 50),
(2, 'Gold', 20, 1024, 100),
(3, 'Gronze', 5, 50, 20);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'MEMBER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rank_id` int(11) DEFAULT '1',
  `active` int(11) DEFAULT '1',
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `name`, `rank_id`, `active`, `last_name`) VALUES
(0, 'anonymous@gmail.com', '', 'Anonymous', 1, 0, 'Anonymous'),
(16, 'Login@gmail.com', '$2a$10$Ysc6JO4kes6iWbD44vVDK.985lcA1kW30KPWCknoT4CRqghe2gyL.', 'Login', 1, 1, 'Login'),
(19, 'admin1@gmail.com', '$2a$10$jCT4ika04248VdKVP6pdQ.eyUx6PRh6QgMEajzYjj0WWkfpYwMOj6', 'admin', 1, 1, 'admin'),
(20, 'LoginToLogout@gmail.com', '$2a$10$uByTF6sMVyrJVaDLnlpLp.JV7YrJwOB74/Ak3EDvkqCpFmO9VmKPm', 'Login', 1, 1, 'Login');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(16, 2),
(19, 1),
(20, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories_type`
--
ALTER TABLE `categories_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpikl0j3jmyfanl3147grxy6u` (`category_id`);

--
-- Indexes for table `download`
--
ALTER TABLE `download`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_download_idx` (`id_user`),
  ADD KEY `file_download_idx` (`id_file`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_file_idx` (`user_id`),
  ADD KEY `FKaty6roy7josrpv01akx19s3hr` (`id_type`);

--
-- Indexes for table `rank`
--
ALTER TABLE `rank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rank_user_idx` (`rank_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `categories_type`
--
ALTER TABLE `categories_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `download`
--
ALTER TABLE `download`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `files`
--
ALTER TABLE `files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `rank`
--
ALTER TABLE `rank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `categories_type`
--
ALTER TABLE `categories_type`
  ADD CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FKpikl0j3jmyfanl3147grxy6u` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `download`
--
ALTER TABLE `download`
  ADD CONSTRAINT `FK7nktgc034ogwo071s9krcmq0` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK9iw7r1li6wj6vp2tgwevrkc20` FOREIGN KEY (`id_file`) REFERENCES `files` (`id`),
  ADD CONSTRAINT `file_download` FOREIGN KEY (`id_file`) REFERENCES `files` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_download` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `files`
--
ALTER TABLE `files`
  ADD CONSTRAINT `FK_category_file` FOREIGN KEY (`id_type`) REFERENCES `categories_type` (`id`),
  ADD CONSTRAINT `FKaty6roy7josrpv01akx19s3hr` FOREIGN KEY (`id_type`) REFERENCES `categories_type` (`id`),
  ADD CONSTRAINT `FKkw4jwo1uukj6uwrdxsv6u3qjt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_file` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
