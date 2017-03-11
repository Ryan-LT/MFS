-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2017 at 06:16 PM
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
(5, 4, 19, '2017-03-08');

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
  `active` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `files`
--

INSERT INTO `files` (`id`, `name`, `path`, `size`, `user_id`, `dateupload`, `active`) VALUES
(4, 'abc.docx', NULL, 9878, 20, '2017-03-01 00:00:00', 1),
(6, 'abc', NULL, 123123, 20, '2017-03-09 00:00:00', 1),
(7, 'demo.txt', NULL, 65, 20, '2017-03-10 00:00:00', 1),
(8, 'test.zip', NULL, 1237, 0, '2017-03-08 00:00:00', 1),
(9, 'kiem.zip', NULL, 6755, 0, '2017-03-08 00:00:00', 1);

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
  ADD KEY `user_file_idx` (`user_id`);

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
-- AUTO_INCREMENT for table `download`
--
ALTER TABLE `download`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `files`
--
ALTER TABLE `files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `rank`
--
ALTER TABLE `rank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
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
