-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 05, 2018 at 08:41 PM
-- Server version: 5.7.23
-- PHP Version: 7.1.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user_details`
--
CREATE DATABASE IF NOT EXISTS `user_details` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `user_details`;

-- --------------------------------------------------------

--
-- Table structure for table `alaram_emergency_message_master`
--

DROP TABLE IF EXISTS `alaram_emergency_message_master`;
CREATE TABLE `alaram_emergency_message_master` (
  `id` int(11) NOT NULL,
  `emergency_message_title` varchar(900) DEFAULT NULL,
  `message_desc` text,
  `entry_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alaram_emergency_message_master`
--

INSERT INTO `alaram_emergency_message_master` (`id`, `emergency_message_title`, `message_desc`, `entry_date`, `status`) VALUES
(1, 'Hospital Alert', ' Its Emergency in Karur Hospital', '2018-07-03 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `blood_req`
--

DROP TABLE IF EXISTS `blood_req`;
CREATE TABLE `blood_req` (
  `u_id` int(11) NOT NULL,
  `u_name` varchar(50) NOT NULL,
  `req_blood_grp` varchar(7) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `priority` varchar(50) NOT NULL DEFAULT 'low',
  `b_bank_phone` bigint(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood_req`
--

INSERT INTO `blood_req` (`u_id`, `u_name`, `req_blood_grp`, `status`, `priority`, `b_bank_phone`) VALUES
(1, 'ramya', 'B+ve', 1, 'low', 432567132),
(1, 'ramya', 'B+ve', 1, 'low', 432567132);

-- --------------------------------------------------------

--
-- Table structure for table `doc_login`
--

DROP TABLE IF EXISTS `doc_login`;
CREATE TABLE `doc_login` (
  `h_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `d_name` varchar(50) NOT NULL,
  `d_specialization` varchar(100) NOT NULL,
  `d_experience` varchar(70) NOT NULL,
  `password` varchar(50) NOT NULL,
  `d_email` varchar(50) NOT NULL,
  `d_mobile` bigint(10) NOT NULL,
  `d_hospital` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(12) NOT NULL,
  `dob` date NOT NULL,
  `bloodgroup` varchar(7) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `pincode` int(6) NOT NULL,
  `confirmation` int(11) NOT NULL DEFAULT '0',
  `req_type` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doc_login`
--

INSERT INTO `doc_login` (`h_id`, `d_id`, `d_name`, `d_specialization`, `d_experience`, `password`, `d_email`, `d_mobile`, `d_hospital`, `username`, `age`, `gender`, `dob`, `bloodgroup`, `address`, `city`, `pincode`, `confirmation`, `req_type`, `date`) VALUES
(1, 1, 'sivaram', 'cardio', '10yrs', 'hello', 'sivaram@gmail.com', 9876543210, 'KG', '', 0, 'male', '0000-00-00', '', '', '', 0, 0, 0, '0000-00-00'),
(2, 2, 'ramya', 'brain', '5', 'hello', 'ramya@gmail.com', 890768541, 'KGH', 'ramya', 30, 'female', '1988-07-09', 'A+ve', '100/9', 'karur', 639114, 1, 0, '0000-00-00'),
(2, 3, 'sruthi', 'cardio', '5', 'hello', 'sruthi@gmail.com', 8976876567, 'KGH', 'sruthi', 30, 'female', '1988-07-09', 'A+ve', '\r\n324/23', 'karur', 639114, 0, 0, '0000-00-00'),
(2, 4, 'sruthi', 'cardio', '5years', 'hello', 'sruthi@gmail.com', 9876543210, 'kg', 'sruthi', 30, 'female', '2018-08-02', 'A+ve', 'gandhipuram', 'karur', 639114, 0, 0, '0000-00-00'),
(2, 5, 'sri', 'cardio', '5yrs', 'hello', 'sri@gmail.com', 9087654321, 'HL', 'sri', 30, 'female', '2000-05-09', 'B-ve', 'gandhipuram', 'cbe', 630914, 1, 0, '2018-08-02'),
(1, 6, 'Msk', 'KIDNEY', '4', 'mask', 'mask@gmail.com', 9874663210, 'MSK', 'Sathish', 21, 'male', '2018-08-02', 'B+', 'ksdjfg', 'dkjfh', 0, 1, 0, '2018-08-02');

-- --------------------------------------------------------

--
-- Table structure for table `doc_prescription`
--

DROP TABLE IF EXISTS `doc_prescription`;
CREATE TABLE `doc_prescription` (
  `d_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_disease` varchar(100) NOT NULL,
  `p_condition` varchar(50) NOT NULL,
  `prescription` varchar(500) NOT NULL,
  `mobile` bigint(10) NOT NULL,
  `entry_date` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doc_prescription`
--

INSERT INTO `doc_prescription` (`d_id`, `u_id`, `p_id`, `p_name`, `p_disease`, `p_condition`, `prescription`, `mobile`, `entry_date`, `status`) VALUES
(1, 1, 1, 'ramya', 'fever', 'good', 'amoxilin, dollo', 0, '0000-00-00', 0),
(1, 1, 2, 'ajay', 'fever', 'bad', 'dolo650', 0, '0000-00-00', 0),
(1, 1, 2, 'ajay', 'fever', 'bad', 'dolo650', 0, '0000-00-00', 0),
(1, 1, 2, 'sivani', 'fever', 'good', 'dollo capsules 2', 8769504321, '2018-09-09', 1),
(1, 1, 2, 'sivani', 'fever', 'good', 'dollo capsules 2', 8769504321, '2018-09-09', 1),
(1, 1, 2, 'priya', 'fever', 'good', 'dollo650', 90879887766, '2018-08-03', 1),
(1, 0, 1, 'priya', 'fever', 'good', 'dollo650', 90879887766, '2018-08-03', 1),
(6, 0, 1, 'Priya', 'Fever', 'Good', 'Dollo650', 9080706050, '2018-08-05', 1),
(6, 0, 1, 'Priya', 'Fever', 'Good', 'Dollo650', 9080706050, '2018-08-05', 1),
(6, 0, 1, 'Priya', 'Fever', 'Good', 'Dollo650', 9080706050, '2018-08-05', 1),
(6, 0, 2, 'Priya', 'Jaundice', 'High', 'Notf', 90808645454, '2018-08-03', 1),
(6, 0, 2, 'Priya', 'Jaundice', 'High', 'Notf', 90808645454, '2018-08-03', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hos_login`
--

DROP TABLE IF EXISTS `hos_login`;
CREATE TABLE `hos_login` (
  `hos_name` varchar(100) NOT NULL,
  `h_id` int(11) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `pincode` int(6) NOT NULL,
  `confirmation` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hos_login`
--

INSERT INTO `hos_login` (`hos_name`, `h_id`, `pass`, `address`, `city`, `pincode`, `confirmation`) VALUES
('KG', 1, 'hello', '100/9', 'karur', 639114, 1),
('KRISHNA', 2, 'hello', '324/90', 'karur', 639114, 0),
('KGH', 9, 'hello', '324/34', 'karur', 639114, 0),
('SDF', 10, 'hello', '54/43', 'karur', 639114, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy_req`
--

DROP TABLE IF EXISTS `pharmacy_req`;
CREATE TABLE `pharmacy_req` (
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `prescription` varchar(500) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `d_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy_req`
--

INSERT INTO `pharmacy_req` (`u_id`, `p_id`, `p_name`, `prescription`, `status`, `d_id`) VALUES
(1, 1, 'ramya', 'amoxilin, dollo', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbltips`
--

DROP TABLE IF EXISTS `tbltips`;
CREATE TABLE `tbltips` (
  `find_id` int(11) NOT NULL,
  `fld_title` varchar(255) NOT NULL,
  `fld_description` varchar(255) NOT NULL,
  `fld_content` text NOT NULL,
  `fld_link` varchar(255) NOT NULL,
  `fld_img_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbltips`
--

INSERT INTO `tbltips` (`find_id`, `fld_title`, `fld_description`, `fld_content`, `fld_link`, `fld_img_url`) VALUES
(1, 'Meditation', 'Be calm...', 'Meditation is an approach to training the mind, similar to the way that fitness is an approach to training the body. But many meditation techniques exist — so how do you learn how to meditate?\r\n\r\n“In Buddhist tradition, the word ‘meditation’ is equivalent to a word like ‘sports’ in the U.S. It’s a family of activities, not a single thing,” University of Wisconsin neuroscience lab director Richard J. Davidson, Ph.D., told The New York Times. And different meditation practices require different mental skills.\r\n\r\nIt’s extremely difficult for a beginner to sit for hours and think of nothing or have an “empty mind.” We have some tools such as a beginner mediation DVD or a brain sensing headband to help you through this process when you are starting out. In general, the easiest way to begin meditating is by focusing on the breath — an example of one of the most common approaches to meditation: concentration.', 'https://en.wikipedia.org/wiki/Meditation', 'https://blog.bulletproof.com/wp-content/uploads/2015/03/meditation_sun.jpg'),
(2, 'Yoga', 'Be fit...', 'Yoga is an ancient physical and spiritual discipline and branch of philosophy that originated in India reportedly more than 5,000 years ago. The word yoga comes from the Sanskrit word yuj, which means to yoke, join, or unite. The Iyengar school of yoga defines yuj as the \"joining or integrating of all aspects of the individual - body with mind and mind with soul - to achieve a happy, balanced and useful life.\" The ultimate aim of yoga, they claim, is to reach kaivalya (emancipation or ultimate freedom).\r\nThere is no written record of who invented yoga because it was practiced by yogis (yoga practitioners) long before any written account of it could have come into existence. Yogis over the millennia passed down the discipline to their students, and many different schools of yoga developed as it spread. The earliest written record of yoga, and one of the oldest texts in existence, is generally believed to have been written by Patanjali, an Indian yogic sage who lived somewhere between 2,000 and 2,500 years ago. Patanjali is credited with writing the Yoga Sutras (sutra means \"thread\" in Sanskrit), which are the principles, philosophy, and practices of yoga that are still followed today. Although many schools of yoga have evolved over the centuries, they all follow these same fundamental principles. Buddhism and other Eastern spiritual traditions use many of the yoga techniques or derivations of those techniques', 'https://en.wikipedia.org/wiki/Yoga', 'https://images.pexels.com/photos/588561/pexels-photo-588561.jpeg?auto=compress&cs=tinysrgb&h=350'),
(3, 'Sleep', 'Key to get good sleep', 'Do you ever struggle to sleep? The key to a good night’s sleep, according to David, is to know that what God thinks about your right standing before him is way more important than what you or others believe about it. In verse 1 of the Psalm, David uses a name for God that isn’t used anywhere else in Scripture. He calls him “God of my righteousness.”\r\nAs David was being pursued by forces led by his son Absalom, he was having a lot of accusations thrown at him. They must have kept him up at night. “You are a sham, David. You were a lousy king. Remember what you did with Bathsheba? Even your own son hates you.”\r\nI reckon that a lot of us have voices of accusation that keep us up at night. I don’t know what they sound like to you, but I certainly experience them at times in my life.\r\n“How can you lead these people when you are such a wreck yourself? You barely prayed today; what if everyone in your church prayed like you? If people knew the real you, they would reject you.” Recognize any of these?\r\nNow that’s reason to sleep easy.', 'https://en.wikipedia.org/wiki/Sleep', 'https://images.agoramedia.com/everydayhealth/gcms/SG-Why-Sleep-Matters-722x406.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user_med_history`
--

DROP TABLE IF EXISTS `user_med_history`;
CREATE TABLE `user_med_history` (
  `u_id` int(11) NOT NULL,
  `u_name` varchar(50) NOT NULL,
  `hospital1` varchar(100) NOT NULL,
  `date1` date NOT NULL,
  `medic1` varchar(100) NOT NULL,
  `hospital2` varchar(100) NOT NULL,
  `date2` date NOT NULL,
  `medic2` varchar(100) NOT NULL,
  `hospital3` varchar(100) NOT NULL,
  `date3` date NOT NULL,
  `medic3` varchar(100) NOT NULL,
  `hospital4` varchar(100) NOT NULL,
  `date4` date NOT NULL,
  `medic4` varchar(100) NOT NULL,
  `hospital5` varchar(100) NOT NULL,
  `date5` date NOT NULL,
  `medic5` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_med_history`
--

INSERT INTO `user_med_history` (`u_id`, `u_name`, `hospital1`, `date1`, `medic1`, `hospital2`, `date2`, `medic2`, `hospital3`, `date3`, `medic3`, `hospital4`, `date4`, `medic4`, `hospital5`, `date5`, `medic5`) VALUES
(1, 'ajay', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo6501', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo650'),
(1, 'ramya', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo6501', 'KG Hospital, karur', '2012-05-09', 'Dollo650', 'KG Hospital, karur', '2012-05-09', 'Dollo650'),
(7, 'ramya', 'KG Hospital, coimbatore', '2012-05-09', 'Amoxilin capsules', 'KG Hospital, coimbatore', '2012-05-09', 'Amoxilin capsules', 'KG Hospital, coimbatore', '2012-05-09', 'Amoxilin capsules', 'KG Hospital, coimbatore', '2012-05-09', 'Amoxilin capsules', 'KG Hospital, coimbatore', '2012-05-09', 'Amoxilin capsules');

-- --------------------------------------------------------

--
-- Table structure for table `user_reg`
--

DROP TABLE IF EXISTS `user_reg`;
CREATE TABLE `user_reg` (
  `u_id` int(11) NOT NULL,
  `u_name` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `email` varchar(70) NOT NULL,
  `phone` bigint(10) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(12) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `bloodgroup` varchar(7) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `pincode` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_reg`
--

INSERT INTO `user_reg` (`u_id`, `u_name`, `pass`, `email`, `phone`, `first_name`, `last_name`, `age`, `gender`, `dob`, `bloodgroup`, `address`, `city`, `pincode`) VALUES
(1, 'Ramya', 'hello', 'ramya@gmail.com', 9876543210, 'Ramya', 'vijay', 20, 'Female', '2018-05-07', 'B+ve', '6, pattinathar street', 'karur', 639114),
(7, 'sethu', 'hello', 'ramya@gmail.com', 9876543210, '', '', 0, '', '0000-00-00', '', '', '', 0),
(8, 'priya25', 'priya', 'pr@gmail.com', 124567890, '', '', 0, '', '0000-00-00', '', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_req`
--

DROP TABLE IF EXISTS `user_req`;
CREATE TABLE `user_req` (
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_age` int(11) NOT NULL,
  `p_addr` varchar(100) NOT NULL,
  `h_issue` varchar(50) NOT NULL,
  `doc_specialization` varchar(100) NOT NULL,
  `doc_name` varchar(50) NOT NULL,
  `hos_name` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `slot` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `d_id` int(11) NOT NULL,
  `appointment_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_req`
--

INSERT INTO `user_req` (`u_id`, `p_id`, `p_name`, `p_age`, `p_addr`, `h_issue`, `doc_specialization`, `doc_name`, `hos_name`, `date`, `slot`, `status`, `d_id`, `appointment_status`) VALUES
(1, 1, 'ramya', 20, '10/24A', 'fever', 'general', 'sivaram', 'kg', '2018-07-05', '1to2pm', 1, 1, 'Accepted'),
(1, 2, 'ramya', 20, '10/24A', 'fever', 'general', 'sivaram', 'kg', '2018-07-05', '1to2pm', 1, 6, 'Accepted');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alaram_emergency_message_master`
--
ALTER TABLE `alaram_emergency_message_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blood_req`
--
ALTER TABLE `blood_req`
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `doc_login`
--
ALTER TABLE `doc_login`
  ADD PRIMARY KEY (`d_id`),
  ADD KEY `h_id` (`h_id`),
  ADD KEY `d_id` (`d_id`);

--
-- Indexes for table `doc_prescription`
--
ALTER TABLE `doc_prescription`
  ADD KEY `u_id` (`u_id`),
  ADD KEY `p_id` (`p_id`),
  ADD KEY `d_id` (`d_id`),
  ADD KEY `prescription` (`prescription`);

--
-- Indexes for table `hos_login`
--
ALTER TABLE `hos_login`
  ADD PRIMARY KEY (`h_id`);

--
-- Indexes for table `pharmacy_req`
--
ALTER TABLE `pharmacy_req`
  ADD KEY `u_id` (`u_id`),
  ADD KEY `p_id` (`p_id`),
  ADD KEY `d_id` (`d_id`),
  ADD KEY `prescription` (`prescription`);

--
-- Indexes for table `tbltips`
--
ALTER TABLE `tbltips`
  ADD PRIMARY KEY (`find_id`);

--
-- Indexes for table `user_med_history`
--
ALTER TABLE `user_med_history`
  ADD KEY `u_id` (`u_id`) USING BTREE;

--
-- Indexes for table `user_reg`
--
ALTER TABLE `user_reg`
  ADD PRIMARY KEY (`u_id`) USING BTREE;

--
-- Indexes for table `user_req`
--
ALTER TABLE `user_req`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `u_id` (`u_id`),
  ADD KEY `d_id` (`d_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alaram_emergency_message_master`
--
ALTER TABLE `alaram_emergency_message_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doc_login`
--
ALTER TABLE `doc_login`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `hos_login`
--
ALTER TABLE `hos_login`
  MODIFY `h_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbltips`
--
ALTER TABLE `tbltips`
  MODIFY `find_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_reg`
--
ALTER TABLE `user_reg`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_req`
--
ALTER TABLE `user_req`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blood_req`
--
ALTER TABLE `blood_req`
  ADD CONSTRAINT `blood_req_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user_reg` (`u_id`);

--
-- Constraints for table `doc_login`
--
ALTER TABLE `doc_login`
  ADD CONSTRAINT `doc_login_ibfk_1` FOREIGN KEY (`h_id`) REFERENCES `hos_login` (`h_id`);

--
-- Constraints for table `doc_prescription`
--
ALTER TABLE `doc_prescription`
  ADD CONSTRAINT `doc_prescription_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `user_req` (`p_id`),
  ADD CONSTRAINT `doc_prescription_ibfk_3` FOREIGN KEY (`d_id`) REFERENCES `doc_login` (`d_id`);

--
-- Constraints for table `pharmacy_req`
--
ALTER TABLE `pharmacy_req`
  ADD CONSTRAINT `pharmacy_req_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user_reg` (`u_id`),
  ADD CONSTRAINT `pharmacy_req_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `user_req` (`p_id`),
  ADD CONSTRAINT `pharmacy_req_ibfk_3` FOREIGN KEY (`d_id`) REFERENCES `doc_login` (`d_id`),
  ADD CONSTRAINT `pharmacy_req_ibfk_4` FOREIGN KEY (`prescription`) REFERENCES `doc_prescription` (`prescription`);

--
-- Constraints for table `user_med_history`
--
ALTER TABLE `user_med_history`
  ADD CONSTRAINT `user_med_history_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user_reg` (`u_id`);

--
-- Constraints for table `user_req`
--
ALTER TABLE `user_req`
  ADD CONSTRAINT `user_req_ibfk_1` FOREIGN KEY (`d_id`) REFERENCES `doc_login` (`d_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
